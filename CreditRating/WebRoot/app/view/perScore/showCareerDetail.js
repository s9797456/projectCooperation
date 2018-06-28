Ext.define("PM.view.perScore.showCareerDetail", {
	extend : 'Ext.form.Panel',
	alias : 'widget.showCareerDetail', // 这里一定要设置别名
    itemId : 'showCareerDetailForm',
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
		fieldLabel : '开始时间',
		name : 'startTime',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '结束时间',
		name : 'endTime',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '就职单位',
		name : 'inauguralUnit',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '单位规模',
		name : 'unitScale',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '从事行业',
		name : 'industry',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '工作年限',
		name : 'workingLife',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '职务',
		name : 'post',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '平均薪资',
		name : 'averageSalary',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textarea',
		fieldLabel : '备注',
		name : 'remarks',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 760
	} ]
});
