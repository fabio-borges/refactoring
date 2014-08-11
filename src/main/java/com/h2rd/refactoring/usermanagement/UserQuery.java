package com.h2rd.refactoring.usermanagement;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class UserQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	/*
	 * We could add here paging, sorting, field selection...
	 */

	public UserQuery() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEmpty() {
		return StringUtils.isBlank(this.name);
	}

}
