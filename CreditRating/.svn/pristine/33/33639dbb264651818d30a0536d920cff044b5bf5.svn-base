Ext.define('PM.view.organization.organizationAdd',{
	extend:'Ext.window.Window' ,
	alias : 'widget.organizationAdd',
	layout: 'anchor',
	id : 'organizationAdd',
	border : false,
	width : 425,
	height : 525,
	autoScroll:true,
	modal : true,
	autoShow : true,
	maximizable: true,
	title:"新增",
	iconCls:'organizationAdd',
	initComponent : function() {
		Ext.apply(this, {
			items:[{
				border:false,
				margin:'10 10 10 10',
				itemId:'addOrganizationForm',
				xtype : 'form',
				layout: 'column',
				defaults : {
					xtype : 'textfield',
					margin : '8',
					labelSeparator : "：",
					labelAlign : 'center',
					labelWidth:80,
					width:300
				},
		        items :[{
		        		fieldLabel : "机构名称<font style = 'color:red'>&nbsp;&nbsp;*</font>",
		        		allowBlank : false,
		        		minLength :2,
		        		maxLength:18,
		        		width:280,
						height: 25,
						blankText:'机构名称不能为空',
						emptyText: '请输入机构名称',
						name:'name',
						xtype:'textfield',
						validateOnBlur: true,
						validationDelay:2000,
						validateOnChange: false,
	                	//机构名验证
						validator: function (value) {
                    	var error=true;
                    	if(value.length>0){
	                        var validator = this;
	                        var error = true;
	                        Ext.Ajax.request({
	                            async: false,
	                            scope: validator,
	                            url: _ctxPath+'/control/organization/valid/orgNameExist.do',
	                            params: { name: this.value },
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
		        	allowBlank : false,
					name:'twoDomainNames',
					width:280,
					height: 25,
					minLength :6,
					emptyText: '请输入机构域名',
					fieldLabel : "机构域名<font style = 'color:red'>&nbsp;&nbsp;*</font>",
					validateOnBlur: true,
				    validationDelay:2000,
				    validateOnChange: false,
				    validator: function (value) {
				    	var error=true;
				    	if(value.length>0){
				    		if (!/^([\w-]+(\.[\w-]+)+(\.[\w-]+))?$/.test(value)) {
				    			return "机构域名格式不正确!";
				    		}
				    		var validator = this;
				    		var error = true;
				    		Ext.Ajax.request({
				    			async: false,
				    			scope: validator,
				    			url: _ctxPath+'/control/organization/valid/orgTwoDomainNamesExist.do',
				    			params: { 
				    				twoDomainNames: this.value,
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
					xtype: 'textfield',
		        	allowBlank : false,
					name:'userName',
					width:280,
					height: 25,
					minLength :4,
					maxLength:18,
					emptyText: '请输入登陆账号',
					fieldLabel : "登陆账号<font style = 'color:red'>&nbsp;&nbsp;*</font>",
					validateOnBlur: true,
				    validationDelay:2000,
				    validateOnChange: false,
				    validator: function (value) {
				    	var error=true;
				    	if(value.length>0){
				    		if (!/^.[A-Za-z0-9]+$/.test(value)) {
				    			return "登陆账号格式不正确！只能输入英文、数字";
				    		}
				    		var validator = this;
				    		var error = true;
				    		Ext.Ajax.request({
				    			async: false,
				    			scope: validator,
				    			url: _ctxPath+'/control/organization/valid/orgUserNameExist.do',
				    			params: { 
				    				userName: this.value,
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
					xtype: 'textfield',
		        	allowBlank : false,
					name:'password',
					id:'password',
					width:280,
					height: 25,
					minLength :6,
					maxLength:18,
					inputType: 'password',    //密码
					fieldLabel : "登陆密码<font style = 'color:red'>&nbsp;&nbsp;*</font>",
					emptyText: '请输入登陆密码',
					validateOnBlur: true,
				    validationDelay:2000,
				    validateOnChange: false,
				    validator: function (value) {
				    	var error=true;
				    	if(value.length>0){
				    		if (!/^.[A-Za-z0-9]+$/.test(value)) {
				    			return "密码格式不正确！只能输入英文、数字 ";
				    		}
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
					xtype: 'textfield',
		        	allowBlank : false,
					width:280,
					height: 25,
					minLength :2,
					maxLength:18,
					inputType: 'password',    //密码
					emptyText: '请输入确认密码',
					fieldLabel : "确认密码<font style = 'color:red'>&nbsp;&nbsp;*</font>",
					validateOnBlur: true,
				    validationDelay:2000,
				    validateOnChange: false,
				    validator: function (value) {
				    	var error=true;
				    	if(value.length>0){
				    		var pass = Ext.getCmp('password').getValue();
				    		if (pass != value) {
				    			return "密码不一致！";
				    		}
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
		        	allowBlank : false,
					xtype: 'textfield',
					width:280,
					height: 25,
				    fieldLabel: "手机号码<font style = 'color:red'>&nbsp;&nbsp;*</font>",
					emptyText: '请输入联系方式--可作为登陆帐号',
				    name : 'phone',
				    validateOnBlur: true,
				    validationDelay:2000,
				    validateOnChange: false,
				    validator: function (value) {
				    	var error=true;
				    	if(value.length>0){
				    		if (!/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(value)) {
				    			return "号码格式不正确！";
				    		}
				    		var validator = this;
				    		var error = true;
				    		Ext.Ajax.request({
				    			async: false,
				    			scope: validator,
				    			url: _ctxPath+'/control/organization/valid/orgPhoneExist.do',
				    			params: { 
				    				phone: this.value,
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
		        	allowBlank : true,
					xtype: 'textfield',
					width:280,
					height: 25,
				    fieldLabel: "电子邮箱",
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
				    			url: _ctxPath+'/control/organization/valid/orgEmailExist.do',
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
				},{ 
					xtype: "combobox",
					allowBlank : false,
		            name: "type",
		            fieldLabel: "机构类型<font style = 'color:red'>&nbsp;&nbsp;*</font>",
		            store: typeStore,
		            editable: false,
		            width:280,
					height: 25,
		            displayField: "Name",
		            valueField: "Value",
		            emptyText: "--请选择--",
		            queryMode: "local"
            	},{
		        	allowBlank : true,
					name:'orgUrl',
					minLength :6,
					maxLength:18,
					width:280,
					height: 25,
					fieldLabel : '机构官网',
					validateOnBlur: true,
				    validationDelay:2000,
				    validateOnChange: false,
				    validator: function (value) {
				    	var error=true;
				    	if(value.length>0){
				    		if (!/^([\w-]+(\.[\w-]+)+(\.[\w-]+))?$/.test(value)) {
				    			return "网址格式不正确！";
				    		}
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
		        	allowBlank : true,
					name:'description',
					minLength :2,
					width:280,
					height: 25,
					fieldLabel : '机构描述'
		        }]
			}],
			buttons:[{
				action : 'saveOrg',
				text : '保存'
			},{
				action : 'close',
				text : '关闭',
				scope:this,
				handler:this.close
			}]
		});
		this.callParent(arguments);
	}

});

var typeStore = Ext.create("Ext.data.Store", {
    fields: ["Name", "Value"],
    data: [
        { Name: "企业级机构", Value: 1 },
        { Name: "政府级机构", Value: 2 }
    ]
});