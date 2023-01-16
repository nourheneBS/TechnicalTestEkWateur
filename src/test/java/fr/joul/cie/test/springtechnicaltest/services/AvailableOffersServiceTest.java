package fr.joul.cie.test.springtechnicaltest.services;

import fr.joul.cie.test.springtechnicaltest.entities.PromoCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AvailableOffersServiceTest {

    private final AvailableOffersService availableOffersService = new AvailableOffersService();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllPromoCodes() {
        List<PromoCode> actuals = availableOffersService.getAllPromoCodes();
        List<PromoCode> expected = Arrays.asList(
                new PromoCode("EKWA_WELCOME", 2, "2019-10-04"),
                new PromoCode("ELEC_N_WOOD", 1.5f, "2022-06-20"),
                new PromoCode("ALL_2000", 2.75f, "2023-03-05"),
                new PromoCode("GAZZZZZZZZY", 2.25f, "2018-08-02"),
                new PromoCode("ELEC_IS_THE_NEW_GAS", 3.5f, "2022-04-13"),
                new PromoCode("BUZZ", 2.75f, "2022-02-02"),
                new PromoCode("WOODY", 1.75f, "2022-05-29"),
                new PromoCode("WOODY_WOODPECKER", 1.15f, "2017-04-07")
        );
        assertFalse(actuals.isEmpty());
        assertArrayEquals(expected.toArray(), actuals.toArray());
    }

    @Test
    void getPromoCodeByCode() {
        PromoCode pcode1= availableOffersService.getPromoCodeByCode("EKWA_WELCOME");
        PromoCode pcode2= availableOffersService.getPromoCodeByCode("ALL_2000");
        PromoCode pcode3= availableOffersService.getPromoCodeByCode("GAZZZZZZZZY");
        PromoCode pcode4= availableOffersService.getPromoCodeByCode("BUZZ");
        assertAll(()-> assertNotNull(pcode1),
                    ()-> assertNotNull(pcode2),
                    ()-> assertNotNull(pcode3),
                    ()-> assertNotNull(pcode4) );
        assertAll(() -> assertEquals(new PromoCode("EKWA_WELCOME", 2 , "2019-10-04"), pcode1),
                () -> assertEquals(new PromoCode("ALL_2000", 2.75f , "2023-03-05"), pcode2),
                () -> assertEquals(new PromoCode("GAZZZZZZZZY", 2.25f, "2018-08-02"), pcode3),
                () -> assertEquals(new PromoCode("BUZZ", 2.75f , "2022-02-02"), pcode4));


    }

    @Test
    void isValid() {
        PromoCode pcode = availableOffersService.getPromoCodeByCode("ALL_2000");
        assertNotNull(pcode);
        assertTrue(availableOffersService.isValid("ALL_2000"));
    }

    @Test
    void getAllOffers() {
        //fail("not implemented yet");
    }

    @Test
    void getCompatibleOffers() {
        fail("not implemented yet");
    }

    @Test
    void createResultFile() {

        fail("not implemented yet");
    }
}