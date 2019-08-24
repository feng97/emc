package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.common.aop.UserOperation;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.PulseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class PulseDataService extends BasicService {

    private final static String DEFAULT_OPERATION_DESC = "时域包络数据";

    public List<PulseData> getPage(int index, int size) {
        PulseData pulseData = new PulseData();
        Sort.Direction sort = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(index, size, sort, "createTime");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("createTime", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<PulseData> example = Example.of(pulseData, exampleMatcher);
        Page<PulseData> page = pulseDataJPA.findAll(example, pageable);
        return page.getContent();
    }

    public List<PulseData> getLike(int index, int size, String condition) {
        Pageable pageable = PageRequest.of(index, size);
        return pulseDataJPA.findLike(condition, pageable);
    }

    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 1)
    public void saveOrUpdate(PulseData pulseData, MultipartFile file, String datetime) {
        try {
            String path = uploadService.uploadFile(file);
            pulseData.setData(path);
            if (pulseData.getId() != null) {
                PulseData update = pulseDataJPA.findById(pulseData.getId()).get();
                pulseData.setData(path == null ? update.getData() : path);
            } else {
                pulseData.setData(path);
            }
            pulseData.setCreateTime(formatTime(datetime));

            pulseDataJPA.save(pulseData);
        } catch (Exception ex) {
            log.info("pulseData add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }

    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 2)
    public void delete(Long id) {
        pulseDataJPA.deleteById(id);
    }

    public PulseData getbyId(Long id) {
        return pulseDataJPA.findById(id).get();
    }

    public Long getCount() {
        return pulseDataJPA.count();
    }
}
