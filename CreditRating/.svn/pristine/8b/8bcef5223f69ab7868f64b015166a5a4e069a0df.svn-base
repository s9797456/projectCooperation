Ext.define('PM.store.SecondModelCombos',{
	extend:'Ext.data.Store',
	fields:['uuid','name'],
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