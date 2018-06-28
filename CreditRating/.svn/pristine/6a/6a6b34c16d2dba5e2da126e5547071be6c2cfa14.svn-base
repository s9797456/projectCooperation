package junit.test;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.credit.bean.vo.enterprise.FinanceVo;
import com.credit.service.enterprise.EntResultService;
import com.credit.util.HistoricalDataUtil;
import com.credit.util.financeExecl.ResolveFinance;
import com.credit.util.model.Index;
import com.credit.util.model.IndexRateVo;
import com.credit.util.model.Option;



public class JPASpringTestOfXML {
	private static ApplicationContext cxt;
	
	private static EntityManagerFactory entityManagerFactory;
	
	@Resource 
	private static EntResultService entResultService;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			entResultService = (EntResultService) cxt.getBean("entResultServiceBean");
			
			entityManagerFactory = (EntityManagerFactory) getBean("entityManagerFactory");
			EntityManager em = entityManagerFactory.createEntityManager();
			TransactionSynchronizationManager.bindResource(entityManagerFactory,new EntityManagerHolder(em));
			
		} catch (Exception e) {
			System.out.println("-------------err-----------------");
			System.out.println(e);		
			System.out.println("--------------err----------------");
		}
	}
	
	protected static Object getBean(String beanName) {
		return cxt.getBean(beanName);
	}

	public void tearDown() throws Exception {//解决延迟加载
		EntityManagerHolder holder = (EntityManagerHolder)TransactionSynchronizationManager.getResource(entityManagerFactory);
		EntityManager em = holder.getEntityManager();
		em.flush();
		TransactionSynchronizationManager.unbindResource(entityManagerFactory);
		EntityManagerFactoryUtils.closeEntityManager(holder.getEntityManager());
	}

	
	@Test
	public void TestOfFinance()  throws Exception{
		String path="D:\\20170926104511168240.xlsx";
		List<FinanceVo> lists =ResolveFinance.ReadExecl(path);
		//ExcelShower.getExcelInfo(path);
		for(FinanceVo finance:lists){
			System.out.println(finance.getTable()+"："+finance.getName()+"："+finance.getYear()+"："+finance.getValue());
		}
	}
	@Test
	public void TestOfIndexRate()  throws Exception{
		System.out.println(entResultService);
		String path="C:\\tomcat7\\webapps\\CreditRating\\UploadFiles\\Model\\ScoreXml\\02071c206409472ab4b07a7a920eeb45.xml";
		List<IndexRateVo>indexs=entResultService.getIndexRate(path);
		for(IndexRateVo irv : indexs){
			System.out.println(irv.getIndexName()+"-------"+irv.getRateValue()+"+++++"+irv.getDescription());
		}
	}
	
	@Test
	public void TestOfReadXML()  throws Exception{
		try {
			Document document = new SAXReader().read(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("D:/PushSaveModel.xml"));
			 //获取根节点元素对象
			/*List<Index> indexs=new ArrayList<Index>();
			List<Index>lists=document.selectNodes("//Index");
			for (Iterator<Index> iter = lists.iterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				System.out.println(element.attributeValue("uuid")+"："+element.attributeValue("name")+"："+element.attributeValue("level")+"："+element.attributeValue("weight"));
				System.out.println();
				String uuid=element.attributeValue("uuid");
				String name=element.attributeValue("name");
				String level=element.attributeValue("level");
				String weight=element.attributeValue("weight");
				List<Option> lists1 = element.selectNodes("option");
				List<Option> options = new ArrayList<Option>();
				for (Iterator<Option> iterator = lists1.iterator(); iterator.hasNext();) {
					Element pElem = (Element) iterator.next();
					System.out.println(pElem.attributeValue("name")+":::::::"+pElem.attributeValue("value"));
					Option option=new Option(pElem.attributeValue("name"), pElem.attributeValue("value"));
					options.add(option);
				}

				Index index=new Index(uuid, name,level, weight,options);
				indexs.add(index);
			}
			for(Index index : indexs){
				System.out.println(index.toString());
			}*/
			List<Index> indexs=this.getFirstIndex(document);
			for(Index index1 : indexs){
				System.out.println(index1.getUuid()+"------"+index1.getName()+"------"+index1.getLevel());
				List<Index> indexs2=index1.getChirds();
				for(Index index2 : indexs2){
					System.out.println("-------"+index2.getUuid()+"------"+index2.getName()+"------"+index2.getLevel());
					List<Index> indexs3=index2.getChirds();
					for(Index index3 : indexs3){
						System.out.println("--------------"+index3.getUuid()+"------"+index3.getName()+"------"+index3.getLevel());
						System.out.println("++++++++++++++++++++"+index3.getInsert());
						List<Option> options=index3.getOptions();
						for(Option option : options){
							System.out.println("--------------------"+option.getName()+"----------"+option.getValue()+"--------------"+option.getSelected());
						}
					}
				}
			}
			/*Map<String, List<Index>> map = new HashMap<String, List<Index>>();
			String firstname=new String();
			for(Index index1 : indexs){
				firstname=index1.getName();
				for(Index index2 : index1.getChirds()){
						map.put(firstname, index2.getChirds());
				}
			}
			System.out.println(map.get("制造业行业特征"));*/
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void TestOfUpdateXML()  throws Exception{
		try {
			Document document = new SAXReader().read(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("com/config/model.xml"));
			//Element index2 = document.getRootElement().element("Index").element("Index");

			/*Element index2 = document.getRootElement();
			@SuppressWarnings("static-access")
			Element node = this.parse(index2 , "uuid" , "1b9d02afd17947bbac78213a71d48808");
			System.out.println(node.attributeValue("name"));*/
			
			/*Element node = (Element) document.selectSingleNode("Index[@uuid='1b9d02afd17947bbac78213a71d48805']");
			System.out.println(node.getName());*/
//			Document doc=node.getDocument();
			/*List<Option> lists1 = node.selectNodes("option");
			List<Option> options = new ArrayList<Option>();
			for (Iterator<Option> iterator = lists1.iterator(); iterator.hasNext();) {
				Element pElem = (Element) iterator.next();
				System.out.println(pElem.attributeValue("name")+":::::::"+pElem.attributeValue("value"));
				Option option=new Option(pElem.attributeValue("name"), pElem.attributeValue("value"));
				options.add(option);
			}*/
			List<Index> indexs=this.getFirstIndex(document);
			for(Index firstindex : indexs){
				for(Index secondindex : firstindex.getChirds()){
					for(Index thirdindex : secondindex.getChirds()){
						if(thirdindex.getUuid().equals("1b9d02afd17947bbac78213a71d48805")){
							for(Option option : thirdindex.getOptions()){
								if(option.getValue().equals("1")){
									option.setSelected("true");
								}
							}
						}
					}
				}	
			}
			this.saveXML(indexs,"D://SaveModel.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	private void saveXML(List<Index> indexs,String path) throws Exception {
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
			this.writeXml(document, path);
			System.out.println("保存成功");
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("保存失败!");
		}
	}
	/**
	 * @title 获取指定属性的节点
	 * @author  孙尚飞  2017-8-3
	 * @desc
	 */
	public Element parse(Element node , String type , String val) {
	    for (Iterator iter = node.elementIterator(); iter.hasNext();) {
	        Element element = (Element) iter.next();
	        Attribute name = element.attribute(type);
	        if (name != null) {
	            String value = name.getValue();
	            if (value != null && val.equals(value)){
	            	System.out.println("===========temp3==========="+value+"======================"+element.attributeValue("name"));
	            	return element;
	            }else{
	            	parse(element , type , val);
	            }
	        }
	    }
	    return null;
	}
	/**
	 * @title 获取XML模型数据
	 * @author  孙尚飞  2017-8-3
	 * @desc
	 */
	public List<Index> getFirstIndex(Document document){
		List<Index> indexs=new ArrayList<Index>();
		List<Index>lists=document.selectNodes("//Model/Index");
		for (Iterator<Index> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			//System.out.println(element.attributeValue("uuid")+"："+element.attributeValue("name")+"："+element.attributeValue("level")+"："+element.attributeValue("weight"));
			//System.out.println();
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
	public List<Index> getSecondIndex(Element element2){
		List<Index> indexs=new ArrayList<Index>();
		List<Index>lists=element2.selectNodes("Index");
		for (Iterator<Index> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			//System.out.println(element.attributeValue("uuid")+"："+element.attributeValue("name")+"："+element.attributeValue("level")+"："+element.attributeValue("weight"));
			//System.out.println();
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
	@SuppressWarnings("unchecked")
	public List<Index> getThirdIndex(Element element3){
		List<Index> indexs=new ArrayList<Index>();
		List<Index>lists=element3.selectNodes("Index");
		for (Iterator<Index> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			//System.out.println(element.attributeValue("uuid")+"："+element.attributeValue("name")+"："+element.attributeValue("level")+"："+element.attributeValue("weight"));
			//System.out.println();
			String uuid=element.attributeValue("uuid");
			String name=element.attributeValue("name");
			String level=element.attributeValue("level");
			String weight=element.attributeValue("weight");
			String desc=element.attributeValue("desc");
			List<Option> lists1 = element.selectNodes("option");
			List<Option> options = new ArrayList<Option>();
			for (Iterator<Option> iterator = lists1.iterator(); iterator.hasNext();) {
				Element pElem = (Element) iterator.next();
				//System.out.println(pElem.attributeValue("name")+"："+pElem.attributeValue("value")+"："+pElem.attributeValue("selected"));
				Option option=new Option();
				if(pElem.attributeValue("selected")!=null){
					option=new Option(pElem.attributeValue("name"), pElem.attributeValue("value"),pElem.attributeValue("selected"));
				}else{
					option=new Option(pElem.attributeValue("name"), pElem.attributeValue("value"));
				}
				options.add(option);
			}
			String insert=new String();
			List ecList=element.selectNodes("insert");
			for(Iterator insertiterator = ecList.iterator(); insertiterator.hasNext(); )
		    {
		        Element inelement = (Element)insertiterator.next();
		        System.out.println(inelement.getTextTrim());
		        insert=inelement.getTextTrim();
		    }
			Index index=new Index(uuid, name,level, weight,desc,options,insert);
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
}