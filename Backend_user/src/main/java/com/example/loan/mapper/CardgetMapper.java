package com.example.loan.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import entity.Form;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
                                        // here is bank_card !!! not form
public interface CardgetMapper extends BaseMapper<Form>{

    //我们假设用户的银行卡存在bank_card下
    //@Select("SELECT * FROM bank_card WHERE user_id = #{userId}")
    //List<BankCard> getUserBankCards(@Param("userId") int userId);

}
