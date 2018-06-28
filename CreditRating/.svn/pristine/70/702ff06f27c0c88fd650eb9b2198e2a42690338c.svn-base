Ext.define('PM.view.customer.industry',{
	extend:'Ext.Window',
	alias:"widget.industry",
	layout : 'anchor',
	border : false,
	width : 400,
	height : 200,
	modal : true,
	autoShow : true,
	title:"附加模型",
	initComponent : function() {
		this.items=[{
        	 xtype:'form',
        	 itemId:'customerIndustryForm',
    		 border:false,
        	 defaults:{margin:'10 20 10 20',width:350},
        	 items:[{
        		 xtype:'combo',
        		 fieldLabel: '一级模型:',
        		 name:'modelID',
        		 displayField : 'name',
        		 valueField : 'uuid',
        		 itemId : 'customerFirstIndustry',
        		 store:'customer.modelCombos'
        	 },{
        		 xtype:'combo',
        		 fieldLabel: '二级模型:',
        		 name:'secModelID',
        		 displayField : 'name',
        		 valueField : 'uuid',
        		 itemId : 'customerSecondIndustry',
        		 store:'SecondModelCombos',
        		 lastQuery:''
        	 },{
        		 xtype:'hidden',
        		 fieldLabel:'uuid',
        		 name:'uuid',
        		 value:Ext.ComponentQuery.query('customerManageView grid')[0].getSelectionModel().getSelection()[0].get('entID')
        	 }]
         }];
		this.buttons=[{text:'保存',action:'giveIndustry'},{text:'关闭',scope:this,handler:this.close}];
		this.callParent(arguments);
	}
});