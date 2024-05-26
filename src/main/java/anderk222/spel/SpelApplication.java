package anderk222.spel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@SpringBootApplication
public class SpelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpelApplication.class, args);
	}

	@Bean
	ExpressionParser expressionParser(){

		SpelParserConfiguration configuration = new SpelParserConfiguration(
			null, null, true, true, 0);

		return new SpelExpressionParser(configuration);

	}

}
