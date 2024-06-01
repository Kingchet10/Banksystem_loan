package com.example.loan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Loan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface LoanQueryMapper extends BaseMapper<Loan> {
    List<Loan> queryLoanHistory(@Param("amount") double amount,
                                @Param("term") int term,
                                @Param("status") int status,
                                @Param("rate") double rate,
                                @Param("date_applied") LocalDate date_applied,
                                @Param("date_approved") LocalDate date_approved,
                                @Param("sortCondition") String sortCondition);

    @Select("SELECT amount FROM loan WHERE loan_id = #{loan_id}")
    double getAmount(@Param("loan_id") int loan_id);
    @Select("SELECT rate FROM loan WHERE loan_id = #{loan_id}")
    double getrate(@Param("loan_id") int loan_id);

}
