package com.example.loan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Loan;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LoanQueryMapper extends BaseMapper<Loan> {

    @Select("SELECT amount FROM loan WHERE loan_id = #{loan_id}")
    double getAmount(@Param("loan_id") int loan_id);

    @Select("SELECT rate FROM loan WHERE loan_id = #{loan_id}")
    double getrate(@Param("loan_id") int loan_id);

    @Update("UPDATE loan SET status = repayment WHERE loan_id = #{loan_id}")
    int updatestatus(int loan_id);

     @Select("SELECT * FROM loan WHERE borrow_id = #{borrow_id}")
    List<Loan> getLoansByUserId(@Param("borrow_id") int borrow_id);
}
