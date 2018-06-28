Ext.define("PM.view.perScore.showEducationDetail", {
	extend : 'Ext.form.Panel',
	alias : 'widget.showEducationDetail', // 这里一定要设置别名
    itemId : 'showEducationDetailForm',
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
		fieldLabel : '学历',
		name : 'education',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '毕业院校',
		name : 'university',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '专业',
		name : 'major',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '毕业证书编号',
		name : 'diplomaNo',
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
