package com.tdtien.accounts.service.client;

import com.tdtien.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsFeignClient{

    /**
     * @param correlationId
     * @param mobileNumber
     * @return
     */
    @Override
    public ResponseEntity<CardsDto> getCardDetails(String correlationId, String mobileNumber) {
        return null;
    }

}
