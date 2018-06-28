Ext.define("PM.view.customer.customerManageView", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.customerManageView', // 这里一定要设置别名
	border : 0,
	columnLines : true,
	multiSelect : true,
	initComponent : function() {
		Ext.apply(this, {
			items : [{
				xtype : 'grid',
				title : '客户列表',
				itemId : 'customerListPanel',
				store:'customer.customerLists',
				columnLines : true,
				stripeRows : true,
				dockedItems : [ {
					xtype : 'pagingtoolbar',
					store:'customer.customerLists',
					dock : 'bottom',
					displayInfo : true,
				} ],
				tbar : [{
					xtype : 'button',
					text : '新增',
					action:'addCustomer',
					iconCls : 'Useradd'
				}, {
					xtype : 'button',
					text : '删除',
					action:'deleteCustomer',
					iconCls : 'Userdelete'
				}, {
					xtype : 'button',
					text : '编辑',
					action:'editCustomer',
					iconCls : 'Useredit'
				},{
					xtype : 'button',
					text : '分配行业模型',
					action:'AssignIndustry',
					iconCls : 'Applicationedit'
				},{
					xtype : 'button',
					text : '分配机构',
					action:'AssignOrganization',
					iconCls : 'Applicationviewtile'
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
					action:'customerQuery',
					text : "查询"
				},"" ],
				forceFit : true,
				columns : [ Ext.create('Ext.grid.RowNumberer', {
					width : 40,
					renderer : function(value, metadata, record, rowIndex) {
						return rowIndex + 1;
					}
				}),
				{
					text : '登录名',
					dataIndex : 'userName',
					align:'center',
					width : 125
				}, {
					text : '联系人',
					align:'center',
					dataIndex : 'realName',
					width : 105
				},{
					text : '企业名',
					align:'center',
					dataIndex : 'entName',
					width : 170
				},{
					text : '企业ID',
					align:'center',
					dataIndex : 'entID',
					hidden : true
				},{
					text : '所属行业',
					align:'center',
					dataIndex : 'entIndustry',
				},{
					text : '联系电话',
					align:'center',
					dataIndex : 'cellphone',
					width : 125
				}, {
					text : '模型',
					align:'center',
					dataIndex : 'modelName',
					width : 150
				}, {
					xtype:'actioncolumn',
					text : '状态',
					align: 'center',
					items : [{
						getClass: function(v, meta, rec) {
							var state = rec.get('visible');
	                        if(state == 1){
	                            return 'inactive';
	                        }else{
	                        	return 'active';
	                        };
	                    },
	                    getTip: function(v, meta, rec) {
	                    	var state = rec.get('visible');
	                        if(state == 1){
	                            return '点击锁定';
	                        }else{
	                        	return '点击激活';
	                        };
	                    },
	                    handler: function(grid, rowIndex, colIndex) {
	                        this.up('grid').fireEvent('actioncolumnclick',grid, rowIndex, colIndex);
	                    }
					}],
					width : 50,
				}, {
					text : '更新日期',
					align:'center',
					dataIndex : 'updateTime',
					width : 100
				} ],
			} ]
		});
		this.callParent(arguments);
	},
});
