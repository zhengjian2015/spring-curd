<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>权限人</title>
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
</head>
<body>
<div id="div_modal_canmodiusers" tabindex="-1" role="dialog" aria-labelledby="myModal_canmodiusers" aria-hidden="true">
        
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h3 id="myModal_canmodiusers">设置文章可修改人</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" id="form_canmodiusers">
                <div class="row-fluid">
                    <div class="control-group">
                        <label class="control-label-small">可修改人</label>
                        <div class="controls-large">
                            <input type="hidden" id="ART_ID" name="ART_ID" value="${artId}" />
                            <input type="text" class="span11 m-wrap select2" id="canmodiusers" name="canmodiusers" />
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn blue" data-dismiss="modal" onclick="javascript:mdlsel_savecanmodiusers();">确定</button>
            <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
        </div>
    </div>
</body>
<script>
$(function(){
	var arrayIdValue = [];
	jQuery.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/wiki/modiusers",
        async: false,
        success: function(json) {
            console.log(json);
            for (var k=0; k<json.length; k++) {
            	arrayIdValue.push({id: json[k].userCode, text: json[k].userCode+'-'+json[k].userNickname});
                }
            //jQuery('#canmodiusers').html(str);
        }
    });
	jQuery("#canmodiusers").select2({tags: arrayIdValue});
});

function mdlsel_savecanmodiusers() {
	if (jQuery("#canmodiusers").val() =="" || !jQuery("#canmodiusers").val()) {
		alert("请输入权限人！");
		return false;
	}
	jQuery.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/wiki/modiusers",
        data: { "artId": jQuery('#ART_ID').val(), "canmodiUsers":jQuery("#canmodiusers").val() },
        async: false,
        success: function(data) {
        	if (data.code=200) {
                alert(data.msg);
                window.location.href="${pageContext.request.contextPath}/wiki/wklist";
            } 
        	else if (data.code!=200) {
                alert(data.msg);
            }
        }
    });
	
}
</script>
</html>