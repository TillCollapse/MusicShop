package pl.musicland.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.musicland.dao.AuthoritiesDAO;
import pl.musicland.model.Authorities;;

@Service
public class AuthoritiesManagerImpl implements AuthoritiesManager {

	@Autowired
	AuthoritiesDAO dao;

	public List<Authorities> getAllAuthorities() {
		return dao.getAllAuthorities();
	}

	@Override
	public List<String> getAuthorityTypes() {
		return dao.getAuthorityTypes();
	}

	@Override
	public boolean changeUserAuthority(String email, String authority) {
		return dao.changeUserAuthority(email, authority);
	}

	@Override
	public boolean insertAthorityForUser(String email) {
		return dao.insertAthorityForUser(email);
	}
}
