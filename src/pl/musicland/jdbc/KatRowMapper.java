package pl.musicland.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pl.musicland.model.Kategoria;

public class KatRowMapper implements RowMapper<Kategoria> {
	public Kategoria mapRow(ResultSet rs, int rowNum) throws SQLException {
		Kategoria cat = new Kategoria();
		cat.setKategoriaid(rs.getInt("KATEGORIAID"));
		cat.setNazwa(rs.getString("NAZWA"));
		return cat;
	}

}
