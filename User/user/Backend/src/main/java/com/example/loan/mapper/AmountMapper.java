package com.example.loan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.loan.entity.Loan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper                                         //这里应该是bank_card！！
public interface AmountMapper extends BaseMapper<Loan> {

    @Select("SELECT amount FROM bank_card WHERE card_id = #{cardId}")
    double getAmount(@Param("cardId") int cardId);

    @Update("UPDATE bank_card SET amount = #{amount} WHERE card_id = #{cardId}")
    int updateAmount(@Param("cardId") int cardId, @Param("amount") double amount);
}
