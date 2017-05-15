package com.devplant.introduction.configuration;

import com.devplant.introduction.properties.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableConfigurationProperties(
		{ AdminUserProperties.class,  SecurityProperties.class })
public class LibraryConfiguration {

	@Configuration
	@EnableSwagger2
	public static class SwaggerConfig {
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("com.devplant")).paths(PathSelectors.any()).build();
		}
	}

}
