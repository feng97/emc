package com.cast.emc.service;

import com.cast.emc.common.ResponseType;
import com.cast.emc.exception.BizException;
import com.cast.emc.model.UnitData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class UnitDataService extends BasicService {
    public List<UnitData> getPage(int index, int size) {
        UnitData unitData = new UnitData();
        Sort.Direction sort = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(index, size, sort, "createTime");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("createTime", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<UnitData> example = Example.of(unitData, exampleMatcher);
        Page<UnitData> page = unitDataJPA.findAll(example, pageable);
        return page.getContent();
    }

    public List<UnitData> getLike(int index, int size, String condition) {
        Pageable pageable = PageRequest.of(index, size);
        return unitDataJPA.findLike(condition, pageable);
    }

    public void saveOrUpdate(UnitData unitData,
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
            if (unitData.getId() != null) {
                UnitData update = unitDataJPA.findById(unitData.getId()).get();
                unitData.setLayout(layoutPath == null ? update.getLayout() : layoutPath);
                unitData.setData(dataPath == null ? update.getData() : dataPath);
                unitData.setEnvelope(envelopePath == null ? update.getEnvelope() : envelopePath);
                unitData.setExceed(exceedPath == null ? update.getExceed() : exceedPath);
                unitData.setReport(reportPath == null ? update.getReport() : reportPath);
            } else {
                unitData.setLayout(layoutPath);
                unitData.setData(dataPath);
                unitData.setEnvelope(envelopePath);
                unitData.setExceed(exceedPath);
                unitData.setReport(reportPath);
            }
            unitData.setCreateTime(formatTime(datetime));
            unitDataJPA.save(unitData);

        } catch (Exception ex) {
            log.info("constructRefer add failed", ex);
            throw new BizException(ResponseType.ADD_FAILED);
        }
    }

    public void delete(Long id) {
        unitDataJPA.deleteById(id);
    }

    public UnitData getbyId(Long id) {
        return unitDataJPA.findById(id).get();
    }

    public Long getCount() {
        return unitDataJPA.count();
    }
}
