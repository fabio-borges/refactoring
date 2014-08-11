package com.h2rd.refactoring.usermanagement;

public interface UserService {

	public UserDTO saveUser(UserDTO user);

	public UserDTO updateUser(UserDTO userToUpdate)
			throws UserNotFoundException;

	public UserSearchResult searchUsers(UserQuery searchCritetia);

	public UserDTO getUser(Integer id);

	public UserDTO deleteUser(Integer id) throws UserNotFoundException;

}
