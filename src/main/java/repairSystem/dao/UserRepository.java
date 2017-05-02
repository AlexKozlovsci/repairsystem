package repairSystem.dao;

/**
 * Created by Юрий on 28.04.2017.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repairSystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String name);
}
