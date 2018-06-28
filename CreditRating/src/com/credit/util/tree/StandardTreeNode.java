package com.credit.util.tree;

import java.util.ArrayList;

public class StandardTreeNode {
	private String id;
	
	private String name;
	
	private StandardTreeNode parent;
	
	private ArrayList<Object> datas;
	
	private ArrayList<StandardTreeNode> children;

	public StandardTreeNode() {
		super();
		this.children = new ArrayList<StandardTreeNode>();
	}
	
	public StandardTreeNode(String id, String name, StandardTreeNode parent, ArrayList<Object> datas) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.datas = datas;
		this.children = new ArrayList<StandardTreeNode>();
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

	public ArrayList<Object> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<Object> datas) {
		this.datas = datas;
	}

	public ArrayList<StandardTreeNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<StandardTreeNode> children) {
		this.children = children;
	}

	public StandardTreeNode getParent() {
		return parent;
	}

	public void setParent(StandardTreeNode parent) {
		this.parent = parent;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("id:" + this.id + ", ");
		sb.append("name:" + this.name + ", ");
		sb.append("data" + this.datas);
		sb.append("}\n");
		return sb.toString();
	}
	
}
