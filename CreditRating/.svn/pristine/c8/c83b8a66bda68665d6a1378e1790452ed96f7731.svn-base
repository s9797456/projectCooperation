Ext.define('PM.store.user.userStore',{
	extend:'Ext.data.Store',
	model:'PM.model.user.userModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/user/listUI.do',
		actionMethods:{
			read:'post'
		},
	reader:{
			type : 'json',
			root : 'users',
			totalProperty : 'total'
		}
	},
	autoLoad : true
});