package com.credit.bean.vo.privilege;

import java.util.ArrayList;

public class OrgNode {
	private String id;
	
	private String name;
	
	private String parentId;
	
	private ArrayList<OrgNode> childOrgs;
	
	public OrgNode(){
		super();
		this.id = "";
		this.name = "";
		this.parentId = "";
		this.childOrgs = new ArrayList<OrgNode>();
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public ArrayList<OrgNode> getChildOrgs() {
		return childOrgs;
	}

	public void setChildOrgs(ArrayList<OrgNode> childOrgs) {
		this.childOrgs = childOrgs;
	}
	
	
}
