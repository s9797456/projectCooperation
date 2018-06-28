package com.credit.util.checkbox;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxGroup {
	private int padding =10;
	private String xtype="checkboxgroup";
	private List<CheckBox>items =new ArrayList<CheckBox>();
	private String fieldLabel;
	
	public CheckBoxGroup(){}

	public CheckBoxGroup(int padding, String xtype, List<CheckBox> items,
			String fieldLabel) {
		super();
		this.padding = padding;
		this.xtype = xtype;
		this.items = items;
		this.fieldLabel = fieldLabel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fieldLabel == null) ? 0 : fieldLabel.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + padding;
		result = prime * result + ((xtype == null) ? 0 : xtype.hashCode());
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
		CheckBoxGroup other = (CheckBoxGroup) obj;
		if (fieldLabel == null) {
			if (other.fieldLabel != null)
				return false;
		} else if (!fieldLabel.equals(other.fieldLabel))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (padding != other.padding)
			return false;
		if (xtype == null) {
			if (other.xtype != null)
				return false;
		} else if (!xtype.equals(other.xtype))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CheckBoxGroup [padding=" + padding + ", xtype=" + xtype
				+ ", items=" + items + ", fieldLabel=" + fieldLabel + "]";
	}

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public List<CheckBox> getItems() {
		return items;
	}

	public void setItems(List<CheckBox> items) {
		this.items = items;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
}
