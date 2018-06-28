Ext.define('PM.model.department.departmentModel', {
	extend : 'Ext.data.Model',
	fields : [{
	    name : 'uuid',
		type : 'string',
		srotable : true
	},{
		name : 'parentID',
		type : 'string',
		srotable : true
   	},{
		name : 'parentname',
		type : 'string',
		srotable : true
   	},{
		name : 'name',
		type : 'string',
		srotable : true
	},{
		name : 'sn',
		type : 'string',
		srotable : true
	},{			
		name : 'description',
		type : 'string',
		srotable : true
   	},{
		name : 'phone',
		type : 'string',
		srotable : true
   	},{
		name : 'fax',
		type : 'string',
		srotable : true
   	},{
		name : 'email',
		type : 'string',
		srotable : true
   	},{
		name : 'visible'
   	},{
		name : 'status'
	}, {
		name : 'imgurl',
		type : 'string',
		srotable : true
   	}],
   idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取
});
