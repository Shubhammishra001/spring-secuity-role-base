package com.expenseTracker.expenseTracker.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.expenseTracker.expenseTracker.entity.Expense;
import com.expenseTracker.expenseTracker.service.ExpenseService;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

@Autowired
private ExpenseService expenseService;

// @GetMapping("/test")
// public String Test(){
//     return "test";
// }

@PostMapping
public Expense  addExpense(@RequestBody Expense expense){
    System.err.println(" test->"+expense.getAmount());
    return expenseService.addExpense(expense);
}
@GetMapping
public List<Expense> getAllExpenses(){
    return expenseService.getAllExpenses();
}

@DeleteMapping("/{id}")
public void deleteExpense(@PathVariable Long id){
    expenseService.deleteExpense(id);
}

@GetMapping("/category/{category}")
public List<Expense> getExpensesByCategory(@PathVariable String category){
    return expenseService.getExpensesByCategory(category);
}

@GetMapping("/report")
public List<Expense> getMonthlyReport(@RequestParam String startDate,@RequestParam String endDate){
    return expenseService.getMonthlyReport(startDate, endDate);
}

}
