package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.common.aop.UserOperation;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.EnvirData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class EnvirDataService extends BasicService {

    private final static String DEFAULT_OPERATION_DESC = "环境数据";

    public List<EnvirData> getPage(int index, int size) {
        EnvirData envirData = new EnvirData();
        Sort.Direction sort = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(index, size, sort, "createTime");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("createTime", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<EnvirData> example = Example.of(envirData, exampleMatcher);
        Page<EnvirData> page = envirDataJPA.findAll(example, pageable);
        return page.getContent();
    }

    public List<EnvirData> getLike(int index, int size, String condition) {
        Pageable pageable = PageRequest.of(index, size);
        return envirDataJPA.findLike(condition, pageable);
    }
    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 1)
    public void saveOrUpdate(EnvirData envirData,
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
            if (envirData.getId() != null) {
                EnvirData update = envirDataJPA.findById(envirData.getId()).get();
                envirData.setLayout(layoutPath == null ? update.getLayout() : layoutPath);
                envirData.setData(dataPath == null ? update.getData() : dataPath);
                envirData.setEnvelope(envelopePath == null ? update.getEnvelope() : envelopePath);
                envirData.setExceed(exceedPath == null ? update.getExceed() : exceedPath);
                envirData.setReport(reportPath == null ? update.getReport() : reportPath);
            } else {
                envirData.setLayout(layoutPath);
                envirData.setData(dataPath);
                envirData.setEnvelope(envelopePath);
                envirData.setExceed(exceedPath);
                envirData.setReport(reportPath);
            }
            envirData.setCreateTime(formatTime(datetime));
            envirDataJPA.save(envirData);
        } catch (Exception ex) {
            log.info("constructRefer add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }
    @UserOperation(value = DEFAULT_OPERATION_DESC, type = 2)
    public void delete(Long id) {
        envirDataJPA.deleteById(id);
    }

    public EnvirData getbyId(Long id) {
        return envirDataJPA.findById(id).get();
    }

    public Long getCount() {
        return envirDataJPA.count();
    }
}
