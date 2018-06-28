Ext.define('PM.store.perScore.careerInfo',{
	extend:'Ext.data.Store',
	model:'PM.model.perScore.careerInfo',
	pageSize:5,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/perScore/careerInfo.do',
		actionMethods:{
			read:'post'
		},
	reader:{
			type : 'json',
			root : 'career',
			totalProperty : 'total'
		}
	}
});