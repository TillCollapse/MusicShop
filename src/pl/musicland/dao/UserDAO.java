package pl.musicland.dao;

import java.util.List;
import java.util.Map;

import pl.musicland.model.User;
import pl.musicland.model.UserAuthority;

public interface UserDAO {
	public List<User> getAllUsers();

	public void insertUser(User user);

	public User loginUser(String email, String password);

	public String getUserName(String email);

	public int getUserId(String email);

	public List<UserAuthority> getUserAuthority();

	public List<Map<String, Object>> getUserDetails();

	public User getUserById(int userid);
}
