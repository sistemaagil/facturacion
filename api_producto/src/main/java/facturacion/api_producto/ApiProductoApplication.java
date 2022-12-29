package facturacion.api_producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition
@SpringBootApplication
public class ApiProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProductoApplication.class, args);
	}

}
