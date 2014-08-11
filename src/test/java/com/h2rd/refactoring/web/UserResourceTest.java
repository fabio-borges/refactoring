package com.h2rd.refactoring.web;

import static org.mockito.Mockito.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.h2rd.refactoring.usermanagement.UserDTO;
import com.h2rd.refactoring.usermanagement.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {

	@Mock
	private UserService userService;

	private UserQueryFactory userQueryFactory = new UserQueryFactoryImpl();

	private UserResource userResource;

	@Before
	public void prepare() {
		this.userResource = new UserResource(userService, userQueryFactory);
	}

	@Test
	public void addUserSuccessfully() {

		final String name = "Peter Pan";
		final String email = "peterpan@neverland.com";
		final List<String> roles = Arrays.asList("r1", "r2");
		final Integer id = 123;
		final URI finalUri = URI.create("http://server/users/" + id);

		UserDTO user = new UserDTO(name, email, roles);
		UserDTO createdUser = new UserDTO(id, name, email, roles);
		when(userService.saveUser(user)).thenReturn(createdUser);

		UriInfo uriInfo = mock(UriInfo.class);
		UriBuilder uriBuilder = mock(UriBuilder.class);
		when(uriInfo.getAbsolutePathBuilder()).thenReturn(uriBuilder);
		when(uriBuilder.path(id.toString())).thenReturn(uriBuilder);
		when(uriBuilder.build()).thenReturn(finalUri);

		Response response = userResource.addUser(user, uriInfo);
		
		verify(userService).saveUser(user);
		verify(uriBuilder).path(id.toString());
		
		Assert.assertEquals(Status.CREATED.getStatusCode(),
				response.getStatus());
		Assert.assertEquals(finalUri,
				response.getMetadata().getFirst("Location"));

	}
	
	// Other tests ...
}
