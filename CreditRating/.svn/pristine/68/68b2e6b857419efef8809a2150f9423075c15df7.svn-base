Ext.Loader.setPath('Ext.ux',_ctxPath+'/Resources/extjs/examples/ux');
Ext.require('Ext.ux.form.SearchField');
var confUploadFileStore = Ext.create("PM.store.enterprise.uploadFiles");
confUploadFileStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('confirmInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			entID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});
var entShareholderStore = Ext.create("PM.store.enterprise.entShareholderWin");
entShareholderStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('confirmInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
			entID: t,
    };
    Ext.apply(store.proxy.extraParams, new_params);
});

var entExecutivesStore = Ext.create("PM.store.enterprise.entExecutivesWin");
entExecutivesStore.on('beforeload', function (store, options) {
	var t = Ext.ComponentQuery.query('confirmInfoWin form textfield[name=uuid]')[0].getValue();
	var new_params = { 
    		entID: t,
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
var entID='';
var resultID='';
var scoreStatus;
var entBaseInfoStore = Ext.create("PM.store.enterprise.entBaseInfo");
Ext.define('PM.controller.enterprise.entScoreManage',{
	extend:'Ext.app.Controller',
	views:['enterprise.entScoreManageView','enterprise.investigate','enterprise.scoreMarking',
	       'enterprise.viewEnterBasicInfo','enterprise.manageScoreReport','enterprise.historyWin',
	       'enterprise.confirmInfoWin','enterprise.showShareholderDetail','enterprise.showExecutivesDetail',
           'enterprise.editScoreReason','enterprise.editAdvantageReason','enterprise.editScoreSummary','enterprise.industrys'],
           //'customer.modelCombos','SecondModelCombos' 为行业模型 stores 
	stores:['enterprise.entScoreLists','enterprise.scoreMarks','enterprise.scoreReports','enterprise.reporttemplates',
	        'enterprise.entShareholderWin','enterprise.entExecutivesWin','enterprise.entBaseInfo','enterprise.modelCombos','SecondModelCombos'],
	init: function() {//通过init函数来监听视图事件，控制视图与控制器的交互
		this.control({
			'entScoreManageView grid#entScoreManageViewGrid' : {
				score : this.entScore,
				doNextStep:this.doNextStep//更改状态，进入下一步骤
			},
			'entScoreManageView button[action=refresh]' :{
				click : this.refresh//评分列表刷新
			},
			'entScoreManageView button[action=query]' :{
				click : this.query//评分列表查询
			},
			'investigate button' : {
				click : this.updateOrClose//更新或保存调查信息
			},
			'scoreMarking button' : {
				click : this.saveScore//保存评分
			},
			'confirmInfoWin tabpanel panel#entShareholderPanel grid#entShareholderGrid' :{
				select : this.showShareholderDetail//确认信息页面的股东信息选项卡grid的select事件
			},
			'confirmInfoWin tabpanel panel#entExecutivesPanel grid#entExecutivesGrid' :{
				select : this.showExecutivesDetail//确认信息页面的高层信息选项卡grid的select事件
			},
			'confirmInfoWin button[action=rejectMsg]' :{
				click : this.rejectSubmitInfo//确认信息页面信息驳回按钮
			},
			'confirmInfoWin button[action=confirmMsg]' :{
				click : this.confirmSubmitInfo//确认信息页面信息通过按钮
			},
			'historyWin button[action=downloadHistory]' :{
				click : this.downloadHistory//下载历史信息
			},
			'investigate tabpanel container#extraDataBase' : {
				boxready : this.loadExtraEnterData//调查页面初始化信用信息页面
			},
			'investigate tabpanel container#baseInfoContainer' : {
				boxready : this.loadEnterBaseInfo//调查页面初始化基本信息页面
			},
			'investigate tabpanel panel#uploadFilesPanel' : {
				boxready : this.loadUploadFile
			},
			'scoreMarking tabpanel container#extraDataBase' : {
				boxready : this.loadExtraEnterData
			},
			'scoreMarking tabpanel container#baseInfoContainer' : {
				boxready : this.loadEnterBaseInfo
			},
			'scoreMarking tabpanel panel#uploadFilesPanel' : {
				boxready : this.loadUploadFile
			},
			'viewEnterBasicInfo tabpanel container#extraDataBase' : {
				boxready : this.loadExtraEnterData
			},
			'viewEnterBasicInfo tabpanel container#baseInfoContainer' : {
				boxready : this.loadEnterBaseInfo
			},
			'viewEnterBasicInfo tabpanel panel#uploadFilesPanel' : {
				boxready : this.loadUploadFile
			},
			'tabpanel grid#uploadFilesGird':{
				download : this.downloadUploadFile,//选项卡下载按钮
				preview : this.previewUploadFile//选项卡查看按钮
			},
			'manageScoreReport grid#scoreReportGrid':{
				editReason:this.editReason//管理评分报告编辑功能
			},
			'editScoreReason button':{
				click: this.saveScoreReasonOrClose
			},
			'editAdvantageReason button':{
				click: this.saveAdvantageReasonOrClose
			},
			'editScoreSummary button':{
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
			'industrys combo#enterpriseFirstIndustry':{
				select:this.firstIndustry
			},
			'industrys button[action=giveIndustry]' : {
				click: this.giveIndustry//赋予行业
			},
		});
	},
	refresh:function(){
		var grid = Ext.ComponentQuery.query('entScoreManageView grid')[0];
         grid.getStore().load();
	},
	query:function(){
		var grid = Ext.ComponentQuery.query('entScoreManageView grid')[0];
		var queryType=Ext.ComponentQuery.query('entScoreManageView grid combo[name=queryType]')[0];
		var input=Ext.ComponentQuery.query('entScoreManageView grid textfield[name=searchmatter]')[0];
		grid.getStore().getProxy().extraParams = {
			 name:queryType.getValue(),
			 value:input.getValue()
		};
		grid.getStore().load();
	},
	entScore : function(grid, rowIndex, colIndex){
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
			                	url: _ctxPath+"/control/customer/manage/updateVisible.do",
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
			                	url: _ctxPath+"/control/customer/manage/updateVisible.do",
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
				Ext.widget("confirmInfoWin");
				var rec = grid.getStore().getAt(rowIndex);
				this.removeExtraItems();
				entBaseInfoStore.load({
					params : {
						entID : rec.get('entID')
					},
					scope : this,
					callback : function(records, operation, success){
						var form = Ext.ComponentQuery.query('confirmInfoWin form')[0];
						var basic = form.getForm();//得到BasicForm
						basic.loadRecord(records[0]);
						var new_params = { 
								entID: rec.get('entID'),
					    };
						entShareholderStore.load();
						entExecutivesStore.load();
						confUploadFileStore.load();
					}
				});
			}else if(applyReportState == 1 && (scoreState == 1||scoreState==2)){//编辑和调查
				Ext.widget('investigate');
				var formOne = Ext.ComponentQuery.query('investigate')[0];
				formOne.suspendLayout = true;
				entID=rec.get('entID');;
				resultID=rec.get('resultID');;
				scoreStatus=scoreState;
				this.removeExtraItems();
				var loadmask=new Ext.LoadMask(Ext.getBody(),{
					msg:'页面信息正在生成中,请稍候'
				});
				loadmask.show();
 				var objWindow  = Ext.ComponentQuery.query('investigate form fieldset')[1];
				Ext.Ajax.request({
					url :_ctxPath+'/control/entScore/manage/surveryScoreView.do',
					method : 'post',
					params : {
						resultID : resultID
					},
					success : function(response, option) {
						var responseJSON = Ext.decode(response.responseText);
						if(responseJSON.msg.length!=0){
							Ext.Msg.alert("提示",responseJSON.msg);
						}
						Ext.ComponentQuery.query('investigate form textfield[name=name]')[0].setValue(responseJSON.entName);
						Ext.ComponentQuery.query('investigate form textfield[name=uuid]')[0].setValue(responseJSON.entID);
						Ext.ComponentQuery.query('investigate form textfield[name=opinion]')[0].setValue(responseJSON.opinion);
						Ext.ComponentQuery.query('investigate form textfield[name=resultID]')[0].setValue(resultID);
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
				Ext.widget('scoreMarking');
				var formWindow  = Ext.ComponentQuery.query('scoreMarking')[0];
				formWindow.suspendLayout = true;
				this.removeExtraItems();
				entID=rec.get('entID');;
				resultID=rec.get('resultID');
				scoreStatus=scoreState;
				var gridScore  = Ext.ComponentQuery.query('scoreMarking tabpanel form grid#scoreGrid')[0];
				var gridScoreStore = gridScore.getStore();
				var objWindow  = Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset#adjustOptionFieldSet')[0];
				var formWindow1  = Ext.ComponentQuery.query('scoreMarking tabpanel form')[0];
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
	    	
					    	 Ext.ComponentQuery.query('scoreMarking tabpanel form textfield#encodingFieldset')[0].setValue(responseJSON.encoding).setReadOnly(true);
					    	 //初评分数等级
					    	 var preliminaryScore = responseJSON.preliminaryScore;
					    	 var preliminaryLevel = responseJSON.preliminaryLevel;
					    	 //总评分数等级
					    	 var finalScore = responseJSON.finalScore;
					    	 var finalLevel = responseJSON.finalLevel;
					    	 //给基本信息进行赋值
					    	 Ext.ComponentQuery.query('scoreMarking tabpanel form textfield[name=name]')[0].setValue(responseJSON.name);
							 Ext.ComponentQuery.query('scoreMarking tabpanel form textfield[name=uscc]')[0].setValue(responseJSON.uscc);
							 Ext.ComponentQuery.query('scoreMarking tabpanel form textfield[name=uuid]')[0].setValue(responseJSON.uuid);
							 Ext.ComponentQuery.query('scoreMarking tabpanel form textfield[name=resultid]')[0].setValue(responseJSON.resultid);
							 Ext.ComponentQuery.query('scoreMarking tabpanel form textfield[name=adminOpinion]')[0].setValue(responseJSON.adminOpinion);
							 Ext.ComponentQuery.query('scoreMarking tabpanel form textfield[name=customerOpinion]')[0].setValue(responseJSON.customerOpinion);
							 //初评分数及等级赋值
							 Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset displayfield[name=preliminaryScore]')[0].setValue(preliminaryScore);
							 Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset displayfield[name=preliminaryLevel]')[0].setValue(preliminaryLevel);
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
									 var totalScore2ID = Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset displayfield[itemId=totalScore2ID]')[0];//显示最终得分组件
									 var totalScore2LevelID = Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset displayfield[itemId=totalScore2LevelID]')[0];//显示最终级别 组件
									 var adjustScore = parseFloat(totalScore2ID.getValue());
									 if(cbg.checked){
										 adjustScore += parseFloat(str.substring(str.indexOf("(")+1,str.indexOf(")")));;
									 }else{
										 adjustScore -= parseFloat(str.substring(str.indexOf("(")+1,str.indexOf(")")));;
									 }
									 if(adjustScore>1000){
										 judgeChange = false;
										 p.setValue(false);
										 Ext.Msg.alert("警告","分数超过一千分！！！");
									 }else{
										if(judgeChange){
											 Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset displayfield[name=preliminaryLevel]')[0].setValue(preliminaryLevel);
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
				var view=new Ext.Window({id:'managewin',width:800,height:300,modal:true});
				var scoreReport=Ext.widget('manageScoreReport');
				view.setTitle('管理评分报告('+rec.get('entName')+')');
				entID=rec.get('entID');;
				resultID=rec.get('resultID');
				scoreStatus=scoreState;
				var scoreReportGrid=Ext.ComponentQuery.query('manageScoreReport grid')[0];
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
								title:'生成报告('+rec.get('entName')+')',
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
									store:'enterprise.reporttemplates'
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
								},/*,{
									xtype:'button',
									//hidden:true,
									action:'downLoadImage',
									disabled :true,
									text:'下载图片'
								},*/{
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
							url :_ctxPath+'/control/entScore/manage/judgeFileExist.do',
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
										/*Ext.ComponentQuery.query('button[action=downLoadImage]')[0].setDisabled(false);
										Ext.ComponentQuery.query('button[action=downLoadPdf]')[0].setDisabled(false);*/
									}
								}else{
									if(flag){
										var obj = {
												hidden : true,
												hideMode:'offsets',
												xtype:'container',
												html:'<iframe src="'+_ctxPath+'/Charts/reportFinImage.jsp?entID='+entID+'" frameborder="0" width="100%" height="100%"></iframe>'
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
				Ext.widget('historyWin');
				var formOne = Ext.ComponentQuery.query('historyWin')[0];
				formOne.suspendLayout = true;
				entID=rec.get('entID');;
				resultID=rec.get('resultID');;
				scoreStatus=scoreState;
				this.removeExtraItems();
				Ext.Ajax.request({
					url :_ctxPath+'/control/entScore/manage/lookHistoryView.do',
					method : 'post',
					params : {
						historyID : entID
					},
					success : function(response, option) {
						var responseJSON = Ext.decode(response.responseText);
						if(responseJSON.msg.length!=0){
							Ext.Msg.alert("提示",responseJSON.msg);
						}
						Ext.ComponentQuery.query('historyWin form textfield[name=name]')[0].setValue(responseJSON.name);
						Ext.ComponentQuery.query('historyWin form textfield[name=uuid]')[0].setValue(entID);
						Ext.ComponentQuery.query('historyWin form textfield[name=uscc]')[0].setValue(responseJSON.uscc);
						Ext.ComponentQuery.query('historyWin form textfield[name=finalscore]')[0].setValue(responseJSON.finalscore);
						Ext.ComponentQuery.query('historyWin form textfield[name=finallevel]')[0].setValue(responseJSON.finallevel);
						Ext.ComponentQuery.query('historyWin form textfield[name=historyExist]')[0].setValue(responseJSON.historyExist);
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
		var me = this;
		var store=grid.getStore();
    	var rec = grid.getStore().getAt(rowIndex);
		var scoreState = rec.get('scoreState');
		var modelID = rec.get('modelID');
		var applyReportState = rec.get('applyReportState');
		if(applyReportState == 1 && scoreState == 2){
			Ext.Msg.confirm('请确认', '确定结束编辑调查吗?', function(button) {
				if (button =='yes') {
					Ext.Ajax.request({
						url :_ctxPath+'/control/entScore/manage/endSurvery.do',
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
						url :_ctxPath+'/control/entScore/manage/endScore.do',
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
			entID=rec.get('entID');
			resultID=rec.get('resultID');
			scoreStatus=scoreState;
			Ext.widget('viewEnterBasicInfo');
			var formWindow  = Ext.ComponentQuery.query('viewEnterBasicInfo')[0];
			formWindow.suspendLayout = true;
			var gridScore  = Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form grid#scoreGrid')[0];
			var gridScoreStore = gridScore.getStore();
			var objWindow  = Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form fieldset#adjustOptionFieldSet')[0];
			var formWindow1  = Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form')[0];
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
    	
				    	 Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form textfield#encodingFieldset')[0].setValue(responseJSON.encoding).setReadOnly(true);
				    	 //初评分数等级
				    	 var preliminaryScore = responseJSON.preliminaryScore;
				    	 var preliminaryLevel = responseJSON.preliminaryLevel;
				    	 //总评分数等级
				    	 var finalScore = responseJSON.finalScore;
				    	 var finalLevel = responseJSON.finalLevel;
				    	 //给基本信息进行赋值
				    	 Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form textfield[name=name]')[0].setValue(responseJSON.name);
						 Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form textfield[name=uscc]')[0].setValue(responseJSON.uscc);
						 Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form textfield[name=uuid]')[0].setValue(responseJSON.uuid);
						 Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form textfield[name=resultid]')[0].setValue(responseJSON.resultid);
						 Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form textfield[name=adminOpinion]')[0].setValue(responseJSON.adminOpinion);
						 Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form textfield[name=customerOpinion]')[0].setValue(responseJSON.customerOpinion);
						 //初评分数及等级赋值
						 Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form fieldset displayfield[name=preliminaryScore]')[0].setValue(preliminaryScore);
						 Ext.ComponentQuery.query('viewEnterBasicInfo tabpanel form fieldset displayfield[name=preliminaryLevel]')[0].setValue(preliminaryLevel);
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
			entID=rec.get('entID');
			Ext.widget('industrys');
			Ext.ComponentQuery.query('industrys form hidden[name=uuid]')[0].setValue(entID);
		}
	},
	//编辑调查和调查关闭和保存按钮
	updateOrClose : function(obj, e, eOpts){
		var judgeAction = obj.action;
		var gridPanel = Ext.ComponentQuery.query('entScoreManageView grid#entScoreManageViewGrid')[0];
		if(judgeAction=="saveNew"){
			//提交
			var objWindow  = Ext.ComponentQuery.query('investigate form')[0];
			var basic = objWindow.getForm();//得到BasicForm
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',  
			  		clientValidation : false,
			  		url :_ctxPath+'/control/entScore/manage/saveSurveryScore.do',
			  		params : {
			  			resultID : Ext.ComponentQuery.query('investigate form textfield[name=resultID]')[0].getValue()
			  		},
			  		success: function (form, action) {
			  			Ext.ComponentQuery.query('investigate')[0].close();
			  			gridPanel.getStore().load();
			  			Ext.Msg.alert('操作提示', "数据保存成功");
			  	    },
			  	    failure: function(form,action){
			  	    	Ext.Msg.alert('操作提示', "数据保存失败,请重新尝试");
			  	    }
				});
			}
		}else if(judgeAction=="close"){
			Ext.ComponentQuery.query('investigate')[0].close();
		}
	},
	//保存评分数据
	saveScore:function(obj, e, eOpts){
		var judgeAction = obj.action;
		var gridPanel = Ext.ComponentQuery.query('entScoreManageView grid#entScoreManageViewGrid')[0];
		if(judgeAction=='keepScore'){
			var objWindow  = Ext.ComponentQuery.query('scoreMarking form')[0];
			var basic = objWindow.getForm();//得到BasicForm
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		url :_ctxPath+'/control/entScore/manage/saveEnterpriseOfScore.do',
			  		params:{
			  			preliminaryScore : Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset displayfield[name=preliminaryScore]')[0].getValue(),
						preliminaryLevel: Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset displayfield[name=preliminaryLevel]')[0].getValue(),
						finalScore :  Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset displayfield[itemId=totalScore2ID]')[0].getValue(),
						finalLevel : Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset displayfield[itemId=totalScore2LevelID]')[0].getValue(),
						resultID : Ext.ComponentQuery.query('scoreMarking tabpanel form fieldset textfield[name=resultid]')[0].getValue()
			  		},
			  		success: function (form, action) {
			  			Ext.ComponentQuery.query('scoreMarking')[0].close();
			  			gridPanel.getStore().load();
			  			Ext.Msg.alert('操作提示', action.result.msg);
			  	    },
			  	    failure: function(form,action){
			  	    	Ext.Msg.alert('操作提示', "数据保存失败,请重新尝试");
			  	    }
				});
			}
		}/*else if(judgeAction=='closeScore'){
			gridPanel.getStore().load();
			Ext.ComponentQuery.query('scoreMarking')[0].close();
		}*/
		
	},
	//管理评分报告编辑
	editReason:function(grid, rowIndex, colIndex){
		var rec=grid.getStore().getAt(rowIndex);
		if(rec.get('type')=="0"){
			Ext.widget('editScoreReason');
			Ext.ComponentQuery.query('editScoreReason form hidden[name=uuid]')[0].setValue(rec.get('uuid'));
			var fieldset1=Ext.ComponentQuery.query('editScoreReason form fieldset#reasonAndExplain')[0];
			var adjustReason = rec.get("adjustReason");
			if(adjustReason==null || adjustReason.trim()==""){
				var displayItems=new Ext.form.field.Display({
					fieldLabel:'暂无调整项',
					labelWidth : '500px'
				});
				Ext.ComponentQuery.query('editScoreReason button[action=saveScoreReason]')[0].setDisabled(true);
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
			Ext.widget('editAdvantageReason');
			Ext.ComponentQuery.query('editAdvantageReason form hidden[name=uuid]')[0].setValue(rec.get('uuid'));
			Ext.ComponentQuery.query('editAdvantageReason form textarea[name=advantageReason]')[0].setValue(rec.get('advantageReason'));
		}else if(rec.get('type')=="2"){
			Ext.widget('editScoreSummary');
			Ext.ComponentQuery.query('editScoreSummary form hidden[name=uuid]')[0].setValue(rec.get('uuid'));
			Ext.ComponentQuery.query('editScoreSummary form#scoreSummaryForm textarea[name=scoreSummary]')[0].setValue(rec.get('scoreSummary'));
		}
		
	},
	//调整因素说明保存或关闭
	saveScoreReasonOrClose:function(obj, e, eOpts){
		var objThis = this;
		var judgeAction = obj.action;
		var gridPanel = Ext.ComponentQuery.query('manageScoreReport grid#scoreReportGrid')[0];
		var objWindow=Ext.ComponentQuery.query('editScoreReason form#scoreReasonForm')[0];
		var adjustField=Ext.ComponentQuery.query('editScoreReason form fieldset displayfield');
		var reasonField=Ext.ComponentQuery.query('editScoreReason form fieldset textarea');
		var uuid=Ext.ComponentQuery.query('editScoreReason form hidden')[0].getValue();
		//var view=Ext.ComponentQuery.query('entScoreManageView')[0];
		var view=Ext.getCmp("managewin");
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
					url :_ctxPath+'/control/entScore/manage/addOrUpdateFillReportOption.do',
					method : 'post',
					params : {
						adjustReason : adjustReason,
						resultID : Ext.ComponentQuery.query('editScoreReason form#scoreReasonForm hidden[name=uuid]')[0].getValue()
					},
					success : function(response, option) {
						var record=Ext.decode(response.responseText);
						Ext.ComponentQuery.query('editScoreReason')[0].close();
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
			Ext.ComponentQuery.query('editScoreReason')[0].close();
		}
		
	},
	saveAdvantageReasonOrClose:function(obj, e, eOpts){
		var objThis = this;
		var judgeAction = obj.action;
		var objWindow=Ext.ComponentQuery.query('editAdvantageReason form#advantageReasonForm')[0];
		//var view=Ext.ComponentQuery.query('entScoreManageView')[0];
		var view=Ext.getCmp("managewin");
		var gridPanel = Ext.ComponentQuery.query('manageScoreReport grid#scoreReportGrid')[0];
		var basic = objWindow.getForm();//得到BasicForm
		if(judgeAction=='saveAdvantageReason'){
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		url :_ctxPath+'/control/entScore/manage/addOrUpdateFillReportOption.do',
			  		params : {
			  			resultID : Ext.ComponentQuery.query('editAdvantageReason form#advantageReasonForm hidden[name=uuid]')[0].getValue()
			  		},
			  		success: function (form, action) {
			  			Ext.ComponentQuery.query('editAdvantageReason')[0].close();
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
			Ext.ComponentQuery.query('editAdvantageReason')[0].close();
		}
	},
	saveScoreSummaryOrClose:function(obj, e, eOpts){
		var objThis = this;
		var judgeAction = obj.action;
		var objWindow=Ext.ComponentQuery.query('editScoreSummary form#scoreSummaryForm')[0];
		//var view=Ext.ComponentQuery.query('entScoreManageView')[0];
		var view=Ext.getCmp("managewin");
		var gridPanel = Ext.ComponentQuery.query('manageScoreReport grid#scoreReportGrid')[0];
		var basic = objWindow.getForm();//得到BasicForm
		if(judgeAction=='saveScoreSummary'){
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		url :_ctxPath+'/control/entScore/manage/addOrUpdateFillReportOption.do',
			  		params : {
			  			resultID : Ext.ComponentQuery.query('editScoreSummary form#scoreSummaryForm hidden[name=uuid]')[0].getValue()
			  		},
			  		success: function (form, action) {
			  			Ext.ComponentQuery.query('editScoreSummary')[0].close();
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
			Ext.ComponentQuery.query('editScoreSummary')[0].close();
		}
	},
	//展示股东具体信息
	showShareholderDetail : function (obj, record, index, eOpts){
    	var record = obj.getSelection()[0];
		var p = Ext.widget("showShareholderDetail");
		var objWindow = Ext.ComponentQuery.query('confirmInfoWin tabpanel panel#entShareholderPanel')[0];
		if(objWindow.items.length>1){
			objWindow.items.last().close();
		}
		p.getForm().setValues({
			uuid:record.get('uuid'),
			name:record.get('name'),
			stockpercent:record.get('stockpercent'),
			type:record.get('type'),
			realcapi:record.get('realcapi'),
			realTime:record.get('realTime'),
			shouldcapi:record.get('shouldcapi'),
			shouldTime:record.get('shouldTime'),
    	});
		objWindow.add(p);
		p.setTitle(record.get('name')+"——详细信息");
		this.panelTextReadOnly(p.items);
		var t = p.dockedItems;
		var t1 = t.items[1];
	},
	//展示高层具体信息
	showExecutivesDetail : function (obj, record, index, eOpts){
    	var record = obj.getSelection()[0];
		var p = Ext.widget("showExecutivesDetail");
		var objWindow = Ext.ComponentQuery.query('confirmInfoWin tabpanel panel#entExecutivesPanel')[0];
		if(objWindow.items.length>1){
			objWindow.items.last().close();
		}
		if(objWindow.items.length>5){
			objWindow.items.last().close();
		}
		p.getForm().setValues({
			uuid:record.get('uuid'),
			name:record.get('name'),
			age:record.get('age'),
			gender:record.get('gender'),
			IDCard:record.get('IDCard'),
			job:record.get('job'),
			department:record.get('department'),
			education:record.get('education'),
			workExp:record.get('workExp'),
		});
		objWindow.add(p);
		p.setTitle(record.get('name')+"——详细信息");
		this.panelTextReadOnly(p.items);
		var t = p.dockedItems;
		var t1 = t.items[1];
	},
	downloadUploadFile : function(grid, rowIndex, colIndex){
		var rec = grid.getStore().getAt(rowIndex);
    	window.open(_ctxPath+"/control/entScore/manage/downLoadUploadFile.do?fileName="+rec.get('type')+"&fileUrl="+rec.get('fileUrl'));
    	return;
	},
	previewUploadFile : function(grid, rowIndex, colIndex){
		var rec = grid.getStore().getAt(rowIndex);
		Ext.Ajax.request({
			url:_ctxPath+'/control/entScore/manage/lookUploadFile.do',
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
		var baseInfoState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=baseInfoState]')[0].getSubmitValue();
		var shareholderState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=shareholderState]')[0].getSubmitValue();
		var executivesState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=executivesState]')[0].getSubmitValue();
		var uploadFileState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=uploadFileState]')[0].getSubmitValue();
		var pushModelState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=pushModelState]')[0].getSubmitValue();
		var financeState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=financeState]')[0].getSubmitValue();
		
		if(baseInfoState!=null||shareholderState!=null||executivesState!=null
				||uploadFileState!=null||pushModelState!=null||financeState!=null){
			this.winButoonAjax("0");
		}else{
			Ext.Msg.alert('操作提示', "请至少选择一个驳回项目");
		}
	},
	//信息通过
	confirmSubmitInfo : function (obj, e, eOpts){
		var baseInfoState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=baseInfoState]')[0].getSubmitValue();
		var shareholderState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=shareholderState]')[0].getSubmitValue();
		var executivesState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=executivesState]')[0].getSubmitValue();
		var uploadFileState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=uploadFileState]')[0].getSubmitValue();
		var pushModelState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=pushModelState]')[0].getSubmitValue();
		var financeState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=financeState]')[0].getSubmitValue();
		if(baseInfoState!=null||shareholderState!=null||executivesState!=null
				||uploadFileState!=null||pushModelState!=null||financeState!=null){	
			Ext.Msg.alert('操作提示', "您已选择驳回选项,无法信息通过,请仔细核对后修改！");
		}else{
			this.winButoonAjax("1");
		}
	},
	winButoonAjax : function (stats){
		var baseInfoState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=baseInfoState]')[0].getSubmitValue();
		var shareholderState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=shareholderState]')[0].getSubmitValue();
		var executivesState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=executivesState]')[0].getSubmitValue();
		var uploadFileState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=uploadFileState]')[0].getSubmitValue();
		var pushModelState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=pushModelState]')[0].getSubmitValue();
		var financeState = Ext.ComponentQuery.query('confirmInfoWin form checkbox[name=financeState]')[0].getSubmitValue();
		if(Ext.ComponentQuery.query('confirmInfoWin form textarea[name=dealContent]')[0].isValid()){
			Ext.Ajax.request({
				url:_ctxPath+'/control/entScore/manage/adminOpinionUpdateOrAdd.do',
				method:'post',
				params:{
					entID : Ext.ComponentQuery.query('confirmInfoWin form textfield[name=uuid]')[0].getValue(),
					dealContent : Ext.ComponentQuery.query('confirmInfoWin form textarea[name=dealContent]')[0].getValue(),
					dealStatus : stats,
					baseInfoState : baseInfoState,
					shareholderState : shareholderState,
					executivesState : executivesState,
					uploadFileState : uploadFileState,
					pushModelState : pushModelState,
					financeState : financeState
				},
				success : function(response, option) {
					var responseJSON = Ext.decode(response.responseText);
					if(responseJSON.status==true){
						Ext.ComponentQuery.query("confirmInfoWin")[0].close();
						Ext.ComponentQuery.query("entScoreManageView grid#entScoreManageViewGrid")[0].getStore().load();
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
	//等级转换
	getScoreLevel:function(score){
		var scoreLevel=new String();
		if(score>=700&&score<=1000){
			scoreLevel="AAA";
		}else if(score>=650&&score<700){
			scoreLevel="AA";
		}else if(score>=600&&score<650){
			scoreLevel="A";
		}else if(score>=550&&score<600){
			scoreLevel="BBB";
		}else if(score>=500&&score<550){
			scoreLevel="BB";
		}else if(score>=450&&score<500){
			scoreLevel="B";
		}else if(score>=400&&score<450){
			scoreLevel="CCC";
		}else if(score>=350&&score<400){
			scoreLevel="CC";
		}else if(score<350){
			scoreLevel="C";
		}else{
			scoreLevel="暂无等级";
		}
		return scoreLevel;
	},
	loadExtraEnterData:function(){
		var objwindow=Ext.ComponentQuery.query('container#extraDataBase')[0];
		var formOne;
		if(scoreStatus==1||scoreStatus==2){
			formOne = Ext.ComponentQuery.query('investigate')[0];
		}else if(scoreStatus==4){
			formOne = Ext.ComponentQuery.query('scoreMarking')[0];
		}else if(scoreStatus==5){
			formOne = Ext.ComponentQuery.query('viewEnterBasicInfo')[0];
		}
		var loadmask=new Ext.LoadMask(Ext.getBody(),{
			msg:'页面信息正在生成中,请稍候'
		});
		loadmask.show();	
		Ext.Ajax.request({
			url:_ctxPath+'/control/entScore/extraIndexList.do',
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
	
	loadEnterBaseInfo:function(){
		entBaseInfoStore.load({
			params : {
				entID : entID
			},
			scope : this,
			callback : function(records, operation, success){
				var form = Ext.ComponentQuery.query('tabpanel form#baseInfoForm')[0];
				var basic = form.getForm();//得到BasicForm
				basic.loadRecord(records[0]);
				var entShareholderGrid = Ext.ComponentQuery.query('panel grid#entShareholderGrid')[0];
				entShareholderGrid.getStore().load({
					params : {
						entID : entID,
					}
				});
				var entExecutivesGrid = Ext.ComponentQuery.query('panel grid#entExecutivesGrid')[0];
				entExecutivesGrid.getStore().load({
					params : {
						entID : entID,
					}
				});
			}
		});
	},
	
	loadUploadFile:function(){
		var gridPanel=	Ext.ComponentQuery.query('panel#uploadFilesPanel grid#uploadFilesGird')[0];
		gridPanel.getStore().load({
			params : {
				entID : entID
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
							store:'enterprise.reporttemplates'
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
						},/*{
							xtype:'button',
							//hidden:true,
							action:'downLoadImage',
							disabled :true,
							text:'下载图片'
						},{
							xtype:'button',
							action:'downLoadPdf',
							disabled :true,
							text:'下载pdf'
						},*/{
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
							html:'<iframe src="'+_ctxPath+'/Charts/reportFinImage.jsp?entID='+entID+'" frameborder="0" width="100%" height="100%"></iframe>'
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
			window.location.href = _ctxPath+"/control/reportTemplate/generateReport.do?resultID="+resultID+"&reportTemplate="+secondComboVal;
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
			url :_ctxPath+'/control/reportTemplate/judgeReportExist.do',
			method : 'post',
			params:{
				resultID:resultID
			},
			success : function(response, option) {
				var responseJSON = Ext.decode(response.responseText);
				if(responseJSON.flag==true){
					window.location.href = _ctxPath+"/control/reportTemplate/downLoadPdf.do?resultID="+resultID;
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
			url :_ctxPath+'/control/reportTemplate/judgeReportExist.do',
			method : 'post',
			params:{
				resultID:resultID
			},
			success : function(response, option) {
				var responseJSON = Ext.decode(response.responseText);
				if(responseJSON.flag==true){
					window.location.href = _ctxPath+"/control/reportTemplate/downLoadWord.do?resultID="+resultID;
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
	                        url: _ctxPath+'/control/reportTemplate/uploadReport.do',
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
		var historyExist=Ext.ComponentQuery.query('historyWin form textfield[name=historyExist]')[0].getValue();
		historyExist = historyExist.replace(/\\/g, "//");
		if(historyExist=="false"){
			Ext.MessageBox.alert("提示消息", "无法查看详情");
		}else{
			window.location.href = _ctxPath+"/control/entScore/manage/downloadHistory.do?path="+historyExist;
		}
	},
	resetScoring:function(obj){
		var grid = Ext.ComponentQuery.query('entScoreManageView grid')[0];
		Ext.MessageBox.confirm("请确认","是否重新进行评分？",function( button,text ){
			if( button == 'yes'){
				Ext.getCmp("managewin").close();
				var win = new Ext.Window({
					id:'rodioWin',
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
								url : _ctxPath+"/control/resetEntScore/resetScoring.do",
								method : 'POST',
								params : { 
								    state:Ext.getCmp('state').lastValue.state,
									resultID : resultID
								},
								success : function(reponse, option) {
									var responseJSON = Ext.decode(reponse.responseText);
									Ext.getCmp("rodioWin").close();
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
		var grid = Ext.ComponentQuery.query('entScoreManageView grid')[0];
		Ext.MessageBox.confirm("请确认","是否进行下一次评分？",function( button,text ){
			if( button == 'yes'){
				Ext.Ajax.request({
					url : _ctxPath+"/control/resetEntScore/secondScoring.do",
					method : 'POST',
					params : {
						resultID : resultID
					},
					success : function(reponse, option) {
						var responseJSON = Ext.decode(reponse.responseText);
						Ext.MessageBox.alert("提示消息", responseJSON.msg);
						Ext.getCmp("managewin").close();
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
		var view=Ext.ComponentQuery.query('entScoreManageView')[0];
		for ( var int = 0; int < view.items.length; int++) {
			if(view.items.length>1){
				view.remove(view.items.last());
			}
		}
	},
	//选择行业模型
	firstIndustry:function(obj){
		var store=this.getSecondModelCombosStore();
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
		var grid =Ext.ComponentQuery.query('entScoreManageView grid')[0];
		var store=grid.getStore();
		var form=Ext.ComponentQuery.query('industrys form#enterpriseIndustryForm')[0];
		var basicForm=form.getForm();
		if(basicForm.isValid()){
			basicForm.doAction('submit',{
				url:_ctxPath+'/control/entScore/manage/saveModel.do',
				method:'post',
				success : function(form, action) {
				  if(action.result.status==true){
						Ext.Msg.alert('提示', action.result.msg);
						basicForm.reset();
						store.load();
						Ext.ComponentQuery.query('industrys')[0].close();
					}else{
						Ext.ComponentQuery.query('industrys')[0].close();
						Ext.Msg.alert('提示', action.result.msg);
					}
				}
			});
		}
	}
});
