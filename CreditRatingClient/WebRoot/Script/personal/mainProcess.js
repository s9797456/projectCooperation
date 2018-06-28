$(function() {
	/* 初始化加载操作 */
	refresh();
	/*点击 更多 时进行的操作
	$(".resume-edit-area-more").click(function(){
		if ($("[edit-statue='true']").size() != 0) {
			popup("你正处于编辑状态");
			return;
		}
		$(this).closest(".resume-content").css("display","none");
		$(".info-itemswrap2").css("display","block");
	});*/
	/** 后续增加功能 需添加 end */
	$(".browse-head").on("click",".resume-edit",function() {
				var modelRoot = $(this).closest(".resume-content");
				if ($(this).text() != "取消") {
					if ($("[edit-statue='true']").size() != 0) {
						popup("你正处于编辑状态");
						return;
					}
				}
				/* 更改 模块状态 */
				if (modelRoot.attr("id") == "PerBaseInfo"||modelRoot.attr("id") == "CreditIndex") {
					$(this).text($(this).text() == "编辑" ? "取消" : "编辑");
				} else {
					$(this).text($(this).text() == "添加" ? "取消" : "添加");
				}
				/* 切换模块状态 */
				var shows = $(this).closest('.browse-head').siblings(".resume-browse-area");
				if ($(this).text() == "编辑" || $(this).text() == "添加") {
					if (shows.siblings(".addmorebox").size() != 0 && shows.children().size() == 0) {
						shows.siblings(".addmorebox").css("display", "block");
						shows.css("display", "block").parent().attr("edit-statue", false);
					} else {
						shows.css("display", "block").parent().attr("edit-statue", false);
					}
					if (modelRoot.attr("id") == "UploadFile") {
						/* 执行 编辑界面 */
						$(".form-item").css("display","block");
						$("#uploader").attr("style","position: absolute; opacity: 0;");
						$("#UploadFile").find(".resume-edit").text("取消").css("display","none");
						$("#UploadFile").find("#uploadFileList").css("display","none");
						refresh(modelRoot);
					}
					shows.siblings(".resume-edit-area").css("display", "none").empty();
				} else if ($(this).text() == "取消") {
					shows.siblings(".addmorebox").css("display", "none");
					shows.css("display", "none").parent(".resume-content").attr("edit-statue", true).attr("data-statue",false);
					shows.siblings(".resume-edit-area").css("display", "block").empty();
					
					if (modelRoot.attr("id") == "PerBaseInfo") {
						/* 执行 编辑界面 */
						listPerBaseInfo("edit");
					} else if (modelRoot.attr("id") == "CreditIndex") {
						/* 执行 编辑界面 */
						listCreditIndex("edit");
					} else {
						// 像model 设置属性 和 添加按钮 edit-statue：编辑状态 true是编辑状态   data-statue from表单提交验证
						modelRoot.children(".resume-edit-area").append(eval("new"+modelRoot.attr("id")));
					}
					/* 初始化验证 */
					verification();
					/* 初始化隐藏 ul多选标签 */
					$(".divselect > ul").css("display", "none");
				}else{
					popup("页面错误 ，请刷新页面");
				}

			});

	/* 验证非空 */
	$(".resume-edit-area").on("blur",".validate",function() {
		wrong(false,$(this));
		if ($(this).val().trim() == "") {
			wrong(true,$(this),"该项内容不能为空");
		} else {
			$(this).attr("data-test", true);
			/* 验证字符 */
			if ($(this).attr("class").indexOf("validateCha") != -1) {
				wrong(false,$(this));
				var validateCha = /^[a-zA-Z0-9_]+$/;
				if (!validateCha.test($(this).val().trim())) {
					wrong(true,$(this),"该项内容只能为字母,数字,_");
				} else {
					$(this).attr("data-test", true);
				}
			}
			/* 验证数字 */
			if ($(this).attr("class").indexOf("validateNum") != -1) {
				wrong(false,$(this),"只能输入数字或者小数");
				var validateCha = /(^[1-9]\d*$)|(^\d+\.\d+$)/;
				if (!validateCha.test($(this).val().trim())) {
					wrong(true,$(this),"只能输入数字或者小数");
				} else {
					$(this).attr("data-test", true);
				}
			}
			/* 验证网址 */
			if ($(this).attr("class").indexOf("validateWeb") != -1) {
				wrong(false,$(this),"请输入有效的地址如www.****.com");
				var validateCha = /^(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i;
				if (!validateCha.test($(this).val().trim())) {
					wrong(true,$(this),"请输入有效的地址如www.****.com");
				} else {
					$(this).attr("data-test", true);
				}
			}
			/* 验证联系方式 */
			if ($(this).attr("class").indexOf("validateTel") != -1) {
				wrong(false,$(this));
				var validateCha = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8}|\d{3})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
				if (!validateCha.test($(this).val().trim())) {
					wrong(true,$(this),"请输入正确,如:0920-29392929、132****8901");
				} else {
					$(this).attr("data-test", true);
				}
			}
			/* 验证Email */
			if ($(this).attr("class").indexOf("validateEmail") != -1) {
				wrong(false,$(this));
				var validateCha = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
				if (!validateCha.test($(this).val().trim())) {
					wrong(true,$(this),"请输入有效的Email");
				} else {
					$(this).attr("data-test", true);
				}
			}
			/* 验证中文 */
			if ($(this).attr("class").indexOf("validateChinese") != -1) {
				wrong(false,$(this));
				var validateCha = /^[\u4e00-\u9fa5 (（）)]+$/;
				if (!validateCha.test($(this).val().trim())) {
					wrong(true,$(this),"该项内容只能为中文");
				} else {
					$(this).attr("data-test", true);
				}
			}
			/* 验证中文及字符 */
			if ($(this).attr("class").indexOf("validateCharacter") != -1) {
				wrong(false,$(this));
				var validateCha = /^[a-zA-Z\u4e00-\u9fa5]+$/;
				if (!validateCha.test($(this).val().trim())) {
					wrong(true,$(this),"该项内容只能为中文及英文字符");
				} else {
					$(this).attr("data-test", true);
				}
			}
			/* 验证时间 */
			if ($(this).attr("class").indexOf("validateDate") != -1) {
				wrong(false,$(this));
				var validateCha = /([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/i;
				if (!validateCha.test($(this).val().trim())) {
					wrong(true,$(this),"请输入正确的时间,不足两位需补0  ,如1991-01-01");
				} else {
					$(this).attr("data-test", true);
				}
			}
			
		}
		//验证 显示效果
		function wrong(correct,obj,str){
			if(correct){
				$(obj).css("border", "1px #ff6a0e solid").attr("data-test", false).parent().append("<label class = 'notice'>"+str+"</span>");
			}else{
				$(obj).css("border", "1px #E3E3E3 solid").siblings("label").remove();
			}
		}
	});
	
	//菜单缩进
	$("#narrow").click(function(){
	if($(this).text() == ">>"){
		$(".resume-Rarea").animate({right:'-330px'});
		$("#narrow").animate({right:'0px'}).html("<a  href = '###' class = 'open' >&lt;&lt;</a>");
	}else{
		$(".resume-Rarea").animate({right:'30px'});
		$("#narrow").animate({right:'264px'}).html("<a  href = '###' class = 'indent' >&gt;&gt;</a>");
		}
	});

	/* 单选 radiobox */
	$(".resume-edit-area").on("click",".radioitem",function() {
		/* 点击改变事件 */
		$(this).attr("class", "radioitem radioitemact").siblings().attr("class", "radioitem ");
		$(this).parent().siblings("input").val($(this).text());
	});
	/* 单选 select */
	$(".resume-edit-area").on("click",".radio",function() {
		$(this).siblings("ul").css("display", "block").children("li").click(function() {
			$(this).parent().siblings("input").val($(this).children("a").text()).attr("data-test","true").css("border", "1px #E3E3E3 solid").siblings("label").remove();
			/* 关闭ul标签 */
			$(this).closest("ul").css("display", "none");
		});
		/* 单击其他地方 隐藏 ul */
		$(this).closest("form").click(function() {
			/* this 是 form */
			$(this).find("ul").css("display", "none");
		});
	});

	/* 多选 */
	$(".resume-edit-area").on("click",".multiselect",function() {
		var oldvalue = $(this).val();
		/* 初始选择后 加✔ */
		var list = $(this).siblings("ul").css("display", "block").children("li");
		list.each(function() {
			$(this).children("span").remove();
			var initial = $(this).children("a").text();
			if (oldvalue.indexOf(initial) != -1) {
				$(this).append("<span style='float: right;margin-right: 15px;'>✔</span>");
			}
		});
		/* 点击li标签 加✔ 并添加到input中 */
		$(this).siblings("ul").children("li").click(function() {
			var newvalue = $(this).children("a").text();
			/* 判断li标签 元素 是否在input 标签中存在 */
			if (oldvalue.indexOf(newvalue) != -1) {
				/* 存在后点击 */
				oldvalue = oldvalue.replace(newvalue + ",", "");
				$(this).children("span").remove();
				$(this).closest("ul").siblings(".multiselect").val(oldvalue);
			} else {
				/* 不存在的点击 */
				oldvalue = oldvalue+ $(this).children("a").text() + ",";
				$(this).closest("ul").siblings(".multiselect").val(oldvalue);
				$(this).append("<span style='float: right;margin-right: 15px;'>✔</span>");
			}
			/* 关闭ul标签 */
			$(this).closest("ul").css("display", "none");
		});

		/* 单击其他地方 隐藏 ul */
		$(this).closest("form").click(function() {
			/* this 是 form */
			$(this).find("ul").css("display", "none");
		});
	});

				
			/* 单机删除 */
			$(".resume-edit-area").on("click",".delbt",function() {
				var uuid = $(this).closest("form").children("input[name = uuid]").val();
				/* 删除操作 */
				var modelRoot = $(this).closest(".resume-content");
				eval("delete"+modelRoot.attr("id")+"('"+uuid+"')");
				/* 展示界面更新 */
				refresh($(this).closest(".resume-content"));
				$(this).closest(".form").remove();
				$(this).closest(".resume-content").attr("edit-statue", false).find(".resume-edit").text("添加");
				$(this).closest(".resume-edit-area").css("display", "none").siblings(".resume-browse-area").css("display", "block");
			});

			
			/* 单击编辑按钮 */
			$(".resume-browse-area").on("click",".edit",function() {
				verification();
				if ($("[edit-statue='true']").size() != 0) {
					popup("你正处于编辑状态");
					return;
				}
				var uuid = $(this).siblings("input").val();
				var modelRoot = $(this).closest(".resume-content");
				eval("list"+modelRoot.attr("id")+"('"+uuid+"')");
			});

			/* 单击保存按钮 */
			$(".resume-edit-area").on("click",".save",function() {
				verification();
				var checkSubmitFlg = true;
				var modelRoot = $(this).closest(".resume-content");
				if (checkSubmitFlg) {
					checkSubmitFlg = false;
					var incomplete = $(this).closest(".resume-edit-area ").find("[data-test = false]");
					if (incomplete.length == 0) {
						var params = $(this).closest("form").serialize();
						$.ajax({
							type : "post",
							url : _ctxPath + "/personalInfo/update"+ modelRoot.attr("id") + ".do",
							data : params,
							success : function(data) {
								if(data.status){
									popup("保存成功！");
									// 刷新页面
									refresh(modelRoot);
									// 保存成功后 更改块的状态
									modelRoot.attr("data-statue", true).attr("edit-statue", false);
									if (modelRoot.attr("id") == "PerBaseInfo"||modelRoot.attr("id") == "CreditIndex") {
										modelRoot.find(".resume-edit").text("编辑");
									} else {
										modelRoot.find(".resume-edit").text("添加");
									}
									// 切换 显示和 编辑 的 display状态
									modelRoot.children(".resume-browse-area").css("display", "block");
									modelRoot.children(".resume-edit-area").css("display", "none");
								}else{
									popup(data.msg);
								}
							},
							error : function() {
								popup("网络连接错误")
							}
						});
					}else {
						modelRoot.attr("data-statue", false);
						incomplete.css("border", "1px #ff6a0e solid").siblings("label").remove();
						popup("带 *的填入项为必填项");
					}
				} else {
					modelRoot.attr("data-statue", false);
					incomplete.siblings("label").remove();
					popup("请勿重复提交表单");
				}

			});

			/* 单击 添加**信息块 */
			$(".addmorebox").click(function() {
				/* 查看是否是编辑状态 */
				if ($("[edit-statue='true']").size() != 0) {
					popup("你正处于编辑状态");
					return;
				}
				var modelRoot = $(this).closest(".resume-content");
				/* 显示 隐藏的 取消 /添加 按钮 */
				$(this).css("display", "none");
				/*
				 * 像model 设置属性 和 添加按钮 edit-statue：编辑状态 true是编辑状态
				 * data-statuefrom表单提交验证
				 */
				modelRoot.attr("edit-statue", true).attr("data-statue",false).find(".resume-edit").text("取消");
				/* 添加 展示信息 */
				$(this).siblings(".resume-edit-area").css("display","block").empty();
				$(this).siblings(".resume-edit-area").append(eval("new"+modelRoot.attr("id")));
				/* 初始化验证表单数据 *放在后续增加功能后 */
				verification();
			});

				/*点击  申请报告*/
			$(".resume-Rarea").on("click",".nav-link",function(){
				//$(".resume-Rarea").find(".progress-text").children("span").text() == 100 && 
				if($("#PerBaseInfo").attr("data-statue")&& $("#CreditIndex").attr("data-statue")&& $("#Education").attr("data-statue")
						&& $("#Career").attr("data-statue")&& $("#UploadFile").attr("data-statue")){
					$.ajax({
						type : "post",
						url : _ctxPath + "/personalInfo/updatePerProcess.do",
						success : function(data) {
							popup(data.msg);
							refresh();
						}
					});
				}else{
					popup("必填信息项，未填写完整");
				}
			});

	/* 新增教育模块 */
	var newEducation = "<form><div class='edit_line'>"
			+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>学历：</div><div class='resume-editRight'><div class='divselect'>"
			+ "<input value='' name='education' readonly='readonly' placeholder='请选择学历' class='validate radio cite forminput1'  type='text' >"
			+ "<ul style='display: none;'><li><a href='javascript:;' >博士研究生及以上</a></li><li><a href='javascript:;' >硕士研究生</a></li><li><a href='javascript:;' >本科</a></li><li><a href='javascript:;' >专科</a></li><li><a href='javascript:;' >高中及以下</a></li></ul>"
			+ "</div></div>"
			+ "</div> <div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>起始时间：</div>"
			+ "<div class='resume-editRight' style = 'width:308px;'>" 
			+"<input name='starttime' id = 'starttimeNewEducation' value='' placeholder='请填写开始日期' style='width:38%;' class='validate forminput1 ml10' onfocus='maxdate(\"endtimeNewEducation\")' type='text'><span style='margin: 0 5px;'>至</span> " 
			+"<input name='endtime' id = 'endtimeNewEducation' value='' placeholder='请填写毕业日期' style='width:38%;' class='validate forminput1' onfocus='mindate(\"starttimeNewEducation\")' type='text'>" 
			+ "</div></div><div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>毕业院校：</div>"
			+ "<div class='resume-editRight'><input name='university' value='' placeholder='请填写毕业院校' class='validate forminput1 ml10' type='text'></div>"
			+ "</div> <div class='edit_line '>"
			+ "<div class='resume-editLeft'><span>*</span>专业：</div>"
			+ "<div class='resume-editRight'><input name='major' value='' placeholder='请填写专业' class='validate forminput1 ml10' type='text'></div>"
			+ "</div> <div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>毕业证书编号：</div>"
			+ "<div class='resume-editRight'><input name='diplomano' value='' placeholder='请填写毕业证书编号' class='validate forminput1 ml10' type='text'></div>"
			+ "</div><div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>";
	/* 新增工作经验模块 */
	var newCareer = "<form><div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>工作时间：</div>"
			+ "<div class='resume-editRight' style = 'width:308px;'><input name='starttime' id = 'starttimeNewCareer' value='' placeholder='请填写开始日期' style='width:38%;' class='validate forminput1 ml10' onfocus='maxdate(\"endtimeNewCareer\")' type='text'><span style='margin:0px 5px;'>至</span> " 
			+"<input name='endtime' value='' id = 'endtimeNewCareer' placeholder='请填写结束日期' style='width:38%;' class='validate forminput1' onfocus='mindate(\"starttimeNewCareer\")' type='text'></div>"
			+ "</div> <div class='edit_line '>"
			+ "<div class='resume-editLeft'><span>*</span>就职单位：</div>"
			+ "<div class='resume-editRight'><input name='inauguralunit' value='' placeholder='请填写就职单位' class='validate forminput1 ml10' type='text'></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>职业资质：</div><div class='resume-editRight'><div class='divselect'>"
			+ "<input value='' name='qualifications' readonly='readonly' placeholder='请选择职业资质' class='validate radio cite forminput1'  type='text' >"
			+ "<ul style='display: none;'><li><a href='javascript:;' >高级</a></li><li><a href='javascript:;' >中级</a></li><li><a href='javascript:;' >初级</a></li><li><a href='javascript:;' >无</a></li></ul>"
			+ "</div></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>单位性质：</div><div class='resume-editRight'><div class='divselect'>"
			+ "<input value='' name='unitproperties' readonly='readonly' placeholder='请选择单位性质' class='validate radio cite forminput1'  type='text' >"
			+ "<ul style='display: none;'><li><a href='javascript:;' >国家机关、事业单位、社会团队 </a></li><li><a href='javascript:;' >三资企业、股份制企业</a></li><li><a href='javascript:;' >民营企业、个人工商户</a></li><li><a href='javascript:;' >退休领退休金</a></li><li><a href='javascript:;' >其他</a></li></ul>"
			+ "</div></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>单位规模：</div><div class='resume-editRight'><div class='divselect'>"
			+ "<input value='' name='unitscale' readonly='readonly' placeholder='请选择单位规模' class='validate radio cite forminput1'  type='text' >"
			+ "<ul style='display: none;'><li><a href='javascript:;' >上市企业</a></li><li><a href='javascript:;' >大型企业</a></li><li><a href='javascript:;' >中型企业</a></li><li><a href='javascript:;' >小型企业</a></li><li><a href='javascript:;' >小微企业</a></li></ul>"
			+ "</div></div>"
			+ "</div> <div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>从事行业：</div>"
			+ "<div class='resume-editRight'><input name='industry' value='' placeholder='请填写从事行业' class='validate forminput1 ml10' type='text'></div>"
			+ "</div> <div class='edit_line '>"
			+ "<div class='resume-editLeft'><span>*</span>职务：</div>"
			+ "<div class='resume-editRight'><input name='post' value='' placeholder='请填写职务' class='validate forminput1 ml10' type='text'></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>工作年限：</div><div class='resume-editRight'><div class='divselect'>"
			+ "<input value='' name='workinglife' readonly='readonly' placeholder='请选择工作年限' class='validate radio cite forminput1'  type='text' >"
			+ "<ul style='display: none;'><li><a href='javascript:;' > 1年及以下</a></li><li><a href='javascript:;' > 2 ~ 3年</a></li><li><a href='javascript:;' > 4 ~ 5 年</a></li><li><a href='javascript:;' > 6 ~ 8年</a></li><li><a href='javascript:;' > 8 ~ 10年</a></li><li><a href='javascript:;' >10年以上</a></li></ul>"
			+ "</div></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>平均薪资：</div>"
			+ "<div class='resume-editRight'><input name='averagesalary' value='' placeholder='请填写平均薪资' class='validate forminput1 ml10' min = 0 type='number'></div>"
			+ "</div><div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>";
	
	var newTrain ="<form><div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>培训机构：</div>"
			+ "<div class='resume-editRight'><input name='trainorg' value='' placeholder='请填写培训机构' class='validate forminput1 ml10' type='text'></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>培训日期：</div>"
			+ "<div class='resume-editRight' style = 'width:308px;'><input name='starttime' id = 'starttimeNewTrain' value='' placeholder='请选择开始日期' style='width:38%;' class='validate forminput1 ml10' onfocus='maxdate(\"endtimeNewTrain\")' type='text'><span style='margin:0px 5px;'>至</span> " 
			+"<input name='endtime' value='' id = 'endtimeNewTrain' placeholder='请选择结束日期' style='width:38%;' class='validate forminput1 ' onfocus='mindate(\"starttimeNewTrain\")' type='text'></div>"
			+ "</div> <div class='edit_line '>"
			+ "<div class='resume-editLeft'><span>*</span>培训地址：</div>"
			+ "<div class='resume-editRight'><input name='trainaddress' value='' placeholder='请填写培训地址' class='validate forminput1 ml10' type='text'></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>证书编号：</div>"
			+ "<div class='resume-editRight'><input name='certificateno' value='' placeholder='请填写证书编号' class='validate forminput1 ml10' type='text'></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>培训内容：</div>"
			+ "<div class='resume-editRight'><textarea name='traincontent' value='' style= 'height:100px;width: 300px;' placeholder='请填写培训内容' class='validate forminput1 ml10' type='text'></textarea></div>"
			+ "</div>" 
			+ "<div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>";
	var newSkills ="<form><div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>技能名称：</div>"
			+ "<div class='resume-editRight'><input name='skillname' value='' placeholder='请填写技能名称' class='validate forminput1 ml10' type='text'></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>技能熟练度：</div><div class='resume-editRight'><div class='divselect'>"
			+ "<input value='' name='skillproficiency' readonly='readonly' placeholder='请选择技能熟练度' class='validate radio cite forminput1'  type='text' >"
			+ "<ul style='display: none;'><li><a href='javascript:;' >了解</a></li><li><a href='javascript:;' >熟悉</a></li><li><a href='javascript:;' >掌握</a></li><li><a href='javascript:;' >精通</a></li><li><a href='javascript:;' >专家</a></li></ul>"
			+ "</div></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft'>备注：</div>"
			+ "<div class='resume-editRight'><textarea name='remarks' value='' style= 'height:100px;width: 300px;' placeholder='选填' class='forminput1 ml10' type='text'></textarea></div>"
			+ "</div>" 
			+ "<div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>";
});

function getjson(){
	var data1 = [];
	
	return data1;
}

/* 设置 右侧 状态菜单 的值 */
function listProcessState() {
	$.ajax({
				type : "post",
				url : _ctxPath + "/personalInfo/listPerProcess.do",
				success : function(data) {
					var perProcess = data.perProcess;
					var number = 0;
					
					// 是否申请报告
					if (perProcess.applyreportstate == 1) {
						//头部右侧的 编辑/取消 按钮
						$(".headRarea").remove();
						//编辑界面
						$(".resume-edit-area").remove();
						//新增界面
						$(".addmorebox").remove();
						
						//编辑按钮
						$(".edit").remove();
						// 右侧菜单 申请报告 按钮
						$(".preview-resume").text("已申请报告").css("background-color","#fff").css("color","rgb(0, 170, 238) none repeat scroll 0% 0%").css("font-weight","bold").css("border","0px").attr("disabled","disabled");
						
						$("#uploader").remove();
					} else {
						$(".headRarea").attr("class", "headRarea");
						$("[data-statue = false]").attr("class","resume-content ");
						$(".preview-resume").text("申请报告");
					}
					
					if(perProcess.baseinfostate == 1){
						$("#PerBaseInfo").attr("data-statue", true);
					}else{
						$("#PerBaseInfo").attr("data-statue", false);
					}
					if(perProcess.pushmodelstate==1){
						$("#CreditIndex").attr("data-statue", true);
					}else{
						$("#CreditIndex").attr("data-statue", false);
					}
					if(perProcess.educationstate==1){
						$("#Education").attr("data-statue", true);
					}else{
						$("#Education").attr("data-statue", false);
					}
					if(perProcess.careerstate==1){
						$("#Career").attr("data-statue", true);
					}else{
						$("#Career").attr("data-statue", false);
					}
					if(perProcess.trainstate==1){
						$("#Train").attr("data-statue", true);
					}else{
						$("#Train").attr("data-statue", false);
					}
					if(perProcess.skillstate==1){
						$("#Skills").attr("data-statue", true);
					}else{
						$("#Skills").attr("data-statue", false);
					}
					if(perProcess.uploadfilestate==1){
						$("#UploadFile").attr("data-statue", true);
					}else{
						$("#UploadFile").attr("data-statue", false);
					}
					
					$("[data-statue = 'true']").each(function(){
						$("[href = '#"+$(this).attr("id")+"']").children("span:last").css("color", "#40b95e").text("已添加");
						if($(this).attr("id") == "PerBaseInfo"){
							number = number + 20;
						}else if($(this).attr("id") == "CreditIndex"){
							number = number + 30;
						}else{
							number = number + 10;
						}
					});
					$("[data-statue = 'false']").each(function(){
						$("[href = '#"+$(this).attr("id")+"']").children("span:last").css("color", "#999").text("未添加");
					});
				
					if(number >= 60 && perProcess.applyreportstate == 0){
						$(".preview-resume").attr("style","background-color:#00A0E9; color:#fff; border-radius: 5px;");
					}
					// 设置需要显示的分值
					$(".resume-Rarea").find(".progress-text").children("span").text(number);
					$(".progress-line").children("div").css("width",number+"%");
					
					//附加
					if(	$(".edit").length>0 && perProcess.applyreportstate == 1){
						refresh();
					}
				},
				error : function() {
					popup("网络连接失败，请稍后再试..");
				}
			});
}
/*行业 列表 end*/
function listCreditIndex(state) {
	$.ajax({
		type : "post",
		url : _ctxPath + "/personalInfo/listCreditIndex.do",
		success : function(data) {
			if (data.status) {
				var indexs = data.indexs;
				if(state == "edit"){
					var info = $("#CreditIndex").children(".resume-edit-area");
					info.append("<form id='form'></form>");
					var form="";
					for(var i in indexs){
						var options=indexs[i].options;
						if(indexs[i].write=="true"){
							form+="<div class='edit_line'>"
								    +"<div class='resume-editLeft'><span>*</span>"+indexs[i].name+"：</div>"
								    +"<div class='resume-editRight'><input value='"+ indexs[i].insert+"' name='"+indexs[i].uuid+"' class='validate forminput1 ml10 ' type='text'></div>"
								    +"</div>";
						}else{
							form+="<div class='edit_line'>"
                                    +"<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>"+indexs[i].name+"：</div>"
                                    +"<div class='resume-editRight'>"
                                    +"<div class='divselect'>"
                                    +"<select name='"+indexs[i].uuid+"' id='"+indexs[i].uuid+"' class='validate radio select forminput1'>";

							for(var o in options){
								if(options[o].selected=="true"){
									form+="<option selected='selected' value='"+options[o].value+"'>"+options[o].name+"</option>";
								}
								if(options[o].selected==null){
									form+="<option value='"+options[o].value+"'>"+options[o].name+"</option>";
								}
							}
							form+="</select></div></div></div>";
						}
						
					}
					form+="<div class='save'>保存</div>";
					document.getElementById('form').innerHTML = form;
				}else{
					/* 获取对象 */
					var info = $("#CreditIndex").children(".resume-browse-area");
					info.append("<div class='browse-info'><ul id='credit' style='width:100%'></ul></div>");
					var credit="";
					var count=0;
					for(var i in indexs){
						count++;
						if(count>10)break;
						var options=indexs[i].options;
						if(indexs[i].write=="true"){
							credit+="<li><label>"+indexs[i].name+"：</label><span >"+ subValue(indexs[i].name,indexs[i].insert)+ "</span></li>";
						}else{
							var option="";
							for(var j in options){
								if(options[j].selected=="true"){
									option="<li><label>"+indexs[i].name+"：</label><span >"+subValue(indexs[i].name,options[j].name)+ "</span></li>";
									break;
								}else{
									option="<li><label>"+indexs[i].name+"：</label><span >--</span></li>";
								}
							}
							credit+=option;
						}
						
					}
					document.getElementById('credit').innerHTML = credit;
				}
			} else {
				popup(data.msg);
			}

		},
		error : function() {
			popup("网络连接失败，请稍后再试..");
		}
	});
}
/* ajax加载 个人信息表 */
function listPerBaseInfo(state) {
		$.ajax({
			type : "post",
			url : _ctxPath + "/personalInfo/listPerBaseInfo.do",
			success : function(data) {
				$(".modal-overlay").css("display","none");
				if (data.status) {
					var perBaseInfo = data.perBaseInfo;
					var idtermstart = "";
					if (perBaseInfo.idtermstart != null) {
						idtermstart = (new Date(perBaseInfo.idtermstart)).Format("yyyy-MM-dd");
					}
					var idtermend = "";
					if (perBaseInfo.idtermend != null) {
						idtermend = (new Date(perBaseInfo.idtermend)).Format("yyyy-MM-dd");
					}
					
					
					if (state == "" || state == null) {
						/* 获取对象 */
						var name = isNull(perBaseInfo.name);
						if(name.length>13){
							name = name.slice(0,10)+"...";
						}
						var idissuingagency = isNull(perBaseInfo.idissuingagency);
						if(idissuingagency != null && idissuingagency.length>13){
							idissuingagency = idissuingagency.slice(0,10)+"...";
						}
						var info = $("#PerBaseInfo").children(".resume-browse-area");
						info.empty();
						info.append("<div class='browse-info'>"
								+ "<input type='hidden' id='perID' name='uuid' value='"+perBaseInfo.uuid+"'>"
								+ "<input type='hidden' id='uscc' name='uscc' value='"+perBaseInfo.uscc+"'>"
								+ "<ul><li><label>真实姓名：</label><span >"+name+"</span></li>"
								+ "<li><label>曾用名：</label><span>"+isNull(perBaseInfo.usedname)+"</span></li>"
								+ "<li><label>民族：</label><span>"+isNull(perBaseInfo.nation)+"</span></li>"
								+ "<li><label>政治面貌：</label><span>"+isNull(perBaseInfo.politicaloutlook)+"</span></li>"
								+ "<li><label>生育情况：</label><span>"+isNull(perBaseInfo.fertilitycondition)+"</span></li>"
								+ "<li><label>户籍地址：</label><span>"+isNull(perBaseInfo.permanentaddress)+"</span></li>"
								+ "<li><label>身份证有效期开始：</label><span>"+isNull(idtermstart)+"</span></li>"
								+ "<li><label>现居住地邮政编码：</label><span>"+isNull(perBaseInfo.presentzipcode)+"</span></li>"
								+ "</ul>"
								+ "<ul><li><label>身份证号：</label> <span>"+replaceChat(isNull(perBaseInfo.idcard),6,14,"********")+"</span><em data-telstatue='false' class='telstatue '>已绑定√</em></li>"
								+ "<li><label>国籍：</label><span>"+isNull(perBaseInfo.nationality)+"</span></li>"
								+ "<li><label>性别：</label><span>"+isNull(perBaseInfo.gender)+"</span></li>"
								+ "<li><label>婚姻状况：</label><span>"+isNull(perBaseInfo.maritalstatus)+"</span></li>"
								+ "<li><label>现居住地址：</label><span>"+isNull(perBaseInfo.presentaddress)+"</span></li>"
								+ "<li><label>籍贯：</label><span>"+isNull(perBaseInfo.nativeplace)+"</span></li>"
								+ "<li><label>身份证有效期结束：</label><span>"+isNull(idtermend)+"</span></li>" 
								+ "<li><label>身份证签发机关：</label><span>"+idissuingagency+"</span></li></ul></div>");
						}else {
							var province = "";
							var nation = "";
							var country = "";
							$.ajaxSettings.async = false;//false:同步 true :异步
							$.getJSON(_ctxPath +"/Json/processJson.json",function(data){
								var json_province = data.json_province;
								var json_nation = data.json_nation;
								var json_country = data.json_country;
								for ( var i in json_province) {
									province += "<li><a href='javascript:;' >"+json_province[i].name+"</a></li>";
								}
								for ( var i in json_nation) {
									nation += "<li><a href='javascript:;' >"+json_nation[i].name+"</a></li>";
								}
								for ( var i in json_country) {
									country += "<li><span>("+json_country[i].abbreviation+")</span><a href='javascript:;' >"+json_country[i].name+"</a></li>";
								}
							});
							var info = $("#PerBaseInfo").children(".resume-edit-area");
							info.empty();
							info.append("<form><input type='hidden' value='"+isNull2(perBaseInfo.uuid)+ "' name='uuid'><input type='hidden' name='uscc' value='"+perBaseInfo.uscc+"'>" 
								+ "<div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>真实姓名：</div>"
								+ "<div class='resume-editRight'><input value='"+isNull2(perBaseInfo.name)+ "' name='name' placeholder='请填写真实姓名' class='validate validateCharacter forminput1 ml10' type='text'></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft'>曾用名：</div>"
								+ "<div class='resume-editRight'><input value='"+isNull2(perBaseInfo.usedname)+ "' name='usedname' placeholder='请填写曾用名' class='validateCharacter forminput1 ml10 ' type='text'></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>国籍：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(perBaseInfo.nationality)+ "' name='nationality' readonly='readonly' placeholder='请选择国籍' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'>"+country+"</ul>"
								+ "</div></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>性别：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(perBaseInfo.gender)+ "' name='gender' readonly='readonly' placeholder='请选择性别' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'><li><a href='javascript:;' >男</a></li><li><a href='javascript:;' >女</a></li></ul>"
								+ "</div></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>民族：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(perBaseInfo.nation)+ "' name='nation' readonly='readonly' placeholder='请选择民族' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'>"+nation+"</ul>"
								+ "</div></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>政治面貌：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(perBaseInfo.politicaloutlook)+ "' name='politicaloutlook' readonly='readonly' placeholder='请选择政治面貌' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'><li><a href='javascript:;' >中共党员</a></li><li><a href='javascript:;' >中共预备党员</a></li><li><a href='javascript:;' >共青团员</a></li><li><a href='javascript:;' >民革党员</a></li><li><a href='javascript:;' >民盟盟员</a></li><li><a href='javascript:;' >民建会员</a></li><li><a href='javascript:;' >民进会员</a></li><li><a href='javascript:;' >农工党党员</a></li><li><a href='javascript:;' >致公党党员</a></li><li><a href='javascript:;' >九三学社社员</a></li><li><a href='javascript:;' >台盟盟员</a></li><li><a href='javascript:;' >无党派人士</a></li><li><a href='javascript:;' >群众</a></li></ul>"
								+ "</div></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>婚姻状况：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(perBaseInfo.maritalstatus)+ "' name='maritalstatus' readonly='readonly' placeholder='请选择婚姻状况' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'><li><a href='javascript:;' >未婚</a></li><li><a href='javascript:;' >已婚</a></li><li><a href='javascript:;' >离婚</a></li><li><a href='javascript:;' >丧偶</a></li></ul>"
								+ "</div></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>生育情况：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(perBaseInfo.fertilitycondition)+ "' name='fertilitycondition' readonly='readonly' placeholder='请选择生育情况' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'><li><a href='javascript:;' >未婚未育</a></li><li><a href='javascript:;' >未婚先育</a></li><li><a href='javascript:;' >已婚未育</a></li><li><a href='javascript:;' >已婚已育</a></li></ul>"
								+ "</div></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>籍贯：</div>"
								+ "<div class='resume-editRight'><input value='"+isNull2(perBaseInfo.nativeplace)+ "' name='nativeplace' placeholder='请填写籍贯' class='validate forminput1 ml10' type='text'></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>现居住地址：</div>"
								+ "<div class='resume-editRight'><input value='"+isNull2(perBaseInfo.presentaddress)+ "' name='presentaddress' placeholder='请填写现居住地址' class='validate forminput1 ml10' type='text'></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>户籍地址：</div>"
								+ "<div class='resume-editRight'><input value='"+isNull2(perBaseInfo.permanentaddress)+ "' name='permanentaddress' placeholder='请填写户籍地址' class='validate forminput1 ml10' type='text'></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>身份证号：</div>"
								+ "<div class='resume-editRight'><input value='"+isNull2(perBaseInfo.idcard)+ "' name='idcard' placeholder='请填写身份证号' class='validate validateID forminput1 ml10' type='text'></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>身份证有效期开始：</div>"
								+ "<div class='resume-editRight'><input value='"+isNull2(idtermstart)+ "' name='idtermstart' id = 'idtermstart' placeholder='请填写身份证有效期开始时间' onfocus='maxdate(\"idtermend\")' class='validate validateDate forminput1 ml10' type='text'></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>身份证有效期结束：</div>"
								+ "<div class='resume-editRight'><input value='"+isNull2(idtermend)+ "' name='idtermend' id = 'idtermend' placeholder='请填写身份证有效期结束时间' onfocus='mindate(\"idtermstart\")' class='validate validateDate forminput1 ml10' type='text'></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>身份证签发机关：</div>"
								+ "<div class='resume-editRight'><input value='"+isNull2(perBaseInfo.idissuingagency)+ "' name='idissuingagency' placeholder='请填写身份证签发机关' class='validate forminput1 ml10' type='text'></div>"
								+ "</div><div class='edit_line '>"
								+ "<div class='resume-editLeft'><span>*</span>邮政编码：</div>"
								+ "<div class='resume-editRight'><input value='"+isNull2(perBaseInfo.presentzipcode)+ "' name = 'presentzipcode' maxlength='6' placeholder='请填写地区邮编' class='validate validateNum forminput1 ml10 ' type='text'></div>"
								+ "</div><div class='save'>保存</div></form>");
					}
				} else {
					popup(data.msg);
				}
			},
			error : function() {
				popup("网络连接失败，请稍后再试..");
			}
		});
	}


//学历
function listEducation(uuid) {
	$.ajax({
				type : "post",
				data : {
					educationId : uuid
				},
				url : _ctxPath + "/personalInfo/listEducation.do",
				success : function(data) {
					if (data.status) {
						if (!data.edit) {
							/* 获取 集合 界面 */
							var education = data.educationList;
							if (education != null && education.length > 0) {
								// 隐藏添加界面
								$("#Education").attr("data-statue", true).children(".addmorebox").css("display","none");
								/* 获取 展示 对象 */
								var info = $("#Education").children(".resume-browse-area");
								info.css("display", "block").empty();
								info.append("<div class='browse-info1 layout2-item' data-id=''><ul>"
												+ "<li class='layout2-item1'><span>学历</span></li>"
												+ "<li class='layout2-item1'><span>开始时间</span> </li>"
												+ "<li class='layout2-item1'><span>结束时间</span></li>"
												+ "<li class='layout2-item1'><span>专业</span></li>"
												+ "<li class='layout2-item1' style = 'width:32%;'><span>毕业院校</span></li></ul></div>");
								for ( var i = 0; i < education.length; i++) {
									var starttime = " - ";
									if (education[i].starttime != null) {
										starttime = (new Date(education[i].starttime)).Format("yyyy-MM-dd");
									}
									var endtime = " - ";
									if (education[i].endtime != null) {
										endtime = (new Date(education[i].endtime)).Format("yyyy-MM-dd");
									}
									var university = isNull(education[i].university);
									if(university.length>20){
										university = university.slice(0,18)+"...";
									}
									info.append("<div class='browse-info2' data-id='Education"+ (i + 1)+ "'>"
													+ "<div class='layout2-item' >"
													+ "<input type='hidden' name='uuid' value='"+ education[i].uuid+ "' /><ul>"
													+ "<li class='layout2-item3'><span title='"+isNull(education[i].education)+"'>"+isNull( education[i].education)+ "</span></li>"
													+ "<li class='layout2-item3'><span title='"+isNull(education[i].starttime)+"'>"+isNull( starttime)+ "</span> </li>"
													+ "<li class='layout2-item3'><span title='"+isNull(education[i].endtime)+"'>"+isNull( endtime)+ "</span></li>"
													+ "<li class='layout2-item3'><span title='"+isNull(education[i].major)+"'>"+isNull( education[i].major)+ "</span></li>"
													+ "<li class='layout2-item3' style = 'width:32%;'><span title = '"+education[i].university+"'>"+university+ "</span></li>"
													+ "</ul><p class='item-edit2 edit'>编辑</p></div></div>");
								}
							} else {
								$("#Education").attr("data-statue", false).children(".addmorebox").css("display","block");
								$("#Education").children(".resume-browse-area").css("display","none");
							}
						} else {
							var education = data.education;
							var starttime = "";
							if (education.starttime != null) {
								starttime = (new Date(education.starttime)).Format("yyyy-MM-dd");
							}
							var endtime = "";
							if (education.endtime != null) {
								endtime = (new Date(education.endtime)).Format("yyyy-MM-dd");
							}
							/* 进入 单条信息编辑界面 */
							$("#Education").attr("edit-statue", true).find(".resume-edit").text("取消");
							$("#Education").children(".resume-browse-area").css("display", "none");
							$("#Education").children(".resume-edit-area").css("display", "block").empty();
							$("#Education").children(".resume-edit-area").append("<form><input type='hidden' name='uuid' value='"+ isNull2(education.uuid)+ "' /><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>学历：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(education.education)+"' name='education' readonly='readonly' placeholder='请选择学历' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'><li><a href='javascript:;' >博士研究生及以上</a></li><li><a href='javascript:;' >硕士研究生</a></li><li><a href='javascript:;' >本科</a></li><li><a href='javascript:;' >专科</a></li><li><a href='javascript:;' >高中及以下</a></li></ul>"
								+ "</div></div>"
								+ "</div> <div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>起始时间：</div>"
								+ "<div class='resume-editRight' style = 'width:308px;'>" 
								+"<input name='starttime' id = 'starttimeEducationEdit' value='"+isNull2(starttime)+"' placeholder='请填写开始日期' style='width:38%;' class='validate forminput1 ml10' onfocus='maxdate(\"endtimeEducationEdit\")' type='text'><span style='margin: 0 5px;'>至</span> " 
								+"<input name='endtime' id = 'endtimeEducationEdit' value='"+isNull2(endtime)+"' placeholder='请填写毕业日期' style='width:38%;' class='validate forminput1' onfocus='mindate(\"starttimeEducationEdit\")' type='text'>" 
								+ "</div></div><div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>毕业院校：</div>"
								+ "<div class='resume-editRight'><input name='university' value='"+isNull2(education.university)+"' placeholder='请填写毕业院校' class='validate forminput1 ml10' type='text'></div>"
								+ "</div> <div class='edit_line '>"
								+ "<div class='resume-editLeft'><span>*</span>专业：</div>"
								+ "<div class='resume-editRight'><input name='major' value='"+isNull2(education.major)+"' placeholder='请填写专业' class='validate forminput1 ml10' type='text'></div>"
								+ "</div> <div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>毕业证书编号：</div>"
								+ "<div class='resume-editRight'><input name='diplomano' value='"+isNull2(education.diplomano)+"' placeholder='请填写毕业证书编号' class='validate forminput1 ml10' type='text'></div>"
								+ "</div><div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>");
						}
					} else if(data.status == -1){
						popupUrl(data.msg,_ctxPath+"/customer/logonUI.do");
					}else{
						popup(data.msg);
					}
				},
				error : function() {
					popup("网络连接失败，请稍后再试..");
				}
			});
}
//删除学历
function deleteEducation(uuid) {
	$.ajax({
		type : "post",
		data : {
			educationId : uuid
		},
		url : _ctxPath + "/personalInfo/deleteEducation.do",
		success : function(data) {
			if (data.status == "true") {
				popup("删除成功");
			}
		},
	});
};
//工作经验
function listCareer(uuid) {
	$.ajax({
				type : "post",
				data : {
					careerId : uuid
				},
				url : _ctxPath + "/personalInfo/listCareer.do",
				success : function(data) {
					if (data.status) {
						if (!data.edit) {
							/* 获取 集合 界面 */
							var career = data.careerList;
							if (career != null && career.length > 0) {
								// 隐藏添加界面
								$("#Career").attr("data-statue", true).children(".addmorebox").css("display","none");
								/* 获取 展示 对象 */
								var info = $("#Career").children(".resume-browse-area");
								info.css("display", "block").empty();
								info.append("<div class='browse-info1 layout2-item' data-id=''><ul>"
										+ "<li class='layout2-item1'><span>就职时间</span></li>"
										+ "<li class='layout2-item1'><span>就职单位</span></li>"
										+ "<li class='layout2-item1'><span>职业资质</span> </li>"
										+ "<li class='layout2-item1'><span>单位性质</span> </li>"
										+ "<li class='layout2-item1'><span>单位规模</span></li>"
										+ "<li class='layout2-item1'><span>从事行业</span></li>"
										+ "<li class='layout2-item1'><span>职务</span></li>"
										+ "<li class='layout2-item1'><span>工作年限</span></li>"
										+ "<li class='layout2-item1'><span>平均薪资</span></li></ul></div>");
								for ( var i = 0; i < career.length; i++) {
									var starttime = "";
									if (career[i].starttime != null) {
										starttime = (new Date(career[i].starttime)).Format("yyyy-MM-dd");
									}
									var endtime = "";
									if (career[i].endtime != null) {
										endtime = (new Date(career[i].endtime)).Format("yyyy-MM-dd");
									}
									var inauguralunit = isNull(career[i].inauguralunit);
									if(inauguralunit.length>20){
										inauguralunit = inauguralunit.slice(0,17)+"...";
									}
									info.append("<div class='browse-info2' data-id='Career"+ (i + 1)+ "'>"
													+ "<div class='layout2-item' >"
													+ "<input type='hidden' name='uuid' value='"+career[i].uuid+ "' /><ul>"
													+ "<li class='layout2-item3'><span title='"+isNull(starttime)+"至"+isNull(endtime)+"'>"+isNull(starttime)+ "至"+isNull(endtime)+"</span></li>"
													+ "<li class='layout2-item3'><span title='"+isNull(career[i].inauguralunit)+"'>"+isNull(inauguralunit)+ "</span></li>"
													+ "<li class='layout2-item3'><span title='"+isNull(career[i].qualifications)+"'>"+isNull( career[i].qualifications)+ "</span></li>"
													+ "<li class='layout2-item3'><span title='"+isNull(career[i].unitproperties)+"'>"+isNull( career[i].unitproperties)+ "</span></li>"
													+ "<li class='layout2-item3'><span title='"+isNull(career[i].unitscale)+"'>"+isNull( career[i].unitscale)+ "</span></li>"
													+ "<li class='layout2-item3'><span title='"+isNull(career[i].industry)+"'>"+isNull( career[i].industry)+ "</span></li>"
													+ "<li class='layout2-item3'><span title='"+isNull(career[i].post)+"'>"+isNull( career[i].post)+ " </span></li>"
													+ "<li class='layout2-item3'><span title='"+isNull(career[i].workinglife)+"'>"+isNull( career[i].workinglife)+ " </span></li>"
													+ "<li class='layout2-item3'><span title='"+isNull(career[i].averagesalary)+"'>"+isNull( career[i].averagesalary)+ " </span></li>"
													+ "</ul><p class='item-edit2 edit'>编辑</p></div></div>");
								}
							} else {
								$("#Career").attr("data-statue", false).children(".addmorebox").css("display","block");
								$("#Career").children(".resume-browse-area").css("display","none");
							}
						} else {
							var career = data.career;
							var starttime = career.starttime;
							if (starttime != null) {
								starttime = (new Date(career.starttime)).Format("yyyy-MM-dd");
							}
							var endtime = career.endtime;
							if (endtime != null) {
								endtime = (new Date(career.endtime)).Format("yyyy-MM-dd");
							}
							/* 进入 单条信息编辑界面 */
							$("#Career").attr("edit-statue", true).find(".resume-edit").text("取消");
							$("#Career").children(".resume-browse-area").css("display", "none");
							$("#Career").children(".resume-edit-area").css("display", "block").empty();
							$("#Career").children(".resume-edit-area").append("<form><input type='hidden' name='uuid' value='"+isNull2(career.uuid)+ "' /><div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>工作时间：</div>"
								+ "<div class='resume-editRight' style = 'width:308px;'><input name='starttime' id = 'starttimeCareerEdit' value='"+isNull2(starttime)+"' placeholder='请填写开始日期' style='width:38%;' class='validate forminput1 ml10' onfocus='maxdate(\"endtimeCareerEdit\")' type='text'><span style='margin:0px 5px;'>至</span> " 
								+"<input name='endtime' id = 'endtimeCareerEdit' value='"+isNull2(endtime)+"' placeholder='请填写结束日期' style='width:38%;' class='validate forminput1' onfocus='mindate(\"starttimeCareerEdit\")' type='text'></div>"
								+ "</div> <div class='edit_line '>"
								+ "<div class='resume-editLeft'><span>*</span>就职单位：</div>"
								+ "<div class='resume-editRight'><input name='inauguralunit' value='"+isNull2(career.inauguralunit)+"' placeholder='请填写就职单位' class='validate forminput1 ml10' type='text'></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>职业资质：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(career.qualifications)+"' name='qualifications' readonly='readonly' placeholder='请选择职业资质' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'><li><a href='javascript:;' >高级</a></li><li><a href='javascript:;' >中级</a></li><li><a href='javascript:;' >初级</a></li><li><a href='javascript:;' >无</a></li></ul>"
								+ "</div></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>单位性质：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(career.unitproperties)+"' name='unitproperties' readonly='readonly' placeholder='请选择单位性质' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'><li><a href='javascript:;' >国家机关、事业单位、社会团队 </a></li><li><a href='javascript:;' >三资企业、股份制企业</a></li><li><a href='javascript:;' >民营企业、个人工商户</a></li><li><a href='javascript:;' >退休领退休金</a></li><li><a href='javascript:;' >其他</a></li></ul>"
								+ "</div></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>单位规模：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(career.unitscale)+"' name='unitscale' readonly='readonly' placeholder='请选择单位规模' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'><li><a href='javascript:;' >上市企业</a></li><li><a href='javascript:;' >大型企业</a></li><li><a href='javascript:;' >中型企业</a></li><li><a href='javascript:;' >小型企业</a></li><li><a href='javascript:;' >小微企业</a></li></ul>"
								+ "</div></div>"
								+ "</div> <div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>从事行业：</div>"
								+ "<div class='resume-editRight'><input name='industry' value='"+isNull2(career.industry)+"' placeholder='请填写从事行业' class='validate forminput1 ml10' type='text'></div>"
								+ "</div> <div class='edit_line '>"
								+ "<div class='resume-editLeft'><span>*</span>职务：</div>"
								+ "<div class='resume-editRight'><input name='post' value='"+isNull2(career.post)+"' placeholder='请填写职务' class='validate forminput1 ml10' type='text'></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>工作年限：</div><div class='resume-editRight'><div class='divselect'>"
								+ "<input value='"+isNull2(career.workinglife)+"' name='workinglife' readonly='readonly' placeholder='请选择人员规模' class='validate radio cite forminput1'  type='text' >"
								+ "<ul style='display: none;'><li><a href='javascript:;' > 1年及以下</a></li><li><a href='javascript:;' > 2 ~ 3年</a></li><li><a href='javascript:;' > 4 ~ 5 年</a></li><li><a href='javascript:;' > 6 ~ 8年</a></li><li><a href='javascript:;' > 8 ~ 10年</a></li><li><a href='javascript:;' >10年以上</a></li></ul>"
								+ "</div></div>"
								+ "</div><div class='edit_line'>"
								+ "<div class='resume-editLeft'><span>*</span>平均薪资：</div>"
								+ "<div class='resume-editRight'><input name='averagesalary' value='"+isNull2(career.averagesalary)+"' placeholder='请填写平均薪资' class='validate forminput1 ml10' min = 0 type='number'></div>"
								+ "</div><div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>");
						}
					} else {
						popup(data.msg);
					}
				},
				error : function() {
					popup("网络连接失败，请稍后再试..");
				}
			});
}

function deleteCareer(uuid) {
	$.ajax({
		type : "post",
		data : {
			careerId : uuid
		},
		url : _ctxPath + "/personalInfo/deleteCareer.do",
		success : function(data) {
			if (data.status) {
				popup("删除成功");
			}else{
				popup(data.msg);
			}
		},
	});
};
//培训
function listTrain(uuid) {
	$.ajax({
		type : "post",
		data : {
			trainId : uuid
		},
		url : _ctxPath + "/personalInfo/listTrain.do",
		success : function(data) {
			if (data.status) {
				if (!data.edit) {
					/* 获取 集合 界面 */
					var train = data.trainList;
					if (train != null && train.length > 0) {
						// 隐藏添加界面
						$("#Train").attr("data-statue", true).children(".addmorebox").css("display","none");
						/* 获取 展示 对象 */
						var info = $("#Train").children(".resume-browse-area");
						info.css("display", "block").empty();
						info.append("<div class='browse-info1 layout2-item' data-id=''><ul>"
								+ "<li class='layout2-item1'><span>开始日期</span></li>"
								+ "<li class='layout2-item1'><span>结束日期</span> </li>"
								+ "<li class='layout2-item1'><span>培训机构</span></li>"
								+ "<li class='layout2-item1'><span>证书编号</span></li>"
								+ "<li class='layout2-item1'><span>培训内容</span></li></ul></div>");
						for ( var i = 0; i < train.length; i++) {
							var starttime = "";
							if (train[i].starttime != null) {
								starttime = (new Date(train[i].starttime)).Format("yyyy-MM-dd");
							}
							var endtime = "";
							if (train[i].endtime != null) {
								endtime = (new Date(train[i].endtime)).Format("yyyy-MM-dd");
							}
							var traincontent = isNull(train[i].traincontent);
							if(traincontent.length>40){
								traincontent = traincontent.slice(0,37)+"...";
							}
							var trainorg = isNull(train[i].trainorg);
							if(trainorg.length>20){
								trainorg = trainorg.slice(0,17)+"...";
							}
							info.append("<div class='browse-info2' data-id='Train"+ (i + 1)+ "'>"
									+ "<div class='layout2-item' >"
									+ "<input type='hidden' name='uuid' value='"+train[i].uuid+ "' /><ul>"
									+ "<li class='layout2-item3'><span title='"+isNull(train[i].starttime)+"'>"+isNull(starttime)+ "</span></li>"
									+ "<li class='layout2-item3'><span title='"+isNull(train[i].endtime)+"'>"+isNull(endtime)+ "</span> </li>"
									+ "<li class='layout2-item3'><span title='"+isNull(train[i].trainorg)+"'>"+trainorg+ "</span></li>"
									+ "<li class='layout2-item3'><span title='"+isNull(train[i].certificateno)+"'>"+isNull( train[i].certificateno)+ "</span></li>"
									+ "<li class='layout2-item3'><span title='"+isNull(train[i].traincontent)+"'>"+traincontent+ "</span></li>"
									+ "</ul><p class='item-edit2 edit'>编辑</p></div></div>");
						}
					} else {
						$("#Train").attr("data-statue", false).children(".addmorebox").css("display","block");
						$("#Train").children(".resume-browse-area").css("display","none");
					}
				} else {
					var train = data.train;
					var starttime = train.starttime;
					if (starttime != null) {
						starttime = (new Date(train.starttime)).Format("yyyy-MM-dd");
					}
					var endtime = train.endtime;
					if (endtime != null) {
						endtime = (new Date(train.endtime)).Format("yyyy-MM-dd");
					}
					/* 进入 单条信息编辑界面 */
					var Train = $("#Train");
					Train.attr("edit-statue", true).find(".resume-edit").text("取消");
					Train.children(".resume-browse-area").css("display", "none");
					Train.children(".resume-edit-area").css("display", "block").empty();
					Train.children(".resume-edit-area").append("<form><input type='hidden' name='uuid' value='"+isNull2(train.uuid)+ "' /><input type='hidden' name='perid' value='"+train.perid+ "' /><div class='edit_line'>"
						+ "<div class='resume-editLeft'><span>*</span>培训机构：</div>"
						+ "<div class='resume-editRight'><input name='trainorg' value='"+isNull2(train.trainorg)+"' placeholder='请填写培训机构' class='validate forminput1 ml10' type='text'></div>"
						+ "</div><div class='edit_line'>"
						+ "<div class='resume-editLeft'><span>*</span>培训日期：</div>"
						+ "<div class='resume-editRight' style = 'width:308px;' ><input name='starttime' id = 'starttimeTrainEdit'  value='"+isNull2(starttime)+"' placeholder='请选择开始日期' style='width:38%;' class='validate forminput1 ml10' onfocus='maxdate(\"endtimeTrainEdit\")' type='text'><span style='margin:0px 5px;'>-</span> " 
						+"<input name='endtime' id = 'endtimeTrainEdit' value='"+isNull2(endtime)+"' placeholder='请选择结束日期' style='width:38%;' class='validate forminput1' onfocus='mindate(\"starttimeTrainEdit\")' type='text'></div>"
						+ "</div> <div class='edit_line '>"
						+ "<div class='resume-editLeft'><span>*</span>培训地址：</div>"
						+ "<div class='resume-editRight'><input name='trainaddress' value='"+isNull2(train.trainaddress)+"' placeholder='请填写培训地址' class='validate forminput1 ml10' type='text'></div>"
						+ "</div><div class='edit_line'>"
						+ "<div class='resume-editLeft'><span>*</span>证书编号：</div>"
						+ "<div class='resume-editRight'><input name='certificateno' value='"+isNull2(train.certificateno)+"' placeholder='请填写证书编号' class='validate forminput1 ml10' type='text'></div>"
						+ "</div><div class='edit_line'>"
						+ "<div class='resume-editLeft'><span>*</span>培训内容1：</div>"
						+ "<div class='resume-editRight'><textarea name='traincontent' value='"+isNull2(train.traincontent)+"' style= 'height:100px;width: 300px;' placeholder='请填写培训内容' class='validate forminput1 ml10' type='text'>"+isNull2(train.traincontent)+"</textarea></div>"
						+ "</div>" 
						+ "<div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>");
				}
			} else {
				popup(data.msg);
			}
		},
		error : function() {
			popup("网络连接失败，请稍后再试..");
		}
	});
}

function deleteTrain(uuid) {
	$.ajax({
		type : "post",
		data : {trainId : uuid},
		url : _ctxPath + "/personalInfo/deleteTrain.do",
		success : function(data) {
			if (data.status) {
				popup("删除成功");
			}else{
				popup(data.msg);
			}
		},
	});
};
//技能
function listSkills(uuid) {
	$.ajax({
		type : "post",
		data : {
			skillsId : uuid
		},
		url : _ctxPath + "/personalInfo/listSkills.do",
		success : function(data) {
			if (data.status) {
				if (!data.edit) {
					/* 获取 集合 界面 */
					var skills = data.skillsList;
					if (skills != null && skills.length > 0) {
						// 隐藏添加界面
						$("#Skills").attr("data-statue", true).children(".addmorebox").css("display","none");
						/* 获取 展示 对象 */
						var info = $("#Skills").children(".resume-browse-area");
						info.css("display", "block").empty();
						for ( var i = 0; i < skills.length; i++) {
							var skillname = isNull(skills[i].skillname);
							if(skillname.length>20){
								skillname = skillname.slice(0,17)+"...";
							}
							if(isNull(skills[i].skillproficiency != null)){
								var width = 0;
								switch (skills[i].skillproficiency) {
								case "了解":
									width = 20;
									break;
								case "熟悉":
									width = 40;
									break;
								case "掌握":
									width = 60;
									break;
								case "精通":
									width = 80;
									break;
								case "专家":
									width = 100;
									break;
								default:
									break;
								}
							}
							info.append("<div class='browse-info2' data-id='Skills"+ (i + 1)+ "'>"
									+ "<div class='layout2-item' >"
									+ "<input type='hidden' name='uuid' value='"+skills[i].uuid+ "' /><ul>"
									+ "<li class='layout2-item3' style= 'width:20%;min-width:80px;' ><span title='"+isNull(skills[i].skillname)+"'>"+isNull(skillname)+ "</span></li>"
									+ "<li class='layout2-item3' style= 'width:60%;padding-top: 7px;padding-right: 5%;'>"
										+ "<div class='progress progress-striped active' style = 'height:10px;'>"
										+ "<div class='progress-bar progress-bar-info' style='width: "+width+"%;'>"
										+ "</div></div>"
									+" </li>"
									+ "<li class='layout2-item3'><span>"+isNull(skills[i].skillproficiency)+ "</span></li>"
									+ "</ul><p class='item-edit2 edit'>编辑</p></div></div>");
						}
					} else {
						$("#Skills").attr("data-statue", false).children(".addmorebox").css("display","block");
						$("#Skills").children(".resume-browse-area").css("display","none");
					}
				} else {
					var skills = data.skills;
					var starttime = skills.starttime;
					if (starttime != null) {
						starttime = (new Date(skills.starttime)).Format("yyyy-MM-dd");
					}
					var endtime = skills.endtime;
					if (endtime != null) {
						endtime = (new Date(skills.endtime)).Format("yyyy-MM-dd");
					}
					/* 进入 单条信息编辑界面 */
					var Skills = $("#Skills");
					Skills.attr("edit-statue", true).find(".resume-edit").text("取消");
					Skills.children(".resume-browse-area").css("display", "none");
					Skills.children(".resume-edit-area").css("display", "block").empty();
					Skills.children(".resume-edit-area").append("<form><input type='hidden' name='uuid' value='"+skills.uuid+ "' /><input type='hidden' name='perid' value='"+skills.perid+ "' /><div class='edit_line'>"
						+ "<div class='resume-editLeft'><span>*</span>技能名称：</div>"
						+ "<div class='resume-editRight'><input name='skillname' value='"+isNull2(skills.skillname)+"' placeholder='请填写技能名称' class='validate forminput1 ml10' type='text'></div>"
						+ "</div><div class='edit_line'>"
						+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>技能熟练度：</div><div class='resume-editRight'><div class='divselect'>"
						+ "<input name='skillproficiency' value='"+isNull2(skills.skillproficiency)+"' readonly='readonly' placeholder='请选择技能熟练度' class='validate radio cite forminput1'  type='text' >"
						+ "<ul style='display: none;'><li><a href='javascript:;' >了解</a></li><li><a href='javascript:;' >熟悉</a></li><li><a href='javascript:;' >掌握</a></li><li><a href='javascript:;' >精通</a></li><li><a href='javascript:;' >专家</a></li></ul>"
						+ "</div></div>"
						+ "</div><div class='edit_line'>"
						+ "<div class='resume-editLeft'>备注：</div>"
						+ "<div class='resume-editRight'><textarea name='remarks' value='"+isNull2(skills.remarks)+"' style= 'height:100px;width: 300px;' placeholder='选填' class=' forminput1 ml10' type='text'></textarea></div>"
						+ "</div>" 
						+ "<div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>");
				}
			} else {
				popup(data.msg);
			}
		},
		error : function() {
			popup("网络连接失败，请稍后再试..");
		}
	});
}

function deleteSkills(uuid) {
	$.ajax({
		type : "post",
		data : {skillsId : uuid},
		url : _ctxPath + "/personalInfo/deleteSkills.do",
		success : function(data) {
			if (data.status) {
				popup("删除成功");
			}else{
				popup(data.msg);
			}
		},
	});
};

/* 附件列表 */
function listUploadFile() {
	$.ajax({
		type : "post",
		url : _ctxPath + "/personalInfo/listUploadFile.do",
		success : function(data) {
			if(data.status){
				var categorys=data.categorys;
				var info = $("#UploadFile").find(".dream-controls");
				info.empty();
				var radio="";
				for(var i in categorys){
					var name = categorys[i].name;
					if(categorys[i].ismust==1){
						name = categorys[i].name+"<em style='color:#ff6a0e'>*</em>";
					}
					radio+="<li><input type='radio' name='type' id='"+categorys[i].uuid+"' value='"+categorys[i].uuid+"'/>"
					+"<span class='radio-label'><label class='radio' for='"+categorys[i].uuid+"'>"	      
				    +"</label><label for='"+categorys[i].uuid+"'>"+name+"</label></span></li>";
				}
				info.append("<div class='ui-radio' id='radio'>"+radio+"</div>");
			}else{
				popup(data.msg);
			}
		},
		error : function() {
			popup("网络连接失败，请稍后再试..");
		}
	});
}
/*检查文件是否上传全*/
$(".checkfile").click(function(){
	$.ajax({
		type : "post",
		url : _ctxPath + "/personalInfo/judgeMustFile.do",
		success : function(data) {
			if(data.status){
				$("#UploadFile").attr("data-statue", true);
				refresh();
			}else{
				popup("部分文件未上传，请检查！");
			}
			$(".tip").text("提示："+data.msg);
		},
		error : function() {
			popup("网络连接失败，请稍后再试..");
		}
	});

});
/*点击附件列表的单选按钮，弹出上传模块*/
$(".dream-controls").on("click","input",function(){
	$(".form-item").css("display","none");
	$("#UploadFile").find(".resume-edit").css("display","block");
	$("#UploadFile").find("#uploadFileList").css("display","block");
	var name=$(this).siblings("span").children("label:last").text();
	var uuid=$(this).val();
	getUploadFile(name,uuid);
});
/*获取已上传附件内容*/
function getUploadFile(name,uuid){
	$.ajax({
		type : "post",
		url : _ctxPath + "/personalInfo/getUploadFile.do",
		data:{'fileID':uuid},
		success : function(data) {
			if(data.status){
				$("#uploader").css("display","none");
				var files=data.files;
				var info = $("#UploadFile").find("#uploadFileList");
				info.empty();
				info.append("<div class='box'><h4>"+name+"</h4><ul id='filelist'>" +
						"</ul></div>");
				var filelist="";
				for(var i in files){
					filelist+="<li id='"+files[i].uuid+"'><div class='deatil'><h2>"+files[i].filesize+"</h2>"
						+"<p>"+files[i].adddate+"</p>" 
						+"<a class='confirm' href='javascript:void(0);' onclick='deleteFile(\""+files[i].uuid+"\")'>删除</a></div>"
						+"<img src='"+_ctxPath+files[i].fileurl+"' width='140px' height='140px' alt=''/></li>";
				}
				filelist += "<li ><div class='deatil'>"
						 +"<a style='margin-top:50px' href='javascript:void(0);' onclick='addFile(\""+name+"\""+","+"\""+uuid+"\")'>继续添加</a>"
						 +"</div><img src='"+_ctxPath+"/Images/addfile.png' width='140px' height='140px' alt=''/></li>";
				document.getElementById('filelist').innerHTML = filelist;
			}else{
				//popup(data.msg);
				$("#UploadFile").find("#uploadFileList").empty();
				$("#uploader").attr("style","position: static; opacity: 1;");
				$("#title").text(name);	
				$("#title").attr("data-value",uuid);	
				$("#filePicker").find("div:last").css("width","100%").css("left","0px");
				$("#filePicker").closeUploader();
			}
		},
		error : function() {
			popup("网络连接失败，请稍后再试..");
		}
	});
}
/*删除附件*/
function deleteFile(uuid){
	 var msg = "是否删除该图片？";  
    if (confirm(msg)==true){  
    	$.ajax({
			type : "post",
			url : _ctxPath + "/personalInfo/deleteUploadFile.do",
			data:{'uuid':uuid},
			success : function(data) {
				if(data.status){
					getUploadFile(data.name,data.fileID);
				}
				popup(data.msg);
			},
			error : function() {
				popup("网络连接失败，请稍后再试..");
			}
		});
    }else{  
        return false;  
    }  
}
/*添加附件*/
function addFile(name,uuid){
	$("#UploadFile").find("#uploadFileList").empty();
	$("#uploader").attr("style","position: static; opacity: 1;");
	$("#title").text(name);	
	$("#title").attr("data-value",uuid);	
	$("#filePicker").find("div:last").css("width","100%").css("left","0px");
	$("#filePicker").closeUploader();
}

function refresh(modelRoot) {
	/**   后续增加功能 需添加 startup 单击保存时 调用数据执行异步刷新*/
	if(modelRoot == null){
		listPerBaseInfo();
		listEducation();
		listCareer();
		listTrain();
		listSkills();
		listCreditIndex();
		listUploadFile();
	}else{
		//比较块   然后执行异步刷新
		eval("list"+modelRoot.attr("id")+"()");
	}
	//右侧 菜单设置分值
	listProcessState();
}

function verification(){
	/* 初始化验证 */
	$(".validate").each(function() {
		if ($(this).val().trim() != "" && $(this).val().trim() != null) {
			$(this).attr("data-test", true);
		} else {
			$(this).attr("data-test", false);
		}
	});
}
/*消息提示框*/
function popup(str) {
	$("body").append("<div id = 'popup' style='width: 300px;height: 100px;border: 2px #00a0e9 solid;margin: auto;position: fixed;left: 40%;top: 30%;background:#f9f9f9;border-radius: 15px;box-shadow: 10px 7px 15px #888;'><label style='text-align: center;width: 100%;height: 100%;line-height: 100px;font-family: '微软雅黑';font-size: 15px;'>"+ str + "</label></div>");
	window.setTimeout(function() {
		$("#popup").remove();
	}, 2000);//设置显示时间
}
function maxdate(startDate){
	WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D('+startDate+')}',lang:'zh-cn'});
}
function mindate(endDate){
		WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D('+endDate+')}',lang:'zh-cn'});
}
/*如果字符串为空时，显示--*/
function isNull(param){
	if(param == null){
		param = "--";
	}
	return param;
}
/*如果字符串为空时，显示空字符*/
function isNull2(param){
	if(param == null || param == "null"){
		param = "";
	}
	return param;
}
//替换字符
function replaceChat(source,pos,end,newChar){  
    if(pos<2||pos>=source.length||source.length==0){  
        return " - ";  
    }  
    var iBeginPos= 0, iEndPos=source.length;  
    var sFrontPart=source.substr(iBeginPos,pos);  
    var sTailPart=source.substr(end,source.length);  
    var sRet=sFrontPart+newChar+sTailPart;  
    return sRet;  
} 
/*判断字符长度截取字符串*/
function subValue(lable,span){
	var length=lable.length+span.length;
	if(length >40){
		span = span.substring(0,40-lable.length)+'...';
	}
	return span;
}
/*格式化时间  --- date转str*/
Date.prototype.Format = function(fmt) { //author: meizz
	var o = {
		"M+" : this.getMonth() + 1, //月份
		"d+" : this.getDate(), //日
		"h+" : this.getHours(), //小时
		"m+" : this.getMinutes(), //分
		"s+" : this.getSeconds(), //秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度
		"S" : this.getMilliseconds()
	//毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]): (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};