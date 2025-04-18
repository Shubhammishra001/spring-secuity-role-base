package com.expenseTracker.expenseTracker.entity;
import jakarta.persistence.*;
@Entity
public class Expense{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String amount;
    private String category;
    private String date;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Expense [id=" + id + ", title=" + title + ", ammount=" + amount + ", category=" + category + ", date="
                + date + "]";
    }
    public Expense(Long id, String title, String amount, String category, String date) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
    public Expense() {
        }
}