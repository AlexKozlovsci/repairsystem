package repairSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import repairSystem.model.Workorder;

import java.util.List;

/**
 * Created by Алексей on 29.04.2017.
 */

@Repository
public interface WorkorderRepository extends JpaRepository<Workorder, Long>{
    List<Workorder> findAll();
    Workorder findById(long id);

    @Query(value = "SELECT * FROM workorder WHERE id_engineer = :id", nativeQuery = true)
    List<Workorder> findAllByIdEngineer(@Param("id") long id);
    Workorder save(Workorder workorder);
}
