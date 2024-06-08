package com.example.loan.service;
import com.example.loan.mapper.FormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.loan.entity.Form;

@Service
public class FormInsertService {

    @Autowired
    private FormMapper formMapper;

    public int insertform(Form form){
        return  formMapper.insert(form);
    }
}