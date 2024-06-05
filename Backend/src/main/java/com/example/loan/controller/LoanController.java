package com.example.loan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.*;
import com.example.loan.mapper.AutoLoanMapper;
import com.example.loan.mapper.CreditMapper;
import com.example.loan.mapper.IdgetMapper;
import com.example.loan.mapper.LoanApplyMapper;
import entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public IPage searchLoans(@PathVariable("id_number") String id_number, @RequestParam int page, @RequestParam int pageSize) {
        Page<Loan> loanPage = new Page<>(page, pageSize);
        return loanMapper.selectPage(loanPage, new QueryWrapper<Loan>().eq("id_number", id_number));
    }


}