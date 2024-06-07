package com.example.loan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Manager;
import entity.Officer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OfficerMapper extends BaseMapper<Officer> {
    @Select("SELECT * FROM officer WHERE username = #{username}")
    Officer findByUsername(String username);
}
