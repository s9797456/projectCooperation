Ext.define('PM.store.perScore.skillsInfo',{
	extend:'Ext.data.Store',
	model:'PM.model.perScore.skillsInfo',
	pageSize:5,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/perScore/skillsInfo.do',
		actionMethods:{
			read:'post'
		},
	reader:{
			type : 'json',
			root : 'skills',
			totalProperty : 'total'
		}
	}
});