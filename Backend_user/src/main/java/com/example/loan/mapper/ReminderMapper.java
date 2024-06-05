package com.example.loan.mapper;


import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReminderMapper  {

    @Select("SELECT time FROM Reminder WHERE user_id = #{userId}")
    Integer getReminderTime(@Param("userId") int userId);

    @Insert("INSERT INTO Reminder (user_id, time) VALUES (#{userId}, #{reminderTime})")
    int insertReminder(@Param("userId") int userId, @Param("reminderTime") int reminderTime);

    @Update("UPDATE Reminder SET time = #{reminderTime} WHERE user_id = #{userId}")
    int updateReminder(@Param("userId") int userId, @Param("reminderTime") int reminderTime);
}

