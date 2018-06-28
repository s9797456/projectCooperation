Ext.QuickTips.init();// 错误悬浮提示
Ext.define("PM.view.uploadFilesManages.uploadFilesEdit", {
	extend : 'Ext.form.Panel',
	alias : 'widget.uploadFilesEdit',
	title:'编辑',
	autoScroll : true,
	style : "margin-top:15px;",
	layout : 'column',
	items : [ {
		xtype : 'textfield',
		fieldLabel : 'Id',
		name : 'uuid',
		hidden : true
	},{
		xtype : 'textfield',
		fieldLabel : '上传文件指标名',
		labelWidth : 100,
		width : 300,
		allowBlank:false,
		style : 'margin: 10px 0px 10px 20px;',
		name:'name'
	},{
		xtype:'radiogroup',
		labelWidth : 70,
		width : 200,
		itemId:'isMust',
		style : 'margin: 10px 0px 10px 100px;',
		fieldLabel : "是否必须",
		allowBlank:false,
		items:[{
			boxLabel:"是",
			name:"isMust",
			inputValue: '1'
		},{
			boxLabel:"否",
			name : "isMust",
			inputValue: '0'
		}]
	},{
		xtype:'radiogroup',
		labelWidth : 90,
		width : 200,
		itemId:'isEnt',
		style : 'margin: 10px 0px 10px 100px;',
		fieldLabel : "是否企业文件",
		allowBlank:false,
		items:[{
			boxLabel:"是",
			name:"isEnt",
			inputValue: '0'
		},{
			boxLabel:"否",
			name : "isEnt",
			inputValue: '1'
		}]
	},{
		xtype:'radiogroup',
		labelWidth : 90,
		width : 300,
		itemId:'fileType',
		style : 'margin: 10px 0px 10px 100px;',
		fieldLabel : "上传文件类型",
		allowBlank:false,
		items:[{
			boxLabel:"单一文件",
			name:"type",
			inputValue: '0'
		},{
			boxLabel:"复数文件",
			name : "type",
			inputValue: '1'
		}]
	},{
		xtype : 'textfield',
		labelWidth : 100,
		width : 300,
		style : 'margin: 10px 0px 10px 20px;',
		fieldLabel : '备注',
		name:'remark'
	}],
	buttons : [ {
		text : '保存',
		action : 'saveUploadFilesEdit'
	}, {
		text : '取消',
		action:''
	} ]
});

	