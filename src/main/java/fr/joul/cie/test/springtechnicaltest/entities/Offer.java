package fr.joul.cie.test.springtechnicaltest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    private String offerType;
    private String offerName;
    private String offerDescription;
    private ArrayList<String> validPromoCodeList;
}
