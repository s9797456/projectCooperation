Ext.QuickTips.init();// 错误悬浮提示
Ext.define("PM.view.model.modelEdit", {
	extend : 'Ext.form.Panel',
	alias : 'widget.modelEdit',
	title:'编辑',
	autoScroll : true,
	style : "margin-top:15px;",
	layout : 'column',
	items : [{
		xtype : 'textfield',
		fieldLabel : 'Id',
		name : 'uuid',
		hidden : true
	}, {
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
		xtype : 'textfield',
		labelWidth : 100,
		width : 375,
		style : 'margin: 10px 0px 10px 20px;',
		fieldLabel : '排序号',
		name:'orderNO'
	}],
	buttons : [ {
		text : '保存',
		action : 'saveModelEdit'
	}, {
		text : '取消',
		action:'cancelSave'
	} ]
});

	