package fr.joul.cie.test.springtechnicaltest;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class SpringTechnicalTestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringTechnicalTestApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		//Start here !
	}
}
