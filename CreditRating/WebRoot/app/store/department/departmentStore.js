Ext.define('PM.store.department.departmentStore', {
	extend : 'Ext.data.Store',//Ext.data.Store是EXT中用来进行数据交换和数据交互的标准中间件，
	model : 'PM.model.department.departmentModel',
	pageSize : 15,
	proxy : {
		type : 'ajax',
		url : _ctxPath+'/control/department/listUI.do',
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