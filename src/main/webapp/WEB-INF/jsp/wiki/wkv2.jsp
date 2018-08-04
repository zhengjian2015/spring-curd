<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${artldata.artTitle}</title>
<script src="${pageContext.request.contextPath}/lib/js/jquery-3.3.1.min.js"></script>
<link href="${pageContext.request.contextPath}/lib/wiki/plugins/bootstrap/css/bootstrap.min.dong.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/css/style-metro.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/lib/wiki/css/style.dong.min.css" rel="stylesheet" type="text/css"/>
<link href='${pageContext.request.contextPath}/lib/wiki/css/pages/blog.css'  rel="stylesheet" type="text/css"/>

<link href='${pageContext.request.contextPath}/lib/wiki/static/css/style.css'  rel="stylesheet" type="text/css"/>
<link href='${pageContext.request.contextPath}/lib/wiki/static/css/editormd.preview.min.css'  rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/lib/wiki/static/lib/marked.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/wiki/static/lib/prettify.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/wiki/static/lib/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/wiki/static/lib/underscore.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/wiki/static/lib/sequence-diagram.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/wiki/static/lib/flowchart.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/wiki/static/lib/jquery.flowchart.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/wiki/static/editormd.min.js"></script>
<style>
    .editormd-html-preview {
        width: 100%;
        margin: 0 auto;
    }
    ul, ol {
        list-style: disc;
    }
    li {
        list-style: disc;
    }
    blockquote p {
        font-size: 14px;
        line-height: 1.6
    }
    .side-catalog {
        visibility: visible;
        z-index: 1;
        right: 2px;
        bottom: 39px;
        width: 320px;
        height: 456px;
        position: fixed;
        font-size: 14px;
        font-family: 宋体;
        line-height: 19px;
        filter:alpha(Opacity=80);-moz-opacity:0.8;opacity: 0.8;
    }
    .catalog-scroller {
        position: absolute;
        top: 37px;
        left: 0;
        width: 210px;
        height: 330px;
        overflow: hidden
    }
    #custom-toc-container {
        border: 1px solid #ddd;
        width: 100%;
        height: 100%;
        margin: 0 auto 5px;
        overflow: auto;
        padding: 0px;
    }
    #custom-toc-container > .markdown-toc {
        padding: 10px;
    }
</style>
</head>
<body>
<br>
<div class="side-catalog" id="catalog">
    <div class="markdown-body editormd-preview-container catalog-scroller" id="custom-toc-container"></div>
    <div style="float:right;"><a href="javascript:showOrHideCatalog();" class="btn" style="text-decoration: none;" id="btnSwitch">隐藏目录</a></div>
</div>

<div class="container-fluid" style="text-align: left">
    <div class="row-fluid">
        <div class="span12 blog-page">
            <div class="row-fluid">
                <div class="span9 article-block">
                    <div class="row-fluid">
                        <div class="span12 blog-article">
                            <h1>${artldata.artTitle}
                                <small style="font-size: 14px"><a href="${pageContext.request.contextPath}/wiki/wkv1/${artldata.artId}">只看正文</a></small>
                            </h1>
                            <input type="hidden" value="${artldata.artId}" id="arid"/>
                            <div class="blog-img blog-tag-data">
                                <div class="span6">
                                    <ul class="unstyled inline blog-tags">
                                        <li>
                                            <i class="icon-tags"></i>
                                            <a href="${pageContext.request.contextPath}/wiki/wklist/tag/${artldata.artKeywords}">${artldata.artKeywords}</a>
                                        </li>
                                    </ul>
                                    <ul class="unstyled inline blog-tags">
                                        <li><i class="icon-eye-open"></i> ${artldata.mathchTimes}
                                        </li>
                                    </ul>
                                </div>
                                <div class="span6 blog-tag-data-inner">
                                    <ul class="unstyled inline">
                                        
                                        <li><a href="${pageContext.request.contextPath}/wiki/wkedit/${artldata.artId}"><i class="icon-edit"></i> 编辑</a></li>
                                        <li><i class="icon-user"></i>${artldata.createfullName}<BR>
                                            <i class="icon-user-md"></i>${artldata.updatefullName}
                                        </li>
                                        <li><i class="icon-calendar"></i> ${artldata.createTime}<BR>
                                            <i class="icon-calendar"></i> ${artldata.createTime}
                                        </li>
                                    </ul>
                                </div>
                                <!-- 发现用input框放文章内容，格式就没了 所以改用li -->
                                <li style="display:none""  id="ART_CONTENT" name="ART_CONTENT" >${artldata.artContent}</li>
                            </div>
                        </div>
                    </div>
                    <div id="test-editormd-view">
                       <textarea style="display:none;" name="test-editormd-markdown-doc"></textarea>
                    </div>
                </div>
                <%@ include file="wkside.jsp"%>
            </div>
        </div>
    </div>
</div>                                  
</body>
</html>
<script type="text/javascript">
var testEditormdView;

$(function(){
	console.log(jQuery('#ART_CONTENT').html());
	testEditormdView = editormd.markdownToHTML("test-editormd-view", {
        markdown        : jQuery('#ART_CONTENT').html(),
        htmlDecode      : "style,script,iframe",  // you can filter tags decode
        toc             : true,
        emoji           : true,
        taskList        : true,
        tex             : true,              // 默认不解析
        flowChart       : true,         // 默认不解析
        sequenceDiagram : false,  // 默认不解析
        atLink    : false,    // disable @link
        emailLink : false,    // disable email address auto link
        tocContainer  : "#custom-toc-container",
        tocDropdown   : false
    });
})

function showOrHideCatalog() {
        if (jQuery('#catalog').css("height")=='456px') {
            jQuery('#catalog').css("height",'0px');
            jQuery('#btnSwitch').text('显示目录')
        } else {
            jQuery('#catalog').css("height",'456px')
            jQuery('#btnSwitch').text('隐藏目录')
        }
    }
</script>