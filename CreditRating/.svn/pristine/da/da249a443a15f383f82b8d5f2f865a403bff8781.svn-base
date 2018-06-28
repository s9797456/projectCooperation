Ext.define('PM.store.enterprise.entBaseInfo',{
	extend:'Ext.data.Store',
	model:'PM.model.enterprise.entBaseInfo',
	proxy:{
		type:'ajax',
		url :_ctxPath+'/control/entScore/entBaseInfoUI.do',
		actionMethods:{
			read:'post'
		},
		reader:{
			type : 'json',
			root : 'entBaseInfo',
			totalProperty : 'total'
		}
	},
});