package com.tdtien.cards.service;

import com.tdtien.cards.dto.CardsDto;

public interface ICardsService {
    /**
     * Create a new Card for a Customer
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createCard(String mobileNumber);

    /**
     * Get Card Details by mobileNumber
     * @param mobileNumber - Input mobile Number
     *  @return Card Details by mobileNumber
     */
    CardsDto getCard(String mobileNumber);

    /**
     * Update Card Details
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     * Delete Card Details by mobileNumber
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    boolean deleteCard(String mobileNumber);
}
