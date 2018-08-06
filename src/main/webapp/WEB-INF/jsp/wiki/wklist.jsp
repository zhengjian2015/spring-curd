<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>文章列表</title>
<script src="${pageContext.request.contextPath}/lib/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/wiki/plugins/select2/select2.js"></script>
<link href="${pageContext.request.contextPath}/lib/wiki/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/lib/wiki/plugins/bootstrap-modal/js/bootstrap-modal.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/wiki/plugins/bootstrap-modal/js/bootstrap-modalmanager.js"></script>
<link href="${pageContext.request.contextPath}/lib/wiki/plugins/bootstrap/css/bootstrap.min.dong.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/css/style-metro.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/css/style.dong.min.css" rel="stylesheet" type="text/css"/>
<link href='${pageContext.request.contextPath}/lib/wiki/css/pages/blog.css'  rel="stylesheet" type="text/css"/>
<script type="text/javascript">


function delAction(id) {
	var path = $("#mypath").val();
    if (id && id != "") {
        if (confirm("确定要删除这篇文章吗？")) {
            jQuery.ajax({
                type: "DELETE",
                url: path+"/wiki/art/"+id,
                async: false,
                success: function(data) {
                	if (data.code!=200) {
                        alert(data.msg);
                    }else {
                        alert('删除成功！');
                        location.reload();
                    }
                }
            });
        }
    }
}
function showModiusers(id) {
    	window.location.href="${pageContext.request.contextPath}/wiki/wkmodiuser";
}
</script>
</head>
<body>
<br>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 blog-page">
			<div class="span9 article-block">
			<c:forEach items="${pageInfo.list}" var="a">
				<div class="row-fluid">
					<div class="span12 blog-article">
						<h2><a href="${pageContext.request.contextPath}/wiki/wkv2/${a.artId}">${a.artTitle}</a></h2>
						<div class="blog-img blog-tag-data">
							<div class="span6">
								<a href="${pageContext.request.contextPath}/wiki/wkv1/${a.artId}">只看正文</a>
								<ul class="unstyled inline blog-tags">
									<li>
										<i class="icon-tags"></i>
										<c:forEach items="${a.artKeywords}" var="k">
                    						<a href="${pageContext.request.contextPath}/wiki/wklist?tag=${k}"> ${k}</a>
                						</c:forEach>
									</li>
								</ul>
								<ul class="unstyled inline blog-tags">
	                                <li><i class="icon-eye-open"></i>${a.mathchTimes}
	                                </li>
                                </ul>
							</div>
							<div class="span6 blog-tag-data-inner">
								<ul class="unstyled inline">
									<c:if test="${a.canDeal == true}"> 	                     
										<li><a href="javascript:showModiusers(12)" class="tooltips" data-original-title="aaa"><i class="icon-group"></i> 权限</a></li>
										<li><a href="javascript:delAction('${a.artId}')"><i class="icon-trash"></i> 删除</a></li>
									</c:if>
									<c:if test="${a.canDeal == true}"> 	  
										<li><a href="${pageContext.request.contextPath}/wiki/wkedit/${a.artId}"><i class="icon-edit"></i> 编辑</a></li>
									</c:if>
									<li><i class="icon-user"></i>${a.updatefullName}</li>
                                    <li><i class="icon-calendar"></i>${a.updateTime}</li>
								</ul>
							</div>
						</div>
						<p><c:out value="${fn:substring(a.artContent, 0, 200)}" /></p>
					</div>
					<input type="hidden" value="${pageContext.request.contextPath}" id="mypath"/>
				</div>
				<hr style="margin-top: 0px !important">
			</c:forEach>
			</div>
			<!--end span9-->
			<%@ include file="wkside.jsp"%>
		</div>
		<div class="pagination">
            <!--分页文字信息  -->
            <div class="col-md-6 pageMessage" id="page_info_area">当前 ${pageInfo.pageNum }页,总${pageInfo.pages }
                页,总 ${pageInfo.total } 条记录</div>
            <!-- 分页条信息 -->
            <div class="col-md-6" id="page_nav_area">
                <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="${pageContext.request.contextPath}/wiki/wklist?pn=1">首页</a></li>
                    <c:if test="${pageInfo.hasPreviousPage }">
                        <li><a href="${pageContext.request.contextPath}/wiki/wklist?pn=${pageInfo.pageNum-1}"
                            aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                        </a></li>
                    </c:if>


                    <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                        <c:if test="${page_Num == pageInfo.pageNum }">
                            <li class="active"><a href="#">${page_Num }</a></li>
                        </c:if>
                        <c:if test="${page_Num != pageInfo.pageNum }">
                            <li><a href="${pageContext.request.contextPath}/wiki/wklist?pn=${page_Num }">${page_Num }</a></li>
                        </c:if>

                    </c:forEach>
                    <c:if test="${pageInfo.hasNextPage }">
                        <li><a href="${pageContext.request.contextPath}/wiki/wklist?pn=${pageInfo.pageNum+1 }"
                            aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                        </a></li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}/wiki/wklist?pn=${pageInfo.pages}">末页</a></li>
                </ul>
                </nav>
            </div>
        </div>
	</div>
</div>


</html>