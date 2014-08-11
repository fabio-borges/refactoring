package com.h2rd.refactoring.usermanagement;

interface UserDao {

	void saveUser(final User user);

	User findUserById(Integer id);

	User deleteUser(Integer id) throws UserNotFoundException;

	UserSearchResult searchUsers(UserQuery userQuery);

}
