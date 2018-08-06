<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>查看文章</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/wiki/static/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/wiki/static/css/editormd.preview.min.css" />
       <style>
           .editormd-html-preview {
               width: 100%;
               margin: 0 auto;
           }
           .side-catalog {
               visibility: visible;
               right: 2px;
               bottom: 28px;
               width: 320px;
               height: 80%;
               position: fixed;
               font-size: 14px;
               font-family: 宋体;
               line-height: 19px;
               filter:alpha(Opacity=80);-moz-opacity:0.8;opacity: 0.8;
           }
           .catalog-scroller {
               position: absolute;
               top: 26px;
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
       <div id="layout" style="margin:12px;">
           <h1 id="showitem_title"></h1>
           <div id="test-editormd-view">
              <textarea style="display:none;" name="test-editormd-markdown-doc"></textarea>
           </div>
       </div>
       <div class="side-catalog" id="catalog">
           <input type="hidden" value="${artldata}" id="arid"/>
           <input type="hidden" value="${pageContext.request.contextPath}" id="apath"/>
           <div class="markdown-body editormd-preview-container catalog-scroller" id="custom-toc-container"></div>
           <div style="float:right;"><a href="javascript:showOrHideCatalog();" class="btn" style="text-decoration: none;" id="btnSwitch">隐藏目录</a></div>
       </div>

       <script src="${pageContext.request.contextPath}/lib/js/jquery-3.3.1.min.js"></script>
       <script src="${pageContext.request.contextPath}/lib/wiki/static/lib/marked.min.js"></script>
       <script src="${pageContext.request.contextPath}/lib/wiki/static/lib/prettify.min.js"></script>
       <script src="${pageContext.request.contextPath}/lib/wiki/static/lib/raphael.min.js"></script>
       <script src="${pageContext.request.contextPath}/lib/wiki/static/lib/underscore.min.js"></script>
       <script src="${pageContext.request.contextPath}/lib/wiki/static/lib/sequence-diagram.min.js"></script>
       <script src="${pageContext.request.contextPath}/lib/wiki/static/lib/flowchart.min.js"></script>
       <script src="${pageContext.request.contextPath}/lib/wiki/static/lib/jquery.flowchart.min.js"></script>
       <script src="${pageContext.request.contextPath}/lib/wiki/static/editormd.min.js"></script>
       <script type="text/javascript">
           var testEditormdView;

           jQuery(function() {
        	   var arid = $("#arid").val();
        	   var apath = $("#apath").val();
               jQuery.get(apath+"/wiki/art/"+arid, function(jsondata) {
            	   
                   console.log(jsondata);
                   var markdoc = '';
                   if (jsondata.ERROR) {
                       alert(jsondata.ERROR);
                   } else {
                       markdoc = jsondata.artContent;
                       if (jsondata.artTitle) {
                           document.title = jsondata.artTitle;
                           jQuery('#showitem_title').text(jsondata.artTitle);
                       }
                   }
                   testEditormdView = editormd.markdownToHTML("test-editormd-view", {
                       markdown        : markdoc,
                       // htmlDecode      : true,       // 开启HTML标签解析，为了安全性，默认不开启
                       htmlDecode      : "style,script,iframe",  // you can filter tags decode
                       toc             : true,
                       // tocm            : true,    // Using [TOCM]
                       // gfm             : false,
                       // tocDropdown     : true,
                       emoji           : true,
                       taskList        : true,
                       tex             : true,              // 默认不解析
                       flowChart       : true,         // 默认不解析
                       sequenceDiagram : false,  // 默认不解析
                       atLink    : false,    // disable @link
                       emailLink : false,    // disable email address auto link
                       tocContainer  : "#custom-toc-container",
                       tocDropdown   : false,
                   });

                   //console.log("返回一个jQuery实例 =>", testEditormdView);

                   // 获取Markdown源码
                   // console.log(testEditormdView.getMarkdown());

                   //alert(testEditormdView.getMarkdown());
               });
           });
           function showOrHideCatalog() {
               if (jQuery('#catalog').css("height")>'0px') {
                   jQuery('#catalog').css("height",'0px');
                   jQuery('#btnSwitch').text('显示目录')
               } else {
                   jQuery('#catalog').css("height",'80%')
                   jQuery('#btnSwitch').text('隐藏目录')
               }
           }
       </script>
   </body>
</html>