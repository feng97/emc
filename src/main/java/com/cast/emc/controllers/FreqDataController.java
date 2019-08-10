package com.cast.emc.controllers;

import com.cast.emc.model.FreqData;
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
public class FreqDataController extends BasicController {
    @ResponseBody
    @RequestMapping(value = "/freqdata", method = RequestMethod.POST)
    public Object add(FreqData freqData, MultipartFile file, String datetime) {
        freqDataService.saveOrUpdate(freqData, file, datetime);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/freqdata/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        freqDataService.delete(id);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/api/freqdata", method = RequestMethod.GET)
    public Object getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          String condition) {
        List<FreqData> freqDatas;
        long count;
        if (StringUtils.isEmpty(condition)) {
            freqDatas = freqDataService.getPage(page - 1, limit);
            count =freqDataService.getCount();
        } else {
            freqDatas = freqDataService.getLike(page - 1, limit, condition);
            count = (long) freqDatas.size();
        }
        Map ok = ok(freqDatas);
        ok.put("count", count);
        return ok;
    }

    @RequestMapping(value = "/freqdata/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {
        FreqData freqData = freqDataService.getbyId(id);
        model.addAttribute("freqData", freqData);
        return "freqdata/add";
    }
}
