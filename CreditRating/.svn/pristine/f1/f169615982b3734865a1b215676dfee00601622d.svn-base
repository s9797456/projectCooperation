Ext.Loader.setPath('Ext.ux',_ctxPath+'/Resources/extjs/examples/ux');
Ext.require('Ext.ux.form.SearchField');
var perUploadFilesStore = Ext.create("PM.store.perScore.perUploadFiles");
perUploadFilesStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('personInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var educationStore = Ext.create("PM.store.perScore.educationInfo");
educationStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('personInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});

var trainStore = Ext.create("PM.store.perScore.trainInfo");
trainStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('personInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var careerStore = Ext.create("PM.store.perScore.careerInfo");
careerStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('personInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var skillsStore = Ext.create("PM.store.perScore.skillsInfo");
skillsStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('personInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			perID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
Ext.apply(Ext.form.VTypes,{
	fileTypeValid:function(val,field){//val指这里的文本框值，field指这个文本框组件，大家要明白这个意思
		var str = val.substring(val.lastIndexOf(".")+1,val.length);
		if(str=="pdf"){
			return true;
		}
    },
    fileTypeValidText : "请上传文件格式为pdf"
});
var perID='';
var resultID='';
var scoreStatus;
var perBaseInfoStore = Ext.create("PM.store.perScore.perBaseInfo");
Ext.define('PM.controller.perScore.perScoreManage',{
	extend:'Ext.app.Controller',
	views:['perScore.perScoreManageView','perScore.personInfoWin','perScore.showEducationDetail',
	       'perScore.showTrainDetail','perScore.showCareerDetail','perScore.showSkillsDetail',
	       'perScore.investigateInfoWin','perScore.scoreMarkingInfoWin','perScore.manageReportInfoWin',
	       'perScore.viewPerBasicInfoWin','perScore.industrysInfoWin','perScore.historyInfoWin',
	       'perScore.editPerAdvantageReason','perScore.editPerScoreReason','perScore.editPerScoreSummary'],
	stores:['perScore.perScoreLists','perScore.perUploadFiles','perScore.educationInfo',
	        'perScore.trainInfo','perScore.careerInfo','perScore.skillsInfo','perScore.perBaseInfo',
	        'perScore.scoreMarks','perScore.scoreReports','perScore.reporttemplates',
	        'perScore.modelCombos','PerSecondModelCombos'],
	init: function() {//通过init函数来监听视图事件，控制视图与控制器的交互
		this.control({
			'perScoreManageView grid#perScoreManageViewGrid' : {
				score : this.perScore,
				doNextStep:this.doNextStep//更改状态，进入下一步骤
			},
			'perScoreManageView button[action=refresh]' :{
				click : this.refresh//评分列表刷新
			},
			'perScoreManageView button[action=query]' :{
				click : this.query//评分列表查询
			},
			'personInfoWin tabpanel panel#educationPanel grid#educationGrid' :{
				select : this.showEducationDetail//确认信息页面的教育信息选项卡grid的select事件
			},
			'personInfoWin tabpanel panel#trainPanel grid#trainGrid' :{
				select : this.showTrainDetail//确认信息页面的培训经历选项卡grid的select事件
			},
			'personInfoWin tabpanel panel#careerPanel grid#careerGrid' :{
				select : this.showCareerDetail//确认信息页面的职业生涯选项卡grid的select事件
			},
			'personInfoWin tabpanel panel#skillsPanel grid#skillsGrid' :{
				select : this.showSkillsDetail//确认信息页面的专业技能选项卡grid的select事件
			},
			'personInfoWin button[action=rejectMsg]' :{
				click : this.rejectSubmitInfo//确认信息页面信息驳回按钮
			},
			'personInfoWin button[action=confirmMsg]' :{
				click : this.confirmSubmitInfo//确认信息页面信息通过按钮
			},
			'investigateInfoWin tabpanel container#extraDataBase' : {
				boxready : this.loadExtraPerData//调查页面初始化信用信息页面
			},
			'investigateInfoWin tabpanel container#baseInfoContainer' : {
				boxready : this.loadPerBaseInfo//调查页面初始化基本信息页面
			},
			'investigateInfoWin tabpanel panel#perUploadFilesPanel' : {
				boxready : this.loadUploadFile
			},
			'investigateInfoWin button' : {
				click : this.updateOrClose//更新或保存调查信息
			},
			'scoreMarkingInfoWin tabpanel container#extraDataBase' : {
				boxready : this.loadExtraPerData
			},
			'scoreMarkingInfoWin tabpanel container#baseInfoContainer' : {
				boxready : this.loadPerBaseInfo
			},
			'scoreMarkingInfoWin tabpanel panel#perUploadFilesPanel' : {
				boxready : this.loadUploadFile
			},
			'scoreMarkingInfoWin button' : {
				click : this.saveScore//保存评分
			},
			'viewPerBasicInfoWin tabpanel container#extraDataBase' : {
				boxready : this.loadExtraPerData
			},
			'viewPerBasicInfoWin tabpanel container#baseInfoContainer' : {
				boxready : this.loadPerBaseInfo
			},
			'viewPerBasicInfoWin tabpanel panel#perUploadFilesPanel' : {
				boxready : this.loadUploadFile
			},
			'tabpanel grid#perUploadFilesGird':{
				download : this.downloadUploadFile,//选项卡下载按钮
				preview : this.previewUploadFile//选项卡查看按钮
			},
			'manageReportInfoWin grid#scoreReportGrid':{
				editReason:this.editReason//管理评分报告编辑功能
			},
			'industrysInfoWin combo#personFirstIndustry':{
				select:this.firstIndustry
			},
			'industrysInfoWin button[action=giveIndustry]' : {
				click: this.giveIndustry//赋予行业
			},
			'historyInfoWin button[action=downloadHistory]' :{
				click : this.downloadHistory//下载历史信息
			},
			'editPerScoreReason button':{
				click: this.saveScoreReasonOrClose
			},
			'editPerAdvantageReason button':{
				click: this.saveAdvantageReasonOrClose
			},
			'editPerScoreSummary button':{
				click: this.saveScoreSummaryOrClose
			},
			'fieldset#reportFieldset button[action=generateReport]':{
				click:this.generateReport
			},
			'fieldset#reportFieldset button[action=resetScoring]':{
				click:this.resetScoring
			},
			'fieldset#reportFieldset button[action=secondScoring]':{
				click:this.secondScoring
			},
			'button[action=downLoadReport]':{
				click:this.downLoadReport
			},
			'button[action=downLoadWord]':{
				click:this.downLoadWord
			},
			'button[action=uploadReport]':{
				click:this.uploadReport
			},
		});
	},
	refresh:function(){
		var grid = Ext.ComponentQuery.query('perScoreManageView grid')[0];
         grid.getStore().load();
	},
	query:function(){
		var grid = Ext.ComponentQuery.query('perScoreManageView grid')[0];
		var queryType=Ext.ComponentQuery.query('perScoreManageView grid combo[name=queryType]')[0];
		var input=Ext.ComponentQuery.query('perScoreManageView grid textfield[name=searchmatter]')[0];
		grid.getStore().getProxy().extraParams = {
			 name:queryType.getValue(),
			 value:input.getValue()
		};
		grid.getStore().load();
	},
	perScore : function(grid, rowIndex, colIndex){
		var rec = grid.getStore().getAt(rowIndex);
		var modelName = rec.get("modelName");
		if(modelName == null || modelName.trim() == ""){
			Ext.Msg.alert("提示","请选择相应的模型才能操作");
		}else{
			var rec = grid.getStore().getAt(rowIndex);
			var scoreState = rec.get('scoreState');
			var userName=rec.get("userName");
			var applyReportState = rec.get('applyReportState');
			String.prototype.replaceAll = function(s1,s2){
				return this.replace(new RegExp(s1,"gm"),s2);
			};
			if(applyReportState==0 && scoreState ==0){
				if(rec.get('visible')==1){
			    	Ext.Msg.confirm("请确认", "是否真的要锁定该用户？", function(button, text){
			            if (button == "yes") {
			                Ext.Ajax.request({
			                	waitMsg: '正在提交数据',
			        	  		waitTitle: '提示',
			                	url: _ctxPath+"/control/person/manage/updateVisible.do",
			                    params: {
			                    	userName:userName,
			    					visible:(rec.get('visible')==1)?0:1
			                    },
			                    success : function(response, options){
			                    	var responseJSON = Ext.decode(response.responseText);
			                    	if(responseJSON.status==true){
			                    		Ext.Msg.alert('操作提示', responseJSON.msg);
			                    	}else{
			                    		Ext.Msg.alert(responseJSON.msg, responseJSON.result);
			                    	}
			                    	grid.getStore().load();
			                    },
			        			failure : function(response, options){
			        				failAjaxTips();
			        			}
			                });
			            }
			        });
			    }else{
			    	Ext.Msg.confirm("请确认", "是否真的要激活该用户？", function(button, text){
			            if (button == "yes") {
			                Ext.Ajax.request({
			                	waitMsg: '正在提交数据',
			        	  		waitTitle: '提示',
			                	url: _ctxPath+"/control/person/manage/updateVisible.do",
			                    params: {
			                    	userName:userName,
			    					visible:(rec.get('visible')==1)?0:1
			                    },
			                    success : function(response, options){
			                    	var responseJSON = Ext.decode(response.responseText);
			                    	if(responseJSON.status==true){
			                    		Ext.Msg.alert('操作提示', responseJSON.msg);
			                    	}else{
			                    		Ext.Msg.alert(responseJSON.msg, responseJSON.result);
			                    	}
			                    	grid.getStore().load();
			                    },
			        			failure : function(response, options){
			        				failAjaxTips();
			        			}
			                });
			            }
			        });
			    
			    }
			}
			else if(applyReportState==1&& scoreState ==0){
				//确认信息
				Ext.widget("personInfoWin");
				var rec = grid.getStore().getAt(rowIndex);
				this.removeExtraItems();
				perBaseInfoStore.load({
					params : {
						perID : rec.get('perID')
					},
					scope : this,
					callback : function(records, operation, success){
						var form = Ext.ComponentQuery.query('personInfoWin form')[0];
						var basic = form.getForm();//得到BasicForm
						basic.loadRecord(records[0]);
						var new_params = { 
								perID: rec.get('perID'),
					    };
						educationStore.load();
						careerStore.load();
						trainStore.load();
						skillsStore.load();
						perUploadFilesStore.load();
					}
				});
			}else if(applyReportState == 1 && (scoreState == 1||scoreState==2)){//编辑和调查
				Ext.widget('investigateInfoWin');
				var formOne = Ext.ComponentQuery.query('investigateInfoWin')[0];
				formOne.suspendLayout = true;
				perID=rec.get('perID');;
				resultID=rec.get('resultID');;
				scoreStatus=scoreState;
				this.removeExtraItems();
				var loadmask=new Ext.LoadMask(Ext.getBody(),{
					msg:'页面信息正在生成中,请稍候'
				});
				loadmask.show();
 				var objWindow  = Ext.ComponentQuery.query('investigateInfoWin form fieldset')[1];
				Ext.Ajax.request({
					url :_ctxPath+'/control/perScore/manage/surveryScoreView.do',
					method : 'post',
					params : {
						resultID : resultID
					},
					success : function(response, option) {
						var responseJSON = Ext.decode(response.responseText);
						if(responseJSON.msg.length!=0){
							Ext.Msg.alert("提示",responseJSON.msg);
						}
						Ext.ComponentQuery.query('investigateInfoWin form textfield[name=name]')[0].setValue(responseJSON.Name);
						Ext.ComponentQuery.query('investigateInfoWin form textfield[name=uuid]')[0].setValue(responseJSON.perID);
						Ext.ComponentQuery.query('investigateInfoWin form textfield[name=opinion]')[0].setValue(responseJSON.opinion);
						Ext.ComponentQuery.query('investigateInfoWin form textfield[name=resultID]')[0].setValue(resultID);
						//生成调查项
						var scoreList = responseJSON.indexs;
						var labelSeparator = ':';
						var labelStyle = 'display:table;font-weight:bold;';
						var num = 1;
						for(var key in scoreList){
							var indexVOs=scoreList[key];
							var objSet={
									title:key,
									layout:'column',
									collapsible:true,
									style : 'margin: 0px 5px 5px 5px;',
									width: 1150,
							};
							var p=Ext.widget("fieldset",objSet);
							Ext.Array.each(indexVOs,function(indexVO,index){
								var obj = {
			        					fieldLabel : indexVO.name,
			        					labelStyle : labelStyle,
			        					name : "IndexMap["+num+"].value",
			    						style : 'margin: 5px 5px;',
			    						displayField : 'name',
				    					valueField : 'value',
				    					forceSelection : true,// 用户不能自己输入,只能选择列表中有的记录
			    						labelSeparator : labelSeparator,
			    						labelAlign : 'right',// 标签对齐方式
			    						labelWidth : 200,// 标签宽度
			    						width: 550,
			    						allowBlank:false,
					        	};
								var store = new Array();
								var s = Ext.create('Ext.data.Store',{
		        					fields: [{
		        						name:'name',type:'string'
		        					},{
		        						name:'value',type:'string'
		        					}]
		        				});
								store[index] = s;
								var optionVOs = indexVO.options;
								Ext.Array.each(optionVOs,function(optionVO,indexValue){
									var o = {
			        					name: optionVO.name,
			        					value: optionVO.value
			        				};
									s.add(o);
									if(optionVOs[indexValue].selected!=null){
										Ext.apply(obj,{
				    						value : optionVOs[indexValue].value+"",
				        				});
									}
								});
								Ext.apply(obj,{
		    						store : store[index],
		    						queryMode : 'local',
		    						emptyText : '请选择...',
		    						autoSelect : false,
		    						addAllSelector: true,
		        				});
								var pc = Ext.widget("combo",obj);
								if(pc.getRawValue()==''){
									pc.addCls('decline');
									if(indexVO.insert!=''){
										pc.setRawValue(indexVO.insert);
									}
								}else{
									pc.addCls('accept');
									/*Ext.apply(pc,{
			    						triggerCls:'accept',
			        				});*/
								}
								pc.addListener('select',function(){
									pc.removeCls('decline');
									pc.addCls('accept');
								});

								var objId = {
									name  : "IndexMap["+num+"].name",
									value : indexVO.uuid,
					        	};
								var pcH = Ext.widget("hidden",objId);
								num += 1;
								p.add(pcH);
								p.add(pc);
							});
							objWindow.add(p);
						}
						formOne.suspendLayout = false;
						formOne.doLayout();
						loadmask.hide();
					},
					failure : function(response, options){
						Ext.Msg.alert("提示","查询失败,请重新尝试");
						loadmask.hide();
					}
				});
			}else if(applyReportState == 1 && scoreState==4){
				var me = this;
				Ext.widget('scoreMarkingInfoWin');
				var formWindow  = Ext.ComponentQuery.query('scoreMarkingInfoWin')[0];
				formWindow.suspendLayout = true;
				this.removeExtraItems();
				perID=rec.get('perID');;
				resultID=rec.get('resultID');
				scoreStatus=scoreState;
				var gridScore  = Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form grid#scoreGrid')[0];
				var gridScoreStore = gridScore.getStore();
				var objWindow  = Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset#adjustOptionFieldSet')[0];
				var formWindow1  = Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form')[0];
				loadmask=new Ext.LoadMask(grid,{
					msg:'正在评分中,请稍候'
				});
				loadmask.show();
				gridScoreStore.on('beforeload',function(store,option){
					var params={
						resultID : rec.get('resultID')
					};
					Ext.apply(store.proxy.extraParams,params);
				});
				gridScoreStore.load({
					 scope: this,
				     callback: function(records, operation, success) {
				    	 var responseJSON = Ext.decode(operation.response.responseText);
				    	 if(responseJSON.status==true){
	    	
					    	 Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form textfield#encodingFieldset')[0].setValue(responseJSON.encoding).setReadOnly(true);
					    	 //初评分数等级
					    	 var preliminaryScore = responseJSON.preliminaryScore;
					    	 var preliminaryLevel = responseJSON.preliminaryLevel;
					    	 //总评分数等级
					    	 var finalScore = responseJSON.finalScore;
					    	 var finalLevel = responseJSON.finalLevel;
					    	 //给基本信息进行赋值
					    	 Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form textfield[name=name]')[0].setValue(responseJSON.name);
							 Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form textfield[name=IDCard]')[0].setValue(responseJSON.IDCard);
							 Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form textfield[name=uuid]')[0].setValue(responseJSON.uuid);
							 Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form textfield[name=resultid]')[0].setValue(responseJSON.resultid);
							 Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form textfield[name=adminOpinion]')[0].setValue(responseJSON.adminOpinion);
							 Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form textfield[name=customerOpinion]')[0].setValue(responseJSON.customerOpinion);
							 //初评分数及等级赋值
							 Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset displayfield[name=preliminaryScore]')[0].setValue(preliminaryScore);
							 Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset displayfield[name=preliminaryLevel]')[0].setValue(preliminaryLevel);
							 //调整因素的
							 var reasonList = responseJSON.items;
							 Ext.Array.each(reasonList,function(reason,index){
								 var obj = {
										boxLabel : reason.boxLabel,
										name : reason.name,
										inputValue: reason.inputValue,
										checked:reason.checked
								 };
								 var p = Ext.widget("checkbox",obj);
								 var judgeChange = true;
								 p.on('change',function(cbg, newValue, oldValue, eOpts){
									 var str = cbg.boxLabel;
									 var totalScore2ID = Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset displayfield[itemId=totalScore2ID]')[0];//显示最终得分组件
									 var totalScore2LevelID = Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset displayfield[itemId=totalScore2LevelID]')[0];//显示最终级别 组件
									 var adjustScore = parseFloat(totalScore2ID.getValue());
									 if(cbg.checked){
										 adjustScore += parseFloat(str.substring(str.indexOf("(")+1,str.indexOf(")")));;
									 }else{
										 adjustScore -= parseFloat(str.substring(str.indexOf("(")+1,str.indexOf(")")));;
									 }
									 if(adjustScore>100){
										 judgeChange = false;
										 p.setValue(false);
										 Ext.Msg.alert("警告","分数超过一百分！！！");
									 }else{
										if(judgeChange){
											 Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset displayfield[name=preliminaryLevel]')[0].setValue(preliminaryLevel);
											 totalScore2ID.setValue(adjustScore);
											 totalScore2LevelID.setValue(me.getScoreLevel(adjustScore)); 
										}
									 }
								 });
								 objWindow.add(p);
							 });
							 var fieldset2=new Ext.form.FieldSet({
								  layout: 'column',
								  title : '最终评分',
								  defaults:{
										labelStyle : 'display:table;font-weight:bold;',
										labelAlign : 'right',// 标签对齐方式
										width : 150
							      },
								  items:[{
									fieldLabel: '最终评分总分',
									itemId:'totalScore2ID',
									name : 'finalScore',
									value:finalScore==0?preliminaryScore:finalScore,
									xtype : 'displayfield'	
								  },{
									fieldLabel: '最终评分等级',
									itemId:'totalScore2LevelID',
									name : 'finalLevel',
									value:finalScore==0?preliminaryLevel:finalLevel,
									xtype : 'displayfield'
								  }]
							 });
							 formWindow1.add(fieldset2);
							 formWindow.suspendLayout = false;
							 formWindow.doLayout();
							 loadmask.hide(); 
				    	 }else{
				    		 Ext.Msg.alert('操作提示',responseJSON.msg );
				    		 loadmask.hide(); 
				    	 }
				    	
				     }
				});
			
			}else if(applyReportState == 1 && scoreState == 5){
				var view=new Ext.Window({id:'perManageWin',width:800,height:300,modal:true});
				var scoreReport=Ext.widget('manageReportInfoWin');
				view.setTitle('管理评分报告('+rec.get('name')+')');
				perID=rec.get('perID');;
				resultID=rec.get('resultID');
				scoreStatus=scoreState;
				var scoreReportGrid=Ext.ComponentQuery.query('manageReportInfoWin grid')[0];
				this.removeExtraItems();
				var scoreReportStore=scoreReportGrid.getStore();
				scoreReportStore.removeAll();
				scoreReportStore.on('beforeload',function(store,option){
					var params={resultID : rec.get('resultID' )};
					Ext.apply(store.proxy.extraParams,params);
				});
				scoreReportStore.load({
					scope:this,
					callback:function(records,operation,success){
						view.add(scoreReport);
						var flag=true;
						Ext.Array.each(records,function(record){
							if(record.get('status')=='0'){
								flag=false;
								return;
							};
						});
						if(flag){
							var fieldset=new Ext.form.FieldSet({
								title:'生成报告('+rec.get('name')+')',
								itemId:'reportFieldset',
								layout:'column',
							    defaults:{
							    	margin:'5 0 5 10'
							    },
								items:[{
									xtype:'combo',
									fieldLabel:'选择模版',
									displayField : 'name',
									valueField : 'value',
									itemId:'reporttempCombo',
									store:'perScore.reporttemplates'
								},{
									xtype:'button',
									action:'generateReport',
									text:'生成报告'
								},{
									xtype:'button',
									action:'downLoadReport',
									hidden:true,
									text:'下载报告',
								},{
									xtype:'button',
									action:'downLoadWord',
									hidden:true,
									text:'下载Word'
								},{
									xtype:'button',
									action:'resetScoring',
									text:'重新评分',
									tooltip:"对当次评分不满意,进行重评"
								},{
									xtype:'button',
									action:'secondScoring',
									text:'下一次评分',
									tooltip:"结束当次评分,进行下一次评分"
								},{  
									xtype:'button',
									hidden:true,
									action:'uploadReport',
									text: '上 传',  
									
								}]
							});
						}
						Ext.Ajax.request({
							url :_ctxPath+'/control/perScore/manage/judgeFileExist.do',
							method : 'post',
							async: false,
							params:{
								resultID:rec.get('resultID')
							},
							success : function(response, option) {
								if(view.items.length>2){
									view.remove(view.items.last());
								}
								var responseJSON = Ext.decode(response.responseText);
								if(responseJSON.judgeReportExist==true){
									if(flag){
										view.add(fieldset);
										Ext.ComponentQuery.query('button[action=generateReport]')[0].show();
										Ext.ComponentQuery.query('button[action=downLoadReport]')[0].show();
										Ext.ComponentQuery.query('button[action=downLoadWord]')[0].show();
										Ext.ComponentQuery.query('button[action=uploadReport]')[0].show();
										//Ext.ComponentQuery.query('button[action=downLoadImage]')[0].setDisabled(false);
										//Ext.ComponentQuery.query('button[action=downLoadPdf]')[0].setDisabled(false);
									}
								}else{
									if(flag){
										var obj = {
												hidden : true,
												hideMode:'offsets',
												xtype:'container',
												//html:'<iframe src="'+_ctxPath+'/Charts/reportFinImage.jsp?entID='+entID+'" frameborder="0" width="100%" height="100%"></iframe>'
											};
										var p = Ext.widget("button",obj);
										fieldset.add(p);
										view.add(fieldset);
									}
								}
							},
							failure:function(response, option){
								
						}
					});
				}
			  });
				view.show(); 
			}else if(applyReportState == 1 && scoreState == 6){
				Ext.widget('historyInfoWin');
				var formOne = Ext.ComponentQuery.query('historyInfoWin')[0];
				formOne.suspendLayout = true;
				perID=rec.get('perID');;
				resultID=rec.get('resultID');;
				scoreStatus=scoreState;
				this.removeExtraItems();
				Ext.Ajax.request({
					url :_ctxPath+'/control/perScore/manage/lookHistoryView.do',
					method : 'post',
					params : {
						historyID : perID
					},
					success : function(response, option) {
						var responseJSON = Ext.decode(response.responseText);
						if(responseJSON.msg.length!=0){
							Ext.Msg.alert("提示",responseJSON.msg);
						}
						Ext.ComponentQuery.query('historyInfoWin form textfield[name=name]')[0].setValue(responseJSON.name);
						Ext.ComponentQuery.query('historyInfoWin form textfield[name=uuid]')[0].setValue(perID);
						Ext.ComponentQuery.query('historyInfoWin form textfield[name=IDCard]')[0].setValue(responseJSON.IDCard);
						Ext.ComponentQuery.query('historyInfoWin form textfield[name=finalscore]')[0].setValue(responseJSON.finalscore);
						Ext.ComponentQuery.query('historyInfoWin form textfield[name=finallevel]')[0].setValue(responseJSON.finallevel);
						Ext.ComponentQuery.query('historyInfoWin form textfield[name=historyExist]')[0].setValue(responseJSON.historyExist);
					},
					failure : function(response, options){
						Ext.Msg.alert("提示","查询失败,请重新尝试");
					}
				});
			}
		}
	},
	//确认编辑调查结束及评分结束
	doNextStep:function(grid, rowIndex, colIndex){
		var store=grid.getStore();
    	var rec = grid.getStore().getAt(rowIndex);
		var scoreState = rec.get('scoreState');
		var modelID = rec.get('modelID');
		var applyReportState = rec.get('applyReportState');
		if(applyReportState == 1 && scoreState == 2){
			Ext.Msg.confirm('请确认', '确定结束编辑调查吗?', function(button) {
				if (button =='yes') {
					Ext.Ajax.request({
						url :_ctxPath+'/control/perScore/manage/endSurvery.do',
						method : 'POST',
						params : {
							resultID : rec.get('resultID'),
						},
						success : function(response, option) {
								store.load();
								var responseJSON = Ext.decode(response.responseText);
								Ext.MessageBox.alert("提示消息", responseJSON.msg);
						},
						failure : function() {
							Ext.MessageBox.alert("提示消息", "更新失败,请稍后尝试！");
						}
					});
				}
			});
		}else if(applyReportState == 1 && scoreState == 4){
			Ext.Msg.confirm('请确认', '确定结束评分吗?', function(button) {
				if (button =='yes') {
					Ext.Ajax.request({
						url :_ctxPath+'/control/perScore/manage/endScore.do',
						method : 'POST',
						params : {
							resultID : rec.get('resultID'),
						},
						success : function(response, option) {
								store.load();
								var responseJSON = Ext.decode(response.responseText);
								Ext.MessageBox.alert("提示消息", responseJSON.msg);
						},
						failure : function() {
							Ext.MessageBox.alert("提示消息", "更新失败,请稍后尝试！");
						}
					});
				}
			});
		}else if(applyReportState == 1 && scoreState == 5){
			this.removeExtraItems();
			perID=rec.get('perID');
			resultID=rec.get('resultID');
			scoreStatus=scoreState;
			Ext.widget('viewPerBasicInfoWin');
			var formWindow  = Ext.ComponentQuery.query('viewPerBasicInfoWin')[0];
			formWindow.suspendLayout = true;
			var gridScore  = Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form grid#scoreGrid')[0];
			var gridScoreStore = gridScore.getStore();
			var objWindow  = Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form fieldset#adjustOptionFieldSet')[0];
			var formWindow1  = Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form')[0];
			loadmask=new Ext.LoadMask(grid,{
				msg:'处理中,请稍候'
			});
			loadmask.show();
			gridScoreStore.on('beforeload',function(store,option){
				var params={
					resultID : rec.get('resultID')
				};
				Ext.apply(store.proxy.extraParams,params);
			});
			gridScoreStore.load({
				 scope: this,
			     callback: function(records, operation, success) {
			    	 var responseJSON = Ext.decode(operation.response.responseText);
			    	 if(responseJSON.status==true){
    	
				    	 Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form textfield#encodingFieldset')[0].setValue(responseJSON.encoding).setReadOnly(true);
				    	 //初评分数等级
				    	 var preliminaryScore = responseJSON.preliminaryScore;
				    	 var preliminaryLevel = responseJSON.preliminaryLevel;
				    	 //总评分数等级
				    	 var finalScore = responseJSON.finalScore;
				    	 var finalLevel = responseJSON.finalLevel;
				    	 //给基本信息进行赋值
				    	 Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form textfield[name=name]')[0].setValue(responseJSON.name);
						 Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form textfield[name=IDCard]')[0].setValue(responseJSON.IDCard);
						 Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form textfield[name=uuid]')[0].setValue(responseJSON.uuid);
						 Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form textfield[name=resultid]')[0].setValue(responseJSON.resultid);
						 Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form textfield[name=adminOpinion]')[0].setValue(responseJSON.adminOpinion);
						 Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form textfield[name=customerOpinion]')[0].setValue(responseJSON.customerOpinion);
						 //初评分数及等级赋值
						 Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form fieldset displayfield[name=preliminaryScore]')[0].setValue(preliminaryScore);
						 Ext.ComponentQuery.query('viewPerBasicInfoWin tabpanel form fieldset displayfield[name=preliminaryLevel]')[0].setValue(preliminaryLevel);
						 //调整因素的
						 var reasonList = responseJSON.items;
						 Ext.Array.each(reasonList,function(reason,index){
							 var obj = {
									boxLabel : reason.boxLabel,
									name : reason.name,
									inputValue: reason.inputValue,
									checked:reason.checked,
									disabled:true
							 };
							 var p = Ext.widget("checkbox",obj);
							 var judgeChange = true;
							 objWindow.add(p);
						 });
						 var fieldset2=new Ext.form.FieldSet({
							  layout: 'column',
							  title : '最终评分',
							  defaults:{
									labelStyle : 'display:table;font-weight:bold;',
									labelAlign : 'right',// 标签对齐方式
									width : 150
						      },
							  items:[{
								fieldLabel: '最终评分总分',
								itemId:'totalScore2ID',
								name : 'finalScore',
								value:finalScore==0?preliminaryScore:finalScore,
								xtype : 'displayfield'	
							  },{
								fieldLabel: '最终评分等级',
								itemId:'totalScore2LevelID',
								name : 'finalLevel',
								value:finalScore==0?preliminaryLevel:finalLevel,
								xtype : 'displayfield'
							  }]
						 });
						 formWindow1.add(fieldset2);
						 formWindow.suspendLayout = false;
						 formWindow.doLayout();
						 loadmask.hide(); 
			    	 }else{
			    		 Ext.Msg.alert('操作提示',responseJSON.msg );
			    		 loadmask.hide(); 
			    	 }
			    	
			     }
			});
		}else if(applyReportState == 0 && scoreState == 0 && modelID == null){
			perID=rec.get('perID');
			Ext.widget('industrysInfoWin');
			Ext.ComponentQuery.query('industrysInfoWin form hidden[name=uuid]')[0].setValue(perID);
		}
	},
	//编辑调查和调查关闭和保存按钮
	updateOrClose : function(obj, e, eOpts){
		var judgeAction = obj.action;
		var gridPanel = Ext.ComponentQuery.query('perScoreManageView grid#perScoreManageViewGrid')[0];
		if(judgeAction=="saveNew"){
			//提交
			var objWindow  = Ext.ComponentQuery.query('investigateInfoWin form')[0];
			var basic = objWindow.getForm();//得到BasicForm
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',  
			  		clientValidation : false,
			  		url :_ctxPath+'/control/perScore/manage/saveSurveryScore.do',
			  		params : {
			  			resultID : Ext.ComponentQuery.query('investigateInfoWin form textfield[name=resultID]')[0].getValue()
			  		},
			  		success: function (form, action) {
			  			Ext.ComponentQuery.query('investigateInfoWin')[0].close();
			  			gridPanel.getStore().load();
			  			Ext.Msg.alert('操作提示', "数据保存成功");
			  	    },
			  	    failure: function(form,action){
			  	    	Ext.Msg.alert('操作提示', "数据保存失败,请重新尝试");
			  	    }
				});
			}
		}else if(judgeAction=="close"){
			Ext.ComponentQuery.query('investigateInfoWin')[0].close();
		}
	},
	//保存评分数据
	saveScore:function(obj, e, eOpts){
		var judgeAction = obj.action;
		var gridPanel = Ext.ComponentQuery.query('perScoreManageView grid#perScoreManageViewGrid')[0];
		if(judgeAction=='keepScore'){
			var objWindow  = Ext.ComponentQuery.query('scoreMarkingInfoWin form')[0];
			var basic = objWindow.getForm();//得到BasicForm
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		url :_ctxPath+'/control/perScore/manage/savePersonOfScore.do',
			  		params:{
			  			preliminaryScore : Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset displayfield[name=preliminaryScore]')[0].getValue(),
						preliminaryLevel: Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset displayfield[name=preliminaryLevel]')[0].getValue(),
						finalScore :  Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset displayfield[itemId=totalScore2ID]')[0].getValue(),
						finalLevel : Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset displayfield[itemId=totalScore2LevelID]')[0].getValue(),
						resultID : Ext.ComponentQuery.query('scoreMarkingInfoWin tabpanel form fieldset textfield[name=resultid]')[0].getValue()
			  		},
			  		success: function (form, action) {
			  			Ext.ComponentQuery.query('scoreMarkingInfoWin')[0].close();
			  			gridPanel.getStore().load();
			  			Ext.Msg.alert('操作提示', action.result.msg);
			  	    },
			  	    failure: function(form,action){
			  	    	Ext.Msg.alert('操作提示', "数据保存失败,请重新尝试");
			  	    }
				});
			}
		}
	},
	//管理评分报告编辑
	editReason:function(grid, rowIndex, colIndex){
		var rec=grid.getStore().getAt(rowIndex);
		if(rec.get('type')=="0"){
			Ext.widget('editPerScoreReason');
			Ext.ComponentQuery.query('editPerScoreReason form hidden[name=uuid]')[0].setValue(rec.get('uuid'));
			var fieldset1=Ext.ComponentQuery.query('editPerScoreReason form fieldset#reasonAndExplain')[0];
			var adjustReason = rec.get("adjustReason");
			if(adjustReason==null || adjustReason.trim()==""){
				var displayItems=new Ext.form.field.Display({
					fieldLabel:'暂无调整项',
					labelWidth : '500px'
				});
				Ext.ComponentQuery.query('editPerScoreReason button[action=saveScoreReason]')[0].setDisabled(true);
				fieldset1.add(displayItems);
			}else{
				var adjustReasonStr=adjustReason.split(",");
				for(var i=0;i<adjustReasonStr.length;i++){
					if(adjustReasonStr[i]!=null && adjustReasonStr[i].trim()!=""){
						var adjustReasonStr2=adjustReasonStr[i].split("#");
						var obj={
								value:adjustReasonStr2[0]
						};
						var adjustReasonField=Ext.widget('displayfield',obj);
						fieldset1.add(adjustReasonField);
						var adjustReason=new Ext.form.field.TextArea({
							allowBlank:false,
							width:750,
							value : adjustReasonStr2.length>1?adjustReasonStr2[1]:null
						});
						fieldset1.add(adjustReasonField);
						fieldset1.add(adjustReason);
					} 
				}
			}
		}else if(rec.get('type')=="1"){
			Ext.widget('editPerAdvantageReason');
			Ext.ComponentQuery.query('editPerAdvantageReason form hidden[name=uuid]')[0].setValue(rec.get('uuid'));
			Ext.ComponentQuery.query('editPerAdvantageReason form textarea[name=advantageReason]')[0].setValue(rec.get('advantageReason'));
		}else if(rec.get('type')=="2"){
			Ext.widget('editPerScoreSummary');
			Ext.ComponentQuery.query('editPerScoreSummary form hidden[name=uuid]')[0].setValue(rec.get('uuid'));
			Ext.ComponentQuery.query('editPerScoreSummary form#scoreSummaryForm textarea[name=scoreSummary]')[0].setValue(rec.get('scoreSummary'));
		}
		
	},
	//调整因素说明保存或关闭
	saveScoreReasonOrClose:function(obj, e, eOpts){
		var objThis = this;
		var judgeAction = obj.action;
		var gridPanel = Ext.ComponentQuery.query('manageReportInfoWin grid#scoreReportGrid')[0];
		var objWindow=Ext.ComponentQuery.query('editPerScoreReason form#scoreReasonForm')[0];
		var adjustField=Ext.ComponentQuery.query('editPerScoreReason form fieldset displayfield');
		var reasonField=Ext.ComponentQuery.query('editPerScoreReason form fieldset textarea');
		var uuid=Ext.ComponentQuery.query('editPerScoreReason form hidden')[0].getValue();
		//var view=Ext.ComponentQuery.query('entScoreManageView')[0];
		var view=Ext.getCmp("perManageWin");
		var adjustReason = new String();
		for(var i=0;i<adjustField.length;i++){
			if(adjustField[i].getValue()!=null && adjustField[i].getValue()!=""){
				var adjustValue=adjustField[i].getValue();
				if(reasonField.length !=0){
					var reasonValue=reasonField[i].getValue();
				}
				if(i<(adjustField.length-1)){
				adjustReason=adjustReason+adjustValue+"#"+reasonValue+",";
				}else if(i==(adjustField.length-1)){
					adjustReason=adjustReason+adjustValue+"#"+reasonValue;
				}
			}
		}
		var basic = objWindow.getForm();//得到BasicForm
		if(judgeAction=='saveScoreReason'){
			if(basic.isValid()){
				Ext.Ajax.request({
					url :_ctxPath+'/control/perScore/manage/addOrUpdateFillReportOption.do',
					method : 'post',
					params : {
						adjustReason : adjustReason,
						resultID : Ext.ComponentQuery.query('editPerScoreReason form#scoreReasonForm hidden[name=uuid]')[0].getValue()
					},
					success : function(response, option) {
						var record=Ext.decode(response.responseText);
						Ext.ComponentQuery.query('editPerScoreReason')[0].close();
						objThis.operaSocreReport(gridPanel,view);
						gridPanel.getStore().load();
			  			Ext.Msg.alert('操作提示', record.msg);
					},
					failure:function(response, option){
						Ext.Msg.alert('操作提示', "保存失败");
					}
				
				});
			}
		}else{
			gridPanel.getStore().load();
			Ext.ComponentQuery.query('editPerScoreReason')[0].close();
		}
		
	},
	saveAdvantageReasonOrClose:function(obj, e, eOpts){
		var objThis = this;
		var judgeAction = obj.action;
		var objWindow=Ext.ComponentQuery.query('editPerAdvantageReason form#advantageReasonForm')[0];
		//var view=Ext.ComponentQuery.query('entScoreManageView')[0];
		var view=Ext.getCmp("perManageWin");
		var gridPanel = Ext.ComponentQuery.query('manageReportInfoWin grid#scoreReportGrid')[0];
		var basic = objWindow.getForm();//得到BasicForm
		if(judgeAction=='saveAdvantageReason'){
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		url :_ctxPath+'/control/perScore/manage/addOrUpdateFillReportOption.do',
			  		params : {
			  			resultID : Ext.ComponentQuery.query('editPerAdvantageReason form#advantageReasonForm hidden[name=uuid]')[0].getValue()
			  		},
			  		success: function (form, action) {
			  			Ext.ComponentQuery.query('editPerAdvantageReason')[0].close();
			  			objThis.operaSocreReport(gridPanel,view);
			  			Ext.Msg.alert('操作提示', "操作成功");
			  	    },
			  	    failure: function(form,action){
			  	    	Ext.Msg.alert('操作提示', "数据保存失败,请重新尝试");
			  	    }
				});
			}
		}else{
			gridPanel.getStore().load();
			Ext.ComponentQuery.query('editPerAdvantageReason')[0].close();
		}
	},
	saveScoreSummaryOrClose:function(obj, e, eOpts){
		var objThis = this;
		var judgeAction = obj.action;
		var objWindow=Ext.ComponentQuery.query('editPerScoreSummary form#scoreSummaryForm')[0];
		//var view=Ext.ComponentQuery.query('entScoreManageView')[0];
		var view=Ext.getCmp("perManageWin");
		var gridPanel = Ext.ComponentQuery.query('manageReportInfoWin grid#scoreReportGrid')[0];
		var basic = objWindow.getForm();//得到BasicForm
		if(judgeAction=='saveScoreSummary'){
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		url :_ctxPath+'/control/perScore/manage/addOrUpdateFillReportOption.do',
			  		params : {
			  			resultID : Ext.ComponentQuery.query('editPerScoreSummary form#scoreSummaryForm hidden[name=uuid]')[0].getValue()
			  		},
			  		success: function (form, action) {
			  			Ext.ComponentQuery.query('editPerScoreSummary')[0].close();
			  			objThis.operaSocreReport(gridPanel,view);
			  			Ext.Msg.alert('操作提示', "操作成功");
			  	    },
			  	    failure: function(form,action){
			  	    	Ext.Msg.alert('操作提示', "数据保存失败,请重新尝试");
			  	    }
				});
			}
		}else{
			gridPanel.getStore().load();
			Ext.ComponentQuery.query('editPerScoreSummary')[0].close();
		}
	},
	//展示教育具体信息
	showEducationDetail : function (obj, record, index, eOpts){
    	var record = obj.getSelection()[0];
		var p = Ext.widget("showEducationDetail");
		var objWindow = Ext.ComponentQuery.query('personInfoWin tabpanel panel#educationPanel')[0];
		if(objWindow.items.length>1){
			objWindow.items.last().close();
		}
		p.getForm().setValues({
			uuid:record.get('uuid'),
			startTime:record.get('startTime'),
			endTime:record.get('endTime'),
			education:record.get('education'),
			university:record.get('university'),
			major:record.get('major'),
			diplomaNo:record.get('diplomaNo'),
			remarks:record.get('remarks'),
    	});
		objWindow.add(p);
		p.setTitle(record.get('education'));
		this.panelTextReadOnly(p.items);
		var t = p.dockedItems;
		var t1 = t.items[1];
	},
	//展示培训具体信息
	showTrainDetail : function (obj, record, index, eOpts){
    	var record = obj.getSelection()[0];
		var p = Ext.widget("showTrainDetail");
		var objWindow = Ext.ComponentQuery.query('personInfoWin tabpanel panel#trainPanel')[0];
		if(objWindow.items.length>1){
			objWindow.items.last().close();
		}
		p.getForm().setValues({
			uuid:record.get('uuid'),
			startTime:record.get('startTime'),
			endTime:record.get('endTime'),
			trainOrg:record.get('trainOrg'),
			certificateNo:record.get('certificateNo'),
			trainAddress:record.get('trainAddress'),
			trainContent:record.get('trainContent'),
			remarks:record.get('remarks'),
    	});
		objWindow.add(p);
		p.setTitle(record.get('trainOrg'));
		this.panelTextReadOnly(p.items);
		var t = p.dockedItems;
		var t1 = t.items[1];
	},
	//展示职业生涯具体信息
	showCareerDetail : function (obj, record, index, eOpts){
    	var record = obj.getSelection()[0];
		var p = Ext.widget("showCareerDetail");
		var objWindow = Ext.ComponentQuery.query('personInfoWin tabpanel panel#careerPanel')[0];
		if(objWindow.items.length>1){
			objWindow.items.last().close();
		}
		p.getForm().setValues({
			uuid:record.get('uuid'),
			startTime:record.get('startTime'),
			endTime:record.get('endTime'),
			inauguralUnit:record.get('inauguralUnit'),
			unitScale:record.get('unitScale'),
			industry:record.get('industry'),
			workingLife:record.get('workingLife'),
			post:record.get('post'),
			averageSalary:record.get('averageSalary'),
			remarks:record.get('remarks'),
    	});
		objWindow.add(p);
		p.setTitle(record.get('inauguralUnit'));
		this.panelTextReadOnly(p.items);
		var t = p.dockedItems;
		var t1 = t.items[1];
	},
	//展示专业技能具体信息
	showSkillsDetail : function (obj, record, index, eOpts){
    	var record = obj.getSelection()[0];
		var p = Ext.widget("showSkillsDetail");
		var objWindow = Ext.ComponentQuery.query('personInfoWin tabpanel panel#skillsPanel')[0];
		if(objWindow.items.length>1){
			objWindow.items.last().close();
		}
		p.getForm().setValues({
			uuid:record.get('uuid'),
			skillName:record.get('skillName'),
			skillProficiency:record.get('skillProficiency'),
			remarks:record.get('remarks'),
    	});
		objWindow.add(p);
		p.setTitle(record.get('skillName'));
		this.panelTextReadOnly(p.items);
		var t = p.dockedItems;
		var t1 = t.items[1];
	},
	downloadUploadFile : function(grid, rowIndex, colIndex){
		var rec = grid.getStore().getAt(rowIndex);
    	window.open(_ctxPath+"/control/perScore/manage/downLoadUploadFile.do?fileName="+rec.get('type')+"&fileUrl="+rec.get('fileUrl'));
    	return;
	},
	previewUploadFile : function(grid, rowIndex, colIndex){
		var rec = grid.getStore().getAt(rowIndex);
		Ext.Ajax.request({
			url:_ctxPath+'/control/perScore/manage/lookUploadFile.do',
			method:'post',
			params:{
				fileUrl:rec.get('fileUrl')
			},
			success : function(response, option) {
				new Ext.Window({
			    	 header: true,
			         border: false,
			         baseCls: '',
			         shadow: false,
			         frame: false,
			         width: 500,
			         height: 350,
			         closable :true,
			         closeAction :'destroy',
			         maximizable: false,
			         resizable: false,
			         hideMode: 'offsets',
			         constrain: false,
			         layout: 'fit',
			         items: {
			              html: "<img src="+_ctxPath+rec.get('fileUrl')+" width='500' height='350'>"
			         },
			    }).show();
			},
			failure : function(response, options){
				Ext.Msg.alert("附件不存在，无法查看");
			}
		});
	},
	//信息驳回
	rejectSubmitInfo : function(obj, e, eOpts){
		var baseInfoState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=baseInfoState]')[0].getSubmitValue();
		var educationState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=educationState]')[0].getSubmitValue();
		var trainState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=trainState]')[0].getSubmitValue();
		var careerState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=careerState]')[0].getSubmitValue();
		var skillState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=skillState]')[0].getSubmitValue();
		var uploadFileState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=uploadFileState]')[0].getSubmitValue();
		
		if(baseInfoState!=null||educationState!=null||trainState!=null
				||careerState!=null||skillState!=null||uploadFileState!=null){
			this.winButoonAjax("0");
		}else{
			Ext.Msg.alert('操作提示', "请至少选择一个驳回项目");
		}
	},
	//信息通过
	confirmSubmitInfo : function (obj, e, eOpts){
		var baseInfoState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=baseInfoState]')[0].getSubmitValue();
		var educationState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=educationState]')[0].getSubmitValue();
		var trainState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=trainState]')[0].getSubmitValue();
		var careerState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=careerState]')[0].getSubmitValue();
		var skillState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=skillState]')[0].getSubmitValue();
		var uploadFileState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=uploadFileState]')[0].getSubmitValue();
		
		if(baseInfoState!=null||educationState!=null||trainState!=null
				||careerState!=null||skillState!=null||uploadFileState!=null){	
			Ext.Msg.alert('操作提示', "您已选择驳回选项,无法信息通过,请仔细核对后修改！");
		}else{
			this.winButoonAjax("1");
		}
	},
	winButoonAjax : function (stats){
		var baseInfoState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=baseInfoState]')[0].getSubmitValue();
		var educationState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=educationState]')[0].getSubmitValue();
		var trainState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=trainState]')[0].getSubmitValue();
		var careerState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=careerState]')[0].getSubmitValue();
		var skillState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=skillState]')[0].getSubmitValue();
		var uploadFileState = Ext.ComponentQuery.query('personInfoWin form checkbox[name=uploadFileState]')[0].getSubmitValue();
		if(Ext.ComponentQuery.query('personInfoWin form textarea[name=dealContent]')[0].isValid()){
			Ext.Ajax.request({
				url:_ctxPath+'/control/perScore/manage/adminOpinionUpdateOrAdd.do',
				method:'post',
				params:{
					perID : Ext.ComponentQuery.query('personInfoWin form textfield[name=uuid]')[0].getValue(),
					dealContent : Ext.ComponentQuery.query('personInfoWin form textarea[name=dealContent]')[0].getValue(),
					dealStatus : stats,
					baseInfoState : baseInfoState,
					educationState : educationState,
					trainState : trainState,
					careerState : careerState,
					skillState : skillState,
					uploadFileState : uploadFileState
				},
				success : function(response, option) {
					var responseJSON = Ext.decode(response.responseText);
					if(responseJSON.status==true){
						Ext.ComponentQuery.query("personInfoWin")[0].close();
						Ext.ComponentQuery.query("perScoreManageView grid#perScoreManageViewGrid")[0].getStore().load();
						Ext.Msg.alert('操作提示', "数据保存成功");
					}else{
						Ext.Msg.alert('操作提示', "数据保存失败");
					}
				},
				failure : function(response, options){
					Ext.Msg.alert("保存失败,请重新尝试");
				}
			});
		}
	},
	//将textField组件可读
	panelTextReadOnly: function(obj){
		Ext.Array.each(obj, function(name, index, countriesItSelf) {
			var t = obj.get(index) ;
			t.setReadOnly(true);
		});
	},
	//初始化信用信息页面
	loadExtraPerData:function(){
		var objwindow=Ext.ComponentQuery.query('container#extraDataBase')[0];
		var formOne;
		if(scoreStatus==1||scoreStatus==2){
			formOne = Ext.ComponentQuery.query('investigateInfoWin')[0];
		}else if(scoreStatus==4){
			formOne = Ext.ComponentQuery.query('scoreMarkingInfoWin')[0];
		}else if(scoreStatus==5){
			formOne = Ext.ComponentQuery.query('viewPerBasicInfoWin')[0];
		}
		var loadmask=new Ext.LoadMask(Ext.getBody(),{
			msg:'页面信息正在生成中,请稍候'
		});
		loadmask.show();	
		Ext.Ajax.request({
			url:_ctxPath+'/control/perScore/extraIndexList.do',
			method:'post',
			params:{
				resultID:resultID
			},
			success : function(response, option) {
				var responseJSON = Ext.decode(response.responseText);
				if(responseJSON.statue==true){
					var extraIndexsMap=responseJSON.indexs;
					for(var key in extraIndexsMap){
						var extraIndexs = extraIndexsMap[key];
						var objSet = {
								title : key,
								layout: 'column',
								collapsible: true,
								style : 'margin: 0px 5px 10px 10px;'
						};
						var p = Ext.widget("fieldset",objSet);
						Ext.Array.each(extraIndexs,function(extraIndex,num){
							var obj = {
		        					fieldLabel : extraIndex.name,
		        					name : extraIndex.name,
		    						style : 'margin: 0px 5px 5px 5px;',
		    						labelAlign : 'right',// 标签对齐方式
		    						labelWidth : 240,// 标签宽度
		    						readOnly:true,
		    						width: 550,
		    						value : extraIndex.insert
				        	};
							var pT = Ext.widget("textfield",obj);
							p.add(pT);
						});
						objwindow.add(p);
					}
					loadmask.hide();
				}else{
					Ext.Msg.alert("提示",responseJSON.msg);
					loadmask.hide();
				}
			},
			failure : function(response, options){
				Ext.Msg.alert("提示","查看失败,请重新尝试");
				loadmask.hide();
			}
		});
	},
	//初始化基本信息页面
	loadPerBaseInfo:function(){
		perBaseInfoStore.load({
			params : {
				perID : perID
			},
			scope : this,
			callback : function(records, operation, success){
				var form = Ext.ComponentQuery.query('tabpanel form#baseInfoForm')[0];
				var basic = form.getForm();//得到BasicForm
				basic.loadRecord(records[0]);
				var perEducationGrid = Ext.ComponentQuery.query('panel grid#educationGrid')[0];
				perEducationGrid.getStore().load({
					params : {
						perID : perID,
					}
				});
				var perTrainGrid = Ext.ComponentQuery.query('panel grid#trainGrid')[0];
				perTrainGrid.getStore().load({
					params : {
						perID : perID,
					}
				});
				var perCareerGrid = Ext.ComponentQuery.query('panel grid#careerGrid')[0];
				perCareerGrid.getStore().load({
					params : {
						perID : perID,
					}
				});
				var perSkillsGrid = Ext.ComponentQuery.query('panel grid#skillsGrid')[0];
				perSkillsGrid.getStore().load({
					params : {
						perID : perID,
					}
				});
			}
		});
	},
	//初始化附件信息页面
	loadUploadFile:function(){
		var gridPanel=	Ext.ComponentQuery.query('panel#perUploadFilesPanel grid#perUploadFilesGird')[0];
		gridPanel.getStore().load({
			params : {
				perID : perID
			}
		});
	},
	// 操作评分报告
	operaSocreReport : function (gridPanel,view){
		gridPanel.getStore().load({
			scope:this,
			callback:function(records,operation,success){
				if(view.items.length>2){
					view.items.last().close();
				}
				var flag=true;
				Ext.Array.each(records,function(record){
					if(record.get('status')=='0'){
						flag=false;
					};
				});
				if(flag){
					var fieldset=new Ext.form.FieldSet({
						title:'生成报告',
						itemId:'reportFieldset',
						layout:'column',
					    defaults:{
					    	margin:'5 0 5 10'
					    },
						items:[{
							xtype:'combo',
							fieldLabel:'选择模版',
							displayField : 'name',
							valueField : 'value',
							itemId:'reporttempCombo',
							store:'perScore.reporttemplates'
						},{
							xtype:'button',
							action:'generateReport',
							text:'生成报告'
						},{
							xtype:'button',
							action:'downLoadReport',
							disabled:true,
							text:'下载报告'
						},{
							xtype:'button',
							action:'downLoadWord',
							disabled:true,
							text:'下载Word'
						},{
							xtype:'button',
							action:'resetScoring',
							text:'重新评分',
							tooltip:"对当次评分不满意,进行重评"
						},{
							xtype:'button',
							action:'secondScoring',
							text:'下一次评分',
							tooltip:"结束当次评分,进行下一次评分"
						},{  
							xtype:'button',
							hidden:true,
							action:'uploadReport',
							text: '上 传',  
							
						}]
					});
					var obj = {
							hidden : true,
							xtype:'container',
							//html:'<iframe src="'+_ctxPath+'/Charts/reportFinImage.jsp?entID='+entID+'" frameborder="0" width="100%" height="100%"></iframe>'
						};
					var p = Ext.widget("button",obj);
					fieldset.add(p);
					view.add(fieldset);
				}
			}
		});
	},
	//生成报告
	generateReport:function(obj){
		var secondCombo= Ext.ComponentQuery.query('fieldset#reportFieldset combo')[0];
		var secondComboVal=secondCombo.getValue();
		var loadmask=new Ext.LoadMask(Ext.getBody(),{
			msg:'评分报告正在生成中,请稍候'
		});
		if(secondComboVal==null){
			Ext.Msg.alert('操作提示', "请先选择模版");
		}else{
			loadmask.show();
			window.location.href = _ctxPath+"/control/perscoreReport/generateReport.do?resultID="+resultID+"&reportTemplate="+secondComboVal;
			setTimeout(function () {
				loadmask.hide();
		       },3000);
			var reportFieldset=Ext.ComponentQuery.query('fieldset#reportFieldset')[0];
			var size = reportFieldset.items.length;
			if(size>6){
				reportFieldset.remove(reportFieldset.items.last());
				reportFieldset.remove(reportFieldset.items.last());
				reportFieldset.remove(reportFieldset.items.last());
				reportFieldset.remove(reportFieldset.items.last());
			}else if(size>5){
				reportFieldset.remove(reportFieldset.items.last());
				reportFieldset.remove(reportFieldset.items.last());
				reportFieldset.remove(reportFieldset.items.last());
			}
			var obj = {
				action:'downLoadReport',
				text:'下载报告'
	    	};
			var p = Ext.widget("button",obj);
			reportFieldset.add(p);
			
			var obj1 = {
					action:'downLoadWord',
					text:'下载Word'
		    };
			var p1 = Ext.widget("button",obj1);
			reportFieldset.add(p1);
				
			var obj2 = {
					action:'resetScoring',
					text:'重新评分',
					tooltip:"对当次评分不满意,进行重评"	
			};
			var p2 = Ext.widget("button",obj2);
			reportFieldset.add(p2);
			
			var obj3 = {
					action:'secondScoring',
					text:'下一次评分',
					tooltip:"结束当次评分,进行下一次评分"	
			};
			var p3 = Ext.widget("button",obj3);
			reportFieldset.add(p3);
			
			var obj4 = {
					action:'uploadReport',
					text:'上 传',
			};
			var p4 = Ext.widget("button",obj4);
			reportFieldset.add(p4);
		}
	},
	downLoadReport:function(obj){
		Ext.Ajax.request({
			url :_ctxPath+'/control/perscoreReport/judgeReportExist.do',
			method : 'post',
			params:{
				resultID:resultID
			},
			success : function(response, option) {
				var responseJSON = Ext.decode(response.responseText);
				if(responseJSON.flag==true){
					window.location.href = _ctxPath+"/control/perscoreReport/downLoadPdf.do?resultID="+resultID;
				}else{
					Ext.MessageBox.alert("提示消息", "报告不存在无法下载,请生成报告");
				}
			},
			failure:function(response, option){
				
		}
	});
	},
	downLoadWord:function(obj){
		Ext.Ajax.request({
			url :_ctxPath+'/control/perscoreReport/judgeReportExist.do',
			method : 'post',
			params:{
				resultID:resultID
			},
			success : function(response, option) {
				var responseJSON = Ext.decode(response.responseText);
				if(responseJSON.flag==true){
					window.location.href = _ctxPath+"/control/perscoreReport/downLoadWord.do?resultID="+resultID;
				}else{
					Ext.MessageBox.alert("提示消息", "报告不存在无法下载,请生成报告");
				}
			},
			failure:function(response, option){
				
		}
	});
	},
	uploadReport:function(obj){
		var addImageForm = new Ext.form.Panel({
	        bodyPadding: 10,  
	        frame: true,  
	        items: [
            {xtype : 'hidden',name : 'resultID',value : resultID},
	        {  
	            xtype: 'filefield',  
	            name: 'file',  
	            fieldLabel: '报告',  
	            labelWidth: 50,  
	            msgTarget: 'side',  
	            allowBlank: false,  
	            regex : /\.(pdf)$/,
	        	regexText : "请上传PDF文档",
	            anchor: '100%',  
	            buttonText: '选择文件...'  
	        }],  
	  
	        buttons: [{  
	            text: '上传',  
	            handler: function() {  
	                var form = this.up('form').getForm();  
	                if(form.isValid()){  
	                    form.submit({  
	                        url: _ctxPath+'/control/perscoreReport/uploadReport.do',
	                        waitMsg: '正在上传...',  
	                        success: function(fp, o) {  
	                        	if(o.result.statue){
	                        		Ext.MessageBox.alert("提示消息", o.result.msg);
	                        		window.close();
	                        	}else{
	                        		Ext.MessageBox.alert("提示消息", o.result.msg);
	                        	}
	                        }  
	                    });  
	                }  
	            }  
	        }]  
		});      
		var window = new Ext.Window({  
		    title:'将修改后的报告上传',  
		    width:500,  
		    height:200,  
		    minWidth:500,  
		    minHeight:200,  
		    layout:'fit',  
		    plain:true,  
		    bodyStyle:'padding:5px;',  
		    buttonAlign:'center',  
		    items:addImageForm

		});  
		window.show();  
	},
	downloadHistory:function(obj){
		var historyExist=Ext.ComponentQuery.query('historyInfoWin form textfield[name=historyExist]')[0].getValue();
		historyExist = historyExist.replace(/\\/g, "//");
		if(historyExist=="false"){
			Ext.MessageBox.alert("提示消息", "无法查看详情");
		}else{
			window.location.href = _ctxPath+"/control/perScore/manage/downloadHistory.do?path="+historyExist;
		}
	},
	resetScoring:function(obj){
		var grid = Ext.ComponentQuery.query('perScoreManageView grid')[0];
		Ext.MessageBox.confirm("请确认","是否重新进行评分？",function( button,text ){
			if( button == 'yes'){
				Ext.getCmp("perManageWin").close();
				var win = new Ext.Window({
					id:'perRodioWin',
					title:'请选择,从哪一步开始重评',
					layout:'form',       //弹出窗口内布局会充满整个窗口;
					width:300,          //设置窗口大小;
					height:150,
					closable:true,     //隐藏关闭按钮;
					draggable:true,     //窗口可拖动;
					modal: true,
					buttonAlign: 'center',
					items:[{
						id:'state',
						xtype:'radiogroup',
						margin:'50 20 10 20',
						items: [{
							name: 'state',
							inputValue: '0',
							boxLabel: '客户录入信息',
							checked: true
						}, {
							name: 'state',
							inputValue: '1',
							boxLabel: '管理员确认信息'
						}]
					}],
					buttons: [{ 
						xtype: "button", 
						text: "确定", 
						handler: function () { 
							Ext.Ajax.request({
								url : _ctxPath+"/control/perResetScore/resetScoring.do",
								method : 'POST',
								params : { 
								    state:Ext.getCmp('state').lastValue.state,
									resultID : resultID
								},
								success : function(reponse, option) {
									var responseJSON = Ext.decode(reponse.responseText);
									Ext.getCmp("perRodioWin").close();
									grid.getStore().load();
									Ext.MessageBox.alert("提示消息", responseJSON.msg);
								},
								failure : function() {
									Ext.MessageBox.alert("提示消息", "评分状态重置失败！");
								}
							});
						
						} 
					}]
				});
				win.show();
			}
		} 
		); 
	},
	secondScoring:function(obj){
		var grid = Ext.ComponentQuery.query('perScoreManageView grid')[0];
		Ext.MessageBox.confirm("请确认","是否进行下一次评分？",function( button,text ){
			if( button == 'yes'){
				Ext.Ajax.request({
					url : _ctxPath+"/control/perResetScore/secondScoring.do",
					method : 'POST',
					params : {
						resultID : resultID
					},
					success : function(reponse, option) {
						var responseJSON = Ext.decode(reponse.responseText);
						Ext.MessageBox.alert("提示消息", responseJSON.msg);
						Ext.getCmp("perManageWin").close();
						grid.getStore().load();
					},
					failure : function() {
						Ext.MessageBox.alert("提示消息", "评分状态重置失败！");
					}
				});
			}
		} 
		); 
	},
	removeExtraItems : function(){
		var view=Ext.ComponentQuery.query('perScoreManageView')[0];
		for ( var int = 0; int < view.items.length; int++) {
			if(view.items.length>1){
				view.remove(view.items.last());
			}
		}
	},
	//选择行业模型
	firstIndustry:function(obj){
		var store=this.getPerSecondModelCombosStore();
		store.removeAll();
		store.load({
			params:{
				modelID:obj.getValue()
			},
			scope:this,
		});
	},
	//选择行业子模型
	giveIndustry:function(btn){
		var grid =Ext.ComponentQuery.query('perScoreManageView grid')[0];
		var store=grid.getStore();
		var form=Ext.ComponentQuery.query('industrysInfoWin form#personIndustryForm')[0];
		var basicForm=form.getForm();
		if(basicForm.isValid()){
			basicForm.doAction('submit',{
				url:_ctxPath+'/control/perScore/manage/savePerModel.do',
				method:'post',
				success : function(form, action) {
				  if(action.result.status==true){
						Ext.Msg.alert('提示', action.result.msg);
						basicForm.reset();
						store.load();
						Ext.ComponentQuery.query('industrysInfoWin')[0].close();
					}else{
						Ext.ComponentQuery.query('industrysInfoWin')[0].close();
						Ext.Msg.alert('提示', action.result.msg);
					}
				}
			});
		}
	},
	//等级转换
	getScoreLevel:function(score){
		var scoreLevel=new String();
		if(score>=95&&score<=100){
			scoreLevel="AAA";
		}else if(score>=85&&score<95){
			scoreLevel="AA";
		}else if(score>=75&&score<85){
			scoreLevel="A";
		}else if(score>=70&&score<75){
			scoreLevel="BBB";
		}else if(score>=65&&score<70){
			scoreLevel="BB";
		}else if(score>=60&&score<65){
			scoreLevel="B";
		}else if(score>=50&&score<60){
			scoreLevel="CCC";
		}else if(score>=40&&score<50){
			scoreLevel="CC";
		}else if(score<40){
			scoreLevel="C";
		}else{
			scoreLevel="暂无等级";
		}
		return scoreLevel;
	},
});
