package fr.joul.cie.test.springtechnicaltest.services;

import fr.joul.cie.test.springtechnicaltest.entities.Offer;
import fr.joul.cie.test.springtechnicaltest.entities.PromoCode;

import java.util.List;

public interface IAvailableOffersService {

    public List<PromoCode> getAllPromoCodes();

    public PromoCode getPromoCodeByCode(String code);

    public boolean isValid(String code);

    public List<Offer> getAllOffers();





}
