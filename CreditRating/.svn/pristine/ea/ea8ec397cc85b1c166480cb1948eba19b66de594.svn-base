//重要提示：设置OrganizationManageView界面的id为“pid”的表单不要忘记

Ext.define('PM.controller.organization.organizationManage',{ 
	  extend : 'Ext.app.Controller',
	  stores: [ 'organization.organizationStore'],
	  views : [ 'organization.organizationManageView','organization.organizationAdd','organization.organizationEdit'],
	    		refs:[{
	    			ref:'organizationManageView',
	    			selector:'organizationManageView grid#organizationManageView'
	    		},{
	    			ref:'addOrganizationForm',
	    			selector:'organizationAdd form#addOrganizationForm'
	    		},{
	    			ref:'editOrganizationForm',
	    			selector:'organizationEdit form#editOrganizationForm'
	    		}],
	 init : function() {
		// 通过init函数来监听视图事件，控制视图与控制器的交互
		 this.control({
	         'organizationManageView button[action=addOrgGroup]' : {
				 // 新增
				 click : this.addOrg
			 },
	         'organizationManageView  button[action=deleteOrgGroup]' : {
				 // 删除
				 click : this.deleteOrganization
			 },
		  	 'organizationAdd button[action=saveOrg]' : {
		  		 //保存
				 click: this.saveOrg
		  		  },
		     'organizationManageView button[action=editOrgGroup]' : {
		         //编辑
				 click : this.editOrganization
		     },
			 'organizationEdit button[action=updateOrg]' : {
				 click: this.updateOrg//更新
			 },
		  	 'organizationManageView grid button[id=organizationQuery]' : {
				 click: this.organizationQuery//查询
				 
		  	 },
			  	 
		 });
	 },

	/*
	 *@Description: 增加机构或机构下某个人员
	 */
	addOrg:function(){
		Ext.widget('organizationAdd');
	},
	saveOrg:function(btn){
		var grid = this.getOrganizationManageView();
		var store = grid.getStore();
		var form = this.getAddOrganizationForm();
		var basicForm = form.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				// 文件路径
				url : _ctxPath+'/control/organization/manage/addOrganization.do',
				// 提交方法post或get
				method : 'post',
				params : '',
				// 提交成功的回调函数
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						basicForm.reset();
						store.load();
						Ext.getCmp("organizationAdd").close();
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
	//删除
	deleteOrganization : function() {
		var gridpanel =Ext.ComponentQuery.query('organizationManageView grid')[0];
		var store = gridpanel.getStore();// 获得store对象
		var selModel = gridpanel.getSelectionModel();
		var judgeSel = selModel.hasSelection();
		var data = selModel.getSelection();
		if (data.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
			return;
		}
		var type = data[0].get("type");
		var id = data[0].get("uuid");
		if (judgeSel) {
			Ext.Msg.confirm('警告','确认要删除此记录',function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : _ctxPath+'/control/organization/manage/deleteOrganization.do',
						params : 
						{
							uuid : id,
							type : type
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
	organizationQuery:function(btn){
		var grid = Ext.ComponentQuery.query('organizationManageView grid')[0];
		var orgName=Ext.getCmp('orgName');
		name=orgName.getValue();
		grid.getStore().getProxy().extraParams = {
			 name : name,
		};
		grid.getStore().load();
	},

	/*
	 *@Description: 编辑机构
	 */
	editOrganization:function(){
		var grid = this.getOrganizationManageView();
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			var win =Ext.widget('organizationEdit');
			var form = this.getEditOrganizationForm();
			var basicForm = form.getForm();
			basicForm.loadRecord(records[0]);
		}
	},

	//更新机构信息
	updateOrg:function(){
		var grid = this.getOrganizationManageView();
		var store = grid.getStore();
		var form = this.getEditOrganizationForm();
		var basicForm = form.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				// 文件路径
				url : _ctxPath+'/control/organization/manage/editOrganization.do',
				// 提交方法post或get
				method : 'post',
				params : '',
				// 提交成功的回调函数
				success : function(form, action) {
					if (action.result.status == true) {
						Ext.Msg.alert('提示', action.result.msg);
						store.load();
						Ext.getCmp("organizationEdit").close();
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
//更改机构状态
var changeStatus=function(){
	var surl=_ctxPath+"/control/organization/manage/updateVisible.do";
	var visible=0;
	var grid = Ext.getCmp("organizationManageView");
	var store=grid.getStore();
	var records = grid.getSelectionModel().getSelection();
	var uuid = records[0].get("uuid");
	var type = records[0].get("type");
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
					visible:visible,
					type:type
				},
				success : function(form, action) {
						store.load(); 
						records[0].commit();
						if (option.result.status == true) {
							Ext.Msg.alert("提示消息", option.result.msg);
						} else {
							Ext.Msg.alert('提示', option.result.msg);
						}
				},
				failure : function() {
					Ext.MessageBox.alert("提示消息", "更改失败！");
				}
			});
		}
	});
};


//回到菜单顶端
var backTop=function(){
	var grid = Ext.getCmp("organizationManageView");
	grid.getStore().getProxy().extraParams={
		parentID:null
	};
	var pid=Ext.getCmp('orgpid');
	pid.setValue(null);
	grid.getStore().load();
	grid.setTitle('<a href=javascript:backTop() style="color:#FFF">导航</a>');
};