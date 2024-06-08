package com.example.loan.controller;

import com.example.loan.entity.Loan;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.loan.service.LoanQueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class QueryLoanController {

    @Autowired
    private LoanQueryService loanQueryService;

    @PostMapping("/loan-history")
    public Map<String, Object> getLoanHistory(@RequestBody LoanQueryRequest filter,
                                              @RequestParam(defaultValue = "1") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        QueryWrapper<Loan> queryWrapper = new QueryWrapper<>();
                                                                                 
        
        //queryWrapper.eq("borrower_id", ?);                              //need to get user_id 
        
        if (filter.getAmount() != null) {
            queryWrapper.eq("amount", filter.getAmount());
        }
        if (filter.getStartDate() != null && filter.getEndDate() != null) {
            queryWrapper.between("date_applied", filter.getStartDate(), filter.getEndDate());
        }
        if (filter.getStatus() != null) {
            queryWrapper.eq("status", filter.getStatus());
        }
        if (filter.getRate() != null) {
            queryWrapper.eq("rate", filter.getRate());
        }

        // 添加排序条件
        if (filter.getSortCondition() != null && !filter.getSortCondition().isEmpty()) {
            queryWrapper.orderByDesc(filter.getSortCondition());
        }

        // 分页查询
        Page<Loan> loanPage = new Page<>(page, size);
        Page<Loan> resultPage = loanQueryService.query(loanPage, queryWrapper);

        // 构建返回结果
        Map<String, Object> response = new HashMap<>();
        response.put("total", resultPage.getTotal());
        response.put("loans", resultPage.getRecords());

        return response;
    }
    

    // 内部类用于接收前端传递的查询条件
    static class LoanQueryRequest {
        private Double amount;
        private LoanStatus status;
        private Double rate;
        private LocalDate date_applied;
        private LocalDate date_approved;
        private String sortCondition;

        public Double getAmount() {return amount;}
        public Double getRate() {return rate;}
        public LocalDate getStartDate() {return date_applied;}
        public LocalDate getEndDate() {return date_approved;}
        public String getSortCondition() {return sortCondition;}
        public LoanStatus getStatus() {return status;}
        enum LoanStatus {
            application,
            declined,
            repayment,
            settled,
            overdue
        }
    }
}
