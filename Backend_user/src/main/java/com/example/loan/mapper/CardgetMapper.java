package com.example.loan.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import entity.Form;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
                                        // here is bank_card !!! not form
public interface CardgetMapper extends BaseMapper<Form>{

    //���Ǽ����û������п�����bank_card��
    //@Select("SELECT * FROM bank_card WHERE user_id = #{userId}")
    //List<BankCard> getUserBankCards(@Param("userId") int userId);

}
