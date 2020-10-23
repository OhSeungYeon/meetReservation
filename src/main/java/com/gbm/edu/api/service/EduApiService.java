package com.gbm.edu.api.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gbm.edu.util.CamelCaseMap;

public interface EduApiService {
	
	public CamelCaseMap getApi() throws Exception; 
}
