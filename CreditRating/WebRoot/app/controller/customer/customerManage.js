Ext.define('PM.controller.customer.customerManage', {
	extend : 'Ext.app.Controller',
	stores:['customer.customerLists','customer.modelCombos','SecondModelCombos'],
	views :['customer.customerManageView','customer.customerAdd','customer.customerEdit',
	        'customer.industry','customer.appointOrganization'],
	init : function() {
		this.control({
			'customerManageView grid#customerListPanel' : {
				actioncolumnclick: this.changeStateOfCustomer//激活或锁定用户
			},
			'customerManageView grid button[action=addCustomer]' : {
				click: this.addUI//增加用户
			},
			'customerAdd button[action=saveCustomer]' : {
				click: this.saveCustomer//保存用户
			},
			'customerManageView button[action=deleteCustomer]' : {
				click: this.deleteCustomer//删除用户
			},
			'customerManageView button[action=editCustomer]' : {
				click: this.editCustomer//编辑用户
			},
			'customerEdit button[action=updateCustomer]' : {
				click: this.updateCustomer//编辑用户
			},
			'customerManageView grid button[action=passwordInit]' : {
				click: this.passwordInit//密码初始化
			},
			'customerManageView grid button[action=AssignIndustry]' : {
				click: this.AssignIndustry//分配行业模型
			},
			'customerManageView grid button[action=AssignOrganization]' : {
				click: this.AssignOrganization//分配机构
			},
			'appointOrganization button[action=saveOrganization]' : {
				click: this.saveOrganization//保存机构
			},
			'industry combo#customerFirstIndustry':{
				select:this.firstIndustry
			},
			'industry button[action=giveIndustry]' : {
				click: this.giveIndustry//赋予行业
			},
			'customerManageView grid button[action=customerQuery]' : {
				click: this.customerQuery//查询用户
			},
	  		'customerVisible button' : {
				click : this.updateOrClose
			},

		});
	},

//激活或锁定用户
changeStateOfCustomer:function(grid, rowIndex, colIndex){
	var me = this;
	var rec = grid.getStore().getAt(rowIndex);
	var userName=rec.get("userName");
	var enterpriseID=rec.get("entID");
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
},
	//增加用户
	addUI:function(){
		Ext.widget('customerAdd');
	},
	saveCustomer:function(btn){
		var gridpanel =Ext.ComponentQuery.query('customerManageView grid')[0];
		var store = gridpanel.getStore();
		var formpanel=Ext.ComponentQuery.query('customerAdd form')[0];
		var basicForm = formpanel.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				url : _ctxPath+'/control/customer/manage/addCustomer.do',
				method : 'post',
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.ComponentQuery.query('customerAdd')[0].close();
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
	deleteCustomer:function(){
		var gridpanel =Ext.ComponentQuery.query('customerManageView grid')[0];
		var store = gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			Ext.Msg.confirm('请确认', '是否真的要删除数据?', function(button) {
				if (button =='yes') {
					var userName = records[0].get("userName");
					Ext.Ajax.request({
						url : _ctxPath+'/control/customer/manage/deleteCustomer.do',
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
	editCustomer:function(){
		var gridpanel =Ext.ComponentQuery.query('customerManageView grid')[0];
		var records = gridpanel.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			Ext.Ajax.request({
				url : _ctxPath+'/control/customer/manage/editCustomerUI.do',
				method : 'POST',
				params : {
					userName : records[0].data.userName
				},
				success : function(response, opts) {
					var respText = Ext.JSON.decode(response.responseText); 
					if (respText.status == true) {
						Ext.widget('customerEdit');
						var formpanel = Ext.ComponentQuery.query('customerEdit form')[0];
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
	updateCustomer:function(){
		var gridpanel =Ext.ComponentQuery.query('customerManageView grid')[0];
		var store = gridpanel.getStore();
		var formpanel = Ext.ComponentQuery.query('customerEdit form')[0];
		var basicForm = formpanel.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				url : _ctxPath+'/control/customer/manage/editCustomer.do',
				method : 'post',
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						store.load();
						Ext.ComponentQuery.query('customerEdit')[0].close();
					} else {
						Ext.Msg.alert('提示', action.result.msg);
						Ext.ComponentQuery.query('customerEdit')[0].close();
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
		var gridpanel =Ext.ComponentQuery.query('customerManageView grid')[0];
		var store=gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		if(records.length==0){
			Ext.Msg.alert('提示', '请选择一条数据进行操作');
		}else{
			var userName = records[0].get("userName");
			Ext.Msg.confirm("请确认","是否真的执行密码初始化操作",function(button){
				if(button=='yes'){
					Ext.Ajax.request({
						url : _ctxPath+'/control/customer/manage/initPWD.do',
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
		var gridpanel =Ext.ComponentQuery.query('customerManageView grid')[0];
		var store=gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		if(records.length==0){
			Ext.Msg.alert('提示', '请选择一条数据进行操作');
		}else{
			Ext.widget('industry');
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
		var grid =Ext.ComponentQuery.query('customerManageView grid')[0];
		var store=grid.getStore();
		var form=Ext.ComponentQuery.query('industry form#customerIndustryForm')[0];
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
						Ext.ComponentQuery.query('industry')[0].close();
					}else{
						Ext.ComponentQuery.query('industry')[0].close();
						Ext.Msg.alert('提示', action.result.msg);
					}
				}
			});
		}
	},
	//分配机构
	AssignOrganization:function(btn){
		var gridpanel =Ext.ComponentQuery.query('customerManageView grid')[0];
		var store=gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		if(records.length==0){
			Ext.Msg.alert('提示', '请选择一条数据进行操作');
		}else{
			Ext.widget('appointOrganization');
			var userName=records[0].get("userName");
			Ext.Ajax.request({
				url :  _ctxPath+'/control/customer/manage/appointOrganizationUI.do',
				params : {
					userName:userName,
				},
				success : function(response, option) {
					var resptxt=Ext.decode(response.responseText);
					Ext.ComponentQuery.query('appointOrganization textfield[name=text]')[0].setValue(resptxt.organizationName);
				},
				failure : function() {
					Ext.MessageBox.alert("提示消息", "用户分配部门失败！");
				}
		});
		var orgStore=new Ext.data.TreeStore({
			proxy:{
				type : 'ajax',
				method : 'POST',
				url : _ctxPath+'/control/customer/manage/appointOrganizationUI.do',
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
						Ext.ComponentQuery.query('appointOrganization textfield[name=text]')[0].setValue(record.get("text"));
						Ext.ComponentQuery.query('appointOrganization textfield[name=pId]')[0].setValue(userName);
						Ext.ComponentQuery.query('appointOrganization textfield[name=id]')[0].setValue(record.get("id"));
				}
			}
		});
		Ext.ComponentQuery.query('appointOrganization')[0].add(orgTree);
		}
	},
	//保存分配机构
	saveOrganization:function(btn){
		var gridpanel =Ext.ComponentQuery.query('customerManageView grid')[0];
		var store = gridpanel.getStore();
		var formpanel = Ext.ComponentQuery.query('appointOrganization form')[0];
		var basicForm = formpanel.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				url : _ctxPath+'/control/customer/manage/appointOrganization.do',
				method : 'post',
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						store.load();
						Ext.ComponentQuery.query('appointOrganization')[0].close();
					} else {
						Ext.Msg.alert('提示', action.result.msg);
						Ext.ComponentQuery.query('appointOrganization')[0].close();
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
	customerQuery:function(btn){
		var grid = Ext.ComponentQuery.query('customerManageView grid')[0];
		var input=Ext.ComponentQuery.query('customerManageView grid textfield[name=searchmatter]')[0];
		var inputValue=input.getValue();
		grid.getStore().getProxy().extraParams = {
			 userName : inputValue
		};
		grid.getStore().load();
		
	}
});