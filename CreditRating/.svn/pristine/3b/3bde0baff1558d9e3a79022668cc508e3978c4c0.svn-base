Ext.define('PM.store.customer.customerLists',{
	extend:'Ext.data.Store',
	model:'PM.model.customer.customerList',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/customer/listUI.do',
		actionMethods:{
			read:'post'
		},
	reader:{
			type : 'json',
			root : 'customers',
			totalProperty : 'total'
		}
	},
	autoLoad : true
});