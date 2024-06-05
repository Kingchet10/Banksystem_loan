package com.example.loan.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.loan.mapper.OfficerMapper;
import entity.Officer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class OfficerController {

    @Autowired
    private OfficerMapper officerMapper;

    @PutMapping("/update-officer-password-by-officer")
    public String updateOfficerPasswordByOfficer(@RequestParam String currentPassword, @RequestParam String newPassword) {
        UpdateWrapper<Officer> updateWrapper = new UpdateWrapper<>();
        //updateWrapper.and(wrapper->wrapper.eq("officer_id",/*id_from_token*/).eq("Password", currentPassword)).set("password", newPassword);
        updateWrapper.eq("Password", currentPassword).set("password", newPassword);

        int result = officerMapper.update(null, updateWrapper);
        if (result > 0) {
            return "Password updated successfully!";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Current password is not correct");
        }
    }
}

