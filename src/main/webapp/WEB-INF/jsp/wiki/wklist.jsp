<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章列表</title>

<link href="${pageContext.request.contextPath}/lib/wiki/plugins/bootstrap/css/bootstrap.min.dong.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/css/style-metro.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/css/style.dong.min.css" rel="stylesheet" type="text/css"/>
<link href='${pageContext.request.contextPath}/lib/wiki/css/pages/blog.css'  rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 blog-page">
			<div class="span9 article-block">
			<c:forEach items="${wikiArticlesList}" var="a">
				<div class="row-fluid">
					<div class="span12 blog-article">
						<h2><a href="${pageContext.request.contextPath}/wiki/wkv2/${a.artId}">${a.artTitle}</a></h2>
						<div class="blog-img blog-tag-data">
							<div class="span6">
								<a href="#">只看正文</a>
								<ul class="unstyled inline blog-tags">
									<li>
										<i class="icon-tags"></i>
										<a href="#">tag</a>
									</li>
								</ul>
								<ul class="unstyled inline blog-tags">
	                                <li><i class="icon-eye-open"></i>${a.mathchTimes}
	                                </li>
                                </ul>
							</div>
							<div class="span6 blog-tag-data-inner">
								<ul class="unstyled inline">
									<li><a href="#" class="tooltips" data-original-title="aaa"><i class="icon-group"></i> 权限</a></li>
									<li><a href="javascript:delAction(12)"><i class="icon-trash"></i> 删除</a></li>
									<li><a href="#"><i class="icon-edit"></i> 编辑</a></li>
									<li><i class="icon-user"></i>${a.updateUser}</li>
                                    <li><i class="icon-calendar"></i>${a.updateTime}</li>
								</ul>
							</div>
						</div>
						<p>${a.artTitle}</p>
					</div>
				</div>
				<hr style="margin-top: 0px !important">
			</c:forEach>
			</div>
			<!--end span9-->
			
		</div>
		<div class="pagination">
	        <ul>
	            <li class="active"><a href="#">上一页</a></li>
	            <li class="active"><a href="#">下一页</a></li>
	        </ul>
       	</div>
	</div>
</div>
</body>
</html>