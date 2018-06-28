Ext.define('PM.store.enterprise.uploadFiles',{
	extend:'Ext.data.Store',
	model:'PM.model.enterprise.uploadFiles',
	pageSize : 10,
	proxy:{
		type:'ajax',
		url:_ctxPath+"/control/entScore/uploadFiles.do",
		method : 'post',
		reader:
		{   type:'json',
			root:'uploadFiles'
		}
	}
});