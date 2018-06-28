Ext.define('PM.store.enterprise.entShareholderWin',{
	extend:'Ext.data.Store',
	model:'PM.model.enterprise.entShareholder',
	pageSize:5,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/entScore/entShareholderList.do',
		actionMethods:{
			read:'post'
		},
		reader:{
			type : 'json',
			root : 'shareholders',
			totalProperty : 'total'
		}
	},
});