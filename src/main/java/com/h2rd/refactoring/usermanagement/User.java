package com.h2rd.refactoring.usermanagement;

import java.util.List;

/*
 * User comments
 */
class User {

   /**
    * Id comment
    */
	private Integer id;

	/*
	 * name
	 */
	private String name;

	/**
	 * email
	 */
	private String email;

	private List<String> roles;

	public User(Integer id, String name, String email, List<String> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public User(String name, String email, List<String> roles) {
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
