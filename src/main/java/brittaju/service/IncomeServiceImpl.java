package brittaju.service;

import brittaju.dto.IncomeRequest;
import brittaju.entity.IncomeEntity;
import brittaju.entity.UserEntity;
import brittaju.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    @Override
    public IncomeEntity createIncome(IncomeRequest request, UserEntity userEntity) {
        IncomeEntity incomeEntity = new IncomeEntity();
        incomeEntity.setAmount(request.amount());
        incomeEntity.setCategory(request.category());
        incomeEntity.setIncomeDate(request.expenseDate());
        incomeEntity.setUser(userEntity);
        return incomeRepository.save(incomeEntity);
    }

    @Override
    public List<IncomeEntity> getAllIncome(UserEntity userEntity) {
        return incomeRepository.findByUser(userEntity);
    }

    @Override
    public IncomeEntity updateIncome(Long id, IncomeRequest request, UserEntity userEntity) {
        IncomeEntity incomeEntity = getIncome(id, userEntity);
        incomeEntity.setAmount(request.amount());
        incomeEntity.setCategory(request.category());
        incomeEntity.setIncomeDate(request.expenseDate());
        return incomeRepository.save(incomeEntity);
    }

    @Override
    public IncomeEntity getIncome(Long id, UserEntity userEntity) {
        return incomeRepository.findByIdAndUser(id, userEntity)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    @Override
    public void deleteIncome(Long id, UserEntity userEntity) {
        IncomeEntity incomeEntity = getIncome(id, userEntity);
        incomeRepository.delete(incomeEntity);
    }
}