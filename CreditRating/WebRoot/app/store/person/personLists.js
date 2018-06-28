Ext.define('PM.store.person.personLists',{
	extend:'Ext.data.Store',
	model:'PM.model.person.personList',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/person/listUI.do',
		actionMethods:{
			read:'post'
		},
	reader:{
			type : 'json',
			root : 'persons',
			totalProperty : 'total'
		}
	},
	autoLoad : true
});