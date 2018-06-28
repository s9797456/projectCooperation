package com.credit.util.html2pdf;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


import com.credit.exception.PDFException;
import com.credit.util.html2pdf.component.PDFHeaderFooter;
import com.credit.util.html2pdf.component.builder.MyFontProvider;
import com.credit.util.html2pdf.component.builder.PDFBuilder;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class Html2PDFUtil {
	/**
	 * @title 
	 * @author  孙尚飞  2017-9-29
	 * @desc
	 * @param data   ： 模型中要加载的数据
	 * @param pdf_path    ：生成的PDF路径（完整路径包括pdf文件名）例： c://test//new.pdf
	 * @param template_path  ：模板路径（不包括模板名） 例：C:/test/
	 * @param template_name ：模板文件名（以ftl 结尾的模板文件） 例：hello.ftl
	 */
	public  static boolean createPDF(Object data, String pdf_path,String template_path,String template_name){
		System.out.println(pdf_path);
		System.out.println(template_path);
		System.out.println(template_name);
		boolean flag=true;
		//设置文档大小
		Document document = new Document(PageSize.A4);
		//设置自定义PDF页眉页脚工具类
		PDFHeaderFooter headerFooter=new PDFHeaderFooter();
		File pdf=new File(pdf_path);
		if(!pdf.getParentFile().exists()){
			pdf.getParentFile().mkdirs();
		}
		File templatepath=new File(template_path);
		if(!templatepath.exists()){
			templatepath.mkdirs();
		}
		FileOutputStream outputStream=null;
		try {
			//设置输出路径
			outputStream=new FileOutputStream(pdf_path);
			Configuration config = new Configuration();
			config.setDefaultEncoding("UTF-8");
			config.setDirectoryForTemplateLoading(new File(template_path));
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			//config.setLogTemplateExceptions(false);
			Template template = config.getTemplate(template_name);
			StringWriter writer = new StringWriter();
			template.setEncoding("UTF-8");
			template.process(data, writer);
			writer.flush();
			String htmlData = writer.toString();
			
			//生成word 文档开始
			String word_path=pdf_path.replace(".pdf", ".doc");
			writeWordFile(htmlData, word_path);
			//生成word 文档结束
			
			
			PdfWriter pdfwriter = PdfWriter.getInstance(document, outputStream);
			//设置页眉页脚
			PDFBuilder builder = new PDFBuilder(headerFooter,data);
			builder.setPresentFontSize(10);
			pdfwriter.setPageEvent(builder);

			//输出为PDF文件
			document.open();
			System.out.println("PDF文件生成开始");
			//System.out.println(htmlData);
			InputStream htmlInput = new ByteArrayInputStream(htmlData.getBytes("UTF-8"));  
		    XMLWorkerHelper.getInstance().getDefaultCssResolver(true);  
			XMLWorkerHelper.getInstance().parseXHtml(pdfwriter,document,htmlInput,
					XMLWorkerHelper.class.getResourceAsStream("/default.css"),
					Charset.forName("UTF-8"),new MyFontProvider());
			System.out.println("PDF文件生成结束");
		} catch (Exception e) {
			flag=false;
			System.out.println("PDF文件生成异常");
			e.printStackTrace();
			throw new PDFException("PDF文件生成异常",e);
		}  finally {
			document.close();
		}
		return flag;
	}

	public static void writeWordFile(String content,String path) { 
		try {
			byte b[] = content.getBytes("UTF-8"); 
			ByteArrayInputStream bais = new ByteArrayInputStream(b); 
			POIFSFileSystem poifs = new POIFSFileSystem(); 
			DirectoryEntry directory = poifs.getRoot(); 
			DocumentEntry documentEntry = directory.createDocument("WordDocument", bais); 
			FileOutputStream ostream = new FileOutputStream(path); 
			poifs.writeFilesystem(ostream); 
			bais.close(); 
			ostream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	} 
}
