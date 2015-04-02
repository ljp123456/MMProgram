package com.example.entity;

import java.io.Serializable;

public class User implements Serializable{

	private String userName;
	private String nickName;
	private String userPwd;
	private String userSex;
	//个性签名
	private String userSign;
	//头像
	private int userImageId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserSign() {
		return userSign;
	}
	public void setUserSign(String userSign) {
		this.userSign = userSign;
	}
	public int getUserImageId() {
		return userImageId;
	}
	public void setUserImageId(int userImageId) {
		this.userImageId = userImageId;
	}
	public User(String userName, String nickName, String userPwd,
			String userSex, String userSign, int userImageId) {
		super();
		this.userName = userName;
		this.nickName = nickName;
		this.userPwd = userPwd;
		this.userSex = userSex;
		this.userSign = userSign;
		this.userImageId = userImageId;
	}
	public User(String userName, String nickName, String userPwd,
			String userSex) {
		super();
		this.userName = userName;
		this.nickName = nickName;
		this.userPwd = userPwd;
		this.userSex = userSex;
	}
	public User() {
		super();
	}
}
