Ext.define('PM.store.enterprise.entExecutivesWin',{
	extend:'Ext.data.Store',
	model:'PM.model.enterprise.entExecutives',
	pageSize:5,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/entScore/entExecutivesList.do',
		actionMethods:{
			read:'post'
		},
	reader:{
			type : 'json',
			root : 'executives',
			totalProperty : 'total'
		}
	}
});