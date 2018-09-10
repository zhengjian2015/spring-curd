var emps = {
	//封装秒杀相关ajax的url
	URL : {
		depts : function() {
			return "/ssm-curd/depts";
		},
		emps : function() {
			return "/ssm-curd/emps";
		},
		emp : function(id) {
			return "/ssm-curd/emp/"+id;
		},
		edit:function(id) {
			return "/ssm-curd/emp2/"+id;
		}
	},
	//显示检验的信息
	show_validate_msg : function(ele, status, msg) {
		$(ele).parent().removeClass("has-success has-error");
		$(ele).next("span").text("");
		if ("success" == status) {
			$(ele).parent().addClass("has-success");
			$(ele).next("span").text(msg);
		} else if ("error" == status) {
			$(ele).parent().addClass("has-error");
			$(ele).next("span").text(msg);
		}
	},
	validate_add_form : function() {
		var empName = $("#empName_add_input").val();
		var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		if (!regName.test(empName)) {
			emps.show_validate_msg("#empName_add_input", "error",
					"请输入2-5中文或者6-16的英文");
			return false;
		} else {
			emps.show_validate_msg("#empName_add_input", "success", "");
		}
		var email = $("#email_add_input").val();
		var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		if (!regEmail.test(email)) {
			emps.show_validate_msg("#email_add_input", "error", "邮箱格式不正确");
			return false;
		} else {
			emps.show_validate_msg("#email_add_input", "success", "");
		}
		return true;
	},
	//编辑回写
	getDepts : function(ele) {
		$(ele).empty();
		$.get(emps.URL.depts(), {}, function(result) {
			$.each(result, function() {
				var optionEle = $("<option></option>").append(this.deptName)
						.attr("value", this.deptId);
				optionEle.appendTo(ele);
			});
		});
	},
	getEmp : function(id) {
		$.get(emps.URL.emp(id), {}, function(result) {
                var empDate = result;
                $("#empName_update_static").val(empDate.empName);
                $("#email_update_input").val(empDate.email);
                //放在[]的选中
                $("#empUpdateModal input[name=gender]").val([empDate.gender]);
                $("#empUpdateModal select").val([empDate.dId]); 
            });
	},
	detail : {
		init : function() {
			//点击新增按钮调用模态框
			$("#emp_add_modal_btn").click(function() {
				//弹出模态框
				emps.getDepts("#dept_add_select");
				$("#empAddModal").modal({
					backdrop : "static"
				});
			});
			//点击保存员工按钮
			$("#emp_save_btn").click(function() {
				//1. 模态框表单数据提交给服务器进行保存
				//2. 数据格式正确性
				if (!emps.validate_add_form()) {
					return false;
				}
				//3. 用户存在,ajax-va
				if ($(this).attr("ajax-va") == "error") {
					return false;
				}
				var s = $("#empAddModal form").serialize();
				//4. ajax请求保存
				$.ajax({
					url : emps.URL.emps(),
					type : "POST",
					data : $("#empAddModal form").serialize(),
					success : function(result) {
						if (result.code == 200) {
							//保存成功
							//1. 关闭模态框
							$("#empAddModal").modal('hide');
							//2.来最后一页
							//to_page(result.data.pageInfo.pages);
						} else {
							alert(result.message);
						}
					}
				});
			});
		    $(document).on("click",".edit_btn",function(){
		        //信息回填
		    	emps.getDepts("#empUpdateModal select");
		    	emps.getEmp($(this).attr("edit-id"));
		        //员工id传递给模态框更新按钮
		        $("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
		        $("#empUpdateModal").modal({
		            backdrop:"static"
		        });
		    });
		    //点击删除按钮
		    $(document).on("click",".delete_btn",function(){
		        var empName = $(this).attr("del-name");
		        var empId   = $(this).attr("del-id");
		        if(confirm("确认删除["+empName+"]吗？")){
		            //确认后ajax
		            $.ajax({
		                url:emps.URL.emp(empId),
		                type:"DELETE",
		                success:function(result){
		                    alert(result.result);
		                    location.reload();
		                }
		            });
		        }
		    });
		    //点击更新，更新员工信息
		    $("#emp_update_btn").click(function () {
		        var ds = $("#form-update").serialize();
		        var id = $(this).attr("edit-id");
		        $.ajax({
		            url:emps.URL.edit(id),
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
		            console.log(del_ids);
		            if(confirm("确认删除["+empNames+"]吗?")){
		                $.ajax({
		                    url:emps.URL.emp(del_ids),
		                    type:"DELETE",
		                    success:function(result){
		                        alert(result.msg);
		                    }
		                })
		            }
		        }
		    });
		}
	}
};