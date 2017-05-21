package repairSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repairSystem.model.Pricelist;

import java.util.List;

/**
 * Created by Юрий on 03.05.2017.
 */

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Long> {
    List<Pricelist> findAll();
    Pricelist findById(long id);
    Pricelist save(Pricelist priceItem);
}