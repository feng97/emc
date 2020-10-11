package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.common.aop.UserOperation;
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

    private final static String DEFAULT_OPERATION_DESC = "现场干扰数据";

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

    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 1)
    public void saveOrUpdate(EmcData emcData, MultipartFile file, MultipartFile report, String datetime) {
        try {
            String filePath = uploadService.uploadFile(file);
            String reportPath = uploadService.uploadFile(report);
            emcData.setData(filePath);
            emcData.setReport(reportPath);
            if (emcData.getId() != null) {
                EmcData update = emcDataJPA.findById(emcData.getId()).get();
                emcData.setData(filePath == null ? update.getData() : filePath);
                emcData.setReport(reportPath == null ? update.getReport() : reportPath);
            } else {
                emcData.setData(filePath);
                emcData.setReport(reportPath);
            }
            emcData.setCreateTime(formatTime(datetime));

            emcDataJPA.save(emcData);
        } catch (Exception ex) {
            log.info("constructRefer add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }
    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 2)
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
