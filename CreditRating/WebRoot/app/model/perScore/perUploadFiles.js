Ext.define('PM.model.perScore.perUploadFiles', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'type'
	},{
		name : 'fileName'
	},{
		name : 'fileUrl'
	},{
		name : 'fileSize',
		type : 'int'
	}]
});