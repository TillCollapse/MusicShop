package pl.musicland.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pl.musicland.model.Niealbumy;

public class NieAlbumyRowMapper implements RowMapper<Niealbumy> {
	public Niealbumy mapRow(ResultSet rs, int rowNum) throws SQLException {
		Niealbumy x = new Niealbumy();
		x.setProduktid(rs.getInt("produktid"));
		x.setNazwa_produktu(rs.getString("nazwa_produktu"));
		x.setKategoriaid(rs.getInt("kategoriaid"));
		x.setNazwa_kategorii(rs.getString("nazwa_kategorii"));
		x.setProducentid(rs.getInt("producentid"));
		x.setNazwa_producenta(rs.getString("nazwa_producenta"));
		return x;
	}
	
}
