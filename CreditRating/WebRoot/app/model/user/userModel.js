Ext.define('PM.model.user.userModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'username',
		type : 'string'
	}, {
		name : 'cellphone',
		type : "string",
		srotable : true
	}, {
		name : 'email',
		type : "string",
		srotable : true
	}, {
		name : 'imgurl',
		type : "string",
		srotable : true
	}, {
		name : 'lastloginip',
		type : "string",
		srotable : true
	}, {
		name : 'lastlogintime',
		type : "string",
		srotable : true
	}, {
		name : 'logintimes',
		srotable : true
	}, {
		name : 'password',
		type : "string",
		srotable : true
	}, {
		name : 'realname',
		type : "string",
		srotable : true
	},  {
		name : 'groups',
		type : "string",
		srotable : true
	}, {
		name : 'depname',
		type : "string",
		srotable : true
	},{
		name : 'regip',
		type : "string",
		srotable : true
	}, {
		name : 'regtime',
		srotable : true
	}, {
		name : 'updatetime',
		srotable : true
	},{
		name:'visible',
	}],
	idProperty : 'id'
});
