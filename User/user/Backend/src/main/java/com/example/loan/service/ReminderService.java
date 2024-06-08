package com.example.loan.service;
import com.example.loan.mapper.ReminderMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.loan.mapper.LoanQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.loan.entity.Reminder;
import com.example.loan.entity.Loan;
import java.util.List;

@Service
public class ReminderService {

    @Autowired
    private ReminderMapper reminderMapper;

    @Autowired
    private LoanQueryMapper loanQueryMapper;

    public void saveOrUpdateReminder(Reminder reminder) {
        Integer existingReminderTime = reminderMapper.getReminderTime(reminder.getUser_id());
        if (existingReminderTime == null) {
            // 插入新的提醒设置
            reminderMapper.insertReminder(reminder.getUser_id(), reminder.getTime());
        } else {
            // 更新现有的提醒设置
            reminderMapper.updateReminder(reminder.getUser_id(), reminder.getTime());
        }
    }

    public List<Loan> getLoansToRemind(int userId, int time) {
        // 获取当前日期

        // 查询符合条件的贷款记录
        return loanQueryMapper.selectList(new QueryWrapper<Loan>()
                .eq("status", "repayment") // 未还款状态
                .apply("DATE_ADD(date_approved, INTERVAL term DAY) - INTERVAL {0} DAY <= NOW()", time));
    }

    public Integer gettime(int user_id){ return reminderMapper.getReminderTime(user_id);}
}