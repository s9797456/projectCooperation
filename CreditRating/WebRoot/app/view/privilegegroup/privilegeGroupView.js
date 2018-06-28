Ext.QuickTips.init();// 错误悬浮提示
Ext.define("PM.view.privilegegroup.privilegeGroupView", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.privilegeGroupView', // 这里一定要设置别名
	id:'userMgrInfo',
	initComponent : function(){
		Ext.apply(this,{
			items : [{	
				xtype : 'grid',
			    itemId:'gridPanel',
			    title:'角色列表',
				columnLines : true,
				stripeRows : true,
				forceFit: true,
				store:'privilegegroup.privilegeGroupStore',
				tbar : [ {
						xtype : 'button',
						text : '新增',
						iconCls : 'Useradd',
						action : 'add'
					}, {
						xtype : 'button',
						iconCls : 'Userdelete',
						text : '彻底删除',
						action : 'delete'
					}, {
						xtype : 'button',
						text : '编辑',
						iconCls : 'Useredit',
						action : 'edit'
					}, {
						xtype : 'button',
						iconCls : 'Overlays',
						action : 'assignPermissions',
						text : '分配菜单权限'
					}, {
						xtype : 'button',
						iconCls : 'Usermature',
						action : 'rolePreview',
						text : '角色预览'
					},"->",{
						width : 300,
						id:'tf',
						xtype : 'textfield',
						fieldLabel : '角色名称'
					},
					{
						xtype : 'button',
						text : '查询',
						textAlign:'center',
						action:'query',
					    width:60
					    
					} ],
			columns:[Ext.create('Ext.grid.RowNumberer', {
							width:45,
							renderer : function(value, metadata, record, rowIndex) {
								return rowIndex + 1;
									}
								}),
								{
								text : 'uid',
								dataIndex : 'uuid',
								hidden:true
							},
								{
								text : 'Id',
								dataIndex : 'id',
								hidden:true
						    },
						    	{
								text : '角色名称',
								dataIndex : 'name',
								width : 200,
								align:'center'
						    }]
			 } ]
			});
			this.callParent(arguments);
		},
		dockedItems:[{
			xtype : 'pagingtoolbar',
			store : 'privilegegroup.privilegeGroupStore',
			dock : 'bottom',
			displayInfo : true
		}]
        
	});		