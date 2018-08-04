package com.zj.curd.dao;

import org.apache.ibatis.annotations.Param;

import com.zj.curd.entity.WkArticlesauthor;
import com.zj.curd.entity.WkImages;

public interface WikiImagesDao {
	int saveImages(WkImages wkImages);
	
	WkImages getImageById(@Param(value="imgId") String imgId);
}
