package brittaju.service;

import brittaju.dto.IncomeRequest;
import brittaju.entity.IncomeEntity;
import brittaju.entity.UserEntity;

import java.util.List;

public interface IncomeService {

    IncomeEntity createIncome(IncomeRequest request, UserEntity userEntity);

    List<IncomeEntity> getAllIncome(UserEntity userEntity);

    IncomeEntity getIncome(Long id, UserEntity userEntity);

    IncomeEntity updateIncome(Long id, IncomeRequest request, UserEntity userEntity);

    void deleteIncome(Long id, UserEntity userEntity);
}