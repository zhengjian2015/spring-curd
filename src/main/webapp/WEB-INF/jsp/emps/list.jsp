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
    <style>
    .pageMessage{
        width: 50%;
        height: 70px;
        line-height: 70px;
    }
    </style>
    <script>
$(function() {// 初始化内容
	//检查当前是不是全选装填
    function  checkAllOrNot(){
        var flag = ($(".check_item:checked").length == $(".check_item").length);
        $("#check_all").prop("checked",flag);
    }
    //点击新增按钮调用模态框
    $("#emp_add_modal_btn").click(function() {
        //弹出模态框
        getDepts("#dept_add_select");
        $("#empAddModal").modal({
            backdrop:"static"
        });
    });
    //查出部门信息
    function getDepts(ele){
        $(ele).empty();
        $.ajax({
            url:"depts",
            type:"GET",
            success:function(result) {
                $.each(result, function () { 
                    console.log(result);
                    //console.log(result.data);
                    var optionEle = $("<option></option>").append(this.deptName).attr("value", this.deptId);
                    optionEle.appendTo(ele);
                });
            }
        })
    }
    //校验表单数据
    function validate_add_form(){
        var empName = $("#empName_add_input").val();
        var regName =  /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
        if(!regName.test(empName)){
            show_validate_msg("#empName_add_input","error","请输入2-5中文或者6-16的英文");
            return false;
        }else {
            show_validate_msg("#empName_add_input","success","");
        }
        var email = $("#email_add_input").val();
        var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if(!regEmail.test(email)){
            show_validate_msg("#email_add_input","error","邮箱格式不正确");
            return false;
        }else{
            show_validate_msg("#email_add_input","success","");
        }
        return true;
    }

    //显示检验的信息
    function show_validate_msg(ele,status,msg){
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");
        if("success" == status){
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        }else  if("error" == status){
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }
    //点击保存员工按钮
    $("#emp_save_btn").click(function(){
        //1. 模态框表单数据提交给服务器进行保存
        //2. 数据格式正确性
        if(!validate_add_form()){
            return false;
        }
        //3. 用户存在,ajax-va
        if($(this).attr("ajax-va")=="error"){
            return false;
        }
        var s = $("#empAddModal form").serialize();
        console.log(s);
        //4. ajax请求保存
        $.ajax({
            url:"emps",
            type:"POST",
            data:$("#empAddModal form").serialize(),
            success: function (result) {
                if(result.code == 200){
                    //保存成功
                    //1. 关闭模态框
                    $("#empAddModal").modal('hide');
                    //2.来最后一页
                    //to_page(result.data.pageInfo.pages);
                }else {
                    alert(result.message);
                }

            }
        });
    });
    
    
    $(document).on("click",".edit_btn",function(){
        //信息回填
        getDepts("#empUpdateModal select");
        getEmp($(this).attr("edit-id"));
        //员工id传递给模态框更新按钮
        $("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
        $("#empUpdateModal").modal({
            backdrop:"static"
        });
    })
    //查询员工
    function getEmp(id) {
        $.ajax({
            url:"emp/"+id,
            type:"GET",
            success:function(result){
                var empDate = result;
                $("#empName_update_static").val(empDate.empName);
                $("#email_update_input").val(empDate.email);
                //放在[]的选中
                $("#empUpdateModal input[name=gender]").val([empDate.gender]);
                $("#empUpdateModal select").val([empDate.dId]); 
            }
        })
    }
    
    //点击删除按钮
    $(document).on("click",".delete_btn",function(){
        var empName = $(this).attr("del-name");
        var empId   = $(this).attr("del-id");
        if(confirm("确认删除["+empName+"]吗？")){
            //确认后ajax
            $.ajax({
                url:"emp/"+empId,
                type:"DELETE",
                success:function(result){
                	console.log(result)
                    alert(result.result);
                    location.reload();
                }
            });
        }
    });
    
  //点击更新，更新员工信息
    $("#emp_update_btn").click(function () {
        //校验邮箱信息
   /*      var email = $("#email_update_input").val();
        var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if(!regEmail.test(email)){
            show_validate_msg("#email_update_input","error","邮箱格式不正确");
            return false;
        }else{
            show_validate_msg("#email_update_input","success","");
        } */
        //发送ajax请求，更新数据
        var ds = $("#form-update").serialize();
        $.ajax({
            url:"emp2/"+$(this).attr("edit-id"),
            type:"POST",
            data:$("#form-update").serialize(),
            success: function (result) {
            	if(result.code == 200){
                    //保存成功
                    //1. 关闭模态框
                    alert("操作成功");
                    $("#empUpdateModal").modal('hide');
                    window.location.reload();
                    //2.来最后一页
                    //to_page(result.data.pageInfo.pages);
                }else {
                    alert(result.message);
                }
            }
        })
    });
    
    //全选全不选
    $("#check_all").click(function(){
        //attr获取自定义属性
        //prop修改和读取dom原生的属性
        $(".check_item").prop("checked",$(this).prop("checked"));
    });
    //每个checkitem单击事件
    $(document).on("click",".check_item",function(){
        //判断当前选择中的元素是否5个
        checkAllOrNot();
    });
    //批量删除
    $("#emp_delete_all_btn").click(function(){
        if($(".check_item:checked").length <= 0){
            alert("请勾选需要删除的人员信息!");
        }else {
            var empNames = "";
            var del_ids = "";
            $.each($(".check_item:checked"),function(){
                empNames += $(this).parents("tr").find("td:eq(2)").text()+",";
                del_ids  +=  $(this).parents("tr").find("td:eq(1)").text()+"-";
            });
            //最后的逗号,"-"处理
            empNames.substring(0,empNames.length-1);
            del_ids.substring(0,del_ids.length-1);
            if(confirm("确认删除["+empNames+"]吗?")){
                $.ajax({
                    url:"emp/"+del_ids,
                    type:"DELETE",
                    success:function(result){
                        alert(result.msg);
                        to_page(currentPage);
                    }
                })
            }
        }
    });
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
                <h1>员工信息</h1>
            </div>
        </div>
        <!-- 按钮 -->
        <div class="row">
              <a class="btn btn-primary" href="export">导出数据为excel</a>
            <div class="col-md-4 col-md-offset-8">
                <button class="btn btn-sm btn-info pull-right" id="emp_add_modal_btn">新增</button>
                <button class="btn btn-sm btn-danger pull-right" id="emp_delete_all_btn">删除</button>
            </div>
        </div>
        <!-- 显示表格数据 -->
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover">
                    <tr>
                        <th><input type='checkbox' class='check_item'></th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>email</th>
                        <th>部门</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${pageInfo.list}" var="emp">
                        <tr>
                            <th><input type='checkbox' class='check_item'></th>
                            <th>${emp.empName }</th>
                            <th>${emp.gender=="M"?"男":"女" }</th>
                            <th>${emp.email }</th>
                            <th>${emp.department.deptName }</th>
                            <th>
                                <button class="btn btn-info btn-xs edit_btn" edit-id="${emp.empId}" >
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                </button>
                                <button class="btn btn-danger btn-xs delete_btn" del-id="${emp.empId}" del-name="${emp.empName}" >
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </button>
                            </th>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
<!-- 显示分页信息 -->
        <div class="row">
            <!--分页文字信息  -->
            <div class="col-md-6 pageMessage" id="page_info_area">当前 ${pageInfo.pageNum }页,总${pageInfo.pages }
                页,总 ${pageInfo.total } 条记录</div>
            <!-- 分页条信息 -->
            <div class="col-md-6" id="page_nav_area">
                <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="${pageContext.request.contextPath}/emps?pn=1">首页</a></li>
                    <c:if test="${pageInfo.hasPreviousPage }">
                        <li><a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pageNum-1}"
                            aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                        </a></li>
                    </c:if>


                    <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                        <c:if test="${page_Num == pageInfo.pageNum }">
                            <li class="active"><a href="#">${page_Num }</a></li>
                        </c:if>
                        <c:if test="${page_Num != pageInfo.pageNum }">
                            <li><a href="${pageContext.request.contextPath}/emps?pn=${page_Num }">${page_Num }</a></li>
                        </c:if>

                    </c:forEach>
                    <c:if test="${pageInfo.hasNextPage }">
                        <li><a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pageNum+1 }"
                            aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                        </a></li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pages}">末页</a></li>
                </ul>
                </nav>
            </div>
        </div>
    </div>
</body>
<!-- 修改模态框 -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">员工修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="form-update">
                    <div class="form-group">
                        <label for="empName_add_input" class="col-sm-2 control-label">姓 名:</label>
                        <div class="col-sm-10">
                            <input class="form-control-static" name="empName" id="empName_update_static"></input>
                            <span  class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">邮 箱:</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_update_input" placeholder="请输入邮箱">
                            <span  class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">性 别:</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_update_input" value="M" checked> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">部门名:</label>
                        <div class="col-sm-3">
                            <!-- 部门动态添加 -->
                            <select class="form-control" name="dId" id="dept_update_select">
                            </select>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 增加模态框 -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_add_input" class="col-sm-2 control-label">姓 名:</label>
                        <div class="col-sm-10">
                            <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="输入姓名">
                            <span  class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">邮 箱:</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_add_input" placeholder="请输入邮箱">
                            <span  class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">性 别:</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_add_input" value="M" checked> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">部门名:</label>
                        <div class="col-sm-3">
                            <!-- 部门动态添加 -->
                            <select class="form-control" name="dId" id="dept_add_select">
                            </select>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>
</html>