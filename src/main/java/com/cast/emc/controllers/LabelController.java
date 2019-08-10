package com.cast.emc.controllers;

import com.cast.emc.model.Label;
import com.cast.emc.model.enums.DataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class LabelController extends BasicController {
    @ResponseBody
    @RequestMapping(value = "/label", method = RequestMethod.POST)
    public Object add(Long id, String name, String content, String type) {
        Label label = Label.builder()
                .name(name)
                .content(content)
                .type(DataType.valueOf(type))
                .build();
        if (id != null) {
            label.setId(id);
        }
        labelService.saveOrUpdate(label);
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/label/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        labelService.delete(id);
        return ok();
    }

    @RequestMapping(value = "/label/unit", method = RequestMethod.GET)
    public String getUnit(Model model) {
        List<Label> unitLabel = labelService.getByType(DataType.UNIT);
        model.addAttribute("unitLabel", unitLabel);
        return "label/unit";
    }

    @RequestMapping(value = "/label/sys", method = RequestMethod.GET)
    public String getSys(Model model) {
        List<Label> sysLabel = labelService.getByType(DataType.SYS);
        model.addAttribute("sysLabel", sysLabel);
        return "label/sys";
    }

    @RequestMapping(value = "/label/envir", method = RequestMethod.GET)
    public String getEnvir(Model model) {
        List<Label> envirLabel = labelService.getByType(DataType.ENVIR);
        model.addAttribute("envirLabel", envirLabel);
        return "label/envir";
    }
}
