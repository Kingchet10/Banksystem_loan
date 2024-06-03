package com.example.loan.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CreditMapper {
    @Select("SELECT credit_limit FROM report WHERE user_id = #{userId}")
    Double getCreditLimit(@Param("userId") int userId);
}
