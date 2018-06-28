Ext.define("PM.view.user.userPrivilegegroup",{
		extend:'Ext.Window',
		alias : 'widget.privilegegroupList',
		layout : 'fit',
		border : 0,
		width : 450,
		maximizable:true,
		height : 400,
		title : '分配角色',
		modal : true,
		frame : true,
		iconCls : 'Applicationviewlist',
		autoShow : true,
		initComponent : function() {
			Ext.apply(this,{
				items:[{
					xtype:'form',
					bodyStyle:'padding:30px;',
					autoScroll : true,
					items:[{
						 xtype: 'checkboxgroup', 
						 itemId:'cbg',
						 padding:'5 5 5 5',
						 fieldLabel: '<font style="font-family: Microsoft YaHei;font-size:14px">权限组</font>',
						 vertical: true,
						 columns:1,
						 allowBlank :true,
						 items:[],
						 listeners:{
							  render:function(view, opt){ 
								   var cbg=this;
								   var gridpanel=Ext.ComponentQuery.query('userManageView grid')[0];;
								   var store=gridpanel.getStore();
								   var records = gridpanel.getSelectionModel().getSelection();
								   var userName=records[0].get("username");
								   var groupsCheckBoxs=new Array();
								   Ext.Ajax.request({ 
									   async:false,
									   url:_ctxPath+'/control/user/manage/getRoles.do',
									   params : {
										   userName:userName
									   },
									   method:'post',
									   success: function(response){  
										  var groups=Ext.decode(response.responseText);
										  for(var i=0;i<groups.length;i++){
											  cbg.items.add( new Ext.form.Checkbox({ 
												  checked:groups[i].checked,
												  boxLabel:'<img src="'+_ctxPath+groups[i].imgurl+'" align="AbsBottom" width="18" height="18">'+groups[i].name,
												  inputValue: groups[i].uuid,
											  }));
										  }
										 }
								   });
							  }
						 }
					}]
				}],
				buttons:[{
					text:'确定',
					action:'givePrivilege'
				},{
					text:'取消',
					scope:this,
					handler:this.close
				}]
			});
			this.callParent(arguments);
			
		}
});