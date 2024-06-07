package com.example.loan.Service;

import com.example.loan.mapper.ManagerMapper;
import entity.Manager;
import entity.Officer;
import com.example.loan.mapper.OfficerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OfficerMapper officerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Officer manager = officerMapper.findByUsername(username);
        if (manager == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.withUsername(manager.getUsername())
                .password(manager.getPassword())
                .authorities("USER") // You can customize roles here
                .build();
    }
}
