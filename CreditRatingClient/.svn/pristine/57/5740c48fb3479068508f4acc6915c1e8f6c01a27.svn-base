$(function() {
	/** 后续增加功能 需添加 startup 页面加载时获取数据 */
	/* 初始化加载操作 */
	refresh(null);
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
				if (modelRoot.attr("id") == "EntBaseInfo"||modelRoot.attr("id") == "CreditIndex") {
					$(this).text($(this).text() == "编辑" ? "取消" : "编辑");
				} else {
					$(this).text($(this).text() == "添加" ? "取消" : "添加");
				}

				/* 切换模块状态 */
				var shows = $(this).closest('.browse-head').next(".resume-browse-area");
				if ($(this).text() == "编辑" || $(this).text() == "添加") {
					if (shows.nextAll(".addmorebox").size() != 0 && shows.children().size() == 0) {
						shows.nextAll(".addmorebox").css("display", "block");
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
						//$("#UploadFile").find(".filelist").children().remove();//UL
						//$("#UploadFile").find(".statusBar").remove();
						//window.location.reload(); 
						refresh(modelRoot);
					}
					shows.next(".resume-edit-area").css("display", "none").children().remove();
				} else if ($(this).text() == "取消") {
					shows.nextAll(".addmorebox").css("display", "none");
					shows.css("display", "none").parent(".resume-content").attr("edit-statue", true).attr("data-statue",false);
					shows.next(".resume-edit-area").css("display", "block").children().remove();
					
					if (modelRoot.attr("id") == "EntBaseInfo") {
						/* 执行 编辑界面 */
						listEntBaseInfo("edit");
					} else if (modelRoot.attr("id") == "CreditIndex") {
						/* 执行 编辑界面 */
						listCreditIndex("edit");
					} else {
						// 像model 设置属性 和 添加按钮 edit-statue：编辑状态 true是编辑状态   data-statue from表单提交验证
/** 后续增加功能 需添加 startup 单击添加时 调用相对i应的数据 */
						if (modelRoot.attr("id") == "Shareholder") {
							modelRoot.children(".resume-edit-area").append(newShareholder);
						}
						if (modelRoot.attr("id") == "Executives") {
							modelRoot.children(".resume-edit-area").append(newExecutives);
						}
						/** 后续增加功能 需添加 end */
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
						$(this).css("border", "1px #E3E3E3 solid").nextAll("label").remove();
						if ($(this).val().trim() == "") {
							$(this).css("border", "1px #ff6a0e solid").attr("data-test", false).parent().append("<label class = 'notice'>该项内容不能为空</label>")
						} else {
							$(this).attr("data-test", true);
							/* 验证字符 */
							if ($(this).attr("class").indexOf("validateCha") != -1) {
								$(this).css("border", "1px #E3E3E3 solid")
										.nextAll("label").remove();
								var validateCha = /^[a-zA-Z0-9_]+$/;
								if (!validateCha.test($(this).val().trim())) {
									$(this).css("border", "1px #ff6a0e solid").attr("data-test", false).parent().append("<label class = 'notice'>该项内容只能为字母,数字,_</label>")
								} else {
									$(this).attr("data-test", true);
								}
							}
							/* 验证数字 */
							if ($(this).attr("class").indexOf("validateNum") != -1) {
								$(this).css("border", "1px #E3E3E3 solid")
										.next("label").remove();
								var validateCha = /^[1-9]\d*$|^\d+\.\d+$/;
								if (!validateCha.test($(this).val().trim())) {
									$(this)
											.css("border", "1px #ff6a0e solid")
											.attr("data-test", false)
											.parent()
											.append(
													"<label class = 'notice'>只能输入数字或者小数</span>")
								} else {
									$(this).attr("data-test", true);
								}
							}
							/* 验证网址 */
							if ($(this).attr("class").indexOf("validateWeb") != -1) {
								$(this).css("border", "1px #E3E3E3 solid")
										.next("label").remove();
								var validateCha = /^(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i;
								if (!validateCha.test($(this).val().trim())) {
									$(this)
											.css("border", "1px #ff6a0e solid")
											.attr("data-test", false)
											.parent()
											.append(
													"<label class = 'notice'>请输入有效的地址如www.****.com</span>")
								} else {
									$(this).attr("data-test", true);
								}
							}
							/* 验证联系方式 */
							if ($(this).attr("class").indexOf("validateTel") != -1) {
								$(this).css("border", "1px #E3E3E3 solid")
										.next("label").remove();
								var validateCha = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8}|\d{3})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
								if (!validateCha.test($(this).val().trim())) {
									$(this)
											.css("border", "1px #ff6a0e solid")
											.attr("data-test", false)
											.parent()
											.append(
													"<label class = 'notice'>请输入正确,如:0920-29392929、132****8901</span>")
								} else {
									$(this).attr("data-test", true);
								}
							}
							/* 验证Email */
							if ($(this).attr("class").indexOf("validateEmail") != -1) {
								$(this).css("border", "1px #E3E3E3 solid")
										.next("label").remove();
								var validateCha = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
								if (!validateCha.test($(this).val().trim())) {
									$(this)
											.css("border", "1px #ff6a0e solid")
											.attr("data-test", false)
											.parent()
											.append(
													"<label class = 'notice'>请输入有效的Email</span>")
								} else {
									$(this).attr("data-test", true);
								}
							}
							/* 验证中文 */
							if ($(this).attr("class")
									.indexOf("validateChinese") != -1) {
								$(this).css("border", "1px #E3E3E3 solid")
										.next("label").remove();
								var validateCha = /^[\u4e00-\u9fa5 (（）)]+$/;
								if (!validateCha.test($(this).val().trim())) {
									$(this)
											.css("border", "1px #ff6a0e solid")
											.attr("data-test", false)
											.parent()
											.append(
													"<label class = 'notice'>该项内容只能为中文</span>")
								} else {
									$(this).attr("data-test", true);
								}
							}
							/* 验证时间 */
							if ($(this).attr("class").indexOf("validateDate") != -1) {
								$(this).css("border", "1px #E3E3E3 solid")
										.next("label").remove();
								var validateCha = /([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/i;
								if (!validateCha.test($(this).val().trim())) {
									$(this)
											.css("border", "1px #ff6a0e solid")
											.attr("data-test", false)
											.parent()
											.append(
													"<label class = 'notice'>请输入正确的时间,不足两位需补0  ,如1991-01-01</span>")
								} else {
									$(this).attr("data-test", true);
								}
							}
							if ($(this).attr("class").indexOf(
									"validatePercentage") != -1) {
								$(this).css("border", "1px #E3E3E3 solid")
										.next("label").remove();

								if (parseInt($(this).val()) > 100
										|| parseInt($(this).val()) <= 0) {
									$(this)
											.css("border", "1px #ff6a0e solid")
											.attr("data-test", false)
											.parent()
											.append(
													"<label class = 'notice'>填入项分数为 百分制</span>")
								} else {
									$(this).attr("data-test", true);
								}
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
							$(this).parent().prevAll("input").val($(this).text());
					});
		/* 单选 select */
				$(".resume-edit-area").on("click",".radio",function() {
					$(this).nextAll("ul").css("display", "block").children("li").click(function() {
						$(this).parent().prev("input").val($(this).children("a").text()).attr("data-test","true").css("border", "1px #E3E3E3 solid").nextAll("label").remove();
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
						var list = $(this).next("ul").css("display", "block").children("li");
						list.each(function() {
									$(this).children("span").remove();
									var initial = $(this).children("a").text();
									if (oldvalue.indexOf(initial) != -1) {
										$(this).append("<span style='float: right;margin-right: 15px;'>✔</span>");
									}
								});
						/* 点击li标签 加✔ 并添加到input中 */
						$(this).next("ul").children("li").click(function() {
											var newvalue = $(this).children("a").text();
											/* 判断li标签 元素 是否在input 标签中存在 */
											if (oldvalue.indexOf(newvalue) != -1) {
												/* 存在后点击 */
												oldvalue = oldvalue.replace(newvalue + ",", "");
												$(this).children("span").remove();
												$(this).closest("ul").prev(".multiselect").val(oldvalue);
											} else {
												/* 不存在的点击 */
												oldvalue = oldvalue+ $(this).children("a").text() + ",";
												$(this).closest("ul").prev(".multiselect").val(oldvalue);
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
				var uuid = $(this).closest("form").children("input").val();
				/* 删除操作 */
				var modelRoot = $(this).closest(".resume-content");
/** 后续增加功能 需添加 startup 单击删除时 调用相对应的数据 */
				if (modelRoot.attr("id") == "Shareholder") {
					deleteShareholder(uuid);
				}
				if (modelRoot.attr("id") == "Executives") {
					deleteExecutives(uuid);
				}
/** 后续增加功能 需添加 end  */
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
				var uuid = $(this).prevAll("input").val();
				var modelRoot = $(this).closest(".resume-content");
/** 后续增加功能 需添加 startup 单击编辑按钮时 从后台 获取数据 */
				if (modelRoot.attr("id") == "Shareholder") {
					listShareholder(uuid);
				}
				if (modelRoot.attr("id") == "Executives") {
					listExecutives(uuid);
				}
/** 后续增加功能 需添加 end */
			});

			/* 单击保存按钮 */
			$(".resume-edit-area").on("click",".save",function() {
				verification();
				var checkSubmitFlg = true;
				if (checkSubmitFlg) {
					checkSubmitFlg = false;
					
					var incomplete = $(this).closest(".resume-edit-area ").find("[data-test = false]");
					var modelRoot = $(this).closest(".resume-content");
					if (incomplete.length == 0) {
						var params = $(this).closest("form").serialize();
						var entUuid = $("#entUuid").val();
						$.ajax({
							type : "post",
							url : _ctxPath + "/initEnterprise/update"+ modelRoot.attr("id") + ".do",
							data : params,
							success : function(msg) {
								if(msg.sturts=="true"){
									popup("保存成功！");
									// 刷新页面
									refresh(modelRoot);
									// 保存成功后 更改块的状态
									if (modelRoot.attr("id") == "EntBaseInfo"||modelRoot.attr("id") == "CreditIndex") {
										modelRoot.attr("data-statue", true).attr("edit-statue", false).find(".resume-edit").text("编辑");
									} else {
										modelRoot.attr("data-statue", true).attr("edit-statue", false).find(".resume-edit").text("添加");
									}
									// 切换 显示和 编辑 的 display状态
									modelRoot.children(".resume-browse-area").css("display", "block");
									modelRoot.children(".resume-edit-area").css("display", "none");
								}else{
									popup(msg.msg);
								}
							},
							error : function() {
								popup("网络连接错误")
							}
						});
					}else {
						modelRoot.attr("data-statue", false);
						incomplete.nextAll("label").remove();
						popup("带 *的填入项为必填项");
					}
				} else {
					modelRoot.attr("data-statue", false);
					incomplete.nextAll("label").remove();
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
						$(this).prevAll(".resume-edit-area").css("display","block").children().remove();
/** 后续增加功能 需添加 startup 单击保存时 调用数据执行异步刷新 */
						if (modelRoot.attr("id") == "Shareholder") {
							$(this).prevAll(".resume-edit-area").append(newShareholder);
						}
						if (modelRoot.attr("id") == "Executives") {
							$(this).prevAll(".resume-edit-area").append(newExecutives);
						}

/** 后续增加功能 需添加 end */
						/* 初始化验证表单数据 *放在后续增加功能后 */
						verification();
					});

				/*点击  申请报告*/
			$(".resume-Rarea").on("click",".nav-link",function(){
				if($(".resume-Rarea").find(".progress-text").children("span").text() == 100 
						&& $("#EntBaseInfo").attr("data-statue") == "true"
						&& $("#Shareholder").attr("data-statue") == "true"
						&& $("#Executives").attr("data-statue") == "true"
						&& $("#CreditIndex").attr("data-statue") == "true"
						&& $("#Finance").attr("data-statue") == "true"
						&& $("#UploadFile").attr("data-statue") == "true"){
					$.ajax({
						type : "post",
						url : _ctxPath + "/initEnterprise/updateProcessState.do",
						success : function(msg) {
							popup(msg.msg);
							refresh();
						}
					});
				}else{
					popup("必填信息项，未填写完整");
				}
			});

	/* 新增股东模块 */
	var newShareholder = "<form><div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>股东姓名：</div>"
			+ "<div class='resume-editRight'><input name='name' value=''  placeholder='请填写股东姓名' class='validate forminput1 ml10' type='text'></div>"
			+ "</div> <div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>股东类型：</div>"
			+ "<div class='resume-editRight'>"
			+ "<input name = 'type' value='自然人' type='hidden' class='radio'/>"
			+ "<div class='radiobox '>"
			+ "<div class='radioitem radioitemact'>自然人</div> <div class='radioitem'>法人</div> </div> </div>"
			+ "</div> <div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>认缴日期：</div>"
			+ "<div class='resume-editRight'><input name='shouldtime' value='' placeholder='请填写认缴日期' class='validate validateDate  forminput1 ml10' onfocus='date()' type='date'></div>"
			+ "</div> <div class='edit_line '>"
			+ "<div class='resume-editLeft'><span>*</span>认缴金额：</div>"
			+ "<div class='resume-editRight'><input name='shouldcapi' value='' placeholder='请填写认缴金额' class='validate validateNum forminput1 ml10' type='text'><span style='margin-left: -25px;'>万</span></div>"
			+ "</div> <div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>实缴日期：</div>"
			+ "<div class='resume-editRight'><input name='realtime' value='' placeholder='请填写实缴日期' class='validate validateDate forminput1 ml10' onfocus='date()' type='date'></div>"
			+ "</div><div class='edit_line '>"
			+ "<div class='resume-editLeft'><span>*</span>实缴金额：</div>"
			+ "<div class='resume-editRight'><input name='realcapi' value='' placeholder='请填写实缴金额' class='validate validateNum forminput1 ml10' type='text'><span style='margin-left: -25px;'>万</span></div>"
			+ "</div><div class='edit_line '>"
			+ "<div class='resume-editLeft'><span>*</span>占股比例：</div>"
			+ "<div class='resume-editRight'><input name='stockpercent' value='' placeholder='请填写占股比例' class='validate validateNum validatePercentage forminput1 ml10' type='text'><span style='margin-left: -25px;'>%</span></div>"
			+ "</div><div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>";
	/* 新增高管模块 */
	var newExecutives = "<form><div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>高管姓名：</div>"
			+ "<div class='resume-editRight'><input name='name' value=''  placeholder='请填写高管姓名' class='validate forminput1 ml10' type='text'></div>"
			+ "</div> <div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>性别：</div>"
			+ "<div class='resume-editRight'>"
			+ "<input name = 'gender' value='男' type='hidden' class='radio'/>"
			+ "<div class='radiobox '>"
			+ "<div class='radioitem radioitemact'>男</div> <div class='radioitem'>女</div> </div> </div>"
			+ "</div> <div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>年龄：</div>"
			+ "<div class='resume-editRight'><input name='age' value='' placeholder='请填写年龄' class='validate validateNum forminput1 ml10' type='text'></div>"
			+ "</div> <div class='edit_line '>"
			+ "<div class='resume-editLeft'><span>*</span>职位：</div>"
			+ "<div class='resume-editRight'><input name='job' value='' placeholder='请填写职位' class='validate forminput1 ml10' type='text'></div>"
			+ "</div> <div class='edit_line'>"
			+ "<div class='resume-editLeft'><span>*</span>部门：</div>"
			+ "<div class='resume-editRight'><input name='department' value='' placeholder='请填写部门' class='validate forminput1 ml10' type='text'></div>"
			+ "</div><div class='edit_line'>"
			+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>学历：</div><div class='resume-editRight'><div class='divselect'>"
			+ "<input value='' name='education' readonly='readonly' placeholder='请选择学历' class='validate radio cite forminput1' type='text'>"
			+ "<ul style='display: none;'><li><a>博士及以上</a></li><li><a>硕士、研究生</a></li><li><a>本科</a></li><li><a>大专</a></li><li><a>中专</a></li><li><a>其他</a></li></ul>"
			+ "</div></div></div><div class='edit_line '>"
			+ "<div class='resume-editLeft'><span>*</span>工作经历：</div>"
			+ "<div class='resume-editRight'><textarea name='workexp' value='' placeholder='请填写工作经历' class='validate forminput1 ml10' style='width: 300px;height:100px' type='text'></textarea></div>"
			+ "</div><div class='edit_line '>"
			+ "<div class='resume-editLeft'>证件号：</div>"
			+ "<div class='resume-editRight'><input name='idcard' value='' placeholder='请填写证件号' class='forminput1 ml10' type='text'></div>"
			+ "</div><div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>";
});

/* 设置 右侧 状态菜单 的值 */
function listProcessState() {
	$.ajax({
				type : "post",
				url : _ctxPath + "/initEnterprise/listProcessState.do",
				success : function(msg) {
					var processState = msg.processState;
					var number = 0;
										
					// 是否申请报告
					if (processState.applyreportstate == 1) {
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
						//财务报表
						$("#Finance").children(".finance").attr("style","padding-top: 10px; padding-bottom: 10px; margin-left: 50px;").html("<div style = 'width: 80%;'> <span> 财务报表 ：已上传 <span class = 'glyphicon glyphicon-ok-sign' style = 'color: rgb(62, 182, 62); '></span></div>");
						$("#financialModal").remove();
						var str = "";
						if(msg.fileAllUploadName.length > 0 ){
							for ( var int = 0; int < msg.fileAllUploadName.length; int++) {
								str += "<div style = 'width: 80%; margin-top:10px;'> <span> "+msg.fileAllUploadName[int]+"：已上传 <span class = 'glyphicon glyphicon-ok-sign' style = 'color: rgb(62, 182, 62); '></span></div>"
							}
						}
						//上传
						$("#uploader").remove();
						$("#UploadFile").children(".form-item").html(str);
					} else {
						$(".headRarea").attr("class", "headRarea");
						$("[data-statue = false]").attr("class","resume-content ");
						$(".preview-resume").text("申请报告");
					}
					
					if(processState.baseinfostate==1){
						$("#EntBaseInfo").attr("data-statue", true);
					}else{
						$("#EntBaseInfo").attr("data-statue", false);
					}
					if(processState.shareholderstate==1){
						$("#Shareholder").attr("data-statue", true);
					}else{
						$("#Shareholder").attr("data-statue", false);
					}
					if(processState.executivesstate==1){
						$("#Executives").attr("data-statue", true);
					}else{
						$("#Executives").attr("data-statue", false);
					}
					if(processState.pushmodelstate==1){
						$("#CreditIndex").attr("data-statue", true);
					}else{
						$("#CreditIndex").attr("data-statue", false);
					}
					if(processState.financestate==1){
						$("#Finance").attr("data-statue", true);
					}else{
						$("#Finance").attr("data-statue", false);
					}
					if(processState.uploadfilestate==1){
						$("#UploadFile").attr("data-statue", true);
					}else{
						$("#UploadFile").attr("data-statue", false);
					}
					
					$("[data-statue = 'true']").each(function(){
						$("[href = '#"+$(this).attr("id")+"']").children("span:last").css("color", "#40b95e").text("已添加");
						if($(this).attr("id") == "EntBaseInfo"||$(this).attr("id") == "CreditIndex"){
							number = number + 30;
						}else{
							number = number + 10;
						}
					});
					$("[data-statue = 'false']").each(function(){
						$("[href = '#"+$(this).attr("id")+"']").children("span:last").css("color", "#999").text("未添加");
					});
				
					if(number >= 60 && processState.applyreportstate == 0){
						$(".preview-resume").attr("style","background-color:#00A0E9; color:#fff; border-radius: 5px;");
					}
					// 设置需要显示的分值
					$(".resume-Rarea").find(".progress-text").children("span").text(number);
					$(".progress-line").children("div").css("width",number+"%");
					
					
					//附加
					if(	$(".edit").length>0 && processState.applyreportstate == 1){
						refresh();
					}
				},
				error : function() {
					popup("网络连接失败，请稍后再试..");
				}
			});
}

/* ajax加载 企业信息表 */
function listEntBaseInfo(state) {
	
	$.ajax({
				type : "post",
				url : _ctxPath + "/initEnterprise/listEntBaseInfo.do",
				success : function(msg) {
					$(".modal-overlay").css("display","none");
					if (msg.sturts == "true") {
						var entBaseInfo = msg.entBaseInfo;
						if (entBaseInfo.setupdate != null) {
							entBaseInfo.setupdate = (new Date(entBaseInfo.setupdate)).Format("yyyy-MM-dd");
						}else{
							entBaseInfo.setupdate = null;
						}
						if (entBaseInfo.startdate != null) {
							entBaseInfo.startdate = (new Date(entBaseInfo.startdate)).Format("yyyy-MM-dd");
						}else{
							entBaseInfo.startdate = null;
						}
						if (entBaseInfo.issuedate != null) {
							entBaseInfo.issuedate = (new Date(entBaseInfo.issuedate)).Format("yyyy-MM-dd");
						}else{
							entBaseInfo.issuedate = null;
						}
						if (entBaseInfo.enddate != null) {
							entBaseInfo.enddate = (new Date(entBaseInfo.enddate)).Format("yyyy-MM-dd");
						}else{
							entBaseInfo.enddate = null;
						}
						if (state == "" || state == null) {
							/* 获取对象 */
							var info = $("#EntBaseInfo").children(".resume-browse-area");
							info.children().remove();
							
							var brief = isNull(entBaseInfo.brief);
							if(brief.length>13){
								brief = brief.slice(0,10)+"...";
							}
							var name = isNull(entBaseInfo.name);
							if(name.length>13){
								name = name.slice(0,10)+"...";
							}
							
							var regisorg = isNull( entBaseInfo.regisorg);
							if(regisorg.length>13){
								regisorg = regisorg.slice(0,10)+"...";
							}
							var address = isNull( entBaseInfo.address);
							if(address.length>13){
								address = address.slice(0,10)+"...";
							}
							var industry = isNull( entBaseInfo.industry);
							if(industry.length>13){
								industry = industry.slice(0,10)+"...";
							}
							var businessscope = isNull( entBaseInfo.businessscope);
							if(businessscope.length>13){
								businessscope = businessscope.slice(0,10)+"...";
							}
							
							info.append("<div class='browse-info'><input type = 'hidden' id = 'entUuid' value = '"+ entBaseInfo.uuid + "'>"
									+ "<ul><li><label>企业名称：</label><span >"+ name+ "</span></li>"
									+ "<li><label>公司性质：</label><span>"+isNull(entBaseInfo.enttype)+ "</span></li>"
									+ "<li><label>注册资本：</label><span>"+isNull( entBaseInfo.regicapital)+ "</span></li>"
									+ "<li><label>法人代表：</label><span>"+isNull( entBaseInfo.legalperson)+ "</span></li>"
									+ "<li><label>企业成立日期：</label><span>"+isNull( entBaseInfo.setupdate)+ "</span></li>"
									+ "<li><label>营业开始日期：</label><span>"+isNull( entBaseInfo.startdate)+ "</span></li>"
									+ "<li><label>联系电话：</label><span>"+isNull( entBaseInfo.tel)+ "</span></li>"
									+ "<li><label>企业官网：</label><span>"+isNull( entBaseInfo.website)+ "</span></li>"
									+ "<li><label>电子邮箱：</label><span>"+isNull( entBaseInfo.email)+ "</span></li>"
									+ "<li><label>人员规模：</label><span>"+isNull( entBaseInfo.scale)+ "</span></li>"
									+ "<li><label>企业简介：</label><span>"+brief+ "</span></li></ul>"
									
									+ "<ul><li><label>营业执照号：</label> <span>"+isNull( entBaseInfo.uscc)+ "</span><em data-telstatue='false' class='telstatue '>已绑定√</em></li>"
									+ "<li><label>企业地址：</label><span>"+address+ "</span></li>"
									+ "<li><label>币种：</label><span>"+isNull( entBaseInfo.currencytype)+ "</span></li>"
									+ "<li><label>登记机关：</label><span>"+regisorg+ "</span></li>"
									+ "<li><label>企业发照日期：</label><span>"+isNull( entBaseInfo.issuedate)+ "</span></li>"
									+ "<li><label>营业结束日期：</label><span>"+isNull( entBaseInfo.enddate)+ "</span></li>"
									+ "<li><label>企业传真：</label><span>"+isNull( entBaseInfo.fax)+ "</span></li>"
									+ "<li><label>地区邮编：</label><span>"+isNull( entBaseInfo.zipcode)+ "</span></li>"
									+ "<li><label>所属行业：</label><span>"+industry+ "</span></li>"
									+ "<li><label>经营范围：</label><span>"+businessscope+ "</span></li></ul></div>");
						} else {
							var info = $("#EntBaseInfo").children(".resume-edit-area");
							info.append("<form><input type='hidden' value='"+entBaseInfo.uuid+ "' name = 'uuid'><div class='edit_line'>"
									+ "<div class='resume-editLeft'><span>*</span>企业名称：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.name)+ "' name='name' placeholder='请填写企业名称' class='validate forminput1 ml10' type='text'></div>"
									+ "</div><div class='edit_line'><label></label>"
									+ "<div class='resume-editLeft'><span>*</span>营业执照号：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.uscc)+ "' name='uscc' readonly='readonly' placeholder='请填写营业执照号' class='validate validateCha forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line'>"
									+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>公司性质：</div><div class='resume-editRight'><div class='divselect'>"
									+ "<input value='"+isNull2( entBaseInfo.enttype)+ "' name='enttype' readonly='readonly' placeholder='请选择企业类型' class='validate radio cite forminput1'  type='text' >"
									+ "<ul style='display: none;'><li><a>上市企业</a></li><li><a>事业单位</a></li><li><a>国有企业</a></li><li><a>股份合作企业</a></li><li><a>私营企业</a></li><li><a>港、澳、台商投资(合资)企业</a></li><li><a>其他企业</a></li></ul>"
									+ "</div></div>"
									+ "</div><div class='edit_line'>"
									+ "<div class='resume-editLeft'><span>*</span>企业地址：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.address)+ "' name = 'address' placeholder='请填写企业地址' class='validate forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line'>"
									+ "<div class='resume-editLeft'><span>*</span>注册资本：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.regicapital)+ "' name='regicapital' placeholder='请填写注册资本' class='validate validateNum forminput1 ml10 ' name='registeredCapital' type='text'><span style='margin-left: -25px;'>万</span></div>"
									+ "</div><div class='edit_line'>"
									+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>币种：</div><div class='resume-editRight'><div class='divselect'>"
									+ "<input value='"+isNull2( entBaseInfo.currencytype)+ "' name='currencytype' readonly='readonly' placeholder='请选择币种' class='validate radio cite forminput1' type='text'>"
									+ "<ul style='display: none;'><li><a>人民币</a></li><li><a>美元</a></li><li><a>欧元</a></li><li><a>英镑</a></li></ul>"
									+ "</div></div></div>"
									+ "<div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>法人代表：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.legalperson)+ "' name = 'legalperson' placeholder='请填写法人代表' class='validate forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>登记机关：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.regisorg)+ "' name='regisorg' placeholder='请填写登记机关' class='validate validateChinese forminput1 ml10' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>企业成立日期：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.setupdate)+ "' name='setupdate' placeholder='请填写企业成立日期' onfocus='date()' class='validate validateDate forminput1 ml10' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>企业发照日期：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.issuedate)+ "' name = 'issuedate' placeholder='请填写企业发照日期' onfocus='date()' class='validate validateDate forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>营业开始日期：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.startdate)+ "' name = 'startdate' placeholder='请填写营业开始日期' onfocus='date()' class='validate validateDate forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>营业结束日期：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.enddate)+ "' name = 'enddate' placeholder='请填写营业结束日期' onfocus='date()' class='validate validateDate forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>联系电话：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.tel)+ "' name = 'tel' maxlength='13'  placeholder='请填写联系电话' class='validate validateTel forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>企业传真：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.fax)+ "' name = 'fax' placeholder='请填写企业传真' class='validate validateTel forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>企业官网：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.website)+ "' name = 'website' placeholder='请填写企业官网' class='validate validateWeb forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>地区邮编：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.zipcode)+ "' name = 'zipcode' maxlength='6' placeholder='请填写地区邮编'  class='validate validateNum forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>电子邮箱：</div>"
									+ "<div class='resume-editRight'><input value='"+isNull2( entBaseInfo.email)+ "' name = 'email' placeholder='请填写电子邮箱' class='validate validateEmail forminput1 ml10 ' type='text'></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>所属行业：</div>"
									+ "<div class='resume-editRight'>"
										+"<div><input id='industry' value='"+isNull2( entBaseInfo.industry)+ "' name = 'industry'  placeholder='请填写所属行业'  onclick='showMenu(); return false;' class='validate validateChinese forminput1 ml10 ' type='text'>" +
												"<input id='industrycode' value='"+isNull2( entBaseInfo.industrycode)+ "' name = 'industrycode' type='hidden'></div>" 
										+"<div id='menuContent' class='menuContent' style='display:none; position: absolute;z-index:1;'>"
											+"<ul id='treeDemo' class='ztree' style='margin-top:0px;margin-left0px; width:298px;border: 1px solid #ddd;background: #f9f9f9;overflow-x:auto;z-index:0;height:210px;'></ul>"
										+"</div>"
									+"</div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>人员规模：</div><div class='resume-editRight'><div class='divselect'>"
									+ "<input value='"+isNull2( entBaseInfo.scale)+ "' name='scale' readonly='readonly' placeholder='请填写人员规模' class='validate radio cite forminput1' type='text'>"
									+ "<ul style='display: none;'><li><a> 10 以下 </a></li><li><a> 10 ~ 49 </a></li><li><a> 50 ~ 99 </a></li><li><a> 100 ~ 300 </a></li><li><a> 300以上</a></li></ul>"
									+ "</div></div></div>"
									+"<div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>经营范围：</div>"
									+ "<div class='resume-editRight'><textarea name = 'businessscope' placeholder='请填写经营范围' class='validate forminput1 ml10 'style='width: 300px;height:100px'>"+isNull2( entBaseInfo.businessscope)+ "</textarea></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'>企业简介：</div>"
									+ "<div class='resume-editRight'><textarea name = 'brief' placeholder='请填写企业简介' class=' forminput1 ml10 'style='width: 300px;height:100px' type='text'>"+isNull2( entBaseInfo.brief)+ "</textarea></div>"
									+ "</div><div class='save'>保存</div></form>");
						}
					} else {
						popup(msg.msg);
					}

				},
				error : function() {
					popup("网络连接失败，请稍后再试..");
				}
			});
}

/*行业 列表*/

var setting = {
		view: {
			dblClickExpand: true,//双击节点时，是否自动展开父节点的标识
			showLine: true,//是否显示节点之间的连线
			selectedMulti: false //设置是否允许同时选中多个节点
		},
		data: {
			key: {
				name: "industry"
			},
			simpleData: {
				enable: true,
				idKey: "code", // id编号命名
                pIdKey: "parentcode", // 父id编号命名   
			}
		},
		callback: {//设置事件
			beforeClick: beforeClick,
			onClick: onClick
		}
};

function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check) return false;
}
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	nodes = zTree.getSelectedNodes(),
	name = "";
	value="";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		name += nodes[i].industry + ",";
		value += nodes[i].code + ",";
	}
	if (name.length > 0 ) name = name.substring(0, name.length-1);
	var industry = $("#industry");
	industry.attr("value", name).attr("data-test","true").css("border", "1px #E3E3E3 solid").nextAll("label").remove();
	if (value.length > 0 ) value = value.substring(0, value.length-1);
	var industrycode = $("#industrycode");
	industrycode.attr("value", value).nextAll("label").remove();
	hideMenu();
}
function showMenu() {
	$.ajax({
		type: "POST",
	    url: _ctxPath + "/initEnterprise/findRootName.do",
	    dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
	    success: function (data) {
	        $.fn.zTree.init($("#treeDemo"), setting, data.list);
	    }
	});
	var industry = $("#industry");
	var cityOffset = $("#industry").offset();
	$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + industry.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
/*行业 列表 end*/
function listCreditIndex(state) {
	$.ajax({
				type : "post",
				url : _ctxPath + "/initEnterprise/listCreditIndex.do",
				success : function(msg) {
					if (msg.sturts == "true") {
						var indexs = msg.indexs;
						if (state == "" || state == null) {
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
						} else {
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
						}
					} else {
						popup(msg.msg);
					}

				},
				error : function() {
					popup("网络连接失败，请稍后再试..");
				}
			});
}
function listShareholder(uuid) {
	$.ajax({
				type : "post",
				data : {
					shareholderUuid : uuid
				},
				url : _ctxPath + "/initEnterprise/listShareholder.do",
				success : function(msg) {
					if (msg.sturts == "true") {
						if (msg.edit == "false") {
							/* 获取 集合 界面 */
							var shareholder = msg.shareholderList;
							if (shareholder != null && shareholder.length > 0) {
								// 隐藏添加界面
								$("#Shareholder").attr("data-statue", true).children(".addmorebox").css("display","none");
								/* 获取 展示 对象 */
								var info = $("#Shareholder").children(".resume-browse-area");
								info.css("display", "block").children().remove();
								info.append("<div class='browse-info1 layout2-item' data-id=''><ul>"
												+ "<li class='layout2-item1'><span>姓名</span></li>"
												+ "<li class='layout2-item1'><span>类型</span> </li>"
												+ "<li class='layout2-item1'><span>认缴时间</span></li>"
												+ "<li class='layout2-item1'><span>认缴金额</span></li>"
												+ "<li class='layout2-item1'><span>实缴时间</span></li>"
												+ "<li class='layout2-item1'><span>实缴金额</span></li>"
												+ "<li class='layout2-item1'><span>占股比例</span></li></ul></div>");
								for ( var i = 0; i < shareholder.length; i++) {
									if (shareholder[i].shouldtime != null) {
										shareholder[i].shouldtime = (new Date(shareholder[i].shouldtime)).Format("yyyy-MM-dd");
									}else{
										shareholder[i].shouldtimedate = null;
									}
									if (shareholder[i].realtime != null) {
										shareholder[i].realtime = (new Date(shareholder[i].realtime)).Format("yyyy-MM-dd");
									}else{
										shareholder[i].realtime = null;
									}
									info.append("<div class='browse-info2' data-id='Shareholder"+ (i + 1)+ "'>"
													+ "<div class='layout2-item' >"
													+ "<input type='hidden' name='uuid' value='"+ shareholder[i].uuid+ "' /><ul>"
													+ "<li class='layout2-item3'><span>"+isNull( shareholder[i].name)+ "</span></li>"
													+ "<li class='layout2-item3'><span>"+isNull( shareholder[i].type)+ "</span> </li>"
													+ "<li class='layout2-item3'><span>"+isNull( shareholder[i].shouldtime)+ "</span></li>"
													+ "<li class='layout2-item3'><span>"+isNull( shareholder[i].shouldcapi)+ " 万</span></li>"
													+ "<li class='layout2-item3'><span>"+isNull( shareholder[i].realtime)+ "</span></li>"
													+ "<li class='layout2-item3'><span>"+isNull( shareholder[i].realcapi)+ " 万</span></li>"
													+ "<li class='layout2-item3'><span>"+isNull( shareholder[i].stockpercent)+ " %</span></li>"
													+ "</ul><p class='item-edit2 edit'>编辑</p></div></div>");
								}
							} else {
								$("#Shareholder").attr("data-statue", false).children(".addmorebox").css("display","block");
								$("#Shareholder").children(".resume-browse-area").css("display","none");
							}
						} else {
							var shareholder = msg.shareholder;
							if (shareholder.shouldtime != null) {
								shareholder.shouldtime = (new Date(shareholder.shouldtime)).Format("yyyy-MM-dd");
							}else{
								shareholder.shouldtimedate = null;
							}
							if (shareholder.realtime != null) {
								shareholder.realtime = (new Date(shareholder.realtime)).Format("yyyy-MM-dd");
							}else{
								shareholder.realtime = null;
							}
							var ty = "";
							if(shareholder.type == "法人"){
								ty = "<div class='radioitem '>自然人</div> <div class='radioitem radioitemact'>法人</div> "
							}else{
								ty = "<div class='radioitem radioitemact'>自然人</div> <div class='radioitem'>法人</div> "
								
							}
							/* 进入 单条信息编辑界面 */
							$("#Shareholder").attr("edit-statue", true).find(".resume-edit").text("取消");
							$("#Shareholder").children(".resume-browse-area").css("display", "none");
							$("#Shareholder").children(".resume-edit-area").css("display", "block").children().remove();
							$("#Shareholder").children(".resume-edit-area").append("<form><input type='hidden' name='uuid' value='"+ shareholder.uuid+ "' /><div class='edit_line'>"
													+ "<div class='resume-editLeft'><span>*</span>股东姓名：</div>"
													+ "<div class='resume-editRight'><input name='name' value='"+isNull2( shareholder.name)+ "'  placeholder='请填写股东姓名' class='validate forminput1 ml10' type='text'></div>"
													+ "</div> <div class='edit_line'>"
													+ "<div class='resume-editLeft'><span>*</span>股东类型：</div>"
													+ "<div class='resume-editRight'>"
													+ "<input name = 'type' value='"+isNull2( shareholder.type)+ "' type='hidden' class='radio'/>"
													+ "<div class='radiobox job-natruedata'>"+ty+" </div></div>"
													+ "</div> <div class='edit_line'>"
													+ "<div class='resume-editLeft'><span>*</span>认缴日期：</div>"
													+ "<div class='resume-editRight'><input name='shouldtime' value='"+ isNull2(shareholder.shouldtime)+ "' placeholder='请填写认缴日期' class='validate validateDate forminput1 ml10' onfocus='date()' type='date'></div>"
													+ "</div> <div class='edit_line '>"
													+ "<div class='resume-editLeft'><span>*</span>认缴金额：</div>"
													+ "<div class='resume-editRight'><input name='shouldcapi' value='"+isNull2( shareholder.shouldcapi)+ "' placeholder='请填写认缴金额' class='validate validateNum forminput1 ml10' type='text'><span style='margin-left: -25px;'>万</span></div>"
													+ "</div> <div class='edit_line'>"
													+ "<div class='resume-editLeft'><span>*</span>实缴日期：</div>"
													+ "<div class='resume-editRight'><input name='realtime' value='"+isNull2( shareholder.realtime)+ "' placeholder='请填写实缴日期' class='validate validateDate forminput1 ml10' onfocus='date()' type='date'></div>"
													+ "</div><div class='edit_line '>"
													+ "<div class='resume-editLeft'><span>*</span>实缴金额：</div>"
													+ "<div class='resume-editRight'><input name='realcapi' value='"+isNull2( shareholder.realcapi)+ "' placeholder='请填写实缴金额' class='validate validateNum forminput1 ml10' type='text'><span style='margin-left: -25px;'>万</span></div>"
													+ "</div><div class='edit_line '>"
													+ "<div class='resume-editLeft'><span>*</span>占股比例：</div>"
													+ "<div class='resume-editRight'><input name='stockpercent' value='"+isNull2( shareholder.stockpercent)+ "' placeholder='请填写占股比例' class='validate validateNum validatePercentage forminput1 ml10' type='text'><span style='margin-left: -25px;'>%</span></div>"
													+ "</div><div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>");
						}
					} else {
						popup(msg.msg);
					}
				},
				error : function() {
					popup("网络连接失败，请稍后再试..");
					if (ses == null)
						window.location = _ctxPath;
				}
			});
}

function deleteShareholder(uuid) {
	$.ajax({
		type : "post",
		data : {
			shareholderUuid : uuid
		},
		url : _ctxPath + "/initEnterprise/deleteShareholder.do",
		success : function(msg) {
			if (msg.sturts == "true") {
				popup("删除成功");
			}
		},
	});
};

function listExecutives(uuid) {
	$.ajax({
				type : "post",
				data : {
					executivesUuid : uuid
				},
				url : _ctxPath + "/initEnterprise/listExecutives.do",
				success : function(msg) {
					if (msg.sturts == "true") {
						if (msg.edit == "false") {
							/* 获取 集合 界面 */
							var executives = msg.executivesList;
							if (executives != null && executives.length > 0) {
								// 隐藏添加界面
								$("#Executives").attr("data-statue", true).children(".addmorebox").css("display","none");
								/* 获取 展示 对象 */
								var info = $("#Executives").children(".resume-browse-area");
								info.css("display", "block").children().remove();
								info.append("<div class='browse-info1 layout2-item' data-id=''><ul>"
										+ "<li class='layout2-item1'><span>姓名</span></li>"
										+ "<li class='layout2-item1'><span>性别</span> </li>"
										+ "<li class='layout2-item1'><span>年龄</span></li>"
										+ "<li class='layout2-item1'><span>职位</span></li>"
										+ "<li class='layout2-item1'><span>部门</span></li>"
										+ "<li class='layout2-item1'><span>学历</span></li></ul></div>");
								for ( var i = 0; i < executives.length; i++) {
									info.append("<div class='browse-info2' data-id='Executives"+ (i + 1)+ "'>"
													+ "<div class='layout2-item' >"
													+ "<input type='hidden' name='uuid' value='"+executives[i].uuid+ "' /><ul>"
													+ "<li class='layout2-item3'><span>"+isNull( executives[i].name)+ "</span></li>"
													+ "<li class='layout2-item3'><span>"+isNull( executives[i].gender)+ "</span> </li>"
													+ "<li class='layout2-item3'><span>"+isNull( executives[i].age)+ "</span></li>"
													+ "<li class='layout2-item3'><span>"+isNull( executives[i].job)+ " </span></li>"
													+ "<li class='layout2-item3'><span>"+isNull( executives[i].department)+ "</span></li>"
													+ "<li class='layout2-item3'><span>"+isNull( executives[i].education)+ " </span></li>"
													+ "</ul><p class='item-edit2 edit'>编辑</p></div></div>");
								}
							} else {
								$("#Executives").attr("data-statue", false).children(".addmorebox").css("display","block");
								$("#Executives").children(".resume-browse-area").css("display","none");
							}
						} else {
							var executives = msg.executives;
							if(executives.idcard == null) executives.idcard = "";
							/* 进入 单条信息编辑界面 */
							$("#Executives").attr("edit-statue", true).find(".resume-edit").text("取消");
							$("#Executives").children(".resume-browse-area").css("display", "none");
							$("#Executives").children(".resume-edit-area").css("display", "block").children().remove();
							var gen = "";
							if(executives.gender == "女"){
								gen = "<div class='radioitem '>男</div> <div class='radioitem radioitemact'>女</div>";
							}else{
								gen = "<div class='radioitem radioitemact'>男</div> <div class='radioitem'>女</div>";
							}
							$("#Executives").children(".resume-edit-area").append("<form><input type='hidden' name='uuid' value='"+ executives.uuid+ "' /><div class='edit_line'>"
									+ "<div class='resume-editLeft'><span>*</span>高管姓名：</div>"
									+ "<div class='resume-editRight'><input name='name' value='"+isNull2(executives.name)+"'  placeholder='请填写高管姓名' class='validate forminput1 ml10' type='text'></div>"
									+ "</div> <div class='edit_line'>"
									+ "<div class='resume-editLeft'><span>*</span>性别：</div>"
									+ "<div class='resume-editRight'>"
									+ "<input name = 'gender' value='"+isNull2(executives.gender)+"' type='hidden' class='radio'/>"
									+ "<div class='radiobox '>"+gen+ " </div> </div>"
									+ "</div> <div class='edit_line'>"
									+ "<div class='resume-editLeft'><span>*</span>年龄：</div>"
									+ "<div class='resume-editRight'><input name='age' value='"+isNull2(executives.age)+"' placeholder='请填写年龄' class='validate validateNum forminput1 ml10' type='text'></div>"
									+ "</div> <div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>职位：</div>"
									+ "<div class='resume-editRight'><input name='job' value='"+isNull2(executives.job)+"' placeholder='请填写职位' class='validate forminput1 ml10' type='text'></div>"
									+ "</div> <div class='edit_line'>"
									+ "<div class='resume-editLeft'><span>*</span>部门：</div>"
									+ "<div class='resume-editRight'><input name='department' value='"+isNull2(executives.department)+"' placeholder='请填写部门' class='validate forminput1 ml10' type='text'></div>"
									+ "</div><div class='edit_line'>"
									+ "<div class='resume-editLeft' style='margin-top: 10px;'><span>*</span>学历：</div><div class='resume-editRight'><div class='divselect'>"
									+ "<input value='"+isNull2(executives.education)+"' name='education' readonly='readonly' placeholder='请选择学历' class='validate radio cite forminput1' type='text'>"
									+ "<ul style='display: none;'><li><a>博士及以上</a></li><li><a>硕士、研究生</a></li><li><a>本科</a></li><li><a>大专</a></li><li><a>中专</a></li><li><a>其他</a></li></ul>"
									+ "</div></div></div><div class='edit_line '>"
									+ "<div class='resume-editLeft'><span>*</span>工作经历：</div>"
									+ "<div class='resume-editRight'><textarea name='workexp' style='width: 300px;height:100px'placeholder='请填写工作经历' class='validate forminput1 ml10' type='text'>"+isNull2(executives.workexp)+"</textarea></div>"
									+ "</div><div class='edit_line '>"
									+ "<div class='resume-editLeft'>证件号：</div>"
									+ "<div class='resume-editRight'><input name='idcard' value='"+isNull2(executives.idcard)+"' placeholder='请填写证件号' class='forminput1 ml10' type='text'></div>"
									+ "</div><div class = 'operatebox'><span class='save save2'>保存</span><span class='delbt'>删除</span></div></form>");
						}
					} else {
						popup(msg.msg);
					}
				},
				error : function() {
					popup("网络连接失败，请稍后再试..");
					if (ses == null)
						window.location = _ctxPath;
				}
			});
}

function deleteExecutives(uuid) {
	$.ajax({
		type : "post",
		data : {
			executivesUuid : uuid
		},
		url : _ctxPath + "/initEnterprise/deleteExecutives.do",
		success : function(msg) {
			if (msg.sturts == "true") {
				popup("删除成功");
			}else{
				popup(msg.msg);
			}
		},
	});
};
/* 附件列表 */
function listUploadFile() {
	$.ajax({
				type : "post",
				url : _ctxPath + "/initEnterprise/listUploadFile.do",
				success : function(msg) {
					if(msg.sturts=="true"){
						var categorys=msg.categorys;
						var info = $("#UploadFile").find(".dream-controls");
						info.children().remove();
						info.append("<div class='ui-radio' id='radio'></div>");
						var radio="";
						for(var i in categorys){
							if(categorys[i].ismust==1){
								radio+="<li><input type='radio' name='type' id='"+categorys[i].uuid+"' value='"+categorys[i].uuid+"'/>"
								+"<span class='radio-label'><label class='radio' for='"+categorys[i].uuid+"'>"	      
							    +"</label><label for='"+categorys[i].uuid+"'>"+categorys[i].name+"<em style='color:#ff6a0e'>*</em></label></span> </li>";
							}else{
								radio+="<li><input type='radio' name='type' id='"+categorys[i].uuid+"' value='"+categorys[i].uuid+"'/>"
								+"<span class='radio-label'><label class='radio' for='"+categorys[i].uuid+"'>"	      
							    +"</label><label for='"+categorys[i].uuid+"'>"+categorys[i].name+"</label></span></li>";
							}
						}
						if(document.getElementById('radio') != null){
							document.getElementById('radio').innerHTML = radio;
						}
					}else{
						popup(msg.msg);
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
		url : _ctxPath + "/initEnterprise/judgeMustFile.do",
		success : function(msg) {
			if(msg.sturts=="true"){
				$("#UploadFile").attr("data-statue", true);
				refresh();
			}else{
				popup("部分文件未上传，请检查！");
			}
			$(".tip").text("提示："+msg.msg);
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
	var name=$(this).next("span").children("label:last").text();
	var uuid=$(this).val();
	getUploadFile(name,uuid);
});
/*获取已上传附件内容*/
function getUploadFile(name,uuid){
	$.ajax({
		type : "post",
		url : _ctxPath + "/initEnterprise/getUploadFile.do?fileID="+uuid,
		success : function(msg) {
			if(msg.sturts=="true"){
				$("#uploader").css("display","none");
				var files=msg.files;
				var info = $("#UploadFile").find("#uploadFileList");
				info.children().remove();
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
				popup(msg.msg);
				$("#UploadFile").find("#uploadFileList").children().remove();
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
			url : _ctxPath + "/initEnterprise/deleteUploadFile.do?uuid="+uuid,
			success : function(msg) {
				if(msg.sturts=="true"){
					getUploadFile(msg.name,msg.fileID);
				}
				popup(msg.msg);
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
	$("#UploadFile").find("#uploadFileList").children().remove();
	$("#uploader").attr("style","position: static; opacity: 1;");
	$("#title").text(name);	
	$("#title").attr("data-value",uuid);	
	$("#filePicker").find("div:last").css("width","100%").css("left","0px");
	$("#filePicker").closeUploader();
}
/*判断财务模板是否存在，如果存在则下载模板*/
$("#download").click(function() {
	$.ajax({
		type : "post",
		url : _ctxPath + "/initEnterprise/judgeExecl.do",
		success : function(msg) {
			if(msg.sturts=="true"){
				window.location.href = _ctxPath+"/initEnterprise/downloadFinance.do";
			}else{
				popup(msg.msg);
			}
			
		},
		error : function() {
			popup("网络连接失败，请稍后再试..");
		}
	});
});
/*判断财务数据是否已上传*/
function judgeFinance(){
	$.ajax({
		type : "post",
		url : _ctxPath + "/initEnterprise/judgeFinance.do",
		success : function(msg) {
			if(msg.sturts=="true"){
				$("#picker").find(".webuploader-pick").text("选择财务报表(已上传)");
			}
		},
		error : function() {
			popup("网络连接失败，请稍后再试..");
		}
	});
}
/*校检财务数据*/
$("#check").click(function() {
	$.ajax({
		type : "post",
		url : _ctxPath + "/initEnterprise/checkExecl.do",
		success : function(msg) {
			if(msg.sturts=="true"){
				$("#financialModalBody .tab-content").remove();
				$("#financialModalBody").append(msg.excelhtml);
				$("#assetsLiabilityDiv").tab('show');//初始化显示哪个tab
				$("#assetsLiability").addClass("active");
				$("#Finance").attr("data-statue", true);
				refresh();
			}else{
				popup(msg.msg);
			}
			
		},
		error : function() {
			popup("网络连接失败，请稍后再试..");
		}
	});
});
function refresh(modelRoot) {
	/**   后续增加功能 需添加 startup 单击保存时 调用数据执行异步刷新*/
	if(modelRoot == null){
		listEntBaseInfo(null);
		listShareholder();
		listExecutives();
		listCreditIndex(null);
		listUploadFile();
		judgeFinance();
	}else{
		var id = modelRoot.attr("id");
		//比较块   然后执行异步刷新
		if (id == "EntBaseInfo") {
			listEntBaseInfo(null);
		}
		if (id == "Shareholder") {
			listShareholder();
		}
		if (id == "Executives") {
			listExecutives();
		}
		if (id == "CreditIndex") {
			listCreditIndex(null);
		}
		if (id == "UploadFile") {
			listUploadFile();
		}
	}
	
	
	/**   后续增加功能 需添加 end*/
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
function date(){
	WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true});
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
	if(param == null){
		param = "";
	}
	return param;
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
