Ext.define('PM.store.perScore.trainInfo',{
	extend:'Ext.data.Store',
	model:'PM.model.perScore.trainInfo',
	pageSize:5,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/perScore/trainInfo.do',
		actionMethods:{
			read:'post'
		},
	reader:{
			type : 'json',
			root : 'train',
			totalProperty : 'total'
		}
	}
});