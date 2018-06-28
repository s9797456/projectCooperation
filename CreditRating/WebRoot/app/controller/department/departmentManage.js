//重要提示：设置DepartmentManageView界面的id为“pid”的表单不要忘记

Ext.define('PM.controller.department.departmentManage',{ 
	  extend : 'Ext.app.Controller',
	  stores: [ 'department.departmentStore'],
	  views : [ 'department.departmentManageView','department.departmentAdd',
	            'department.departmentEdit'],
	    		refs:[{
	    			ref:'departmentManageView',
	    			selector:'departmentManageView grid#departmentManageView'
	    		},{
	    			ref:'addDepartmentForm',
	    			selector:'departmentAdd form#addDepartmentForm'
	    		},{
	    			ref:'editDepartmentForm',
	    			selector:'departmentEdit form#editDepartmentForm'
	    		}],
	 init : function() {
		// 通过init函数来监听视图事件，控制视图与控制器的交互
		 this.control({
	         'departmentManageView button[action=addDepGroup]' : {
				 // 新增
				 click : this.addDep
			 },
	         'departmentManageView  button[action=deleteDepGroup]' : {
				 // 删除
				 click : this.deleteDepartment
			 },
		  	 'departmentAdd button[action=saveDep]' : {
		  		 //保存
				 click: this.saveDep
		  		  },
		     'departmentManageView button[action=editDepGroup]' : {
		         //编辑
				 click : this.editDepartment
		     },
			 'departmentEdit button[action=updateDep]' : {
				 click: this.updateDep//更新
			 },
		  	 'departmentManageView grid button[id=departmentQuery]' : {
				 click: this.departmentQuery//查询
				 
		  	 },
			  	 
		 });
	 },

	/*
	 *@Description: 增加部门或部门下某个人员
	 */
	addDep:function(){
		var grid=this.getDepartmentManageView();
		var store = grid.getStore();// 获得store对象
		var id ="";
		var selModel = grid.getSelectionModel();
		if(selModel){
				id= store.getProxy().extraParams.parentid;}
		Ext.Ajax.request({
			url : _ctxPath+'/control/department/manage/addDepartmentUI.do',
			method : 'post',
			params : {
				parentID:id
			},
			success : function(response, option) {
				var record=Ext.JSON.decode(response.responseText);
				if(record.status==true){
					var win=Ext.widget('departmentAdd');
					Ext.getCmp("parentID").setValue(record.parentID);
				}else{
					Ext.Msg.alert('提示', record.msg);
				}
		},
			failure : function() {
		}
		});
	},
	saveDep:function(btn){
		var grid = this.getDepartmentManageView();
		var store = grid.getStore();
		var form = this.getAddDepartmentForm();
		var basicForm = form.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				// 文件路径
				url : _ctxPath+'/control/department/manage/addDepartment.do',
				// 提交方法post或get
				method : 'post',
				params : '',
				// 提交成功的回调函数
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						basicForm.reset();
						store.load();
						Ext.getCmp("departmentAdd").close();
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
	
	deleteDepartment : function() {
		var gridpanel =Ext.ComponentQuery.query('departmentManageView grid')[0];
		var store = gridpanel.getStore();// 获得store对象
		var selModel = gridpanel.getSelectionModel();
		var judgeSel = selModel.hasSelection();
		var data = selModel.getSelection();
		if (data.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
			return;
		}
		var id = data[0].get("uuid");
		if (judgeSel) {
			Ext.Msg.confirm('警告','确认要删除此记录',function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : _ctxPath+'/control/department/manage/deleteDepartment.do',
						params : 
						{
							uuid : id
						},
						method : 'POST',
						success : function(response,opts) {
							var success = Ext.decode(response.responseText).success;
							var msg = Ext.decode(response.responseText).msg;
							if (success) {
							   Ext.Array.each(data,function(record) {
							   store.remove(record);
							   store.load();// 页面效果
							   Ext.MessageBox.alert("提示", msg);	
							   });
						} else {
								Ext.MessageBox.alert("提示", msg);	
								}
							 }
						 });
					  }
				  });
		  } else {
				Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
			}

		},

	//查询
	departmentQuery:function(btn){
		var grid = Ext.ComponentQuery.query('departmentManageView grid')[0];

		var depName=Ext.getCmp('depName');
		var pid=Ext.getCmp('pid');
		name=depName.getValue();
		parentId=pid.getValue();
		grid.getStore().getProxy().extraParams = {
			 name : name,
			 parentid:parentId
		};
		
		grid.getStore().load();
	},

	/*
	 *@Description: 编辑部门
	 */
	editDepartment:function(){
		var grid = this.getDepartmentManageView();
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			var win =Ext.widget('departmentEdit');
			var value=records[0].get('name');
			var arr=value.split(" ");
			var form = this.getEditDepartmentForm();
			var basicForm = form.getForm();
			basicForm.loadRecord(records[0]);
			Ext.getCmp('name').setValue(arr[0]);
		}
	},

	//更新部门信息
	updateDep:function(){
		var grid = this.getDepartmentManageView();
		var store = grid.getStore();
		var form = this.getEditDepartmentForm();
		var basicForm = form.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				// 文件路径
				url : _ctxPath+'/control/department/manage/editDepartment.do',
				// 提交方法post或get
				method : 'post',
				params : '',
				// 提交成功的回调函数
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						store.load();
						Ext.getCmp("departmentEdit").close();
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
//将状态根据需要变成超链接
var changeStatus2Link=function(value){
	var url="javascript:changeStatus()";
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
//将部门名变成超链接
var change2Link=function(value,field){
	var url="javascript:getChildrenDepartment();";
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
//更改部门状态
var changeStatus=function(){
	var surl=_ctxPath+"/control/department/manage/updateVisible.do";
	var visible=0;
	var grid = Ext.getCmp("departmentManageView");
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
//获得子部门的基本信息
var getChildrenDepartment=function(){
	var grid = Ext.getCmp("departmentManageView");
	var records = grid.getSelectionModel().getSelection();
	var parentID=records[0].get("uuid");
	grid.getStore().getProxy().extraParams={
		parentid:parentID,
	};
	
	var pid=Ext.getCmp('deppid');
	
	pid.setValue(parentID);

	//获取父部门名称
	Ext.Ajax.request({ 		
    	url: _ctxPath+"/control/department/manage/getParentDepartmentName.do",
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
	grid.getStore().loadPage(1, null);//此处用Ext.data.Store.loadPage()方法默认加载第一页内容。若用load()方法，则点击父级菜单进入子菜单时，page默认不刷新，导致子菜单无法显示
};

var backParent=function(uuid){
	var grid = Ext.getCmp("departmentManageView");
	grid.getStore().getProxy().extraParams={
		parentid:uuid
	};
	var pid=Ext.getCmp('deppid');
	pid.setValue(uuid);
	//获取父部门名称
	Ext.Ajax.request({ 		
    	url: _ctxPath+"/control/department/manage/getParentDepartmentName.do",
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
	var grid = Ext.getCmp("departmentManageView");
	grid.getStore().getProxy().extraParams={
		parentID:null
	};
	var pid=Ext.getCmp('deppid');
	pid.setValue(null);
	grid.getStore().load();
	grid.setTitle('<a href=javascript:backTop() style="color:#FFF">导航</a>');
};