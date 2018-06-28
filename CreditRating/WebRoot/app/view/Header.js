function formatDate(value){//格式化时间
    return value ? Ext.Date.dateFormat(value, 'Y年m月d日') : '';
}

Ext.define('PM.view.Header', {//创建头部组件
    extend: 'Ext.panel.Panel',//组件类型为系统面板
    border: false,//边框
    layout: 'hbox',//箱子布局
    cls: 'header',//定义样式
    region: 'north',//组件在布局中所处位置
    height: '72px',//组件高度
    margin: '0 0 7 0',
    alias : 'widget.trustHeader',// 组件别名
    bodyStyle: {
    	background: '#3793d2'
//    	padding: '5px'
    },//css定义
    items: [{
        id: 'header-top',//组件id
        xtype: 'box',//创建box组件实例
        cls: 'header',//定义样式
        border: false,//隐藏边框
        minWidth:300,
        style: logoImage/*'padding: 45px 20px 10px 31px;background-repeat: no-repeat ; background-image: url('+_ctxPath+'/Styles/DWZ/themes/default/images/logo.png);',*/,
        flex: 2,//表示组件占总高度分之一
//        html:'no-repeat '
    }, new Ext.Toolbar({
    	//flex: 6,//表示组件占总高度分之一
        flex: 8,//表示组件占总高度分之一
        border: true,
        baseCls : 'header',
        style: 'padding: 15px 20px 10px 30px; color: white; font-family: Microsoft YaHei;',
        items: [ "->",{
            //此处加载登录用户信息
            xtype: 'label',//创建一个label实例
            text: userName,
            //margin: '0 20 0 20'//样式规范
        },'-',{
//        	flex: 3,//表示组件占总高度分之一
            //此处加载登录用户信息
            xtype: 'label',//创建一个label实例
            text:formatDate(new Date()),//label显示内容
            //margin: '0 20 0 20'//样式规范
        },{
        	xtype:'button',
        	action:'editPassword',
        	iconCls : 'Useredit',
        	text: '修改密码'
        },{
            xtype:'button',
        	text: '退出',
        	id : 'logout',
        	iconCls : 'Userdelete',
        	handler:function(){
        		Ext.Msg.confirm('请确认', '<font color=red>是否真的退出系统?</font>', function(button) {
        			if (button =='yes') {
    					var msgTip = Ext.MessageBox.show({
    						title:'提示',
    						width:250,
    						msg:'正在退出，请稍候...'
    					});
                		Ext.Ajax.request({
                			url:_ctxPath+'/user/quit.do',
                			success:function(){
                				msgTip.hide();
                				window.location.replace(_ctxPath+"/user/quit.do");
                			}
                			
                		});
        			}
        		});
        	}
        }]
    })]
});


