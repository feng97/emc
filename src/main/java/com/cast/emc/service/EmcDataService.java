package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.EmcData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class EmcDataService extends BasicService {
    public List<EmcData> getPage(int index, int size) {
        EmcData emcData = new EmcData();
        Sort.Direction sort = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(index, size, sort, "createTime");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("createTime", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<EmcData> example = Example.of(emcData, exampleMatcher);
        Page<EmcData> page = emcDataJPA.findAll(example, pageable);
        return page.getContent();
    }

    public List<EmcData> getLike(int index, int size, String condition) {
        Pageable pageable = PageRequest.of(index, size);
        return emcDataJPA.findLike(condition, pageable);
    }

    public void saveOrUpdate(EmcData emcData, MultipartFile file, String datetime) {
        try {
            String path = uploadService.uploadFile(file);
            emcData.setData(path);
            if (emcData.getId() != null) {
                EmcData update = emcDataJPA.findById(emcData.getId()).get();
                emcData.setData(path == null ? update.getData() : path);
            } else {
                emcData.setData(path);
            }
            emcData.setCreateTime(formatTime(datetime));

            emcDataJPA.save(emcData);
        } catch (Exception ex) {
            log.info("constructRefer add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }

    public void delete(Long id) {
        emcDataJPA.deleteById(id);
    }

    public EmcData getbyId(Long id) {
        return emcDataJPA.findById(id).get();
    }

    public Long getCount() {
        return emcDataJPA.count();
    }
}
