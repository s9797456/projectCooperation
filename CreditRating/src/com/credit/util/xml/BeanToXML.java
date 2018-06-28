package com.credit.util.xml;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class BeanToXML {
	
	public static String beanToXml(Object object,Class<?> load)throws JAXBException{
        JAXBContext context=JAXBContext.newInstance(load);
        Marshaller marshaller=context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
        StringWriter writer=new StringWriter();
        marshaller.marshal(object,writer);
        return writer.toString();
        
        
    }
	
	
	
 
 
        /*写入到xml*/
       /* String xmlPath="D:\\testConfig.xml";
        BufferedWriter bfw=new BufferedWriter(new FileWriter(new File(xmlPath)));
        bfw.write(str);
        bfw.close();*/
	
}
