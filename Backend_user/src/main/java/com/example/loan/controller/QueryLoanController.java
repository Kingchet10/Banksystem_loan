package com.example.loan.controller;

import entity.Loan;
import com.example.loan.service.LoanQueryService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class QueryLoanController {

    @Autowired
    private LoanQueryService loanService;

    @PostMapping("/loan-history")
    public List<Loan> queryLoanHistory(@RequestBody LoanQueryRequest request) {
        request.setStatus();
        return loanService.queryLoanHistory(
                request.getAmount(),
                request.getTerm(),
                request.getStatus(),
                request.getRate(),
                request.getStartDate(),
                request.getEndDate(),
                request.getSortCondition()
        );
    }
    

    // 内部类用于接收前端传递的查询条件
    static class LoanQueryRequest {
        private double amount;
        private int term;
        private String statussString;
        private int status;
        private double rate;
        private LocalDate date_applied;
        private LocalDate date_approved;
        private String sortCondition;

        public double getAmount() {return amount;}
        public int getTerm() {return term;}
        public String getStatussString() {return statussString;}
        public double getRate() {return rate;}
        public LocalDate getStartDate() {return date_applied;}
        public LocalDate getEndDate() {return date_approved;}
        public String getSortCondition() {return sortCondition;}
        public int getStatus() {return status;}
        private void setStatus() {
            switch (statussString) {
                case "申请中(Application)":
                    status=0;
                case "已拒绝(Declined)":
                    status=1;
                case "还款中(Repayment)":
                    status=2;
                case "已结清(Settled)":
                    status=3;
                case "逾期(Overdue)":
                    status=4;
                default:
                    status=-1; // 如果无法匹配，则返回默认值或者抛出异常
            }
        }
    }
}
