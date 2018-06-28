Ext.define('PM.view.enterprise.editScoreReason',{
	extend:'Ext.window.Window',
	alias:"widget.editScoreReason",
	layout: 'anchor',
	border : false,
	width : 900,
	height : 600,
	autoScroll:true,
	modal : true,
	autoShow : true,
	title:"编辑",
	initComponent : function(){
		Ext.apply(this,{
			items:[{
				xtype : 'form',
				itemId : 'scoreReasonForm',
				defaults : {
					xtype:'fieldset',
					collapsible: true,
					style : 'margin: 0px 10px 10px 10px;'
				},
				items:[{
					title:'调整因素及说明',
					itemId:'reasonAndExplain',
					defaults:{
				        	style : 'margin: 15px 10px 15px 10px;'
				        }
				},{
					xtype:'hidden',
					name:'uuid'
				}]
			}],
			buttons:[{
				action : 'saveScoreReason',
				text : '保存'
			},{
				action : 'close',
				text : '关闭'
			}]
		});
		this.callParent(arguments);
	}
});