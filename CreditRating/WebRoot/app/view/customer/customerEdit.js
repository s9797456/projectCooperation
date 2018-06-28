Ext.define("PM.view.customer.customerEdit",{
	extend : 'Ext.window.Window',
	alias:"widget.customerEdit",
	layout : 'anchor',	
	border : 0,
	width : 300,
	height : 280,
	modal : true,
	title:'编辑',
	autoShow : true,
	defaults:{
		allowBlank:false,
		msgTarget:"side",
		selectOnFocus:true,
		labelAlign:"left",
		labelWidth:80,
		width:300,
		validateOnChange:false
	},
	initComponent : function() {
		Ext.apply(this, {
			items:[{
				border:false,
				padding:20,
				bodyStyle:'margin-top:15px',
				itemId:'customerEditForm',
				xtype : 'form',
				items:[{
					fieldLabel:"登录帐号",
					allowBlank:false,
					blankText:"用户名不能为空",
					minLength :3,
					maxLength:18,
					width:250,
					height: 25,
					readOnly:true,
					name:'userName',
					xtype:'textfield'
				},{
					xtype: 'textfield',
				    allowBlank: false,
				    height: 25,
				    width:250,
				    fieldLabel: '联系人',
				    name : 'realName'
				},{
					border:false,
					xtype: 'textfield',
				    height: 25,
				    width:250,
				    fieldLabel: '移动号码',
				    name : 'cellphone',
				    allowBlank:false,
					blankText:"必填项",
				    validateOnBlur: true,
                    validationDelay:2000,
                    validateOnChange: false,
                    validator: function (value) {
                    	var error=true;
                   if(value.length>0){
                       if (!/^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/.test(value)) {
                           return "移动号码格式不正确！";
                       }
                       var validator = this;
                       var error = true;
                       Ext.Ajax.request({
                           async: false,
                           scope: validator,
                           url: _ctxPath+'/control/customer/valid/editCellphoneExist.do',
                           params: { 
                        	   cellphone: this.value,
                        	   userName: Ext.ComponentQuery.query('form textfield[name=userName]')[0].getValue()
                           },
                           method: 'POST',
                           success: function (response) {
                              var result = Ext.JSON.decode(response.responseText);
                              if (result.status==false) {
                                   error = result.msg;
                               }
                           }
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
				    allowBlank:false,
					blankText:"必填项",
				    validateOnBlur: true,
                    validationDelay:2000,
                    validateOnChange: false,
                    validator: function (value) {
                    	var error=true;
                   if(value.length>0){
                	if (!/^([a-z0-9A-Z]+[-|\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/.test(value)) {
                        return "Email格式不正确!";
                    }
                       var validator = this;
                       var error = true;
                       Ext.Ajax.request({
                           async: false,
                           scope: validator,
                           url: _ctxPath+'/control/customer/valid/editEmailExist.do',
                           params: { 
                        	   email: this.value,
                        	   userName: Ext.ComponentQuery.query('form textfield[name=userName]')[0].getValue()
                           },
                           method: 'POST',
                           success: function (response) {
                              var result = Ext.JSON.decode(response.responseText);
                              if (result.status==false) {
                                   error = result.msg;
                               }
                           }
                       });
                       return error;
                   }else{
                	   return error; 
                   }
                   },
                   listeners: {
						focus: function(){
							this.clearInvalid();
					}
                   }
				}]
			}],
			buttons:[{
				action:'updateCustomer',
			    text : '确定'
			},{
			    text : '关闭',
			    scope:this,
			    handler:this.close
			}]
		});
		this.callParent(arguments);
	}
});