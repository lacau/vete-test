package io.redspark;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(paths())
				.build()
				.pathMapping("/")
				.apiInfo(apiInfo());
	}

	@Bean
	public SecurityConfiguration security() {
		return new SecurityConfiguration(
				"ClientID",
				"ClientSecret",
				"codeTest",
				"codetest",
				"Basic YWRtaW46c2VjcmV0",
				ApiKeyVehicle.HEADER,
				"Authorization",
				"");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.description("Code Test - Redspark")
				.license("API License")
				.licenseUrl("API License URL")
				.termsOfServiceUrl("Terms")
				.title("Code Test - Redspark API Doc")
				.version("0.0.1")
				.build();
	}

	@SuppressWarnings("unchecked")
	private Predicate<String> paths() {
		return or(regex("/*.*"));
	}
}
