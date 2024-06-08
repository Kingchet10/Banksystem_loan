package com.example.loan.controller;
import com.example.loan.service.ReminderService;

import com.example.loan.entity.Reminder;
import com.example.loan.entity.Loan;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping("/save-reminder")
    public ResponseEntity<String> saveReminder(@RequestBody Reminder reminder) {
        reminder.setUser_id(1);                                             // need user_id
        reminderService.saveOrUpdateReminder(reminder);
        return ResponseEntity.ok("Reminder saved successfully");
    }

    @GetMapping("/timeget-reminder")
    public ResponseEntity<Integer> getReminder() {
        int userId=1;                                                           // need user_id
        Integer time = reminderService.gettime(userId);
        if (time != null) {
            return ResponseEntity.ok(time);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/loanget-reminder")
    public List<Loan> getLoanHistory(@RequestParam int time) {
        int userId=1;                                                               // need user_id
        return reminderService.getLoansToRemind(userId, time);
    }
}