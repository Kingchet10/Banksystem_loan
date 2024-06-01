package com.example.loan.service;

import entity.Loan;
import com.example.loan.mapper.LoanQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanQueryService {

    @Autowired
    private LoanQueryMapper loanMapper;

    public List<Loan> queryLoanHistory(double amount, Integer term,int status, double rate,
                                        LocalDate startDate, LocalDate endDate, String sortCondition) {
        List<Loan> loans=loanMapper.queryLoanHistory(amount, term, status, rate, startDate, endDate, sortCondition);
        for(Loan loan:loans)  loan.setStatusstring();
        return loans;
    }
}
