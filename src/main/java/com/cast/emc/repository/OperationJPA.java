package com.cast.emc.repository;

import com.cast.emc.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @创建人 feng
 * @创建时间 2019/8/24
 * @描述
 */
public interface OperationJPA extends JpaRepository<Operation, Long> {

}
