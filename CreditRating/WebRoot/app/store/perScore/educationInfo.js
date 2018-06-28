Ext.define('PM.store.perScore.educationInfo',{
	extend:'Ext.data.Store',
	model:'PM.model.perScore.educationInfo',
	pageSize:5,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/perScore/educationInfo.do',
		actionMethods:{
			read:'post'
		},
	reader:{
			type : 'json',
			root : 'education',
			totalProperty : 'total'
		}
	}
});