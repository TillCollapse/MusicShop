package pl.musicland.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.musicland.model.Koszyk;

public class KoszykRowMapper implements RowMapper<Koszyk> {
	public Koszyk mapRow(ResultSet rs, int rowNum) throws SQLException {
		Koszyk koszyk = new Koszyk();
		koszyk.setKoszykid(rs.getInt("koszykid"));
		koszyk.setStatusid(rs.getInt("statusid"));
		koszyk.setUserid(rs.getInt("userid"));
		return koszyk;
	}
}
