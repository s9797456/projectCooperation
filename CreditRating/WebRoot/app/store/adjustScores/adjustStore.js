Ext.define('PM.store.adjustScores.adjustStore', {
	extend : 'Ext.data.Store',
	model : 'PM.model.adjustScores.adjustModel',
	pageSize : 10,
	proxy : {
		type : 'ajax',
		url : _ctxPath + '/control/addition/adjustScores/listUI.do',
		actionMethods : {
			read : 'POST'
		},
		reader : {
			type : 'json',
			root : 'adjustScores',
			totalProperty : 'total'
		}
	},
	autoLoad : true
});