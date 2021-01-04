package com.gbm.edu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.gbm.edu.api.model.BoardModel;
import com.gbm.edu.api.model.ResModel;
import com.gbm.edu.api.model.UserModel;
import com.gbm.edu.security.model.SecurityUser;
import com.gbm.edu.security.model.UserInfo;
import com.gbm.edu.util.CamelCaseMap;

@Repository
public interface EduApiMapper {
	
	//회의실
	List<CamelCaseMap> getList1();
	
	//회의실예약
	List<CamelCaseMap> getRes();

	int getcountUser();
	
	List<CamelCaseMap> getUser(@Param("start")String start, @Param("end")String end);
	
	//관리자등급
	List<CamelCaseMap> getManager();
	
	List<CamelCaseMap> position();
	
	List<CamelCaseMap> department();
	
	//----- security ----------
	SecurityUser readUser(String username);

	//security (회원가입)
	int joinUser(SecurityUser securityUser);
	
	//security (회원가입 - 최신 user_num 값 가져오기)
	String userCount(SecurityUser securityUser);

	//void updatePw(SecurityUser securityUser);
	void updatePw(@Param("sessionId") String sessionId, @Param("passwordEn") String passwordEn);

	void deleteMember(String sessionId);

	List<CamelCaseMap> modifyUser(String sessionId);
	
	void updateMember(String username);

	//아이디중복체크
	int idChk(SecurityUser securityUser);

	void updateMember(SecurityUser securityUser);

	void insertRes(ResModel resModel);

	List<CamelCaseMap> approvalRes();

	void updateRes(ResModel resModel);

	int countApproval();

	void updateAdmin(SecurityUser securityUser);

	UserInfo readUser1(String username);

	//session id에 맞는 password 가져오기
	String getPassword(@Param("id") String id);

}
