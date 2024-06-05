package com.example.loan.Service;

import com.example.loan.entity.Manager;
import com.example.loan.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private ManagerMapper managerMapper;

    public Manager findByUsername(String username) {
        return managerMapper.findByUsername(username);
    }
}
