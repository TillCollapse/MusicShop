package pl.musicland.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pl.musicland.model.Albumy;

public class AlbumyRowMapper implements RowMapper<Albumy> {
	public Albumy mapRow(ResultSet rs, int rowNum) throws SQLException {
		Albumy album = new Albumy();
		album.setProduktid(rs.getInt("produktid"));
		album.setNazwa_produktu(rs.getString("nazwa_produktu"));
		album.setKategoriaid(rs.getInt("kategoriaid"));
		album.setNazwa_kategorii(rs.getString("nazwa_kategorii"));
		album.setGatunekid(rs.getInt("gatunekid"));
		album.setNazwa_gatunku(rs.getString("nazwa_gatunku"));
		return album;
	}

}
