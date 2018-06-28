var viewPerUploadFileStore = Ext.create("PM.store.perScore.perUploadFiles");
viewPerUploadFileStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('viewPerBasicInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var viewPerEducationStore = Ext.create("PM.store.perScore.educationInfo");
viewPerEducationStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('viewPerBasicInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});

var viewPerTrainStore = Ext.create("PM.store.perScore.trainInfo");
viewPerTrainStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('viewPerBasicInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var viewPerCareerStore = Ext.create("PM.store.perScore.careerInfo");
viewPerCareerStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('viewPerBasicInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var viewPerSkillsStore = Ext.create("PM.store.perScore.skillsInfo");
viewPerSkillsStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('viewPerBasicInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});

Ext.define('PM.view.perScore.viewPerBasicInfoWin',{
	extend:'Ext.window.Window',
	alias:"widget.viewPerBasicInfoWin",
	layout : 'anchor',
	border : false,
	width : 1250,
	height : 650,
	modal : true,
	autoShow : true,
	autoScroll:true,
	title:"个人基本信息",
	initComponent : function() {
		Ext.apply(this,{
			items:[{
				xtype:'tabpanel',
				layout:'anchor',
				anchor:'100%',
				activeTab:0,//当前标签为第1个tab（从0开始索引）
				autoShow : true,
				items:[{
					xtype : 'form',
					title : "评分",
					itemId : 'scoreMarkForm',
					defaults : {
						xtype:'fieldset',
						layout: 'column',
						collapsible: true,
						style : 'margin: 0px 10px 10px 10px;'
					},
					items : [{
				        title: '基本信息',
				        defaults:{
				        	readOnly : true,
							labelStyle : 'display:table;font-weight:bold;',
							style : 'margin: 5px 10px;',
							labelSeparator : ':',
							labelAlign : 'right',// 标签对齐方式
							labelWidth : 250,// 标签宽度
							width : 450,
							xtype : 'textfield'
				        },
				        items :[{
				            fieldLabel: '姓名',
				            name: 'name',
				            html :'<div>dddddddddddd</div>'
				        },{
				        	fieldLabel: '身份证号',
				        	name:'IDCard'
				        },{
				            fieldLabel: '评级编号',
				            itemId : 'encodingFieldset',
				            emptyText :'请填写评级编码',
				            allowBlank : false,
				            readOnly : false,
				            name: 'encoding'
				        },{
				        	fieldLabel: '管理员意见',
				        	name:'adminOpinion',
				            width:920
				        },{
				        	fieldLabel: '客户初评意见',
				        	name:'customerOpinion',
				            width:920
				        },{
				        	fieldLabel: 'uuid',
				            name: 'uuid',
				            hidden : true
				        },{
				        	fieldLabel: 'resultid',
				            name: 'resultid',
				            hidden : true
				        }]
					},{
						xtype:'grid',
						itemId : "scoreGrid",
						columnLines : true,
						stripeRows : true,
						forceFit : true,
						store:'perScore.scoreMarks',
						columns : [ Ext.create('Ext.grid.RowNumberer', {
							width:35,
							renderer : function(value, metadata, record, rowIndex) {
								return rowIndex + 1;
							}
						}), {
							text : '指标名称',
							align:'center',
							dataIndex : 'indexName'
						},{
							text : '单项得分',
							align:'center',
							dataIndex : 'indexScore',
							renderer:function(val){
								 return Ext.util.Format.number(parseFloat(val), '00.00');
							},
							width:20
						}, {
							text : '取档及计分',
							align:'center',
							dataIndex : 'optionName',
						}]
					},{
						 xtype:'fieldset',
						 title : '初评评分',
						 layout: 'column',
						 defaults:{
								labelStyle : 'display:table;font-weight:bold;',
								labelAlign : 'right',// 标签对齐方式
								width : 150,
								margin : '0 0 0 -50'
					     },
					     items:[{
					    	 fieldLabel:'总分',
					    	 xtype : 'displayfield',
					    	 name : 'preliminaryScore'
					     },{
					    	 fieldLabel:'级别',
					    	 xtype : 'displayfield',
					    	 name : 'preliminaryLevel'
					     }]
					},{
						xtype:'fieldset',
						title:'调整因素',
						itemId : 'adjustOptionFieldSet',
						defaults:{
					        style : 'margin: 0px 10px 10px 10px;'
					    }
					}]
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
						store: viewPerEducationStore,
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
							store : viewPerEducationStore,
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
							store : viewPerTrainStore,
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
								store : viewPerTrainStore,
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
							store : viewPerCareerStore,
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
								store : viewPerCareerStore,
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
							store : viewPerSkillsStore,
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
								store : viewPerSkillsStore,
								dock : 'bottom',
								displayInfo : true
							}]
						}]
					}]
				},{
					xtype: 'container',
					itemId : 'extraDataBase',
					layout : 'anchor',
					anchor: '100%',
					title : '信用信息',
					style : 'margin: 5px 10px;',
					items : [{
						title : "ces"
					},{
						title : "ces"
					}],
					items:[{
				    	border:false,
						xtype:'form',
						itemId:'extraDataForm',
						layout: 'column',
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
						store : viewPerUploadFileStore,
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
								store : viewPerUploadFileStore,
								onTrigger2Click : function(obj,e){ // 节点单击事件监听
									var name = this.getRawValue();
									var viewPerUploadFileStore = Ext.ComponentQuery.query("scoreMarkingInfoWin tabpanel grid#perUploadFilesGird")[0];
									viewPerUploadFileStore.getStore().load({
										params : {
											perID: Ext.ComponentQuery.query("scoreMarkingInfoWin tabpanel form textfield[name=uuid]")[0].getValue(),
											name : name
										}
									});
								}
							}
		                },{
							xtype : 'pagingtoolbar',
							store : viewPerUploadFileStore,
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