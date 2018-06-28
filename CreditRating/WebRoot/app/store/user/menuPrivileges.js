Ext.define('PM.store.user.menuPrivileges',{
	extend:'Ext.data.TreeStore',
	proxy:{
		type:'ajax',
		url:_ctxPath+'/control/user/manage/menuPrivilegeEditUI.do',
		reader:'json',
		
	}
});