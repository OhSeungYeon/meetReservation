package com.gbm.edu.security.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.gbm.edu.api.model.ResModel;
import com.gbm.edu.security.model.SecurityUser;
import com.gbm.edu.security.model.UserInfo;
import com.gbm.edu.service.EduApiMapper;
import com.gbm.edu.util.CamelCaseMap;

import org.apache.log4j.Logger;

@Service
public class UserService implements UserDetailsService {
	
	//log
	private Logger log = Logger.getLogger(UserService.class);
	
	@Autowired
    EduApiMapper eduApiMapper;

	@Override
	public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
		//로그인
		UserInfo userInfo = eduApiMapper.readUser1(username);
		
		if(userInfo==null) {
			throw new  UsernameNotFoundException(username);
		}
		
		return userInfo;
	}

	//회원가입
	public int joinUser(SecurityUser securityUser, HttpServletRequest request) {
		
		 //비밀번호 암호화
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

		 String passwordEn = passwordEncoder.encode(securityUser.getPassword());
		 log.info("--------회원가입 비밀번호 암호화 : " + passwordEn + "--------");
		 
		 //수동으로 넣어야하는 값 set
		 securityUser.setPassword(passwordEn);
		 securityUser.setManagerLe("A001");
		 securityUser.setRegId(securityUser.getUserNm());
		 securityUser.setModId(securityUser.getUserNm());
		 securityUser.setRole("USER");
		 securityUser.setCudMode("Create");
		 	
	    String ip = request.getHeader("X-FORWARDED-FOR"); 
		//proxy 환경일 경우
		if (ip == null || ip.length() == 0) ip = request.getHeader("Proxy-Client-IP");
		//웹로직 서버일 경우
		if (ip == null || ip.length() == 0) ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0) ip = request.getRemoteAddr() ;
	        
	        
	    securityUser.setModIp(ip);
	    securityUser.setRegIp(ip);
	     		 
		 //가장 최근에 들어갔던 user_num 불러오기 (ex. U010)
		 String userCount = eduApiMapper.userCount(securityUser);

		 //앞에 문자열 부분만 제외시키고 짜르기
		 String substring = userCount.substring(1, 4);
		 //문자열을 int형으로 변환
		 int intUserCount = Integer.parseInt(substring);
		 //최근에 값에서 ++ (ex.010 -> 011)
		 intUserCount++;
		 
		 //3자리 중 남는 자리가 생기면 0으로 채움
		 String userCountRe=String.format("%03d", intUserCount);
		 
		 String userNum = "U" + userCountRe;
		 
		 securityUser.setUserNum(userNum);
 
		 securityUser.getUserNum();
		 eduApiMapper.joinUser(securityUser);
		 
		 log.info("--------회원가입 성공--------");
		 return 1; 	
	}
	
	
	//회원가입 아이디 중복체크
	public String idChk(SecurityUser securityUser) {
		
		int result = eduApiMapper.idChk(securityUser);
		if(result == 0) return "Y";
		else return "N";
			
	}

}
