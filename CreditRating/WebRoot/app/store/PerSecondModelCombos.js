Ext.define('PM.store.PerSecondModelCombos',{
	extend:'Ext.data.Store',
	fields:['uuid','name'],
	proxy:{
		type:'ajax',
		url : _ctxPath+'/control/perScore/manage/saveModelUI.do',
		method : 'post',
		reader:{   
			type:'json',
			root:'list'
		}
	}
});