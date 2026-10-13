package brittaju.repository;

import brittaju.entity.ExpenseEntity;
import brittaju.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findByUser(UserEntity userEntity);

    Optional<ExpenseEntity> findByIdAndUser(Long id, UserEntity userEntity);
}