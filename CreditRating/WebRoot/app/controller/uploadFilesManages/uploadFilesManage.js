Ext.define("PM.controller.uploadFilesManages.uploadFilesManage",{
	extend : 'Ext.app.Controller',
	views:['uploadFilesManages.uploadFilesView','uploadFilesManages.uploadFilesAdd','uploadFilesManages.uploadFilesEdit'],
	stores : ['uploadFilesManages.uploadFilesStore'],
	init : function() {
		this.control({
			'uploadFilesView grid button[action=uploadFilesAdd]':{
				click:this.uploadFilesAdd
			},
			'uploadFilesView grid button[action=uploadFilesDelete]':{
				click:this.uploadFilesDelete
			},
			'uploadFilesView grid button[action=uploadFilesEdit]':{
				click:this.uploadFilesEdit
			},
			'uploadFilesAdd button':{
				click:this.saveUploadFilesAddOrClose
			},
			'uploadFilesEdit button':{
				click:this.saveUploadFilesEdit
			}
		});
	},
	//新增
	uploadFilesAdd:function(){
		var uploadFilesAdd=Ext.widget("uploadFilesAdd");
		var s=Ext.ComponentQuery.query('uploadFilesView')[0];
		if(s.items.length>1){
			s.remove(s.items.last());
		}
		s.add(uploadFilesAdd);
	},
	//保存
	saveUploadFilesAddOrClose:function(btn){
		var action=btn.action;
		var formpanel = Ext.ComponentQuery.query('uploadFilesAdd')[0];
		if(action=='saveUploadFilesAdd'){
			var basic = formpanel.getForm();//得到BasicForm
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		url:_ctxPath+'/control/addition/uploadFiles/add.do',
			  		success: function (form, action) {
			  			if(action.result.status==true){
			  				Ext.Msg.alert('操作提示', action.result.msg);
			  				Ext.ComponentQuery.query('uploadFilesView grid')[0].getStore().load();
			  				formpanel.close();
			  			}else{
			  				Ext.Msg.alert('操作提示', action.result.msg);
			  			}
			  	    },
			  	    failure: function(form,action){
			  	    	Ext.Msg.alert('操作提示', "数据保存失败,请重新尝试");
			  	    }
				});
			}
		}else{
			formpanel.close();
		 }
	},
	//编辑
	uploadFilesEdit:function(){
		var s=Ext.ComponentQuery.query('uploadFilesView')[0];
		if(s.items.length>1){
			s.items.last().close();
		}
		var grid = Ext.ComponentQuery.query('uploadFilesView grid#uploadFilesGrid')[0];
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		}else {
			var uploadFilesEdit=Ext.widget("uploadFilesEdit");
			s.add(uploadFilesEdit);
			var p=Ext.ComponentQuery.query('uploadFilesEdit')[0];
			var radio=Ext.ComponentQuery.query('uploadFilesEdit radiogroup#isMust radiofield');
			var radio2=Ext.ComponentQuery.query('uploadFilesEdit radiogroup#fileType radiofield');
			var radio3=Ext.ComponentQuery.query('uploadFilesEdit radiogroup#isEnt radiofield');
			p.getForm().setValues({
				uuid:records[0].get("uuid"),
				name:records[0].get('name'),
				remark:records[0].get('remark'),
				isMust:records[0].get('isMust')=='1'?radio[0].setValue(true):radio[1].setValue(true),
				type:records[0].get('type')=='0'?radio2[0].setValue(true):radio2[1].setValue(true),
				isEnt:records[0].get('isEnt')=='0'?radio3[0].setValue(true):radio3[1].setValue(true)
	    	});
		}
	},
	//更新
	saveUploadFilesEdit:function(btn){
		var action=btn.action;
		var formpanel = Ext.ComponentQuery.query('uploadFilesEdit')[0];
		if(action=='saveUploadFilesEdit'){
			var basic = formpanel.getForm();//得到BasicForm
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		url:_ctxPath+'/control/addition/uploadFiles/update.do',
			  		success: function (form, action) {
			  			if(action.result.status==true){
			  				Ext.Msg.alert('操作提示', action.result.msg);
			  				Ext.ComponentQuery.query('uploadFilesView grid')[0].getStore().load();
			  				formpanel.close();
			  			}else{
			  				Ext.Msg.alert('操作提示', action.result.msg);
			  			}
			  	    },
			  	    failure: function(form,action){
			  	    	Ext.Msg.alert('操作提示', "数据保存失败,请重新尝试");
			  	    }
				});
			}
		}else{
			formpanel.close();
		}
	},
	//删除
	uploadFilesDelete:function(){
		var grid = Ext.ComponentQuery.query('uploadFilesView grid')[0];
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		}else {
			var s=Ext.ComponentQuery.query('uploadFilesView')[0];
			if(s.items.length>1){
				s.items.last().close();
			}
			Ext.Msg.confirm("请确认", "是否真的要删除该项？", function(button, text){
				 if (button == "yes"){
					 Ext.Ajax.request({
		    				url: _ctxPath+'/control/addition/uploadFiles/delete.do',
		    				method: 'POST',
		    				params: {
		    					uuid :records[0].get("uuid")
		    					},
		    				success: function(response){
		    					var resptxt=Ext.decode(response.responseText);
		    					if(resptxt.status==true){
		    						grid.getStore().remove(records[0]);
		    						records[0].commit();
		    						grid.getStore().load(records);
		    						Ext.Msg.alert('操作提示', resptxt.msg);
		    					}else{
		    						Ext.Msg.alert('操作提示', '删除失败');
		    					}
		    				},
			    			failure: function(form, action) {
			    				Ext.Msg.alert('操作提示', '删除失败');
			    			}
					 }); 
				 }
			});
		}
	}
});