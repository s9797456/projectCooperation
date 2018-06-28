package com.credit.controller.addition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.addition.UploadFileCategory;
import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.addition.UploadFileCategoryService;
import com.credit.util.DateTime;

/**
 * @Title: 扫描件管理
 * @author Administrator @date 2017-7-26 上午9:47:44
 */

@Controller
@RequestMapping("/control/addition/uploadFiles/")
public class UploadFileCategoryManageAction {

	boolean flag = true;
	private static final Logger logger = Logger.getLogger(UploadFileCategoryManageAction.class);
	private String moduleName = "管理员操作情况";
	
	
	@Resource
	UploadFileCategoryService uploadFileCategoryService;

	/**
	 * @Title 获取需要上传的文件列表 show
	 * @author  Administrator  @date 2017-7-27 
	 * @Description 
	 *
	 */
	@Permission(model = "uploadFile", privilegeValue = "list")
	@RequestMapping("/listUI")
	@ResponseBody
	public Map<String, Object> listUI(int page,int start,int limit) {
		logger.info(moduleName + "[查看扫描件管理列表]");
		
		Map<String, Object> msgMap = new HashMap<String, Object>();
		
		PageView<UploadFileCategory> pageView = new PageView<UploadFileCategory>(limit, page);
		
		QueryResult<UploadFileCategory> qr = uploadFileCategoryService.getScrollData(
				pageView.getFirstResult(),// n-1页总记录数
				pageView.getMaxresult());// 每页显示记录数
		
		pageView.setQueryResult(qr);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("Y年m月d日");
		for (UploadFileCategory uploadFileCategory : qr.getResultlist()) {
			Date date = uploadFileCategory.getUpdateTime();
			try {
				uploadFileCategory.setUpdateTime(simpleDateFormat.parse(simpleDateFormat.format(date))) ;
			} catch (ParseException e) {
				e.printStackTrace();
				logger.info(moduleName + "[查看扫描件管理列表-时间处理异常]");
			}
		}
		msgMap.put("uploadFiles", qr.getResultlist());
		msgMap.put("total", qr.getTotalrecord());
		msgMap.put("success", true);
		msgMap.put("status", true);
		return msgMap;
	}

	/**
	 * @Title 添加 文件上传列表信息
	 * @author  Administrator  @date 2017-7-27 
	 * @Description 
	 *
	 */
	@Permission(model = "uploadFile", privilegeValue = "insert")
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(UploadFileCategory uploadFileCategory) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if(uploadFileCategory == null){
			flag = false;
		}else{
			if(uploadFileCategory.getName() == null || "".equals(uploadFileCategory.getName().trim())){ 
				flag = false;
			}
			if(uploadFileCategoryService.exist("name", uploadFileCategory.getName())) {
				flag = false;
			}
		}
		if (flag) {
				uploadFileCategory.setUpdateTime(DateTime.getCurrentTimeStamp());
				uploadFileCategory.setUuid(UUID.randomUUID().toString().replace("-", ""));
				uploadFileCategoryService.save(uploadFileCategory);
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "保存成功");
				logger.info(moduleName + "[插入扫描件指标成功]");
			
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "数据库中已有相同的指标,请重新录入");
			logger.info(moduleName + "[插入扫描件指标失败]");
		}
		return msgMap;
	}
	
	/**
	 * @Title 删除文件上传管理列表单条信息
	 * @author  Administrator  @date 2017-7-27 
	 * @Description 
	 *
	 */
	@Permission(model = "uploadFile", privilegeValue = "delete")
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String uuid) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		UploadFileCategory uploadFileCategory = null ;
		if(uuid == null || "".equals(uuid.trim())){
			flag = false;
		}else{
			uploadFileCategory = uploadFileCategoryService.find(uuid);
			if(uploadFileCategory == null ){ 
				flag = false;
			}
		}
		if (flag) {
			   uploadFileCategoryService.delete(uploadFileCategory.getUuid());
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "删除成功");
				logger.info(moduleName + "[删除扫描件指标成功]");
			
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "数据库中无相关指标,请重新尝试");
			logger.info(moduleName + "[删除扫描件指标失败]");
		}
		return msgMap;
	}

	/**
	 * @Title 更新 上传文件管理列表信息
	 * @author  Administrator  @date 2017-7-27 
	 * @Description 
	 *
	 */
	@Permission(model = "uploadFile", privilegeValue = "update")
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(UploadFileCategory uploadFileCategory) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if(uploadFileCategory == null){
			flag = false;
		}else{
			if(uploadFileCategory.getUuid() == null || "".equals(uploadFileCategory.getUuid().trim())){ 
				flag = false;
			}else{
				UploadFileCategory ufc = uploadFileCategoryService.find(uploadFileCategory.getUuid());
				if(ufc!=null && ufc.getName()!=null){
					if(!ufc.getName().equals(uploadFileCategory.getName())){//此处考虑用户不更新Name
						if(uploadFileCategoryService.exist("name", uploadFileCategory.getName())){ 
							flag = false;
						} 
					}
				}else{
					flag = false;
				}
			}
			if(uploadFileCategory.getName() == null || "".equals(uploadFileCategory.getName().trim())){ 
				flag = false;
			}
		}
		if (flag) {
				uploadFileCategory.setUpdateTime(DateTime.getCurrentTimeStamp());
				uploadFileCategoryService.update(uploadFileCategory);
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "保存成功");
				logger.info(moduleName + "[更新扫描件指标成功]");
			
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "数据库中未查询到相同的指标,请重新录入");
			logger.info(moduleName + "[更新扫描件指标失败]");
		}
		return msgMap;
	}

}
