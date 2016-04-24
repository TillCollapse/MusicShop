package pl.musicland.dao;

import java.util.List;

import pl.musicland.model.Authorities;

public interface AuthoritiesDAO {
	public List<Authorities> getAllAuthorities();

	public List<String> getAuthorityTypes();

	public boolean changeUserAuthority(String email, String authority);

	public boolean insertAthorityForUser(String email);
}
