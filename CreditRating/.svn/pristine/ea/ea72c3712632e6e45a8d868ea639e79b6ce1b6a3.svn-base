package com.credit.controller.addition;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.credit.bean.addition.Model;
import com.credit.bean.addition.Template;
import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.privilege.Menu;
import com.credit.bean.vo.addition.ModelVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.addition.ModelService;
import com.credit.util.DateTime;
import com.credit.util.DeleteFileUtil;
import com.credit.util.SaveFilePathUtil;
import com.credit.util.model.FileToString;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.XMLFormat;

@Controller
@RequestMapping("/control/addition/model/")
public class ModelManageAction {
	private static final Logger logger = Logger.getLogger(ModelManageAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	private Map<String,Object> msgMap = new HashMap<String, Object>();
	private String absolutePathDir = SaveFilePathUtil.saveFilePathDesc("adr")+"UploadFiles/CreditRating/"+DateTime.getDirectory();
	
	@Resource
	ModelService modelService;
	
	@Permission(model = "model", privilegeValue = "list")
	@RequestMapping("/listUI")
	@ResponseBody
	public Map<String,Object> listUI(int limit,int page,String parentID){
		logger.info(moduleName + "[查看模型列表]");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
		PageView<Menu> pageView = new PageView<Menu>(limit,page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		Map<String, Object> msgMap = new HashMap<String, Object>();
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(parentID!=null && !"".equals(parentID.trim())){
			if (params.size() > 0) jpql.append(" and ");
			jpql.append(" o.parent.uuid=?" + (params.size() + 1));
			params.add(parentID);
			Model model = modelService.find(parentID);
			Model parenModel = model.getParent();
			List<Model> models = new ArrayList<Model>();
			models.add(model);
			while (parenModel != null) {
				models.add(parenModel);
				parenModel = parenModel.getParent();
			}
			msgMap.put("models", models);
		}else{
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append("o.parent is null");
		}
		orderby.put("orderNO", "asc");//按排序号进行排序
		QueryResult<Model> qr = modelService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray(), orderby);
		List<Model> models = qr.getResultlist();
		List<ModelVO> mvos = new ArrayList<ModelVO>();
		for(Model m :models){
			ModelVO mv = new ModelVO();
			mv.setUuid(m.getUuid());
			if(m.getName()!=null && !"".equals(m.getName())){
				mv.setName(m.getName());
			}
			mv.setRemarks(m.getRemark());
			if(m.getOrderNO()!=null){
				mv.setOrderNO(m.getOrderNO().toString());
			}
			mv.setVisible(m.getVisible().toString());
			mv.setUpdateTime(simpleDateFormat.format(m.getUpdateTime()));
			if(m.getParent()==null){
				mv.setParentID("");
			}else{
				mv.setParentID(m.getParent().getUuid());
				if(m.getXMLurl()!=null && !"".equals(m.getXMLurl())){
					mv.setXMLurl(m.getXMLurl());
				}				
			}
			mvos.add(mv);
		}
		msgMap.put("total", qr.getTotalrecord());
		msgMap.put("models", mvos);
		return msgMap;
	}
	
	
	@Permission(model = "model", privilegeValue = "insert")
	@RequestMapping("/add")
	@ResponseBody
	public Map<String,Object> add(Model model){
		boolean flag = true;
		if(model == null){
			flag = false;
		}else{
			if(model.getName() == null || "".equals(model.getName().trim())){ 
				flag = false;
			}
			if(modelService.exist("name", model.getName())) {
				flag = false;
			}
		}
		if(flag){
			model.setUuid(UUID.randomUUID().toString().replace("-", ""));
			model.setUpdateTime(DateTime.getCurrentTimeStamp());
			model.setVersion("1");
			modelService.save(model);
		}
		msgMap.put("success", true);
		msgMap.put("status", true);
		msgMap.put("msg", "保存成功");
		logger.info(moduleName + "[更新模型成功]");
		return msgMap;
	}
	
	
	
	
	@Permission(model = "model", privilegeValue = "insert")
	@RequestMapping("/addCon")
	@ResponseBody
	public Map<String,Object> addCon(Model model,MultipartFile uploadFile,String parentID) throws IllegalStateException, IOException{
		Integer state = 0;
		MultipartFile multipartFile = uploadFile;
		boolean flag = true;
		if(parentID == null&&"".equals(parentID)){
			flag = false;
		}
		if(multipartFile == null){//判断xml文件是否上传
			flag = false;
		}
		if(model == null){
			flag = false;
		}else{
			if(model.getName() == null || "".equals(model.getName().trim())){ 
				flag = false;
			}
			if(modelService.exist("name", model.getName())) {
				flag = false;
			}
		}
		if(flag){
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
							String newFileName = UUID.randomUUID().toString().replace("-", "") + "." + ext;//构建文件新名称
							String realname = absolutePathDir+"/"+newFileName;
							File file = new File(realname);
							if(!file.exists()) file.mkdirs();
							//保存上传文件
							multipartFile.transferTo(file);//输出文件流到目标文件
							if(ModelUtil.getWeight(realname) !=100){
								msgMap.put("weightMsg", "权重总数不为100，请检查");
							}
							logger.info(multipartFile.getOriginalFilename()+"上传成功！！！");
							model.setXMLurl(realname);
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
			model.setUuid(UUID.randomUUID().toString().replace("-", ""));
			model.setUpdateTime(DateTime.getCurrentTimeStamp());
			model.setVisible(1);
			model.setVersion("1");
			model.setParent(modelService.find(parentID));
			modelService.save(model);
		}
		msgMap.put("success", true);
		msgMap.put("status", true);
		msgMap.put("msg", "保存成功");
		logger.info(moduleName + "[更新模型成功]");
		return msgMap;
	}
	
	@Permission(model = "model", privilegeValue = "update")
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String,Object> edit(Model model,String uuid){
		boolean flag = true;
		if(model==null){
			flag = false;
		}else{
			if(model.getUuid() == null || "".equals(model.getUuid().trim())){
				flag = false;
			}
			if(model.getName() == null || "".equals(model.getName().trim())){
				flag = false;
			}
			if(uuid!=null && !"".equals(uuid)){
				String oldName = modelService.find(uuid).getName();
				String newName = model.getName();
				if(!oldName.equals(newName)){
					if(modelService.exist("name", model.getName())){ 
						flag = false;
					}
				}
			}
		}
		if(flag){
			model.setUpdateTime(DateTime.getCurrentTimeStamp());
			model.setVersion("1");
			modelService.update(model);
		}
		msgMap.put("success", true);
		msgMap.put("status", true);
		msgMap.put("msg", "保存成功");
		logger.info(moduleName + "[更新模型成功]");
		return msgMap;
	}
	
	@Permission(model = "model", privilegeValue = "update")
	@RequestMapping("/editCon")
	@ResponseBody
	public Map<String,Object> editCon(Model model,MultipartFile uploadFile) throws IllegalStateException, IOException{
		Integer state = 0;
		MultipartFile multipartFile = uploadFile;
		boolean flag = true;
		if(model==null){
			flag = false;
		}else{
			
			if(model.getName() == null || "".equals(model.getName().trim())){
				flag = false;
			}
			if(model.getUuid() == null || "".equals(model.getUuid().trim())){
				flag = false;
			}else{
				String oldName = modelService.find(model.getUuid()).getName();
				String newName = model.getName();
				if(!oldName.equals(newName)){
					if(modelService.exist("name", model.getName())){ 
						flag = false;
					}
				}
			}
		}
		if(flag){
			if(!"application/octet-stream".equals(multipartFile.getContentType())){//是否上传了文件 判断为TRUE则上传了文件  multipartFile!=null判断无效
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
							if(ModelUtil.getWeight(realname) !=100){
								msgMap.put("weightMsg", "权重总数不为100，请检查");
							}
							logger.info(moduleName+"["+multipartFile.getOriginalFilename()+"上传成功]");
							String url = modelService.find(model.getUuid()).getXMLurl();
							if(url!=null && !"".equals(url)){//判断原文件是否存在
								File f = new File(url);
								if(f.exists()){
									DeleteFileUtil.deleteFile(url);//删除服务器原文件
									logger.info(moduleName + "[服务器文件删除成功]");
								}
							}
							model.setXMLurl(realname);
							break;
							
					case 1:	msgMap.put("success", true);
							msgMap.put("status", false);
							msgMap.put("msg", "请上传XML格式文件");
							logger.info(moduleName + "[插入模型失败]");
							return msgMap;
							
					case 2: msgMap.put("success", true);
							msgMap.put("status", false);
							msgMap.put("msg", "请控制上传文件大小不超过2M");
							logger.info(moduleName + "[插入模型失败]");
							return msgMap;
							
					case 3: msgMap.put("success", true);
							msgMap.put("status", false);
							msgMap.put("msg", "请不要上传空文件");
							logger.info(moduleName + "[插入模型失败]");
							return msgMap;
				}
			}
			/*用该方法传递参数获取到的对象不完整，部分信息需要通过uuid查到该对象补全*/
			Model mm = modelService.find(model.getUuid());
			model.setUpdateTime(DateTime.getCurrentTimeStamp());
			if(mm.getParent()!=null){
				model.setParent(mm.getParent());
			}
			if(mm.getVersion()!=null && !"".equals(mm.getVersion())){
				model.setVersion(mm.getVersion());
			}
			if(mm.getVisible()!=null){
				model.setVisible(mm.getVisible());
			}
			if(mm.getXMLurl()!=null && !"".equals(mm.getXMLurl())){
				model.setXMLurl(mm.getXMLurl());
			}
			modelService.update(model);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "保存成功");
			logger.info(moduleName + "[更新模型成功]");
		}else{
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "数据库中未查询到相同的模型,请重新录入");
			logger.info(moduleName + "[更新模型失败]");
		}
		return msgMap;
	}
	
	@Permission(model = "model", privilegeValue = "delete")
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String uuid) throws Exception {
		logger.info( "删除模板delete;String:"+uuid);
		boolean flag = true;
		message.setLength(0);
		Model model = null;
		if(uuid!=null &&!"".equals(uuid)){
			model = modelService.find(uuid);
		}
		if (model == null) {
			message.append("参数错误");
			flag = false;
		}
		if (model.getChilds().size() >= 1) {
			message.append("请先删除子模型");
			flag = false;
		}
		if (flag) {
			modelService.delete((Serializable) uuid.trim());
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
	
	@Permission(model = "template", privilegeValue = "delete")
	@RequestMapping("/deleteCon")
	@ResponseBody
	public Map<String, Object> deleteCon(String uuid) throws Exception {
		logger.info( "删除模型delete;String:"+uuid);
		boolean flag = true;
		message.setLength(0);
		Model model = null;
		if(uuid!=null&&!"".equals(uuid)){
			model = modelService.find(uuid);
		}
		if (model == null) {
			message.append("参数错误");
			flag = false;
		}
		if (model.getChilds().size() >= 1) {
			message.append("请先删除子模型");
			flag = false;
		}
		if (flag) {
			String url = model.getXMLurl();
			if(url!=null && !"".equals(url)){
				File file = new File(url);
				if(file.exists()){
					DeleteFileUtil.deleteFile(url);
					logger.info(moduleName + "[服务器文件删除成功]");
				}
			}
			modelService.delete((Serializable) uuid.trim());
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "删除模型成功");
			message.append("操作成功");
			logger.info(moduleName + "[模型删除:" + message + "]");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "删除模型失败,"+message);
			logger.info(moduleName + "[模型删除:" + message + "]");
		}
		return msgMap;
	}
	
	@Permission(model = "model", privilegeValue = "update")
	@RequestMapping("/updateVisible")
	@ResponseBody
	public Map<String, Object> updateVisible(String uuid,Integer visible) throws Exception {
		if(uuid!=null &&!"".equals(uuid) && visible !=null){
			Model m = modelService.find(uuid);
			m.setVisible(visible);
			modelService.update(m);
		}
		if (visible == 1) {
			message.append("<p>激活操作成功</p>");
		} else {
			message.append("<p>锁定操作成功</p>");
		}
		msgMap.put("success", true);
		msgMap.put("status", true);
		msgMap.put("msg", message.toString());
		logger.info(moduleName + "[模型状态设置:" + message + "]");
		// return "controlResult";
		return msgMap;
	}
	
	@Permission(model = "model", privilegeValue = "insert")
	@RequestMapping("/preview")
	@ResponseBody
	public Map<String, Object> preview(String uuid) throws Exception {
		Model m = null;
		String content = "";
		if(uuid!=null &&!"".equals(uuid)){
			 m = modelService.find(uuid);
		}
		if(m!=null){
			String fileContent = XMLFormat.format(m.getXMLurl());
			 content = FileToString.readFileContent(fileContent);
		}
		msgMap.put("content", content);
		msgMap.put("success", true);
		msgMap.put("status", true);
		msgMap.put("msg", message.toString());
		logger.info(moduleName + "[模型状态设置:" + message + "]");
		// return "controlResult";
		return msgMap;
	}
}
