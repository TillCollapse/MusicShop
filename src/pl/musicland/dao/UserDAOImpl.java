package pl.musicland.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import pl.musicland.jdbc.UserAuthorityRowMapper;
import pl.musicland.jdbc.UserRowMapper;
import pl.musicland.model.User;
import pl.musicland.model.UserAuthority;
import pl.musicland.validation.Sha;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public List<User> getAllUsers() {
		String SQL = "select imie, nazwisko, email from Users where enabled = '1'";
		List<User> users = null;
		try {
			users = jdbcTemplate.query(SQL, new UserRowMapper());
		} catch (DataAccessException ex) {
			System.out.println(ex);
		}
		return users;
	}

	@Override
	public void insertUser(User user) {
		String SQL = "call adduser(?, ?, ?, ?, ?, ?, ?)";
		String hashpass = null;
		hashpass = passwordEncoder.encode(user.getHaslo());
		try {
			jdbcTemplate.update(SQL, user.getImie(), user.getNazwisko(), user.getEmail(), hashpass, user.getMiasto(),
					user.getKodpocztowy(), user.getAdres());
		} catch (DataAccessException ex) {
			System.out.println(ex);
		}
	}

	@Override
	public User loginUser(String email, String password) {
		String hashpass = null;
		User user = null;
		try {
			hashpass = Sha.hash256(password.toString());
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex);
		} catch (NullPointerException ex) {
			System.out.println(ex);
		}

		String SQL = "select * from users where email = ? and haslo = ?";
		System.out.println("Hash " + hashpass);
		try {
			user = jdbcTemplate.queryForObject(SQL, new Object[] { email, hashpass }, new UserRowMapper());
		} catch (DataAccessException ex) {
			System.out.println(ex);
		}
		return user;
	}

	@Override
	public String getUserName(String email) {
		String SQL = "select imie from users where email = ?";
		String name = null;
		try {
			name = jdbcTemplate.queryForObject(SQL, String.class, email);
		} catch (DataAccessException ex) {
			System.out.println(ex);
		}
		return name;
	}

	@Override
	public int getUserId(String email) {
		String SQL = "select userid from users where email = ?";
		Integer id = null;
		try {
			id = jdbcTemplate.queryForObject(SQL, Integer.class, email);
		} catch (DataAccessException ex) {
			System.out.println(ex);
		}
		return id.intValue();
	}

	@Override
	public List<UserAuthority> getUserAuthority() {
		String SQL = "select u.userid, u.imie, u.nazwisko, u.email, a.authorityid, a.authority from users u, authorities a where u.enabled='1' and u.email=a.email";
		List<UserAuthority> userauthority = null;
		try {
			userauthority = jdbcTemplate.query(SQL, new UserAuthorityRowMapper());
		} catch (DataAccessException ex) {
			System.out.println(ex);
		}
		return userauthority;
	}

	@Override
	public List<Map<String, Object>> getUserDetails() {
		String SQL = "select k.koszykid, u.imie, u.nazwisko, u.email, data, s.status from users u, koszyk k, status s where u.userid=k.userid and k.statusid=2 and s.statusid=2";
		List<Map<String, Object>> result = null;
		try {
			result = jdbcTemplate.queryForList(SQL);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return result;
	}

	@Override
	public User getUserById(int userid) {
		String SQL = "SELECT userid,imie, nazwisko, email, \"????\" as haslo ,adres, miasto, kodpocztowy, enabled from users where userid=?";
		User result = null;
		try {
			result = jdbcTemplate.queryForObject(SQL, new UserRowMapper(), userid);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return result;
	}

}
