package fr.joul.cie.test.springtechnicaltest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Result OBJECT model
public class AvailableOffers {
    private String code;
    private float discountValue;
    private String endDate;

    private List<OfferDetails> compatibleOfferList;
}
