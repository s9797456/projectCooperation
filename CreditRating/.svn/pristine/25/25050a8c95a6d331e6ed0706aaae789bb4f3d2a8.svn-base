Ext.define('PM.view.South',{//创建底部组件
    extend: 'Ext.Toolbar',//定义组件类型
    initComponent : function(){//创建组件实例后初始化模块
        Ext.apply(this,{//将配置与组件内容复制到this对象即组件实例
            id:"bottom",
            //frame:true,
            region:"south",
            height:33,
            margin: '5 0 0 0',
            style: 'background-color:#e0eaf3',
            items:['->',"<a href="+orgUrl+" target='_blank' style='text-decoration:none;'><font color='#0000FF'>"+name+"</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tel:0512-62956863&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+email]
        });
        this.callParent(arguments);
    }
});