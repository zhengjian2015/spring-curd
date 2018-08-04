package com.zj.curd.entity;

public class WkImages {
	private String imgId;
	private String fileName;
	private Integer fileSize;
	private String createUser;
	private String createTime;
	private String fileData;
	public WkImages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
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
	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	@Override
	public String toString() {
		return "WkImages [imgId=" + imgId + ", fileName=" + fileName + ", fileSize=" + fileSize + ", createUser="
				+ createUser + ", createTime=" + createTime + ", fileData=" + fileData + "]";
	}
	
	
	
}
