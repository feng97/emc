package com.cast.emc.repository;

import com.cast.emc.model.EmcData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmcDataJPA extends JpaRepository<EmcData, Long> {
    @Query(value = "select * from emc_data " +
            "where concat(ifnull(id,''),ifnull(name,''),ifnull(item,'')," +
            "ifnull(device,''),ifnull(place,''),ifnull(center_freq,'')," +
            "ifnull(band_width,''),ifnull(modulation,'')) " +
            "like concat('%',:condition,'%') or :condition is null",
            nativeQuery = true)
    List<EmcData> findLike(@Param("condition") String condition, Pageable pageable);
}
