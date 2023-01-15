package fr.joul.cie.test.springtechnicaltest;

import fr.joul.cie.test.springtechnicaltest.entities.Offer;
import fr.joul.cie.test.springtechnicaltest.entities.PromoCode;
import fr.joul.cie.test.springtechnicaltest.services.AvailableOffersService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Log
public class SpringTechnicalTestApplication implements CommandLineRunner {

	@Autowired
	AvailableOffersService availableOffersService;

	public static void main(String[] args) {
		SpringApplication.run(SpringTechnicalTestApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		//Reading Command-Line argument - MON_CODE_PROMO_A_TESTER
		//String MON_CODE_PROMO_A_TESTER = args[0];
		//System.out.println("MON_CODE_PROMO_A_TESTER: " + MON_CODE_PROMO_A_TESTER);
	}
}
