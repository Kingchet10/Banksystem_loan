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

    @PostMapping("/add-officer")
    public String insertManager(@RequestBody Officer officer) {
        int result = officerMapper.insert(officer);
        if (result > 0) {
            return "Manager inserted successfully!";
        } else {
            return "Failed to insert manager.";
        }
    }

    @PutMapping("/update-officer-password")
    public String updateOfficerPassword(@RequestParam String username, @RequestParam String newPassword) {
        UpdateWrapper<Officer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", username).set("password", newPassword);

        int result = officerMapper.update(null, updateWrapper);
        if (result > 0) {
            return "Password updated successfully!";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Officer not found");
        }
    }

    @PutMapping("/update-officer-permission")
    public String updateOfficerPermission(@RequestParam String username, @RequestParam String newPermission) {
        UpdateWrapper<Officer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", username).set("permissions", newPermission);

        int result = officerMapper.update(null, updateWrapper);
        if (result > 0) {
            return "Permission updated successfully!";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Officer not found");
        }
    }

    @GetMapping("/get-officers")
    public IPage getOfficers(@RequestParam int page, @RequestParam int pageSize) {
        Page<Officer> officerPage = new Page<>(page, pageSize);
        return officerMapper.selectPage(officerPage, null);
    }


    @DeleteMapping("/delete-officer/{id}")
    public String deleteOfficer(@PathVariable("id") int officer_id) {
        int result = officerMapper.deleteById(officer_id);
        if (result > 0) {
            return "Officer deleted successfully!";
        } else {
            return "Failed to delete officer.";
        }
    }
}

