package com.cast.emc.repository;

import com.cast.emc.model.PulseData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PulseDataJPA extends JpaRepository<PulseData, Long> {
    @Query(value = "select * from pulse_data " +
            "where concat(ifnull(id,''),ifnull(name,''),ifnull(item,'')," +
            "ifnull(device,''),ifnull(place,'')) " +
            "like concat('%',:condition,'%') or :condition is null",
            nativeQuery = true)
    List<PulseData> findLike(@Param("condition") String condition, Pageable pageable);
}
