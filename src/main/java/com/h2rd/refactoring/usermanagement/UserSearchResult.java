package com.h2rd.refactoring.usermanagement;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSearchResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pagingInfo = "Paging info goes here";
	
	@XmlElementWrapper(name="users")
	@XmlElement(name="user")
	private List<UserDTO> users;

	public UserSearchResult() {
	}

	public UserSearchResult(List<UserDTO> users) {
		this.users = users;
	}

	public String getPagingInfo() {
		return pagingInfo;
	}

	public void setPagingInfo(String pagingInfo) {
		this.pagingInfo = pagingInfo;
	}

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

}
