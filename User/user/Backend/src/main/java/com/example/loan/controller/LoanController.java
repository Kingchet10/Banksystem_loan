package com.example.loan.controller;

import com.example.loan.entity.Loan;
import com.example.loan.service.AmountService;
import com.example.loan.service.CreditReportService;
import com.example.loan.service.LoanApplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class LoanController {

    @Autowired
    private LoanApplyService loanApplyService;
    @Autowired
    private CreditReportService creditReportService;
    @Autowired
    private AmountService amountService;

    @PostMapping("/add-loan")
    public Map<String, Object> insertLoan(@RequestBody Loan loan) {

        int result; 
        Map<String, Object> response = new HashMap<>();

        //get borrower_id(user_id) from outside                                            not implement
        loan.setBorrow_id(1);

        //set date 
        loan.setDate_applied(LocalDate.now());

                                                                                        
        Double credit= creditReportService.calculateCreditLimit(loan.getBorrow_id());
        
        String permission;double rate;
        if(loan.getAmount()>100000) {permission="large";rate=0.03;}
        else {permission="small";rate=0.01;}
        rate+=0.02*(1-credit/loan.getAmount());                                            
        loan.setRate(rate);

        //artificial        
        if(loan.getAmount()>credit){         
            loan.setStatus(0);
            //get random suitable officer_id from officer_tanble
            int id=loanApplyService.getofficerid(permission);
            loan.setOfficer_id(id);
            result=loanApplyService.insertloan(loan);
        }
        //automic 
        else{
            double money=amountService.getamount(loan.getCard_id());                     // update amount
            amountService.changeamount(loan.getCard_id(), money);
            loan.setStatus(2);
            loan.setDate_approved(LocalDate.now());
            result=loanApplyService.autoloan(loan);
        }

        if (result > 0) {
            response.put("message", "Loan created successfully!");
            response.put("loan_id", loan.getLoan_id());
        } else {
            response.put("message", "Failed to create loan.");
        }
        return response;
        }

}
enum LoanStatus {
    application,
    declined,
    repayment,
    settled,
    overdue
}