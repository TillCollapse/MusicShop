package pl.musicland.service;

import pl.musicland.model.Authorities;
import java.util.List;

public interface AuthoritiesManager {
	public List<Authorities> getAllAuthorities();

	public List<String> getAuthorityTypes();

	public boolean changeUserAuthority(String email, String authority);

	public boolean insertAthorityForUser(String email);
}
