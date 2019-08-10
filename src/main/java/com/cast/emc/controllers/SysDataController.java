package com.cast.emc.controllers;

import com.cast.emc.model.Label;
import com.cast.emc.model.SysData;
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
public class SysDataController extends BasicController {
    @ResponseBody
    @RequestMapping(value = "/sysdata", method = RequestMethod.POST)
    public Object add(SysData sysData,
                      @RequestParam(value = "layoutFile") MultipartFile layoutFile,
                      @RequestParam(value = "dataFile") MultipartFile dataFile,
                      @RequestParam(value = "envelopeFile") MultipartFile envelopeFile,
                      @RequestParam(value = "exceedFile") MultipartFile exceedFile,
                      @RequestParam(value = "reportFile") MultipartFile reportFile,
                      String datetime) {
        sysDataService.saveOrUpdate(sysData, layoutFile, dataFile, envelopeFile, exceedFile, reportFile, datetime);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/sysdata/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        sysDataService.delete(id);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/api/sysdata", method = RequestMethod.GET)
    public Object getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          String condition) {
        List<SysData> sysDatas;
        long count;
        if (StringUtils.isEmpty(condition)) {
            sysDatas = sysDataService.getPage(page - 1, limit);
            count = sysDataService.getCount();
        } else {
            sysDatas = sysDataService.getLike(page - 1, limit, condition);
            count = (long) sysDatas.size();
        }
        Map ok = ok(sysDatas);
        ok.put("count", count);
        return ok;
    }

    @RequestMapping(value = "/sysdata/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {
        SysData sysData = sysDataService.getbyId(id);
        model.addAttribute("sysData", sysData);
        List<Label> sysLabel = labelService.getByType(DataType.SYS);
        model.addAttribute("sysLabel", sysLabel);
        return "sysdata/add";
    }
}
