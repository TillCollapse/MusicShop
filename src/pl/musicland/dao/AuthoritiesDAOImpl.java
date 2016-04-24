package pl.musicland.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.musicland.jdbc.AuthoritiesRowMapper;
import pl.musicland.model.Authorities;

@Repository
public class AuthoritiesDAOImpl implements AuthoritiesDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Authorities> getAllAuthorities() {
		String SQL = "SELECT authorityid, authorities.email, authorities.authority from users, authorities WHERE enabled='1' and users.email = authorities.email;";
		List<Authorities> authorities = null;
		try {
			authorities = jdbcTemplate.query(SQL, new AuthoritiesRowMapper());
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return authorities;
	}

	@Override
	public List<String> getAuthorityTypes() {
		String SQL = "select authority from authorities group by authority";
		List<String> authorities = null;
		try {
			authorities = jdbcTemplate.queryForList(SQL, String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return authorities;
	}

	@Override
	public boolean changeUserAuthority(String email, String authority) {
		String SQL = "update authorities set authority = ? where email = ?";
		Integer result = null;
		boolean value = false;
		try {
			result = jdbcTemplate.update(SQL, authority, email);
			if (result.intValue() != 0) {
				value = true;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return value;
	}

	@Override
	public boolean insertAthorityForUser(String email) {
		String authority = "ROLE_USER";
		String SQL = "insert into authorities(email, authority) values(?, ?)";
		Integer rows;
		boolean value = false;
		try {
			rows = jdbcTemplate.update(SQL, email, authority);
			if (rows > 0) {
				value = true;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return value;
	}

}
