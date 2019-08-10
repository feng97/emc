package com.cast.emc.controllers;

import com.cast.emc.model.SampleData;
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
public class SampleDataController extends BasicController {
    @ResponseBody
    @RequestMapping(value = "/sampledata", method = RequestMethod.POST)
    public Object add(SampleData sampleData, MultipartFile file) {
        sampleDataService.saveOrUpdate(sampleData, file);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/sampledata/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        sampleDataService.delete(id);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/api/sampledata", method = RequestMethod.GET)
    public Object getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          String condition) {
        List<SampleData> sampleDatas;
        long count;
        if (StringUtils.isEmpty(condition)) {
            sampleDatas = sampleDataService.getPage(page - 1, limit);
            count = sampleDataService.getCount();
        } else {
            sampleDatas = sampleDataService.getLike(page - 1, limit, condition);
            count = (long) sampleDatas.size();
        }
        Map ok = ok(sampleDatas);
        ok.put("count", count);
        return ok;
    }

    @RequestMapping(value = "/sampledata/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {
        SampleData sampleData = sampleDataService.getbyId(id);
        model.addAttribute("sampleData", sampleData);
        return "sampledata/add";
    }
}
