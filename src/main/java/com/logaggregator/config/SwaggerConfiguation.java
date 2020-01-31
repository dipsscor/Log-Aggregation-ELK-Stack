package com.logaggregator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguation {


	// SWAGGER 2.0 INITIATION

			@Bean
			public Docket ACCTomerManagementApi() {
				return new Docket(DocumentationType.SWAGGER_2)
						.groupName("ACCOUNT-MANAGEMENT").select()
						.apis(RequestHandlerSelectors.any())
						.paths(PathSelectors.ant("/API/**")).build()
						.apiInfo(accountManagementMetaData());
			}
		

			

			//ACCOUNT MANAGEMENT METADATA
			private ApiInfo accountManagementMetaData() {
				ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
				apiInfoBuilder
						.title("REST APIs to TEST Log Aggregation")
						.description("REST APIs to TEST Log Aggregation");
		
				return apiInfoBuilder.build();
			}
			
			
}
