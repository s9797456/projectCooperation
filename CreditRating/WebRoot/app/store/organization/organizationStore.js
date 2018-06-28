Ext.define('PM.store.organization.organizationStore', {
	extend : 'Ext.data.Store',//Ext.data.Store是EXT中用来进行数据交换和数据交互的标准中间件，
	model : 'PM.model.organization.organizationModel',
	pageSize : 15,
	proxy : {
		type : 'ajax',
		url : _ctxPath+'/control/organization/listUI.do',
		actionMethods:{
			read:'post'
		},
		reader : {
			type : 'json',
			root : 'list',
			totalProperty : 'total'
		}
	},
	autoLoad : true
});