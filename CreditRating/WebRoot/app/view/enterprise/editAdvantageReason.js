Ext.define('PM.view.enterprise.editAdvantageReason',{
	extend:'Ext.window.Window',
	alias:"widget.editAdvantageReason",
	layout: 'anchor',
	border : false,
	width : 700,
	height : 600,
	autoScroll:true,
	modal : true,
	autoShow : true,
	title:"编辑",
	initComponent : function(){
		  Ext.apply(this,{
			  items:[{ 
				  xtype:'form',
				  itemId : 'advantageReasonForm',
				  defaults : {
						style : 'margin: 10px 10px 10px 10px;'
					},
				 items:[{
					 xtype:'textarea',
					 name:'advantageReason',
					 allowBlank:false,
					 width:670,
					 height:500
				 },{
					 xtype:'hidden',
					 name:'uuid' 
				    }]
			}],
			 
		    buttons:[{
				action : 'saveAdvantageReason',
				text : '保存'
			},{
				action : 'close',
				text : '关闭'
			}]
        });
		  this.callParent(arguments);
	  }
  });