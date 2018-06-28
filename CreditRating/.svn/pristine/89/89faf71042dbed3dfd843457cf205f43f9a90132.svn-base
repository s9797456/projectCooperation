Ext.define('PM.controller.MenuTree', {
    extend: 'Ext.app.Controller',
    refs : [ {
		ref : 'hometabpanel',
		selector : 'hometabpanel'
	} ],
    init: function(app) {
    	this.control({
    		'cdmenu':{
    			render:this.addTreePanel
    		},
			'hometabpanel button#test' : {
				click:this.testTab
			}
		});
    	app2=app;
    },
    
    addTreePanel:function(panel){
    	Ext.Ajax.request({
    		url:_ctxPath+'/control/navigation/getTreePanelList.do',
    		method:'post',
    		success:function(response,option){
    			var record=Ext.JSON.decode(response.responseText);
    			for(var i=0;i<record.length;i++){
	    			panel.add(Ext.create('Ext.tree.Panel',{
	    					 title : record[i].title,
	    					 icon:record[i].icon,
	    					 autoScroll : true,
	                         rootVisible : false,
	                         viewConfig : {
	                             loadingText : "正在加载..."
	                         },
	                        store:createStore(record[i].id),
	                        listeners:{
	                        	itemmousedown :this.loadMenu
	                        }
	    				}));
	    			panel.doLayout();
    			}
    		},
    		failure:function(response,option){
    			
    		}
    	});
    	var createStore=function(id){
			 return Ext.create("Ext.data.TreeStore",{
                  defaultRootId : id, //默认的根节点id
                  proxy : {
                      type : "ajax", //获取方式
                      url : _ctxPath+'/control/navigation/userMenuTree.do', //获取树节点的地址
                      extraParams:{id:id}
                  },
                  clearOnLoad : true,
                  nodeParam : "id"//设置传递给后台的参数名,值是树节点的id属性
              });
    	};
    }
});
var loadMenu=function(tree,record,item,index,e,eOpts){
	var selected = record;
	var app = app2;
	var center = app2.getController('MenuTree').getHometabpanel();
	var text=selected.get('text');
	var id=selected.get('id');
	var leaf=selected.get('leaf');
	if(leaf){
	Ext.Ajax.request({
		url: _ctxPath+'/control/navigation/getMenus.do',
		method:'post',
		params:{id:id},
		success:function(response,option){
			var record=Ext.JSON.decode(response.responseText);
			var rel=record.rel;
			var target=record.target;
			Ext.require("PM.controller."+rel, function() {
				self.generateCenter(rel, target,
						selected, app, center);
			}, self);
		}
	});
	}
};
var generateCenter=function(controller,resource,selected, app, center) {
	app.getController(controller);
	// resource对应alias : 'widget.xxx'的xxx                                                                                                                                                                                                             
	var panel = center.child(resource);
	if (!panel) {
		panel = Ext.widget(resource, {
			title : selected.get('text'),
			icon : selected.get('icon'),
			closable : true
		});
		center.add(panel);
	}
	center.setActiveTab(panel);
	return panel;
};
var app2;