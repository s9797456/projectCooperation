Ext.QuickTips.init();// 错误悬浮提示
Ext.define('PM.view.department.departmentManageView',{
	extend : 'Ext.panel.Panel',
	alias : 'widget.departmentManageView', // 这里一定要设置别名
	border : 0,
	columnLines : true,
	multiSelect : true,
	initComponent : function(){
		Ext.apply(this,{
			items : [{
				 xtype : 'grid',
		         itemId:'departmentManageView',
				 id:'departmentManageView',
		         title : '<a href=javascript:backTop() style="color:#FFF">导航</a>',
				 columnLines : true,
				 stripeRows : true,
		         store:'department.departmentStore',
				 tbar:[{
					 xtype : 'button',
					 text : '新增部门',
					 action: 'addDepGroup',
					 iconCls : 'Add'//此处对应的EXT自带的图标属性，一般无需改动
				 }, {
					xtype : 'button',
					text : '编辑部门',
					iconCls : 'Applicationedit',
					action:'editDepGroup'
				 }, {
					xtype : 'button',
					text : '删除部门',
					iconCls : 'Delete',
					action:'deleteDepGroup',
				 },"->",{
						id:'deppid',
						xtype : 'textfield',	
						hidden:true
					},{
						width : 300,
						id:'depName',
						xtype : 'textfield',
						fieldLabel : '部门名称'
					},
					{
						xtype : 'button',
						text : '查询',
						textAlign:'center',
						id:'departmentQuery',
					    width:60					    
					} ],
				 forceFit : true,
				columns:[{
					text : 'uuid',
					dataIndex : 'uuid',
					hidden:true
				},{
					text : 'id',
					dataIndex : 'parentID',
					hidden:true
				},{
					text : '上级部门名称',
					dataIndex : 'parentname'
				},{
					text : '当前部门名称',
					dataIndex : 'name',
					renderer : change2Link
				},{
					text : '手机',
					dataIndex : 'phone'
				},{
					text : '邮箱',
					dataIndex : 'email'
				},{
					text : '状态',
					align:'center',
					dataIndex : 'status',
					width:40,
					renderer : changeStatus2Link
				}],
				dockedItems:[{
					xtype : 'pagingtoolbar',
					store : 'department.departmentStore',
					dock : 'bottom',
					displayInfo : true
				}],
			}]
		});
		this.callParent(arguments);
	}
});

