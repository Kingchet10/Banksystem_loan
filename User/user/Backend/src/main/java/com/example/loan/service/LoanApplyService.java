package com.example.loan.service;
import com.example.loan.mapper.AutoLoanMapper;
import com.example.loan.mapper.IdgetMapper;
import com.example.loan.mapper.LoanApplyMapper;
import com.example.loan.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApplyService {

    @Autowired
    private LoanApplyMapper loanMapper;
    @Autowired
    private IdgetMapper idgetMapper;
    @Autowired
    private AutoLoanMapper  autoloanMapper;

    public int insertloan(Loan loan){
        return  loanMapper.insert(loan);
    }
    public int autoloan(Loan loan){
        return autoloanMapper.insert(loan);
    }
    public int getofficerid(String permission){
        return  idgetMapper.idrandomget(permission);
    }
}