<%@ page language="java" pageEncoding="UTF-8"%>
				 <nav>
				    <ul class="ucenterNav" id="list">
				        <li class="navIndex">
				                <a href="<%=path %>/customer/jumpToOrgMain.do"><i class="iconfont"></i>我的主页</a>
				        </li>
				        <li >
				                <a href="<%=path %>/initOrganization/scoringEnterpriseList.do"><i class="iconfont"></i>评分企业列表</a>
				        </li>
                
				        <li class="navInfoLi">
				            <a id="ucenterSubA"><i class="iconfont"></i>用户中心<i class="centerIcon subDown"></i></a>
				            <ul class="ucenterSub">
				                <li ><a href="<%=path %>/main/userInfoUI.do"><i class="iconfont"></i>账户信息</a></li>
				                <li ><a href="javascript:updatePassword('${Crc_customer.realname }','${Crc_customer.cellphone }')"><i class="iconfont"></i>密码修改</a></li>
				                <li ><a href="javascript:logout()"><i class="iconfont"></i>退出登录</a></li>
				                <li>&nbsp;</li>
				            </ul>
				        </li>
				    </ul>
				</nav>
