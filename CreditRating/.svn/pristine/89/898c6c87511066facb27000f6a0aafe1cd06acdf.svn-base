package junit.test;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.beans.swing.TestBean;

public class TestDataSource {

	 private static String configLocation = "classpath:beans.xml";  
	    private static ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);  
	    //1、Spring自带依赖注入注解  
	     @Test  
	    public void testRequiredForXmlSetterInject() {  
	        ProxoolDataSource testBean = ctx.getBean("dataSource", org.logicalcobwebs.proxool.ProxoolDataSource.class);  
	       System.out.println("hello "+testBean);  
	    }  
}