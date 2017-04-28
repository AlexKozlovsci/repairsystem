package repairSystem.dao;

import org.springframework.stereotype.Repository;
import repairSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Created by Юрий on 27.04.2017.
 */

@Repository
public interface AuthRepository extends JpaRepository<User, Long>{
    User findByName(String name);
}