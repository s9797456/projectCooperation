Ext.define("PM.view.perScore.showTrainDetail", {
	extend : 'Ext.form.Panel',
	alias : 'widget.showTrainDetail', // 这里一定要设置别名
    itemId : 'showTrainDetailForm',
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
		fieldLabel : '培训机构',
		name : 'trainOrg',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '培训证书编号',
		name : 'certificateNo',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textarea',
		fieldLabel : '培训地址',
		name : 'trainAddress',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 760
	},{
		xtype : 'textarea',
		fieldLabel : '培训内容',
		name : 'trainContent',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 760
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
