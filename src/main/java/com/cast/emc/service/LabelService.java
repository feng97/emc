package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.Label;
import com.cast.emc.model.enums.DataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LabelService extends BasicService {
    public List<Label> getAll() {
        return labelJPA.findAll();
    }

    public void saveOrUpdate(Label label) {
        try {
            labelJPA.save(label);
        } catch (Exception ex) {
            log.info("constructRefer add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }

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
