Ext.define("PM.view.menu.menuAdd",{
	extend : 'Ext.window.Window',
	alias:"widget.menuWindow",
	layout : 'anchor',	
	id : 'menuWindow',
	border : false,
	width : 450,
	height : 250,
	modal : true,
	frame:true,
	autoShow : true,
	title:"<center>新增菜单</center>",
	defaults:{
		allowBlank:false,
		msgTarget:"side",
		selectOnFocus:true,
		labelAlign:"left",
		labelWidth:80,
		width:450,
		validateOnChange:false
	},
	initComponent : function() {
		Ext.apply(this, {
			items:[{
				border:false,
				padding:15,
				fileUpload: true,
				bodyStyle:'margin-top:20px',
				itemId:'addMenuForm',
				xtype : 'form',
				items:[{
					border:false,
					fieldLabel:"菜单名",
					width:380,
					minLength:2,
					maxLength:50,
					height: 25,
				    allowBlank: false,
					name :  'name',
					xtype:'textfield'
				},{
					border:false,
					xtype: 'textfield',
				    height: 25,
				    width:380,
					maxLength:20,
				    fieldLabel: '菜单编号',
				    name : 'sn'
				},{
					border:false,
					xtype: 'textfield',
				    height: 25,
				    width:250,
				    allowBlank: false,
				    fieldLabel: '排序号',
				    name : 'orderNO'
				},{
					xtype:'hidden',
					name:'parentid',
					id:'parentID',
					fieldLabel:'上级菜单的主键'
					//hidden:true
				},
				/*{
					xtype:'fileuploadfield',
					name:'uploadFile.imgUrl',
					fieldLabel:'上传图片',
					width:380,
					emptyText: 'Select an image',
					buttonText: '请选择'
				}*/
				]
			}],
			buttons:[{
				action:'save',
			    text : '提交'
			},{
				scope:this,
				handler:this.close,
			     text : '关闭'
			}]
		});
		this.callParent(arguments);
	}
});