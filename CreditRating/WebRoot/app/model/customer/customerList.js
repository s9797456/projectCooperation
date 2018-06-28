Ext.define('PM.model.customer.customerList', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'userName',
		type : 'string'
	}, {
		name : 'cellphone',
		type : "string",
		srotable : true
	}, {
		name : 'email',
		type : "string",
		srotable : true
	},{
		name : 'lastLoginIP',
		type : "string",
		srotable : true
	}, {
		name : 'lastLoginTime',
		type : "string",
		srotable : true
	}, {
		name : 'loginTimes',
		type : "string",
		srotable : true
	}, {
		name : 'password',
		type : "string",
		srotable : true
	}, {
		name : 'realName',
		type : "string",
		srotable : true
	}, {
		name : 'regIP',
		type : "string",
		srotable : true
	}, {
		name : 'regTime',
		type : "string",
		srotable : true
	}, {
		name : 'updateTime',
		type : "string",
		srotable : true
	}, {
		name : 'status',
		type : "string",
		srotable : true
	},{
		name:'visible'
	},{
		name:'entName'
	},{
		name:'entID'
	},{
		name:'entIndustry'
	},{
		name:'modelName'
	}]
});
