package pl.musicland.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.musicland.jdbc.UserRowMapper;
import pl.musicland.model.User;
import pl.musicland.others.Sha;

@Repository
public class UserDAOImpl implements UserDAO {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<User> getAllUsers() {
		String SQL = "select * from User";
		List <User> users = jdbcTemplate.query(SQL, new UserRowMapper());
		return users;			
	}


	@Override
	public void insertUser(User user) {
		String SQL = "call adduser(?, ?, ?, ?, ?, ?, ?)";
		String hashpass = null;
		try {
			hashpass = Sha.hash256(user.getHaslo());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdbcTemplate.update(SQL, user.getImie(), user.getNazwisko(),user.getEmail(),hashpass, user.getMiasto(),user.getKodpocztowy(),user.getAdres());
	}


	@Override
	public User loginUser(String email, String password) {
		String hashpass = null;
		try {
			hashpass = Sha.hash256(password.toString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String SQL = "select * from user where email = ? and haslo = ?";
		System.out.println(hashpass);
		User user = jdbcTemplate.queryForObject(SQL, new Object[] { email, hashpass }, new UserRowMapper());
		//System.out.println(user.getImie());
		//System.out.println(user.getHaslo());
		return user;
	}

}
