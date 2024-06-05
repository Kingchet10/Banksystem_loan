package com.example.loan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Loan;
import entity.Form;
import org.apache.ibatis.annotations.Select;

public interface LoanDetailsMapper extends BaseMapper<Loan> {
    //@Select({"select key1,v1,key2,v2\n" +
     //       "from t1\n" +
       //     "left join t2\n" +
         //   "on t1.key1 = t2.key2\n"};
}
