package com.credit.listener;

import java.util.Calendar;  
import java.util.Timer;  
  
import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;  

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.credit.service.enterprise.UploadFileService;
  
  
public class TaskManager implements ServletContextListener {  
  
    //每天的毫秒数  
    public static final long DAY = 86400000;  
    //定时器  
    private Timer timer;  
      
    /** 
     * 在Web应用结束时停止任务 
     */  
    public void contextDestroyed(ServletContextEvent sce) {  
        timer.cancel();//定时器销毁  
  
    }  
  
    /** 
     * 在Web应用启动时初始化任务 
     */  
    public void contextInitialized(ServletContextEvent sce) {  
    	UploadFileService uploadFileService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(UploadFileService.class);
        //定义定时器  
        Calendar c = Calendar.getInstance();  
        c.add(Calendar.DATE, 1);  
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        timer = new Timer(true);  
        TaskListener locationTask = new TaskListener(uploadFileService);//定时执行的内容  
        timer.schedule(locationTask, c.getTime(), DAY); //定时器在每日凌晨0点执行  
        //timer.schedule(locationTask, 5000, 10000); // 启动后5秒执行,后每隔10秒在执行  
    }  
}  