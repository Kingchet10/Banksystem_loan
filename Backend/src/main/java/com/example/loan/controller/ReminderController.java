package com.example.loan.controller;
import com.example.loan.mapper.ReminderMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ReminderController {

    @Autowired
    private ReminderMapper reminderMapper;


    @PostMapping("/save-reminder")
    public String SetRemimder(@RequestParam int time) {
        //donnot know id                            not implement
        int user_id=1;
        if(reminderMapper.getReminderTime(user_id)==null)  reminderMapper.insertReminder(user_id,time);
        else reminderMapper.updateReminder(user_id, time);
        return "repay successfully!";
    }

    @PostMapping("/get-reminder")
    public int GetReminder(@RequestParam int time) {
        //donnot know id                            not implement
        int user_id=1;

        int reminder_time=reminderMapper.getReminderTime(user_id);
        
        return reminder_time;
    }
}