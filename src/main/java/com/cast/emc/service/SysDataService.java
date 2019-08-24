package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.common.aop.UserOperation;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.SysData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class SysDataService extends BasicService {

    private final static String DEFAULT_OPERATION_DESC = "型号数据";

    public List<SysData> getPage(int index, int size) {
        SysData unitData = new SysData();
        Sort.Direction sort = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(index, size, sort, "createTime");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("createTime", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<SysData> example = Example.of(unitData, exampleMatcher);
        Page<SysData> page = sysDataJPA.findAll(example, pageable);
        return page.getContent();
    }

    public List<SysData> getLike(int index, int size, String condition) {
        Pageable pageable = PageRequest.of(index, size);
        return sysDataJPA.findLike(condition, pageable);
    }

    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 1)
    public void saveOrUpdate(SysData sysData,
                             MultipartFile layout,
                             MultipartFile data,
                             MultipartFile envelope,
                             MultipartFile exceed,
                             MultipartFile report,
                             String datetime) {
        try {
            String layoutPath = uploadService.uploadFile(layout);
            String dataPath = uploadService.uploadFile(data);
            String envelopePath = uploadService.uploadFile(envelope);
            String exceedPath = uploadService.uploadFile(exceed);
            String reportPath = uploadService.uploadFile(report);
            if (sysData.getId() != null) {
                SysData update = sysDataJPA.findById(sysData.getId()).get();
                sysData.setLayout(layoutPath == null ? update.getLayout() : layoutPath);
                sysData.setData(dataPath == null ? update.getData() : dataPath);
                sysData.setEnvelope(envelopePath == null ? update.getEnvelope() : envelopePath);
                sysData.setExceed(exceedPath == null ? update.getExceed() : exceedPath);
                sysData.setReport(reportPath == null ? update.getReport() : reportPath);
            } else {
                sysData.setLayout(layoutPath);
                sysData.setData(dataPath);
                sysData.setEnvelope(envelopePath);
                sysData.setExceed(exceedPath);
                sysData.setReport(reportPath);
            }
            sysData.setCreateTime(formatTime(datetime));
            sysDataJPA.save(sysData);
        } catch (Exception ex) {
            log.info("constructRefer add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }

    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 2)
    public void delete(Long id) {
        sysDataJPA.deleteById(id);
    }

    public SysData getbyId(Long id) {
        return sysDataJPA.findById(id).get();
    }

    public Long getCount() {
        return sysDataJPA.count();
    }
}
