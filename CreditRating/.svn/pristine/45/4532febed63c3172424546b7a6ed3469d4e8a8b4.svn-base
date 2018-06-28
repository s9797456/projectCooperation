Ext.define("PM.view.menu.menuManageView", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.menuManageView', // 这里一定要设置别名
	border : 0,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'grid',
				title : '<a href=javascript:backTop() style="color:#FFF">导航</a>',
				itemId : 'menuManageView',
				id:'menuManageView',
				columnLines : true,
				stripeRows : true,
				store : 'menu.menuStore',
				tbar : [ {
					text : '新增菜单',
					id : 'addBtn1',
					iconCls : 'Applicationadd'
				}, {
					text : '删除菜单',
					id : 'deleteBtn1',
					iconCls : 'Applicationdelete'
				}, {
					text : '编辑菜单',
					id : 'edtionBtn1',
					iconCls : 'Applicationedit'
				}, {
					text : '菜单预览',
					id : 'lookBtn',
					iconCls : 'Applicationformmagnify'
				} ,{
					id:'menupid',
					xtype : 'textfield',
					hidden:true							
				}],
				
				forceFit : true,
				
				columns : [ Ext.create('Ext.grid.RowNumberer', {
					width:35,
					renderer : function(value, metadata, record, rowIndex) {
						return rowIndex + 1;
					}
				}), {
					text : '主键',
					dataIndex : 'uuid',
					hidden : true
				}, {
					text : '上级菜单的主键',
					dataIndex : 'parentID',
					hidden : true
				}, {
					text : 'rel',
					dataIndex : 'rel',
					hidden : true
				},{
					text : 'target',
					dataIndex : 'target',
					hidden : true
				},{
					text : '上级菜单名称',
					align:'center',
					dataIndex : 'parentName'
				}, {
					text : '当前菜单名称',
					align:'center',
					dataIndex : 'name',
					renderer : change2Link
				}, {
					text : '排序号',
					align:'center',
					dataIndex : 'orderNO',
					width:20
				}, {
					text : '状态',
					align:'center',
					dataIndex : 'status',
					width:40,
					renderer : changeStatus2Link
				}, {
					text : '地址',
					align:'center',
					dataIndex : 'url'
				}, {
					text : '视图 | 控制',
					align:'center',
					dataIndex : 'target_rel'
				}, {
					text : '分配地址 | 赋权',
					align:'center',
					dataIndex : 'distributionOfAddress',
					renderer : changeAddress2Link
				} ]
			} ],
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : 'menu.menuStore',
				dock : 'bottom',
				displayInfo : true,
			} ]
		});
		this.callParent(arguments);
	}
});
