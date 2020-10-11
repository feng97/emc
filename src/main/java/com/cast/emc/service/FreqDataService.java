package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.common.aop.UserOperation;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.FreqData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class FreqDataService extends BasicService {

    private final static String DEFAULT_OPERATION_DESC = "频谱包络数据";

    public List<FreqData> getPage(int index, int size) {
        FreqData freqData = new FreqData();
        Sort.Direction sort = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(index, size, sort, "createTime");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("createTime", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<FreqData> example = Example.of(freqData, exampleMatcher);
        Page<FreqData> page = freqDataJPA.findAll(example, pageable);
        return page.getContent();
    }

    public List<FreqData> getLike(int index, int size, String condition) {
        Pageable pageable = PageRequest.of(index, size);
        return freqDataJPA.findLike(condition, pageable);
    }

    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 1)
    public void saveOrUpdate(FreqData freqData, MultipartFile file, String datetime) {
        try {
            String path = uploadService.uploadFile(file);
            freqData.setData(path);
            if (freqData.getId() != null) {
                FreqData update = freqDataJPA.findById(freqData.getId()).get();
                freqData.setData(path == null ? update.getData() : path);
            } else {
                freqData.setData(path);
            }
            freqData.setCreateTime(formatTime(datetime));

            freqDataJPA.save(freqData);
        } catch (Exception ex) {
            log.info("constructRefer add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }

    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 2)
    public void delete(Long id) {
        freqDataJPA.deleteById(id);
    }

    public FreqData getbyId(Long id) {
        return freqDataJPA.findById(id).get();
    }

    public Long getCount() {
        return freqDataJPA.count();
    }
}
