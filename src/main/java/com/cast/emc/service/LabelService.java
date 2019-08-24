package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.common.aop.UserOperation;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.Label;
import com.cast.emc.model.enums.DataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LabelService extends BasicService {

    private final static String DEFAULT_OPERATION_DESC = "标签";

    public List<Label> getAll() {
        return labelJPA.findAll();
    }

    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 1)
    public void saveOrUpdate(Label label) {
        try {
            labelJPA.save(label);
        } catch (Exception ex) {
            log.info("constructRefer add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }

    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 2)
    public void delete(Long id) {
        labelJPA.deleteById(id);
    }

    public Label getbyId(Long id) {
        return labelJPA.findById(id).get();
    }

    public Long getCount() {
        return labelJPA.count();
    }

    public List<Label> getByType(DataType type) {
        return labelJPA.getByType(type);
    }
}
