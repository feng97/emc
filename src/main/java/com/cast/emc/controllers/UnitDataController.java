package com.cast.emc.controllers;

import com.cast.emc.model.Label;
import com.cast.emc.model.UnitData;
import com.cast.emc.model.enums.DataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping
public class UnitDataController extends BasicController {
    @ResponseBody
    @RequestMapping(value = "/unitdata", method = RequestMethod.POST)
    public Object add(UnitData unitData,
                      @RequestParam(value = "layoutFile") MultipartFile layoutFile,
                      @RequestParam(value = "dataFile") MultipartFile dataFile,
                      @RequestParam(value = "envelopeFile") MultipartFile envelopeFile,
                      @RequestParam(value = "exceedFile") MultipartFile exceedFile,
                      @RequestParam(value = "reportFile") MultipartFile reportFile,
                      String datetime) {

        unitDataService.saveOrUpdate(unitData, layoutFile, dataFile, envelopeFile, exceedFile, reportFile, datetime);

        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/unitdata/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        unitDataService.delete(id);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/api/unitdata", method = RequestMethod.GET)
    public Object getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          String condition) {
        List<UnitData> unitDatas;
        long count;
        if (StringUtils.isEmpty(condition)) {
            unitDatas = unitDataService.getPage(page - 1, limit);
            count = unitDataService.getCount();
        } else {
            unitDatas = unitDataService.getLike(page - 1, limit, condition);
            count = (long) unitDatas.size();
        }
        Map ok = ok(unitDatas);
        ok.put("count", count);
        return ok;
    }

    @RequestMapping(value = "/unitdata/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {
        UnitData unitData = unitDataService.getbyId(id);
        List<Label> unitLabel = labelService.getByType(DataType.UNIT);
        model.addAttribute("unitData", unitData);
        model.addAttribute("unitLabel", unitLabel);
        return "unitdata/add";
    }
}
