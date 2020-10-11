package com.cast.emc.repository;

import com.cast.emc.model.UsedFreqPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @创建人 feng
 * @创建时间 2019/8/18
 * @描述
 */
public interface UsedFreqPointJPA extends JpaRepository<UsedFreqPoint, Long> {
    @Query(value = "select * from used_freq_point " +
            "where concat(ifnull(id,''),ifnull(biz_name,'')) " +
            "like concat('%',:condition,'%') or :condition is null",
            nativeQuery = true)
    List<UsedFreqPoint> findLike(@Param("condition") String condition, Pageable pageable);
}
