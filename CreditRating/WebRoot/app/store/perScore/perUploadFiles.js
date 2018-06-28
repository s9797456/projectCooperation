Ext.define('PM.store.perScore.perUploadFiles',{
	extend:'Ext.data.Store',
	model:'PM.model.perScore.perUploadFiles',
	pageSize : 10,
	proxy:{
		type:'ajax',
		url:_ctxPath+"/control/perScore/perUploadFiles.do",
		method : 'post',
		reader:
		{   type:'json',
			root:'perUploadFiles'
		}
	}
});