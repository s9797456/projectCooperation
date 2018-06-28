Ext.QuickTips.init();// 错误悬浮提示
Ext.define('PM.view.privilegegroup.privilegeGroupEdit',{
	extend:"Ext.Window" ,
	alias : 'widget.privilegeGroupEdit',
	closable:true,
	//closeAction:'hide',
	id:'editUser',
	maximizable:true,
	layout: 'fit',
	autoShow: true,
	title:'编辑',
	x:500,
	modal : true,
	y:120,
	iconCls : 'Useredit',
	initComponent:function(){
	  this.items = [{
		    xtype: 'form',
		    itemId:'EditformPanel',
		    bodyPadding: 10,
		    items : [ 
				{
				xtype : 'textfield',
				id:'uuid',
				name : 'uuid',
				hidden : true
					},
		        {
				xtype : 'textfield',
				fieldLabel : '角色名',
				name : 'name',
				minLength : 2,
				maxLength : 10,
				size : 20,
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
			         var id=Ext.getCmp('uuid').getValue();
			         Ext.Ajax.request({
			             async: false,
			             scope: validator,
			             url: _ctxPath+'/control/privilegegroup/valid/nameExist.do',
			             params: { name: this.value,uuid:id },
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
						}
		      ]
					}
		];
		this.buttons = [
			        {text:'提交',action:'submit'},
			        {text:'关闭',scope:this,handler:this.close}
		
            ];                
					
		this.callParent(arguments);
	}
}
	);