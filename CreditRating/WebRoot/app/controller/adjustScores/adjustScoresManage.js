Ext.define("PM.controller.adjustScores.adjustScoresManage", {
	extend : 'Ext.app.Controller',
	views : [ 'adjustScores.adjustScoresView','adjustScores.adjustScoresAdd' ,'adjustScores.adjustScoresEdit' ],
	stores : [ 'adjustScores.adjustStore' ],
	init : function() {
		this.control({
			'adjustScoresView button[action="addScore"]' : {
				click : this.addScore
			},
			'adjustScoresView button[action="editScore"]' : {
				click : this.editScore
			},
			'adjustScoresView button[action="deleteScore"]' : {
				click : this.deleteScore
			},
			'adjustScoresAdd button[action="saveadd"]' : {
				click : this.saveadd
			},
			'adjustScoresAdd button[action="canceladd"]' : {
				click : this.canceladd
			},
			'adjustScoresEdit button[action="saveedit"]' : {
				click : this.saveedit
			},
			'adjustScoresEdit button[action="canceledit"]' : {
				click : this.canceledit
			}
		});
	},
	addScore : function(addScore) {
		var addScoreManager=Ext.widget("adjustScoresAdd");
		var s=Ext.ComponentQuery.query('adjustScoresView')[0];
		if(s.items.length>1){
			s.items.last().close();
		}
		s.add(addScoreManager);
	},
	editScore : function(editScore) {
		//1.获取grid
		//2.获取records
		//3.如果记录长度为0，提示 请选择一条记录进行操作
		//4.else 获取编辑界面组件
		//5、获取adjustScoresView，不让其展示多个窗口（只展示一个），并将编辑界面组件嵌入到视图里
		//6、获取视图表单并设值
		var grid = Ext.ComponentQuery.query('adjustScoresView grid#scoreManagerInfo')[0];
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			var editScoreManager=Ext.widget("adjustScoresEdit");
			var s=Ext.ComponentQuery.query('adjustScoresView')[0];
			if(s.items.length>1){
				s.items.last().close();
			}
			s.add(editScoreManager);
			var p=Ext.ComponentQuery.query('adjustScoresView form#editScoreManagerForm')[0];
			p.getForm().setValues({
				uuid:records[0].get("uuid"),
				reasonName:records[0].get('name'),
				reasonValue:records[0].get('value'),
	    	});
		}
	},
	deleteScore : function(deleteScore) {
		var grid = Ext.ComponentQuery.query('adjustScoresView grid#scoreManagerInfo')[0];
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		} else {
			var s=Ext.ComponentQuery.query('adjustScoresView')[0];
			if(s.items.length>1){
				s.items.last().close();
			}
    		//根据选中的model得到相应的id，查出相应的记录
			var uuid = records[0].get("uuid");
			Ext.Msg.confirm("请确认", "是否真的要删除该项？", function(button, text){
				 if (button == "yes"){
					 Ext.Ajax.request({
		    				url: _ctxPath+'/control/addition/adjustScores/delete.do',
		    				method: 'POST',
		    				params: {uuid : uuid},
		    				success: function(response){
		    					Ext.Msg.alert('操作提示', "数据删除成功");
		    					records[0].commit;
		    					Ext.ComponentQuery.query('adjustScoresView grid')[0].getStore().load();
		    				},
			    			failure: function(form, action) {
			    				Ext.Msg.alert('操作提示', '删除失败');
			    			}
					 }); 
				 }
			});
    	}
	},
	saveadd:function(){
		//提交新增
		var form = Ext.ComponentQuery.query('adjustScoresView form#addScoreManagerForm')[0];
		var basic = form.getForm();//得到BasicForm
		if(basic.isValid()){
			basic.submit({
				waitMsg: '正在提交数据',
		  		waitTitle: '提示',
		  		method:'post',
		  		url:_ctxPath+'/control/addition/adjustScores/add.do',
		  		success: function (form, action) {
		  			Ext.Msg.alert('操作提示', "数据保存成功");
		  			Ext.ComponentQuery.query('adjustScoresView grid')[0].getStore().load();
		  			var addScoreManager=Ext.widget("adjustScoresAdd");
		  			Ext.ComponentQuery.query('adjustScoresView')[0].remove(addScoreManager);
		  	    },
		  	    failure: function(form,action){
		  	    	Ext.Msg.alert('操作提示', "数据保存失败,请重新尝试");
		  	    }
			});
		}
	},
	canceladd:function(canceladd){
		var addScoreManager=Ext.widget("adjustScoresAdd");
		Ext.ComponentQuery.query('adjustScoresView')[0].remove(addScoreManager);
	},
	saveedit : function(saveedit) {
		// 提交编辑
		var form = Ext.ComponentQuery.query('adjustScoresView form#editScoreManagerForm')[0];
		var basic = form.getForm();// 得到BasicForm
		if (basic.isValid()) {
			basic.submit({
			waitMsg : '正在提交数据',
			waitTitle : '提示',
			method : 'post',
			url : _ctxPath + '/control/addition/adjustScores/update.do',
			success : function(form, action) {
				Ext.Msg.alert('操作提示', "数据编辑成功");
				Ext.ComponentQuery.query('adjustScoresView grid')[0].getStore().load();
				var editScoreManager = Ext.widget("adjustScoresEdit");
				Ext.ComponentQuery.query('adjustScoresView')[0].remove(editScoreManager);
			},
			failure : function(form, action) {
				Ext.Msg.alert('操作提示',"数据保存失败,请重新尝试");
			}
		});
	}
	},
	canceledit:function(canceledit){
		var editScoreManager=Ext.widget("adjustScoresEdit");
		Ext.ComponentQuery.query('adjustScoresView')[0].remove(editScoreManager);
	}
});