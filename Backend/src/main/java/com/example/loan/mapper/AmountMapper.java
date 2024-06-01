package com.example.loan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Loan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper                                         //这里应该是bank_card！！
public interface AmountMapper extends BaseMapper<Loan> {

    @Select("SELECT amount FROM bank_card WHERE card_id = #{cardId}")
    double getAmount(int cardId);

    @Update("UPDATE bank_card SET amount = #{amount} WHERE card_id = #{cardId}")
    int updateAmount(int cardId, double amount);
}
