Ext.define("PM.view.adjustScores.adjustScoresAdd", {
	extend : 'Ext.form.Panel',
	alias : 'widget.adjustScoresAdd', // 这里一定要设置别名
	itemId : 'addScoreManagerForm',
	xtype:'form',
	title : '新增',
	autoScroll : true,
	iconCls : 'Applicationformadd',
	style : "margin-top:15px;",
	layout : 'column',
	items : [ {
		xtype : 'textfield',
		fieldLabel : '隐藏uuid',
		name : 'uuid',
		style : "margin:15px auto;",
		hidden : true
	},{
		xtype : 'textfield',
		fieldLabel : "名称",
		labelAlign : "right",
		name : "name",
		allowBlank : false,
		style : "margin:15px auto;",
		width : 350
	}, {
		xtype : 'numberfield',
		fieldLabel : "分值",
		labelAlign : "right",
		name : "value",
		style : "margin:15px auto;",
		maxValue : "10",
		minValue : "-10",
		allowDecimals : false,
		allowBlank : false,
		width : 350,
		validator : function(value) {
			if (value == 0) {
				return "";
			}
			return true;
		}
	} ],
	buttons : [ {
		text : '保存',
		action : "saveadd"
	}, {
		text : '取消',
		action : "canceladd"
	} ]
});