package com.example.loan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.loan.mapper.FormMapper;
import entity.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FormController {

    @Autowired
    private FormMapper formMapper;

    @GetMapping("/get-forms/{form_id}")
    public Form getFormDetails(@PathVariable("form_id") int form_id) {
        return formMapper.selectById(form_id);
    }

    @GetMapping("/search-forms/{id_number}")
    public IPage searchForms(@PathVariable("id_number") String id_number, @RequestParam int page, @RequestParam int pageSize) {
        Page<Form> formPage = new Page<>(page, pageSize);
        return formMapper.selectPage(formPage, new QueryWrapper<Form>().eq("id_number", id_number));
    }
}