Ext.define('PM.view.Cords', {// 创建导航窗口组件
	extend : 'Ext.panel.Panel',// 定义组件类型
	alias : 'widget.cdmenu',// 组件别名
	initComponent : function() {// 初始化组件
		Ext.apply(this, {
			collapsible: true,
			title : '导航菜单',
			border : false,
			width : 205,
			region : 'west',// 组件部署在左侧
			layout : 'accordion',
            margin: '0 7 0 0',
			defaults : {
				bodyStyle : 'padding:15px'
			},
			layoutConfig : {
				titleCollapse : false,
				animate : true,
				activeOnTop : true
			},
		});
		this.callParent(arguments);
	},
});