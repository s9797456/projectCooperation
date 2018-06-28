package com.credit.util.properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ValidateFile {

	private static Properties imageProperties = new Properties();
	private static Properties fileproperties = new Properties();
	private static Properties fileproperties1 = new Properties();
	static{
		try {
			imageProperties.load(ValidateFile.class.getClassLoader().getResourceAsStream("uploadImageType.properties"));
			fileproperties.load(ValidateFile.class.getClassLoader().getResourceAsStream("uploadFileType.properties"));
			fileproperties1.load(ValidateFile.class.getClassLoader().getResourceAsStream("uploadFileType1.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 验证上传文件类型是否属于图片格式
	 * @return
	 */
//	public static boolean imageFileType(String sfile,String sfiletype){
//		if(sfile!=null && sfile.length()>0){
//			List<String> arrowType = Arrays.asList("image/bmp","image/png","image/gif","image/jpg","image/jpeg","image/pjpeg");
//			List<String> arrowExtension = Arrays.asList("gif","jpg","bmp","png");
//			String ext = sfile.trim().substring(sfile.trim().lastIndexOf('.')+1).toLowerCase();
//			
//			return arrowType.contains(sfiletype) && arrowExtension.contains(ext);
//		}	
//		return false;
//	}
	/**
	 * 验证上传文件类型是否属于图片格式
	 * @return
	 */
	public static boolean imageType(String sfile,String sfiletype){
		if(sfile!=null && sfile.length()>0){
			String ext = sfile.trim().substring(sfile.trim().lastIndexOf('.')+1).toLowerCase();
			List<String> arrowType = new ArrayList<String>();
			for(Object key : imageProperties.keySet()){
				String value = (String)imageProperties.get(key);
				String[] values = value.split(",");
				for(String v : values){
					arrowType.add(v.trim());
				}
			}
			return arrowType.contains(sfiletype) && imageProperties.keySet().contains(ext);
		}
		return false;
	}	
	/**
	 * 验证上传文件是否属于图片/flash动画/word文件/exe文件/pdf文件/TxT文件/xls文件/ppt文件
	 * @param formfile
	 * @return
	 */
	public static boolean fileType(String sfile,String sfiletype){
		System.out.println(sfile);
		if(sfile!=null && sfile.length()>0){
			String ext = sfile.trim().substring(sfile.trim().lastIndexOf('.')+1).toLowerCase();
			System.out.println(ext);
			List<String> arrowType = new ArrayList<String>();
			for(Object key : fileproperties.keySet()){
				String value = (String)fileproperties.get(key);
				String[] values = value.split(",");
				for(String v : values){
					arrowType.add(v.trim());
				}
			}
			return arrowType.contains(sfiletype) && fileproperties.keySet().contains(ext);
		}
		return false;
	}
	
	/**
	 *  验证上传文件是否为word文件
	 */
	public static boolean fileType1(String sfile,String sfiletype){
		System.out.println(sfile);
		if(sfile!=null && sfile.length()>0){
			String ext = sfile.trim().substring(sfile.trim().lastIndexOf('.')+1).toLowerCase();
			System.out.println(ext);
			List<String> arrowType = new ArrayList<String>();
			for(Object key : fileproperties1.keySet()){
				String value = (String)fileproperties1.get(key);
				String[] values = value.split(",");
				for(String v : values){
					arrowType.add(v.trim());
				}
			}
			return arrowType.contains(sfiletype) && fileproperties1.keySet().contains(ext);
		}
		return false;
	}

	
	
}
