package com.credit.ws.auth;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddHeaderInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	private String userId;
	private String userPass;
	
	public AddHeaderInterceptor(String userId, String userPass) {
		super(Phase.PREPARE_SEND);//准备发送soap消息时启用该拦截器
		this.userId=userId;
		this.userPass=userPass;
	}


	public void handleMessage(SoapMessage msg) throws Fault {
		List<Header> headers = msg.getHeaders();
		
		Document doc = DOMUtils.createDocument();
		Element ele = doc.createElement("authHeader");

		Element idEle = doc.createElement("userId");
		idEle.setTextContent(userId);
		Element passEle = doc.createElement("userPass");
		passEle.setTextContent(userPass);
		
		ele.appendChild(idEle);
		ele.appendChild(passEle);

		headers.add(new Header(new QName("cfxServer"),ele));
	}

}
