Ext.QuickTips.init();// 错误悬浮提示
Ext.define('PM.view.organization.organizationManageView',{
	extend : 'Ext.panel.Panel',
	alias : 'widget.organizationManageView', // 这里一定要设置别名
	border : 0,
	columnLines : true,
	multiSelect : true,
	initComponent : function(){
		Ext.apply(this,{
			items : [{
				 xtype : 'grid',
		         itemId:'organizationManageView',
				 id:'organizationManageView',
		         title : '<a href=javascript:backTop() style="color:#FFF">导航</a>',
				 columnLines : true,
				 stripeRows : true,
		         store:'organization.organizationStore',
				 tbar:[{
					 xtype : 'button',
					 text : '新增机构',
					 action: 'addOrgGroup',
					 iconCls : 'Add'//此处对应的EXT自带的图标属性，一般无需改动
				 }, {
					xtype : 'button',
					text : '编辑机构',
					iconCls : 'Applicationedit',
					action:'editOrgGroup'
				 }, {
					xtype : 'button',
					text : '删除机构',
					iconCls : 'Delete',
					action:'deleteOrgGroup',
				 },"->",{
						width : 300,
						id:'orgName',
						xtype : 'textfield',
						fieldLabel : '机构名称'
					},
					{
						xtype : 'button',
						text : '查询',
						textAlign:'center',
						id:'organizationQuery',
					    width:60					    
					} ],
				 forceFit : true,
				columns : [ Ext.create('Ext.grid.RowNumberer', {
					width : 60,
					renderer : function(value, metadata, record, rowIndex) {
						return rowIndex + 1;
					}
				}),{
					text : 'uuid',
					dataIndex : 'uuid',
					hidden:true
				},{
					text : '登陆账号',
					dataIndex : 'userName',
				},{
					text : '机构名称',
					dataIndex : 'name',
					//renderer : change2Link
				},{
					text : '手机',
					dataIndex : 'phone'
				},{
					text : '邮箱',
					dataIndex : 'email'
				},{
					text : '机构类型',
					dataIndex : 'type',
					renderer:function(value){
						if(value == 1){
							return "企业级机构";
						}else if(value == 2){
							return "政府级机构";
						}else{
							return "-";
						}
					}
				},{
					text : '状态',
					align:'center',
					dataIndex : 'status',
					width:40,
					renderer : changeStatus2Link
				}],
				dockedItems:[{
					xtype : 'pagingtoolbar',
					store : 'organization.organizationStore',
					dock : 'bottom',
					displayInfo : true
				}],
			}]
		});
		this.callParent(arguments);
	}
});

