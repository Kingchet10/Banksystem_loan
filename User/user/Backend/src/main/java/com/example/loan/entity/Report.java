package com.example.loan.entity;

import java.time.LocalDate;

public class Report {
    private int report_id;
    private int user_id;
    private double credit_limit;
    private LocalDate date;
    private double credit_score;

    // Getters and Setters

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getCreditLimit() {
        return credit_limit;
    }

    public void setCreditLimit(double creditlimit) {
        this.credit_limit = creditlimit;
    }

    public double getCreditScore() {
        return credit_score;
    }

    public void setCreditScore(double creditscore) {
        this.credit_score = creditscore;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
