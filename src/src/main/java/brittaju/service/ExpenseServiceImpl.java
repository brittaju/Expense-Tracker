package brittaju.service;

import brittaju.dto.ExpenseRequest;
import brittaju.entity.Expense;
import brittaju.entity.User;
import brittaju.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense createExpense(ExpenseRequest request, User user) {
        Expense expense = new Expense();
        expense.setDescription(request.description());
        expense.setAmount(request.amount());
        expense.setCategory(request.category());
        expense.setExpenseDate(request.expenseDate());
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses(User user) {
        return expenseRepository.findByUser(user);
    }

    @Override
    public Expense getExpense(Long id, User user) {
        return expenseRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    @Override
    public Expense updateExpense(Long id, ExpenseRequest request, User user) {
        Expense expense = getExpense(id, user);
        expense.setDescription(request.description());
        expense.setAmount(request.amount());
        expense.setCategory(request.category());
        expense.setExpenseDate(request.expenseDate());
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Long id, User user) {
        Expense expense = getExpense(id, user);
        expenseRepository.delete(expense);
    }
}