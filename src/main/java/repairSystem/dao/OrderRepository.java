package repairSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repairSystem.model.Order;

/**
 * Created by Алексей on 29.04.2017.
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
