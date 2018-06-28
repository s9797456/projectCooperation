package com.credit.filter;

import java.lang.reflect.Method;
import java.util.Set;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import com.credit.model.privilege.C_SystemPrivilegeKey;
import com.credit.modelvo.Permission;
import com.credit.modelvo.SessionName;


public class PermissionInterceptor  extends HandlerInterceptorAdapter  {  

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			Permission authPassport = ((HandlerMethod) handler).getMethodAnnotation(Permission.class);
			// 没有声明需要权限,或者声明不验证权限
			if (authPassport == null)
				return true;
			else {
				if (validate(handler, request)) {
					return true;
				}else{
					System.out.println("权限不足，无法登陆");
					request.getRequestDispatcher("/WEB-INF/Page/permission.jsp").forward(request, response);
					return false;
				}
			}
		} else{
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	private boolean validate(Object handler, HttpServletRequest request) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String[] arr1;
		String[] arr2;
		Method method = null;
		try {
			method = handlerMethod.getMethod();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		if (method != null && method.isAnnotationPresent(Permission.class)) {
			Permission permission = method.getAnnotation(Permission.class);
			System.out.println("[开始检验！方法名："+method+"]");
			System.out.println("[开始检验！权限："+permission.model()+"："+permission.privilegeValue()+"]");
			if (permission.model().contains(",")) {
				arr1 = permission.model().split(",");
				arr2 = permission.privilegeValue().split(",");
				for (int i = 0; i < arr1.length; i++) {
					C_SystemPrivilegeKey methodPrivilege = new C_SystemPrivilegeKey(arr1[i], arr2[i]);
					Set<C_SystemPrivilegeKey> cspks = (Set<C_SystemPrivilegeKey>) request.getSession().getAttribute(SessionName.PRIVILEGE);
					if(cspks.contains(methodPrivilege)) return true;
				}
			} else {
				C_SystemPrivilegeKey methodPrivilege = new C_SystemPrivilegeKey(permission.model(), permission.privilegeValue());
				Set<C_SystemPrivilegeKey> cspks = (Set<C_SystemPrivilegeKey>) request.getSession().getAttribute(SessionName.PRIVILEGE);
				if(cspks.contains(methodPrivilege))return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unused")
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
}
