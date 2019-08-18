package com.cast.emc.controllers;

import com.cast.emc.model.UsedFreqPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @创建人 feng
 * @创建时间 2019/8/18
 * @描述
 */
@Slf4j
@Controller
@RequestMapping
public class UsedFreqPointController extends BasicController{
    @ResponseBody
    @RequestMapping(value = "/usedfreqpoint", method = RequestMethod.POST)
    public Object add(UsedFreqPoint usedFreqPoint) {
        usedFreqPointService.saveOrUpdate(usedFreqPoint);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/usedfreqpoint/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        usedFreqPointService.delete(id);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/api/usedfreqpoint", method = RequestMethod.GET)
    public Object getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          String condition) {
        List<UsedFreqPoint> usedFreqPoints;
        long count;
        if (StringUtils.isEmpty(condition)) {
            usedFreqPoints = usedFreqPointService.getPage(page - 1, limit);
            count = sampleDataService.getCount();
        } else {
            usedFreqPoints = usedFreqPointService.getLike(page - 1, limit, condition);
            count = (long) usedFreqPoints.size();
        }
        Map ok = ok(usedFreqPoints);
        ok.put("count", count);
        return ok;
    }

    @RequestMapping(value = "/usedfreqpoint/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {
        UsedFreqPoint usedFreqPoint = usedFreqPointService.getbyId(id);
        model.addAttribute("usedFreqPoint", usedFreqPoint);
        return "usedfreqpoint/list";
    }
}
