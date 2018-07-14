<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test jquery</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/js/jquery.metadata.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/js//localization/messages_zh.js"></script>
<style type="text/css">
    *{font-family: verdana: font-size:96%;}
    label {width: 10em;float:left;}
    label.error {float: none;color:red;padding-left: .5em;vertical-align: top;}
    p{clear: both;}
    .submit {margin-left:12em;}
    em{font-weight: bold;padding-right: lem;vertical-align: top;}
</style>
<script type="text/javascript">
$().ready(function() {
    $("#commentForm").validate({
    	rules:{
    		username:{
    			required:true,
    			minlength:2
    		},
    		email:{
    			required:true,
    			email:true,
    		},
    		url:"url",
    		comment:"required"
    	}
    });
});
</script>
</head>
<body>
    <form class="cmxform" id="commentForm" method="get" action="#">
        <fieldset>
            <legend>一个简单的验证带验证提示的评论例子</legend>
            <p>
                <label for="cusername">姓名</label><em>*</em>
                <input id="cusername" name="username" size="25"  />
            </p>
            <p>
                <label for="email">电子邮箱</label><em>*</em>
                <input id="email" name="email" size="25"/>
            </p>
            <p>
                <label for="curl">网址</label><em>*</em>
                <input id="curl" name="curl" size="25" value=""/>
            </p>
            <p>
                <label for="comment">你的评论</label><em>*</em>
                <textarea id="comment" name="comment" cols="22" ></textarea>
            </p>
            <p>
                <input class="submit" type="submit" value="提交"/>
            </p>
        </fieldset>
    </form>
</body>
</html>