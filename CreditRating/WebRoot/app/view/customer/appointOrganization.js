Ext.define('PM.view.customer.appointOrganization',{
	extend:'Ext.Window',
	alias:"widget.appointOrganization",
	layout : 'anchor',
	border : false,
	width : 500,
	height : 600,
	modal : true,
	autoShow : true,
	title:"分配机构",
	initComponent : function() {
		this.items=[{
        	 xtype:'form',
        	 itemId:'appointOrganizationForm',
    		 border:false,
        	 defaults:{margin:'10 20 10 20',width:350},
        	 items:[{
				border:false,
				padding:'10,10,10,10',
				xtype : 'textfield',
				name :  'text',
				readOnly:true,
				width:400,
				fieldLabel:'分配用户部门'
			},{
				xtype : 'textfield',
				name :  'id',
				hidden:true
			},{
				xtype : 'textfield',
				name :  'pId',
				hidden:true
			}]
         }];
		this.buttons=[{text:'保存',action:'saveOrganization'},{text:'关闭',scope:this,handler:this.close}];
		this.callParent(arguments);
	}
});