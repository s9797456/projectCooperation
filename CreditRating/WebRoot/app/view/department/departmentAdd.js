Ext.define('PM.view.department.departmentAdd',{
	extend:'Ext.window.Window' ,
	alias : 'widget.departmentAdd',
	layout: 'anchor',
	id : 'departmentAdd',
	border : false,
	width  : 350,
	height : 450,
	autoScroll:true,
	modal : true,
	autoShow : true,
	maximizable: true,
	title:"新增",
	iconCls:'departmentAdd',
	initComponent : function() {
		Ext.apply(this, {
			items:[{
				border:false,
				margin:'10 10 10 10',
				itemId:'addDepartmentForm',
				//bodyStyle:'background:#ffc;',
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
		        	xtype : 'hidden',
					name:'parentID',
					id:'parentID',
					fieldLabel:'上级部门的主键'
		        	},{
					fieldLabel : '部门名称',
					allowBlank : false,
					minLength :2,
					maxLength:18,
					width:280,
					height: 25,
					blankText:'部门名不能为空',
					name:'name',
					xtype:'textfield',
					validateOnBlur: true,
	                validationDelay:2000,
	                validateOnChange: false,
                     //部门名验证
                    validator: function (value) {
                    	var error=true;
                    if(value.length>0){
                        var validator = this;
                        var error = true;
                        Ext.Ajax.request({
                            async: false,
                            scope: validator,
                            url: _ctxPath+'/control/department/valid/depNameExist.do',
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
		        	allowBlank : true,
					name:'sn',
					width:280,
					height: 25,
					minLength :2,
					maxLength:18,
					fieldLabel : '部门编码'
		        },{
		        	allowBlank : true,
					name:'description',
					minLength :2,
					width:280,
					height: 25,
					fieldLabel : '部门描述'
		        },{
					border:false,
		        	allowBlank : true,
					xtype: 'textfield',
					width:280,
					height: 25,
				    fieldLabel: '手机',
				    name : 'phone',
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
				    			url: _ctxPath+'/control/department/valid/depPhoneExist.do',
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
		        	allowBlank : true,
					name:'fax',
					minLength :4,
					maxLength:18,
					width:280,
					height: 25,
					fieldLabel : '传真'
		        },{
					border:false,
		        	allowBlank : true,
					xtype: 'textfield',
					width:280,
					height: 25,
				    fieldLabel: '邮箱',
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
				    			url: _ctxPath+'/control/department/valid/depEmailExist.do',
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
				action : 'saveDep',
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