package com.credit.util.checkbox;

public class CheckBox {
	private String boxLabel;
	private String name="SP";
	private String inputValue;
	private boolean checked;
	private boolean disabled;
	private String value;
	
	public CheckBox(){}
	public CheckBox(String boxLabel, String name, String inputValue,
			boolean checked,boolean disabled) {
		super();
		this.boxLabel = boxLabel;
		this.name = name;
		this.inputValue = inputValue;
		this.checked = checked;
		this.disabled=disabled;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((boxLabel == null) ? 0 : boxLabel.hashCode());
		result = prime * result + (checked ? 1231 : 1237);
		result = prime * result
				+ ((inputValue == null) ? 0 : inputValue.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		CheckBox other = (CheckBox) obj;
		if (boxLabel == null) {
			if (other.boxLabel != null)
				return false;
		} else if (!boxLabel.equals(other.boxLabel))
			return false;
		if (checked != other.checked)
			return false;
		if (inputValue == null) {
			if (other.inputValue != null)
				return false;
		} else if (!inputValue.equals(other.inputValue))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public String getBoxLabel() {
		return boxLabel;
	}
	public void setBoxLabel(String boxLabel) {
		this.boxLabel = boxLabel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "CheckBox [boxLabel=" + boxLabel + ", name=" + name
				+ ", inputValue=" + inputValue + ", checked=" + checked + "]";
	};
}
