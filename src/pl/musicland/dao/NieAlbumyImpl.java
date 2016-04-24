package pl.musicland.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.musicland.jdbc.NieAlbumyRowMapper;
import pl.musicland.model.Niealbumy;

@Repository
public class NieAlbumyImpl implements NieAlbumyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Niealbumy> getAllNieAlbumy() {
		String SQL = "select * from niealbumy";
		List<Niealbumy> albumy = jdbcTemplate.query(SQL, new NieAlbumyRowMapper());
		return albumy;
	}

	public List<Map<String, Object>> getCatNames() {
		String SQL = "select distinct nazwa_kategorii from niealbumy";
		return jdbcTemplate.queryForList(SQL);
	}

	public List<Map<String, Object>> getGenreInCat() {
		String SQL = "select distinct nazwa_producenta, producentid,kategoriaid,nazwa_kategorii from niealbumy";
		return jdbcTemplate.queryForList(SQL);
	}
}
