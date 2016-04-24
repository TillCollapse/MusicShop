package pl.musicland.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.musicland.model.Albumy;
import pl.musicland.jdbc.AlbumyRowMapper;

@Repository
public class AlbumyDAOImpl implements AlbumyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Albumy> getAllAlbumy() {
		String SQL = "select * from Albumy";
		List<Albumy> albumy = jdbcTemplate.query(SQL, new AlbumyRowMapper());

		return albumy;
	}

	@Override
	public List<Map<String, Object>> getCatNames() {
		String SQL = "select distinct nazwa_kategorii from albumy";
		return jdbcTemplate.queryForList(SQL);
	}

	@Override
	public List<Map<String, Object>> getGenreInCat() {
		String SQL = "select distinct nazwa_gatunku,gatunekid,kategoriaid,nazwa_kategorii from albumy";
		return jdbcTemplate.queryForList(SQL);
	}

}
