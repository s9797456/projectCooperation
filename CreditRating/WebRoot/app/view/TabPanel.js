Ext.define('PM.view.TabPanel', {//创建主窗口组件
    extend: 'Ext.tab.Panel',//定义类型
    alias: 'widget.hometabpanel',//组件别名
    initComponent: function(){//初始化组件
        Ext.apply(this, {//将配置与组件内容复制到this对象即组件实例
            region: 'center',//定义组件位置
            defaults: {//为所有其下所有组件设置默认属性
                autoScroll: true,
                bodyPadding: 5
            },
            activeTab: 0,//最初被激活的选项卡
            border: false,//边框
            animScroll:true,
            deferredRender: false,//渲染设置，false渲染所有组件，true只渲染激活的组件
            plain: true,//默认为false，true时不在选项卡栏上显示所有背景
            items: [{   
                id: 'HomePage',   
                title: '首页', 
                iconCls:'home',
                layout: {  
                  type: 'hbox',  
                  pack: 'start',  
                  align: 'stretch' 
                },  
                items: [{
                	xtype: 'panel',
                    flex: 5,  
                    border: 0,  
                    width: 604,
                    bodyStyle: 'background-image: url('+_ctxPath+'/Images/replace/introduction.jpg);background-repeat: no-repeat;background-position: center;background-size:100% 100%;',
                    itemId : "vboxFristPanel",
                    layout: {  
                        align: 'stretch',  
                        type: 'vbox' 
                    },  
                 /*   items: [{
                  	  xtype: 'panel',  
                  	  title: '用户操作流程' ,
                  	  itemId:'firstHomePanel',
                  	  flex: 1, 
                  	  layout: {
                         type: 'hbox',
                         pack: 'start',              //纵向对齐方式 start：从顶部；center：从中部；end：从底部
                         align: 'middle'             //对齐方式 center、left、right：居中、左对齐、右对齐；stretch：延伸；stretchmax：以最大的元素为标准延伸
                      },
                      items: [{
                    	  xtype: 'container',
                          flex: 1,
                          border: 0, 
                          margin : '0 0 0 60',
                          html : '<a href="javascript:testTab();"><img id="tip1" src="../../Styles/dfImage/firstStep.png" alt="阅读协议书"  width="90%"></a>'
                      },{
                    	  xtype: 'container',
                          flex: 1,
                          border: 0, 
                          margin : '0 0 0 -20',
                          html : '<a href="javascript:testTab();"><img id="tip2" src="../../Styles/dfImage/secondStep.png" alt="阅读协议书"  width="90%"></a>'
                      },{
                    	  xtype: 'container',
                          flex: 1,
                          border: 0, 
                          margin : '0 0 0 -20',
                          html : '<a href="javascript:testTab();"><img id="tip3" src="../../Styles/dfImage/thridStep.png" alt="阅读协议书"  width="90%"></a>'
                      },{
                    	  xtype: 'container',
                          flex: 1,
                          border: 0, 
                          margin : '0 0 0 -20',
                          html : '<a href="javascript:testTab();"><img id="tip4" src="../../Styles/dfImage/fourStep.png" alt="阅读协议书"  width="90%"></a>'
                      }],
                      listeners: {
                    	  boxready: function(){
                    		  new Ext.ToolTip({  
                                  target : "tip1",  
                                  trackMouse : false,  
                                  draggable : true,  
                                  maxWidth : 200,  
                                  minWidth : 100,  
                                  title : '操作提示',  
                                  html : "ces"  
                              });
                    		  new Ext.ToolTip({  
                                  target : "tip2",  
                                  trackMouse : false,  
                                  draggable : true,  
                                  maxWidth : 200,  
                                  minWidth : 100,  
                                  title : '操作提示',  
                                  html : "ces"  
                              });
                    		  new Ext.ToolTip({  
                                  target : "tip3",  
                                  trackMouse : false,  
                                  draggable : true,  
                                  maxWidth : 200,  
                                  minWidth : 100,  
                                  title : '操作提示',  
                                  html : "ces"  
                              });
                    		  new Ext.ToolTip({  
                                  target : "tip4",  
                                  trackMouse : false,  
                                  draggable : true,  
                                  maxWidth : 200,  
                                  minWidth : 100,  
                                  title : '操作提示',  
                                  html : "ces"  
                              });
                    		  Ext.ComponentQuery.query('hometabpanel panel#firstHomePanel')[0].doLayout();
                    	   }
                      	}
                    }]*/
                }/*,{
                	xtype: 'panel',  
                    flex: 2,  
                    //margin: '0 0 0 0',  
                    header: false,  
                    border: false,  
                    itemId : 'vboxSecondPanel',
                    //bodyStyle: 'background-image: url('+_ctxPath+'/Styles/DWZ/themes/default/images/right.jpg);background-repeat: no-repeat;background-position: center;background-size:100% 100%;',
                     layout: {  
                        align: 'stretch',//可能会加其他对象  
                        type: 'vbox' 
                    },  
                    items:[{
                  	   xtype: 'panel',  
                       flex: 1,  
                       margin: '0 0 0 0',  
                       title: '系统介绍'
                    }]
                 }*/]
              }]   
        });
        this.callParent(arguments);
    }
});
