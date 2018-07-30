package com.zj.curd.entity;

import java.util.Date;

public class WkArticles {
	private String artId;
	private String artTitle;
	private String artKeywords;
	private String artContent;
	private String createUser;
	private String  createTime;
	private String  updateUser;
	private String  updateTime;
	private Integer status;
	private Integer mathchTimes;
	private Integer supportTimes;
	private Integer opposeTimes;
	private String canmodiUsers;
	
	public WkArticles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getArtId() {
		return artId;
	}
	public void setArtId(String artId) {
		this.artId = artId;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	public String getArtKeywords() {
		return artKeywords;
	}
	public void setArtKeywords(String artKeywords) {
		this.artKeywords = artKeywords;
	}
	public String getArtContent() {
		return artContent;
	}
	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getMathchTimes() {
		return mathchTimes;
	}
	public void setMathchTimes(Integer mathchTimes) {
		this.mathchTimes = mathchTimes;
	}
	public Integer getSupportTimes() {
		return supportTimes;
	}
	public void setSupportTimes(Integer supportTimes) {
		this.supportTimes = supportTimes;
	}
	public Integer getOpposeTimes() {
		return opposeTimes;
	}
	public void setOpposeTimes(Integer opposeTimes) {
		this.opposeTimes = opposeTimes;
	}
	public String getCanmodiUsers() {
		return canmodiUsers;
	}
	public void setCanmodiUsers(String canmodiUsers) {
		this.canmodiUsers = canmodiUsers;
	}
	@Override
	public String toString() {
		return "WfArticles [artId=" + artId + ", artTitle=" + artTitle + ", artKeywords=" + artKeywords
				+ ", artContent=" + artContent + ", createUser=" + createUser + ", createTime=" + createTime
				+ ", updateUser=" + updateUser + ", updateTime=" + updateTime + ", status=" + status + ", mathchTimes="
				+ mathchTimes + ", supportTimes=" + supportTimes + ", opposeTimes=" + opposeTimes + ", canmodiUsers="
				+ canmodiUsers + "]";
	}
	
	
}
