package com.example.loan.mapper;


import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IdgetMapper  {

    @Select("SELECT id FROM officer WHERE permission = #{permission} ORDER BY RAND() LIMIT 1")

    int idrandomget(@Param("permission") String permission);

}
