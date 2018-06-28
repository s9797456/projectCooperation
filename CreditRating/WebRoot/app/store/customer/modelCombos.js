Ext.define('PM.store.customer.modelCombos',{
	extend:'Ext.data.Store',
	fields:['name','uuid'],
	proxy:{
		type:'ajax',
		url : _ctxPath+'/control/entScore/manage/saveModelUI.do',
		method : 'post',
		reader:{   
			type:'json',
			root:'list'
		}
	}
});