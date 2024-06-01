package com.example.loan.controller;

import com.example.loan.mapper.AutoLoanMapper;
import com.example.loan.mapper.CreditMapper;
import com.example.loan.mapper.IdgetMapper;
import com.example.loan.mapper.LoanApplyMapper;
import entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@CrossOrigin
public class LoanController {

    @Autowired
    private LoanApplyMapper loanMapper;
    private IdgetMapper idgetMapper;
    private CreditMapper creditgetMapper;
    private AutoLoanMapper  autoloanMapper;

    @PostMapping("/add-loan")
    public String insertLoan(@RequestBody Loan loan) {

        int result;

        //get borrower_id(user_id) from outside                 not implement

        //set date 
        loan.setDate_applied(LocalDate.now());

        double credit=creditgetMapper.getCreditLimit(loan.getBorrow_id());
        
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
            loan.setStatus(2);
            loan.setDate_approved(LocalDate.now());
            result=autoloanMapper.insert(loan);
        }

        if (result > 0) {
            return "Loan created successfully!";
        } else {
            return "Failed to create loan.";
        }
        }

}
