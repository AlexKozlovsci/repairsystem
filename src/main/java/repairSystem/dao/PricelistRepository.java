package repairSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repairSystem.model.PriceList;

import java.util.List;

/**
 * Created by Юрий on 03.05.2017.
 */

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {
    List<PriceList> findAll();
    PriceList findById(long id);
    PriceList save(PriceList priceItem);
}