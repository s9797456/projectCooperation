package com.credit.interfaces;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.credit.util.properties.BusinessUtil;


@Controller
@RequestMapping("/cross")
public class CrossDomain {
	private static final Logger logger = Logger.getLogger(CrossDomain.class);
	private String moduleName = "管理员操作情况";

	
	/**
	 * @title 跨域接收文件
	 * @author  孙尚飞  2017-12-19
	 * @desc
	 */
	@RequestMapping("/receiveCrossFile")
	@ResponseBody
	public Map<String,Object> receiveCrossFile(HttpServletRequest request) {
		String relativePath=request.getParameter("filepath");
		String path=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root");
		logger.info(moduleName + "[跨域文件接收开始] "+relativePath);
		File relativefile=new File(path+relativePath);
		//判断目标文件所在的目录是否存在  
        if(!relativefile.getParentFile().exists()) {  
            //如果目标文件所在的目录不存在，则创建父目录  
            System.out.println("目标文件所在目录不存在，准备创建它！");  
            if(!relativefile.getParentFile().mkdirs()) {  
                System.out.println("创建目标文件所在目录失败！");  
            }else{
            	System.out.println("创建目标文件所在目录成功！");  
            }  
        }  
		Map<String, Object> msgMap = new HashMap<String, Object>();
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		//创建一个通用的多部分解析器  
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		//判断 request 是否有文件上传,即多部分请求  
		if(multipartResolver.isMultipart(request)){  
			//转换成多部分request    
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
			//取得request中的所有文件名  
			Iterator<String> iter = multiRequest.getFileNames();  
			while(iter.hasNext()){  
				//取得上传文件  
				MultipartFile file = multiRequest.getFile(iter.next());  
				if(file != null){  
					//取得当前上传文件的文件名称  
					String myFileName = file.getOriginalFilename();  
					//如果名称不为“”,说明该文件存在，否则说明该文件不存在  
					if(myFileName.trim() !=""){
						File localFile1 = new File(path+relativePath); 
						// 转存文件  
						try {
							file.transferTo(localFile1);
							msgMap.put("success", true);
							logger.info(moduleName + "[跨域文件保存成功]");
						} catch (Exception e) {
							e.printStackTrace();
							msgMap.put("success", false);
							logger.info(moduleName + "[跨域文件保存失败]");
						}
					}  
				}  
			}  
		}
		logger.info(moduleName + "[跨域文件接收结束]");
		return msgMap;  
	}

}
