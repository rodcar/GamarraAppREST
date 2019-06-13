package pe.edu.upc.gamarra.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT = new Contact("Curso de Aplicaciones para dispositivos móviles", "",
			"");
	
	@SuppressWarnings("rawtypes")
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Documentación de GamarraAppREST", "Documentación de GamarraAppREST", "1.0",
			"PREMIUM", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<VendorExtension>());

	@SuppressWarnings("deprecation")
	@Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(null, null, null, null, "", ApiKeyVehicle.HEADER,"Authorization","");
    }
	
    @Bean
    public Docket api() {
    	return new Docket(DocumentationType.SWAGGER_2)
    			.select() 
		          .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))		         
		          .build()
		          .apiInfo(DEFAULT_API_INFO)
		          .securitySchemes(Arrays.asList(apiKey()));
    }
    
    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }
}
