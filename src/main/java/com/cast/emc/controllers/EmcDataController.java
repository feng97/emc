package com.cast.emc.controllers;

import com.cast.emc.model.EmcData;
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
public class EmcDataController extends BasicController {
    @ResponseBody
    @RequestMapping(value = "/emcdata", method = RequestMethod.POST)
    public Object add(EmcData emcData, MultipartFile file, String datetime) {
        emcDataService.saveOrUpdate(emcData, file, datetime);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/emcdata/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        emcDataService.delete(id);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/api/emcdata", method = RequestMethod.GET)
    public Object getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          String condition) {
        List<EmcData> emcDatas;
        long count;
        if (StringUtils.isEmpty(condition)) {
            emcDatas = emcDataService.getPage(page - 1, limit);
            count = emcDataService.getCount();
        } else {
            emcDatas = emcDataService.getLike(page - 1, limit, condition);
            count = (long) emcDatas.size();
        }
        Map ok = ok(emcDatas);
        ok.put("count", count);
        return ok;
    }

    @RequestMapping(value = "/emcdata/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {
        EmcData emcData = emcDataService.getbyId(id);
        model.addAttribute("emcData", emcData);
        return "emcdata/add";
    }
}
