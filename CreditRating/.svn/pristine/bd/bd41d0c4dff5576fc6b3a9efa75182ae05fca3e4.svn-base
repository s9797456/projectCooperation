Ext.define('PM.view.perScore.editPerScoreSummary',{
	extend:'Ext.window.Window',
	alias:"widget.editPerScoreSummary",
	layout: 'anchor',
	border : false,
	width : 700,
	height : 600,
	autoScroll:true,
	modal : true,
	autoShow : true,
	title:"评分总结",
	initComponent : function(){
		  Ext.apply(this,{
			  items:[{ 
				  xtype:'form',
				  itemId : 'scoreSummaryForm',
				  defaults : {
						style : 'margin: 10px 10px 10px 10px;'
					},
				 items:[{
					 xtype:'textarea',
					 name:'scoreSummary',
					 allowBlank:false,
					 width:670,
					 height:500
				 },{
					 xtype:'hidden',
					 name:'uuid' 
				 }]
	   }],
		    buttons:[{
				action : 'saveScoreSummary',
				text : '保存'
			},{
				action : 'close',
				text : '关闭'
			}]
        });
		  this.callParent(arguments);
	  }
  });