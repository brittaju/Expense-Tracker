package brittaju.service;

import brittaju.dto.ExpenseRequest;
import brittaju.entity.ExpenseEntity;
import brittaju.entity.UserEntity;

import java.util.List;

public interface ExpenseService {

    ExpenseEntity createExpense(ExpenseRequest request, UserEntity userEntity);

    List<ExpenseEntity> getAllExpenses(UserEntity userEntity);

    ExpenseEntity getExpense(Long id, UserEntity userEntity);

    ExpenseEntity updateExpense(Long id, ExpenseRequest request, UserEntity userEntity);

    void deleteExpense(Long id, UserEntity userEntity);
}