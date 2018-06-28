package com.credit.util.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class FileToString {
	/* public static void main(String[] args) throws IOException {
	 
	  String fileContent = readFileContent("D:/UploadFiles/CreditRating/2017/08/17/c1fedeae35834cfcb6e31f72f76279a6.xml");
	  System.out.println(fileContent);
	  System.out.println(XMLFormat.format(fileContent));
	  
	 }*/
	 
	 public static String readFileContent(String fileName) throws IOException {
	 
	  File file = new File(fileName);
	   
	  BufferedReader bf = new BufferedReader(new FileReader(file));
	   
	  String content = "";
	  StringBuilder sb = new StringBuilder();
	   
	  while(content != null){
	   content = bf.readLine();
	    
	   if(content == null){
	    break;
	   }
	    
	   sb.append(content.trim());
	  }
	   
	bf.close();
	  return sb.toString();
	 }
}
