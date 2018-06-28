package com.credit.util.properties;


import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;


public class AliyunUtil {
	private static Properties aliyunmsg = new Properties();
	private static String aliyun_sms_region_id = "";    //阿里云regionId
    private static String aliyun_sms_access_key_id = "";    //阿里云 accessKeyId
    private static String aliyun_sms_access_key_secret = "";    //阿里云 accessKeySecret
    private static String aliyun_sms_product = "";  //阿里云产品
    private static String aliyun_sms_domain = "";   //阿里云调用短信服务平台接口域名
    private static String aliyun_sms_sign_name = "";   //短信签名
    
    private static String aliyun_mail_smtp_host= "";   //SMTP服务地址
    private static String aliyun_mail_smtp_port= "";   //SMTP端口号
    private static String aliyun_mail_user= "";   //发件人的账号
    private static String aliyun_mail_password= "";   //访问SMTP服务时需要提供的密码
    private static String aliyun_mail_nickname= "";   //发件人昵称
    private static String aliyun_mail_sign= "";   //控制台创建的标签
    private static String aliyun_mail_model= "";   //批量发送邮件模版
	static{
		try {
			aliyunmsg.load(AliyunUtil.class.getClassLoader().getResourceAsStream("com/config/aliyun.properties"));
			
			aliyun_sms_region_id = AliyunUtil.aliyunMsg("SMS_regionId");
		    aliyun_sms_access_key_id = AliyunUtil.aliyunMsg("SMS_accessKeyID");
		    aliyun_sms_access_key_secret = AliyunUtil.aliyunMsg("SMS_accessKeySecret");
		    aliyun_sms_product = AliyunUtil.aliyunMsg("SMS_product");
		    aliyun_sms_domain=AliyunUtil.aliyunMsg("SMS_domain");
		    aliyun_sms_sign_name=AliyunUtil.aliyunMsg("SMS_signName");
		    
		    aliyun_mail_smtp_host=AliyunUtil.aliyunMsg("Mail_smtp_host");
		    aliyun_mail_smtp_port=AliyunUtil.aliyunMsg("Mail_smtp_port");
		    aliyun_mail_user=AliyunUtil.aliyunMsg("Mail_user");
		    aliyun_mail_password=AliyunUtil.aliyunMsg("Mail_password");
		    aliyun_mail_nickname=AliyunUtil.aliyunMsg("Mail_nickname");
		    aliyun_mail_sign=AliyunUtil.aliyunMsg("Mail_sign");
		    aliyun_mail_model=AliyunUtil.aliyunMsg("Mail_model");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String aliyunMsg(String aliyun){
		String value = (String)aliyunmsg.get(aliyun);
		return value;
	}	
	@SuppressWarnings("finally")
	public static boolean sendSMS(String cellphone,Map<String, String> paramMap,String SMSModel) {    
		boolean isSendSuccess = false;
		try {
			IClientProfile profile = DefaultProfile.getProfile(aliyun_sms_region_id, aliyun_sms_access_key_id, aliyun_sms_access_key_secret);
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			DefaultProfile.addEndpoint(aliyun_sms_region_id, aliyun_sms_region_id, aliyun_sms_product,  aliyun_sms_domain);
			request.setSignName(aliyun_sms_sign_name);
			request.setTemplateCode(SMSModel);
			paramMap.put("product", aliyun_sms_sign_name);
			request.setParamString(JSON.toJSONString(paramMap));
			request.setRecNum(cellphone);
			client.getAcsResponse(request);
			isSendSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
            return isSendSuccess;
        }
	}
	/*@SuppressWarnings("finally")
	public static boolean sendMail(String mail,String title,String content) {   
		boolean isSendSuccess = false;
		   try {
			   IClientProfile profile = DefaultProfile.getProfile(aliyun_sms_region_id, aliyun_sms_access_key_id, aliyun_sms_access_key_secret);
	            IAcsClient client = new DefaultAcsClient(profile);
	            //单一发信接口 
	            SingleSendMailRequest request = new SingleSendMailRequest();
	            request.setAccountName(aliyun_mail_user);
	            request.setFromAlias(aliyun_mail_nickname);
	            request.setAddressType(1);
	            request.setTagName(aliyun_mail_sign);
	            request.setReplyToAddress(true);
	            request.setToAddress(mail);
	            request.setSubject(title);
	            request.setHtmlBody(content);
	            //批量发信接口
	            BatchSendMailRequest request=new BatchSendMailRequest();
	            request.setAccountName(aliyun_mail_user);
	            request.setAddressType(1);
	            request.setTagName(aliyun_mail_sign);
	            request.setTemplateName(aliyun_mail_model);
	            request.setReceiversName(mail);
	            //发送邮件
	            client.getAcsResponse(request);
	            System.out.println("发送成功");
	            isSendSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
            return isSendSuccess;
        }
	}*/
	@SuppressWarnings("finally")
	public static boolean sendMailBySmtp(String mail,String title,String content) {   
		boolean isSendSuccess = false;
		// 配置发送邮件的环境属性
		final Properties props = new Properties();
		// 表示SMTP发送邮件，需要进行身份验证
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", aliyun_mail_smtp_host);
		props.put("mail.smtp.port", aliyun_mail_smtp_port);   
		// 如果使用ssl，则去掉使用25端口的配置，进行如下配置, 
		// props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// props.put("mail.smtp.socketFactory.port", "465");
		// props.put("mail.smtp.port", "465");
		// 发件人的账号
		props.put("mail.user", aliyun_mail_user);
		// 访问SMTP服务时需要提供的密码
		props.put("mail.password",aliyun_mail_password);

		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		try {
			InternetAddress form = new InternetAddress(
					props.getProperty("mail.user"));
			message.setFrom(form);
			// 设置收件人
			InternetAddress to = new InternetAddress(mail);
			message.setRecipient(MimeMessage.RecipientType.TO, to);
			// 设置邮件标题
			message.setSubject(title);
			// 设置邮件的内容体
			message.setContent(content, "text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
			isSendSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
            return isSendSuccess;
        }
	}
	

	public static String getAliyun_sms_domain() {
		return aliyun_sms_domain;
	}
	public static void setAliyun_sms_domain(String aliyun_sms_domain) {
		AliyunUtil.aliyun_sms_domain = aliyun_sms_domain;
	}
	public static String getAliyun_sms_region_id() {
		return aliyun_sms_region_id;
	}
	public static void setAliyun_sms_region_id(String aliyun_sms_region_id) {
		AliyunUtil.aliyun_sms_region_id = aliyun_sms_region_id;
	}
	public static String getAliyun_sms_access_key_id() {
		return aliyun_sms_access_key_id;
	}
	public static void setAliyun_sms_access_key_id(String aliyun_sms_access_key_id) {
		AliyunUtil.aliyun_sms_access_key_id = aliyun_sms_access_key_id;
	}
	public static String getAliyun_sms_access_key_secret() {
		return aliyun_sms_access_key_secret;
	}
	public static void setAliyun_sms_access_key_secret(
			String aliyun_sms_access_key_secret) {
		AliyunUtil.aliyun_sms_access_key_secret = aliyun_sms_access_key_secret;
	}
	public static String getAliyun_sms_product() {
		return aliyun_sms_product;
	}
	public static void setAliyun_sms_product(String aliyun_sms_product) {
		AliyunUtil.aliyun_sms_product = aliyun_sms_product;
	}
	public static String getAliyun_sms_sign_name() {
		return aliyun_sms_sign_name;
	}
	public static void setAliyun_sms_sign_name(String aliyun_sms_sign_name) {
		AliyunUtil.aliyun_sms_sign_name = aliyun_sms_sign_name;
	}
	public static String getAliyun_mail_smtp_host() {
		return aliyun_mail_smtp_host;
	}
	public static void setAliyun_mail_smtp_host(String aliyun_mail_smtp_host) {
		AliyunUtil.aliyun_mail_smtp_host = aliyun_mail_smtp_host;
	}
	public static String getAliyun_mail_smtp_port() {
		return aliyun_mail_smtp_port;
	}
	public static void setAliyun_mail_smtp_port(String aliyun_mail_smtp_port) {
		AliyunUtil.aliyun_mail_smtp_port = aliyun_mail_smtp_port;
	}
	public static String getAliyun_mail_user() {
		return aliyun_mail_user;
	}
	public static void setAliyun_mail_user(String aliyun_mail_user) {
		AliyunUtil.aliyun_mail_user = aliyun_mail_user;
	}
	public static String getAliyun_mail_password() {
		return aliyun_mail_password;
	}
	public static void setAliyun_mail_password(String aliyun_mail_password) {
		AliyunUtil.aliyun_mail_password = aliyun_mail_password;
	}
	public static String getAliyun_mail_nickname() {
		return aliyun_mail_nickname;
	}
	public static void setAliyun_mail_nickname(String aliyun_mail_nickname) {
		AliyunUtil.aliyun_mail_nickname = aliyun_mail_nickname;
	}
	public static String getAliyun_mail_sign() {
		return aliyun_mail_sign;
	}
	public static void setAliyun_mail_sign(String aliyun_mail_sign) {
		AliyunUtil.aliyun_mail_sign = aliyun_mail_sign;
	}
	public static String getAliyun_mail_model() {
		return aliyun_mail_model;
	}
	public static void setAliyun_mail_model(String aliyun_mail_model) {
		AliyunUtil.aliyun_mail_model = aliyun_mail_model;
	}
	
}
