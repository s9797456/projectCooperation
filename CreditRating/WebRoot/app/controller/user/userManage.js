Ext.define('PM.controller.user.userManage', {
	extend : 'Ext.app.Controller',
	stores:['user.userStore','user.menuPrivileges'],
	views :['user.userManageView','user.userAdd','user.userEdit','user.userPrivilegegroup','user.userDeps','user.menuPrivilegeList'],
	
	init : function() {
		this.control({
	  		'userManageView grid button[action=addUser]' : {
				  click: this.addUI//增加用户
	  		  },
	  		'userAdd button[action=saveUser]' : {
				  click: this.saveUser//保存用户
	  		  },
	  		'userManageView button[action=deleteUser]' : {
				  click: this.deleteUser//删除用户
	  		  },
	  		'userManageView button[action=editUser]' : {
				  click: this.editUser//编辑用户
	  		  },
	  		'userEdit button[action=updateUser]' : {
				  click: this.updateUser//编辑用户
	  		  },
	  		'userManageView grid button[action=passwordInit]' : {
				  click: this.passwordInit//密码初始化
	  		  },
	  		'userManageView grid button[action=AssignPvilegegroup]' : {
				  click: this.AssignPvilegegroup//分配角色
	  		  },
	  		'userManageView grid button[action=AssignDep]' : {
				  click: this.AssignDep//分配部门
	  		  },
	  		'privilegegroupList button[action=givePrivilege]' : {
	  			  click: this.givePrivilege//赋予角色
	  		  },
	  		'userDeps button[action=saveUserDep]' : {
				  click: this.saveUserDep//保存用户部门
	  		  },
	  		'userManageView grid button[action=AssignMenuPvilege]' : {
				  click: this.AssignMenuPvilege//分配菜单权限
	  		  },
	  		'menuPrivilegeList button[action=saveMenuPrivilege]' : {
				  click: this.saveMenuPrivilege//更新分配菜单权限
	  		  },
	  		'userManageView grid button[action=userQuery]' : {
				  click: this.userQuery//查询用户
	  		  },
	  		'userEdit button[action=userImgUpload]' : {
				  click: this.userImgUpload//上传附件
	  		  },
	  		
		});
	},
	//增加用户
	addUI:function(){
		Ext.widget('userAdd');
	},
	saveUser:function(btn){
		var gridpanel =Ext.ComponentQuery.query('userManageView grid')[0];
		var store = gridpanel.getStore();
		var formpanel=Ext.ComponentQuery.query('userAdd form')[0];
		var basicForm = formpanel.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				url : _ctxPath+'/control/user/manage/addUser.do',
				method : 'post',
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.ComponentQuery.query('userAdd')[0].close();
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
	deleteUser:function(){
		var gridpanel =Ext.ComponentQuery.query('userManageView grid')[0];
		var store = gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		var userName="";
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择记录进行操作');
		} else{
			Ext.Msg.confirm('请确认', '是否真的要删除数据?', function(button) {
				if (button =='yes') {
					 for (var i = 0; i < records.length; i++) {
						 var un=records[i].get("username");
						 userName+=un+",";
                     }
					Ext.Ajax.request({
						url : _ctxPath+'/control/user/manage/deleteUser.do',
						method : 'POST',
						params : {
							userNames : userName
						},
						success : function(response, option) {
							var respText = Ext.JSON.decode(response.responseText); 
							if (respText.status == true) {
								store.remove(records[0]);
								store.load();
								records[0].commit();
								Ext.MessageBox.alert("提示消息", respText.msg);
							}else{
								Ext.MessageBox.alert("提示消息", respText.msg);
							}
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
	editUser:function(){
		var gridpanel =Ext.ComponentQuery.query('userManageView grid')[0];
		var records = gridpanel.getSelectionModel().getSelection();
		if (records.length == 1) {
			Ext.Ajax.request({
				url : _ctxPath+'/control/user/manage/editUI.do',
				method : 'POST',
				params : {
					userName : records[0].data.username
				},
				success : function(response, opts) {
					var respText = Ext.JSON.decode(response.responseText); 
					if (respText.status == true) {
						Ext.widget('userEdit');
						var formpanel = Ext.ComponentQuery.query('userEdit form')[0];
						var basicForm = formpanel.getForm();
						basicForm.loadRecord(records[0]);

						var username1=Ext.getCmp('username1');
						username1.setValue(records[0].data.username);
						var staffavatar1=Ext.getCmp('staffavatar');
						if(respText.imgurl==null){
							staffavatar1.setSrc(_ctxPath+respText.defaultAvatar);
						}else{
							staffavatar1.setSrc(_ctxPath+respText.imgurl);
						}
					}else{
						Ext.MessageBox.alert("提示消息", respText.msg);
					}
				},
				failure : function(response, opts) {
					Ext.MessageBox.alert("提示消息", respText.msg);
				}});
		} else  {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} 
	},
	//更新用户信息
	updateUser:function(){
		var gridpanel =Ext.ComponentQuery.query('userManageView grid')[0];
		var store = gridpanel.getStore();
		var formpanel = Ext.ComponentQuery.query('userEdit form')[0];
		var basicForm = formpanel.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				url : _ctxPath+'/control/user/manage/editUser.do',
				method : 'post',
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						store.load();
						Ext.ComponentQuery.query('userEdit')[0].close();
					} else {
						Ext.Msg.alert('提示', action.result.msg);
						Ext.ComponentQuery.query('userEdit')[0].close();
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
		var gridpanel =Ext.ComponentQuery.query('userManageView grid')[0];
		var store=gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		if(records.length==1){
			var username = records[0].get("username");
			Ext.Msg.confirm("请确认","是否执行密码初始化操作",function(button){
				if(button=='yes'){
					Ext.Ajax.request({
						url : _ctxPath+'/control/user/manage/initPWD.do',
						method : 'POST',
						params : {
							userName : username
						},
						success : function(reponse, option) {
								store.load();
								records[0].commit();
								Ext.MessageBox.alert("提示消息", "密码初始化成功!<br>初始化密码：123456");
						},
						failure : function() {
							Ext.MessageBox.alert("提示消息", "初始化失败！");
						}
					});
				}
			});
		
		}else{
			Ext.Msg.alert('提示', '请选择一条数据进行操作');
		}
	},
	
	//分配角色
	AssignPvilegegroup:function(btn){
		var gridpanel =Ext.ComponentQuery.query('userManageView grid')[0];
		var records = gridpanel.getSelectionModel().getSelection();
		if(records.length==0){
			Ext.Msg.alert('提示', '请选择一条数据进行操作');
		}else{
			Ext.widget('privilegegroupList');
		}
	},
	//赋予角色
	givePrivilege:function(){
		var gridpanel=Ext.ComponentQuery.query('userManageView grid')[0];
		var store=gridpanel.getStore();
		var records = gridpanel.getSelectionModel().getSelection();
		var userName=records[0].get("username");
		var basicForm=Ext.ComponentQuery.query('privilegegroupList form')[0].getForm();
		var gcbItems=Ext.ComponentQuery.query('privilegegroupList form checkboxgroup')[0].items;
		var ids=[];
		if(gcbItems.length==0){
			ids=[];
		}else{
			for(var i=0;i<gcbItems.length;i++){
				if(gcbItems.get(i).checked){
					ids[i]=gcbItems.get(i).inputValue;
				}
			}
		}
		basicForm.doAction('submit', {
			url : _ctxPath+'/control/user/manage/privilegeGroupEdit.do',
			method:'post',
			params:{
				userName:userName,
				groupIds:ids
			},
			success : function(form, action) {
				if (action.result.status == true) {
					Ext.Msg.alert('提示', action.result.msg);
					store.load();
					Ext.ComponentQuery.query('privilegegroupList')[0].close();
				}else {
					Ext.Msg.alert('提示', action.result.msg);
					Ext.ComponentQuery.query('privilegegroupList')[0].close();
				}
			},
			failure : function() {
				Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
			}
		});
	},
	//分配部门权限
	AssignDep:function(btn){
		var gridpanel =Ext.ComponentQuery.query('userManageView grid')[0];
		var records = gridpanel.getSelectionModel().getSelection();
		if(records.length==0){
			Ext.Msg.alert('提示', '请选择一条数据进行操作');
		}else{
			 var userName=records[0].get("username");
			 Ext.widget('userDeps');
			 Ext.Ajax.request({
					url :  _ctxPath+'/control/user/manage/editDepUI.do',
					
					params : {
						userName:userName,
						node:"root",
					},
					success : function(response, option) {
						var resptxt=Ext.decode(response.responseText);
						Ext.ComponentQuery.query('userDeps textfield[name=depName]')[0].setValue(resptxt.userDep);
					},
					failure : function() {
						Ext.MessageBox.alert("提示消息", "用户分配部门失败！");
					}
				});
		var depStore=new Ext.data.TreeStore({
			proxy:{
				type : 'ajax',
				method : 'POST',
				url : _ctxPath+'/control/user/manage/editDepUI.do',
				reader : {
					type : 'json',
					root : 'responseJsonTree'
				},
				extraParams : {
					userName:userName
				}
			},
			autoLoad:true
		});
		var depTree=Ext.create('Ext.tree.Panel',{
			width:425,
			height:500,
			padding:18,
			border:false,
		    store: depStore,
		    useArrows : true,  
		    rootVisible: false,
		    renderTo: Ext.getBody(),
		    listeners:{
				itemclick:function(view,record){
						Ext.ComponentQuery.query('userDeps textfield[name=depName]')[0].setValue(record.get("text"));
						Ext.ComponentQuery.query('userDeps textfield[name=userName]')[0].setValue(userName);
						Ext.ComponentQuery.query('userDeps textfield[name=department]')[0].setValue(record.get("id"));
				}
			}
		});
		Ext.ComponentQuery.query('userDeps')[0].add(depTree);
	}
},

	//更新用户部门权限
	saveUserDep:function(){
		   Ext.Ajax.request({
				url : _ctxPath+'/control/user/manage/editDep.do',
				method : 'POST',
				params : {
					userName:Ext.ComponentQuery.query('userDeps textfield[name=userName]')[0].getValue(),
					departmentId:Ext.ComponentQuery.query('userDeps textfield[name=department]')[0].getValue()
				},
				success : function(reponse, option) {
						Ext.MessageBox.alert("提示消息", " 用户分配部门成功,请刷新！");
						Ext.ComponentQuery.query('userDeps')[0].close();
				},
				failure : function() {
					Ext.MessageBox.alert("提示消息", "用户分配部门失败！");
				}
			});
	   },
	
	//分配菜单权限
	AssignMenuPvilege:function(btn){
		var gridpanel=Ext.ComponentQuery.query('userManageView grid')[0];
		var records = gridpanel.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		}else{
			Ext.widget('menuPrivilegeList');
			var treeStore=Ext.ComponentQuery.query('menuPrivilegeList treepanel')[0].getStore();
			treeStore.on('beforeload',function(store,option){
				var params={userName:records[0].get("username")};
				Ext.apply(store.proxy.extraParams,params);
			});
			treeStore.load();
		}
	},
    //更新用户菜单权限
	saveMenuPrivilege:function(){
		var selectId=[];
		var gridpanel=Ext.ComponentQuery.query('userManageView grid')[0];
		var treepanel=Ext.ComponentQuery.query('menuPrivilegeList treepanel')[0];
		var store=treepanel.getStore();
		var username= store.getProxy().extraParams.userName;
		var treeRecord=treepanel.getChecked();
		j=0;
		if(treeRecord.length==0){
			selectId=[];
		}else{
			for(var i=0;i<treeRecord.length;i++){
				if(treeRecord[i].get('leaf')){
					selectId[j]=treeRecord[i].get("id");
					j++;
				}
			}
		}
		Ext.Ajax.request({
			url : _ctxPath+'/control/user/manage/menuPrivilegeEdit.do',
			method : 'POST',
			params : {
				userName :username,
				privilegesStr:selectId
			},
			success : function(response, option) {
				var respText = Ext.JSON.decode(response.responseText); 
				if (respText.status == true) {
					Ext.MessageBox.alert("提示消息", respText.msg);
					gridpanel.getStore().load();
					Ext.ComponentQuery.query('menuPrivilegeList')[0].close();
				}else{
					Ext.MessageBox.alert("提示消息", respText.msg);
				}
			},
			failure : function() {
				Ext.MessageBox.alert("提示消息", "操作失败！");
			}
		});
	},
	//查询用户
	userQuery:function(btn){
		var grid = Ext.ComponentQuery.query('userManageView grid')[0];
		var input=Ext.ComponentQuery.query('userManageView grid textfield[name=searchmatter]')[0];
		var inputValue=input.getValue();
		grid.getStore().getProxy().extraParams = {
			 userName : inputValue
		};
		grid.getStore().load();
		
	},

	/*editIndustry:function(btn){
		var judgeAction = btn.action;
		var gridpanel =Ext.ComponentQuery.query('userManageView grid')[0];
		if(judgeAction=='editIndustry'){
			var form=Ext.ComponentQuery.query('editIndustry form#editIndustryForm')[0];
			var basicForm=form.getForm();
			basicForm.doAction('submit',{
				url:_ctxPath+'/control/user/manage_setIndustry.do',
				method:'post',
				params:{
					industry : basicForm.findField("industry").lastValue
				},
				success: function (form, action) {
		  			Ext.ComponentQuery.query('editIndustry')[0].close();
		  			gridpanel.getStore().load();
		  	    }
			});
		}else{
			Ext.ComponentQuery.query('editIndustry')[0].close();
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
		var grid =Ext.ComponentQuery.query('userManageView grid')[0];
		var store=grid.getStore();
		var form=Ext.ComponentQuery.query('industryList form#industryForm')[0];
		var basicForm=form.getForm();
		if(basicForm.isValid()){
			basicForm.doAction('submit',{
				url:_ctxPath+'/control/score/enterprise_info/manage_saveModel.do',
				method:'post',
				success : function(form, action) {
				  if(action.result.status==true){
						Ext.Msg.alert('提示', action.result.msg);
						basicForm.reset();
						store.load();
						Ext.ComponentQuery.query('industryList')[0].close();
					}else{
						Ext.ComponentQuery.query('industryList')[0].close();
						Ext.Msg.alert('提示', action.result.msg);
					}
				}
			});
		}
	},*/

	//头像上传
	userImgUpload :function(){
		var gridpanel =Ext.ComponentQuery.query('userManageView grid')[0];
		var store = gridpanel.getStore();
		var form = Ext.ComponentQuery.query('userEdit form')[0];
		var basic = form.getForm();
		var username1=Ext.getCmp('username1');
		alert(username1.getValue());
		if(username1.getValue() == null || username1.getValue().trim == ""){
			Ext.Msg.alert('提示', '未添加需上传的文件');
		}
		basic.submit({
	  		waitTitle: '提示',
	  		url:_ctxPath+'/control/user/manage/userImgUpload.do',
	  		params: {
	        	username:username1.getValue(),
	        },
	        method:'post',
	  		success: function (form, action) {
	  			if (action.result.status == true) {
					Ext.Msg.alert('提示', action.result.msg);
				}
	  	    },
	  	    failure: function(){
	  	    	Ext.Msg.alert('提示', "上传失败");
	  	    }
		});
	},
});
//将状态根据需要变成超链接
var changeStatus2Link=function(value){
	var url="javascript:changeMenuStatus()";
	var arr=value.split(" | ");
	var color="red";
	var tColor="green";
	if(arr[0]=="正常"){
		return "<font color="+tColor+">"+arr[0]+"</font>"+" | "+
					"<a href="+url+"><font color="+color+">"+arr[1]+"</font>"+"</a>";
	}else{
		return "<font color="+color+">"+arr[0]+"</font>"+" | "+
					"<a href="+url+"><font color="+color+">"+arr[1]+"</font>"+"</a>";
	}
	
};
//更改状态
var changeMenuStatus=function(){
	var surl=_ctxPath+"/control/user/manage/updateVisible.do";
	var visible=0;
	var grid = Ext.ComponentQuery.query('userManageView grid')[0];
	var store=grid.getStore();
	var records = grid.getSelectionModel().getSelection();
	var username=records[0].get("username");
	var status=records[0].get("visible").split(" | ");
	if(status[0]=="关闭")
		visible=1;
	Ext.Msg.confirm('请确认', '是否真的要更改状态?', function(button) {
		if (button =='yes') {
			Ext.Ajax.request({
				url : surl,
				method : 'POST',
				params : {
					userName:username,
					visible:visible
				},
				success : function(reponse, option) {
						store.load(); 
						records[0].commit();
						Ext.MessageBox.alert("提示消息", "更改成功！");
				},
				failure : function() {
					Ext.MessageBox.alert("提示消息", "更改失败！");
				}
			});
		}
	});
};



