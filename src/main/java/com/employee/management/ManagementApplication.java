package com.employee.management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.employee.management.model.Employee;
import com.employee.management.model.UserRole;
import com.employee.management.service.IRoleService;

@SpringBootApplication
public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner (IRoleService service) {
		return args -> {
			UserRole r1 = new UserRole();
			r1.setName("EMPLOYEE");
			UserRole r2 = new UserRole();
			r2.setName("HR");
			service.saveRole(r1);
			service.saveRole(r2);
		};
	}
}
