Ext.define('PM.controller.person.personManage', {
	extend : 'Ext.app.Controller',
	stores:['person.personLists','person.modelCombos','SecondModelCombos'],
	views :['person.personManageView','person.personAdd','person.personEdit',
	        'person.perIndustry','person.perAppointOrganization'],
	init : function() {
		this.control({
			'personManageView grid#personListPanel' : {
				actioncolumnclick: this.changeStateOfperson//激活或锁定用户
			},
			'personManageView grid button[action=addperson]' : {
				click: this.addUI//增加用户
			},
			'personAdd button[action=saveperson]' : {
				click: this.saveperson//保存用户
			},
			'personManageView button[action=deleteperson]' : {
				click: this.deleteperson//删除用户
			},
			'personManageView button[action=editperson]' : {
				click: this.editperson//编辑用户
			},
			'personEdit button[action=updateperson]' : {
				click: this.updateperson//编辑用户
			},
			'personManageView grid button[action=passwordInit]' : {
				click: this.passwordInit//密码初始化
			},
			'personManageView grid button[action=AssignIndustry]' : {
				click: this.AssignIndustry//分配行业模型
			},
			'personManageView grid button[action=AssignOrganization]' : {
				click: this.AssignOrganization//分配机构
			},
			'perAppointOrganization button[action=saveOrganization]' : {
				click: this.saveOrganization//保存机构
			},
			'perIndustry combo#personFirstIndustry':{
				select:this.firstIndustry
			},
			'perIndustry button[action=giveIndustry]' : {
				click: this.giveIndustry//赋予行业
			},
			'personManageView grid button[action=personQuery]' : {
				click: this.personQuery//查询用户
			},
	  		'personVisible button' : {
				click : this.updateOrClose
			},

		});
	},

//激活或锁定用户
changeStateOfperson:function(grid, rowIndex, colIndex){
	var me = this;
	var rec = grid.getStore().getAt(rowIndex);
	var userName=rec.get("userName");
	var enterpriseID=rec.get("perID");
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
},
	//增加用户
	addUI:function(){
		Ext.widget('personAdd');
	},
	saveperson:function(btn){
		var gridpanel =Ext.ComponentQuery.query('personManageView grid')[0];
		var store = gridpanel.getStore();
		var formpanel=Ext.ComponentQuery.query('personAdd form')[0];
		var basicForm = formpanel.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				url : _ctxPath+'/control/person/manage/addPerson.do',
				method : 'post',
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.ComponentQuery.query('personAdd')[0].close();
						store.load();
			  			Ext.Msg.alert('操作提示', action.result.msg);
					} else {
						Ext.Msg.alert('操作提示', action.result.msg);
					}
				},
				failure : function() {
					failAjaxTips();
				}
			});
		} else {
			Ext.Msg.alert('提示', '请继续补充表单必填项!');
		}
	},
	//删除用户
	deleteperson:function(){
		var gridpanel =Ext.ComponentQuery.query('personManageView grid')[0];
		var store = gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			Ext.Msg.confirm('请确认', '是否真的要删除数据?', function(button) {
				if (button =='yes') {
					var userName = records[0].get("userName");
					Ext.Ajax.request({
						url : _ctxPath+'/control/person/manage/deletePerson.do',
						method : 'POST',
						params : {
							userName : userName
						},
						success : function(reponse, option) {
							var responseJSON = Ext.decode(reponse.responseText);
                        	if(responseJSON.status==true){
                        		store.remove(records[0]);
                        		records[0].commit();
								Ext.MessageBox.alert("提示消息", "删除成功！");
                        	}else{
                        		Ext.Msg.alert(responseJSON.msg,responseJSON.result);
                        	}
							store.load();
								
						},
						failure : function() {
							failAjaxTips();
						}
					});
				}
			});
		}
	},
	//编辑用户
	editperson:function(){
		var gridpanel =Ext.ComponentQuery.query('personManageView grid')[0];
		var records = gridpanel.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			Ext.Ajax.request({
				url : _ctxPath+'/control/person/manage/editPersonUI.do',
				method : 'POST',
				params : {
					userName : records[0].data.userName
				},
				success : function(response, opts) {
					var respText = Ext.JSON.decode(response.responseText); 
					if (respText.status == true) {
						Ext.widget('personEdit');
						var formpanel = Ext.ComponentQuery.query('personEdit form')[0];
						var basicForm = formpanel.getForm();
						basicForm.loadRecord(records[0]);
					}else{
						Ext.MessageBox.alert("提示消息", respText.msg);
					}
				},
				failure : function(response, opts) {
					Ext.MessageBox.alert("提示消息", respText.msg);
				}});
		}
	},
	//更新用户信息
	updateperson:function(){
		var gridpanel =Ext.ComponentQuery.query('personManageView grid')[0];
		var store = gridpanel.getStore();
		var formpanel = Ext.ComponentQuery.query('personEdit form')[0];
		var basicForm = formpanel.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				url : _ctxPath+'/control/person/manage/editPerson.do',
				method : 'post',
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						store.load();
						Ext.ComponentQuery.query('personEdit')[0].close();
					} else {
						Ext.Msg.alert('提示', action.result.msg);
						Ext.ComponentQuery.query('personEdit')[0].close();
					}
				},
				// 提交失败的回调函数
				failure : function() {
					failAjaxTips();
				}
			});
		} else {
			Ext.Msg.alert('错误', '表单验证失败！');
		}
	},
	//密码初始化
	passwordInit:function(){
		var gridpanel =Ext.ComponentQuery.query('personManageView grid')[0];
		var store=gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		if(records.length==0){
			Ext.Msg.alert('提示', '请选择一条数据进行操作');
		}else{
			var userName = records[0].get("userName");
			Ext.Msg.confirm("请确认","是否真的执行密码初始化操作",function(button){
				if(button=='yes'){
					Ext.Ajax.request({
						url : _ctxPath+'/control/person/manage/initPWD.do',
						method : 'POST',
						params : {
							userName : userName
						},
						success : function(reponse, option) {
								store.load();
								records[0].commit();
								var responseJSON = Ext.decode(reponse.responseText);
								Ext.MessageBox.alert("提示消息", responseJSON.msg);
						},
						failure : function() {
							Ext.MessageBox.alert("提示消息", "初始化失败！");
						}
					});
				}
			});
		}
	},
	//分配行业
	AssignIndustry:function(btn){
		var gridpanel =Ext.ComponentQuery.query('personManageView grid')[0];
		var store=gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		if(records.length==0){
			Ext.Msg.alert('提示', '请选择一条数据进行操作');
		}else{
			Ext.widget('perIndustry');
		}
	},
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
	giveIndustry:function(btn){
		var grid =Ext.ComponentQuery.query('personManageView grid')[0];
		var store=grid.getStore();
		var form=Ext.ComponentQuery.query('perIndustry form#personIndustryForm')[0];
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
						Ext.ComponentQuery.query('perIndustry')[0].close();
					}else{
						Ext.ComponentQuery.query('perIndustry')[0].close();
						Ext.Msg.alert('提示', action.result.msg);
					}
				}
			});
		}
	},
	//分配机构
	AssignOrganization:function(btn){
		var gridpanel =Ext.ComponentQuery.query('personManageView grid')[0];
		var store=gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		if(records.length==0){
			Ext.Msg.alert('提示', '请选择一条数据进行操作');
		}else{
			Ext.widget('perAppointOrganization');
			var userName=records[0].get("userName");
			Ext.Ajax.request({
				url :  _ctxPath+'/control/person/manage/appointOrganizationUI.do',
				params : {
					userName:userName,
				},
				success : function(response, option) {
					var resptxt=Ext.decode(response.responseText);
					Ext.ComponentQuery.query('perAppointOrganization textfield[name=text]')[0].setValue(resptxt.organizationName);
				},
				failure : function() {
					Ext.MessageBox.alert("提示消息", "用户分配部门失败！");
				}
		});
		var orgStore=new Ext.data.TreeStore({
			proxy:{
				type : 'ajax',
				method : 'POST',
				url : _ctxPath+'/control/person/manage/appointOrganizationUI.do',
				reader : {
					type : 'json',
					root : 'organizations'
				},
				extraParams : {
					userName:userName
				}
			},
			autoLoad:true
		});
		var orgTree=Ext.create('Ext.tree.Panel',{
			width:425,
			height:500,
			padding:18,
			border:false,
		    store: orgStore,
		    useArrows : true,  
		    rootVisible: false,
		    renderTo: Ext.getBody(),
		    listeners:{
				itemclick:function(view,record){
						Ext.ComponentQuery.query('perAppointOrganization textfield[name=text]')[0].setValue(record.get("text"));
						Ext.ComponentQuery.query('perAppointOrganization textfield[name=pId]')[0].setValue(userName);
						Ext.ComponentQuery.query('perAppointOrganization textfield[name=id]')[0].setValue(record.get("id"));
				}
			}
		});
		Ext.ComponentQuery.query('perAppointOrganization')[0].add(orgTree);
		}
	},
	//保存分配机构
	saveOrganization:function(btn){
		var gridpanel =Ext.ComponentQuery.query('personManageView grid')[0];
		var store = gridpanel.getStore();
		var formpanel = Ext.ComponentQuery.query('perAppointOrganization form')[0];
		var basicForm = formpanel.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				url : _ctxPath+'/control/person/manage/appointOrganization.do',
				method : 'post',
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						store.load();
						Ext.ComponentQuery.query('perAppointOrganization')[0].close();
					} else {
						Ext.Msg.alert('提示', action.result.msg);
						Ext.ComponentQuery.query('perAppointOrganization')[0].close();
					}
				},
				// 提交失败的回调函数
				failure : function() {
					failAjaxTips();
				}
			});
		} else {
			Ext.Msg.alert('错误', '表单验证失败！');
		}
	},
	personQuery:function(btn){
		var grid = Ext.ComponentQuery.query('personManageView grid')[0];
		var input=Ext.ComponentQuery.query('personManageView grid textfield[name=searchmatter]')[0];
		var inputValue=input.getValue();
		grid.getStore().getProxy().extraParams = {
			 userName : inputValue
		};
		grid.getStore().load();
		
	}
});