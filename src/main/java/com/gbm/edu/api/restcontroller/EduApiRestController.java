package com.gbm.edu.api.restcontroller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gbm.edu.api.model.ResModel;
import com.gbm.edu.api.service.EduApiService;
import com.gbm.edu.security.model.SecurityUser;
import com.gbm.edu.security.model.UserInfo;
import com.gbm.edu.security.service.UserService;
import com.gbm.edu.util.CamelCaseMap;
import com.gbm.edu.config.PagingVO;

import org.apache.log4j.Logger;

@RestController
@RequestMapping(path="/api/edu")
public class EduApiRestController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private EduApiService service;
    
	private Logger log = Logger.getLogger(EduApiRestController.class);
    
    //회의실 테이블
    @RequestMapping(value="/readView" , method=RequestMethod.GET)
    public ModelAndView list() throws Exception {
    	
    	ModelAndView mv = new ModelAndView();
		List<CamelCaseMap> result = service.getList1();
		mv.addObject("params", result);

		mv.setViewName("page:readView");
		return mv;
    }
    
    //회의실예약 테이블
    @RequestMapping(value="/res" , method=RequestMethod.GET)
    public ModelAndView resView() throws Exception{
    	
    	ModelAndView mv = new ModelAndView();
		List<CamelCaseMap> res = service.getRes();
		mv.addObject("params", res);	
		
		mv.setViewName("page:resView");
		return mv;
    }  
    
    //사용자 테이블
    @RequestMapping(value="/user" , method=RequestMethod.GET)
    public ModelAndView user(PagingVO vo,
            				 @RequestParam(defaultValue="1", required=false)String nowPage) throws Exception {
    	
    	ModelAndView mv = new ModelAndView("page:user");
		vo = new PagingVO(service.getcountUser(), Integer.parseInt(nowPage), 10);
    	mv.addObject("paging", vo);
    	mv.addObject("params", service.getUser(vo));
		return mv;
    }
    
    //관리자등급 테이블
    @RequestMapping(value="/manager" , method=RequestMethod.GET)
    public ModelAndView manager() throws Exception {
    	
    	ModelAndView mv = new ModelAndView();
		List<CamelCaseMap> manager = service.getManager();
		mv.addObject("params", manager);

		mv.setViewName("page:manager");
		return mv;
    }
    
    //비밀번호 변경 form 연결
    @RequestMapping(value="/pwChangeForm", method=RequestMethod.GET)
    public ModelAndView pwChangeForm() throws Exception {
    	
		ModelAndView mv = new ModelAndView();
			
		mv.setViewName("foot:pwChange");
		return mv;
    }
    
    //기존 비밀번호가 맞는지 체크 (비밀번호 변경 및 회원 탈퇴)
    @RequestMapping(value="/pwChange" , method=RequestMethod.POST)
    public Map<String, String> pwChange(@RequestParam(value="existPass", required=false)String existPass
    									,HttpSession session) throws Exception { 

		Map<String, String> pwChange = new HashMap<>();
		String result = service.pwChange(existPass, session);
		
		if(result == "Y") pwChange.put("data", "success");
		else pwChange.put("data", "fail");
		
		return pwChange;
	
	}
   
    //비밀번호 변경 update
    @RequestMapping(value="/updatePw" , method=RequestMethod.POST)
    public Map<String, String> updatePw(@RequestParam(value="newPass", required=false)String newPass
    							        ,@RequestParam(value="sessionId", required=false)String sessionId) throws Exception {
    	
    	Map<String, String> pwChange = new HashMap<>();
		String result = service.updatePw(newPass, sessionId);
		
		if(result == "Y") pwChange.put("data", "success");
		else pwChange.put("data", "fail");
		
		return pwChange;
    }
    
    //회원탈퇴 form 연결
    @RequestMapping(value="/deleteMemberForm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteMemberForm() throws Exception {
    	
		ModelAndView mv = new ModelAndView();
			
		mv.setViewName("foot:deleteMemberForm");
		return mv;
    }
    
    //회원 탈퇴 delete
    @RequestMapping(value="/deleteMember" , method=RequestMethod.POST)
    public Map<String, String> deleteMember(
    		@RequestParam(value="sessionId", required=false)String sessionId) throws Exception {
    	
    	Map<String, String> deleteMember = new HashMap<>();
		String result = service.deleteMember(sessionId);
		
		if(result == "Y") deleteMember.put("data", "success");
		else deleteMember.put("data", "fail");
		
		return deleteMember;
    }
    
    //개인정보 수정 form 연결
    @RequestMapping(value="/modifyInformation", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView modifyInformation(HttpSession session) throws Exception {
    	
		ModelAndView mv = new ModelAndView();
		
		List<CamelCaseMap> user = service.modifyUser(session);
		List<CamelCaseMap> department = service.department(); 
		List<CamelCaseMap> position = service.position(); 

		mv.addObject("params", user);
		mv.addObject("department", department);	
		mv.addObject("position", position);		
	
		mv.setViewName("part:modifyInformation");
		return mv;
    }
    
    
    //개인정보 update
    @RequestMapping(value="/updateMember", method=RequestMethod.POST)
    public Map<String, String> updateMember(
    		SecurityUser securityUser, HttpServletRequest request) throws Exception {

		Map<String, String> pwChange = new HashMap<>();
		String result = service.updateMember(securityUser, request);
		
		if(result == "Y") pwChange.put("data", "success");
		else pwChange.put("data", "fail");
		
		return pwChange;
	}
    
    //회의실 갤러리
    @RequestMapping(value="/gallery" , method=RequestMethod.GET)
    public ModelAndView gallery() throws Exception {
    	
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("page:gallery");
		return mv;
    }
    
    //갤러리
    @RequestMapping(value="/gallery1" , method=RequestMethod.GET)
    public ModelAndView gallery1() throws Exception {
    	
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("page:gallery1");
		return mv;
    }
    
    
    //예약하기
    @RequestMapping(value="/reservation" , method=RequestMethod.GET)
    public ModelAndView timeRes() throws Exception {
    	
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("part:timeRes");
		return mv;
    }
    
    //예약하기
    @RequestMapping(value="/insertRes" , method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> insertRes(@RequestParam(value="stHour", required=false)String stHour
    									 ,@RequestParam(value="stMinute", required=false)String stMinute
    									 ,@RequestParam(value="finHour", required=false)String finHour
    									 ,@RequestParam(value="finMinute", required=false)String finMinute
    									 ,ResModel resModel, HttpServletRequest request) throws ParseException {
    	
    	Map<String, String> timeResCheck = new HashMap<>();

    	log.info(resModel.getUsername());
    	log.info(resModel.getMeetingId());
    	log.info(resModel.getUserNm());
    	log.info(resModel.getReason());
    	log.info(stHour);
    	log.info(stMinute);
    	log.info(finHour);
    	log.info(finMinute);
    	
		String result = service.insertRes(stHour, stMinute, finHour, finMinute, resModel, request);
		
		if(result == "Y") timeResCheck.put("data", "success");
		else timeResCheck.put("data", "fail");
		
		return timeResCheck;
    }
    
    //admin
    //관리자 등록 페이지
    @RequestMapping(value="/admin/admin" , method=RequestMethod.GET)
    public ModelAndView admin() throws Exception {
    	
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("foot:admin");
		return mv;
    }
    
    //updateAdmin
    @RequestMapping(value="/admin/updateAdmin" , method=RequestMethod.POST)
    public Map<String, String> updateAdmin(SecurityUser securityUser) throws Exception {
    	
    	Map<String, String> updateAdmin = new HashMap<>();
		String result = service.updateAdmin(securityUser);
		
		if(result == "Y") updateAdmin.put("data", "success");
		else updateAdmin.put("data", "fail");
		
		return updateAdmin;
    }
    
    //admin
    //승인 테이블 (ADMIN) 승인한시간이 null인 컬럼들만 가져와서 보여주기
    @RequestMapping(value="/admin/approvalRes" , method=RequestMethod.GET)
    public ModelAndView approvalRes() {
    	
    	ModelAndView mv = new ModelAndView();

		try {
			List<CamelCaseMap> res = service.approvalRes();
			mv.addObject("params", res);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("page:approvalRes");
		return mv;
    }
    
    //admin
    //승인하기 버튼 실행시 승인시간 update
    @RequestMapping(value="/admin/updateRes" , method=RequestMethod.POST)
    public Map<String, String> updateRes(ResModel resModel) {
    	
    	Map<String, String> updateRes = new HashMap<>();

		String result = service.updateRes(resModel);

		if(result == "Y") updateRes.put("data", "success");
		else updateRes.put("data", "fail");
		
		return updateRes;
    }
    
    //admin
    //1시간마다 승인 몇건 남았는지 log로 출력
    @Scheduled(cron = "0 0 0/1 * * *")
    @RequestMapping(value="/countApproval" , method=RequestMethod.POST)
    public void countApproval() {
    	
		int result = service.countApproval();
		
		log.info("승인 " + result + "건 남았습니다.");
		
    }
   
    
}
