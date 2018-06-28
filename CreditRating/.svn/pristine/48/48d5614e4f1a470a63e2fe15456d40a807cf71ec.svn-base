Ext.define("PM.view.user.menuPrivilegeList",{
	extend:'Ext.Window',
	alias:'widget.menuPrivilegeList',
	border : 0,
	width : 600,
	height : 550,
	title:'分配菜单权限',
	layout:'anchor',
	maximizable:true,
	autoScroll:true,
	iconCls:'Applicationviewlist',
	autoShow : true,
	initComponent :function() {
		Ext.apply(this,{
			items:[{
				xtype:'treepanel',
				border:false,
				rootVisible: false,
				lines :false,
				multiSelect:true,
				store:'user.menuPrivileges',
				listeners:{
					'checkchange':function(node,checked){
							var treepanel=this;
			                node.raw.checked = checked;
			                node.eachChild(function(child) {   
			                	child.set('checked',checked);
			                	treepanel.fireEvent('checkchange', child, checked);
			                });   
					}
				}
			}],
			buttons:[{
				text:'确定',
				action:'saveMenuPrivilege'
			},{
				text:'取消',
				scope:this,
				handler:this.close
			}]
		});
		this.callParent(arguments);
	}
});