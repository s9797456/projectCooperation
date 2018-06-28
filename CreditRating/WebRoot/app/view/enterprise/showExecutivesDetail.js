Ext.define("PM.view.enterprise.showExecutivesDetail", {
	extend : 'Ext.form.Panel',
	alias : 'widget.showExecutivesDetail', // 这里一定要设置别名
    itemId : 'showExecutivesDetailForm',
	autoScroll: true,
    layout: 'column',
	margin : '30 5 5 5',
	collapsible : true,
	titleCollapse : true,
	items :[{
		xtype : 'textfield',
		fieldLabel : 'uuid',
		name : 'uuid',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375,
		hidden : true
	},{
		xtype : 'textfield',
		fieldLabel : '姓名',
		name : 'name',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '年龄',
		name : 'age',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '性别',
		name : 'gender',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '身份证号',
		name : 'IDCard',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '职位',
		name : 'job',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '部门',
		name : 'department',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	}, {
		xtype : 'textfield',
		fieldLabel : '学历',
		name : 'education',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textarea',
		fieldLabel : '工作经历',
		name : 'workExp',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 760
	} ]
});
