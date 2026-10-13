package brittaju.repository;

import brittaju.entity.IncomeEntity;
import brittaju.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<IncomeEntity, Long> {

    List<IncomeEntity> findByUser(UserEntity userEntity);

    Optional<IncomeEntity> findByIdAndUser(Long id, UserEntity userEntity);
}