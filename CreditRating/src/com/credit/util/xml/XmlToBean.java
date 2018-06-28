package com.credit.util.xml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
 
/**
 * Created by GongHuaigu on 2017/9/12.
 */
public class XmlToBean {
 
    public static Object xmlToBean(String xmlPath,Class<?> load) throws JAXBException, IOException{
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(new BufferedReader(new InputStreamReader(new FileInputStream(xmlPath), "UTF-8")));
        return object;
    }
 
 
}