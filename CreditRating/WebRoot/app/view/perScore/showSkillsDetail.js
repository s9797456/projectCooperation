Ext.define("PM.view.perScore.showSkillsDetail", {
	extend : 'Ext.form.Panel',
	alias : 'widget.showSkillsDetail', // 这里一定要设置别名
    itemId : 'showSkillsDetailForm',
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
		fieldLabel : '技能名',
		name : 'skillName',
		style : 'margin: 10px 0px 10px 10px;',
		labelSeparator : '：',
		labelWidth : 70,
		width : 375
	},{
		xtype : 'textfield',
		fieldLabel : '熟练度',
		name : 'skillProficiency',
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
