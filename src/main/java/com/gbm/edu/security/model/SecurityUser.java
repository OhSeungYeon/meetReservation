package com.gbm.edu.security.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbm.edu.model.Domain;

import lombok.Data;

@Data
public class SecurityUser extends Domain {
	
	//사용자아이디
	private String username;
	
	//사용자비밀번호
	private String password;
	
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
	
	//사용자
	private String role;
	
	//사용자직급
	private String position;
	
	//private String _csrf;
	
    //private boolean isAccountNonExpired;
    
    //private boolean isAccountNonLocked;
    
    //private boolean isCredentialsNonExpired;
    
   // private boolean isEnabled;

	//private Collection<? extends GrantedAuthority> authorities;

	
	//---------------------------------
	
	/*
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
*/
	//-------------------------
	

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/*
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	*/
}
