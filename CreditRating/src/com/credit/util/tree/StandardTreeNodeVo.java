package com.credit.util.tree;

import java.util.Set;

public class StandardTreeNodeVo {
	private String id;
	private String text;
	private boolean expanded;
	private boolean leaf;
	private boolean checked;
	private Set<StandardTreeNodeVo> children;
	
	public StandardTreeNodeVo(){}

	public StandardTreeNodeVo(String id, String text, boolean expanded,
			boolean leaf, boolean checked, Set<StandardTreeNodeVo> children) {
		super();
		this.id = id;
		this.text = text;
		this.expanded = expanded;
		this.leaf = leaf;
		this.checked = checked;
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Set<StandardTreeNodeVo> getChildren() {
		return children;
	}

	public void setChildren(Set<StandardTreeNodeVo> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardTreeNodeVo other = (StandardTreeNodeVo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StandardTreeNode [id=" + id + ", text=" + text + ", expanded="
				+ expanded + ", leaf=" + leaf + ", children=" + children + "]";
	}
}
