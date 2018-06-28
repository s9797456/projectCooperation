Ext.define('PM.store.perScore.scoreReports', {
	extend : 'Ext.data.Store',//Ext.data.Store是EXT中用来进行数据交换和数据交互的标准中间件，
	//无论是Grid还是ComboBox，都是通过它实现数据读取、类型转换、排序分页和搜索等操作的。
	model : 'PM.model.perScore.scoreReport',
	proxy:{
		type : 'ajax',
		async: false,
		url :_ctxPath+'/control/perScore/manage/manageReportView.do',
		actionMethods : {
			read : 'POST'
		},
		reader : {
			type : 'json',
			root : 'perResultVO'
		}
	},
	idproperty:'resultID'
});