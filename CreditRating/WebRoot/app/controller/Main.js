Ext.define('PM.controller.Main',{
    extend: 'Ext.app.Controller',
    views:['user.userEditPassword'],
    init : function(app){
    	var meMain = this;
    	this.control({
    		'trustHeader button[action=editPassword]' : {
    			click:this.editPassword
			},
			'editPassword button[action=updatePassword]' : {
    			click:this.updatePassword
			},
			'trustHeader menucheckitem' : {
				click:this.changeTheme
			},
			'trustHeader' : {
				afterrender:this.initOrg
			},
		});
    	failAjaxTips = function(){
    		Ext.MessageBox.show({
    	        title: "提示",
    	        msg: "系统出错，请与管理员联系！",
    	        buttons: Ext.Msg.OK,
    	        icon: Ext.MessageBox.ERROR
    	    });
    	};
    },
    editPassword :function(){
    	Ext.widget('editPassword');
		Ext.Ajax.request({
			url : _ctxPath+'/control/user/manage/editPWDUI.do',
			method : 'POST',
			success : function(response, opts) {
				var responseJSON = Ext.decode(response.responseText);
				var formpanel = Ext.ComponentQuery.query('editPassword form')[0];
				formpanel.getForm().setValues({
					userName:responseJSON.userName
				});
			}
		});
    },
    updatePassword :function(){
    	var formpanel = Ext.ComponentQuery.query('editPassword form')[0];
		var basicForm = formpanel.getForm();
		if (basicForm.isValid()) {
			basicForm.doAction('submit', {
				url : _ctxPath+'/control/user/manage/editPWD.do',
				method : 'post',
				success : function(form, action) {
					if (action.result.status == true) {
						alert("操作成功,请重新登录!");
						Ext.ComponentQuery.query('editPassword')[0].close();
						location.href=_ctxPath+'/user/logonUI.do';
					}else{
						Ext.Msg.alert('提示', action.result.msg);
					}
				},
				failure : function() {
					Ext.MessageBox.alert("提示消息", "操作失败！");
				}
			});
		}
    },
    closeOperaWin : function(){},
    changeTheme : function(obj,e,eOpts){
    	if(obj.text == "灰白色"){
    		Ext.util.CSS.swapStyleSheet('theme', '../../extjs/resources/css/ext-all-gray.css');
    	}else if(obj.text == "蓝色经典"){
    		Ext.util.CSS.swapStyleSheet('theme', '../../extjs/resources/css/ext-all-neptune.css');
    	}else if(obj.text == "浅蓝色"){
    		Ext.util.CSS.swapStyleSheet('theme', '../../extjs/resources/ext-theme-classic/ext-theme-classic-all.css');
    	}/*else if(obj.text == "纯黑"){
    		Ext.util.CSS.swapStyleSheet('theme', '../../extjs/resources/css/ext-all-access-debug.css');
    	}*/
    	var t = Ext.ComponentQuery.query("trustHeader box")[0];
    },
    initOrg : function(){
    	Ext.ComponentQuery.query("trustHeader box")[0].style='padding: 45px 20px 10px 31px;background-repeat: no-repeat ; background-image: url('+_ctxPath+'/Styles/DWZ/themes/default/images/logo.png) ';
    	Ext.ComponentQuery.query("trustHeader box")[0].updateBox(Ext.ComponentQuery.query("trustHeader box")[0]);
    },
    generateTab:function(controller,resource,selected, app, center) {
    	app.getController(controller);
    	// resource对应alias : 'widget.xxx'的xxx                                                                                                                                                                                                             
    	var panel = center.child(resource);
    	if (!panel) {
    		panel = Ext.widget(resource, {
    			title : selected,
    			closable : true
    		});
    		center.add(panel);
    	}
    	center.setActiveTab(panel);
    	return panel;
    }
});

