package entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

@TableName("loan")
public class Loan {

    @TableId(value = "loan_id", type = IdType.AUTO)
    private int loan_id;

    @TableField("borrow_id")
    private int borrow_id;

    @TableField("card_id")
    private int card_id;

    @TableField("officer_id")
    private int officer_id;

    @TableField("amount")
    private double amount;

    @TableField("rate")
    private double rate;

    @TableField("term")
    private int term;

    @TableField("status")
    private LoanStatus status;

    @TableField("date_applied")
    private Date date_applied;

    @TableField("date_approved")
    private Date date_approved;

    @TableField("form_id")
    private int form_id;

    // Getters and Setters

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public int getOfficer_id() {
        return officer_id;
    }

    public void setOfficer_id(int officer_id) {
        this.officer_id = officer_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Date getDate_applied() {
        return date_applied;
    }

    public void setDate_applied(Date date_applied) {
        this.date_applied = date_applied;
    }

    public Date getDate_approved() {
        return date_approved;
    }

    public void setDate_approved(Date date_approved) {
        this.date_approved = date_approved;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }
}

enum LoanStatus {
    APPLICATION,
    DECLINED,
    REPAYMENT,
    SETTLED,
    OVERDUE
}
