package brittaju.service;

import brittaju.dto.IncomeRequest;
import brittaju.entity.Income;
import brittaju.entity.User;
import brittaju.repository.IncomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Income createIncome(IncomeRequest request, User user) {
        Income income = new Income();
        income.setAmount(request.amount());
        income.setCategory(request.category());
        income.setIncomeDate(request.expenseDate());
        income.setUser(user);
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> getAllIncome(User user) {
        return incomeRepository.findByUser(user);
    }

    @Override
    public Income updateIncome(Long id, IncomeRequest request, User user) {
        Income income = getIncome(id, user);
        income.setAmount(request.amount());
        income.setCategory(request.category());
        income.setIncomeDate(request.expenseDate());
        return incomeRepository.save(income);
    }

    @Override
    public Income getIncome(Long id, User user) {
        return incomeRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    @Override
    public void deleteIncome(Long id, User user) {
        Income income = getIncome(id, user);
        incomeRepository.delete(income);
    }
}