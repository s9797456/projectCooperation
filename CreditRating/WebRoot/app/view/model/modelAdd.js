Ext.QuickTips.init();// 错误悬浮提示
Ext.define("PM.view.model.modelAdd", {
	extend : 'Ext.form.Panel',
	alias : 'widget.modelAdd',
	title:'新增',
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
		maxValue: 15,
        minValue: 1,
		fieldLabel : '排序号',
		labelWidth : 100,
		width : 375,
		allowBlank:false,
		style : 'margin: 10px 0px 10px 20px;',
		name:'orderNO'
	}],
	buttons : [ {
		text : '保存',
		action : "saveModelAdd"
	}, {
		text : '取消',
		action:'cancelSave'
	} ]
});

	