Ext.define("PM.controller.model.modelManage",{
	extend : 'Ext.app.Controller',
	views:['PM.view.model.modelView','PM.view.model.modelContentView','PM.view.model.modelAdd','PM.view.model.modelEdit',
	       'PM.view.model.modelContentAdd','PM.view.model.modelContentEdit','PM.view.model.modelEditor'/*'PM.model.view.ExtKindEditor'*/],
	stores : 'model.modelStore',
	refs:[{
		ref : 'modelGrid',//itemId
		selector : 'modelView grid#modelGrid'
	}],
	init : function() {
		this.control({
			'modelView button[action="addModelBtn"]' : {
				// 新增模型
				click : this.addModel
			},
			'modelAdd button':{
				click:this.addModelOrClose
			},'modelView button[action="editModelBtn"]' : {
				// 编辑模型
				click : this.editModel
			},
			'modelView button[action="deleteModelBtn"]' : {
				// 删除模型
				click : this.deleteModel
			},
			'modelEdit button' : {
				// 更新模型
				click : this.editModelOrClose
			},
			'modelView grid#modelGrid' : {
				actioncolumnclick: this.changeStateOfModel,
				preview : this.preview
			},'modelContentView grid#modelContentGrid' : {
				actioncolumnclick: this.changeStateOfModel,
				preview : this.previewCon
			},
			'modelContentView button[action="addModelConBtn"]':{
				click:this.addConModel
			},
			'modelContentAdd button':{
				click:this.addModelConOrClose
			},
			'modelContentView button[action="editModelConBtn"]':{
				click:this.editConModel
			},
			'modelContentEdit button':{
				click:this.editModelConOrClose
			},
			'modelContentView button[action=deleteModelConBtn]':{
				click:this.deleteConModel
			},
			'modelContentEdit fileuploadfield[id=form_File]':{
				change:this.checkFile
			}
		});
	},
	// 新增模型
	addModel:function(){
		var modelAdd=Ext.widget("modelAdd");
		var s=Ext.ComponentQuery.query('modelView')[0];
		if(s.items.length>1){
			s.remove(s.items.last());
		}
		s.add(modelAdd);
	},
	//保存
	addModelOrClose:function(btn){
		var action = btn.action;
		var formpanel = Ext.ComponentQuery.query('modelAdd')[0];
		if(action=="saveModelAdd"){
			var basic = formpanel.getForm();//得到BasicForm
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		url:_ctxPath+'/control/addition/model/add.do',
			  		success: function (form, action) {
			  			if(action.result.status==true){
			  				Ext.Msg.alert('操作提示', action.result.msg);
			  				Ext.ComponentQuery.query('modelView grid')[0].getStore().load();
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
	editModel:function(){
		var grid = this.getModelGrid();
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			if(records[0].get("visible")==1){
				var modelEdit=Ext.widget("modelEdit");
				var s=Ext.ComponentQuery.query('modelView')[0];
				if(s.items.length>1){
					s.remove(s.items.last());
				}
				s.add(modelEdit);
				Ext.ComponentQuery.query('modelEdit')[0].getForm().setValues({
					uuid:records[0].get("uuid"),
					name:records[0].get('name'),
					remark:records[0].get('remarks'),
					orderNO:records[0].get('orderNO'),
				});
			}else{
				Ext.Msg.alert("提示","信息被锁定,请解锁后再操作");
			}
		}
	},
	editModelOrClose:function(btn){
		var action = btn.action;
		var formpanel = Ext.ComponentQuery.query('modelEdit')[0];
		if(action=="saveModelEdit"){
			var basic = formpanel.getForm();//得到BasicForm
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		url:_ctxPath+'/control/addition/model/edit.do',
			  		success: function (form, action) {
			  			if(action.result.status==true){
			  				Ext.Msg.alert('操作提示', action.result.msg);
			  				Ext.ComponentQuery.query('modelView grid')[0].getStore().load();
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
	deleteModel:function(){
		var modelGrid = this.getModelGrid();
		var records = modelGrid.getSelectionModel().getSelection();
		var store = modelGrid.getStore();
		if (records.length == 0) {
			Ext.Msg.alert("提示", "请选择一条记录进行操作！");
		}else{
			if(records[0].get("visible")==1){
				Ext.Msg.confirm('警告','确认要删除此记录',function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : _ctxPath+'/control/addition/model/delete.do',
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
			}else{
				Ext.Msg.alert("提示","信息被锁定,请解锁后再操作");
			}
		}
	
	},
	addConModel:function(){
		var modelAdd=Ext.widget("modelContentAdd");
		/*var modelContentGird = Ext.ComponentQuery.query("modelView grid#modelContentGrid")[0];
		var parentID = modelContentGird.getStore().getProxy().extraParams.parentID;*/
		var s=Ext.ComponentQuery.query('modelView')[0];
		if(s.items.length>1){
			s.remove(s.items.last());
		}
		s.add(modelAdd);
		/*Ext.ComponentQuery.query("modelContentAdd form#addModelForm textfield[name=parentID]")[0].setValue(parentID);*/	
		},
	addModelConOrClose:function(btn){
		var modelContentGird = Ext.ComponentQuery.query("modelView grid#modelContentGrid")[0];
		var parentID = modelContentGird.getStore().getProxy().extraParams.parentID;
		var action = btn.action;
		var formpanel = Ext.ComponentQuery.query('modelContentAdd')[0];
		if(action=="saveModelConAdd"){
			var basic = formpanel.getForm();//得到BasicForm
			if(basic.isValid()){
				basic.submit({
					waitMsg: '正在提交数据',
			  		waitTitle: '提示',
			  		method:'post',
			  		params: {  
			  			parentID:parentID
                    }, 
			  		url:_ctxPath+'/control/addition/model/addCon.do',
			  		success: function (form, action) {
			  			if(action.result.status==true){	
			  				Ext.Msg.alert('操作提示', action.result.weightMsg);
			  				Ext.ComponentQuery.query('modelContentView grid')[0].getStore().load();
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
		}else if(action=="cancelConSave"){
			formpanel.close();
		}
	
	},
	editConModel:function(){
		var grid = Ext.ComponentQuery.query("modelView grid#modelContentGrid")[0];
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			if(records[0].get("visible")==1){
				var modelEdit=Ext.widget("modelContentEdit");
				var s=Ext.ComponentQuery.query('modelContentView')[0];
				if(s.items.length>1){
					s.remove(s.items.last());
				}
				s.add(modelEdit);
				Ext.ComponentQuery.query('modelContentEdit')[0].getForm().setValues({
					uuid:records[0].get("uuid"),
					name:records[0].get('name'),
					remark:records[0].get('remarks'),
					orderNO:records[0].get('orderNO'),
				});
			}else{
				Ext.Msg.alert("提示","信息被锁定,请解锁后再操作");
			}
		}
	
	},
	editModelConOrClose:function(btn){
				var action = btn.action;
				var formpanel = Ext.ComponentQuery.query('modelContentEdit')[0];
				if(action=="saveModelConEdit"){
						var basic = formpanel.getForm();//得到BasicForm
						if(basic.isValid()){
							basic.submit({
								waitMsg: '正在提交数据',
								waitTitle: '提示',
								method:'post',
								url:_ctxPath+'/control/addition/model/editCon.do',
								success: function (form, action) {
									if(action.result.status==true){
										Ext.Msg.alert('操作提示', action.result.msg);
										Ext.ComponentQuery.query('modelContentView grid')[0].getStore().load();
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
	deleteConModel:function(){
		var modelContentGrid = Ext.ComponentQuery.query("modelView grid#modelContentGrid")[0];
		var records = modelContentGrid.getSelectionModel().getSelection();
		var store = modelContentGrid.getStore();
		if (records.length == 0) {
			Ext.Msg.alert("提示", "请选择一条记录进行操作！");
		}else{
			if(records[0].get("visible")==1){
				Ext.Msg.confirm('警告','确认要删除此记录,模型将被移除',function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : _ctxPath+'/control/addition/model/deleteCon.do',
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
			}else{Ext.Msg.alert("提示","信息被锁定,请解锁再操作");}
		}
	
	},
	checkFile:function(){
		var formFile = Ext.getCmp('form_File').getValue();
		var formpanel = Ext.ComponentQuery.query('modelContentEdit')[0];
		if(formFile!=''){
			Ext.Msg.confirm('请确认', '是否上传新模型，原模型将被移除?', function(button) {
				if (button !='yes') {
					formpanel.close();
				}
			});
		}
	},
	//更改状态
	changeStateOfModel : function(grid, rowIndex, colIndex){
		var me = this;
    	var rec = grid.getStore().getAt(rowIndex);
    	if(rec.get('visible')== 1){
        	Ext.Msg.confirm("请确认", "是否真的要锁定该项？", function(button, text){
                if (button == "yes") {
                    Ext.Ajax.request({
                    	waitMsg: '正在提交数据',
            	  		waitTitle: '提示',
                    	url: _ctxPath+"/control/addition/model/updateVisible.do",
                        params: {
                        	uuid:rec.get('uuid'),
        					visible:0
                        },
                        success : function(response, options){
                        	var record=Ext.JSON.decode(response.responseText);
							if(record.status==true){
	                        	grid.getStore().load();
	                        	Ext.MessageBox.alert("提示消息", "更改成功！");
                        	}
                        },
            			failure : function(response, options){
            				failAjaxTips();
            			}
                    });
                }
            });
        }else{
        	Ext.Msg.confirm("请确认", "是否真的要解锁该项？", function(button, text){
        		if (button == "yes") {
                	Ext.Ajax.request({
                    	waitMsg: '正在提交数据',
            	  		waitTitle: '提示',
                    	url: _ctxPath+"/control/addition/model/updateVisible.do",
                        params: {
                        	uuid:rec.get('uuid'),
        					visible:1
                        },
                        success : function(response, options){
                        	var record = Ext.JSON.decode(response.responseText);
                        	if(record.status==true){
	                        	grid.getStore().load();
	                        	Ext.MessageBox.alert("提示消息", "更改成功！");
                        	}
                        },
            			failure : function(response, options){
            				failAjaxTips();
            			}
                    });
                }
            });
        }
	},
	//模型下面的具体评分卡
	preview : function(grid, rowIndex, colIndex){
		var me = this;
    	var rec = grid.getStore().getAt(rowIndex);
    	if(rec.get("visible")==1){
    		var parentID =  rec.get('uuid');
    		var modelPanel = Ext.ComponentQuery.query("modelView")[0];
    		if(modelPanel.items.length>0){
    			modelPanel.remove(modelPanel.items.last());
    		}
    		var p = Ext.widget("modelContentView");
    		modelPanel.add(p);
    		Ext.ComponentQuery.query("modelContentView hidden[name=parentID]")[0].setValue(parentID);
    		var modelContentGird = Ext.ComponentQuery.query("modelView grid#modelContentGrid")[0];
    		modelContentGird.getStore().getProxy().extraParams={
    			parentID:parentID
    		};
    		modelContentGird.getStore().load();
    	}else{
    		Ext.Msg.alert("提示","信息被锁定,请解锁在操作");
    	}
	},
	previewCon : function(grid, rowIndex, colIndex){
		var me = this;
    	var rec = grid.getStore().getAt(rowIndex);
    	if(rec.get("visible")==1){
    		var p = Ext.widget("modelEditor");
    	/*	modelPanel.add(p);*/
    		/*var parentID =  rec.get('uuid');
    		var modelPanel = Ext.ComponentQuery.query("modelView")[0];
    		if(modelPanel.items.length>0){
    			modelPanel.remove(modelPanel.items.last());
    		}
    		var p = Ext.widget("modelContentView");
    		modelPanel.add(p);
    		Ext.ComponentQuery.query("modelContentView hidden[name=parentID]")[0].setValue(parentID);
    		var modelContentGird = Ext.ComponentQuery.query("modelView grid#modelContentGrid")[0];
    		modelContentGird.getStore().getProxy().extraParams={
    			parentID:parentID
    		};
    		modelContentGird.getStore().load();*/
    	}else{
    		Ext.Msg.alert("提示","信息被锁定,请解锁在操作");
    	}
	}
});
var backTopModel = function(){
	var modelPanel = Ext.ComponentQuery.query("modelView")[0];
	if(modelPanel.items.length>0){
		modelPanel.removeAll();
	}
	var p = Ext.widget("modelView");
	modelPanel.add(p);
	var modelGird = Ext.ComponentQuery.query("modelView grid#modelGrid")[0];
	modelGird.getStore().getProxy().extraParams={
		parentID:null
	};
	modelGird.getStore().load();
};
