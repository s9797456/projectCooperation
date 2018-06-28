Ext.define('PM.store.enterprise.reporttemplates', {
	extend : 'Ext.data.Store',//Ext.data.Store是EXT中用来进行数据交换和数据交互的标准中间件，
	//无论是Grid还是ComboBox，都是通过它实现数据读取、类型转换、排序分页和搜索等操作的。
	fields:['name','value'],
	proxy:{
		type:'ajax',
		url : _ctxPath+'/control/entScore/reportTemplateList.do',
		method : 'post',
		reader:
		{   type:'json',
			root:'options'
		}
	}
	//autoLoad : true
});