package com.example.loan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Form;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface FormMapper extends BaseMapper<Form> {
    @Insert("INSERT INTO form (user_name, id_number, gender, emotion, income, address, phone_number, email, education, purpose, statement) " +
            "VALUES (#{user_name}, #{id_number}, #{gender}, #{emotion}, #{income}, #{address}, #{phone_number}, #{email}, #{education}, #{purpose}, #{statement})")
    @Options(useGeneratedKeys = true, keyProperty = "form_id")
    int insert(Form form);
}
