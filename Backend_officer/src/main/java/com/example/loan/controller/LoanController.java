package com.example.loan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.loan.mapper.LoanMapper;
import entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class LoanController {

    @Autowired
    private LoanMapper loanMapper;

    @GetMapping("/get-loans/{officer_id}")
    public IPage<Loan> getLoans(@PathVariable("officer_id") int officer_id,@RequestParam int page, @RequestParam int pageSize) {
        Page<Loan> loanPage = new Page<>(page, pageSize);
        return loanMapper.selectPage(loanPage, new QueryWrapper<Loan>().eq("status", "application").eq("officer_id", officer_id));
    }

    @PutMapping("/approve-loan/{id}")
    public String approveLoan(@PathVariable("id") int loan_id) {
        UpdateWrapper<Loan> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("loan_id", loan_id).set("status", "repayment").set("date_approved", LocalDate.now());
        int result = loanMapper.update(null, updateWrapper);
        if (result > 0) {
            return "Loan approved successfully!";
        } else {
            return "Failed to approve loan.";
        }
    }

    @PutMapping("/reject-loan/{id}")
    public String rejectLoan(@PathVariable("id") int loan_id) {
        UpdateWrapper<Loan> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("loan_id", loan_id).set("status", "declined").set("date_approved", LocalDate.now());
        int result = loanMapper.update(null, updateWrapper);
        if (result > 0) {
            return "Loan rejected successfully!";
        } else {
            return "Failed to reject loan.";
        }
    }

    @GetMapping("/get-approvals/{officer_id}")
    public IPage<Loan> getApprovals(@PathVariable("officer_id") int officer_id,@RequestParam int page, @RequestParam int pageSize) {
        Page<Loan> loanPage = new Page<>(page, pageSize);
        return loanMapper.selectPage(loanPage, new QueryWrapper<Loan>().ne("status", "application").eq("officer_id",officer_id));
    }

    @GetMapping("/search-loans/{form_id}")
    public Loan searchLoans(@PathVariable("form_id") int form_id) {
        return loanMapper.selectOne(new QueryWrapper<Loan>().eq("form_id", form_id));
    }
}
