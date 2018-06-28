package com.credit.util.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class ModelUtil {
	/**
	 * @title 保存XML文件
	 * @author  孙尚飞  2017-8-3
	 * @desc
	 */
	public static void saveXML(List<Index> indexs,String path) throws Exception {
		try{
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("Model");
			for(Index firstIndex : indexs) {
				Element firstNode = root.addElement("Index");
				firstNode.addAttribute("uuid", firstIndex.getUuid());
				firstNode.addAttribute("name", firstIndex.getName());
				firstNode.addAttribute("level", firstIndex.getLevel());
				firstNode.addAttribute("weight", firstIndex.getWeight());
				firstNode.addAttribute("desc", firstIndex.getDesc());
				for(Index secondIndex : firstIndex.getChirds()){
					Element secondNode = firstNode.addElement("Index");
					secondNode.addAttribute("uuid", secondIndex.getUuid());
					secondNode.addAttribute("name", secondIndex.getName());
					secondNode.addAttribute("level", secondIndex.getLevel());
					secondNode.addAttribute("weight", secondIndex.getWeight());
					secondNode.addAttribute("desc", secondIndex.getDesc());
					for(Index thirdIndex : secondIndex.getChirds()){
						Element thirdNode = secondNode.addElement("Index");
						thirdNode.addAttribute("uuid", thirdIndex.getUuid());
						thirdNode.addAttribute("name", thirdIndex.getName());
						thirdNode.addAttribute("level", thirdIndex.getLevel());
						thirdNode.addAttribute("weight", thirdIndex.getWeight());
						thirdNode.addAttribute("desc", thirdIndex.getDesc());
						for(Option option:thirdIndex.getOptions()){
							Element optionNode = thirdNode.addElement("option");
							optionNode.addAttribute("name", option.getName());
							optionNode.addAttribute("value", option.getValue());
							if(option.getSelected()!=null){
								optionNode.addAttribute("selected", option.getSelected());
							}
						}
					}
				}
			}
			writeXml(document, path);
			System.out.println("保存XML成功");
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("保存XML失败!");
		}
	}
	@SuppressWarnings("unchecked")
	public static List<Index> getIndex(Document document){
		System.out.println("=================开始读取XML==================");
		List<Index> indexs=new ArrayList<Index>();
		List<Index> lists=document.selectNodes("//Model/Index");
		for (Iterator<Index> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			System.out.println(element.attributeValue("uuid")+"："+element.attributeValue("name")+"："+element.attributeValue("level")+"："+element.attributeValue("weight"));
			System.out.println();
			String uuid=element.attributeValue("uuid");
			String name=element.attributeValue("name");
			String level=element.attributeValue("level");
			String weight=element.attributeValue("weight");
			String desc=element.attributeValue("desc");
			List<Index> secondIndexs=getSecondIndex(element);
			List<Option> options = new ArrayList<Option>();
			Index index=new Index(uuid, name,level, weight,desc,options,secondIndexs);
			indexs.add(index);
		}
		return indexs;
	}
	@SuppressWarnings("unchecked")
	private static List<Index> getSecondIndex(Element element2){
		List<Index> indexs=new ArrayList<Index>();
		List<Index> lists=element2.selectNodes("Index");
		for (Iterator<Index> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			System.out.println(element.attributeValue("uuid")+"："+element.attributeValue("name")+"："+element.attributeValue("level")+"："+element.attributeValue("weight"));
			System.out.println();
			String uuid=element.attributeValue("uuid");
			String name=element.attributeValue("name");
			String level=element.attributeValue("level");
			String weight=element.attributeValue("weight");
			String desc=element.attributeValue("desc");
			List<Option> options = new ArrayList<Option>();
			List<Index> thirdIndexs=getThirdIndex(element);
			Index index=new Index(uuid, name,level, weight,desc,options,thirdIndexs);
			indexs.add(index);
		}
		return indexs;
	}
	@SuppressWarnings({ "unchecked"})
	private static List<Index> getThirdIndex(Element element3){
		List<Index> indexs=new ArrayList<Index>();
		List<Index> lists=element3.selectNodes("Index");
		for (Iterator<Index> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			System.out.println(element.attributeValue("uuid")+"："+element.attributeValue("name")+"："+element.attributeValue("level")+"："+element.attributeValue("weight"));
			System.out.println();
			String uuid=element.attributeValue("uuid");
			String name=element.attributeValue("name");
			String level=element.attributeValue("level");
			String weight=element.attributeValue("weight");
			String pushed=element.attributeValue("pushed");
			String desc=element.attributeValue("desc");
			List<Option> lists1 = element.selectNodes("option");
			List<Option> options = new ArrayList<Option>();
			for (Iterator<Option> iterator = lists1.iterator(); iterator.hasNext();) {
				Element pElem = (Element) iterator.next();
				//System.out.println(pElem.attributeValue("name")+":::::::"+pElem.attributeValue("value"));
				Option option=new Option();
				if(pElem.attributeValue("selected")!=null){
					option=new Option(pElem.attributeValue("name"), pElem.attributeValue("value"),pElem.attributeValue("selected"));
				}else{
					option=new Option(pElem.attributeValue("name"), pElem.attributeValue("value"));
				}
				options.add(option);
			}
			String insert=new String();
			List insertList=element.selectNodes("insert");
			for(Iterator insertiterator = insertList.iterator(); insertiterator.hasNext(); )
		    {
		        Element inelement = (Element)insertiterator.next();
		        insert=inelement.getTextTrim();
		    }
			Index index=new Index(uuid, name,level, weight,pushed,desc,options,insert);
			indexs.add(index);
		}
		return indexs;
	}
	 /**
     * @title 	输出xml文件
     * @author  孙尚飞  2017-8-3
     * @desc
     */
    public static void writeXml(Document document, String filePath) throws IOException {
        File xmlFile = new File(filePath);
        XMLWriter writer = null;
        try {
            if (xmlFile.exists())
                xmlFile.delete();
            writer = new XMLWriter(new FileOutputStream(xmlFile), OutputFormat.createPrettyPrint());
            writer.write(document);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }
    
    /**
     * @title 计算model的权重
     * @author 严树炜  2017-8-17
     */
    public static Integer getWeight(String url){
    	Document document = null;
		try {
			document = new SAXReader().read(url);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		List<Index> indexs=getIndex(document);
		Integer weight = 0;
		for(Index firstindex : indexs){
			for(Index secondindex : firstindex.getChirds()){
				for(Index thirdindex : secondindex.getChirds()){
					/*weight += Integer.parseInt(thirdindex.getWeight());*/
				}
			}	
		}
		System.out.println("权重总数为："+weight);
    	return weight;
    }
    /**
     * @title 读取基础模型
     * @author 严树炜  2017-8-17
     */
    public static String getXMLUuid(String url){
    	System.out.println(url);
    	File file=new File(url);
    	Document document = null;
		try {
			document = new SAXReader().read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		List<Index> lists=document.selectNodes("//Model");
		String uuid = "";
		for (Iterator<Index> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			uuid=element.attributeValue("uuid");
		}
		return uuid;
    }
}
