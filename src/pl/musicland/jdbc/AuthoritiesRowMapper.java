package pl.musicland.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.musicland.model.Authorities;;

public class AuthoritiesRowMapper implements RowMapper<Authorities> {
	public Authorities mapRow(ResultSet rs, int rowNum) throws SQLException {
		Authorities authorities = new Authorities();
		authorities.setAuthorityid(rs.getInt("AUTHORITYID"));
		authorities.setEmail(rs.getString("EMAIL"));
		authorities.setAuthority(rs.getString("AUTHORITY"));
		return null;
	}
}
