package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.ads.IntroApp"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(info());
    }

    private ApiInfo info(){
        return new ApiInfo("Documentação Oficial", "Aplicação Spring", "1.0",
                "http://www.jlgregorio.com.br",
                new Contact("Jorge Luís Gregório", "http://www.jlgregorio.com.br",
                        "jorg.gregorio@fatec.sp.gov.br" ),
                "License X", "http://www.jlgregorio.com.br",
                Collections.emptyList());
    }
}
