/*package com.credit.controller.addition;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.credit.bean.addition.Template;
import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.vo.addition.TemplateVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.addition.TemplateService;
import com.credit.service.addition.UploadFileCategoryService;
import com.credit.util.DateTime;
import com.credit.util.DeleteFileUtil;
import com.credit.util.SaveFilePathUtil;
import com.credit.util.tree.SimpleTreeNode;

*//**
 * @Title: 模版管理
 * @author Administrator @date 2017-7-26 
 *//*

@Controller
@RequestMapping("/control/addition/template/")
public class TemplateManageAction {

	private static final Logger logger = Logger.getLogger(TemplateManageAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	private Integer state = 0;
	//绝对路径
	private String absolutePathDir = SaveFilePathUtil.saveFilePathDesc("adr")+"UploadFiles/CreditRating/"+DateTime.getDirectory();
	
	@Resource
	TemplateService templateService;
	@Resource
	UploadFileCategoryService uploadFileCategoryService;

	*//**
	 * @Title 获取需要上传的文件列表 show
	 * @author  Administrator  @date 2017-7-27 
	 * @Description 
	 *
	 *//*
	@Permission(model = "template", privilegeValue = "list")
	@RequestMapping("/listUI")
	@ResponseBody
	public Map<String, Object> listUI(int page,int limit,String parentid,boolean query,String searchTitle) {
		logger.info(moduleName + "[查看模板列表]");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		PageView<Template> pageView = new PageView<Template>(limit, page);
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if (parentid != null && !"".equals(parentid.trim())) {
			if(params.size()>0) jpql.append(" and ");
			jpql.append(" o.parent.uuid=?" + (params.size() + 1));
			params.add(parentid);
			Template temp = templateService.find(parentid);
			Template parentTemplate = temp.getParent();
			List<Template> temps = new ArrayList<Template>();
			temps.add(temp);
			while (parentTemplate != null) {
				temps.add(parentTemplate);
				parentTemplate = parentTemplate.getParent();
			}
			msgMap.put("templates", temps);
		}else if(query){
			if(searchTitle!=null&&!"".equals(searchTitle.trim())){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.templateName like ?"+ (params.size() + 1));
				params.add("'%"+ searchTitle + "%'");
			}
		}else{
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append("o.parent is null");
		}
		
		
		QueryResult<Template> qr = templateService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray(),null);
		pageView.setQueryResult(qr);
		List<TemplateVO> list = new ArrayList<TemplateVO>();
		if(qr!=null){
			for (Template template : qr.getResultlist()) {
				TemplateVO templateVO = new TemplateVO();
				templateVO.setUuid(template.getUuid());
				templateVO.setRemark(template.getRemark());
				if(template.getParent()!=null){
					templateVO.setParentID(template.getParent().getUuid());
				}
				if(template.getChilds().size()>0 && template.getParent()==null){//有子模板且为父模板
					templateVO.setTemplateName(template.getTemplateName()+" （有" + template.getChilds().size()+ "子模板）");
				}else if((template.getTemplateUrl()==null || "".equals(template.getTemplateUrl())) && template.getParent()==null){//未上传文件且为父模板
					templateVO.setTemplateName(template.getTemplateName()+" 0");
				}else{//上传了文件的单一模板
					templateVO.setTemplateName(template.getTemplateName());
				}
				templateVO.setTemplateUrl(template.getTemplateUrl());
				templateVO.setUpdateTime(simpleDateFormat.format(template.getUpdateTime()) );
				if(template.getVisible()!=null){
					if (template.getVisible() == 1) {
						templateVO.setVisible("正常" + " " + "锁定");
					} else {
						templateVO.setVisible("关闭" + " " + "激活");
					}
				}else{
					templateVO.setVisible("关闭" + " " + "激活");
				}
				list.add(templateVO);
			}
		}
		msgMap.put("total", qr.getTotalrecord());
		msgMap.put("templates", list);
		return msgMap;
	
	}

	
	@SuppressWarnings({ "rawtypes" })
	@Permission(model="template", privilegeValue="list")
	@RequestMapping("/treeUI")
	@ResponseBody
	public List<SimpleTreeNode> treeUI(String parentID,Integer queryDemand) throws Exception{
		List<SimpleTreeNode> simpleTreeNodeList = new ArrayList<SimpleTreeNode>();
		if (parentID != null && !"".equals(parentID.trim()) && queryDemand != null) {
			if(queryDemand == 0){//新增查询父模板id为parentID的模板
				Template temp = templateService.find(parentID);
				if(temp!=null){
					SimpleTreeNode simpleTreeNode = new SimpleTreeNode();
					simpleTreeNode.setId(temp.getUuid());
					simpleTreeNode.setText(temp.getTemplateName());
					simpleTreeNode.setExpanded(true);
					if(temp.getChilds()!=null){
						simpleTreeNode.setLeaf(true);
					}
					simpleTreeNodeList.add(simpleTreeNode);
				}
			}else if(queryDemand == 1){//编辑查询所有parentID为null的模板
				StringBuffer jpql = new StringBuffer("");
				jpql.append(" o.parent.uuid is null");
				QueryResult<Template> qr = templateService.getScrollData(1, 15, jpql.toString(),null);
				if(qr!=null) {
					List<Template> tempList = qr.getResultlist();
					for (Template temp : tempList) {
						SimpleTreeNode simpleTreeNode = new SimpleTreeNode();
						simpleTreeNode.setId(temp.getUuid());
						simpleTreeNode.setText(temp.getTemplateName());
						simpleTreeNode.setExpanded(true);
						simpleTreeNode.setLeaf(true);
						simpleTreeNodeList.add(simpleTreeNode);
					}
				}
			}
		}
		return simpleTreeNodeList;
	}
	
	
	*//**
	 * @Title 添加 文件上传列表信息
	 * @author  Administrator  @date 2017-7-27 
	 * @throws IOException 
	 * @Description 
	 *
	 *//*
	@Permission(model = "template", privilegeValue = "insert")
	@RequestMapping(value="/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Template template,MultipartFile uploadFile,String parentID) throws IOException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		*//********************文件上传***************************//*
		//使用MultipartHttpServletRequest包装文件数据
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//通过ExtJS页面控件的name获取文件流
		MultipartFile multipartFile = multipartRequest.getFile("uploadFile");
		//上传文件状态控制
		MultipartFile multipartFile = uploadFile;
		
		boolean flag = true;
		if(template == null){
			flag = false;
		}else{
			if(template.getTemplateName() == null || "".equals(template.getTemplateName().trim())){ 
				flag = false;
			}
			if(templateService.exist("templateName", template.getTemplateName())) {
				flag = false;
			}
		}
		if (flag) {//新增界面template非空、重名验证
			if(!"application/octet-stream".equals(multipartFile.getContentType())){//是否上传了文件  multipartFile!=null判断无效
				if(multipartFile.getContentType().indexOf("xml")<0){//判断是否为XML文件
					state = 1;
				}
				if(multipartFile.getSize() > 2048000 ){//判断文件大小是否大于2M
					state = 2;
				}
				if(multipartFile.getSize() == 0){//判断是否是空文件
					state = 3;
				}
				switch(state){
					case 0:	String pathdir = "/UploadFiles/"+ DateTime.getDirectory();//构建图片保存的目录 相对路径
							String realpathdir = request.getSession().getServletContext().getRealPath(pathdir);
							String ext = multipartFile.getOriginalFilename().trim().substring(multipartFile.getOriginalFilename().trim().lastIndexOf('.')+1).toLowerCase();
							String newFileName = UUID.randomUUID().toString() + "." + ext;//构建文件新名称
							String realname = absolutePathDir+"/"+newFileName;
							File file = new File(realname);
							if(!file.exists()) file.mkdirs();
							//保存上传文件
							multipartFile.transferTo(file);//输出文件流到目标文件
							logger.info(multipartFile.getOriginalFilename()+"上传成功！！！");
							template.setTemplateUrl(realname);
							break;
							
					case 1:	msgMap.put("success", true);
							msgMap.put("status", false);
							msgMap.put("msg", "请上传XML格式文件");
							logger.info(moduleName + "[插入模板失败]");
							return msgMap;
							
					case 2: msgMap.put("success", true);
							msgMap.put("status", false);
							msgMap.put("msg", "请控制上传文件大小不超过2M");
							logger.info(moduleName + "[插入模板失败]");
							return msgMap;
							
					case 3: msgMap.put("success", true);
							msgMap.put("status", false);
							msgMap.put("msg", "请不要上传空文件");
							logger.info(moduleName + "[插入模板失败]");
							return msgMap;
				}
			}
			if(parentID!=null){
				template.setParent(templateService.find(parentID));
			}
			template.setUpdateTime(DateTime.getCurrentTimeStamp());
			template.setUuid(UUID.randomUUID().toString().replace("-", ""));
			template.setVisible(1);
			templateService.save(template);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "保存成功");
			logger.info(moduleName + "[插入模板成功]");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "数据库中已有相同的模板名称,请重新录入");
			logger.info(moduleName + "[插入模板失败]");
		}
		return msgMap;
	}
	
	
	@Permission(model = "template", privilegeValue = "delete")
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String uuid) throws Exception {
		logger.info( "删除模板delete;String:"+uuid);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		boolean flag = true;
		message.setLength(0);
		Template temp = templateService.find(uuid);
		if (temp == null) {
			message.append("参数错误");
			flag = false;
		}
		if (temp.getChilds().size() >= 1) {
			message.append("请先删除子模板");
			flag = false;
		}
		if (flag) {
			String url = temp.getTemplateUrl();
			if(url!=null && !"".equals(url)){
				if (DeleteFileUtil.deleteFile(url)){
					logger.info(moduleName + "[服务器文件删除成功]");
				}
			}
			templateService.delete((Serializable) uuid.trim());
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "删除模板成功");
			message.append("操作成功");
			logger.info(moduleName + "[模板删除:" + message + "]");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "删除模板失败,"+message);
			logger.info(moduleName + "[模板删除:" + message + "]");
		}
		return msgMap;
	}
	
	
	*//**
	 * @Title 更新 上传文件管理列表信息
	 * @author  严树炜  @date 2017-7-27 
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @Description 
	 *
	 *//*
	@Permission(model = "template", privilegeValue = "update")
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String, Object> edit(Template template,String categoryNameE,MultipartFile uploadFile) throws IllegalStateException, IOException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		MultipartFile multipartFile = uploadFile;
		boolean flag = true;
		if(template == null){
			flag = false;
		}else{
			if(template.getUuid() == null || "".equals(template.getUuid().trim())){
				flag = false;
			}
			if(template.getTemplateName() == null || "".equals(template.getTemplateName().trim())){
				flag = false;
			}
			if(template.getUuid()!=null && !"".equals(template.getUuid())){
				String oldName = templateService.find(template.getUuid()).getTemplateName();
				String newName = template.getTemplateName();
				if(!oldName.equals(newName)){
					if(templateService.exist("templateName", template.getTemplateName())){ 
						flag = false;
					}
				}
			}
		}
		if (flag) {
			if(!"application/octet-stream".equals(multipartFile.getContentType())){//是否上传了文件  multipartFile!=null判断无效
				if(multipartFile.getContentType().indexOf("xml")<0){//判断是否为XML文件
					state = 1;
				}
				if(multipartFile.getSize() > 2048000 ){//判断文件大小是否大于2M
					state = 2;
				}
				if(multipartFile.getSize() == 0){//判断是否是空文件
					state = 3;
				}
				switch(state){
					case 0:	String ext = multipartFile.getOriginalFilename().trim().substring(multipartFile.getOriginalFilename().trim().lastIndexOf('.')+1).toLowerCase();
							String newFileName = UUID.randomUUID().toString() + "." + ext;//构建文件新名称
							String realname = absolutePathDir+"/"+newFileName;
							File file = new File(realname);
							if(!file.exists()) file.mkdirs();
							//保存上传文件
							multipartFile.transferTo(file);//输出文件流到目标文件
							logger.info(multipartFile.getOriginalFilename()+"上传成功！！！");
							String url = templateService.find(template.getUuid()).getTemplateUrl();
							if(url!=null && !"".equals(url)){
								if (DeleteFileUtil.deleteFile(url)){//删除服务器原文件
									logger.info(moduleName + "[服务器文件删除成功]");
									template.setTemplateUrl(realname);
								}
							}
							break;
							
					case 1:	msgMap.put("success", true);
							msgMap.put("status", false);
							msgMap.put("msg", "请上传XML格式文件");
							logger.info(moduleName + "[插入模板失败]");
							return msgMap;
							
					case 2: msgMap.put("success", true);
							msgMap.put("status", false);
							msgMap.put("msg", "请控制上传文件大小不超过2M");
							logger.info(moduleName + "[插入模板失败]");
							return msgMap;
							
					case 3: msgMap.put("success", true);
							msgMap.put("status", false);
							msgMap.put("msg", "请不要上传空文件");
							logger.info(moduleName + "[插入模板失败]");
							return msgMap;
				}
			}
			if(categoryNameE!=null && !"".equals(categoryNameE)){
				template.setParent(templateService.SelectByName(categoryNameE));
			}
			template.setVisible(1);
			template.setUpdateTime(DateTime.getCurrentTimeStamp());
			templateService.update(template);
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "保存成功");
				logger.info(moduleName + "[更新模板成功]");
			
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "该名称已被占用,请重新录入");
			logger.info(moduleName + "[更新模板失败]");
		}
		return msgMap;
	}
	
	@RequestMapping("/getParentName")
	@ResponseBody
	public Map<String, Object> getParentName(String uuid) throws Exception {
		logger.info( "得到父菜单名称getParentName;String:"+uuid);
		System.out.println("===========开始获取===========");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Template temp=templateService.find(uuid);
		if (temp != null) {
			String names="";
			String ownName="------"+"<a href=javascript:backParent("+"'"+temp.getUuid()+"'"+") style='color:#FFF'>"+temp.getTemplateName()+"</a>";
			System.out.println("************"+ownName);
			while(temp.getParent()!=null){			
				Template parentTemp=templateService.find(temp.getParent().getUuid());		
				names="------"+"<a href=javascript:backParent("+"'"+parentTemp.getUuid()+"'"+") style='color:#FFF'>"+parentTemp.getTemplateName()+"</a>"+"------"+names;
				System.out.println(names);
				temp=parentTemp;
			}
			if(!names.equals("")){	
				names=names.substring(0, names.length()-6);
			}
			msgMap.put("names", names);
			msgMap.put("ownName", ownName);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "获取成功");
		}else{			
			msgMap.put("success", false);
			msgMap.put("status", false);
			msgMap.put("msg", "获取失败");
		} 	
		return msgMap;
	}
}
*/