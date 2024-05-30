package com.example.loan.controller;

import com.example.loan.mapper.FormMapper;
import entity.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}