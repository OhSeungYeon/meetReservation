package com.gbm.edu.api.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserModel {
	//사용자(user) 테이블
	
	
	//사용자아이디
	private String userId;
	
	//사용자비밀번호
	private String userPw;
	
	//관리자등급
	private String managerLe;
	
	//사용자번호
	private String userNum;
	
	//사용자이름
	private String userNm;
	
	//사용자부서
	private String department;
	
	//사용자전화번호
	private String phoneNum;
	
	//사용자이메일
	private String email;
	
	//사용자직급
	private String position;
	
    private boolean isAccountNonExpired;
    
    private boolean isAccountNonLocked;
    
    private boolean isCredentialsNonExpired;
    
    private boolean isEnabled;

	private Collection<? extends GrantedAuthority> authorities;

	
	//-----------------------------------
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getManagerLe() {
		return managerLe;
	}

	public void setManagerLe(String managerLe) {
		this.managerLe = managerLe;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
    
    
    

}
