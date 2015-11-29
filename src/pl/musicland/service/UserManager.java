package pl.musicland.service;

import java.util.List;
import pl.musicland.model.User;

public interface UserManager {
	public List<User> gatAllUsers();
	public void insertUser(User user);
	public User loginUser(String email, String password);
}
