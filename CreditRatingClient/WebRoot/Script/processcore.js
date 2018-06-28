/**
 * Created by Hzhu on 2015/8/24.
 */
/**
 * 根据不同的formId判断不同的操作
 * @param formId
 */
function section1Edit(formId) {
    var inputItems = $("#"+formId+" :input");
    inputItems.each(function(){
        if($(this).attr("name")!="name"&&$(this).attr("name")!="businessNo"&&$(this).attr("name")!="industry"){
			$(this).removeAttr("readonly");
			$(this).removeAttr("disabled");
		}
    });
    $("#"+formId).prev().find("a").removeClass("icon18 iconModify");
    /*$("#section-1:first-child>h3>a").removeClass("icon18 iconModify");*/
    $("#"+formId+" .btn-success[type='submit']").show();
    $("#"+formId+" .btn-success[type='submit']").removeClass("disabled").removeAttr("disabled");
	$("#"+formId+" .btn-warning").show();
};
/**
 * 暂无用处，后期在整改
 * @param {Object} formId
 */
function closeBaseInfoBtn(formId){
	var inputItems = $("#"+formId).find("input,textarea,select");
    inputItems.each(function(){
        $(this).attr("readonly","readOnly");
		$(this).attr("disabled","disabled");
    });
    $("#"+formId).find("div").removeClass("has-success");
    $("#"+formId).prev().find("a").addClass("icon18 iconModify");
    $("#"+formId+" .btn-success[type='submit']").hide();
	$("#"+formId+" .btn-warning").hide();
};

function saveBaseInfoBtn(formId,commonInfoIcon){
    var checkSubmitFlg = false; //防止重复提交
	$("#"+formId).on('success.form.fv', function(e) {
        if (!checkSubmitFlg) {
			checkSubmitFlg = true;
			// Prevent form submission
	        e.preventDefault();
	        // Get the form instance
	        var $form = $(e.target);
	        // Get the FormValidation instance
	        var bv = $form.data('formValidation');
	        // Use Ajax to submit form data
	        if(formId=="baseInfoForm"){
				$.post(_ctxPath+"/tbEnterpriseInfo/updateTbEnterpriseInfo.do", $form.serialize(), function(result) {
		            //成功后的操作
			        saveBaseSuccess(formId,commonInfoIcon);
	       		}, 'json');
			}else if(formId=="basicAccountForm"){
				$.post(_ctxPath+"/tbAccount/updateOrAddAccount.do", $form.serialize(), function(result) {
		            //成功后的操作
			        saveBaseSuccess(formId,commonInfoIcon);
	       		}, 'json');
			}else if(formId=="creditForm"){
				$.post(_ctxPath+"/tbExtraIndexOper/saveOrUpdateOpera.do", $form.serialize(), function(result) {
					 //成功后的操作
			        saveBaseSuccess(formId,commonInfoIcon);
	       		}, 'json');
			}
		}
    });
};

$(".studyUnit").hover(function(){
    $(this).addClass("studyUnitHover");
},function(){
    $(this).removeClass("studyUnitHover");
});
/**
 * 列表形式页面的操作通用的编辑方法
 * @param showPulibcInfoId
 * @param editPublicInfoId
 * @param showPublicInfoSpanId
 * @param publicInfoForm
 */
function editCommonInfo(showCommonInfoId,editCommonInfoId,showCommonInfoSpanId,commonInfoForm){
    $("#"+editCommonInfoId).removeAttr("hidden");
    $("#"+showCommonInfoId).attr("hidden","hidden");
    /*$("#"+showShareholderInfoId).addClass("studyUnitHover");*/
    $("#"+showCommonInfoId).parent().unbind('mouseenter').unbind('mouseleave');
	$("#"+commonInfoForm+" .btn-success[type='submit']").css("display","inline");
    /*给input赋值*/
    var spanVal = $("#"+showCommonInfoSpanId).find("span");
    var formInput = $("#"+commonInfoForm).find("input,textarea,select");
    spanVal.each(function(){
        var obj = this;
        formInput.each(function(){
            if($(obj).attr("name")==$(this).attr("name")){
				$(this).val($(obj).text().replaceAll("%","").replaceAll("万元",""));
            }
        });
    });
}
/**
 * 
 * @param {Object} identifier
 * @param {Object} showCommonInfoId
 * @param {Object} editCommonInfoId
 * @param {Object} showCommonInfoSpanId
 * @param {Object} commonInfoForm
 */
function saveCommonInfo(identifier,showCommonInfoId,editCommonInfoId,showCommonInfoSpanId,commonInfoForm){
	var checkSubmitFlg = false; //防止重复提交
	//$("#"+commonInfoForm+" .btn-success[type='submit']").text("提交中");
	$("#"+commonInfoForm).on('success.form.fv', function(e) {
		if(!checkSubmitFlg){
			checkSubmitFlg = true;
	        /*去掉button的disable*/
	        $("#"+commonInfoForm+" .btn-success[type='submit']").removeClass("disabled").removeAttr("disabled");
	        // Prevent form submission
	        e.preventDefault();
	        // Get the form instance
	        var $form = $(e.target);
	        // Get the FormValidation instance
	        var bv = $form.data('formValidation');
			if(identifier=="section-2"){
				// Use Ajax to submit form data
				$.post(_ctxPath+"/tbShareholderInfo/updateOrAddShareholderInfo.do", $form.serialize(), function(result) {
		          	saveSuccess(result.id,showCommonInfoId,editCommonInfoId,showCommonInfoSpanId,commonInfoForm,'shareholderInfoIcon');
		        }, 'json');
			}else if(identifier=="section-3"){
				// Use Ajax to submit form data
		        $.post(_ctxPath+"/tbMainPersonInfo/updateOrAddMainpersonInfo.do", $form.serialize(), function(result) {
		          	saveSuccess(result.id,showCommonInfoId,editCommonInfoId,showCommonInfoSpanId,commonInfoForm,'mainpersonInfoIcon');
		        }, 'json');
			}else if(identifier=="section-5"){
				// Use Ajax to submit form data
		        $.post(_ctxPath+"/tbMainaccount/updateOrAddMainaccount.do", $form.serialize(), function(result) {
		          	saveSuccess(result.id,showCommonInfoId,editCommonInfoId,showCommonInfoSpanId,commonInfoForm,'mainaccountsIcon');
		        }, 'json');
			}
		}
	});
}
/**
 * 删除数据的公用方法
 * @param identifier 标示符判断列表数据属于某个具体信息比如股东信息，主要人员信息等等
 * @param showShareholderInfoId
 * @param showShareholderInfoSpan
 */
function deleteCommonInfo(identifier,showCommonInfoId,showCommonInfoSpan,commonModal,commonModalDiv){
    /*获取id*/
    var id = $("#"+showCommonInfoSpan+">span[name='id']").text();
   
    if(identifier=="section-2"){/*股东信息删除动作*/
		$.post(_ctxPath+"/tbShareholderInfo/deleteShareholderInfo.do", {id:id}, function(result) {
			deleteSuccess(identifier,showCommonInfoId,showCommonInfoSpan,'shareholderInfoIcon');
		});
    }else if(identifier=="section-3"){/*主要人员信息删除动作*/
		$.post(_ctxPath+"/tbMainPersonInfo/deleteMainpersonInfo.do", {id:id}, function(result) {
			deleteSuccess(identifier,showCommonInfoId,showCommonInfoSpan,'mainpersonInfoIcon');
		});
    }else if(identifier=="section-5"){/*主要结算信息删除动作*/
		$.post(_ctxPath+"/tbMainaccount/deleteMainaccount.do", {id:id}, function(result) {
			deleteSuccess(identifier,showCommonInfoId,showCommonInfoSpan,'mainpersonInfoIcon',commonModal,commonModalDiv);
		});
	}
}

function addShareholderInfo(){
    /*获取页面当前记录数*/
	$("#addShareholderInfo").find("p").css('display','none');
 	var len = $("#section-2").find(".studyUnit").length;
    if(len==1){
		$("#addShareholderInfo").prev().after($("#standardShareholderInfoModel").html().replaceAll("Standard",len+"").replaceAll('<div class="lineSolid"></div>',""));
	}else{
		$("#addShareholderInfo").prev().after($("#standardShareholderInfoModel").html().replaceAll("Standard",len+""));
	}
	$("#shareholderInfoForm"+len+" .btn-success[type='submit']").css("display","inline");
	$('#shareholderInfoForm'+len)
        .formValidation({
            message: 'This value is not valid',
            /* icon: {
             valid: 'glyphicon glyphicon-ok',
             invalid: 'glyphicon glyphicon-remove',
             validating: 'glyphicon glyphicon-refresh'
             },*/
            fields: {
               shareholdername: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '必填项'
                        },
                        stringLength: {
                            min: 2,
                            max: 30,
                            message: '至少二个字符'
                        },
                        regexp: {
                            regexp: /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/,
                            message: '输入字符非法,请检查后重新输入'
                        }
                    }
                },
                shareholdertype: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '必填项'
                        }
                    }
                },
				investment: {
                    message: 'The username is not valid',
                    validators: {
						 regexp: {
                            regexp: /^[1-9]\d*$|^\d+\.\d+$/,
                            message: '只能输入数字或者小数'
                        }
                    }
                },
				stake: {
                    message: 'The username is not valid',
                    validators: {
						 regexp: {
                            regexp: /^[1-9]\d*$|^\d+\.\d+$/,
                            message: '只能输入数字或者小数'
                        }
                    }
                }
            }
        });
        /*.on('success.form.fv', function(e) {
            // Prevent form submission
            e.preventDefault();
            // Get the form instance
            var $form = $(e.target);

            // Get the FormValidation instance
            var bv = $form.data('formValidation');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');
        });*/
}

function addMainpersonInfo(){
    /*获取页面当前记录数*/
    $("#addMainpersonInfo").find("p").css('display','none');
    var len = $("#section-3").find(".studyUnit").length;
    if(len==1){
		$("#addMainpersonInfo").prev().after($("#standardMainpersonInfoModel").html().replaceAll("Standard",len+"").replaceAll('<div class="lineSolid"></div>',""));
	}else{
		$("#addMainpersonInfo").prev().after($("#standardMainpersonInfoModel").html().replaceAll("Standard",len+""));
	}
	$("#mainpersonInfoForm"+len+" .btn-success[type='submit']").css("display","inline");
	$(".form_datetime").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒 
		format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 
		language: 'zh-CN', //汉化 
		autoclose:true //选择日期后自动关闭 
     });
	$('#mainpersonInfoForm'+len)
        .formValidation({
            message: 'This value is not valid',
            /* icon: {
             valid: 'glyphicon glyphicon-ok',
             invalid: 'glyphicon glyphicon-remove',
             validating: 'glyphicon glyphicon-refresh'
             },*/
            fields: {
                name: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '必填项'
                        },
                        stringLength: {
                            min: 2,
                            max: 30,
                            message: '至少两个字符'
                        },
                        /*remote: {
                         url: 'remote.php',
                         message: 'The username is not available'
                         },*/
                        regexp: {
                            regexp: /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/,
                            message: '输入字符非法,请检查后重新输入'
                        }
                    }
                },
                position: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '必填项'
                        }
                    }
                },
                sex: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '必填项'
                        }
                    }
                },
                cardno: {
                    message: 'The username is not valid',
                    validators: {
                        regexp: {
                            regexp: /^(\d{18,18}|\d{15,15}|\d{17,17}X)$/,
                            message: '请入正确的身份证号码'
                        }
                    }
                },
            }
        });
    /*.on('success.form.fv', function(e) {
     // Prevent form submission
     e.preventDefault();
     // Get the form instance
     var $form = $(e.target);

     // Get the FormValidation instance
     var bv = $form.data('formValidation');

     // Use Ajax to submit form data
     $.post($form.attr('action'), $form.serialize(), function(result) {
     console.log(result);
     }, 'json');
     });*/
}

function addMainaccounts(){
    /*获取页面当前记录数*/
	$("#addMainaccounts").find("p").css('display','none');
 	var len = $("#section-5").find(".studyUnit").length;
    if(len==1){
		$("#addMainaccounts").prev().after($("#standardMainaccountsModel").html().replaceAll("Standard",len+"").replaceAll('<div class="lineSolid"></div>',""));
	}else{
		$("#addMainaccounts").prev().after($("#standardMainaccountsModel").html().replaceAll("Standard",len+""));
	}
	$("#mainaccountsForm"+len+" .btn-success[type='submit']").css("display","inline");
    $('#mainaccountsForm'+len)
        .formValidation({
            message: 'This value is not valid',
            /* icon: {
             valid: 'glyphicon glyphicon-ok',
             invalid: 'glyphicon glyphicon-remove',
             validating: 'glyphicon glyphicon-refresh'
             },*/
             fields: {
                settleaccontname: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '必填项'
                        },
                        stringLength: {
                            min: 2,
                            max: 30,
                            message: '至少两个字符'
                        },
                        /*remote: {
                         url: 'remote.php',
                         message: 'The username is not available'
                         },*/
                        regexp: {
                            regexp: /^[\u4e00-\u9fa5(（）)a-zA-Z0-9]+$/,
                            message: '输入字符非法,请检查后重新输入'
                        }
                    }
                },
                settleaccontaddr: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '必填项'
                        }
                    }
                },
                settleacconttel: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '必填项'
                        },
                        regexp: {
                            regexp: /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8}|\d{3})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/,
                            message: '请输入正确的电话,如:0920-29392929、12345678901、1234-12345678-1234'
                        }
                    }
                }
            }
        });
    /*.on('success.form.fv', function(e) {
     // Prevent form submission
     e.preventDefault();
     // Get the form instance
     var $form = $(e.target);

     // Get the FormValidation instance
     var bv = $form.data('formValidation');

     // Use Ajax to submit form data
     $.post($form.attr('action'), $form.serialize(), function(result) {
     console.log(result);
     }, 'json');
     });*/
}
function saveBaseSuccess(formId,commonInfoIcon){
	var inputItems = $("#"+formId).find("input,textarea,select");
    inputItems.each(function(){
        $(this).attr("readonly","readOnly");
		$(this).attr("disabled","disabled");
    });
    $("#"+formId).find("div").removeClass("has-success");
    $("#"+formId).prev().find("a").addClass("icon18 iconModify");
    $("#"+formId+" .btn-success[type='submit']").hide();
	$("#"+formId+" .btn-warning").hide();
	if(!$("#"+commonInfoIcon).hasClass("sdbRightIconY")){
		$("#"+commonInfoIcon).removeClass("sdbRightIconN");
    	$("#"+commonInfoIcon).addClass("sdbRightIconY");
	}
};

function saveSuccess(id,showCommonInfoId,editCommonInfoId,showCommonInfoSpanId,commonInfoForm,commonInfoIcon){
	 /*成功保存数据后执行操作*/
	if(!$("#"+commonInfoIcon).hasClass("sdbRightIconY")){
		$("#"+commonInfoIcon).removeClass("sdbRightIconN");
		$("#"+commonInfoIcon).addClass("sdbRightIconY");
	}
    $("#"+editCommonInfoId).attr("hidden","hidden");
    $("#"+showCommonInfoId).removeAttr("hidden");
    $("#"+showCommonInfoId).parent().removeClass("studyUnitHover");
    $("#"+editCommonInfoId).parent().bind({
        mouseenter: function (e) {
            $(this).addClass("studyUnitHover");
        }, mouseleave: function (e) {
            $(this).removeClass("studyUnitHover");
        }
    });
    /*给span赋值*/
    var spanVal = $("#"+showCommonInfoSpanId).find("span");
    var formInput = $("#"+commonInfoForm).find("input,textarea,select");
    formInput.each(function(){
        var obj = this;
        spanVal.each(function(){
			if($(obj).attr("name")==$(this).attr("name")){
                if($(obj).attr("name")=="id"){
					if(id==null){
						$(this).text($(obj).val());
					}else{
						$(this).text(id);
					}
				}else{
					if($(obj).attr("name")=="investment"){
						$(this).text($(obj).val()+"万元");
					}else if($(obj).attr("name")=="stake"){
						$(this).text($(obj).val()+"%");
					}else{
						$(this).text($(obj).val());
					}
				}
            }
        });
    });
}
function deleteSuccess(identifier,showCommonInfoId,showCommonInfoSpan,commonInfoIcon,commonModal,commonModalDiv){
	$("#"+commonModal).modal('hide');
	$("#"+commonModalDiv).remove();
	/*数据删除成功后的操作*/
	var currentLen = parseInt(showCommonInfoId.substr(showCommonInfoId.length-1,showCommonInfoId.length));
    var len = $("#"+identifier).find('.studyUnit').length;
    /**
     * len等于2的时候，什么都不删除，增加提示信息
     * len大于2的时候且len等于currentLen+1时候，不删除下一个，删除上一个
     * len大于2的时候且len不等于currentLen+时候，删除下一个，删除上一个
     */
    if(len==2){
		$("#"+showCommonInfoId).parent().nextAll(".textNull").find("p").css('display','block');
		$("#"+commonInfoIcon).removeClass("sdbRightIconY");
		$("#"+commonInfoIcon).addClass("sdbRightIconN");
    }else if(len>2){
        if($("#"+showCommonInfoId).parent().nextAll(".lineSolid").length==0){
			$("#"+showCommonInfoId).parent().prevAll(".lineSolid").eq(0).remove();
		}
		$("#"+showCommonInfoId).parent().nextAll(".lineSolid").eq(0).remove();
    }
    $("#"+showCommonInfoId).parent().remove();
}

function downFileFinancialModel(){
	window.open(_ctxPath+"/tbEnterpriseInastmt/downLoadFinancialStatementsModel.do");
	return;
}

function changeShwoOperScanFile(obj){
	//删除初始化的提示
	$("#controlSelectOption").remove();
	$("#controlScanFileShow").removeAttr("hidden");
	$("#SWFUpload_1").css('display','inline');
	var scanfileId = $(obj).val().split("#")[0];
	var type = $(obj).val().split("#")[1];
	$.ajax({
		type: "post",
		url: _ctxPath+"/tbOperaScanFile/selectScanFile.do",
		data:{scanfileId:scanfileId},
		success: function(data, textStatus){
			if("1"==type){
				upload2.setFileQueueLimit(0);
				upload2.setFileUploadLimit(0);
			}else{
				upload2.setFileQueueLimit(1);
				upload2.setFileUploadLimit(1);
			}
			upload2.resetUploadedCount();
			upload2.setPostParams({"scanfileIdAndtype": $(obj).val()});
			$("#fsUploadProgress2>div[class='progressWrapper']").remove();
			upload2.plusUploadedCount(data.tbOperaScanFiles.length);
			$.each(data.tbOperaScanFiles,function(n,tbOperaScanFile){
				function file(id,name,size){ 
					var o = {
						id: id,
						name: name,
						size:size
					};
					return o;  
				};
				var fileObj = new file(tbOperaScanFile.id,tbOperaScanFile.filename,tbOperaScanFile.filesize);
				var fileId = fileObj.id;
				var progress = new FileProgress(fileObj,"fsUploadProgress2");
				progress.setComplete();
				var fileSize = fileObj.size;
		       	var i = 0;
		       	while(fileSize>1024&&i<2){
		       		fileSize = fileSize/1024;
		       		i++;
		       	}
		       	var sizeDW;
		       	if(i==0)
		       		sizeDW = "B";
		       	else if(i==1)
		       		sizeDW = "K";
		       	else if(i==2)
		       		sizeDW = "M";
				progress.setStatus(toDecimal2(fileSize)+sizeDW+"  上传完成");
				progress.toggleCancel(false);
				var filelist = document.getElementById("progressContainer"+fileId);
				filelist.style.paddingLeft="50px";
				filelist.style.background="#F0F5FF url('../resources/plug/swfUpload/file-icon/"+fileObj.name.substring(fileObj.name.indexOf(".")+1)+".png') no-repeat 5px 5px";
				//alert(filelist.style.background);
				if(data.tbOperaProccess.scanstatus!=1){
					//删除附件按钮
					var deleteObject = document.createElement("a");
					deleteObject.className = "myProgressBt";
					deleteObject.href = "javascript:void(0);";
					deleteObject.appendChild(document.createTextNode("删除"));
					deleteObject.onclick = function(){
						//this.parentNode.parentNode.style.display='none';
						//var progress = new FileProgress(fileObj, this.customSettings.progressTarget);
						$.ajax({
							data : {fileId:fileId},
							type: "post",
							url: _ctxPath+"/tbOperaScanFile/deleteScanFile.do",
							success: function(data, textStatus){
								//this.parentNode.parentNode.style.display='none';
								//var progress = new FileProgress(fileObj, this.customSettings.progressTarget);
								if(data.initFile==true){
									$("#scanFileIcon").removeClass("sdbRightIconN");
					    			$("#scanFileIcon").addClass("sdbRightIconY");
								}else{
									$("#scanFileIcon").removeClass("sdbRightIconY");
					    			$("#scanFileIcon").addClass("sdbRightIconN");
								}
								progress.setDelete();
								upload2.subUploadedCount(1);
								var idObj = document.getElementById("arrId"+fileId);
								idObj.parentNode.removeChild(idObj);
							}
						});
					};								
					filelist.appendChild(deleteObject);
				}else{
					$("#SWFUpload_1").css('display','none');
				}
				//action 返回的附件ID
				var idObj = document.createElement("input");
				idObj.setAttribute("type","hidden"); 
				idObj.setAttribute("name","arrId");
				idObj.setAttribute("id","arrId"+fileId);
				filelist.appendChild(idObj);
				var clearObj = document.createElement("div");
				clearObj.className = "clear";	
				filelist.appendChild(clearObj);
				//alert(fileObj.value);
			});
		},
		error : function(s,t,g){
			
		}
	});
}

function confirmFinancestatus(){
	$.ajax({
		type: "post",
		url: _ctxPath+"/tbEnterpriseInastmt/confirmFinancestatus.do",
		success: function(data, textStatus){
			$("#financialModal").modal('hide');
			alert(data.msg);
		}
	});
}
function confirmScanFile(){
	$.ajax({
		type: "post",
		url: _ctxPath+"/tbOperaScanFile/judgeMustFile.do",
		success: function(data, textStatus){
			alert(data.msg);
		}
	});
}
function subApplyReport(){
	var judge = true;
	var str = "";
	$.each($("#processNav").find("i"),function(index,obj){
		if($(obj).hasClass('sdbRightIconN')){
			judge = false;
			str += $(obj).parent().text().trim()+",";
		}
	});
	if(judge){
		$.ajax({
			url:_ctxPath+"/tbOpeprocess/applyReport.do",
			type:"post",
			success:function(data){
				if(data.status==true){
					alert("您已成功申请评分报告,请在两个工作日后查看结果");
					$("#subApplyReportBtn").attr("disabled", true);
					$("#subApplyReportBtn").text("已经提交申请");
					$("#basicInfoHref").removeClass("icon18");
					$("#section-2").find("a").removeClass("icon18");
					$("#section-3").find("a").removeClass("icon18");
					$("#basicAccountHref").removeClass("icon18");
					$("#section-5").find("a").removeClass("icon18");
					$("#creditHref").removeClass("icon18");
					$("object[id*='SWFUpload']").css('display','none');
					$("#financialModalConfirmBtn").hide();
					$("#confirmScanFile").attr("disabled",true); 
					$(".ctnAdd").css('display','none');
				}
			}
		});
	}else{
		$("#subApplyReportTipContent").text(str+"请完善这些信息后在提交报告");
		$("#subApplyReportTip").modal('show');
	}
}
String.prototype.replaceAll = function(s1,s2) {
    return this.replace(new RegExp(s1,"gm"),s2);
};
(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};  
        var array=this.serializeArray();  
        var str=this.serialize();  
        $(array).each(function(){  
            if(serializeObj[this.name]){  
                if($.isArray(serializeObj[this.name])){  
                    serializeObj[this.name].push(this.value);  
                }else{  
                    serializeObj[this.name]=[serializeObj[this.name],this.value];  
                }  
            }else{  
                serializeObj[this.name]=this.value;   
            }  
        });  
        return serializeObj;  
    };  
})(jQuery);

function toDecimal2(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return false;  
    }  
    var f = Math.round(x*100)/100;  
    var s = f.toString();  
    var rs = s.indexOf('.');  
    if (rs < 0) {  
        rs = s.length;  
        s += '.';  
    }  
    while (s.length <= rs + 2) {  
        s += '0';  
    }  
    return s;  
 }
$(document).ready(function() {
	$.ajax({
	type: "post",
	url: _ctxPath+"/tbEnterpriseInastmt/initFormUpload.do",
	success: function(data, textStatus){
			if(data.tbEnterpriseInastmt!=null){
				//$("#SWFUpload_0").css('display','inline');
				$("#financialModalBody .tab-content").remove();
				$("#financialModalBody").append(data.excelhtml);
				$("#assetsLiabilityDiv").tab('show');//初始化显示哪个tab
				$("#assetsLiability").addClass("active");
				$("#confirmFianal").removeAttr("disabled");
				$("#financeIcon").removeClass("sdbRightIconN");
    			$("#financeIcon").addClass("sdbRightIconY");
				function file(id,name,size){ 
					var o = {
						id: id,
						name: name,
						size:size
					};
					return o;  
				}; 
				var fileObj = new file(data.tbEnterpriseInastmt.id,data.tbEnterpriseInastmt.uploadfilename,data.tbEnterpriseInastmt.uploadsequence);
				$("#fsUploadProgress1>div[class='progressWrapper']").remove();
				var fileId = fileObj.id;
				var progress = new FileProgress(fileObj,"fsUploadProgress1");
				progress.setComplete();
				var fileSize = fileObj.size;
		       	var i = 0;
		       	while(fileSize>1024&&i<2){
		       		fileSize = fileSize/1024;
		       		i++;
		       	}
		       	var sizeDW;
		       	if(i==0)
		       		sizeDW = "B";
		       	else if(i==1)
		       		sizeDW = "K";
		       	else if(i==2)
		       		sizeDW = "M";
				progress.setStatus(toDecimal2(fileSize)+sizeDW+"  上传完成");
				progress.toggleCancel(false);
				var filelist = document.getElementById("progressContainer"+fileId);
				filelist.style.paddingLeft="50px";
				filelist.style.background="#F0F5FF url('../resources/plug/swfUpload/file-icon/"+fileObj.name.substring(fileObj.name.indexOf(".")+1)+".png') no-repeat 5px 5px";
				//alert(filelist.style.background);
				//删除附件按钮
				//alert(filelist.style.background);
				if(data.tbOperaProccess.financestatus!=1){
					var deleteObject = document.createElement("a");
					deleteObject.className = "myProgressBt";
					deleteObject.href = "javascript:void(0);";
					deleteObject.appendChild(document.createTextNode("删除"));
					deleteObject.onclick = function(){
						$.ajax({
							type: "post",
							url: _ctxPath+"/tbEnterpriseInastmt/deleteFormUpload.do",
							success: function(data, textStatus){
								//this.parentNode.parentNode.style.display='none';
								//var progress = new FileProgress(fileObj, this.customSettings.progressTarget);
								$("#financialModalBody .tab-content").remove();
								$("#financeIcon").removeClass("sdbRightIconY");
	    						$("#financeIcon").addClass("sdbRightIconN");
								$("#confirmFianal").attr("disabled","disabled"); 
								progress.setDelete();
								var idObj = document.getElementById("arrId"+fileId);
								idObj.parentNode.removeChild(idObj);
							}
						});
					};								
					filelist.appendChild(deleteObject);
				}
				//action 返回的附件ID
				var idObj = document.createElement("input");
				idObj.setAttribute("type","hidden"); 
				idObj.setAttribute("name","arrId");
				idObj.setAttribute("id","arrId"+fileId);
				filelist.appendChild(idObj);
				var clearObj = document.createElement("div");
				clearObj.className = "clear";	
				filelist.appendChild(clearObj);
				//alert(fileObj.value);
			}else{
				 $("#financeIcon").removeClass("sdbRightIconY");
    			 $("#financeIcon").addClass("sdbRightIconN");
			}
		
		},
		error: function(){
		
		}
	});
	
	$.ajax({
		type: "post",
		url: _ctxPath+"/tbOperaScanFile/initJudge.do",
		success: function(data, textStatus){
			if(data.initFile==true){
				$("#scanFileIcon").removeClass("sdbRightIconN");
    			$("#scanFileIcon").addClass("sdbRightIconY");
			}else{
				$("#scanFileIcon").removeClass("sdbRightIconY");
    			$("#scanFileIcon").addClass("sdbRightIconN");
			}
		}
	});
	
	$.ajax({
		type: "post",
		url: _ctxPath+"/initProccess/initExtraData.do",
		success: function(data, textStatus){
			if(data.msg!=null&&""!=data.msg.trim()){
				alert(data.msg);
			}else{
				var strHtml = '';
			$.each(data.extraIndexVos,function(index,obj){
				strHtml += '<input name="tbExtraIndexOpers['+index+'].indexchinese" type=hidden value="'+obj.indexChinese+'"/>';
				strHtml += '<input name="tbExtraIndexOpers['+index+'].indexid" type=hidden value="'+obj.indexId+'"/>';
				if(index%2==0){
					strHtml += '<div class="form-group" style="margin-right: -10px;margin-left: -60px;">';
				}
				strHtml += '<label  class="col-md-2 control-label labelSamllcustomized">'+obj.indexChinese+'</label>';
				if(obj.operType == 1){
					strHtml += '<div class="col-md-4">';
					strHtml += '<select class="form-control selectchosen" type="text" name="tbExtraIndexOpers['+index+
					'].fillincontent"  disabled required data-fv-notempty-message="必选项">';
					$.each(obj.optionRecord.split("♠"),function(strindex,str){
						if(str.split("♥")[0]==obj.fillInContent){
							strHtml += '<option selected="selected">'+str.split("♥")[0]+'</option> ';
						}else{
							strHtml += '<option>'+str.split("♥")[0]+'</option> ';
						}
					});
					strHtml += '</select>';
					strHtml += '</div>';
				}
				
				if(obj.operType == 0){
					if(obj.operWriteType==0){
						strHtml += '<div class="col-md-4"><input class="form-control" type="text" name="tbExtraIndexOpers['+index
						+'].fillincontent" disabled value="'+obj.fillInContent+'" required data-fv-notempty-message="必选项"pattern="^[0-9]*$|^[0-9]+[.][0-9]+$" data-fv-regexp-message="必须输入数字或小数"/></div>';
					}else{
						strHtml += '<div class="col-md-4"><input class="form-control" type="text" name="tbExtraIndexOpers['+index
						+'].fillincontent" disabled value="'+obj.fillInContent+'" required data-fv-notempty-message="必选项"/></div>';
					}
				}
				if(index%2==1){
					strHtml += '</div>';
				}
			});
			$("#creditForm").prepend(strHtml);
			$('#creditForm').formValidation();
			}
		}
	});
	$.ajax({
		url:_ctxPath+"/tbOpeprocess/init.do",
		type:"post",
		success:function(data){
			if(data.tbOperaProccess.applyreportstatus==1){
				$("#subApplyReportBtn").attr("disabled", true);
				$("#subApplyReportBtn").text("已经提交申请");
				$("#basicInfoHref").hide();
				$("#section-2").find("a").removeClass("icon18");
				$("#section-3").find("a").removeClass("icon18");
				$("#basicAccountHref").hide();
				$("#section-5").find("a").removeClass("icon18");
				$("#creditHref").hide();
				$("object[id*='SWFUpload']").css('display','none');
				$("#financialModalConfirmBtn").hide();
				$("#confirmScanFile").attr("disabled",true);
				$(".ctnAdd").css('display','none');
				$(".myProgressBt").css('display','none');
			}//暂时屏蔽掉
			/**else{
				if(data.tbOperaProccess.confirmstatus==1){
					$("#basicInfoHref").hide();
				}
				if(data.tbOperaProccess.shareholderstatus==1){
					$("#section-2").find("a").removeClass("icon18");
					$("#section-2 .ctnAdd").css('display','none');
				}
				if(data.tbOperaProccess.mainpersonstatus==1){
					$("#section-3").find("a").removeClass("icon18");
					$("#section-3 .ctnAdd").css('display','none');
				}
				if(data.tbOperaProccess.baseaccountstatus==1){
					$("#basicAccountHref").hide();
				}
				if(data.tbOperaProccess.mainaccountstatus==1){
					$("#section-5").find("a").removeClass("icon18");
					$("#section-5 .ctnAdd").css('display','none');
				}
				if(data.tbOperaProccess.extraindexstatus==1){
					$("#creditHref").hide();
				}
				if(data.tbOperaProccess.financestatus==1){
					$("#SWFUpload_0").css('display','none');
					$("#financialModalConfirmBtn").hide();
				}
				if(data.tbOperaProccess.scanstatus==1){
					$("#confirmScanFile").attr("disabled",true);
				}
			}**/
		}
	});
});


