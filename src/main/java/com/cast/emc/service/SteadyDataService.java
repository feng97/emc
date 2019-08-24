package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.SteadyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @创建人 feng
 * @创建时间 2019/8/24
 * @描述
 */
@Slf4j
@Service
public class SteadyDataService extends BasicService{
    public List<SteadyData> getPage(int index, int size) {
        SteadyData steadyData = new SteadyData();
        Sort.Direction sort = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(index, size, sort, "createTime");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("createTime", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<SteadyData> example = Example.of(steadyData, exampleMatcher);
        Page<SteadyData> page = steadyDataJPA.findAll(example, pageable);
        return page.getContent();
    }

    public List<SteadyData> getLike(int index, int size, String condition) {
        Pageable pageable = PageRequest.of(index, size);
        return steadyDataJPA.findLike(condition, pageable);
    }

    public void saveOrUpdate(SteadyData steadyData, MultipartFile file, MultipartFile report, String datetime) {
        try {
            String filePath = uploadService.uploadFile(file);
            String reportPath = uploadService.uploadFile(report);
            steadyData.setData(filePath);
            steadyData.setReport(reportPath);
            if (steadyData.getId() != null) {
                SteadyData update = steadyDataJPA.findById(steadyData.getId()).get();
                steadyData.setData(filePath == null ? update.getData() : filePath);
                steadyData.setReport(reportPath == null ? update.getReport() : reportPath);
            } else {
                steadyData.setData(filePath);
                steadyData.setReport(reportPath);
            }
            steadyData.setCreateTime(formatTime(datetime));

            steadyDataJPA.save(steadyData);
        } catch (Exception ex) {
            log.info("constructRefer add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }

    public void delete(Long id) {
        steadyDataJPA.deleteById(id);
    }

    public SteadyData getbyId(Long id) {
        return steadyDataJPA.findById(id).get();
    }

    public Long getCount() {
        return steadyDataJPA.count();
    }
}
