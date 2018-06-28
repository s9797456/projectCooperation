Ext.define('PM.store.organization.organizationPrivileges',{
	extend:'Ext.data.TreeStore',
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/user/manage/editOrgUI.do',
		reader:'json'
	},
	autoLoad : true,
});


