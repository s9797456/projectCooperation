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
Ext.define('PM.view.template.addTemplate',{
	extend : 'Ext.Window',
	alias : 'widget.addTemplate',
	closable : true,
	id : 'addTemplate',
	autoShow : true,
	title : '新增',
	layout : 'column',
	border : 0,
	modal : true,
	selectOnFocus : true,
	iconCls : 'Applicationformadd',
	width : 400,
	height : 400,
	initComponent : function() {
		var parentID = Ext.getCmp('gridPanel').getStore().getProxy().extraParams.parentid;
		var TreeStore = Ext.create('Ext.data.TreeStore',{
			proxy : {
				type : 'ajax',
				url : _ctxPath + '/control/addition/template/treeUI.do',
				extraParams: {
					parentID:parentID,
					queryDemand:0
				},
				actionMethods : {
					read : 'POST'
				},
				writer:{type:"json"},
				reader : 'json',
			},
			autoLoad : true
		});
		TreeStore.load();
		var treePanel = Ext.create('Ext.tree.Panel', {
			id : 'tree',
			useArrows : true,
			rootVisible : false,
			border : true,
			lines : false,
			split : true,
			autoScroll : true,
			renderTo : Ext.getBody(),
			store : TreeStore,
			listeners : {
				'itemclick' : addValue
			}
		});
		this.items = [ {
			xtype : 'fieldset',
			columnWidth : 1,
			margin : '10 10 10 10',
			height : 300,
			id:'addItemId',
			items : [ {
				xtype : 'form',
				itemId : 'panel',
				border : false,
				autoWidth : true,
				bodyPadding : 5,
				defaults : {
					labelAlign : "right",
					margin : '0 0 10 0'
				},
				items : [ {
					xtype : 'textfield',
					type : 'hidden',
					name : 'parentID',
					id:'parentID',
					hidden : true
				}, {
					xtype : 'textfield',
					type : 'hidden',
					name : 'uuid',
					hidden : true
				}, {
					xtype : 'textfield',
					fieldLabel : '模版名称',
					allowBlank : false,
					width : 300,
					name : 'templateName',
					id:'templateName'
				}, {
					xtype : 'textarea',
					fieldLabel : '备注',
					name : 'remark',
					width : 300,
					height : 40
				}, {
					xtype : 'textfield',
					fieldLabel : '分类',
					allowBlank : false,
					readOnly:true,
					width : 300,
					id : 'categoryName'
				}, {
					xtype : 'fieldset',
					width : 300,
					id:'cateShow',
					height : 80,
					overflowX : 'hidden',
					overflowY : 'auto',
					padding : 5,
					items : treePanel
				}, {
					xtype : 'fileuploadfield',
					id : 'formFile',
					width : 300,
					emptyText : '请选择xml文件....',
					fieldLabel : '上传模版',
					name : 'uploadFile',
					buttonText : '选择文件',
					buttonCfg : {
						iconCls : 'upload-icon'
					},
					vtype:'fileTypeValid',
					msgTarget: 'side',
				}]
			} ]
		} ];
		this.buttons = [ {
			text : '提交',
			action : 'addsubmit'
		}, {
			text : '关闭',
			scope : this,
			handler : this.close
		} ];
		this.callParent(arguments);
	}
});