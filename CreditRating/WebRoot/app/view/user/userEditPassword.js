Ext.define("PM.view.user.userEditPassword",{
	extend : 'Ext.window.Window',
	alias:"widget.editPassword",
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
				itemId:'editPasswordForm',
				xtype : 'form',
				items:[{
					xtype:'textfield',
					fieldLabel:"用户名",
					name:'userName',
					readOnly : true,//只读
					width:250,
					height: 25
				},{
					xtype:'textfield',
					inputType:'password',
					fieldLabel:"旧密码",
					name:'oldpassword',
					allowBlank:false,//非空判断
					minLength:6,
					width:250,
					height: 25
				},{
					xtype:'textfield',
					inputType:'password',
					fieldLabel:"新密码",
					name:'newpassword',
					minLength:6,
					allowBlank:false,//非空判断
					width:250,
					height: 25
				},{
					xtype:'textfield',
					inputType:'password',
					fieldLabel:"确认密码",
					allowBlank:false,//非空判断
					minLength:6,
					name:'repeatpassword',
					width:250,
					height: 25
				}]
			}],
			buttons:[{
				action:'updatePassword',
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