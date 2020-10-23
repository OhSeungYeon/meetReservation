package com.gbm.edu.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gbm.edu.util.CamelCaseMap;

@Repository
public interface EduApiMapper {
	
	CamelCaseMap getApi(@Param("seq") String seq);
}
