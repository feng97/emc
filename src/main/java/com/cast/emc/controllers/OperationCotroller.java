package com.cast.emc.controllers;

import com.cast.emc.model.FreqData;
import com.cast.emc.model.Label;
import com.cast.emc.model.Operation;
import com.cast.emc.model.enums.DataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @创建人 feng
 * @创建时间 2019/8/25
 * @描述
 */
@Slf4j
@Controller
@RequestMapping
public class OperationCotroller extends BasicController {
    @ResponseBody
    @RequestMapping(value = "/api/operation/getall", method = RequestMethod.GET)
    public Object getAll() {
        List<Operation> operations;
        long count;

        operations = operationService.getAll();
        count = operationService.getCount();

        Map ok = ok(operations);
        ok.put("count", count);
        return ok;
    }

}
