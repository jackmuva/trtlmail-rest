package com.jackmu.slowcapsules;

import com.jackmu.slowcapsules.repository.security.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SlowcapsulesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlowcapsulesApplication.class, args);
	}

}
