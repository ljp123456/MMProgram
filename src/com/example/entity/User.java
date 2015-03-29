package com.example.entity;

import java.io.Serializable;

public class User implements Serializable{

	private int id;
	private String userName;
	private String userPwd;
	private String userSex;
	//个性签名
	private String userSign;
	//头像
	private int userImageId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public User(int id, String userName, String userPwd, String userSex,
			String userSign, int userImageId) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userSex = userSex;
		this.userSign = userSign;
		this.userImageId = userImageId;
	}
	public User(String userName, String userPwd, String userSex,
			String userSign, int userImageId) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
		this.userSex = userSex;
		this.userSign = userSign;
		this.userImageId = userImageId;
	}
	public User() {
		super();
	}
}
