package com.gbm.edu.api.service.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.net.URLCodec;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gbm.edu.api.resttemplate.RestTemplateService;
import com.gbm.edu.api.service.EduApiService;
import com.gbm.edu.api.util.CommonUtils;
import com.gbm.edu.service.EduApiMapper;
import com.gbm.edu.util.AES256Util;
import com.gbm.edu.util.CamelCaseMap;
import com.gbm.edu.util.CommonUtil;

@Service
public class EduApiServiceImpl implements EduApiService {

	private static final DecimalFormat formatter = new DecimalFormat("#,###,###");

	@Autowired
	private RestTemplateService restTemplateService;

	@Autowired
	private EduApiMapper eduApiMapper;

	@Override
	@SuppressWarnings("unchecked")
    public CamelCaseMap getApi() throws Exception {
		CamelCaseMap result1 = new CamelCaseMap();
		result1.put("data", "education");
    	return result1;

    }


}
