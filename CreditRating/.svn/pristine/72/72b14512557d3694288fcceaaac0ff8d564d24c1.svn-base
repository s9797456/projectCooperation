Ext.QuickTips.init();// 错误悬浮提示
Ext.define("PM.view.model.modelContentEdit", {
	extend : 'Ext.form.Panel',
	alias : 'widget.modelContentEdit',
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
		fieldLabel : '名称',
		labelWidth : 100,
		width : 375,
		allowBlank:false,
		style : 'margin: 10px 0px 10px 20px;',
		name:'name'
	},{
		xtype : 'textfield',
		labelWidth : 100,
		width : 375,
		style : 'margin: 10px 0px 10px 20px;',
		fieldLabel : '模型描述',
		name:'remark'
	},{
		xtype : 'numberfield',
		labelWidth : 100,
		width : 375,
		style : 'margin: 10px 0px 10px 20px;',
		maxValue: 15,
        minValue: 1,
		fieldLabel : '排序号',
		name:'orderNO'
	},{
		xtype : 'fileuploadfield',
		id : 'form_File',
		width : 375,
		style : 'margin: 10px 0px 10px 20px;',
		emptyText : '请选择xml文件(大小不超过2M)...',
		fieldLabel : '上传模型',
		name : 'uploadFile',
		buttonText : '选择文件',
		buttonCfg : {
			iconCls : 'upload-icon'
		},
		vtype:'fileTypeValid',
		msgTarget: 'side',
	}],
	buttons : [ {
		text : '保存',
		action : 'saveModelConEdit'
	}, {
		text : '取消',
		action:'cancelConSave'
	} ]
});

	