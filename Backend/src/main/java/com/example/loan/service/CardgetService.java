package com.example.loan.service;

//import entity.bank_card;
import com.example.loan.mapper.CardgetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CardgetService {

    @Autowired
    private CardgetMapper bankCardMapper;

    /*public List<BankCard> getUserBankCards(int userId) {
        return bankCardMapper.getUserBankCards(userId);
    }
    */
    
}
