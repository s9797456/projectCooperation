package junit.test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.credit.model.addition.Model;
import com.credit.model.enterprise.EntResult;
import com.credit.model.enterprise.ProcessState;
import com.credit.service.addition.ModelService;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.enterprise.ProcessStateService;
import com.credit.util.WebUtil;
import com.credit.util.model.Index;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;
import com.credit.util.properties.BusinessUtil;

public class TestOfXML2 {
	private static ApplicationContext cxt;

	private static EntResultService entResultService;
	private static ModelService modelService;
	private static ProcessStateService processStateService;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			entResultService=(EntResultService) cxt.getBean("entResultServiceBean");
			modelService=(ModelService) cxt.getBean("modelServiceBean");
			processStateService=(ProcessStateService) cxt.getBean("processStateServiceBean");
		} catch (Exception e) {
		}
	}
	@Test
	public void Test05() throws Exception {
		String path="C://Users//Administrator//Desktop//model//score";
		File file = new File(path);
		int a=0;
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				System.out.println("文件夹是空的!");
				return;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						System.out.println("文件夹:" + file2.getAbsolutePath());
					} else {
						System.out.println("文件:" + file2.getAbsolutePath());
						String url=file2.getAbsolutePath();
						String path1 = url.substring(url.lastIndexOf("\\")+1);
						String resultID=path1.substring(0, path1.indexOf("."));
						EntResult result=entResultService.selectByPrimaryKey(resultID);
						if(result==null){
							a++;
							File urlfile=new File(url);
							urlfile.delete();
						}
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
		System.out.println(a);
	}
	@Test
	public void Test04() throws Exception {
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		List<EntResult> results = entResultService.selectAll();
		for(EntResult result : results){
		//EntResult result=entResultService.selectByPrimaryKey("97620aa0f875403c96b3b605952c17f6");
			if(result.getScorexmlurl()!=null){
				File file=new File(adr+root+result.getScorexmlurl());
				if(file.exists()){
					Document document = new SAXReader().read(file);
					List<Index> Indexs=ModelUtil.getIndex(document);
					double score=0;
					for(Index firstindex : Indexs){
						for(Index secondindex : firstindex.getChirds()){
							for(Index thirdindex : secondindex.getChirds()){
									for(Option option : thirdindex.getOptions()){
										if(option.getSelected()!=null&&option.getSelected().equals("true")){
											double value=Double.parseDouble(option.getValue());
											double weight=Double.parseDouble(thirdindex.getWeight());
											double temp=value*weight;
											score+=temp;
											System.out.println("++value="+value+"++weight="+weight+"++temp="+temp+"++score="+score);
										}
									}
							}
						}	
					}
					String level=WebUtil.getLevelByScore(score);
					result.setPreliminaryscore(new BigDecimal(score));
					result.setPreliminarylevel(level);
					result.setFinalscore(new BigDecimal(score));
					result.setFinallevel(level);
					entResultService.updateByPrimaryKey(result);
				}else{
					updateResult1(result);
				}
			}else{
				updateResult1(result);
			}
			
			

			if(result.getInputxmlurl()!=null){
				File file=new File(adr+root+result.getInputxmlurl());
				if(!file.exists()){
					updateResult2(result);
				}
			}else{
				updateResult2(result);
			}
		
		}
/*d57542b8557346e9b5dfcd3d956c2078
40092718f6934db4b8d6b294a91b9950
21cbc8982894414e8db6e330f2c6b8c4
2f6063370d284a53bf00e93522715baa
80169307529a4f7495a4d27471270c7d
a9803582d92a4eed8e446fd4d25a7103
074c416c6a814da1a73ad45e2a869948
339f4f9d0537472e9dba8629a2dac712
cfb94e09f2d54f63bd9bbf20a7a10cd1
cad49b6b06694cf88608b301e0ca112c
9e9658ba8b7e430187cb86fad67c0a72
18292d76727648dbbcf0be980ea3ef77
0b65ab727e164cbe92a2bd9daec66ebb
abc99615feb340fcab6f1301749b44bc
81f1db341962442fa28a5b35086cbfed
c4bc802e62ed4f04a16298c47e9fd0f6
97620aa0f875403c96b3b605952c17f6
7b58b97c076e4d38b7e329762180d026
30a9490c9bbc4a8fbe23091f71297ddf
4786410b96214030aa8acc83a06429a3
900230422b864b429c952a07b91707b3*/
	}
	private void updateResult1(EntResult result) {
		result.setScorexmltime(null);
		result.setScorexmlurl(null);
		result.setFinallevel(null);
		result.setFinalscore(null);
		result.setPreliminarylevel(null);
		result.setPreliminaryscore(null);
		entResultService.updateByPrimaryKey(result);
		ProcessState process=processStateService.selectByEntBaseInfoKey(result.getEntid());
		if(process!=null){
			process.setScorestate(0);
			processStateService.updateByPrimaryKeySelective(process);
		}
	}
	private void updateResult2(EntResult result) {
		result.setInputxmltime(null);
		result.setInputxmlurl(null);
		entResultService.updateByPrimaryKey(result);
		ProcessState process=processStateService.selectByEntBaseInfoKey(result.getEntid());
		if(process!=null){
			process.setScorestate(0);
			process.setApplyreportstate(0);
			processStateService.updateByPrimaryKeySelective(process);
		}
	}
	@Test
	public void Test01() throws Exception {
		String path="D://Model//InputXml";
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				System.out.println("文件夹是空的!");
				return;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						System.out.println("文件夹:" + file2.getAbsolutePath());
					} else {
						System.out.println("文件:" + file2.getAbsolutePath());
						Document document = new SAXReader().read(file2);
						String str=ModelUtil.getModelID(document);
						String modelID=str.split("#")[0];
						String modelName=str.split("#")[1];
						System.out.println("模型ID"+modelID);
						System.out.println("模型名"+modelName);
						List<Index> Indexs=ModelUtil.getThreeIndex(document);
						List<Index> modelIndexs=getModelIndex(modelID);
						for(Index firstindex : modelIndexs){
							if(firstindex.getUuid().indexOf("-")!=-1){
								firstindex.setUuid(firstindex.getUuid().replace("-", "").toString());
							}
							for(Index secondindex : firstindex.getChirds()){
								if(secondindex.getUuid().indexOf("-")!=-1){
									secondindex.setUuid(secondindex.getUuid().replace("-", "").toString());
								}
								for(Index thirdindex : secondindex.getChirds()){
									String thirdindexID="";
									if(thirdindex.getUuid().indexOf("-")!=-1){
										thirdindexID=thirdindex.getUuid().replace("-", "").toString();
									}else{
										thirdindexID=thirdindex.getUuid();
									}
									//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+thirdindex.getName()+"："+thirdindexID);
									for(Index index : Indexs){
										String indexID="";
										if(index.getUuid().indexOf("-")!=-1){
											indexID=index.getUuid().replace("-", "").toString();
										}else{
											indexID=index.getUuid();
										}
										thirdindex.setUuid(thirdindexID);
										//System.out.println(index.getName()+"："+index.getUuid());
										if(index.getName().equals(thirdindex.getName())&&indexID.equals(thirdindexID)){
											//System.out.println(thirdindex.getName());
											List<Option> options=index.getOptions();
											List<Option> option2s=thirdindex.getOptions();
											String value="";
											for(Option option : options){
												if(option.getSelected()!=null&&option.getSelected().equals("true")){
													//System.out.println(option.getName());
													value=option.getValue();
												}
											}
											for(Option option2 : option2s){
												if(option2.getValue().equals(value)){
													option2.setSelected("true");
												}
											}
										}
									}
								}
							}	
						}
						String url=file2.getAbsolutePath();
						String path1 = url.substring(url.lastIndexOf("\\")+1);
						System.out.println(path1);
						saveXML(modelIndexs, "C://Users//Administrator//Desktop//model//input//"+path1,modelID,modelName);
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
		
	}
	@Test
	public void Test02() throws Exception {
		String path="D://Model//ScoreXml//";
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				System.out.println("文件夹是空的!");
				return;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						System.out.println("文件夹:" + file2.getAbsolutePath());
					} else {
						System.out.println("文件:" + file2.getAbsolutePath());
						Document document = new SAXReader().read(file2);
						String str=ModelUtil.getModelID(document);
						String modelID=str.split("#")[0];
						String modelName=str.split("#")[1];
						System.out.println("模型ID"+modelID);
						System.out.println("模型名"+modelName);
						List<Index> Indexs=ModelUtil.getThreeIndex(document);
						List<Index> modelIndexs=getModelIndex(modelID);
						for(Index firstindex : modelIndexs){
							if(firstindex.getUuid().indexOf("-")!=-1){
								firstindex.setUuid(firstindex.getUuid().replace("-", "").toString());
							}
							for(Index secondindex : firstindex.getChirds()){
								if(secondindex.getUuid().indexOf("-")!=-1){
									secondindex.setUuid(secondindex.getUuid().replace("-", "").toString());
								}
								for(Index thirdindex : secondindex.getChirds()){
									String thirdindexID="";
									if(thirdindex.getUuid().indexOf("-")!=-1){
										thirdindexID=thirdindex.getUuid().replace("-", "").toString();
									}else{
										thirdindexID=thirdindex.getUuid();
									}
									int a=0;
									//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+thirdindex.getName()+"："+thirdindexID);
									for(Index index : Indexs){
										String indexID="";
										if(index.getUuid().indexOf("-")!=-1){
											indexID=index.getUuid().replace("-", "").toString();
										}else{
											indexID=index.getUuid();
										}
										thirdindex.setUuid(thirdindexID);
										//System.out.println(index.getName()+"："+index.getUuid());
										if(index.getName().equals(thirdindex.getName())&&indexID.equals(thirdindexID)){
											//System.out.println(thirdindex.getName());
											List<Option> options=index.getOptions();
											List<Option> option2s=thirdindex.getOptions();
											String value="";
											int b=0;
											for(Option option : options){
												if(option.getSelected()!=null&&option.getSelected().equals("true")){
													//System.out.println(option.getName());
													value=option.getValue();
												}
											}
											for(Option option2 : option2s){
												if(option2.getValue().equals(value)){
													option2.setSelected("true");
												}else{
													b++;
												}
											}
											if(b==option2s.size()){
												for(Option option2 : option2s){
													if(option2.getValue().equals("0")){
														option2.setSelected("true");
													}
												}
											}
										}else{
											a++;
										}
									}
									System.out.println("a："+a+"===="+Indexs.size());
									if(a==Indexs.size()){
										System.out.println(thirdindex.getName());
										for(Option option2 : thirdindex.getOptions()){
											if(option2.getValue().equals("0")){
												option2.setSelected("true");
											}
										}
									}
								}
							}	
						}
						String url=file2.getAbsolutePath();
						String path1 = url.substring(url.lastIndexOf("\\")+1);
						System.out.println(path1);
						saveXML(modelIndexs, "C://Users//Administrator//Desktop//model//score//"+path1,modelID,modelName);
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
		
	}
	private List<Index> getModelIndex(String modelID) {
		List<Index> indexs=new ArrayList<Index>();
		String path="D://Model//Xml//"+modelID+".xml";
		File file = new File(path);
		if (file.exists()) {
			try {
				Document document = new SAXReader().read(file);
				indexs=ModelUtil.getIndex(document);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(modelID+"文件不存在!");
		}
		return indexs;
	}

	public static boolean saveXML(List<Index> indexs,String path, String modelID, String modelName) throws Exception {
		boolean flag=true;
		try{
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("Model");
			root.addAttribute("uuid", modelID);
			root.addAttribute("name",modelName);
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
						thirdNode.addAttribute("write",thirdIndex.getWrite());
						thirdNode.addAttribute("pushed", thirdIndex.getPushed());
						if(thirdIndex.getWrite()!=null&&thirdIndex.getWrite().equals("true")){
							thirdNode.addAttribute("pushed", "true");
						}
						for(Option option:thirdIndex.getOptions()){
							Element optionNode = thirdNode.addElement("option");
							optionNode.addAttribute("name", option.getName());
							optionNode.addAttribute("value", option.getValue());
							if(option.getSelected()!=null){
								thirdNode.addAttribute("pushed", "true");
								optionNode.addAttribute("selected", option.getSelected());
							}
						}
						if(!"".equals(thirdIndex.getInsert())){
							Element insertNode = thirdNode.addElement("insert");
							insertNode.addText(thirdIndex.getInsert());
						}
					}
				}
			}
			ModelUtil.writeXml(document, path);
			System.out.println("保存XML成功");
		}catch(Exception e) {
			e.printStackTrace();
			flag=false;
			throw new Exception("保存XML失败!");
		}
		return flag;
	}
	
	@Test
	public void Test03() throws Exception {
		String path="D://Model//InputXml//00f69a68e82040ae9797f28c398a4503.xml";
		File file = new File(path);
		Document document = new SAXReader().read(file);
		List<Index> indexs=ModelUtil.getIndex(document);
		double score=0;
		for(Index firstindex : indexs){
			for(Index secondindex : firstindex.getChirds()){
				for(Index thirdindex : secondindex.getChirds()){
					score+=Double.parseDouble(thirdindex.getWeight());
				}
			}	
		}
		System.out.println(score);
	}
}