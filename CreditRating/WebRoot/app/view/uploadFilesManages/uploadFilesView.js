Ext.define("PM.view.uploadFilesManages.uploadFilesView", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.uploadFilesView', // 这里一定要设置别名
	border : 0,//false
	layout: 'anchor',
	anchor:'100% 100%',
	initComponent : function() {
		Ext.apply(this, {
			items : [{
				xtype : 'grid',
				title : '扫描件指标管理',
				itemId:'uploadFilesGrid',
				columnLines : true,
				stripeRows : true,
				forceFit : true,
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : 'uploadFilesManages.uploadFilesStore',
					dock : 'bottom',
					displayInfo : true
				}],
				store : 'uploadFilesManages.uploadFilesStore',
				tbar : [{
					xtype : 'button',
					text : '新增',
					action: 'uploadFilesAdd',
					iconCls : 'Applicationformadd'
				}, {
					xtype : 'button',
					text : '删除',
					action: 'uploadFilesDelete',
					iconCls : 'Applicationformdelete'
				}, {
					xtype : 'button',
					text : '编辑',
					action : 'uploadFilesEdit',
					iconCls : 'Applicationedit'
				}],
				columns : [ Ext.create('Ext.grid.RowNumberer', {
					width : 40,
					renderer : function(value, metadata, record, rowIndex) {
						return rowIndex + 1;
					}
				}),{
					text : '主键',
					dataIndex:'uuid',
					width : 80,
					hidden:true
				},{
					text : '上传文件指标名',
					dataIndex:'name',
					align:'center'
				},{
					text : '是否必须(1是0否)',
					dataIndex:'isMust',
					align:'center'
				},{
					text : '是否企业文件(0企业文件1个人文件)',
					dataIndex:'isEnt',
					align:'center'
				},{
					text : '上传文件类型(1复数0单一)',
					dataIndex:'type',
					align:'center'
				},{
					text : '备注',
					dataIndex:'remark',
					align:'center'
				},{
					text : '更新时间',
					dataIndex:'updateTime',
					renderer:Ext.util.Format.dateRenderer('Y年m月d日'),
					align:'center'
				}]
			}]
		});
		this.callParent(arguments);
	}
});