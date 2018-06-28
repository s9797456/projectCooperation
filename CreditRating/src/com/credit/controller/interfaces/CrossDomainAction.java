package com.credit.controller.interfaces;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.credit.bean.enterprise.HistoricalData;
import com.credit.bean.vo.enterprise.HistoricalVO;
import com.credit.controller.addition.ModelManageAction;
import com.credit.service.enterprise.HistoricalDataService;
import com.credit.util.properties.BusinessUtil;


@Controller
@RequestMapping("/cross")
public class CrossDomainAction {
	private static final Logger logger = Logger.getLogger(ModelManageAction.class);
	private String moduleName = "管理员操作情况";

	
	@Resource(name = "historicalDataServiceBean")
	private HistoricalDataService historicalDataService;
	/**
	 * @title 跨域接收文件
	 * @author  孙尚飞  2017-12-19
	 * @desc
	 */
	@RequestMapping("/receiveCrossFile")
	@ResponseBody
	public Map<String,Object> receiveCrossFile(HttpServletRequest request) {
		String relativePath=request.getParameter("filepath");
		String path=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root");
		logger.info(moduleName + "[跨域文件接收开始] "+relativePath);
		File relativefile=new File(path+relativePath);
		//判断目标文件所在的目录是否存在  
        if(!relativefile.getParentFile().exists()) {  
            //如果目标文件所在的目录不存在，则创建父目录  
            System.out.println("目标文件所在目录不存在，准备创建它！");  
            if(!relativefile.getParentFile().mkdirs()) {  
                System.out.println("创建目标文件所在目录失败！");  
            }else{
            	System.out.println("创建目标文件所在目录成功！");  
            }  
        }  
		Map<String, Object> msgMap = new HashMap<String, Object>();
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		//创建一个通用的多部分解析器  
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		//判断 request 是否有文件上传,即多部分请求  
		if(multipartResolver.isMultipart(request)){  
			//转换成多部分request    
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
			//取得request中的所有文件名  
			Iterator<String> iter = multiRequest.getFileNames();  
			while(iter.hasNext()){  
				//取得上传文件  
				MultipartFile file = multiRequest.getFile(iter.next());  
				if(file != null){  
					//取得当前上传文件的文件名称  
					String myFileName = file.getOriginalFilename();  
					//如果名称不为“”,说明该文件存在，否则说明该文件不存在  
					if(myFileName.trim() !=""){
						File localFile1 = new File(path+relativePath); 
						// 转存文件  
						try {
							file.transferTo(localFile1);
							msgMap.put("success", true);
							logger.info(moduleName + "[跨域文件保存成功]");
						} catch (Exception e) {
							e.printStackTrace();
							msgMap.put("success", false);
							logger.info(moduleName + "[跨域文件保存失败]");
						}
					}  
				}  
			}  
		}
		logger.info(moduleName + "[跨域文件接收结束]");
		return msgMap;  
	}
	/**
	 * @title 跨域下载文件
	 * @author  孙尚飞  2017-12-19
	 * @desc
	 */
	@RequestMapping("/downloadFile")
	@ResponseBody
	public Map<String,Object> downloadFile(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		String entid=request.getParameter("entid");
		List<HistoricalData> historicals = historicalDataService.selectByEntId(entid);
		String adr=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root");
		if(!historicals.isEmpty()||historicals.size()>0){
			//通过xml 获取历史
			for (HistoricalData historical : historicals) {
				try {
					 System.out.println("path :" +historical.getHistoricalXMLUrl());
				     File f = new File(adr+historical.getHistoricalXMLUrl());   
				     if(!f.isFile()){
				    	 System.out.println("该路径下不存在相关文件:"+adr+historical.getHistoricalXMLUrl());
				    	 continue;
				     }else{
				    	 
				     }
				    } catch (Exception e) {   
				     e.printStackTrace();   
				   }   
			}
			msgMap.put("success", true);
		}else{
			msgMap.put("success", false);
		}
		return msgMap;
	}

	
	/**
	 * @title 跨域下载文件
	 * @author  孙尚飞  2017-12-19
	 * @desc
	 */
	@RequestMapping("/selectHistorical")
	@ResponseBody
	public Map<String,Object> selectHistorical(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		String entid=request.getParameter("entid");
		List<HistoricalData> historicals = historicalDataService.selectByEntId(entid);
		String adr=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root");
		List<HistoricalVO> historicalVOs = new ArrayList<HistoricalVO>(); 
		if(!historicals.isEmpty()||historicals.size()>0){
			//通过xml 获取历史
			for (HistoricalData historical : historicals) {
				HistoricalVO historicalVO = new HistoricalVO();
				try {
					 System.out.println("path :" +historical.getHistoricalXMLUrl());
				     File f = new File(adr+historical.getHistoricalXMLUrl());   
				     if(!f.isFile()){
				    	 System.out.println("该路径下不存在相关文件:"+adr+historical.getHistoricalXMLUrl());
				    	 continue;
				     }
				     SAXReader reader = new SAXReader();   
				     Document doc = reader.read(f);  
				     //获取根节点
				     Element root = doc.getRootElement();   
				     //查找子节点
				     Element entBaseInfo =  (Element) root.elementIterator("EntBaseInfo").next();
				     historicalVO.setHistoricalId(historical.getUuid());
				     historicalVO.setEntuuid(entBaseInfo.elementText("uuid"));
				     historicalVO.setAddress(entBaseInfo.elementText("address"));
				     historicalVO.setEmail(entBaseInfo.elementText("email"));
				     historicalVO.setLegalPerson(entBaseInfo.elementText("legalPerson"));
				     historicalVO.setName(entBaseInfo.elementText("name"));
				     historicalVO.setTel(entBaseInfo.elementText("tel"));
				     historicalVO.setUscc(entBaseInfo.elementText("USCC"));
				     historicalVO.setWebsite(entBaseInfo.elementText("website"));
				     
				     Element entResult =  (Element) root.elementIterator("EntResult").next();
				     historicalVO.setEncoding(entResult.elementText("encoding"));
				     historicalVO.setFinalLevel(entResult.elementText("finalLevel"));
				     historicalVO.setFinalScore(entResult.elementText("finalScore"));
				     historicalVO.setGradeTime(entResult.elementText("gradeTime"));
				     
				     Element models =  (Element) root.elementIterator("Model").next();
				     historicalVO.setModelName(models.elementText("name"));
				     historicalVO.setModelVersion(models.elementText("version"));
				     
				     System.out.println("historicalVO : "+ historicalVO);
				     
				    } catch (Exception e) {   
				     e.printStackTrace();   
				   }   
				historicalVOs.add(historicalVO);
			}
			msgMap.put("success", true);
			msgMap.put("historicalVOs", historicalVOs);
		}else{
			msgMap.put("success", false);
		}
		return msgMap;
	}

}
