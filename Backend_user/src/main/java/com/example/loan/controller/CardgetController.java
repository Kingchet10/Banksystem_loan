
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
        // ����������һ�������ܹ���ȡ��ǰ��¼�û������п��б�
        // �����Ѿ���ȡ����ǰ��¼�û����û�ID����������û�IDΪ1
        int userId = 1;
        return bankCardService.getUserBankCards(userId);
    }

    */
}
