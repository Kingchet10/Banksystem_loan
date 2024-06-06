package com.example.loan.controller;

import com.example.loan.mapper.AmountMapper;
import com.example.loan.mapper.AutoLoanMapper;
import com.example.loan.mapper.IdgetMapper;
import com.example.loan.mapper.LoanApplyMapper;
import entity.Loan;
import com.example.loan.service.CreditReportService;
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
    private LoanApplyMapper loanMapper;
    @Autowired
    private IdgetMapper idgetMapper;
    @Autowired
    private CreditReportService creditReportService;
    @Autowired
    private AutoLoanMapper  autoloanMapper;
    @Autowired
    private AmountMapper amountMapper;

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
            int id=idgetMapper.idrandomget(permission);
            loan.setOfficer_id(id);
            result=loanMapper.insert(loan);
        }
        //automic 
        else{
            double money=amountMapper.getAmount(loan.getCard_id());                     // update amount
            amountMapper.updateAmount(loan.getCard_id(), money);
            loan.setStatus(2);
            loan.setDate_approved(LocalDate.now());
            result=autoloanMapper.insert(loan);
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
