Ext.define('PM.model.model.Model', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'uuid' /*主键*/
	}, {
		name : 'name' //模板名称
	}, {
		name : 'XMLurl'//模板地址
	}, {
		name : 'version'//版本号
	}, {
		name : 'updateTime'//更新时间
	}, {
		name : 'remarks'//模型描述
	}, {
		name : 'parentID'//父ID
	}, {
		name : 'orderNO'//排序号
	}, {
		name : 'visible'//启用标志
	}],
	idProperty : 'id',// 极为重要的配置。关系到表格修改数据的获取
});