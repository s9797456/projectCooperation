Ext.QuickTips.init();// 错误悬浮提示
var editUploadFileStore = Ext.create("PM.store.enterprise.uploadFiles");
editUploadFileStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('investigate form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			entID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var editEntShareholderStore = Ext.create("PM.store.enterprise.entShareholderWin");
editEntShareholderStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('investigate form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			entID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});

var editEntExecutivesStore = Ext.create("PM.store.enterprise.entExecutivesWin");
editEntExecutivesStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('investigate form textfield[name=uuid]')[0].getValue();
	var new_params = { 
    		entID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
Ext.define('PM.view.enterprise.investigate',{
	extend:'Ext.window.Window',
	alias:"widget.investigate",
	layout: 'anchor',
	border : false,
	width : 1250,
	height : 650,
	autoScroll:true,
	modal : true,
	maximizable :true,
	autoShow : true,
	closable:true, 
	resizable:false,
	title:"编辑调查",
	initComponent : function(){
		Ext.apply(this,{
			items:[{
				xtype:'tabpanel',
				layout:'anchor',
				anchor:'100%',
				activeTab:0,//当前标签为第1个tab（从0开始索引）
				autoShow : true,
				items:[{
					xtype : 'form',
					title: '信用调查',
					defaults : {
						xtype:'fieldset',
						layout: 'column',
						collapsible: true,
						style : 'margin: 0px 10px 10px 10px;',
						title: '基本信息'
					},
					items : [{
				        defaults:{
				        	readOnly : true,
							labelStyle : 'display:table;font-weight:bold;',
							style : 'margin: 5px 10px;',
							labelSeparator : ':',
							labelAlign : 'right',// 标签对齐方式
							labelWidth : 250,// 标签宽度
							width : 450,
							xtype : 'textfield',
				        },
				        items :[{
				            fieldLabel: '企业名称',
				            name: 'name',
				        },{
				        	fieldLabel: 'resultID',
				            name: 'resultID',
				            hidden : true
				        },{
				        	fieldLabel: 'uuid',
				            name: 'uuid',
				            hidden : true
				        },{
				            fieldLabel: '客户初评反馈意见',
				            name: 'opinion',
				        },]
					},{
				        title: '调查评分项'
					}],
					buttons:[{
						action : 'saveNew',
						text : '保存'
					},{
						action : 'close',
						text : '关闭'
					}]
					},{
						xtype: 'container',
						itemId : 'extraDataBase',
						layout : 'anchor',
						anchor: '100%',
						title : '信用信息',
						style : 'margin: 5px 10px;',
					},{
						xtype: 'container',
						itemId : 'baseInfoContainer',
						layout : 'anchor',
						anchor: '100%',
						title : '基本信息',
						style : 'margin: 5px 10px;',
						defaults:{
							xtype:'fieldset',
							style : 'margin: 0px 10px 10px 10px;'
						},
						items:[{
							title: '基本信息',
							items:[{
							border:false,
							xtype:'form',
							itemId:'baseInfoForm',
							layout: 'column',
							defaults : {
								xtype : 'textfield',
								margin : '5',
								labelSeparator : "：",
								labelAlign : 'right',
								labelWidth : '190px',
								width : 450,
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
							}]
						}]
					},{ 
						title:'股东信息',
						items:[{
						xtype : 'grid',
						itemId:'entShareholderGrid',
						layout : 'anchor',
						margin:'5 5 5 5',
						anchor: '100%',
						forceFit : true,
						store: editEntShareholderStore,
						columnLines : true,
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
							store : editEntShareholderStore,
							dock : 'bottom',
							displayInfo : true
						}]
					}]
					},{
						title:'高层信息',
						items:[{
							xtype : 'grid',
							itemId:'entExecutivesGrid',
							layout : 'anchor',
							margin:'5 5 5 5',
							anchor:'100%',
							store : editEntExecutivesStore,
							forceFit:true,
							columnLines : true,
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
								store : editEntExecutivesStore,
								dock : 'bottom',
								displayInfo : true
							}]
						}]
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
						store : editUploadFileStore,
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
								emptyText : '请输入文件类型......'  ,
								fieldLabel : '搜索',
								labelWidth : 50,
								xtype : 'searchfield',
								store : editUploadFileStore,
								onTrigger2Click : function(obj,e){ // 节点单击事件监听
									var name = this.getRawValue();
									var editUploadFileStore = Ext.ComponentQuery.query("investigate tabpanel grid#uploadFilesGird")[0];
									editUploadFileStore.getStore().load({
										params : {
											entID: Ext.ComponentQuery.query("investigate tabpanel form textfield[name=uuid]")[0].getValue(),
											name : name
										}
									});
								}
							}
		                },{
							xtype : 'pagingtoolbar',
							store : editUploadFileStore,
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