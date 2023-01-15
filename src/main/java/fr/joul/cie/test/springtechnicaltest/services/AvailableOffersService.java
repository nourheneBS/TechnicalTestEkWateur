package fr.joul.cie.test.springtechnicaltest.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.joul.cie.test.springtechnicaltest.entities.Offer;
import fr.joul.cie.test.springtechnicaltest.entities.PromoCode;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
                    System.out.println("nième Promocode: " + pcode);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return promoCodesList;
    }

    @Override
    public PromoCode getPromoCodeByCode(String code){
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //convert String to LocalDate
        LocalDate pc_endDate = LocalDate.parse(promoCode.getEndDate(), formatter);

        if(promoCode!=null && pc_endDate.isAfter(LocalDate.now())){
            return true;
        }
        else {
            return false;
        }
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
                    System.out.println("Another offer: " + ofr);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return offerList;
    }
}
