package com.h2rd.refactoring.web;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Component;

import com.h2rd.refactoring.usermanagement.UserQuery;

@Component
class UserQueryFactoryImpl implements UserQueryFactory {

	/**
	 * Could handle other parameters for sorting, paging e field selecting.
	 * Should throw a validation exception for fields not supported for searching.  
	 */
	public UserQuery getUserQuery(final MultivaluedMap<String, String> form) {
		final UserQuery query = new UserQuery();
		query.setName(form.getFirst("name"));
		return query;
	}

}
