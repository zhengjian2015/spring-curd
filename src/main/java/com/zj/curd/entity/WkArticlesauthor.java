package com.zj.curd.entity;

public class WkArticlesauthor extends WkArticles{
	private String createfullName;

	private String updatefullName;
	
	private Boolean canDeal;

	private Boolean canModi;
	
	public String getCreatefullName() {
		return createfullName;
	}

	public void setCreatefullName(String createfullName) {
		this.createfullName = createfullName;
	}

	public String getUpdatefullName() {
		return updatefullName;
	}

	public void setUpdatefullName(String updatefullName) {
		this.updatefullName = updatefullName;
	}

	public Boolean getCanDeal() {
		return canDeal;
	}

	public void setCanDeal(Boolean canDeal) {
		this.canDeal = canDeal;
	}

	public Boolean getCanModi() {
		return canModi;
	}

	public void setCanModi(Boolean canModi) {
		this.canModi = canModi;
	}
	
	
}
