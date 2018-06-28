package com.credit.util.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.credit.bean.vo.addition.ModelIndex;
import com.credit.service.addition.ModelIndexService;
import com.credit.util.Excel.ReadOfData;

/**
 * @title 将模型数据从Excel导出到XML
 * @author 严树炜  2017-9-12
 */
@Component
@SuppressWarnings("all")
public class ModelToXML {

	private static ApplicationContext cxt;

	private static ModelIndexService modelIndexService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");

			modelIndexService = (ModelIndexService) cxt.getBean("modelIndexServiceBean");
		} catch (Exception e) {
		}
	}

	@Test
	public void Test01() throws Exception {
		System.out.println(modelIndexService);
	}
	
	@Test
	public void Test02(){ 
		modelIndexService.selectByName("客户资信管理内容", "first");//指标名，数据库对应字段
	}
	
	@Test
	public void Test03() throws Exception{
		String path = "c:\\Users\\Administrator\\Desktop\\模型数据表.xlsx";
		getIndexs(path);
	}
	
	public void getIndexs(String path) throws Exception {
		List<ModelIndex> lists = new ReadOfData().readModelIndexExcel(path);
		List<ModelIndex> newlists=new ArrayList<ModelIndex>();
		newlists=this.removeDuplicate(lists, 1);//去重
		List<Index> indexs=new ArrayList<Index>();
		for(ModelIndex mi:newlists){
			Index first=new Index();
			first.setName(mi.getFirst());
			first.setLevel("1");
			first.setUuid(UUID.randomUUID().toString().replace("-", ""));
			List<Index> chirden=new ArrayList<Index>();
			chirden=this.getSecondIndexs(mi.getFirst(),lists);
			first.setChirds(chirden);
			indexs.add(first);
		}
		saveXML(indexs, "c:\\Users\\Administrator\\Desktop\\Model.xml");//导出到XML
		//验证
		for(Index index:indexs){
			System.out.println("1:"+index.getName());
			List<Index> secondIndexs=index.getChirds();
			for(Index second:secondIndexs){
				System.out.println("2:"+second.getName());
				List<Index> thirdIndexs=second.getChirds();
				for(Index third:thirdIndexs){
					System.out.println("3:"+third.getName());
					for(Option option:third.getOptions()){
						System.out.println("o:"+option.getName());
					}
				}
			}
		}
		
	}
	
	private List<Index> getSecondIndexs(String first, List<ModelIndex> lists) {
		List<ModelIndex> newlists=new ArrayList<ModelIndex>();
		newlists=this.removeDuplicate(lists, 2);
		List<Index> secondindexs=new ArrayList<Index>();
		for(ModelIndex mi:newlists){
			String firstName=mi.getFirst();
			if(firstName.equals(first)){
				Index second=new Index();
				second.setName(mi.getSecond());
				second.setLevel("2");
				second.setUuid(UUID.randomUUID().toString().replace("-", ""));
				List<Index> chirden=new ArrayList<Index>();
				chirden=this.getThirdIndexs(mi.getSecond(),lists);
				second.setChirds(chirden);
				secondindexs.add(second);
			}
		}
		return secondindexs;
	}

	private List<Index> getThirdIndexs(String second, List<ModelIndex> lists) {
		List<Index> thirdindexs=new ArrayList<Index>();
		for(ModelIndex mi:lists){
			String secondName=mi.getSecond();
			if(secondName.equals(second)){
				Index third=new Index();
				third.setName(mi.getThird());
				third.setLevel("3");
				third.setUuid(UUID.randomUUID().toString().replace("-", ""));
				third.setWeight(mi.getWight());
				List<Option> options=new ArrayList<Option>();
				Option option1=new Option(mi.getOne(), "1");
				Option option2=new Option(mi.getPoint75(), "0.75");
				Option option3=new Option(mi.getHalf(), "0.5");
				Option option4=new Option(mi.getPoint25(), "0.25");
				Option option5=new Option(mi.getZero(), "0");
				if(mi.getOne()!=null) options.add(option1);
				if(mi.getPoint75()!=null) options.add(option2);
				if(mi.getHalf()!=null) options.add(option3);
				if(mi.getPoint25()!=null) options.add(option4);
				if(mi.getZero()!=null) options.add(option5);
				third.setOptions(options);
				thirdindexs.add(third);
			}
		}
		return thirdindexs;
	
	}
	
	private static ArrayList<ModelIndex> removeDuplicate(List<ModelIndex> indexs,final int level) {
        Set<ModelIndex> set = new TreeSet<ModelIndex>(new Comparator<ModelIndex>() {
            public int compare(ModelIndex o1, ModelIndex o2) {
            	if(level==1){
            		 return o1.getFirst().compareTo(o2.getFirst());
            	}else {
            		return o1.getSecond().compareTo(o2.getSecond());
            	}
               
            }
        });
        set.addAll(indexs);
        return new ArrayList<ModelIndex>(set);
    }
	
	public static void saveXML(List<Index> indexs,String path) throws Exception {
		try{
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("Model");
			for(Index firstIndex : indexs) {
				Element firstNode = root.addElement("Index");
				firstNode.addAttribute("uuid", firstIndex.getUuid());
				firstNode.addAttribute("name", firstIndex.getName());
				firstNode.addAttribute("level", firstIndex.getLevel());
				for(Index secondIndex : firstIndex.getChirds()){
					Element secondNode = firstNode.addElement("Index");
					secondNode.addAttribute("uuid", secondIndex.getUuid());
					secondNode.addAttribute("name", secondIndex.getName());
					secondNode.addAttribute("level", secondIndex.getLevel());
					for(Index thirdIndex : secondIndex.getChirds()){
						Element thirdNode = secondNode.addElement("Index");
						thirdNode.addAttribute("uuid", thirdIndex.getUuid());
						thirdNode.addAttribute("name", thirdIndex.getName());
						thirdNode.addAttribute("level", thirdIndex.getLevel());
						thirdNode.addAttribute("weight", thirdIndex.getWeight());
						thirdNode.addAttribute("pushed", "true");//视情况添加
						for(Option option:thirdIndex.getOptions()){
							Element optionNode = thirdNode.addElement("option");
							optionNode.addAttribute("name", option.getName());
							optionNode.addAttribute("value", option.getValue());
						}
					}
				}
			}
			writeXml(document, path);//生成XML文件
			System.out.println("保存XML成功");
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("保存XML失败!");
		}
	}
	
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
	@Test
	public void XmlDataInsert() throws Exception{
		String path = "c:\\Users\\Administrator\\Desktop\\模型数据表.xlsx";
		List<Index> indexs = new ArrayList<Index>();
		List<ModelIndex> lists = new ReadOfData().readModelIndexExcel(path);
		List<ModelIndex> newlists=new ArrayList<ModelIndex>();
		newlists=this.removeDuplicate(lists, 2);
		List<String> firstNames = new ArrayList<String>();
		if(newlists!=null){
			for (ModelIndex mi : newlists) {
				if(mi.getFirst()!=null && !"".equals(mi.getFirst())) firstNames.add(mi.getFirst());//获取所有的一级指标名
			}
		}
		for(String firstIndexs : firstNames){//遍历一级指标
			List<Index> indexs1 = new ArrayList<Index>();//二级指标容器
			Index index = new Index();
			index.setName(firstIndexs);
			index.setUuid(UUID.randomUUID().toString().replace("-", ""));
			index.setLevel("1");
			List<String> mds = modelIndexService.selectByUpperName(firstIndexs,"first","second");
			for (String secondIndex : mds) {//遍历二级指标
				List<Index> indexs2 = new ArrayList<Index>();//三级指标容器
				Index index2 = new Index();
				index2.setName(secondIndex);
				index2.setUuid(UUID.randomUUID().toString().replace("-", ""));
				index2.setLevel("2");
				indexs1.add(index2);
				List<ModelIndex> mds2 = modelIndexService.selectByName(secondIndex, "second");
				for (ModelIndex thirdIndexs : mds2) {//遍历三级指标
					String third = thirdIndexs.getThird();
					List<Option> options = new ArrayList<Option>();
					Option option = new Option();
					Option option2 = new Option();
					Option option3 = new Option();
					if(thirdIndexs.getOne()!=null){
						option.setName(thirdIndexs.getOne());
						option.setValue("1");
						options.add(option);
					}
					if(thirdIndexs.getHalf()!=null){
						option2.setName(thirdIndexs.getHalf());
						option2.setValue("0.5");
						options.add(option2);
					}
					if(thirdIndexs.getZero()!=null){
						option3.setName(thirdIndexs.getZero());
						option3.setValue("0");
						options.add(option3);
					}
					Index index3 = new Index();
					index3.setName(third);
					index3.setUuid(UUID.randomUUID().toString().replace("-", ""));
					index3.setLevel("3");
					index3.setOptions(options);
					index3.setWeight(thirdIndexs.getWight());
					indexs2.add(index3);
				}
				index2.setChirds(indexs2);
			}
			index.setChirds(indexs1);
			indexs.add(index);
		}
		saveXML(indexs, "c:\\Users\\Administrator\\Desktop\\Model.xml");//导出到XML
	}
	
	
	

}
