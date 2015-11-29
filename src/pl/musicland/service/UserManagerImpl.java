package pl.musicland.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import pl.musicland.model.User;
import pl.musicland.dao.UserDAO;;

@Service
public class UserManagerImpl implements UserManager {
	
	@Autowired
	UserDAO dao;

	@Override
	public List<User> gatAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public void insertUser(User user) {
		dao.insertUser(user);
	}

	@Override
	public User loginUser(String email, String password) {
		return dao.loginUser(email, password);
	}

}
