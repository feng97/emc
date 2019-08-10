package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class SampleDataService extends BasicService{
    public List<SampleData> getPage(int index, int size) {
        SampleData sampleData = new SampleData();
        Sort.Direction sort = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(index, size, sort, "createTime");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("createTime", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<SampleData> example = Example.of(sampleData, exampleMatcher);
        Page<SampleData> page = sampleDataJPA.findAll(example, pageable);
        return page.getContent();
    }

    public List<SampleData> getLike(int index, int size, String condition) {
        Pageable pageable = PageRequest.of(index, size);
        return sampleDataJPA.findLike(condition, pageable);
    }

    public void saveOrUpdate(SampleData sampleData, MultipartFile file) {
        try {
            String path = uploadService.uploadFile(file);
            sampleData.setData(path);
            if (sampleData.getId() != null) {
                SampleData update = sampleDataJPA.findById(sampleData.getId()).get();
                sampleData.setData(path == null ? update.getData() : path);
            } else {
                sampleData.setData(path);
            }
            sampleData.setCreateTime(Instant.now().toEpochMilli());

            sampleDataJPA.save(sampleData);
        } catch (Exception ex) {
            log.info("sampleData add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }

    public void delete(Long id) {
        sampleDataJPA.deleteById(id);
    }

    public SampleData getbyId(Long id) {
        return sampleDataJPA.findById(id).get();
    }

    public Long getCount() {
        return sampleDataJPA.count();
    }
}
