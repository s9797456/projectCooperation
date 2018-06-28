Ext.define("PM.view.enterprise.entScoreManageView", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.entScoreManageView', // 这里一定要设置别名
	border : 0,
	layout : 'anchor',
	anchor : '100% 100%',
	initComponent : function() {
		Ext.apply(this, {
			items : [{
				xtype : 'grid',
				itemId : 'entScoreManageViewGrid',
				columnLines : true,
				store : 'enterprise.entScoreLists',
				viewConfig:{
					 getRowClass:function(){
                     return '.x-grid-cell-inner';
                 }
                },
				dockedItems : [ {
					xtype : 'pagingtoolbar',
					store:'enterprise.entScoreLists',
					dock : 'bottom',
					displayInfo : true,
				} ],
				tbar : [ {
					xtype : 'button',
					text : '刷新',
					action:'refresh',
					iconCls : 'Tablerefresh'
				},"->",{
					fieldLabel : "查询",
					labelWidth : 50,
					xtype : 'combo',  
					name : 'queryType',
					id : 'queryType',
					mode : 'local',
					readOnly : false,
					triggerAction : 'all',
					store : new Ext.data.SimpleStore({  
						fields : [ 'name', 'value' ],
						data : [ [ '客户名', 'userName' ], [ '客户号码', 'cellphone' ], [ '统一社会信用代码', 'uscc' ],
						         [ '企业名称', 'entName' ], [ '企业法人', 'legalPerson' ]] 
                    }),
					hiddenName : 'queryType',
					valueField : 'value',
					emptyText: "--请选择--",
					displayField : 'name',
				}, {
					width : 150,
					xtype : 'textfield',
					name : "searchmatter",
					emptyText : "请输入",
					selectOnFocus : true
				}, {
					xtype : "button",
					iconCls : "Applicationformmagnify",
					action:'query',
					text : "查询"
				},"" ],
				features: [
				    Ext.create('Ext.grid.feature.Grouping',{ 
				    //startCollapsed: true,
					groupHeaderTpl: '{columnName} :  {name} ({rows.length})',
					showGroupsText:"显示分组",
					groupByText:"是否以当前列分组",
					enableGroupingMenu:true,
				})],  
				forceFit : true,
				columns : [ Ext.create('Ext.grid.RowNumberer', {
					width : 30,
					align:'center',
					renderer : function(value, metadata, record, rowIndex) {
						return rowIndex + 1;
					}
				}),{
					text : 'entID',
					dataIndex : 'entID',
					hidden:true
				},{
					text : 'modelID',
					dataIndex : 'modelID',
					hidden:true
				},{
					text : 'processID',
					dataIndex : 'processID',
					hidden:true
				},{
					text : 'resultID',
					dataIndex : 'resultID',
					hidden:true
				},{
					text : 'applyReportstate',
					dataIndex : 'applyReportstate',
					hidden:true
				},{
					text : 'scoreState',
					dataIndex : 'scoreState',
					hidden:true
				},{
					text : '客户名',
					align:'center',
					dataIndex:'userName',
					flex:1,
					sortable: true
				},{
					text : '客户电话',
					align:'center',
					dataIndex:'cellphone',
					flex:1,
					sortable: true
				},{
					text : '企业名称',
					align:'center',
					dataIndex : 'entName',
					hidden:true,
				},{
					text : '企业法人',
					align:'center',
					dataIndex : 'legalPerson',
					flex:0.8,
					sortable: true
				},{
					text : '统一社会信用代码',
					align:'center',
					dataIndex : 'uscc',
					flex:1.2,
					sortable: true
				},{
					text : '模型名称',
					align:'center',
					dataIndex : 'modelName',
					flex:1.5,
					sortable: true
				},{
					text : '更新日期',
					align:'center',
					dataIndex : 'updateTime',
					flex:1,
					sortable: true,
					renderer:function(value){
						if(value instanceof Date){
							return new Date(value).faormat("Y-m-d");
						}else{
							return value.slice(0,10);
						}
					}
				},{
					text : '状态',
					align:'center',
					dataIndex : 'state',
					flex:1.5,
					sortable: true
				},{
					xtype:'actioncolumn',
					text : '管理',
					align:'center',
					flex:2,
					sortable: true,
					items : [{
						getClass: function(v, meta, rec) {
							var scoreState = rec.get('scoreState');
							var visible = rec.get('visible');
							var applyReportState = rec.get('applyReportState');
							if(applyReportState == 1&& scoreState ==0){
	                            return 'confirmstatus';
	                        }else if(applyReportState == 1 && scoreState == 1 && visible == 1){
	                        	 return 'investigate';
	                        }else if(applyReportState == 1 && scoreState == 2 && visible == 1){
	                        	return 'editInvestigate';
	                        }else if(applyReportState == 1 && scoreState == 4 && visible == 1){
	                        	return 'score';
	                        }else if(applyReportState == 1 && scoreState == 5 && visible == 1){
	                        	return 'manageScoreReport';
							}else if(applyReportState == 1 && scoreState == 6){
	                        	return 'history';
							}else if(applyReportState == 0 && scoreState == 0){
								if(visible == 1){
		                            return 'lockAccounts';
		                        }else{
		                        	return 'unlockAccount';
		                        }
							}
	                    },
	                    getTip: function(v, meta, rec) {
	                    	var visible = rec.get('visible')*1;
	                    	var scoreState = rec.get('scoreState');
	                    	var applyReportState = rec.get('applyReportState');
	                    	if(applyReportState == 1&& scoreState ==0){
	                            return '确认信息';
	                        }else if(applyReportState == 1 && scoreState == 1 && visible == 1){
	                        	return '调查';
	                        }else if(applyReportState == 1 && scoreState == 2 && visible == 1){
	                        	return '编辑调查';
	                        }else if(applyReportState == 1 && scoreState == 4 && visible == 1){
	                        	return '评分';
	                        }else if(applyReportState == 1 && scoreState == 5 && visible == 1){
	                        	return '管理评分报告';
		                    }else if(applyReportState == 1 && scoreState == 6){
	                        	return '查看历史信息';
		                    }else if(applyReportState == 0 && scoreState == 0 ){
		                    	if(visible == 1){
		                            return '点击锁定';
		                        }else{
		                        	return '点击激活';
		                        }
							}
	                    },
	                    handler: function(grid, rowIndex, colIndex) {
	                        this.up('grid').fireEvent('score',grid, rowIndex, colIndex);
	                    }
					},"-",{
						getClass: function(v, meta, rec) {
							var scoreState = rec.get('scoreState');
							var modelID = rec.get('modelID');
							var applyReportState = rec.get('applyReportState');
							if(applyReportState == 1 && scoreState == 2 && modelID != null){
	                            return 'endInvestigate';
	                        }else if(applyReportState == 1 && scoreState == 4 && modelID != null){
	                        	return 'confirmScore';
	                        }else if(applyReportState == 1 && scoreState == 5 && modelID != null){
	                        	return 'viewBasicInfo';
	                        }else if(applyReportState == 0 && scoreState == 0 && modelID == null){
								return 'assignmentModel';
							}
	                    },
	                    getTip: function(v, meta, rec) {
	                    	var scoreState = rec.get('scoreState');
	                    	var modelID = rec.get('modelID');
	                    	var applyReportState = rec.get('applyReportState');
							if(applyReportState == 1 && scoreState == 2 && modelID != null){
	                            return '结束调查';
	                        }else if(applyReportState == 1 && scoreState == 4 && modelID != null){
	                        	return '确认评分';
	                        }else if(applyReportState == 1 && scoreState == 5 && modelID != null){
	                        	return '查看企业基本信息';
	                        }else if(applyReportState == 0 && scoreState == 0 && modelID == null){
								return '分配模型';
							}
	                    },
	                    handler: function(grid, rowIndex, colIndex) {
	                        this.up('grid').fireEvent('doNextStep',grid, rowIndex, colIndex);
	                    }
					}],
				}]
			} ],
			
		});
		this.callParent(arguments);
	}
});
