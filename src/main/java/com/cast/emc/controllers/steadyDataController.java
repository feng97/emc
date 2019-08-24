package com.cast.emc.controllers;

import com.cast.emc.model.SteadyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @创建人 feng
 * @创建时间 2019/8/24
 * @描述
 */
@Slf4j
@Controller
@RequestMapping
public class steadyDataController extends BasicController{
    @ResponseBody
    @RequestMapping(value = "/steadydata", method = RequestMethod.POST)
    public Object add(SteadyData steadyData, MultipartFile file, MultipartFile reportFile, String datetime) {
        steadyDataService.saveOrUpdate(steadyData, file, reportFile, datetime);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/steadydata/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        steadyDataService.delete(id);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/api/steadydata", method = RequestMethod.GET)
    public Object getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          String condition) {
        List<SteadyData> steadyDatas;
        long count;
        if (StringUtils.isEmpty(condition)) {
            steadyDatas = steadyDataService.getPage(page - 1, limit);
            count = steadyDataService.getCount();
        } else {
            steadyDatas = steadyDataService.getLike(page - 1, limit, condition);
            count = (long) steadyDatas.size();
        }
        Map ok = ok(steadyDatas);
        ok.put("count", count);
        return ok;
    }

    @RequestMapping(value = "/steadydata/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {
        SteadyData steadyData = steadyDataService.getbyId(id);
        model.addAttribute("steadyData", steadyData);
        return "steadydata/add";
    }
}
