package com.gbm.edu.api.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gbm.edu.api.service.EduApiService;
import com.gbm.edu.security.model.SecurityUser;
import com.gbm.edu.security.service.UserService;
import com.gbm.edu.service.EduApiMapper;
import com.gbm.edu.util.CamelCaseMap;

@RestController
@RequestMapping(path="/api/login")
public class EduLoginController {
    
    @Autowired
    private EduApiService eduService;
    
    @Autowired
    private UserService userService;
	
    // 로그인 폼 화면
 	@GetMapping("/login")
 	public ModelAndView loginGet() throws Exception {
 		ModelAndView mv = new ModelAndView("/login");
 		return mv;
 	}
 	
    //회원가입
    @RequestMapping(value="/signup" , method=RequestMethod.GET)
    public ModelAndView signup() throws Exception {
    	
    	ModelAndView mv = new ModelAndView();

    	//부서테이블
		List<CamelCaseMap> department = eduService.department(); 
		//직급테이블
		List<CamelCaseMap> position = eduService.position(); 	
		mv.addObject("department", department);	
		mv.addObject("position", position);		
		
		mv.setViewName("/signup");
		return mv;
    }
    
    //회원가입 입력 값 받는 곳
    @RequestMapping(value="/signupList" , method=RequestMethod.POST)
    public ModelAndView signupList(SecurityUser securityUser, HttpServletRequest request) throws Exception {

    	ModelAndView mv = new ModelAndView();
    	userService.joinUser(securityUser, request);
    	
    	mv.setViewName("signupSuccess");
		return mv;
    }
    
    //아이디 중복체크
    @RequestMapping(value="/idCheck" , method=RequestMethod.POST)
    public Map<String, String> idCheck(SecurityUser securityUser) throws Exception{

    	Map<String, String> idChk = new HashMap<>();
		String result = userService.idChk(securityUser);
		
		if(result == "Y") idChk.put("data", "success");
		else idChk.put("data", "fail");
		
		return idChk;
		
    }
    
}