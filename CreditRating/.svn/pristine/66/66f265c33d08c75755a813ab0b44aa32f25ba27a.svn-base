Ext.define("PM.view.user.userManageView", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.userManageView', // 这里一定要设置别名
	border : 0,
	columnLines : true,
	multiSelect : true,
	initComponent : function() {
		Ext.apply(this, {
			items : [{
				xtype : 'grid',
				title : '用户列表',
				itemId : 'userListPanel',
				store:'user.userStore',
				columnLines : true,
				stripeRows : true,
				selModel: Ext.create('Ext.selection.CheckboxModel'),
				dockedItems : [ {
					xtype : 'pagingtoolbar',
					store:'user.userStore',
					dock : 'bottom',
					displayInfo : true
				} ],
				tbar : [ {
					xtype : 'button',
					text : '新增',
					action:'addUser',
					iconCls : 'Useradd'
				}, {
					xtype : 'button',
					text : '删除',
					action:'deleteUser',
					iconCls : 'Userdelete'
				}, {
					xtype : 'button',
					text : '编辑',
					action:'editUser',
					iconCls : 'Useredit'
				},{
					xtype : 'button',
					text : '分配角色',
					action:'AssignPvilegegroup',
					iconCls : 'Usersuitblack'
				},{
					xtype : 'button',
					text : '分配部门',
					action:'AssignDep',
					iconCls : 'Userhome'
				}, {
					xtype : 'button',
					text : '分配菜单权限',
					action:'AssignMenuPvilege',
					iconCls : 'Usertick'
				},{
		        	xtype:'button',
		        	action:'editPassword',
		        	text: '修改密码'
		        },{
					xtype : 'button',
					text : '密码初始化',
					action:'passwordInit',
					iconCls : 'Userkey'
				},"->", {
					fieldLabel : "用户名",
					labelWidth : 50,
					width : 150,
					xtype : 'textfield',
					name : "searchmatter",
					emptyText : "请输入用户名",
					selectOnFocus : true
				}, {
					xtype : "button",
					iconCls : "Applicationformmagnify",
					action:'userQuery',
					text : "查询"
				},"" ],
				forceFit : true,
				columns : [
				{
					text : '登录名',
					dataIndex : 'username',
					align:'center',
					width : 125
				}, {
					text : '真实姓名',
					align:'center',
					dataIndex : 'realname',
					width : 105
				}, {
					text : '角色',
					align:'center',
					dataIndex : 'groups',
					width : 105
				}, {
					text : '联系电话',
					align:'center',
					dataIndex : 'cellphone',
					width : 125
				}, {
					text : '邮件地址',
					align:'center',
					dataIndex : 'email',
					width : 150
				}, {
					text : '所属部门',
					align:'center',
					dataIndex : 'depname',
					width : 150
				}, {
					text : '状态',
					align: 'center',
					dataIndex : 'visible',
					width : 50,
					renderer : changeStatus2Link,
				}, {
					text : '更新日期',
					align:'center',
					dataIndex : 'updatetime',
					width : 100
				}],
			} ]
		});
		this.callParent(arguments);
	},
});