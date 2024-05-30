package com.example.loan.controller;

import com.example.loan.mapper.LoanMapper;
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
    private LoanMapper loanMapper;

    @PostMapping("/add-loan")
    public String insertManager(@RequestBody Loan loan) {
        loan.setDate_applied(LocalDate.now());
        loan.setStatus(0);
        int result = loanMapper.insert(loan);
        if (result > 0) {
            return "Loan created successfully!";
        } else {
            return "Failed to create loan.";
        }
    }
}