package brittaju.service;

import brittaju.dto.ExpenseRequest;
import brittaju.entity.Expense;
import brittaju.entity.User;

import java.util.List;

public interface ExpenseService {

    Expense createExpense(ExpenseRequest request, User user);

    List<Expense> getAllExpenses(User user);

    Expense getExpense(Long id, User user);

    Expense updateExpense(Long id, ExpenseRequest request, User user);

    void deleteExpense(Long id, User user);
}