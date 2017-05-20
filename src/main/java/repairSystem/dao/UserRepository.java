package repairSystem.dao;

/**
 * Created by Юрий on 28.04.2017.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repairSystem.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findById(long id);
    User findByLogin(String name);
    List<User> findAllByRole(String role);
}
