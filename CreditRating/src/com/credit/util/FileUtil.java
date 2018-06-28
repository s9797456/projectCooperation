package com.credit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.credit.util.properties.BusinessUtil;



public class FileUtil {
	
	public static boolean isExist(String path){
		File file=new File(path);
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	}
	 public static boolean deleteFile(String fileName) {
		    boolean flag=true;
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
	                flag=false;
	            }
	        } else {
	            System.out.println("删除单个文件失败：" + fileName + "不存在！");
	            str="none";
	            flag=false;
	        }
			return flag;
	    }
	 
	 public static boolean copyFile(String oldPath, String newPath) { 
		 boolean flag=true;
		 File file=new File(oldPath);
			if(file.exists()){
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
							 fs.write(buffer, 0, byteread); 
						 } 
						 inStream.close(); 
					 } 
				 } 
				 catch (Exception e) { 
					 flag=false;
					 System.out.println("复制单个文件操作出错"); 
					 e.printStackTrace(); 
				 }
			}else{
				 flag=false;
			}
		return flag; 
	 } 
	 public static boolean copyAndDelete(String oldPath, String newPath){
		 boolean flag=true;
		 File file=new File(oldPath);
			if(file.exists()){
				if(copyFile(oldPath, newPath)){
					System.out.println(oldPath+"文件复制成功");
					/*if(deleteFile(oldPath)){
						System.out.println(oldPath+"文件删除成功");
					}else{
						System.out.println(oldPath+"文件删除失败");
						flag=false;
					}*/
				}else{
					System.out.println(oldPath+"文件复制失败");
					flag=false;
				}
			}else{
				flag=false;
			}
			return flag;
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
				 if (resEntity != null&& resEntity.getContentLength() >0) {  
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
	 /**
      * 读取某个文件夹下的所有文件
	 * @param url 
      */
	 public static boolean readfile(String filepath, String url) throws FileNotFoundException, IOException {
		 String adr=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root");
		 try {
			 File file = new File(filepath);
			 if (!file.isDirectory()) {
				 System.out.println("文件：path=" + file.getAbsolutePath());
				 String relativePath=file.getAbsolutePath().replace(adr, "");
				 WebUtil.crossFile(relativePath, file.getAbsolutePath(), url);
			 } else if (file.isDirectory()) {
				 System.out.println("文件夹");
				 String[] filelist = file.list();
				 for (int i = 0; i < filelist.length; i++) {
					 File readfile = new File(filepath + "\\" + filelist[i]);
					 if (!readfile.isDirectory()) {
						 System.out.println("文件：path=" + readfile.getAbsolutePath());
						 String relativePath=readfile.getAbsolutePath().replace(adr, "");
						 WebUtil.crossFile(relativePath, readfile.getAbsolutePath(), url);
					 } else if (readfile.isDirectory()) {
						 readfile(filepath + "\\" + filelist[i],url);
					 }
				 }

			 }

		 } catch (FileNotFoundException e) {
			 System.out.println("readfile()   Exception:" + e.getMessage());
		 }
		 return true;
	 }
}
