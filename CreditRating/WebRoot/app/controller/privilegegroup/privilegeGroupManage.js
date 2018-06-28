Ext.define('PM.controller.privilegegroup.privilegeGroupManage',{
	extend:'Ext.app.Controller',
	views : [ 'privilegegroup.privilegeGroupView','privilegegroup.privilegeGroupAdd','privilegegroup.privilegeGroupEdit'],
	stores : [ 'privilegegroup.privilegeGroupStore'],
	refs:[{
			ref : 'gridPanel',
		    selector : 'privilegeGroupView grid#gridPanel'
		},{
	       ref : 'panel',
	       selector : 'privilegeGroupAdd form#panel'
	    },{
	       ref : 'EditformPanel',
	       selector : 'privilegeGroupEdit form#EditformPanel'
	    	   }],
					           
	init: function() {//通过init函数来监听视图事件，控制视图与控制器的交互
		this.control(
		{	'privilegeGroupView  button[action=add]':{
			click:this.privilegeGroupAdd //添加角色按钮
			},
			'privilegeGroupAdd  button[action=submit]':{
				click:this.save //添加角色界面  保存
			},
			'privilegeGroupView  button[action=edit]':{
				click:this.privilegeGroupEdit //编辑角色信息
			},
			'privilegeGroupEdit  button[action=submit]':{
				click:this.submit  //更新角色信息
			},
			'privilegeGroupView  button[action=delete]':{
				click:this.deletePrivilegeGroup  //删除角色信息
			},
			'privilegeGroupView  button[action=assignPermissions]':{
				click:this.distributionMenuPrivilege //分配菜单权限
			},
			'privilegeGroupView  button[action=rolePreview]':{
				click:this.roleShow  //角色预览
			},
			'privilegeGroupView  button[action=query]':{
				click:this.queryPrivilegeGroup //查询
			},
		});
	},
	//创建添加角色面板
	privilegeGroupAdd:function(){
	   Ext.widget('privilegeGroupAdd');
   },
   //保存角色信息
   save:function(){
	   var formpanel=this.getPanel();
	   var grid=this.getGridPanel();
	   var store=grid.getStore();// 获得store对象
	   var basicForm = formpanel.getForm();
	   if(basicForm.isValid()){
		  basicForm.doAction('submit',{
           url:_ctxPath+'/control/privilegegroup/manage/add.do',//文件路径
           method:'post',
           success:function(form,action){
                  if (action.result.status == true) {
                     Ext.Msg.alert('提示窗口',action.result.msg);
                     store.load();
                     //Ext.ComponentQuery("privilegeGroupAdd").close();
                     Ext.getCmp('privilegeGroupAdd').close();
                  } else {
                       Ext.Msg.alert('提示窗口',action.result.msg);
                       Ext.getCmp('privilegeGroupAdd').close();
                  }
          },
          failure:function(){
              Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
              Ext.getCmp('privilegeGroupAdd').close();
		  }
		});
	  }else{
		   	Ext.Msg.alert('提示','请填入表单必填项！');
	   }
   },
   //删除角色信息
   deletePrivilegeGroup:function(){
	   var grid=this.getGridPanel();
	   var  store=grid.getStore();// 获得store对象
	   var selModel = grid.getSelectionModel();
	   var judgeSel = selModel.hasSelection();
	   var data=selModel.getSelection();
	   var id=data[0].get("uuid");
	   if(judgeSel){
	    Ext.Msg.confirm('警告','确认要删除此记录',function(btn){
		    if(btn=='yes'){
				Ext.Ajax.request({
					url :_ctxPath+'/control/privilegegroup/manage/delete.do', 
					params :{uuid:id},
					method : 'POST',  
					success : function(response, opts) {
					  var success = Ext.decode(response.responseText).success;
					   if (success) {  
					   		Ext.Array.each(data, function(record) {  
					   		store.remove(record);// 页面效果
					   			         });
					   	}else {  
					   		Ext.MessageBox.show({
					   			title : "提示",  
					   			msg : "数据删除失败!"
					   		});  
					   	}
					 }
				});
			}
	    });
		 }else{
			  Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
		  }
	   
   },
   //编辑角色信息
   privilegeGroupEdit:function(){
	   var grid=this.getGridPanel();
	   var selModel = grid.getSelectionModel();
	   var record = selModel.getSelection();
	   var judgeSel = selModel.hasSelection();
	   if(judgeSel){
		   var view=Ext.widget('privilegeGroupEdit');
			view.down('form').loadRecord(record[0]);
	   }
	   else{
			  Ext.Msg.alert("错误", "没有任何行被选中，无法进行编辑操作！");
		  }
   },
   //更新角色信息
   submit:function(){
	   var formPanel=this.getEditformPanel();
	   var grid=this.getGridPanel();
	   var store=grid.getStore();// 获得store对象
	   var basicForm = formPanel.getForm();
	   if(basicForm.isValid()){
		  basicForm.doAction('submit',{
           url:_ctxPath+'/control/privilegegroup/manage/edit.do',
           method:'post',
           success:function(form,action){
               if (action.result.status == true) {
                    Ext.Msg.alert('提示窗口',action.result.msg);
                    store.load();
                    Ext.getCmp('privilegeGroupEdit').close();
               } else {
                    Ext.Msg.alert('提示窗口',action.result.msg);
                    Ext.getCmp('privilegeGroupEdit').close();
                }
             },
          failure:function(){
                    Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
                    Ext.getCmp('privilegeGroupEdit').close();
           	}
	   	});   
	   	}else{
		   	 Ext.Msg.alert('提示','请填入表单必填项！');
	   }
   },
   
   queryPrivilegeGroup:function(){
	   var tf=Ext.getCmp('tf');
	   	var grid=this.getGridPanel();
		var store=grid.getStore();
		var name=tf.getValue();
		var query=false;
		if(name!=null||!name.equals("")){
			query=true;
			store.getProxy().extraParams={userName:name};
			store.load();
		}else{
			Ext.Msg.alert("提示","请输入关键字");
		}
	   
	   
	   
   },
   
   //分配菜单权限
   distributionMenuPrivilege:function(){
	    var grid=this.getGridPanel();
		var records = grid.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请选择一条记录进行操作');
		}else{
			var store=Ext.create("Ext.data.TreeStore",{
				proxy:{
					type:'ajax',
					url:_ctxPath+'/control/privilegegroup/manage/menuPrivilegeEditUI.do',
					reader:'json',
					extraParams : {
						uuid:records[0].get("uuid")
					},
					aotoLoad:true
				}
			});
			var menuTre=Ext.create("Ext.tree.Panel",{
				name:'privilegegroupEditAllMenuTree',
				id:'privilegegroupEditAllMenuTree',
				padding:18,
				border:false,
				margin:'14 14 14 30',
				useArrows : true,
				//border:false,
				rootVisible: false,
				width:520,
				lines :false,
				multiSelect:true,
				//height:450,
				store:store,
				listeners:{
					'itemcontextmenu':function(menutree,record,items,index,e){
						e.preventDefault();
						e.stopEvent();
					//判断是否为叶子结点
					if(record.data.leaf==false){
						var nodemenu = new Ext.menu.Menu({
						floating:true,
						items:[{
							text:'全选',
							handler:function(){
								for( var i =0;i<record.data.children.length;++i){
							//设置结点checked属性为true
								record.childNodes[i].set('checked',true);	
										}
									}
								},
						{
							text:'反选',
							handler:function(){
								for( var i =0;i<record.data.children.length;++i){
									if(record.childNodes[i].data.checked == false) {
								//设置结点checked属性为true
									record.childNodes[i].set('checked',true);
									}else {
								//设置结点checked属性为true
									record.childNodes[i].set('checked',false);	
											}	
										}
									}
								},{
									text:'撤销全部',
									handler:function(){
										for( var i =0;i<record.data.children.length;++i){
									//设置结点checked属性为false
											record.childNodes[i].set('checked',false);	
										}
									}
								}]
								
							});
							nodemenu.showAt(e.getXY());
							}
						}
					}
			});
		var win=Ext.create("Ext.window.Window",{
			id : 'privilegegroupMenuTreeWin',
			border : 0,
			width : 600,
			height : 550,
			autoScroll:true,
			title:'分配菜单权限',
			maximizable:true,
			frame: true,
			iconCls:'Applicationviewlist',
			autoShow : true,
			items:menuTre,
			buttons:[{
				text:'提交',
				handler:function(){
					var selectId=[];
					var tree=Ext.getCmp("privilegegroupEditAllMenuTree");
					var treeRecord=tree.getChecked();
					if(treeRecord.length==0){
						selectId=[];
					}else{
						for(var i=0;i<treeRecord.length;i++){
							selectId[i]=treeRecord[i].get("id");
						}
					}
						Ext.Ajax.request({
							url : _ctxPath+'/control/privilegegroup/manage/menuPrivilegeEdit.do',
							method : 'POST',
							params : {
								uuid :records[0].get("uuid"),
								privilegesStr:selectId
							},
							success : function(response, option) {
									Ext.MessageBox.alert("提示消息", "操作成功！");
							},
							failure : function() {
								Ext.MessageBox.alert("提示消息", "操作失败！");
							}
						});
						Ext.getCmp("privilegegroupMenuTreeWin").close();
				}
			},{
				text:'关闭',
				handler:function(){
					Ext.getCmp("privilegegroupMenuTreeWin").close();
				}
			}]
			});
		}
	   
   },
   //角色预览
   roleShow:function(){
	   var privilegeGroupList=Ext.widget('privilegeGroupList');
	   var privilegeGroupViewPanel=Ext.getCmp('privilegeGroupView');
	   var tab=privilegeGroupViewPanel.up('tabpanel');
	   tab.add(privilegeGroupList);
	   tab.setActiveTab('privilegeGroupList');
   
   },
});




