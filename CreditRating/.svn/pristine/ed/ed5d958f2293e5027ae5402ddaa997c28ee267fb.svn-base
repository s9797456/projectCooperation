Ext.QuickTips.init();// 错误悬浮提示
var editPerUploadFileStore = Ext.create("PM.store.perScore.perUploadFiles");
editPerUploadFileStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('investigateInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var editPerEducationStore = Ext.create("PM.store.perScore.educationInfo");
editPerEducationStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('investigateInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});

var editPerTrainStore = Ext.create("PM.store.perScore.trainInfo");
editPerTrainStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('investigateInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var editPerCareerStore = Ext.create("PM.store.perScore.careerInfo");
editPerCareerStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('investigateInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var editPerSkillsStore = Ext.create("PM.store.perScore.skillsInfo");
editPerSkillsStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('investigateInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
Ext.define('PM.view.perScore.investigateInfoWin',{
	extend:'Ext.window.Window',
	alias:"widget.investigateInfoWin",
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
				            fieldLabel: '姓名',
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
								fieldLabel : "UUID",
								name : 'uuid',
								hidden : true
							},{
								fieldLabel : "姓名",
								name : 'name'
							},{
								fieldLabel : "身份证号",
								name : 'idcard'
							},{
								fieldLabel : "曾用名",
								name : 'usedName'
							},{
								fieldLabel : "性别",
								name : 'gender'
							},{
								fieldLabel : "政治面貌",
						        name : 'politicalOutlook'
							},{
								fieldLabel : "民族",
								name : 'nation'
							},{
								fieldLabel : "国籍",
								name : 'nationality'
							},{
								fieldLabel : "籍贯",
								name : 'nativePlace'
							},{
								fieldLabel : "婚姻状况",
								name : 'maritalStatus'
							},{
								fieldLabel : "生育情况",
								name : 'fertilityCondition'
							},{
								fieldLabel : "身份证签发机关",
								name : 'idissuingAgency'
							},{
								fieldLabel : "现居住地邮政编码",
								name : 'presentZipCode'
							},{
								xtype : 'datefield',
								fieldLabel : "身份证有效期始",
								name : 'idtermStart'
							},{
								xtype : 'datefield',
								fieldLabel : "身份证有效期至",
								name : 'idtermEnd'
							},{
								xtype : 'textarea',
								fieldLabel : "现居住地址",
								name : 'presentAddress',
								width : 710,
								height:70
							}]
						}]
					},{ 
						title:'教育经历',
						items:[{
						xtype : 'grid',
						itemId:'educationGrid',
						layout : 'anchor',
						margin:'5 5 5 5',
						anchor: '100%',
						forceFit : true,
						store: editPerEducationStore,
						columnLines : true,
						columns : {
							items : [{
								text:'uuid',
								dataIndex : 'uuid',
								hidden : true
							},{
								text:'起始时间',
								dataIndex : 'startTime',
							},{
								text:'结束时间',
								dataIndex : 'endTime',
							},{
								text:'学历',
								dataIndex : 'education',
								width : 50
							},{
								text:'毕业院校',
								dataIndex : 'university',
								width : 180
							},{
								text:'专业',
								dataIndex : 'major',
								width : 100
							},{
								text:'毕业证编号',
								dataIndex : 'diplomaNo',
								width : 100
							}],
							defaults: {
								align: 'center',
								menuDisabled: true,
								sortable : false
							}
						},
						dockedItems : [ {
							xtype : 'pagingtoolbar',
							store : editPerEducationStore,
							dock : 'bottom',
							displayInfo : true
						}]
					}]
					},{
						title:'培训经历',
						items:[{
							xtype : 'grid',
							itemId:'trainGrid',
							layout : 'anchor',
							margin:'5 5 5 5',
							anchor:'100%',
							store : editPerTrainStore,
							forceFit:true,
							columnLines : true,
							columns : {
								items : [{
									text:'uuid',
									dataIndex : 'uuid',
									hidden : true
								},{
									text:'起始时间',
									dataIndex : 'startTime',
								},{
									text:'结束时间',
									dataIndex : 'endTime',
								},{
									text:'培训机构',
									dataIndex : 'trainOrg',
									width : 120
								},{
									text:'培训地址',
									dataIndex : 'trainAddress',
									width :120
								},{
									text:'培训内容',
									dataIndex : 'trainContent',
									width :120
								},{
									text:'培训证书编号',
									dataIndex : 'certificateNo',
									width : 100
								}],
								defaults: {
									align: 'center',
									menuDisabled: true,
									sortable : false
								}
							},
							dockedItems : [ {
								xtype : 'pagingtoolbar',
								store : editPerTrainStore,
								dock : 'bottom',
								displayInfo : true
							}]
						}]
					},{
						title:'职业生涯',
						items:[{
							xtype : 'grid',
							itemId:'careerGrid',
							layout : 'anchor',
							margin:'5 5 5 5',
							anchor:'100%',
							store : editPerCareerStore,
							forceFit:true,
							columnLines : true,
							columns : {
								items : [{
									text:'uuid',
									dataIndex : 'uuid',
									hidden : true
								},{
									text:'起始时间',
									dataIndex : 'startTime',
								},{
									text:'结束时间',
									dataIndex : 'endTime',
								},{
									text:'就职单位',
									dataIndex : 'inauguralUnit',
									width : 120
								},{
									text:'单位规模',
									dataIndex : 'unitScale',
									width :50
								},{
									text:'从事行业',
									dataIndex : 'industry',
									width :100
								},{
									text:'工作年限',
									dataIndex : 'workingLife',
									width : 50
								},{
									text:'职务',
									dataIndex : 'post',
									width : 80
								},{
									text:'平均薪资',
									dataIndex : 'averageSalary',
									width : 80
								}],
								defaults: {
									align: 'center',
									menuDisabled: true,
									sortable : false
								}
							},
							dockedItems : [ {
								xtype : 'pagingtoolbar',
								store : editPerCareerStore,
								dock : 'bottom',
								displayInfo : true
							}]
						}]
					},{
						title:'专业技能',
						items:[{
							xtype : 'grid',
							itemId:'skillsGrid',
							layout : 'anchor',
							margin:'5 5 5 5',
							anchor:'100%',
							store : editPerSkillsStore,
							forceFit:true,
							columnLines : true,
							columns : {
								items : [{
									text:'uuid',
									dataIndex : 'uuid',
									hidden : true
								},{
									text:'技能名',
									dataIndex : 'skillName',
									width : 120
								},{
									text:'熟练度',
									dataIndex : 'skillProficiency',
									width :120
								}],
								defaults: {
									align: 'center',
									menuDisabled: true,
									sortable : false
								}
							},
							dockedItems : [ {
								xtype : 'pagingtoolbar',
								store : editPerSkillsStore,
								dock : 'bottom',
								displayInfo : true
							}]
						}]
					}]
				},{
					xtype : 'panel',
					itemId : 'perUploadFilesPanel',
					layout : 'anchor',
					anchor: '100%',
					title : '附件',
					items:[{
						xtype : 'grid',
						title : '附件列表',
						anchor:'100%',
					    columnLines : true,
						autoScroll: true,
						store : editPerUploadFileStore,
						itemId : 'perUploadFilesGird',
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
								store : editPerUploadFileStore,
								onTrigger2Click : function(obj,e){ // 节点单击事件监听
									var name = this.getRawValue();
									var editPerUploadFileStore = Ext.ComponentQuery.query("investigateInfoWin tabpanel grid#perUploadFilesGird")[0];
									editPerUploadFileStore.getStore().load({
										params : {
											perID: Ext.ComponentQuery.query("investigateInfoWin tabpanel form textfield[name=uuid]")[0].getValue(),
											name : name
										}
									});
								}
							}
		                },{
							xtype : 'pagingtoolbar',
							store : editPerUploadFileStore,
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