package com.example.loan.controller;

import com.example.loan.Util.JwtUtil;
import entity.Manager;
import com.example.loan.Service.LoginService;
import entity.Officer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService officeService;
    @Autowired
    private JwtUtil jwtUtill;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Officer officer) {
        System.out.println("Received login request: " + officer);
        Officer user = officeService.findByUsername(officer.getUsername());
        Map<String, Object> result = new HashMap<>();
        if (user != null && user.getPassword().equals(officer.getPassword())) {
            String token = jwtUtill.generateToken(user.getUsername());
            result.put("token", token);
            result.put("message", "登录成功");
        } else {
            result.put("message", "用户名或密码错误");
        }
        System.out.println("Login result: " + result);
        return result;
    }

}
