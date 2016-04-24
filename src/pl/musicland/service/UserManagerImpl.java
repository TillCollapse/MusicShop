package pl.musicland.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import pl.musicland.model.User;
import pl.musicland.model.UserAuthority;
import pl.musicland.dao.UserDAO;;

@Service
public class UserManagerImpl implements UserManager {

	@Autowired
	UserDAO dao;

	@Override
	public void insertUser(User user) {
		dao.insertUser(user);
	}

	@Override
	public User loginUser(String email, String password) {
		return dao.loginUser(email, password);
	}

	@Override
	public String getUserName(String email) {
		return dao.getUserName(email);
	}

	@Override
	public int getUserId(String email) {
		return dao.getUserId(email);
	}

	@Override
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public List<UserAuthority> getUserAuthority() {
		return dao.getUserAuthority();
	}

	@Override
	public List<Map<String, Object>> getUserDetails() {
		return dao.getUserDetails();
	}

	@Override
	public User getUserById(int userid) {
		return dao.getUserById(userid);
	}

}
