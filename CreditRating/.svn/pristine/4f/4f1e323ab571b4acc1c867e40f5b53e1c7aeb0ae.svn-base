Ext.QuickTips.init();// 错误悬浮提示
Ext.define('PM.view.department.departmentEdit',{
	extend:'Ext.window.Window' ,
	alias : 'widget.departmentEdit',
	layout: 'anchor',
	id : 'departmentEdit',
	border : false,
	width  : 350,
	height : 450,
	autoScroll:true,
	modal : true,
	autoShow : true,
	maximizable: true,
	title:"编辑",
	initComponent : function() {
		Ext.apply(this, {
			items:[{
				border:false,
				margin:'10 10 10 10',
				itemId:'editDepartmentForm',
				xtype : 'form',
				layout: 'column',
				defaults : {
					xtype : 'textfield',
					margin : '8',
					labelSeparator : "：",
					labelAlign : 'center',
					labelWidth : 80,
					width : 300
				},
		        items :[{
		        	xtype : 'hidden',
			        name:'uuid',
			        id:"uuid"
		        },{
		        	xtype : 'hidden',
			        name:'parentID'
		        },{
					border:false,
		        	xtype : 'textfield',
					id:'name',
					name:'name',
					allowBlank : false,
					minlength:4,
					maxlength:18,
					width:280,
					height: 25,
					blankText:'部门名不能为空',
					fieldLabel : '部门名称',
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
                            url: _ctxPath+'/control/department/valid/editDepNameExist.do',
                            params: { 
                            	name: this.value,
                            	uuid: Ext.getCmp('uuid').getValue()
                            },
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
		        	allowBlank : true,
					name:'sn',
					minlength:4,
					maxlength:18,
					width:280,
					height: 25,
					fieldLabel : '部门编码'
		        },{
		        	allowBlank : true,
					name:'description',
					minlength:4,
					maxlength:18,
					width:280,
					height: 25,
					fieldLabel : '部门描述'
		        },{
		        	allowBlank : true,
					name:'phone',
					minlength:4,
					maxlength:18,
					width:280,
					height: 25,
					fieldLabel : '手机',
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
					    			url: _ctxPath+'/control/department/valid/editDepPhoneExist.do',
					    			params: { 
					    				phone: this.value,
					    				uuid: Ext.getCmp('uuid').getValue()
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
					    }
		        },{
		        	allowBlank : true,
					name:'fax',
					minlength:4,
					maxlength:18,
					width:280,
					height: 25,
					fieldLabel : '传真'
		        },{
		        	allowBlank : true,
					name:'email',
					minlength:2,
					maxlength:18,
					width:280,
					height: 25,
					fieldLabel : '邮箱',
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
				    			url: _ctxPath+'/control/department/valid/editDepEmailExist.do',
				    			params: { 
				    				email: this.value,
				    				uuid: Ext.getCmp('uuid').getValue()
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
				    } 
		        }]
			}],
			buttons:[{
				action : 'updateDep',
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