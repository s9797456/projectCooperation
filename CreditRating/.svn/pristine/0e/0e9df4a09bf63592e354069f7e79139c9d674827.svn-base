package com.credit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.credit.util.properties.BusinessUtil;





public class SaveFile {
	

	public static String uploadFile(HttpServletRequest request,String path, String fileName) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        File file1 = new File(path);
        if(!file1.exists()&& !file1.isDirectory()) file1.mkdirs();
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
                        //重命名上传后的文件名  
                    	String str=myFileName.substring(myFileName.lastIndexOf(".") + 1);
                    	if(fileName==null){
                    		fileName = sdf.format(new Date())+RandomUtil.getRandomNumber(6)+"."+str;  
                    	}
                        File localFile1 = new File(path+fileName); 
                        // 转存文件  
                        file.transferTo(localFile1);
                    }  
                }  
            }  
        }
		return fileName;  
	}
	public static boolean judgeSuffix(HttpServletRequest request,String suffix) throws Exception {
		boolean flag=false;
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
                        //重命名上传后的文件名  
                    	String str=myFileName.substring(myFileName.lastIndexOf(".") + 1);
                    	if(str.equals(suffix)){
                    		flag=true;
                    	}
                    }  
                }  
            }  
        }
		return flag;  
	}
	 public static String deleteFile(String fileName) {
		 String str=new String();
	        File file = new File(fileName);
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	                System.out.println("删除单个文件" + fileName + "成功！");
	                str="success";
	            } else {
	                System.out.println("删除单个文件" + fileName + "失败！");
	                str="fail";
	            }
	        } else {
	            System.out.println("删除单个文件失败：" + fileName + "不存在！");
	            str="none";
	        }
			return str;
	    }
	 
	 public static void copyFile(String oldPath, String newPath) { 
		 try { 
			 int bytesum = 0; 
			 int byteread = 0; 
			 File oldfile = new File(oldPath); 
			 if (oldfile.exists()) { //文件存在时 
				 InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				 FileOutputStream fs = new FileOutputStream(newPath); 
				 byte[] buffer = new byte[1444]; 
				 while ( (byteread = inStream.read(buffer)) != -1) { 
					 bytesum += byteread; //字节数 文件大小 
					 System.out.println(bytesum); 
					 fs.write(buffer, 0, byteread); 
				 } 
				 inStream.close(); 
			 } 
		 } 
		 catch (Exception e) { 
			 System.out.println("复制单个文件操作出错"); 
			 e.printStackTrace(); 
		 } 
	 } 
	 /**
	  * @param relativePath 相对路径 向服务器传参
	  * @param absolutePath 绝对路径 本地文件路径
	  */
	 public static boolean crossFile(String relativePath,String absolutePath,String url){
		 boolean success=false;
		 CloseableHttpClient httpclient = HttpClients.createDefault();  
		 try {  
			 url=url+BusinessUtil.getMsg("ReceiveUrl");
			 HttpPost httppost = new HttpPost(url+"?filepath="+URLEncoder.encode(relativePath, "UTF-8"));  
			 FileBody bin = new FileBody(new File(absolutePath));  
			 StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);  
			 HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();  
			 httppost.setEntity(reqEntity);  
			 CloseableHttpResponse response = httpclient.execute(httppost);
			 try {  
				 System.out.println(response.getStatusLine());  
				 HttpEntity resEntity = response.getEntity();  
				 if (resEntity != null) {  
					 System.out.println("Response content length: " + resEntity.getContentLength());  
					 //接收返回信息
					 String result=EntityUtils.toString(resEntity); 
					 JSONObject json=JSONObject.fromObject(result);
					 success=json.getBoolean("success");
				 }  
				 EntityUtils.consume(resEntity);  
			 } finally {  
				 response.close();  
			 }  
		 } catch (ClientProtocolException e) {  
			 e.printStackTrace();  
		 } catch (IOException e) {  
			 e.printStackTrace();  
		 } finally {  
			 try {  
				 httpclient.close();  
			 } catch (IOException e) {  
				 e.printStackTrace();  
			 }  
		 }
		return success;  
	 }  
}
