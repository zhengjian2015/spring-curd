<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--begin span3-->
<div class="span3 blog-sidebar">
    <h3>功能</h3>
    <div>
        <a class="btn" href="${pageContext.request.contextPath}/wiki/wklist"><i class="icon-list-ul"></i> 文章列表</a>
        <a class="btn" href="${pageContext.request.contextPath}/wiki/wkedit"><i class="icon-plus"></i> 新建文章</a>
        <a class="btn" href="${pageContext.request.contextPath}/f/v/objlist?clsid=e65f8985d221442dab43370f722affc0" title="管理图片"><i class="icon-instagram"></i></a>
    </div>
    <h3>搜索</h3>
    <div class="">
        <form action="${pageContext.request.contextPath}/wiki/wklist" method="GET">
        	<!-- autocomplete="off"禁止历史纪录 -->
            <input class="m-wrap" type="text" style="box-sizing:content-box;height: 23px" id="qry" name="qry" value="" autocomplete="off">
            <input type="hidden" id="tag" name="tag" value="$param.get('tag','')">
            <button type="submit" class="btn" style="height: 30px;padding-bottom: 5px;padding-left: 12px;padding-right: 12px;padding-top: 5px;">
                <i class="icon-search"></i>
            </button>
        </form>
    </div>
    <h3>热门文章</h3>
       <c:forEach items="${hotart}" var="h">
         <div class="blog-twitter-block">
               <a href="${pageContext.request.contextPath}/wiki/wkv2/${h.artId}">${h.artTitle}</a>
               <span>${h.updateTime}</span>
               <i class="icon-file blog-twiiter-icon"></i>
         </div>
       </c:forEach>
</div>