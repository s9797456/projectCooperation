Ext.define('PM.controller.menu.menuManage', {
	extend : 'Ext.app.Controller',
	views : [ 'menu.menuManageView','menu.menuAdd','menu.menuEdit'],
	stores : [ 'menu.menuStore'],
	refs:[{
		ref:'menuManageView',
		selector:'menuManageView grid#menuManageView'
	},{
		ref:'addMenuForm',
		selector:'menuWindow form#addMenuForm'
	},{
		ref:'editionMenuForm',
		selector:'editionMenuWin form#editionMenuForm'
	}],
	init : function() {
		this.control({
			'menuManageView button[id="addBtn1"]' : {
				//新增菜单
				click : this.addMenu
			},
			'menuManageView button[id="deleteBtn1"]' : {
				//删除菜单
				click : this.deleteMenu
			},
			'menuManageView button[id="edtionBtn1"]' : {
				//编辑菜单
				click : this.edtionMenu
			},
			'menuManageView button[id="lookBtn"]' : {
				//菜单预览
				click : this.lookMenu
			},
			'menuWindow button[action="save"]':{
				click : this.doSave
			},
			'editionMenuWin button[action="saveBtn"]':{
				click : this.doUpdate
			},
		});
	},
	//新增菜单
	addMenu:function(){
		var grid=this.getMenuManageView();
		var store = grid.getStore();// 获得store对象
		var id ="";
		var selModel = grid.getSelectionModel();
		if(selModel){
				id= store.getProxy().extraParams.parentid;}
		Ext.Ajax.request({
			url : _ctxPath+'/control/menu/manage/addUI.do',
			method : 'post',
			params : {
				parentID:id
			},
			success : function(response, option) {
				var record=Ext.JSON.decode(response.responseText);
				if(record.status==true){
					var win=Ext.widget('menuWindow');
					Ext.getCmp("parentID").setValue(record.parentID);
				}else{
					Ext.Msg.alert('提示', record.msg);
				}
		},
			failure : function() {
		}
		});
	},
	//删除菜单
	deleteMenu:function(){
		var grid = this.getMenuManageView();
		var store = grid.getStore();
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			Ext.Msg.confirm('请确认', '是否真的要删除数据?', function(button) {
				if (button =='yes') {
					var vuuid = records[0].get("uuid"); 
					Ext.Ajax.request({
						url : _ctxPath+'/control/menu/manage/delete.do',
						method : 'POST',
						params : {
							uuid : vuuid,
						},
						success : function(response, option) {
							var record=Ext.JSON.decode(response.responseText);
							if(record.status==true){
								store.remove(records[0]);
								store.load();
								records[0].commit();
								Ext.MessageBox.alert("提示消息", "删除成功！");
							}else{
								Ext.Msg.alert('提示', record.msg);
							}
						},
						failure : function() {
							Ext.MessageBox.alert("提示消息", "删除失败！");
						}
					});
				}
			});
		}
	},
	//编辑菜单
	edtionMenu:function(){
		var grid = this.getMenuManageView();
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			var win =Ext.widget('editionMenuWin');
			var value=records[0].get('name');
			var arr=value.split(" ");
			var form = this.getEditionMenuForm();
			var basicForm = form.getForm();
			basicForm.loadRecord(records[0]);
			Ext.getCmp('menuName').setValue(arr[0]);
		}
	},
	//菜单预览
	lookMenu:function(){
		//菜单store
		var store=Ext.create("Ext.data.TreeStore",{
			proxy:{
				type:'ajax',
				url:_ctxPath+'/control/menu/menuPrivilegeTreeUI.do',
				reader:'json',
				aotoLoad:true
			}
		}); 
		//菜单树
		var menuTree=Ext.create("Ext.tree.Panel",{
			name:'menuTree',
			padding:18,
			border:false,
			margin:'14 14 14 30',
			id:'menuTree',
			useArrows : true,
			rootVisible: false,
			width:520,
			lines :false,
			store:store
		});
		//菜单窗口
		var win=Ext.create("Ext.window.Window",{
			id : 'menuTreeWin',
			border : 0,
			width : 600,
			height : 550,
			autoScroll: true, 
			title:'菜单预览',
			maximizable:true,
			frame: true,
			iconCls:'Applicationviewlist',
			autoShow : true,
			items:[menuTree]
		});
	},
	//保存菜单
	doSave:function(){
		var grid = this.getMenuManageView();
		var store = grid.getStore();
		var form = this.getAddMenuForm();
		var basicForm = form.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				// 文件路径
				url : _ctxPath+'/control/menu/manage/add.do',
				// 提交方法post或get
				method : 'post',
				params : '',
				// 提交成功的回调函数
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						basicForm.reset();
						store.load();
						Ext.getCmp("menuWindow").close();
					} else {
						Ext.Msg.alert('提示', action.result.msg);
					}
				},
				// 提交失败的回调函数
				failure : function() {
					Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
				}
			});
		} else {
			Ext.Msg.alert('提示', '请继续补充表单必填项!');
		}
	},
	doUpdate:function(){
		var grid = this.getMenuManageView();
		var store = grid.getStore();
		var form = this.getEditionMenuForm();
		var basicForm = form.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				// 文件路径
				url : _ctxPath+'/control/menu/manage/edit.do',
				// 提交方法post或get
				method : 'post',
				params : '',
				// 提交成功的回调函数
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						store.load();
						Ext.getCmp("editionMenuWin").close();
					} else {
						Ext.Msg.alert('提示', action.result.msg);
					}
				},
				// 提交失败的回调函数
				failure : function() {
					Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
				}
			});
		} else {
			Ext.Msg.alert('错误', '表单验证失败！');
		}
	}
});
//将菜单名变成超链接
var change2Link=function(value,field){//value为当前菜单名
	var url="javascript:getChildrenMenu();";
	var arr=value.split(" ");
	if(arr[1]==null){
		return "<a href="+url+" style='text-decoration:none;'>"+arr[0]+"</a>";
	}else if(arr[1]==0){
		return arr[0];
	}else{
	var color="red";
	return "<a href="+url+" style='text-decoration:none;'>"+arr[0]+"</a>"+
				"<font color="+color+">"+arr[1]+"</font>";
	}
};
//将状态根据需要变成超链接
var changeStatus2Link=function(value){
	var url="javascript:changeMenuStatus()";
	var arr=value.split(" | ");
	var color="red";
	var tColor="green";
	if(arr[0]=="正常"){
		return "<font color="+tColor+">"+arr[0]+"</font>"+" | "+
					"<a href="+url+"><font color="+color+">"+arr[1]+"</font>"+"</a>";
	}
	return "<font color="+color+">"+arr[0]+"</font>"+" | "+
				"<a href="+url+"><font color="+color+">"+arr[1]+"</font>"+"</a>";
};
//将地址与权限变成超链接
var changeAddress2Link=function(value){
	var urlAdress="javascript:urlEditUI()";
	var urlPrivilege="javascript:distributionPrivilege()";
	if(value!=null && value!=""){
		var arr=value.split(" | ");
		var color="red";
		return "<a href="+urlAdress+">"+"<font color="+color+">"+arr[0]+"</font>"+"</a>"+" | "+
					"<a href="+urlPrivilege+">"+"<font color="+color+">"+arr[1]+"</font>"+"</a>";
	}
	return ;
};


//更改菜单状态
var changeMenuStatus=function(){
	var surl=_ctxPath+"/control/menu/manage/updateVisible.do";
	var visible=0;
	var grid = Ext.getCmp("menuManageView");
	var store=grid.getStore();
	var records = grid.getSelectionModel().getSelection();
	var uuid=records[0].get("uuid");
	var status=records[0].get("status").split(" | ");
	if(status[0]=="关闭")
		visible=1;
	Ext.Msg.confirm('请确认', '是否真的要更改状态?', function(button) {
		if (button =='yes') {
			Ext.Ajax.request({
				url : surl,
				method : 'POST',
				params : {
					uuid:uuid,
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

//分配地址
var urlEditUI=function(){
	var menuBasicInfoForm=Ext.create("Ext.form.Panel",{
		id:'menuBasicInfo',
		margin:'0 20 15 45',
		width:580,
		height:200,
		defaults:{
			labelAlign:"left",
			labelWidth:100,
			width:450,
			allowBlank:false,
			msgTarget:"side",
			selectOnFocus:true
		},
		bodyStyle:'margin-top:15px;background:#ffc;',
		defaultType:'textfield',
		border:false,
		items:[{
				name:'uuid',
				hidden:true
			},{
				fieldLabel:"菜单名",
				name:'name',
				readOnly:true
			},{
				fieldLabel:"菜单地址",
				name:'url'
			},{
				fieldLabel:"视图层",
				name:'target',
			    validateOnBlur: true,
				validationDelay:2000,
				validateOnChange: false,
				validator: function (value) {
				    var error=true;
				    if(value.length>0){
				    	var validator = this;
				    	var error = true;
				    	Ext.Ajax.request({
				    		async: false,
				    		scope: validator,
				    		url: _ctxPath+'/control/menu/valid/targetExist.do',
				    		params: { 
				    			target: this.value,
				    			uuid: Ext.ComponentQuery.query('form textfield[name=uuid]')[0].getValue()
				    		},
				    		method: 'POST',
				    		success: function (response) {
				    			var result = Ext.JSON.decode(response.responseText);
				    			if (result.status==false) {
				    				error = result.msg;
				    			};
				    		},
				    	});
				    	return error;
				    }else{
				    	return error;
				    }
				} ,
				listeners: {
				    focus: function(){
				    	this.clearInvalid();
				    }
				}
			},{
				fieldLabel:"控制层",
				name:'rel',
				 validateOnBlur: true,
					validationDelay:2000,
					validateOnChange: false,
					validator: function (value) {
					    var error=true;
					    if(value.length>0){
					    	var validator = this;
					    	var error = true;
					    	Ext.Ajax.request({
					    		async: false,
					    		scope: validator,
					    		url: _ctxPath+'/control/menu/valid/relExist.do',
					    		params: { 
					    			rel: this.value,
					    			uuid: Ext.ComponentQuery.query('form textfield[name=uuid]')[0].getValue()
					    		},
					    		method: 'POST',
					    		success: function (response) {
					    			var result = Ext.JSON.decode(response.responseText);
					    			if (result.status==false) {
					    				error = result.msg;
					    			};
					    		},
					    	});
					    	return error;
					    }else{
					    	return error;
					    }
					} ,
					listeners: {
					    focus: function(){
					    	this.clearInvalid();
					    }
					}
		}]
	});
	//分配地址窗口
	var win=Ext.create('Ext.window.Window',{
		title:'分配地址',
		id : 'menuAdressWin',
		border :false,
		width : 600,
		height : 220,
		maximizable:true,
		bodyStyle: 'background:#ffc;',
		frame: true,
		iconCls:'Applicationviewlist',
		autoShow : true,
		items:[menuBasicInfoForm],
		buttons:[{
			text:'提交',
			handler:function(){
				var store=Ext.getCmp("menuManageView").getStore();
				var basicForm=Ext.getCmp("menuBasicInfo").getForm();
				if (basicForm.isValid()) {
					basicForm.doAction('submit', {
						url : _ctxPath+'/control/menu/manage/urlEdit.do',
						method : 'post',
						params :{},
						success : function(form, action) {
							if (action.result.status == true) {
								Ext.Msg.alert('提示', action.result.msg);
								basicForm.reset();
								store.load();
								Ext.getCmp("menuAdressWin").close();
							} else {
								Ext.Msg.alert('提示', action.result.msg);
							}
						},
						failure : function() {
							Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
						}
					});
				} else {
					Ext.Msg.alert('提示', '请继续补充表单必填项!');
				}
			}
		},{
			text:'关闭',
			handler:function(){
				Ext.getCmp("menuAdressWin").close();
			}
		}]
	});
	//将选中的记录加载到表单中
	var grid = Ext.getCmp("menuManageView");
	var records = grid.getSelectionModel().getSelection();
	var baiscForm=Ext.getCmp("menuBasicInfo").getForm();
	baiscForm.loadRecord(records[0]);
	
};
//分配权限
var distributionPrivilege=function(){
	var grid = Ext.getCmp("menuManageView");
	var records = grid.getSelectionModel().getSelection();
	var formSP=Ext.create("Ext.form.Panel",{
		id:'spform',
		border :false,
		width : 590,
		height : 500,
		bodyStyle: 'background:#ffc;',
		autoScroll: true,
		items:[],
		listeners:{
			  render:function(view, opt){ 
				   var cbg=this;  
				   var groupsCheckBoxs=new Array();
				   Ext.Ajax.request({ 
					   async:false,
					   url:_ctxPath+'/control/menu/manage/privilegeEditUI.do',
					   params : {
						   uuid:records[0].get("uuid")
					   },
					   method:'post',
					   success: function(response){ 
						  var groups=eval('('+response.responseText+')');
						  for(var i=0;i<groups.length;i++){
							  cbg.items.add( new Ext.form.CheckboxGroup({  
								  xtype:groups[i].xtype,
								  padding:10,
								  items:groups[i].items,
								  columns:3
							  }));
						  }
						 }
				   });
			  }
		 }
	});
	//分配权限窗口
	var win=Ext.create('Ext.window.Window',{
		title:"分配权限",
		id:'distributionPrivilegeWin',
		border :false,
		width : 600,
		height : 560,
		maximizable:true,
		//bodyStyle: 'background:#ffc;',
		frame: true,
		iconCls:'Applicationviewlist',
		autoShow : true,
		items:formSP,
		buttons:[{
			text:'提交',
			handler:function(){
				var checkboxsform=Ext.getCmp("spform");
				var basicForm=checkboxsform.getForm();
				var uuid=(records[0].get("uuid"));
				basicForm.doAction("submit",{
					url:_ctxPath+'/control/menu/manage/privilegeEdit.do',
					method:'post',
					params:{
						uuid:uuid
					},
					success : function(form, action) {
						if (action.result.status == true) {
							Ext.Msg.alert('提示', action.result.msg);
							Ext.getCmp("distributionPrivilegeWin").close();
						}else {
							Ext.Msg.alert('提示', action.result.msg);
							Ext.getCmp("distributionPrivilegeWin").close();
						}
					},
					failure : function() {
						Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
						Ext.getCmp("distributionPrivilegeWin").close();
					}
				});
			}
		},{
			text:'关闭',
			handler:function(){
				Ext.getCmp("distributionPrivilegeWin").close();
			}
		}]
	});
};
//获得子菜单的基本信息
var getChildrenMenu=function(){
	var grid = Ext.getCmp("menuManageView");
	var records = grid.getSelectionModel().getSelection();
	var parentID=records[0].get("uuid");
	grid.getStore().getProxy().extraParams={
		parentid:parentID
	};
	var pid=Ext.getCmp('menupid');
	pid.setValue(parentID);
	//获取父菜单名称
	Ext.Ajax.request({ 		
    	url: _ctxPath+"/control/menu/manage/getParentMenuName.do",
        params: {
        	uuid:parentID			
        },
        success : function(response, options){
        	var record=Ext.JSON.decode(response.responseText);
        	grid.setTitle('<a href=javascript:backTop() style="color:#FFF">导航</a>'+record.names+record.ownName);
        },
		failure : function(response, options){
			failAjaxTips();
		}
    });
	/*grid.getStore().load();*/
	grid.getStore().loadPage(1, null);//此处用Ext.data.Store.loadPage()方法默认加载第一页内容。若用load()，则点击父级菜单进入子菜单时，page默认不刷新，导致子菜单无法显示
};

var backParent=function(uuid){
	var grid = Ext.getCmp("menuManageView");
	grid.getStore().getProxy().extraParams={
		parentid:uuid
	};
	var pid=Ext.getCmp('menupid');
	pid.setValue(uuid);
	//获取父菜单名称
	Ext.Ajax.request({ 		
    	url: _ctxPath+"/control/menu/manage/getParentMenuName.do",
        params: {
        	uuid:uuid
        },
        success : function(response, options){
        	var record=Ext.JSON.decode(response.responseText);
        	grid.setTitle('<a href=javascript:backTop() style="color:#FFF">导航</a>'+record.names+record.ownName);
        },
		failure : function(response, options){
			failAjaxTips();
		}
    });
	grid.getStore().load();
};

//回到菜单顶端
var backTop=function(){
	var grid = Ext.getCmp("menuManageView");
	grid.getStore().getProxy().extraParams={
		parentid:null
	};
	var pid=Ext.getCmp('menupid');
	pid.setValue(null);
	grid.getStore().load();
	grid.setTitle('<a href=javascript:backTop() style="color:#FFF">导航</a>');
};
