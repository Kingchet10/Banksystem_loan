package com.example.loan.Service;

import entity.Manager;
import entity.Officer;
import com.example.loan.mapper.ManagerMapper;
import com.example.loan.mapper.OfficerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private OfficerMapper officerMapper;

    public Officer findByUsername(String username) {
        return officerMapper.findByUsername(username);
    }
}
