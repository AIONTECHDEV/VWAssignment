package com.example.digitalhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket transactionsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/accounts.*"))
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(apiEndPointsInfo());
				
	}
	
	private ApiInfo apiEndPointsInfo() {
		
		return new ApiInfoBuilder()
				.title("Transactions REST API")
				.license("Apache License 2.0")
				.version("1.0")
				.build();
		
	}
	
	
}
