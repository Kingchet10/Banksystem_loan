package com.example.loan.controller;


import com.example.loan.mapper.ManagerMapper;
import entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ManagerController {
/*
    @Autowired
    private ManagerMapper managerMapper;

    @GetMapping("/Manager")
    public String queryManager() {
        List<Manager> managers = managerMapper.selectList(null);
        return managers.toString();
    }

    @PostMapping("/Manager")
    public String insertManager(@RequestBody Manager manager) {
        int result = managerMapper.insert(manager);
        if (result > 0) {
            return "Manager inserted successfully!";
        } else {
            return "Failed to insert manager.";
        }
    }*/
}
