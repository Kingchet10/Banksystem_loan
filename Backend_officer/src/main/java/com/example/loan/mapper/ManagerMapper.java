package com.example.loan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.loan.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
    @Select("SELECT * FROM manager WHERE username = #{username}")
    Manager findByUsername(String username);
}
