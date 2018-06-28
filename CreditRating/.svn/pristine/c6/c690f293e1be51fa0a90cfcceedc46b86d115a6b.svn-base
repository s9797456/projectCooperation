Ext.define("PM.view.user.userEdit",{
	extend : 'Ext.window.Window',
	alias:"widget.userEdit",
	layout : 'anchor',	
	border : 0,
	width : 600,
	height : 400,
	modal : true,
	title:'编辑',
	autoShow : true,
	defaults:{
		allowBlank:false,
		msgTarget:"side",
		selectOnFocus:true,
		labelAlign:"left",
		labelWidth:80,
		width:300,
		validateOnChange:false
	},
	initComponent : function() {
		Ext.apply(this, {
             items: [{
 				padding:20,
 				bodyStyle:'margin-top:15px',
 				itemId:'userEditForm',
 				layout: 'column',
 				width: 600,
 				height:320,
 				xtype : 'form',
 				items:[{
 					columnWidth: .5,
 	                border:false,
 	                padding:10,
 	                items:[ {
 	 					fieldLabel:"登录帐号",
 	 					blankText:"用户名不能为空",
 	 					minLength :4,
 	 					id:'username1',
 	 					maxLength:18,
 	 					readOnly:true,
 	 					name:'username',
 	 					xtype:'textfield'
 	 				},{
 	 					xtype: 'textfield',
 	 				    allowBlank: false,
 	 				    fieldLabel: '真实姓名',
 	 				    name : 'realname',
 	 				    regex: /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/ ,
 	 					regexText:'不是真实姓名，请重新输入(不能小于两个字符且不能出现数字或其他符号)！'
 	 				},{
 	 					border:false,
 	 					xtype: 'textfield',
 	 				    fieldLabel: '电话号码',
 	 				    name : 'cellphone',
 	 				    validateOnBlur: true,
 	                     validationDelay:2000,
 	                     validateOnChange: false,
 	 				    validator: function (value) {
 	 				    	var error=true;
 	 				    	if(value.length>0){
 	 				    		if (!/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(value)) {
 	 				    			return "电话号码格式不正确！";
 	 				    		}
 	 				    		var validator = this;
 	 				    		var error = true;
 	 				    		Ext.Ajax.request({
 	 				    			async: false,
 	 				    			scope: validator,
 	 				    			url: _ctxPath+'/control/user/valid/editCellphoneExist.do',
 	 				    			params: { 
 	 				    				cellphone: this.value,
 	 				    				username: Ext.ComponentQuery.query('form textfield[name=username]')[0].getValue()
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
 	 					border:false,
 	 					xtype: 'textfield',
 	 				    fieldLabel: '电子邮件',
 	 				    name : 'email',
 	 				    validateOnBlur: true,
 	                     validationDelay:2000,
 	                     validateOnChange: false,
 	                     validator: function (value) {
 	 				    	var error=true;
 	 				    	if(value.length>0){
 	 				    		if (!/^\s*\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*(\;\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*(\;)*\s*$/.test(value)) {
 	 				    			return "电子邮件格式不正确！";
 	 				    		}
 	 				    		var validator = this;
 	 				    		var error = true;
 	 				    		Ext.Ajax.request({
 	 				    			async: false,
 	 				    			scope: validator,
 	 				    			url: _ctxPath+'/control/user/valid/editEmailExist.do',
 	 				    			params: { 
 	 				    				email: this.value,
 	 				    				username: Ext.ComponentQuery.query('form textfield[name=username]')[0].getValue()
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
 	 					fieldLabel: '登录次数',
 	 					xtype:'displayfield',
 	 					readOnly:true,
 	 					name:'logintimes',
 	 				},{
 	 					fieldLabel: '登录时间',
 	 					xtype:'displayfield',
 	 					readOnly:true,
 	 					name:'lastlogintime',
 	 				},{
 	 					fieldLabel: '注册IP',
 	 					xtype:'displayfield',
 	 					readOnly:true,
 	 					name:'regip',
 	 				},{
 	 					fieldLabel: '注册时间',
 	 					xtype:'displayfield',
 	 					readOnly:true,
 	 					name:'regtime',
 	 				},{
 	 					fieldLabel: '上次登录IP',
 	 					xtype:'displayfield',
 	 					readOnly:true,
 	 					name:'lastloginip',
 	 				}]
 				},{
 					columnWidth: .5,
 	 	            border:false,
 	 	            padding:10,
 	 	            items:[{
 	 					xtype: 'fieldset',
 	 	 			    title: '图片预览',
 	 	 			    defaults: {margin:'1px 1px 1px 1px', width: 300,height:200},
 	 	 			    items: [{
 	 	 			    	  xtype: 'image',
 	 	 			    	  id: 'staffavatar',
 	 	 			    	  border: 1,
 	 	 			    	  width: 250,
 	 	 			    	  height:200
 	 	 			        }
 	 	 			    ]
 	 				},{
 	 					layout: 'column',
 	 					border:false,
 	 					items: [{
 	 						columnWidth: .8,
 	 						border:false,
 	 						items: [{
 	 						  xtype:'fileuploadfield',
 	  					      fieldLabel:'更改头像',
 	  					      labelWidth:60,  
                              width:200 ,
 	 					      name:'userImgUpload',
 	 					      anchor: '90%',
 	 					      buttonText:'',
 	 					      buttonConfig:{icon : _ctxPath+'/Images/menuIcon/uploadfilemanage.png',},
 	 					      listeners:{
 	 					          change:function(btn,value){
 	 					           //是否是规定的图片类型
 	 					          var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([gG][iI][fF]){1}$|\.([pP][nN][gG]){1}$|\.([bB][mM][pP]){1}$/;
 	 					          if (img_reg.test(value)) {
 	 					             var img = Ext.getCmp('staffavatar');
 	 					             var file = btn.fileInputEl.dom.files[0];
 	 					             var url = URL.createObjectURL(file);
 	 					             img.setSrc(url);
 	 					             } else {
 	 					             Ext.Msg.alert('提示', '请选择图片类型的文件！');
 	 					             return ;
 	 					            }
 	 					          }
 	 					        }}],
 	 					},{
 	 						columnWidth: .2,
 	 						border:false,
 	 						items: [{ 
 	 						  xtype:'button',
 	  					      anchor: '10%',
 	 					      text:'上传',
 	 					      action:'userImgUpload'
 	 					     }],
 	 					}],
 					   }
 				 ]}
 				]
             }],
			buttons:[{
				action:'updateUser',
			    text : '确定'
			},{
			    text : '关闭',
			    scope:this,
			    handler:this.close
			}]
		});
		this.callParent(arguments);
	}
});
