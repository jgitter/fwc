package org.gitter.fwc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/institution")
public class InstitutionRestController {

    @Autowired
    private InstitutionService service;

    @RequestMapping(value = "/{keyword}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, String>> find(@PathVariable("keyword") String keyword) {
        return service.findByKeyword(keyword);
    }
}
