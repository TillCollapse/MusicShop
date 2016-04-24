package pl.musicland.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import pl.musicland.model.UserAuthority;

public class UserAuthorityRowMapper implements RowMapper<UserAuthority> {
	public UserAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserAuthority userauthority = new UserAuthority();
		userauthority.setUserid(rs.getInt("USERID"));
		userauthority.setImie(rs.getString("IMIE"));
		userauthority.setNazwisko(rs.getString("NAZWISKO"));
		userauthority.setEmail(rs.getString("EMAIL"));
		userauthority.setAuthorityid(rs.getInt("AUTHORITYID"));
		userauthority.setAuthority(rs.getString("AUTHORITY"));
		return userauthority;
	}
}
