package com.gbm.edu.api.restcontroller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gbm.edu.api.service.EduApiService;
import com.gbm.edu.util.CamelCaseMap;

@RestController
@RequestMapping(path="/api/edu")
public class EduApiRestController {

    @Autowired
    private EduApiService EduApiService;

    @RequestMapping(value="/edu" , method= {RequestMethod.POST , RequestMethod.GET },headers = {"Accept=application/json"})
    public ModelAndView getOrderInput() {
		ModelAndView mv = new ModelAndView();
		try {
			CamelCaseMap result = EduApiService.getApi();
			mv.addObject("params", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//mv.addObject("params", params);
		mv.setViewName("edu/edu");
		return mv;
    }
    
}
