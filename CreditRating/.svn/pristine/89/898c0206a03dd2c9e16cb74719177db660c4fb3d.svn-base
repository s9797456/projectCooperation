package com.credit.bean.privilege;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

//菜单   --  菜单对权限一对多 
@Entity
@Table(name="TP_Menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = -1275489117507208109L;
	//主键
	@Id @Column(length=36)
	private String uuid;
	//名称
	@Column(length=100,nullable=false)
	private String name;
	//菜单编号
	@Column(length=200)
	private String sn;
	//排序号
	@Column(nullable=false)
	private int orderNO;

	//地址
	@Column(length=200)
	private String url;

	@Column(length=200)
	private String target;

	@Column(length=200)
	private String rel;

	@Column(nullable=false)
	private Integer visible=1;
	//代表图片
	@Column(length=200)
	private String imgUrl;

	//fetch=FetchType.EAGER   EAGER立即加载LAZY是延迟加载
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent",fetch=FetchType.LAZY)
	@OrderBy("orderNO ASC")
	private Set<Menu> childs = new HashSet<Menu>();


	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="parentID")
	private Menu parent;
	
	

	@ManyToMany(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinTable(name="TP_menu_privilege",joinColumns=@JoinColumn(name="menuID"),
			inverseJoinColumns={@JoinColumn(name="model", referencedColumnName="model"),
								@JoinColumn(name="privilegeValue", referencedColumnName="privilegeValue")})
	private Set<SystemPrivilege> systemPrivileges = new HashSet<SystemPrivilege>();

	/**
	 * 添加权限
	 * @param privilege 权限
	 */
	public void addPrivilege(SystemPrivilege privilege) {
		this.systemPrivileges.add(privilege);
	}
	
	
	public Menu(String uuid) {
		this.uuid = uuid;
	}
	public Menu() {
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		final Menu other = (Menu) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	public Set<SystemPrivilege> getSystemPrivileges() {
		return systemPrivileges;
	}
	public void setSystemPrivileges(Set<SystemPrivilege> systemPrivileges) {
		this.systemPrivileges = systemPrivileges;
	}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVisible() {
		return visible;
	}
	public void setVisible(Integer visible) {
		this.visible = visible;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public int getOrderNO() {
		return orderNO;
	}
	public void setOrderNO(int orderNO) {
		this.orderNO = orderNO;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Menu> getChilds() {
	/*	List<Menu> list = new ArrayList<Menu>(childs);
		Collections.sort(list, new Comparator<Menu>(){
		    public int compare(Menu o1, Menu o2) {
		        //按照升序排列  
		        if(o1.getOrderNO() > o2.getOrderNO()){  
		        	return 1;  
		        }  
		        if(o1.getOrderNO() == o2.getOrderNO()){  
		          return 0;  
		        }  
		        return -1;  
		      }  
		  }); */
		return childs;
	}
	public void setChilds(Set<Menu> childs) {
		this.childs = childs;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}

}
