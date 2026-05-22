package cl.duoc.vendedores_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VendedoresServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendedoresServiceApplication.class, args);
	}

}
