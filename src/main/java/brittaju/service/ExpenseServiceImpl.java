package brittaju.service;

import brittaju.dto.ExpenseRequest;
import brittaju.entity.ExpenseEntity;
import brittaju.entity.UserEntity;
import brittaju.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Override
    public ExpenseEntity createExpense(ExpenseRequest request, UserEntity userEntity) {
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setDescription(request.description());
        expenseEntity.setAmount(request.amount());
        expenseEntity.setCategory(request.category());
        expenseEntity.setExpenseDate(request.expenseDate());
        expenseEntity.setUser(userEntity);
        return expenseRepository.save(expenseEntity);
    }

    @Override
    public List<ExpenseEntity> getAllExpenses(UserEntity userEntity) {
        return expenseRepository.findByUser(userEntity);
    }

    @Override
    public ExpenseEntity getExpense(Long id, UserEntity userEntity) {
        return expenseRepository.findByIdAndUser(id, userEntity)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    @Override
    public ExpenseEntity updateExpense(Long id, ExpenseRequest request, UserEntity userEntity) {
        ExpenseEntity expenseEntity = getExpense(id, userEntity);
        expenseEntity.setDescription(request.description());
        expenseEntity.setAmount(request.amount());
        expenseEntity.setCategory(request.category());
        expenseEntity.setExpenseDate(request.expenseDate());
        return expenseRepository.save(expenseEntity);
    }

    @Override
    public void deleteExpense(Long id, UserEntity userEntity) {
        ExpenseEntity expenseEntity = getExpense(id, userEntity);
        expenseRepository.delete(expenseEntity);
    }
}