package com.example.loan.controller;
import com.example.loan.mapper.AmountMapper;
import com.example.loan.mapper.LoanQueryMapper;
import com.example.loan.service.CardgetService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RepayController {

    @Autowired
    private AmountMapper getamountmapper;
    @Autowired
    private LoanQueryMapper loanQueryMapper;
    @Autowired
    private CardgetService bankCardService;


    //@PostMapping("/bank-cards")
    /*
    public List<BankCard> getUserBankCards() {
        // 假设这里有一个方法能够获取当前登录用户的银行卡列表
        // 假设已经获取到当前登录用户的用户ID，这里假设用户ID为1
        int userId = 1;                                                     // need user_id
        return bankCardService.getUserBankCards(userId);
    }*/

    @PostMapping("/confirm-repayment")
    public Map<String, Object> Repayment(@RequestParam int loan_id,@RequestParam String info) {
        int card_id=0;
        String[] parts = info.split(" - ");
        if (parts.length > 0) {
            try {
                // 将提取的字符串转换为整数
                int number = Integer.parseInt(parts[0]);
                System.out.println(number); // 输出：66025
            } catch (NumberFormatException e) {
                System.out.println("未找到匹配的数字部分");
            }
        } else {
            System.out.println("未找到匹配的数字部分");
        }

        double loan_amount=loanQueryMapper.getAmount(loan_id);

        double amount=getamountmapper.getAmount(card_id);

        double repay_amount=loan_amount*(1+loanQueryMapper.getrate(loan_id)*0.01);

        int result=getamountmapper.updateAmount(card_id, amount-repay_amount);

        loanQueryMapper.updatestatus(loan_id);

        Map<String, Object> response = new HashMap<>();
        
        if(amount>repay_amount){
            if (result > 0) response.put("message", "Repayment successfully!");
            else response.put("message", "fail to repay!");
        }    
        else response.put("message", "Lack of balance!");

        return response;
    }
}