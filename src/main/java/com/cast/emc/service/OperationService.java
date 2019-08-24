package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.Label;
import com.cast.emc.model.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 feng
 * @创建时间 2019/8/24
 * @描述
 */
@Slf4j
@Service
public class OperationService extends BasicService{
    public List<Operation> getAll() {
        return operationJPA.findAll();
    }

    public void saveOrUpdate(Operation operation) {
        try {
            operationJPA.save(operation);
        } catch (Exception ex) {
            log.info("operation add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }
}
