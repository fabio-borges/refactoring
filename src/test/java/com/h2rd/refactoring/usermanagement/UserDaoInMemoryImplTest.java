package com.h2rd.refactoring.usermanagement;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class UserDaoInMemoryImplTest {

	@Test
	public void saveUserTest() {
		final UserDaoInMemoryImpl userDao = new UserDaoInMemoryImpl();

		final User user1 = new User("Fake Name", "fake@email.com",
				Arrays.asList("admin", "master"));
		final User user2 = new User("Fake Name 2", "fake2@email.com",
				Arrays.asList("admin2", "master2"));

		userDao.saveUser(user1);
		checkCreation(user1, userDao);

		userDao.saveUser(user2);
		checkCreation(user2, userDao);

		Assert.assertFalse(user1.equals(user2));
		Assert.assertEquals(2, userDao.searchUsersAsEntities(new UserQuery())
				.size());
	}

	@Test
	public void deleteUserTest() throws UserNotFoundException {
		final UserDaoInMemoryImpl userDao = new UserDaoInMemoryImpl();

		final User user = new User("Fake Name", "fake@email.com",
				Arrays.asList("admin", "master"));

		userDao.saveUser(user);
		Assert.assertNotNull(userDao.findUserById(user.getId()));

		userDao.deleteUser(user.getId());
		Assert.assertNull(userDao.findUserById(user.getId()));
	}

	@Test
	public void searchUsersTest() {
		final UserDaoInMemoryImpl userDao = new UserDaoInMemoryImpl();

		final String searchName = "Fake Name";

		userDao.saveUser(new User("Fake 2", "fake@email.com", Arrays.asList(
				"admin", "master")));

		userDao.saveUser(new User(searchName, "fake@email.com", Arrays.asList(
				"admin", "master")));

		userDao.saveUser(new User("Fake 2", "fake@email.com", Arrays.asList(
				"admin", "master")));

		userDao.saveUser(new User(searchName, "fake@email.com", Arrays.asList(
				"admin", "master")));

		final UserQuery query = new UserQuery();
		query.setName(searchName);

		final List<User> result = userDao.searchUsersAsEntities(query);
		Assert.assertEquals(2, result.size());
	}

	private void checkCreation(User user, UserDaoInMemoryImpl userDao) {
		Assert.assertNotNull(user.getId());
		Assert.assertTrue(haveIdenticalData(user,
				userDao.findUserById(user.getId())));
	}

	private boolean haveIdenticalData(User user1, User user2) {
		return user1.getName().equals(user2.getName())
				&& user1.getEmail().equals(user2.getEmail())
				&& user1.getRoles().size() == user2.getRoles().size()
				&& user1.getRoles().containsAll(user2.getRoles())
				&& user2.getRoles().containsAll(user1.getRoles());
	}

}