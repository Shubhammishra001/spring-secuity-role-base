package com.expenseTracker.expenseTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenseTracker.expenseTracker.entity.Expense;
import com.expenseTracker.expenseTracker.repository.ExpenseRepository;

@Service
public class ExpenseService {
@Autowired
private ExpenseRepository expenseRepository;

public  List<Expense> getAllExpenses(){
    return expenseRepository.findAll();
}


public  Expense addExpense(Expense expense){
    Expense obj=new Expense();
    obj.setAmount(expense.getAmount());
    obj.setCategory(expense.getCategory());
    obj.setDate(expense.getDate());
    obj.setTitle(expense.getTitle());
    return expenseRepository.save(obj);
}

public void deleteExpense(Long id){
    expenseRepository.deleteById(id);
}

public List<Expense> getExpensesByCategory(String  category){
    return expenseRepository.findByCategory(category);
}

public List<Expense> getMonthlyReport(String startDate,String endDate){
    return expenseRepository.findByDateBetween(startDate,endDate);
}
}
