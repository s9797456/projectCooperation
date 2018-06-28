package com.credit.util.tree;

import java.util.ArrayList;

public class OrgTreeHelper {
	/**
	 * 构建出机构下拉菜单的初始化数据
	 * 根据传入的用户可见的ArrayList<TreeNode>，返回符合zTree控件simpleData格式的初始化数据的字符串
	 * 
	 * @param tnList 用户可见的ArrayList<TreeNode>
	 * @return 初始化字符串
	 */
	public static String getSeeOrgTreeJsonData(ArrayList<TreeNode> tnList) {
		StringBuilder sb = new StringBuilder();
		int size = tnList.size();
		TreeNode userOrgTn = null;
		if(size > 0) {
			userOrgTn = tnList.get(size-1);
			tnRecursive(userOrgTn, sb);
		}
		int sbLen = sb.length();
		//若有节点需要被显示的话则删掉最后一个“,”
		if(sbLen > 0) sb.deleteCharAt(sbLen-1);
		sb.insert(0, "[");
		sb.append("]");
		return sb.toString();
	}
	
	public static void tnRecursive(TreeNode tn, StringBuilder sb) {
		convertTnToZTreeJson(sb, tn);
		for(TreeNode child : tn.getChilds()) {
			tnRecursive(child, sb);
		}
	}
	
	public static void convertTnToZTreeJson(StringBuilder sb, TreeNode tn) {
		if(tn != null) {
			sb.append("{\"id\":\"");
			sb.append(tn.getId());
			sb.append("\", \"name\":\"");
			sb.append(tn.getName());
			sb.append("\", \"sn\":\"");
			sb.append(tn.getData());
			sb.append("\", \"parent\":\"");
			sb.append(tn.getParent());
			sb.append("\", \"open\":\"");
			sb.append(true);
			sb.append("\"},");
		}
	}
	
}
