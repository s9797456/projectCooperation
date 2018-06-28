Ext.apply(Ext.form.VTypes,{
	fileTypeValid:function(val,field){//val指这里的文本框值，field指这个文本框组件，大家要明白这个意思
		var str = val.substring(val.lastIndexOf(".")+1,val.length);
		if(str=="xml"){
			return true;
		}
    },
    fileTypeValidText : "请上传文件格式为xml"
});
Ext.QuickTips.init();// 错误悬浮提示
Ext.define("PM.view.model.modelContentAdd", {
	extend : 'Ext.form.Panel',
	alias : 'widget.modelContentAdd',
	title:'新增',
	itemId:'addModelForm',
	xtype:'form',
	autoScroll : true,
	style : "margin-top:15px;",
	layout : 'column',
	items : [/*{
		xtype : 'textfield',
		fieldLabel : 'parentID',
		labelWidth : 100,
		name : 'parentID',
		hidden:true,
	},*/{
		xtype : 'textfield',
		fieldLabel : '名称',
		labelWidth : 100,
		width : 375,
		allowBlank:false,
		style : 'margin: 10px 0px 10px 20px;',
		name:'name'
	},{
		xtype : 'textfield',
		labelWidth : 100,
		width : 375,
		style : 'margin: 10px 0px 10px 20px;',
		fieldLabel : '模型描述',
		name:'remark'
	},{
		xtype : 'numberfield',
		fieldLabel : '排序号',
		labelWidth : 100,
		width : 375,
		allowBlank:false,
		maxValue: 15,
        minValue: 1,
		style : 'margin: 10px 0px 10px 20px;',
		name:'orderNO'
	}, {
		xtype : 'fileuploadfield',
		id : 'formFile',
		width : 375,
		style : 'margin: 10px 0px 10px 20px;',
		emptyText : '请选择xml文件(大小不超过2M)...',
		fieldLabel : '上传模型',
		name : 'uploadFile',
		buttonText : '选择文件',
		buttonCfg : {
			iconCls : 'upload-icon'
		},
		vtype:'fileTypeValid',
		msgTarget: 'side',
		allowBlank:false
	}],
	buttons : [ {
		text : '保存',
		action : "saveModelConAdd"
	}, {
		text : '取消',
		action:'cancelConSave'
	} ]
});

	