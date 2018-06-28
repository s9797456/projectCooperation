Ext.define('PM.store.uploadFilesManages.uploadFilesStore',{
	extend:'Ext.data.Store',
	model:'PM.model.uploadFilesManages.uploadFilesModel',
	pageSize:15,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/addition/uploadFiles/listUI.do',
		actionMethods:{
			read:'post'
		},
		reader:{
			type : 'json',
			root : 'uploadFiles',
			totalProperty : 'total'
		}
	},
	autoLoad : true
});