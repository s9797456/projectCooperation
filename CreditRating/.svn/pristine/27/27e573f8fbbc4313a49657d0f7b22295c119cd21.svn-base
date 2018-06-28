Ext.define('PM.store.perScore.perBaseInfo',{
	extend:'Ext.data.Store',
	model:'PM.model.perScore.perBaseInfo',
	proxy:{
		type:'ajax',
		url :_ctxPath+'/control/perScore/perBaseInfoUI.do',
		actionMethods:{
			read:'post'
		},
		reader:{
			type : 'json',
			root : 'perBaseInfo',
			totalProperty : 'total'
		}
	}
});