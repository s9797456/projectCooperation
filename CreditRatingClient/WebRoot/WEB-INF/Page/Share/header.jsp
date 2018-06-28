<%@ page language="java" pageEncoding="UTF-8"%>
<div id="header" >
			 <header >
			    <a class="centerLogo" style="position:relative;color: #00a0e9;text-decoration:none;border:0px;">
			    <c:if test="${Crc_customer.type == 1 }">
							<div style="position:relative;margin-left: 30px;"><label style="font-size: 2rem; margin-top: -5px;">企业信用评价平台</label><p style="font-size: 1rem; margin-top: -5px;">enterprise credit evaluation platform</p></div>
				</c:if>
				<c:if test="${Crc_customer.type == 0 }">
						<img style="position:relative;margin-top: -5px;margin-left: 10px;" src="<%=path %>/Images/replaceImage/mainlogo.png" alt="企业信用评价平台">
				</c:if>
			    <c:if test="${Crc_customer.type == 2 }">
						<img style="position:relative;margin-top: -5px;margin-left: 10px;" src="<%=path %>/Images/replaceImage/mainlogo.png" alt="企业信用评价平台">
				</c:if>
			    </a>
			    <ul class="headerUl">
							<li class="headerLi1">
								<div class="headerInfo">
									<span>
										<c:if test="${! empty Crc_entName}">${Crc_entName}</c:if>
										<c:if test="${ empty Crc_entName}">尊敬的用户</c:if>
									</span>
									<i class="headerIcon1 centerIcon"></i>
								</div> 
								<a class="centerLogout" target="_blank" href="<%=path %>/Resources/operation/front.pdf">操作手册</a>
								<a class="centerLogout" style=" top: 100px;" onclick="logout()">退出登录</a>
							</li>
							
			        <li class="headerLi3">
			            <div ><span>关注</span><i class="headerIcon1 centerIcon"></i></div>
			            <div class="juheCode">
			                <span class="centerIcon"></span>
			            </div>
			        </li>
			        <li>
			            <a href="javascript:void(0);" onclick="contactInfo('${corp_name}','${corp_phone}','${corp_email}','${corp_address}')" >联系我们</a>
			        </li>
			    </ul>
			</header>
		</div>