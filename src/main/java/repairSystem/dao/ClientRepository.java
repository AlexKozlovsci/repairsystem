package repairSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repairSystem.model.Client;

/**
 * Created by Алексей on 07.05.2017.
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}