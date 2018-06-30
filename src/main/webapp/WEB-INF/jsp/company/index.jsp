<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--第一步：引入Javascript / CSS （CDN）-->


<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<!-- ajax submit -->
<script type="text/javascript" charset="utf8" src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<title>2017中国软件业务收入百强公司</title>
<body>
<br/>
<!--第二步：添加如下 HTML 代码-->
<form id="formImport" enctype="multipart/form-data">    
    <input type="file" id="uploadFile" name="uploadFile"/>    
	<button type="button"  onclick="javascript:submitImport();">确定</button>
</from>   
<table id="example"  class="display" style="width:100%">
        <thead>
            <tr>
                <th>公司名称</th>
                <th>公司城市</th>
                <th>2017年收入(万元)</th>
                <th>公司图标</th>
                <th>排名</th>
            </tr>
        </thead>
    </table>
 
 
 <script>

<!--第三步：初始化Datatables-->
$(document).ready( function () {
	 $('#example').DataTable( {
	        "ajax": "${pageContext.request.contextPath}/companys"
	 } );
} );


function submitImport() {
	    var epath = jQuery('#uploadFile').val();
        if(epath==""){    
            alert( '导入文件不能为空!');    
            return;    
        }    
            
        /*if(epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()=="xlsx"){    
            _alert( '03以上版本Excel导入暂不支持!');    
            return;    
        }    */
        if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()!="xls" && epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()!="xlsx") {    
            alert( '导入文件类型必须为excel!');    
            return;    
        }
    
        jQuery("#formImport").ajaxSubmit({     
            type: "POST",
            url: "${pageContext.request.contextPath}/improtExcel",
            async:false,
            success: function (data1) {
                console.log(data1);
            	if(data1.code == 200) {
                alert("操作成功");
                window.location.reload();
               } else {
                alert(data1.msg);
               }
            },
        });
}

</script>
</body>
</html>