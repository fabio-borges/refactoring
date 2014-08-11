package com.h2rd.refactoring.usermanagement;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private Integer id;

	private String name;

	private String email;

	@XmlElement(name = "role")
	private List<String> roles;

	UserDTO(User user) {
		this(user.getId(), user.getName(), user.getEmail(), user.getRoles());
	}

	public UserDTO() {
	}

	public UserDTO(Integer id, String name, String email, List<String> roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public UserDTO(String name, String email, List<String> roles) {
		this(null, name, email, roles);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	User toUserEntity() {
		return new User(this.id, this.name, this.email, this.roles);
	}

	@Override
	public int hashCode() {
		return 31 + ((id == null) ? 0 : id.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
