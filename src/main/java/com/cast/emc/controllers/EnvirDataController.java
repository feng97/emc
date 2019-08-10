package com.cast.emc.controllers;

import com.cast.emc.model.EnvirData;
import com.cast.emc.model.Label;
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
public class EnvirDataController extends BasicController {
    @ResponseBody
    @RequestMapping(value = "/envirdata", method = RequestMethod.POST)
    public Object add(EnvirData envirData,
                      @RequestParam(value = "layoutFile") MultipartFile layoutFile,
                      @RequestParam(value = "dataFile") MultipartFile dataFile,
                      @RequestParam(value = "envelopeFile") MultipartFile envelopeFile,
                      @RequestParam(value = "exceedFile") MultipartFile exceedFile,
                      @RequestParam(value = "reportFile") MultipartFile reportFile,
                      String datetime) {
        envirDataService.saveOrUpdate(envirData, layoutFile, dataFile, envelopeFile, exceedFile, reportFile, datetime);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/envirdata/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        envirDataService.delete(id);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/api/envirdata", method = RequestMethod.GET)
    public Object getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          String condition) {
        List<EnvirData> envirDatas;
        long count;
        if (StringUtils.isEmpty(condition)) {
            envirDatas = envirDataService.getPage(page - 1, limit);
            count = envirDataService.getCount();
        } else {
            envirDatas = envirDataService.getLike(page - 1, limit, condition);
            count = (long) envirDatas.size();
        }
        Map ok = ok(envirDatas);
        ok.put("count", count);
        return ok;
    }

    @RequestMapping(value = "/envirdata/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {
        EnvirData envirData = envirDataService.getbyId(id);
        List<Label> envirLabel = labelService.getByType(DataType.ENVIR);
        model.addAttribute("envirData", envirData);
        model.addAttribute("envirLabel", envirLabel);
        return "envirdata/add";
    }
}
