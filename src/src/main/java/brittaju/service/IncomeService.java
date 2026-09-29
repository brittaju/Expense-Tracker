package brittaju.service;

import brittaju.dto.IncomeRequest;
import brittaju.entity.Income;
import brittaju.entity.User;

import java.util.List;

public interface IncomeService {

    Income createIncome(IncomeRequest request, User user);

    List<Income> getAllIncome(User user);

    Income getIncome(Long id, User user);

    Income updateIncome(Long id, IncomeRequest request, User user);

    void deleteIncome(Long id, User user);
}