package pl.musicland.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pl.musicland.model.User;

public class UserRowMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("USERID"));
		user.setImie(rs.getString("IMIE"));
		user.setNazwisko(rs.getString("NAZWISKO"));
		user.setHaslo(rs.getString("HASLO"));
		user.setMiasto(rs.getString("MIASTO"));
		user.setKodpocztowy(rs.getString("KODPOCZTOWY"));
		user.setAdres(rs.getString("ADRES"));
		user.setCzyadmin(rs.getInt("czyadmin"));
		return user;
	}

}
