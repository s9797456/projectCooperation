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
Ext.define('PM.view.template.editTemplate',{
	extend:'Ext.Window' ,
	alias : 'widget.editTemplate',
	closable:true,
	//closeAction:'hide',
	id:'editReportTemplateWin',
	autoShow: true,
	title:'编辑',
	layout : 'column',
	border : 0,
	modal : true,
	selectOnFocus:true,
	iconCls:'Applicationformedit',
	width:400,
	height:400,
	initComponent:function(){
		var parentID = Ext.getCmp('gridPanel').getStore().getProxy().extraParams.parentid;
		var treeStore=Ext.create('Ext.data.TreeStore',{
			proxy : {
				type : 'ajax', 
				url : _ctxPath+'/control/addition/template/treeUI.do',
				actionMethods : {
					read : 'POST'
				},
				extraParams: {
					parentID:parentID,
					queryDemand:1
				},
				reader:'json',
			},
			autoLoad : true
		});
		treeStore.load();
		var treePanel=Ext.create('Ext.tree.Panel',{
			id:'tree',
			useArrows : true,
			rootVisible: false,
			border:false,
			lines :true,
			renderTo:Ext.getBody(),
			store:treeStore,
			listeners:{'itemclick':addValue2}
		});
		
		  this.items = [{xtype:'fieldset',columnWidth:1,margin:'10 10 10 10',height:300,id:"editItemId",items:[{
			xtype : 'form',
			border:false,
			itemId:'EditformPanel',
			bodyPadding: 5,
			defaults:{margin:'0 0 10 0'},
			items : [{
				xtype : 'textfield',
				type : 'hidden',
				name : 'parentID',
				id:'parentIDE',
				hidden : true
			},{
				xtype : 'textfield',
                type:'hidden',
				name:'uuid',
				hidden:true
			},{
				xtype : 'textfield',
				fieldLabel : '模板名称',
				width:300,
				allowBlank : false,
				name:'templateName',
				id:'templateNameE'
			}, {
				xtype : 'textarea',
				fieldLabel : '备注',
				name : 'remark',
				width : 300,
				height : 40
			},{
				xtype : 'textfield',
				fieldLabel : '分类',
				allowBlank : false,
				readOnly:true,
				width : 300,
				name:'categoryNameE',
				id : 'categoryNameE'
			}, {
				xtype : 'fieldset',
				width : 300,
				id:'cateShowE',
				height : 80,
				overflowX : 'hidden',
				overflowY : 'auto',
				padding : 5,
				items : treePanel
			}, {
				xtype : 'fileuploadfield',
				id : 'formFileE',
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
		this.buttons = [
			        {text:'提交',action:'doEdit4'},
			        {text:'关闭',scope:this,handler:this.close,}
		
            ];                
		this.callParent(arguments);
	}
}
	);

