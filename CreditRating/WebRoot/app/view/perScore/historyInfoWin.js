//确认信息窗口
Ext.define('PM.view.perScore.historyInfoWin',{
	extend:'Ext.window.Window',
	alias:"widget.historyInfoWin",
	layout: 'anchor',
	border : false,
	width : 950,
	modal : true,
	autoShow : true,
	title:"重要信息展示",
	closable:true, 
	maximizable :true,
	initComponent : function(){
		Ext.apply(this,{
			items: [{
				xtype:'form',
				layout: 'column',
				margin:'5 5 5 5',
				defaults : {
					xtype : 'textfield',
					margin : '5',
					labelSeparator : "：",
					labelAlign : 'right',
					buttonAlign: 'center',
					labelWidth : '150px',
					width : 350,
					readOnly : true
				},
				items:[{
					fieldLabel : "个人id",
					name : 'uuid',
					hidden : true
				},{
					fieldLabel : "历史快照是否存在",
					name : 'historyExist',
					hidden : true
				},{
					fieldLabel : "姓名",
					name : 'name'
				},{
					fieldLabel : "身份证号",
					name : 'IDCard'
				},{
					fieldLabel : "终评分数",
					name : 'finalscore'
				},{
					fieldLabel : "终评等级",
					name : 'finallevel'
				}],
				buttons: [{
					text : '查看详情',
					action : 'downloadHistory'
				}]
			}]
		});
		this.callParent(arguments);
	}
});