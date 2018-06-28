Ext.define('PM.model.privilegegroup.privilegeGroupModel', {
	extend : 'Ext.data.Model',
	fields : [{
	      		name : 'uuid',
	      	},
	      	{
				name : 'name',
			}],
	idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取
});