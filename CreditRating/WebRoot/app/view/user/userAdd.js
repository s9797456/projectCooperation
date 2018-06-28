Ext.apply(Ext.form.VTypes, {
	repetition : function(val, field) { // 返回true，则验证通过，否则验证失败
		if (field.repetition) { // 如果表单有使用repetition配置，repetition配置是一个JSON对象，该对象提供了一个名为targetCmpId的字段，该字段指定了需要进行比较的另一个组件ID。
			var cmp = Ext.getCmp(field.repetition.targetCmpId); // 通过targetCmpId的字段查找组件
			if (Ext.isEmpty(cmp)) { // 如果组件（表单）不存在，提示错误
				Ext.MessageBox.show({
					title : '错误',
					msg : '发生异常错误，指定的组件未找到',
					icon : Ext.Msg.ERROR,
					buttons : Ext.Msg.OK
				});
				return false;
			}
			if (val == cmp.getValue()) { // 取得目标组件（表单）的值，与宿主表单的值进行比较。
				return true;
			} else {
				return false;
			}
		}
	},
	repetitionText : '两次密码输入不一致！',
});
Ext.define("PM.view.user.userAdd",{
	extend : 'Ext.window.Window',
	alias:"widget.userAdd",
	layout : 'anchor',	
	border : 0,
	title:'新增',
	iconCls:'Useradd',
	width : 300,
	height : 335,
	modal : true,
	autoShow : true,
	defaults:{
		allowBlank:false,
		msgTarget:"side",
		selectOnFocus:true,
		labelAlign:"left",
		labelWidth:80,
		width:300
	},
	initComponent : function() {
		Ext.apply(this, {
			items:[{
				border:false,
				padding:20,
				bodyStyle:'margin-top:15px',
				itemId:'userAddForm',
				xtype : 'form',
				items:[{
					fieldLabel:"登录帐号",
					allowBlank:false,
					blankText:"用户名不能为空",
					minLength :4,
					maxLength:18,
					width:250,
					height: 25,
					name : 'userName',
					xtype:'textfield',
					validateOnBlur: true,
                    validationDelay:2000,
                    validateOnChange: false,
                     //登录名验证
                    validator: function (value) {
                    	var error=true;
                    	if(value.length>0){
	                        if (!/^[A-Za-z0-9_]+$/.test(value)) {
	                            return "只允许输入英文字母、数字和下划线等字符！";
	                        }
	                        var validator = this;
	                        var error = true;
	                        Ext.Ajax.request({
	                            async: false,
	                            scope: validator,
	                            url: _ctxPath+'/control/user/valid/usernameExist.do',
	                            params: { username: this.value },
	                            method: 'POST',
	                            success: function (response) {
	                                var result = Ext.JSON.decode(response.responseText);
	                                if (result.status==false) {
				    					error = result.msg;
				    				};
	                            }
	                        });
	                        return error;
	                    }else{
	                    	return error;
	                    }
                    },
                    listeners: {
                        focus: {
                            fn: function () { this.clearInvalid(); }
                        }
                    }
				},{
					xtype: 'textfield',
				    allowBlank: false,
				    height: 25,
				    width:250,
				    fieldLabel: '登录密码',
				    allowBlank:false,
					blankText:"登录密码不能为空",
                    validateOnChange: false,
					minLength :5,
					maxLength:18,
				    inputType:'password',
				    name : 'password',
				    id:'pass1'
				},{
					xtype: 'textfield',
				    allowBlank: false,
				    height: 25,
				    width:250,
				    fieldLabel: '确认密码',
				    inputType:'password',
                    validateOnChange: false,
				    name : 'password2',
				    minLength :5,
					maxLength:18,
					allowBlank:false,
					blankText:"确认密码不能为空",
					vtype:"repetition",
					repetition: { targetCmpId: 'pass1' }  //配置repetition验证，提供目标组件（表单）ID  
				},{
					xtype: 'textfield',
				    allowBlank: false,
				    height: 25,
				    width:250,
				    fieldLabel: '真实姓名',
                    validateOnChange: false,
				    name : 'realName',
				    regex: /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/ ,
					regexText:'不是真实姓名，请重新输入(不能小于两个字符且不能出现数字或其他符号)！'
				}/*,{
					xtype:"radiogroup",
					fieldLabel:"性别",
					columns:2,
                    validateOnChange: false,
					height: 25,
				    width:250,
					items:[{
						border:false,
						xtype:"radiofield",
						boxLabel:'男',
				        name:'gender',
				        checked:true,
				        inputValue: 'MAN'
						},{
							border:false,
						xtype:"radiofield",
						boxLabel:'女',
				        name:'gender',
				        inputValue: 'WOMEN'
					}]
				}*/,{
					border:false,
					xtype: 'textfield',
				    height: 25,
				    width:250,
				    fieldLabel: '电话号码',
				    name : 'cellphone',
				    validateOnBlur: true,
				    validationDelay:2000,
				    validateOnChange: false,
				    validator: function (value) {
				    	var error=true;
				    	if(value.length>0){
				    		if (!/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(value)) {
				    			return "电话号码格式不正确！";
				    		}
				    		var validator = this;
				    		var error = true;
				    		Ext.Ajax.request({
				    			async: false,
				    			scope: validator,
				    			url: _ctxPath+'/control/user/valid/cellphoneExist.do',
				    			params: { 
				    				cellphone: this.value,
				    			},
				    			method: 'POST',
				    			success: function (response) {
				    				var result = Ext.JSON.decode(response.responseText);
				    				if (result.status==false) {
				    					error = result.msg;
				    				};
				    			},
				    		});
				    		return error;
				    	}else{
				    		return error;
				    	}
				    } ,
				    listeners: {
				    	focus: function(){
				    		this.clearInvalid();
				    	}
				    }
				},{
					border:false,
					xtype: 'textfield',
				    height: 25,
				    width:250,
				    fieldLabel: '电子邮件',
				    name : 'email',
				    validateOnBlur: true,
				    validationDelay:2000,
				    validateOnChange: false,
				    validator: function (value) {
				    	var error=true;
				    	if(value.length>0){
				    		if (!/^\s*\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*(\;\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*(\;)*\s*$/.test(value)) {
				    			return "电子邮件格式不正确！";
				    		}
				    		var validator = this;
				    		var error = true;
				    		Ext.Ajax.request({
				    			async: false,
				    			scope: validator,
				    			url: _ctxPath+'/control/user/valid/emailExist.do',
				    			params: { 
				    				email: this.value,
				    			},
				    			method: 'POST',
				    			success: function (response) {
				    				var result = Ext.JSON.decode(response.responseText);
				    				if (result.status==false) {
				    					error = result.msg;
				    				};
				    			},
				    		});
				    		return error;
				    	}else{
				    		return error;
				    	}
				    } ,
				    listeners: {
				    	focus: function(){
				    		this.clearInvalid();
				    	}
				    }
				}]
			}],
			buttons:[{
				action:'saveUser',
			    text : '保存'
			},{
			    text : '关闭',
			    scope:this,
			    handler:this.close
			}]
		});
		this.callParent(arguments);
	}
});

