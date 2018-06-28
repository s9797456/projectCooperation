Ext.QuickTips.init();// 错误悬浮提示
Ext.define('PM.view.privilegegroup.privilegeGroupAdd',{
	extend:"Ext.Window" ,
	alias : 'widget.privilegeGroupAdd',
	closable:true,
	id:'addPrivilegeGroup',
	layout: 'fit',
	autoShow: true,
	title:'新增',
	x:500,
	modal : true,
	y:120,
	iconCls : 'Useradd',
	width:300,
	height:130,
	initComponent:function(){
		this.items = [{
		     xtype: 'form',
		     itemId:'panel',
		     bodyPadding: 10,
		     items : {
				xtype : 'textfield',
				fieldLabel : '角色名',
				name : 'name',
				minLength : 2,
				maxLength : 10,
				allowBlank : false,
				blankText : '角色名不能为空',
				validateOnBlur: true,
				validationDelay:2000,
			    validateOnChange: false,
			    validator:function (value) {
					if (!/[\u4e00-\u9fa5]/.test(value)) {
						return "只允许输入汉字";
			        }
				    var validator = this;
				    var error = true;
				    Ext.Ajax.request({
				       async: false,
				       scope: validator,
				       url: _ctxPath+'/control/privilegegroup/valid/nameExist.do',
				       params: { name: this.value },
				       method: 'POST',
				       success: function (response) {
				           var result = Ext.JSON.decode(response.responseText);
				           if (result.status==false) {
				              error = "该名称己经存在,请重新输入！";
				            }
				       }
				   });
			   return error;
			   },
		       listeners: {
                   focus: {
                	   fn: function () { this.clearInvalid(); }
                   }
		       }
		   },
		}];
		this.buttons = [
			        {text:'提交',action:'submit'},
			        {text:'关闭',scope:this,handler:this.close}
		
            ];                
					
		this.callParent(arguments);
	}
}
	);
