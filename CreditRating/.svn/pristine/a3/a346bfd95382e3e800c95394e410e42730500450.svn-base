Ext.define('PM.store.menu.menuStore',{
	extend:'Ext.data.Store',
	model:'PM.model.menu.menuModel',
	pageSize:15,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/menu/listUI.do',
		actionMethods:{
			read:'post'
		},
		reader:{
			type : 'json',
			root : 'menus',
			totalProperty : 'total'
		}
	},
	autoLoad : true
});