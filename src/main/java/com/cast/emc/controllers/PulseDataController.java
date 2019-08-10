package com.cast.emc.controllers;

import com.cast.emc.model.PulseData;
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
public class PulseDataController extends BasicController{
    @ResponseBody
    @RequestMapping(value = "/pulsedata", method = RequestMethod.POST)
    public Object add(PulseData pulseData, MultipartFile file, String datetime) {
        pulseDataService.saveOrUpdate(pulseData, file, datetime);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/pulsedata/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        pulseDataService.delete(id);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/api/pulsedata", method = RequestMethod.GET)
    public Object getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          String condition) {
        List<PulseData> pulseDatas;
        long count;
        if (StringUtils.isEmpty(condition)) {
            pulseDatas = pulseDataService.getPage(page - 1, limit);
            count = pulseDataService.getCount();
        } else {
            pulseDatas = pulseDataService.getLike(page - 1, limit, condition);
            count = (long) pulseDatas.size();
        }
        Map ok = ok(pulseDatas);
        ok.put("count", count);
        return ok;
    }

    @RequestMapping(value = "/pulsedata/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {
        PulseData pulseData = pulseDataService.getbyId(id);
        model.addAttribute("pulseData", pulseData);
        return "pulsedata/add";
    }
}
