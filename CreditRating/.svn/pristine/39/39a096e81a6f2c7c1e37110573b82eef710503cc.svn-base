//调查窗口
Ext.define('PM.view.enterprise.manageScoreReport', {
	extend : 'Ext.panel.Panel',
	alias : "widget.manageScoreReport",
	layout : 'anchor',
	margin:'10 5 5 0',
	border : 0,
	initComponent : function() {
		Ext.apply(this,{
			items:[{
				xtype : 'grid',
				itemId : 'scoreReportGrid',
				columnLines : true,
				store:'enterprise.scoreReports',
				forceFit : true,
				columns :[ Ext.create('Ext.grid.RowNumberer', {
						width : 40,
						renderer : function(value, metadata, record, rowIndex) {
							return rowIndex + 1;
						}
					}),
			     {
					text : '报告模块',
					dataIndex : 'name',
				},{
					text : 'uuid',
					dataIndex : 'uuid',
					hidden:true
				},{
					text : '状态',
					dataIndex : 'status',
					width:100,
					renderer:function(value){
						if(value=='0'){
							return "无";
						}else{
							return "有";
						}
					}
				},
				{
					xtype:'actioncolumn',
					text : '管理',
					items:[{
						getClass: function(v, meta, rec) {
							return 'edit';
	                    },
	                    getTip: function(v, meta, rec) {
	                    	return "编辑";
	                    },
	                    handler: function(grid, rowIndex, colIndex) {
	                        this.up('grid').fireEvent('editReason',grid, rowIndex, colIndex);
	                    }
					}]
				}]
			}]
			
		});
		this.callParent(arguments);
		
	}
});