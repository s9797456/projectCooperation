Ext.QuickTips.init();
Ext.define('PM.view.template.templateView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.templateView',
	id : 'reportTemplateView',
	border : 0,
	layout: 'anchor',
	anchor: '100% 100%',
	tbar : [ {
		xtype : 'button',
		text : '新增',
		frame : false,
		iconCls : 'Applicationformadd',
		action : 'add'
	},{
		xtype : 'button',
		text : '编辑',
		iconCls : 'Applicationformedit',
		action : 'edit'

	},{
		xtype : 'button',
		iconCls : 'Applicationdelete',
		action : 'delete',
		text : '删除'
	},{
		id:'templatepid',
		xtype : 'textfield',
		hidden:true							
	},"->", {
		xtype : 'textfield',
		fieldLabel : '名称',
		labelWidth : 40,
		id : 'Name'
	}, {
		xtype : 'button',
		text : '查询',
		action : 'query'
	} ],
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'grid',
				itemId : 'gridPanel',
				id:'gridPanel',
				columnLines : true,
				stripeRows : true,
				store : 'template.templateStore',
				forceFit : true,
				columns : [ Ext.create('Ext.grid.RowNumberer', {
					width : 45,
					text : '序号',
					align:'center',
					renderer : function(value, metadata, record, rowIndex) {
						return rowIndex + 1;
					}
				}),{
					text:'parentID',
					dataIndex:'parentID',
					hidden:true
				},{
					text : 'uuid',
					dataIndex : 'uuid',
					hidden : true
				}, {
					text : '模板名称',
					align:'center',
					dataIndex : 'templateName',
					renderer:nextDir
				}, {
					text : '模板地址',
					align:'center',
					dataIndex : 'templateUrl',
					id:'templateUrl'
				}, {
					text : '备注',
					align:'center',
					dataIndex : 'remark'
				},{
					text : '更新日期',
					align:'center',
					dataIndex : 'updateTime',
				}, {
					text : '状态',
					align:'center',
					dataIndex : 'visible',
					renderer : change2Link
				} ]

			} ]
		});
		this.callParent(arguments);
	},
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'template.templateStore',
		dock : 'bottom',
		displayInfo : true
	} ]
});