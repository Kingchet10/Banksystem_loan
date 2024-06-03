
package com.example.loan.controller;

import com.example.loan.mapper.CardgetMapper;
import com.example.loan.service.CardgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
public class CardgetController {

    @Autowired
    private CardgetService bankCardService;

    //@PostMapping("/bank-cards")
    /*
    public List<BankCard> getUserBankCards() {
        // 假设这里有一个方法能够获取当前登录用户的银行卡列表
        // 假设已经获取到当前登录用户的用户ID，这里假设用户ID为1
        int userId = 1;
        return bankCardService.getUserBankCards(userId);
    }

    */
}
