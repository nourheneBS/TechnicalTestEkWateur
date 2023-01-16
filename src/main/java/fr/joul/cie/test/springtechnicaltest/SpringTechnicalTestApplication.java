package fr.joul.cie.test.springtechnicaltest;

import fr.joul.cie.test.springtechnicaltest.entities.AvailableOffers;
import fr.joul.cie.test.springtechnicaltest.entities.PromoCode;
import fr.joul.cie.test.springtechnicaltest.services.AvailableOffersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class SpringTechnicalTestApplication implements CommandLineRunner {

	@Autowired
	AvailableOffersService availableOffersService;

	public static void main(String[] args) {
		SpringApplication.run(SpringTechnicalTestApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {

		try {
			//Reading Command-Line argument - MON_CODE_PROMO_A_TESTER
			String MON_CODE_PROMO_A_TESTER = args[0];
			log.trace("MON_CODE_PROMO_A_TESTER: " + MON_CODE_PROMO_A_TESTER);

			PromoCode promoCode = availableOffersService.getPromoCodeByCode(MON_CODE_PROMO_A_TESTER);
			if (promoCode==null){ //verifying that MON_CODE_PROMO_A_TESTER exists in available promoCodeList
				log.info("Promo code does not exist!");
			}
			else{ //if promo code not null (exists)
				if (availableOffersService.isValid(MON_CODE_PROMO_A_TESTER)) { //promo code valid (not expired)
					AvailableOffers availableOffers = availableOffersService.getCompatibleOffers(promoCode);

					if (availableOffers != null) {// if compatible offers found
							log.info("Compatible offers found..");
							availableOffersService.createResultFile(availableOffers);
						} else { //if valid promo code && no compatible offers found
							log.info("NO COMPATIBLE OFFERS FOUND!");
						}
					}
				}

		} catch (IndexOutOfBoundsException e){
			log.warn("You probably forgot to enter MON_CODE_PROMO_A_TESTER.. please try again!");
		}




	}
}
