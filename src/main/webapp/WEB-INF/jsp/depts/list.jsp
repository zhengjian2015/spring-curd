<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${pageContext.request.contextPath}/lib/js/jquery-3.3.1.min.js"></script>
<link
	href="${pageContext.request.contextPath}/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script>
$(function() {// 初始化内容
	alert(12);
});  
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门列表</title>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>序号</th>
						<th>部门</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${emptList}" var="dep">
						<tr>
							<th>${emptList.deptId }</th>
							<th>${emptList.deptName }</th>
							<th>${emptList.gender=="M"?"男":"女" }</th>
							<th>
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									删除
								</button>
							</th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

</body>
</html>