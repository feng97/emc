package com.cast.emc.repository;

import com.cast.emc.model.FreqData;
import com.cast.emc.model.SteadyData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @创建人 feng
 * @创建时间 2019/8/24
 * @描述
 */
public interface SteadyDataJPA extends JpaRepository<SteadyData, Long> {
    @Query(value = "select * from steady_data " +
            "where concat(ifnull(id,''),ifnull(name,''),ifnull(item,'')," +
            "ifnull(place,''),ifnull(remarks,'')) " +
            "like concat('%',:condition,'%') or :condition is null",
            nativeQuery = true)
    List<SteadyData> findLike(@Param("condition") String condition, Pageable pageable);
}
