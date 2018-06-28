Ext.QuickTips.init();// 错误悬浮提示
Ext.define("PM.view.adjustScores.adjustScoresView", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.adjustScoresView',
	border : 0,
	title : '评分设置',
	layout : "anchor",
	anchor : "100% 100%",
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'grid',
				itemId : 'scoreManagerInfo',
				title : '企业加减分项参数设置',
				collapsible : true,
				titleCollapse : true,
				columnLines : true,
				stripeRows : true,
				store : 'adjustScores.adjustStore',
				tbar : [ {
					xtype : 'button',
					text : '新增',
					iconCls : 'Applicationformadd',
					action : 'addScore'
				}, {
					xtype : 'button',
					text : '编辑',
					iconCls : 'Applicationformedit',
					action : 'editScore'
				}, {
					xtype : 'button',
					text : '删除',
					iconCls : 'Applicationdelete',
					action : 'deleteScore'
				} ],
				forceFit:true,
				columns : [ Ext.create('Ext.grid.RowNumberer', {
					width : 50,
					renderer : function(value, metadata, record, rowIndex) {
						return rowIndex + 1;
					}
				}), {
					text : '隐藏uuid',
					align:'center',
					dataIndex : "uuid",
					hidden : true
				}, {
					text : '名称',
					dataIndex : "name",
					align:'center',
					width : 350
				}, {
					text : '分值',
					align:'center',
					dataIndex : "value",
					width : 350
				} ],
				
			} ]
		});
		this.callParent(arguments);
	},
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'adjustScores.adjustStore',
		dock : 'bottom',
		displayInfo : true
	} ]
});