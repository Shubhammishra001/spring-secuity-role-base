package com.expenseTracker.expenseTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenseTracker.expenseTracker.entity.Expense;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    List<Expense> findByCategory(String category);
    List<Expense> findByDateBetween(String startDate, String endDate);

}
