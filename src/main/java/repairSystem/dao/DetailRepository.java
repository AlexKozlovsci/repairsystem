package repairSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repairSystem.model.Detail;

import java.util.List;

/**
 * Created by Юрий on 03.05.2017.
 */

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findAll();
    Detail findById(long id);
    Detail save(Detail detail);
}
