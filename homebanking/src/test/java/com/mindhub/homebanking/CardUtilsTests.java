package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.utils.CardUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

@SpringBootTest
public class CardUtilsTests {

    @Test
    public void cardNumberIsCreated(){
        String cardNumber = CardUtils.getCardNumber();
        assertThat(cardNumber,is(not(emptyOrNullString())));
    }

    @Test
    public void cvvNumberIsNotExceeding(){
        int cvv = CardUtils.getCVV();
        assertThat(cvv, not(greaterThan(999)));
    }


}
