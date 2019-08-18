package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.UsedFreqPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @创建人 feng
 * @创建时间 2019/8/18
 * @描述
 */
@Slf4j
@Service
public class UsedFreqPointService extends BasicService {
    public List<UsedFreqPoint> getPage(int index, int size) {
        UsedFreqPoint usedFreqPoint = new UsedFreqPoint();
        Sort.Direction sort = Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(index, size, sort, "id");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<UsedFreqPoint> example = Example.of(usedFreqPoint, exampleMatcher);
        Page<UsedFreqPoint> page = usedFreqPointJPA.findAll(example, pageable);
        return page.getContent();
    }

    public List<UsedFreqPoint> getLike(int index, int size, String condition) {
        Pageable pageable = PageRequest.of(index, size);
        return usedFreqPointJPA.findLike(condition, pageable);
    }

    public void saveOrUpdate(UsedFreqPoint usedFreqPoint) {
        try {
            usedFreqPoint.setCreateTime(Instant.now().toEpochMilli());
            usedFreqPointJPA.save(usedFreqPoint);
        } catch (Exception ex) {
            log.info("usedFreqPoint add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }

    public void delete(Long id) {
        usedFreqPointJPA.deleteById(id);
    }

    public UsedFreqPoint getbyId(Long id) {
        return usedFreqPointJPA.findById(id).get();
    }

    public Long getCount() {
        return usedFreqPointJPA.count();
    }
}
