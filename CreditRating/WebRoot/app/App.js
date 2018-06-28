Ext.Loader.setConfig({//Ext.Loader.setConfig帮我们开启了自动加载的功能，这个功能默认是不开启的，需要我们手动开启，否则以后开发的很多文件都会找不到
    enabled: true//开启动态加载
});
Ext.application({//创建应用程序的实例
    name: 'PM',//应用名字 给项目规定了一个命名空间，它将作为一个全局变量应用于整个项目中，帮助我们识别属于本项目的应用文件。
    appFolder: _ctxPath+'/app',//应用的目录
    controllers: ['MenuTree','Main'],
    launch: function() {//当前页面加载完成后执行的函数
    	Ext.create('Ext.Viewport',{
    		layout: 'fit',
		    hideBorders: true,
		    requires : [
		        'PM.view.Header',
		        'PM.view.Cords',
		        'PM.view.TabPanel',
		        'PM.view.South'
		    ],
		    items: [{
                id:'desk',
                layout: 'border',
				region: 'center',
                items: [
                    Ext.create('PM.view.Header'),
                    Ext.create('PM.view.Cords'),
                    Ext.create('PM.view.TabPanel'),
                    Ext.create('PM.view.South')
                ]
            }]
    	});

    
    	Ext.tip.QuickTipManager.init();//开启悬浮提示功能
    	Ext.util.Observable.observeClass(Ext.data.Connection);
    	Ext.data.Connection.on("requestcomplete", function(g, h, f) {
			if (h && h.getResponseHeader) {
				if (h.getResponseHeader("error:timeout")) {
					window.location.replace(_ctxPath+"/user/quit.do");
				} else {
					if (h.getResponseHeader("error:403")) {
						Ext.MessageBox.alert('操作提示','您没有此模块权限!');
					}
				}
			}
		});
    	Ext.data.Connection.on("requestexception", function(g, h, f) {
		if (h && h.getResponseHeader) {
				if (h.getResponseHeader("error:500")) {
					Ext.MessageBox.alert('后台出错','您访问的URL:{0}出错了，具体原因请联系管理员。!');
				} else {
					if (h.getResponseHeader("error:404")) {
						Ext.MessageBox.alert('后台出错','您访问的URL:{0}对应的页面不存在，具体原因请联系管理员。!');
					}
				}
			}
    	});
    	

    },
});
