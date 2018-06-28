Ext.Loader.setConfig({  
   enabled: true,  
   paths : {//'类名前缀':'所在路径'  
      'hytdata' : '/CreditRating/Resources/plugins',
      'kindeditor1':'/CreditRating/Resources/kindeditor',
      'kindeditor2':'/CreditRating/Resources/kindeditor/lang'
   }  
});  
//或者用setPath设置匹配路径  
//Ext.Loader.setPath('App.ux', 'lib');//'类名前缀','所在路径'  
Ext.require(['kindeditor1.kindeditor-all']);
Ext.require(['kindeditor2.zh-CN']);
Ext.require(['hytdata.ExtEditor']);//通过匹配会自动加载'lib/MusicWin.js'  
Ext.QuickTips.init();// 错误悬浮提示
Ext.define('PM.view.model.modelEditor',{
	extend : 'Ext.Window',
	alias : 'widget.modelEditor',
	closable : true,
	id : 'modelEdit',
	autoShow : true,
	title : 'XML编辑器',
	layout : 'column',
	border : 0,
	modal : true,
	selectOnFocus : true,
	width : 400,
	height : 400,
	initComponent : function() {
		this.items = [ {
			xtype : 'fieldset',
			columnWidth : 1,
			margin : '10 10 10 10',
			height : 300,
			id:'addItemId',
			items:[
					{
						xtype:'htmleditor',
						name:'HTML',
						height:320,
						width:580,
						fontFamilies :['宋体','黑体','楷体'],
					}
				],
		} ];
		this.buttons = [ {
			text : '提交',
			action : 'xmlsubmit'
		}, {
			text : '关闭',
			scope : this,
			handler : this.close
		} ];
		this.callParent(arguments);
	}
});