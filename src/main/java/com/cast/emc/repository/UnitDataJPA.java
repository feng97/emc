package com.cast.emc.repository;

import com.cast.emc.model.UnitData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitDataJPA extends JpaRepository<UnitData, Long> {
    @Query(value = "select * from unit_data " +
            "where concat(ifnull(id,''),ifnull(spacecraft,''),ifnull(batch,'')," +
            "ifnull(standard,''),ifnull(model,''),ifnull(equ_name,'')," +
            "ifnull(equ_number,''),ifnull(subsys,''),ifnull(stage,'')," +
            "ifnull(item,''),ifnull(place,''),ifnull(pol,'')," +
            "ifnull(test_obj,''),ifnull(frequencyl,''),ifnull(frequencyr,''),ifnull(state,'')) " +
            "like concat('%',:condition,'%') or :condition is null",
            nativeQuery = true)
    List<UnitData> findLike(@Param("condition") String condition, Pageable pageable);
}
