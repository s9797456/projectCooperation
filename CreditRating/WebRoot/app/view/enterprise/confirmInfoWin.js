//确认信息窗口
Ext.define('PM.view.enterprise.confirmInfoWin',{
	extend:'Ext.window.Window',
	alias:"widget.confirmInfoWin",
	layout: 'anchor',
	border : false,
	width : 950,
	height : 650,
	autoScroll:true,
	modal : true,
	autoShow : true,
	title:"信息展示",
	closable:true, 
	maximizable :true,
	initComponent : function(){
		Ext.apply(this,{
			items : [{
				xtype:'tabpanel',
				layout:'anchor',
				anchor:'100%',
				activeTab:0,//当前标签为第1个tab（从0开始索引）
				items: [{
					xtype:'form',
					layout: 'column',
					margin:'5 5 5 5',
					title:"基本信息",
					defaults : {
						xtype : 'textfield',
						margin : '5',
						labelSeparator : "：",
						labelAlign : 'right',
						labelWidth : '150px',
						width : 350,
						readOnly : true
					},
					items:[{
						fieldLabel : "隐藏企业id",
						name : 'uuid',
						hidden : true
					},{
						fieldLabel : "企业名称",
						name : 'name'
					},{
						fieldLabel : "统一社会信用代码",
						name : 'uscc'
					},{
						fieldLabel : "登记机关",
						name : 'regisOrg'
					},{
						fieldLabel : "公司性质",
				        name : 'entType'
					},{
						fieldLabel : "注册资本",
						name : 'regiCapital'
					},{
						fieldLabel : "币种",
						name : 'currencyType'
					},{
						xtype : 'datefield',
						fieldLabel : "企业成立日期",
						name : 'setupDate'
					},{
						xtype : 'datefield',
						fieldLabel : "企业发照日期",
						name : 'issueDate'
					},{
						xtype : 'datefield',
						fieldLabel : "营业开始日期",
						name : 'startDate'
					},{
						xtype : 'datefield',
						fieldLabel : "营业结束日期",
						name : 'endDate'
					},{
						fieldLabel : "法定代表人",
						name : 'legalPerson'
					},{
						fieldLabel : "人数规模",
						name : 'scale'
					},{
						fieldLabel : "所属行业",
				        name : 'industry'
					},{
						fieldLabel : "公司网址",
						name : 'website'
					},{
						fieldLabel : "电子邮箱",
						name : 'email'
					},{
						fieldLabel : "电话",
						name : 'tel'
					},{
						fieldLabel : "传真",
						name : 'fax'
					},{
						fieldLabel : "邮政编码",
						name : 'zipCode'
					},{
						xtype : 'textarea',
						fieldLabel : "企业地址",
						name : 'address',
						width : 710,
						height:70
					},{
						xtype : 'textarea',
						fieldLabel : "企业简介",
						name : 'brief',
						width : 710,
						height:140
					},{
						xtype : 'textarea',
						fieldLabel : "经营范围",
						name : 'businessScope',
						width : 710,
						height:140
					},{
						xtype: 'checkboxgroup',
				        fieldLabel: '驳回选项',
				        columns: 4,
				        vertical: true,
				        items: [
				            { boxLabel: '基本信息', name: 'baseInfoState', inputValue: 0,width:120},
				            { boxLabel: '股权结构', name: 'shareholderState', inputValue: 0,width:120},
				            { boxLabel: '高层信息', name: 'executivesState', inputValue: 0,width:120}, 
				            { boxLabel: '附件信息', name: 'uploadFileState', inputValue: 0,width:120},
				            { boxLabel: '指标信息', name: 'pushModelState', inputValue: 0 ,width:120},
				            { boxLabel: '财务数据', name: 'financeState', inputValue: 0 ,width:120},
				        ]
					},{
						xtype : 'textarea',
						fieldLabel:'评审人员的相关意见',
						name : 'dealContent',
						width : 710,
						height:70,
						readOnly : false,
						allowBlank:false
					}],
					buttons: [{
						text : '信息通过',
						action : 'confirmMsg'
					},{
						text : '信息驳回',
						action : 'rejectMsg'
					}]
			    },{
					xtype : 'panel',
					itemId : 'entShareholderPanel',
					layout : 'anchor',
					anchor: '100%',
					title : '股东信息',
					items : [{
						xtype : 'grid',
						layout : 'anchor',
						margin:'5 5 5 5',
						anchor: '100%',
						forceFit : true,
						store: entShareholderStore,
						collapsible : true,
						titleCollapse : true,
						columnLines : true,
						title : '股权结构列表',
						itemId : "entShareholderGrid",
						columns : {
							items : [{
								text:'uuid',
								dataIndex : 'uuid',
								hidden : true
							},{
								text:'股东名称',
								dataIndex : 'name',
								width : 180
							},{
								text:'持股比例(%)',
								dataIndex : 'stockpercent',
								width : 50
							},{
								text:'股东类型',
								dataIndex : 'type',
								width : 100
							},{
								text:'认缴金额',
								dataIndex : 'shouldcapi',
							},{
								text:'实缴金额',
								dataIndex : 'realcapi',
							}],
							defaults: {
								align: 'center',
								menuDisabled: true,
								sortable : false
							}
						},
						dockedItems : [ {
							xtype : 'pagingtoolbar',
							store : entShareholderStore,
							itemId : 'entShareholderPagingtoolbar',
							dock : 'bottom',
							displayInfo : true
						}],
					}]
			    	
				},{
					xtype : 'panel',
					itemId : 'entExecutivesPanel',
					layout : 'anchor',
					anchor: '100%',
					title : '高层信息',
					items :[{
						xtype : 'grid',
						layout : 'anchor',
						title : '公司高级管理层信息',
						margin:'5 5 5 5',
						anchor:'100%',
						store : entExecutivesStore,
						itemId : "entExecutivesGrid",
						collapsible : true,
						titleCollapse : true,
						columnLines : true,
						forceFit : true,
						columns : {
							items : [{
								text:'uuid',
								dataIndex : 'uuid',
								hidden : true
							},{
								text:'姓名',
								dataIndex : 'name',
								width : 120
							},{
								text:'性别',
								dataIndex : 'gender',
								width :50
							},{
								text:'年龄',
								dataIndex : 'age',
								width :50
							},{
								text:'职位',
								dataIndex : 'job',
								width : 180
							},{
								text:'学历',
								dataIndex : 'education',
								width : 100
							},{
								text:'部门',
								dataIndex : 'department',
							},{
								text:'身份证',
								dataIndex : 'IDCard',
							}],
							defaults: {
								align: 'center',
								menuDisabled: true,
								sortable : false
							}
						},
						dockedItems : [ {
							xtype : 'pagingtoolbar',
							store : entExecutivesStore,
							itemId : 'entExecutivesPagingtoolbar',
							dock : 'bottom',
							displayInfo : true
						}],
					}]
				},{
					xtype : 'panel',
					itemId : 'uploadFilesPanel',
					layout : 'anchor',
					anchor: '100%',
					title : '附件',
					items:[{
						xtype : 'grid',
						title : '附件列表',
						anchor:'100%',
					    columnLines : true,
						autoScroll: true,
						store : confUploadFileStore,
						itemId : 'uploadFilesGird',
						columns : {
					    	items:[
                                {text: '文件类型',flex:2,dataIndex: 'type'},
                                {text: '文件路径',flex:2,dataIndex: 'fileUrl',hidden:true},
					   			{text: '文件名',flex:2,dataIndex: 'fileName',hidden:true},
					   			{text: '大小', flex:2,dataIndex: 'fileSize',renderer:function(v){
					   			    return Ext.util.Format.fileSize(v);
					   			}},
					   			{
					   				xtype:'actioncolumn',
					   				flex:1,
					   			    text:'管理',
					   			    items: [{
					   			    	icon: _ctxPath+'/Images/icon/down.png',
					   			    	tooltip: '下载',
					   			    	handler: function(grid, rowIndex, colIndex) {
					   			    		this.up('grid').fireEvent('download',grid, rowIndex, colIndex);
					   			    	}
					   			    },{
					   			    	icon: _ctxPath+'/Images/icon/preview.png',
					   			    	tooltip: '查看',
					   			    	handler: function(grid, rowIndex, colIndex) {
					   			    		this.up('grid').fireEvent('preview',grid, rowIndex, colIndex);
					   			    	}
					   			    }]
					   			} ],
					   			defaults: {
					   			align: 'center',
					   			menuDisabled: true,
					   			sortable : false
					   		}
					   	},
					   	dockedItems : [ {
							dock : 'top',
							xtype : 'toolbar',
							items : {
								itemId : 'topHis',
								width : 300,
								emptyText : '请输入文件类型（证件名称）'  ,
								fieldLabel : '搜索',
								labelWidth : 50,
								xtype : 'searchfield',
								store : confUploadFileStore,
								onTrigger2Click : function(obj,e){ // 节点单击事件监听
									var name = this.getRawValue();
									var confUploadFileStore = Ext.ComponentQuery.query("confirmInfoWin tabpanel grid#uploadFilesGird")[0];
									confUploadFileStore.getStore().load({
										params : {
											entID: Ext.ComponentQuery.query("confirmInfoWin tabpanel form textfield[name=uuid]")[0].getValue(),
											name : name
										}
									});
								}
							}
		                },{
							xtype : 'pagingtoolbar',
							store : confUploadFileStore,
							dock : 'bottom',
							displayInfo : true
						}]
					}]
				}]
			}]
		});
		this.callParent(arguments);
	}
});