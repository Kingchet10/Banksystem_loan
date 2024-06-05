package com.example.loan.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import entity.Report;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReportMapper extends BaseMapper<Report> {
    @Select("SELECT credit_limit FROM report WHERE user_id = #{user_id}")
    Double getCreditLimit(@Param("user_id") int user_id);
    
}
