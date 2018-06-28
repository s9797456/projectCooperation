Ext.define("PM.view.user.userDeps",{
	extend:'Ext.Window',
	alias:'widget.userDeps',
	border : 0,
	width : 600,
	height : 550,
	title:'分配部门',
	layout:'anchor',
	maximizable:true,
	autoScroll:true,
	modal : true,
	iconCls:'Applicationviewlist',
	autoShow : true,
	initComponent :function() {
		Ext.apply(this,{
			items:[{
				border:false,
				padding:'10,10,10,10',
				xtype : 'textfield',
				name :  'depName',
				readOnly:true,
				width:400,
				fieldLabel:'分配用户部门'
			},{
				border:false,
				xtype : 'textfield',
				name :  'department',
				hidden:true
			},{
				border:false,
				xtype : 'textfield',
				name :  'userName',
				hidden:true
			}],
		buttons:[{
				text:'确定',
				action:'saveUserDep'
		    },{
				text:'取消',
				scope:this,
				handler:this.close
		    }]
		});
	this.callParent(arguments);
	}
});