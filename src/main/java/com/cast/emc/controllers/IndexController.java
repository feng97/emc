package com.cast.emc.controllers;

import com.cast.emc.model.Label;
import com.cast.emc.model.enums.DataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class IndexController extends BasicController {
    @RequestMapping(value = "/")
    public String index() {
        return "base";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/label/list")
    public String label() {
        return "unit";
    }

    @RequestMapping(value = "/emcdata/add")
    public String emcDataAdd() {
        return "emcdata/add";
    }

    @RequestMapping(value = "/emcdata/list")
    public String emcDataList() {
        return "emcdata/list";
    }

    @RequestMapping(value = "/pulsedata/add")
    public String pulseDataAdd() {
        return "pulsedata/add";
    }

    @RequestMapping(value = "/pulsedata/list")
    public String pulseDataList() {
        return "pulsedata/list";
    }

    @RequestMapping(value = "/freqdata/add")
    public String freqDataAdd() {
        return "freqdata/add";
    }

    @RequestMapping(value = "/freqdata/list")
    public String freqDataList() {
        return "freqdata/list";
    }

    @RequestMapping(value = "/sampledata/add")
    public String SampleDataAdd() {
        return "sampledata/add";
    }

    @RequestMapping(value = "/sampledata/list")
    public String SampleDataList() {
        return "sampledata/list";
    }

    @RequestMapping(value = "/envirdata/add")
    public String envirDataAdd(Model model) {
        List<Label> envirLabel = labelService.getByType(DataType.ENVIR);
        model.addAttribute("envirLabel", envirLabel);
        return "envirdata/add";
    }

    @RequestMapping(value = "/envirdata/list")
    public String envirDataList() {
        return "envirdata/list";
    }

    @RequestMapping(value = "/unitdata/add")
    public String unitDataAdd(Model model) {
        List<Label> unitLabel = labelService.getByType(DataType.UNIT);
        model.addAttribute("unitLabel", unitLabel);
        return "unitdata/add";
    }

    @RequestMapping(value = "/unitdata/list")
    public String unitDataList() {
        return "unitdata/list";
    }

    @RequestMapping(value = "/sysdata/add")
    public String sysDataAdd(Model model) {
        List<Label> sysLabel = labelService.getByType(DataType.SYS);
        model.addAttribute("sysLabel", sysLabel);
        return "sysdata/add";
    }

    @RequestMapping(value = "/sysdata/list")
    public String sysDataList() {
        return "sysdata/list";
    }
}
