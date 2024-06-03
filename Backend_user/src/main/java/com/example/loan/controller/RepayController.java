package com.example.loan.controller;
import com.example.loan.mapper.AmountMapper;
import com.example.loan.mapper.LoanQueryMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RepayController {

    @Autowired
    private AmountMapper getamountmapper;
    private LoanQueryMapper loanQueryMapper;


    @PostMapping("/confirm-repayment")
    public String Repayment(@RequestParam int loan_id,@RequestParam int card_id) {

        double loan_amount=loanQueryMapper.getAmount(loan_id);

        double amount=getamountmapper.getAmount(card_id);

        double repay_amount=loan_amount*(1+loanQueryMapper.getrate(loan_id)*0.01);

        if(amount<repay_amount) return "not enough amount!";

        getamountmapper.updateAmount(card_id, amount-repay_amount);

        return "repay successfully!";
    }
}