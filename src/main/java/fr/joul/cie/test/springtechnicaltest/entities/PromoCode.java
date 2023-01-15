package fr.joul.cie.test.springtechnicaltest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoCode {

    private String code;
    private float discountValue;
    private String endDate;
}
