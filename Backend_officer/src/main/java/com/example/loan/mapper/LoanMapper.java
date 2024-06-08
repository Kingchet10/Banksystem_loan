package com.example.loan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Form;
import entity.Loan;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoanMapper extends BaseMapper<Loan> {
    @Select("SELECT card_id FROM loan WHERE loan_id = #{loan_id}")
    int getCardId(@Param("loan_id") int loan_id);
    @Select("SELECT amount FROM loan WHERE loan_id = #{loan_id}")
    double getAmount(@Param("loan_id") int loan_id);
}
