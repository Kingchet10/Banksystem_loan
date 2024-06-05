package com.example.loan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Form;
import entity.Loan;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoanMapper extends BaseMapper<Loan> {
}
