package anderk222.spel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class MyService {

    @Autowired
    ExpressionParser parser;

    public String helloWorldWithSpel() {

        Expression ex = parser.parseExpression("'Hola Mundo'");

        return (String) ex.getValue();

    }

    public String helloWorldCallSelfMethod() {

        Expression ex = parser.parseExpression("'Hola Mundo '.concat('!!')");

        return ex.getValue(String.class);

    }

    public byte[] helloWorldGetBytes() {

        Expression ex = parser.parseExpression("'Hola Mundo'.bytes");

        return (byte[]) ex.getValue();

    }

    public Integer helloWorldGetLengthBytes() {

        Expression ex = parser.parseExpression("'Hola Mundo'.bytes.length");

        return (Integer) ex.getValue();

    }

    public String helloWorldWithConstructor() {

        Expression ex = parser
                .parseExpression("new String('Hola mundo').toUpperCase()");

        return ex.getValue(String.class);

    }

    public String userInfoFromUserInstance(Usuario usuario){

        Expression ex = parser
        .parseExpression("'he/she is '.concat(name).concat(edad >=18 ? ' mayor' : ' menor').concat(' de edad')");

        return ex.getValue(usuario, String.class);


    }

    public String bindTemplateFromFile(Usuario data) throws IOException{

        BufferedReader reader = new BufferedReader(
            new FileReader(ResourceUtils.getFile("classpath:templates/plantilla.html")));
        
        TemplateParserContext parserContext = new TemplateParserContext(
            "{{", "}}");

        
        String line;
        StringBuilder concatenacion = new StringBuilder();
        Expression expression;

        while ((line = reader.readLine()) !=null) {

            expression = parser.parseExpression(line, parserContext);

            concatenacion.append(expression.getValue(data,String.class));
        }

        reader.close();

        return concatenacion.toString();

    }

    // Llenar template de forma dinamica.
    // Este ejemplo se realizo con fines de aprendizaje.
    // NO DEBE SER REPLICADO EN CASOS CUYO OBJETIVO
    // NO ES EL APRENDIZAJE.
    public String bindTemplate(BindTemplate data){

        int chunkSize = SpelParserConfiguration.DEFAULT_MAX_EXPRESSION_LENGTH;

        int length = data.getTemplate().length();

        TemplateParserContext templateParserContext = new TemplateParserContext(
            "{{", "}}");

        List<String> chunks = new ArrayList<>();  
        
        for (int i = 0; i < length; i += chunkSize) {
            chunks.add(data.getTemplate().substring(i, Math.min(length, i + chunkSize)));
        };

        StringBuilder sBuilder = new StringBuilder();

        StandardEvaluationContext context = new StandardEvaluationContext(data.getDatos());

        context.addPropertyAccessor(new MapAccessor());

        for(String chunk : chunks){

           Expression expression = parser.parseExpression(chunk, templateParserContext);

           sBuilder.append(expression.getValue(context, String.class));

        }

        return sBuilder.toString();

    }

}   