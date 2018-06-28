Ext.define("PM.view.enterprise.showShareholderDetail", {
	extend : 'Ext.form.Panel',
	alias : 'widget.showShareholderDetail', // 这里一定要设置别名
    itemId : 'showShareholderDetailForm',
	autoScroll: true,
    layout: 'column',
	margin : '30 5 5 5',
	collapsible : true,
	titleCollapse : true,
	items :[ {
		xtype : 'textfield',
		fieldLabel : 'uuid',
		name : 'uuid',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		hidden : true
	},{
		xtype : 'textfield',
		fieldLabel : '股东名称',
		name : 'name',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		allowBlank: false,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '股比(%)',
		name : 'stockpercent',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '实缴金额',
		name : 'realcapi',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '实缴时间',
		name : 'realTime',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '认缴金额',
		name : 'shouldcapi',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '认缴时间',
		name : 'shouldTime',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '股东类型',
		name : 'type',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		allowBlank: false,
		width : 375,
	} ]
});
