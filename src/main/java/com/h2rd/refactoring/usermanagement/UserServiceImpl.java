package com.h2rd.refactoring.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public UserServiceImpl() {
	}

	public UserDTO saveUser(final UserDTO userDto) {
		final User user = userDto.toUserEntity();
		userDao.saveUser(user);
		return new UserDTO(user);
	}

	public UserDTO updateUser(final UserDTO userToUpdate)
			throws UserNotFoundException {
		final User user = userDao.findUserById(userToUpdate.getId());
		user.setName(userToUpdate.getName());
		user.setEmail(userToUpdate.getEmail());
		user.setRoles(userToUpdate.getRoles());
		return new UserDTO(user);
	}

	public UserSearchResult searchUsers(final UserQuery userQuery) {
		return userDao.searchUsers(userQuery);
	}

	public UserDTO getUser(final Integer id) {
		final User user = userDao.findUserById(id);
		return user != null ? new UserDTO(user) : new UserDTO();
	}

	public UserDTO deleteUser(final Integer id) throws UserNotFoundException {
		return new UserDTO(userDao.deleteUser(id));
	}

}
