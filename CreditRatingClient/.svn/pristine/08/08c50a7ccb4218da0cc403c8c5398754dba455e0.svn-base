package junit.test;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.credit.model.enterprise.EntBaseInfo;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.member.CustomerService;
import com.credit.util.model.Index;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;

public class TestOfXML {
	private static ApplicationContext cxt;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");

		} catch (Exception e) {
		}
	}

	@Test
	public void TestOfReadXML()  throws Exception{
		File file=new File("D:\\model.xml");
		if(file.exists()){
			System.out.println("文件存在");
		}
		try {
			Document document = new SAXReader().read(file);
			List<Index> indexs=ModelUtil.getPushedIndex(document);
			for(Index index3 : indexs){
				System.out.println("--------------"+index3.getUuid()+"------"+index3.getName()+"------"+index3.getLevel()+"-------"+index3.getWrite());
				System.out.println("++++++++++++++++++++"+index3.getInsert());
				List<Option> options=index3.getOptions();
				for(Option option : options){
					System.out.println("--------------------"+option.getName()+"----------"+option.getValue()+"--------------"+option.getSelected());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	
}