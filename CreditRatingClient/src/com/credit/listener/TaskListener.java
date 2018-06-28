package com.credit.listener;
import java.util.TimerTask;  

import javax.annotation.Resource;

  
import com.credit.service.enterprise.UploadFileService;
import com.credit.util.security.SecurityUtil;
  
public class TaskListener extends TimerTask {  
    private static boolean isRunning = false;  
	@Resource
	private UploadFileService uploadFileService;
    public TaskListener(UploadFileService uploadFileService2) {
    	uploadFileService=uploadFileService2;
	}
	@Override  
    public void run() {  
        if (!isRunning) {  
            isRunning = true;  
            System.out.println("---------------------------------批量数据跨域上传开始！！！----------------------------------");
    		if(SecurityUtil.getMsg("BatchUpload").equals("1")){
    			uploadFileService.batchUploadFile();
    		}
    		System.out.println("---------------------------------批量数据跨域上传成功！！！----------------------------------");
            isRunning = false;  
        } else {  
            System.out.println("---------------批量数据跨域上传失败！！！-----------执行错误");  
        }  
  
    }  
  

  
}  