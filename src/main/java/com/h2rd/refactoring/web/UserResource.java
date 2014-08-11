package com.h2rd.refactoring.web;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.h2rd.refactoring.usermanagement.UserDTO;
import com.h2rd.refactoring.usermanagement.UserNotFoundException;
import com.h2rd.refactoring.usermanagement.UserQuery;
import com.h2rd.refactoring.usermanagement.UserSearchResult;
import com.h2rd.refactoring.usermanagement.UserService;

@Component
@Path("/users")
public class UserResource {

	private UserService userService;

	private UserQueryFactory userQueryFactory;
	
	@Autowired
	public UserResource(UserService userService,
			UserQueryFactory userQueryFactory) {
		super();
		this.userService = userService;
		this.userQueryFactory = userQueryFactory;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addUser(UserDTO user, @Context UriInfo uriInfo) {
		Integer userId = userService.saveUser(user).getId();
		URI uri = uriInfo.getAbsolutePathBuilder().path(userId.toString())
				.build();
		return Response.created(uri).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{id}")
	public Response updateUser(@PathParam("id") Integer id, UserDTO userToUpdate) {
		userToUpdate.setId(id);
		try {
			return Response.ok().entity(userService.updateUser(userToUpdate))
					.build();
		} catch (UserNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") Integer id) {
		try {
			return Response.ok().entity(userService.deleteUser(id)).build();
		} catch (UserNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public UserSearchResult getUsers() {
		return userService.searchUsers(new UserQuery());
	}

	@GET
	@Path("/{id}")
	public Response findUser(@PathParam("id") Integer id) {
		final UserDTO userDTO = userService.getUser(id);
		if (userDTO != null) {
			return Response.ok().entity(userService.getUser(id)).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_XML)
	public UserSearchResult findUser(@Context UriInfo uriInfo) {
		final UserQuery query = userQueryFactory.getUserQuery(uriInfo
				.getQueryParameters());
		return userService.searchUsers(query);
	}
}
