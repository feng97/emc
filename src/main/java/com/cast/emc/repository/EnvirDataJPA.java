package com.cast.emc.repository;

import com.cast.emc.model.EnvirData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnvirDataJPA extends JpaRepository<EnvirData, Long> {
    @Query(value = "select * from envir_data " +
            "where concat(ifnull(id,''),ifnull(standard,''),ifnull(item,'')," +
            "ifnull(place,''),ifnull(pol,''),ifnull(frequencyl,'')," +
            "ifnull(frequencyr,''),ifnull(state,'')) " +
            "like concat('%',:condition,'%') or :condition is null",
            nativeQuery = true)
    List<EnvirData> findLike(@Param("condition") String condition, Pageable pageable);
}
