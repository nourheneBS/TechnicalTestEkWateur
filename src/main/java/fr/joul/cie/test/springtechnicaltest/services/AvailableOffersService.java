package fr.joul.cie.test.springtechnicaltest.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.joul.cie.test.springtechnicaltest.entities.AvailableOffers;
import fr.joul.cie.test.springtechnicaltest.entities.Offer;
import fr.joul.cie.test.springtechnicaltest.entities.OfferDetails;
import fr.joul.cie.test.springtechnicaltest.entities.PromoCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AvailableOffersService implements IAvailableOffersService{

    @Override
    public List<PromoCode> getAllPromoCodes() {
        List<PromoCode> promoCodesList = new ArrayList<PromoCode>();
        try {
            URL url = new URL("https://601025826c21e10017050013.mockapi.io/ekwatest/promoCodeList");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connection is established
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                ObjectMapper mapper = new ObjectMapper();
                promoCodesList = mapper.readValue(url, new TypeReference<List<PromoCode>>() {});
                for (PromoCode pcode : promoCodesList) {
                    log.trace("Promocode: " + pcode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return promoCodesList;
    }

    @Override
    public PromoCode getPromoCodeByCode(String code) {
        Optional<PromoCode> pc= this.getAllPromoCodes().stream().filter(
                x -> x.getCode().equals(code)).findAny();
        PromoCode pcRes = pc.orElse(null);

        return pcRes;
    }

    /***
     *  check if the promo code has not expired (valid)
     * @param code
     * @return boolean
     */
    @Override
    public boolean isValid(String code) {
        PromoCode promoCode =  this.getPromoCodeByCode(code);
        boolean valid = false;
        if(promoCode!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //convert String to LocalDate
            LocalDate pc_endDate = LocalDate.parse(promoCode.getEndDate(), formatter);

            if(pc_endDate.isAfter(LocalDate.now())) {
                valid = true;
                log.info("Valid promo code! ");
            } else {
                log.info("Expired promo code! ");
            }
        }
        return valid;
    }

    @Override
    public List<Offer> getAllOffers() {
        List<Offer> offerList = new ArrayList<Offer>();
        try {
            URL url = new URL("https://601025826c21e10017050013.mockapi.io/ekwatest/offerList");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connection is established
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                ObjectMapper mapper = new ObjectMapper();
                offerList = mapper.readValue(url, new TypeReference<List<Offer>>() {});
                for (Offer ofr : offerList) {
                    log.trace("Offer: "+ ofr);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return offerList;
    }

    @Override
    public AvailableOffers getCompatibleOffers(PromoCode promoCode) {
        AvailableOffers availableOffersResult = new AvailableOffers();
        List<OfferDetails>  offerDetailsList = new ArrayList<OfferDetails>();

        //collect compatible offers
        List<Offer> offerList = this.getAllOffers().stream()
                .filter(x-> x.getValidPromoCodeList()
                        .contains(promoCode.getCode())).collect(Collectors.toList());
        //If compatible offers found -> create Result Object (AvailableOffers)
        if(!offerList.isEmpty()) {
            for (Offer ofr : offerList) {
                availableOffersResult.setCode(promoCode.getCode());
                availableOffersResult.setEndDate(promoCode.getEndDate());
                availableOffersResult.setDiscountValue(promoCode.getDiscountValue());
                offerDetailsList.add(new OfferDetails(ofr.getOfferName(), ofr.getOfferType()));
            }
            availableOffersResult.setCompatibleOfferList(offerDetailsList);
        }

        return availableOffersResult;
    }

    @Override
    public void createResultFile(AvailableOffers availableOffers){
        ObjectMapper mapper = new ObjectMapper();
        //Save "availableOffers" in a JSON File
        File resultFile = new File("availableOffers.json");
        try {
            mapper.writeValue(resultFile, availableOffers);
            log.info("availableOffers.JSON created");
        } catch (IOException e) {
            log.info("Could not create file");
            throw new RuntimeException(e);
        }
    }

}
