Ext.define('PM.view.enterprise.industrys',{
	extend:'Ext.Window',
	alias:"widget.industrys",
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
        	 itemId:'enterpriseIndustryForm',
    		 border:false,
        	 defaults:{margin:'10 20 10 20',width:350},
        	 items:[{
        		 xtype:'combo',
        		 fieldLabel: '一级模型:',
        		 name:'modelID',
        		 displayField : 'name',
        		 valueField : 'uuid',
        		 itemId : 'enterpriseFirstIndustry',
        		 store:'enterprise.modelCombos'
        	 },{
        		 xtype:'combo',
        		 fieldLabel: '二级模型:',
        		 name:'secModelID',
        		 displayField : 'name',
        		 valueField : 'uuid',
        		 itemId : 'enterpriseSecondIndustry',
        		 store:'SecondModelCombos',
        		 lastQuery:''
        	 },{
        		 xtype:'hidden',
        		 fieldLabel:'uuid',
        		 itemId : 'enterpriseUuid',
        		 name:'uuid',
        		 value:''//Ext.ComponentQuery.query('enterpriseManageView grid')[0].getSelectionModel().getSelection()[0].get('entID')
        	 }]
         }];
		this.buttons=[{text:'保存',action:'giveIndustry'},{text:'关闭',scope:this,handler:this.close}];
		this.callParent(arguments);
	}
});