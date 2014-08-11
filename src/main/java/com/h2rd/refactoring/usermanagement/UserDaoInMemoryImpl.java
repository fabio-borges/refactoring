package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

@Repository
class UserDaoInMemoryImpl implements UserDao {

	private final ArrayList<User> users = new ArrayList<User>();
	private final Map<Integer, User> usersById = new HashMap<Integer, User>();

	private AtomicInteger idGenerator = new AtomicInteger();

	public void saveUser(final User user) {
		user.setId(idGenerator.incrementAndGet());
		users.add(user);
		usersById.put(user.getId(), user);
	}

	public User findUserById(Integer id) {
		return usersById.get(id);
	}

	public User deleteUser(Integer id) throws UserNotFoundException {
		User user = usersById.get(id);
		if (user != null) {
			users.remove(user);
			usersById.remove(id);
			return user;
		} else {
			throw new UserNotFoundException();
		}
	}

	public UserSearchResult searchUsers(UserQuery userQuery) {
		return new UserSearchResult(toDTO(searchUsersAsEntities(userQuery)));
	}

	List<User> searchUsersAsEntities(UserQuery query) {
		List<User> result = this.users;
		if (!query.isEmpty()) {
			result = new ArrayList<User>();
			for (User user : users) {
				if (query.getName().equals(user.getName())) {
					result.add(user);
				}
			}
		}
		return result;
	}

	private List<UserDTO> toDTO(List<User> userList) {
		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		for (User user : userList)
			dtoList.add(new UserDTO(user));
		return dtoList;
	}

}
