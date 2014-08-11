package com.h2rd.refactoring.web;

import javax.ws.rs.core.MultivaluedMap;

import com.h2rd.refactoring.usermanagement.UserQuery;

interface UserQueryFactory {
	
	public UserQuery getUserQuery(MultivaluedMap<String, String> form);
	
}
