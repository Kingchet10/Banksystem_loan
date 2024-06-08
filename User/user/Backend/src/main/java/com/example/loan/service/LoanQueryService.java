package com.example.loan.service;
import com.example.loan.mapper.LoanQueryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.loan.entity.Loan;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
@Service
public class LoanQueryService {

    @Autowired
    private LoanQueryMapper loanQueryMapper;

    public Page<Loan> query(Page<Loan> loanPage ,QueryWrapper<Loan> queryWrapper){
        return  loanQueryMapper.selectPage(loanPage,queryWrapper);
    }

    public double getrate(int loan_id){ return loanQueryMapper.getrate(loan_id);}
    public int updatestatus(int loan_id) {return loanQueryMapper.updatestatus(loan_id);}
    public double getamount(int loan_id){ return loanQueryMapper.getAmount(loan_id);}
}