package com.credit.util.tree;

import java.io.Serializable;
import java.util.ArrayList;

public class TreeNode implements Serializable{
	
	private static final long serialVersionUID = -2586556155939349469L;

	private String id;
	
	private String name;
	
	private String parent;
	
	private Object data;
	
	private ArrayList<TreeNode> childs;

	public TreeNode() {
		super();
		this.childs = new ArrayList<TreeNode>();
	}
	
	public TreeNode(String id, String name, String parent, Object data) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.data = data;
		this.childs = new ArrayList<TreeNode>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ArrayList<TreeNode> getChilds() {
		return childs;
	}

	public void setChilds(ArrayList<TreeNode> childs) {
		this.childs = childs;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("id:" + this.id + ", ");
		sb.append("name:" + this.name + ", ");
		sb.append("parent" + this.parent + ", ");
		sb.append("data" + this.data + ", ");
		sb.append("}\n");
		return sb.toString();
	}
	
}
