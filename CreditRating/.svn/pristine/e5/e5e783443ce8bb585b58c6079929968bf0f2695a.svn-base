Ext.QuickTips.init();// 错误悬浮提示
Ext.define('PM.view.model.modelContentView', {
	extend : "Ext.panel.Panel",
	alias : 'widget.modelContentView', // 这里一定要设置别名
	layout : 'anchor',
	border : 0,
	initComponent : function(){
		Ext.apply(this,{
			items : [{
				xtype : 'grid',
				title : '<a href=javascript:backTopModel() style="text-decoration:none;color:#ffc">模型顶层</a>————>评分卡',
				anchor:'100%',
				store:'model.modelStore',
				collapsible : true,
				titleCollapse : true,
				columnLines : true,
				forceFit : true,
				itemId : "modelContentGrid",
				tbar : [ {
					xtype : 'button',
					text : '新增',
					action : 'addModelConBtn',
					iconCls : 'Applicationformadd'
				}, '-',{
					xtype : 'button',
					text : '删除',
					action: 'deleteModelConBtn',
					iconCls : 'Applicationformdelete'
				}, '-',{
					xtype : 'button',
					text : '编辑',
					action: 'editModelConBtn',
					iconCls : 'Applicationedit'
				},{
					xtype:'hidden',
					name:'parentID'
				}],
				columns : {
					items : [ Ext.create('Ext.grid.RowNumberer', {
						text : '序号',
						width : 45,
						align:'center',
						renderer : function(value, metadata, record, rowIndex) {
							return rowIndex + 1;
						}
					}), {
						text : 'ID',
						dataIndex : 'uuid',
						width : 80,
						hidden:true
					},{
						text : 'PID',
						dataIndex : 'parentID',
						width : 80,
						hidden:true
					},{
						text : 'orderNO',
						dataIndex : 'orderNO',
						width : 80,
						hidden:true
					},{
						text : 'visible',
						dataIndex : 'visible',
						width : 80,
						hidden:true
					},{
						text : '当前名',
						dataIndex : 'name',
						width : 250
					}, {
						text : '模型描述',
						dataIndex : 'remarks',
						width : 150
					}, {
						text : '模型地址',
						dataIndex : 'XMLurl',
						width : 150
					}, {
						text : '更新日期',
						dataIndex : 'updateTime',
						width : 150
					}, {
						xtype:'actioncolumn',
						text:'状态',
						items:[{
							getClass: function(v, meta, rec) {
								var state = rec.get('visible');
								if(state == 1){
		                            return 'inactive';
		                        }else{
		                        	return 'active';
		                        };
		                    },
		                    getTip: function(v, meta, rec) {
		                    	var state = rec.get('visible');
		                        if(state == 1){
		                            return '点击锁定';
		                        }else{
		                        	return '点击解锁';
		                        };
		                    },
		                    handler: function(grid, rowIndex, colIndex) {
		                        this.up('grid').fireEvent('actioncolumnclick',grid, rowIndex, colIndex);
		                    }
						},{
							icon : _ctxPath+'/Images/icon/preview.png',
		                    tooltip : '模型管理',
		                    handler: function(grid, rowIndex, colIndex) {
		                        this.up('grid').fireEvent('preview',grid, rowIndex, colIndex);
		                    }
						}]
					}],
					defaults: {
						align: 'center',
						menuDisabled: true,
						sortable : false
					},
				},
				dockedItems : [ {
					xtype : 'pagingtoolbar',
					store : 'model.modelStore',
					dock : 'bottom',
					displayInfo : true
				}]
			}]
		});
		this.callParent(arguments);
	}
});