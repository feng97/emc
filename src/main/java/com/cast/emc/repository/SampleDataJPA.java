package com.cast.emc.repository;

import com.cast.emc.model.SampleData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SampleDataJPA extends JpaRepository<SampleData, Long> {
    @Query(value = "select * from sample_data " +
            "where concat(ifnull(id,''),ifnull(signal_type,''),ifnull(type,'')) " +
            "like concat('%',:condition,'%') or :condition is null",
            nativeQuery = true)
    List<SampleData> findLike(@Param("condition") String condition, Pageable pageable);
}
