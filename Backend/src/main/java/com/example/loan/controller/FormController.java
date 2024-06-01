package com.example.loan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.loan.mapper.FormMapper;
import entity.Form;
import entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class FormController {

    @Autowired
    private FormMapper formMapper;

    @PostMapping("/add-form")
    public Map<String, Object> insertManager(@RequestBody Form form) {
        int result = formMapper.insert(form);
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("message", "Form created successfully!");
            response.put("id", form.getForm_id()); // 返回生成的表单ID
        } else {
            response.put("message", "Failed to create form.");
        }
        return response;
    }
    @GetMapping("/get-forms/{form_id}")
    public IPage getForms(@PathVariable("form_id") int form_id,@RequestParam int page, @RequestParam int pageSize) {
        Page<Form> formPage = new Page<>(page, pageSize);
        return formMapper.selectPage(formPage, new QueryWrapper<Form>().eq("form_id", form_id));
    }

    @GetMapping("/search-froms/{id_number}")
    public IPage searchForms(@PathVariable("id_number") String id_number, @RequestParam int page, @RequestParam int pageSize) {
        Page<Form> formPage = new Page<>(page, pageSize);
        return formMapper.selectPage(formPage, new QueryWrapper<Form>().eq("id_number", id_number));
    }
}