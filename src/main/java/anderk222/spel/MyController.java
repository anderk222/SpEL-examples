package anderk222.spel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/spEL")
public class MyController {
    
    private final MyService service;

    public MyController(MyService service){

        this.service = service;

    }
    
    @GetMapping("helloWorldWithSpel")
    public String helloWorldWithSpel() {
        
        return service.helloWorldWithSpel();

    }

    @GetMapping("helloWorldCallSelfMethod")
    public String helloWorldCallSelfMethod() {

        return service.helloWorldCallSelfMethod();

    }

    @GetMapping("helloWorldGetBytes")
    public byte[] helloWorldGetBytes() {

        return service.helloWorldGetBytes();

    }

    @GetMapping("helloWorldGetLengthBytes")
    public Integer helloWorldGetLengthBytes() {

            

        return service.helloWorldGetLengthBytes();

    }

    @GetMapping("helloWorldWithConstructor")
    public String helloWorldWithConstructor() {

        return service.helloWorldWithConstructor();
    }

    @PostMapping("userInfoFromUserInstance")
    public String userInfoFromUserInstance(@RequestBody Usuario request ) {
        return service.userInfoFromUserInstance(request);
    }

    @PostMapping(path = "bindTemplateFromFile", produces = "text/html; charset=UTF-8")
    public String bindTemplateFromFile(@RequestBody Usuario request) throws IOException {
        
        return service.bindTemplateFromFile(request);

    }

    @PostMapping(path = "bindTemplate", produces = "text/html; charset=UTF-8")
    public String bindTemplate(@RequestBody BindTemplate request) throws IOException {
        
        return service.bindTemplate(request);

    }

}