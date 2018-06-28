Ext.define('PM.controller.template.templateController',{
	extend:'Ext.app.Controller',
	views:['template.templateView','template.addTemplate','template.editTemplate'],
	stores:'template.templateStore',
	refs:[{ref : 'gridPanel',
	   	  selector : 'templateView grid#gridPanel'},
	   	  {ref : 'panel',
	   	  selector : 'addTemplate form#panel'},
	   	  {
	   		ref : 'EditformPanel',
	   		selector : 'editTemplate form#EditformPanel'}
	   	  ],
	init: function() {
		this.control({
			'templateView button[action=add]':{
				click:this.addwin
			},
			'addTemplate button[action=addsubmit]':{
				click:this.addsubmit
			},
			'templateView button[action=delete]':{
				click:this.doDelete
			},
			'templateView button[action=edit]':{
				click:this.editwin
			},
			
			'templateView button[action=query]':{
				click:this.doquery
			},
			
			'editTemplate button[action=doEdit4]':{
				click:this.doEdit
			}
		});
	},
	//创建添加面板
	addwin:function(){
		var grid=this.getGridPanel();
		var store = grid.getStore();// 获得store对象
		var id ="";
		var selModel = grid.getSelectionModel();
		if(selModel){
			id= store.getProxy().extraParams.parentid;
			var win=Ext.widget('addTemplate');
			if(id==null){
					Ext.getCmp('categoryName').hide();
					Ext.getCmp('cateShow').hide();
					Ext.getCmp('addTemplate').setHeight(250);
					Ext.getCmp('addItemId').setHeight(155);
			}
			Ext.getCmp("parentID").setValue(id);
		}
	},
	//保存
	addsubmit:function(){
		var formpanel=this.getPanel();
		var grid=this.getGridPanel();
		var store = grid.getStore();// 获得store对象
		var basicForm = formpanel.getForm();
		var parentID = store.getProxy().extraParams.parentid;
		if(parentID==null){
			Ext.getCmp('categoryName').setValue('defaultCategory');
		}
		/*var value=Ext.getCmp('formFile').getValue();
		var reg=/\.([xX][mM][lL])$/;
		if(!reg.test(value)){
			Ext.Msg.alert('错误','请选择XML文件');
			return false;
		}*/
		basicForm.doAction('submit',{
			url : _ctxPath+'/control/addition/template/add.do',// 文件路径
	        method : 'post',// 提交方法post或get
	        //params :"",
			// 提交成功的回调函数
           success : function(form, action) {
        	   if (action.result.status == true) {
				     Ext.Msg.alert('提示窗口','新增成功');
				     basicForm.reset();
				     store.load();
				     Ext.getCmp("addTemplate").close();
               } else {
             	  	Ext.Msg.alert('提示窗口',action.result.msg);
               }
			}
		});
	},
	doquery:function(){
		var Name=Ext.getCmp('Name');
		var grid=this.getGridPanel();
		var store=grid.getStore();
		var name=Name.getValue();
		var query=false;
		if(name!=null||!name.equals("")){
			query=true;
			store.getProxy().extraParams={query:query,searchTitle:name};
			store.load();
		}else{
			Ext.Msg.alert("提示","请输入关键字");
		}
	},
	
	editwin:function(){
		var modelGrid = Ext.ComponentQuery.query('templateView grid#gridPanel')[0];
		var store = modelGrid.getStore();// 获得store对象
		var id = store.getProxy().extraParams.parentid;
		var records = modelGrid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert("错误", "没有任何行被选中，无法进行编辑操作！");
		}else{
			if(records[0].get("visible").split(" ")[0]=="关闭"){
				Ext.Msg.alert("提示","信息被锁定,请解锁再操作");
			}else{
				var view=Ext.widget('editTemplate');
				if(id==null){//控制编辑窗口大小及显示内容
					Ext.getCmp('categoryNameE').hide();
					Ext.getCmp('cateShowE').hide();
					Ext.getCmp('editReportTemplateWin').setHeight(250);
					Ext.getCmp('editItemId').setHeight(155);
				}
				var value=records[0].get('templateName');
				var arr=value.split(" ");
				view.down('form').loadRecord(records[0]);//获取编辑界面第一个组件（表单）并加载模型
				Ext.getCmp('templateNameE').setValue(arr[0]);//在加载完数据后设置模板名称（不然模板中会带有"有1子模板"字样）
			}
		}
	},
	doEdit:function(){
		
		var formPanel=this.getEditformPanel();
		var grid=this.getGridPanel();
		var store = grid.getStore();// 获得store对象
		var basicForm=	formPanel.getForm();
		var parentID = store.getProxy().extraParams.parentid;
		var uuid = Ext.ComponentQuery.query('templateView grid#gridPanel')[0].getSelectionModel().getSelection()[0].get('uuid');
		if(parentID==null){
			Ext.getCmp('categoryNameE').setValue('defaultCategory');
		}
		/*var value=Ext.getCmp('formFile').getValue();
		var reg=/\.([xX][mM][lL])$/;
		if(!reg.test(value)){
			Ext.Msg.alert('错误','请选择XML文件');
			return false;
		}*/
		basicForm.doAction('submit',{   
			url : _ctxPath+'/control/addition/template/edit.do',
			method : 'post',
			params:'uuid',
			success : function(form, action) {
					if (action.result.status == true){      
						Ext.Msg.alert('提示窗口','编辑成功');
						basicForm.reset();
						store.load();
						Ext.getCmp("editReportTemplateWin").close();
					}else{
						Ext.Msg.alert('提示窗口','编辑失败！,请重新再试');
					} 
			},
			// 提交失败的回调函数
			failure : function() {
				Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
			}
		});
	},
	doDelete:function(){
		var modelGrid = Ext.ComponentQuery.query('templateView grid#gridPanel')[0];
		var records = modelGrid.getSelectionModel().getSelection();
		var store = modelGrid.getStore();
		if (records.length == 0) {
			Ext.Msg.alert("提示", "请选择一条记录进行操作！");
		}else{
			if(records[0].get("visible").split(" ")[0]=="关闭"){
				Ext.Msg.alert("提示","信息被锁定,请解锁再操作");
			}else{
				Ext.Msg.confirm('警告','确认要删除此记录',function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : _ctxPath+'/control/addition/template/delete.do',
							params : {uuid :records[0].get("uuid")},
							method : 'POST',
							success : function(response, option) {
								var record=Ext.JSON.decode(response.responseText);
								if(record.status==true){
									store.remove(records[0]);
									store.load();
									records[0].commit();
									Ext.MessageBox.alert("提示消息", "删除成功！");
								}else{
									Ext.Msg.alert('提示',record.msg);
								}
							},
							failure : function() {
								Ext.MessageBox.alert("提示消息", "删除失败！");
							}
						});	
					}
				});
			}
		}
	}
});

var addValue=function(tree,record,item, index,e,eOpts){
	var id=record.get('id');
    var name=record.get('text');
    Ext.getCmp("categoryName").setValue(name);
    Ext.getCmp("parentID").setValue(id);
};
var addValue2=function(tree,record,item, index,e,eOpts){
	var id=record.get('id');
    var name=record.get('text');
    Ext.getCmp("categoryNameE").setValue(name);
    Ext.getCmp("parentIDE").setValue(id);
};
var dateformat=function(value){
	 return Ext.Date.format(new Date(value),'Y年-m月-d日');
};
//渲染模板名称可点击进入下一级
var nextDir = function(value,field){//value为当前菜单名
	var url="javascript:getChildren();";
	var arr=value.split(" ");
	if(arr[1]==null){
		return arr[0];
	}else if(arr[1]==0){
		return "<a href="+url+" style='text-decoration:none;'>"+arr[0]+"</a>";
	}else{
	var color="red";
	return "<a href="+url+" style='text-decoration:none;'>"+arr[0]+"</a>"+
				"<font color="+color+">"+arr[1]+"</font>";
	}
};
//获得下一级内容
var getChildren = function(){
	var grid = Ext.getCmp("gridPanel");
	var records = grid.getSelectionModel().getSelection();
	var parentID=records[0].get("uuid");
	grid.getStore().getProxy().extraParams={
		parentid:parentID
	};
	var pid=Ext.getCmp('templatepid');
	pid.setValue(parentID);
	//获取父菜单名称
	Ext.Ajax.request({ 		
    	url: _ctxPath+"/control/addition/template/getParentName.do",
        params: {
        	uuid:parentID,
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
	var grid = Ext.getCmp("gridPanel");
	grid.getStore().getProxy().extraParams={
		parentid:uuid
	};
	var pid=Ext.getCmp('templatepid');
	pid.setValue(parentID);
	//获取父菜单名称
	Ext.Ajax.request({ 		
    	url: _ctxPath+"/control/addition/template/getParentName.do",
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
	var grid = Ext.getCmp("gridPanel");
	grid.getStore().getProxy().extraParams={
		parentid:null
	};
	var pid=Ext.getCmp('templatepid');
	pid.setValue(null);
	grid.getStore().load();
	grid.setTitle('<a href=javascript:backTop() style="color:#FFF">导航</a>');
};
var change2Link=function(value){
	var url="javascript:changeStatus()";
	var arr=value.split(" ");
	var color="red";
	var tColor="green";
	if (arr[0] == "正常") {
		return "<font color=" + tColor + ">" + arr[0] + "</font>" + " | "
				+ "<a href=" + url + "><font color=" + color + ">" + arr[1]
				+ "</font>" + "</a>";
	}
	return "<font color=" + color + ">" + arr[0] + "</font>" + " | "
			+ "<a href=" + url + "><font color=" + color + ">" + arr[1]
			+ "</font>" + "</a>";
};
var changeStatus=function(){
	var surl=_ctxPath+"/control/addition/template/manage_updateVisible.do";
	var visible=0;
	var panel=Ext.getCmp("reportTemplateView");
	var grid=panel.getComponent("gridPanel");
	var store=grid.getStore();
	var records = grid.getSelectionModel().getSelection();
	var id=records[0].get("uuid");
	var status=records[0].get("status");
	if(status.split(" ")[0]=="关闭")
		visible=1;
	Ext.Msg.confirm('请确认', '是否真的要更改状态?', function(button) {
		if (button =='yes') {
			Ext.Ajax.request({
				url : surl,
				method : 'POST',
				params : {
					visible:visible,
					uuid:id
				},
				success : function(response, option) {
						store.load(); 
						Ext.MessageBox.alert("提示消息", "更改成功！");
				},
				failure : function() {
					Ext.MessageBox.alert("提示消息", "更改失败！");
				}
			});
		}
	});
};
var insertData=function(){
	var grid=Ext.getCmp('grid');
	//store = grid.getStore();// 获得store对象
	var selModel = grid.getSelectionModel();
	var record = selModel.getSelection();
	var textarea=Ext.getCmp('textarea');
	var oldFieldName=textarea.getValue();
	var fieldName=record[0].get('fieldName');
	if(oldFieldName){
		textarea.setValue(oldFieldName+fieldName);
	}else{
		textarea.setValue(fieldName); 
	}
	
};