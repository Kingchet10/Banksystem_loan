package com.example.loan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.loan.mapper.LoanMapper;
import entity.Form;
import entity.Loan;
import entity.Officer;
import jakarta.servlet.http.HttpServletResponse;
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
    @Autowired
    private HttpServletResponse httpServletResponse;

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

    @GetMapping("/get-loans")
    public IPage getLoans(@RequestParam int page, @RequestParam int pageSize) {
        Page<Loan> loanPage = new Page<>(page, pageSize);
        return loanMapper.selectPage(loanPage, new QueryWrapper<Loan>().eq("status","application"));
    }

    @PutMapping("/approve-loan/{id}")
    public String approveLoan(@PathVariable("id") int loan_id) {
        UpdateWrapper<Loan> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("loan_id", loan_id).set("status", "repayment");
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
        updateWrapper.eq("loan_id", loan_id).set("status", "declined");
        int result = loanMapper.update(null, updateWrapper);
        if (result > 0) {
            return "Loan rejected successfully!";
        } else {
            return "Failed to reject loan.";
        }
    }

    @GetMapping("/get-approvals")
    public IPage getApprovals(@RequestParam int page, @RequestParam int pageSize) {
        Page<Loan> loanPage = new Page<>(page, pageSize);
        return loanMapper.selectPage(loanPage, new QueryWrapper<Loan>().ne("status", "application"));
    }

    @GetMapping("/search-loans/{id_number}")
    public IPage searchloans(@PathVariable("id_number") String id_number, @RequestParam int page, @RequestParam int pageSize) {
        Page<Loan> loanPage = new Page<>(page, pageSize);
        return loanMapper.selectPage(loanPage, new QueryWrapper<Loan>().eq("id_number", id_number));
    }
}