Ext.define('PM.store.person.modelCombos',{
	extend:'Ext.data.Store',
	fields:['name','uuid'],
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