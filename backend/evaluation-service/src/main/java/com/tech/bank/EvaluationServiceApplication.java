package com.tech.bank;

import com.tech.bank.util.UserNameConverter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class EvaluationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluationServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {

		var modelMapper = new ModelMapper();
//		modelMapper.addConverter(new UserNameConverter());
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${spring.application.name}") String appName,
								 @Value("${spring.application.description}") String appDesc) {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("basicScheme", new SecurityScheme()
								.type(SecurityScheme.Type.HTTP)
								.scheme("basic")
						)
				)
				.info(new Info().title(appName).version("1.0").description(appDesc));
	}
}
