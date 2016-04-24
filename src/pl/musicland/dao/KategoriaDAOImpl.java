package pl.musicland.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.musicland.jdbc.KatRowMapper;
import pl.musicland.model.Kategoria;

@Repository
public class KategoriaDAOImpl implements KategoriaDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Logger logger = Logger.getLogger(KategoriaDAOImpl.class);

	@Override
	public List<Kategoria> getAllCat() {
		String SQL = "select * from KATEGORIA";
		List<Kategoria> cats = jdbcTemplate.query(SQL, new KatRowMapper());
		return cats;
	}

	@Override
	public int addCat(String catname) {

		int isexist = isExist(catname);
		if (isexist == 0) {
			String SQL = "insert into kategoria(nazwa) values(?)";
			Integer result;
			try {
				result = jdbcTemplate.update(SQL, catname);
			} catch (DataAccessException ex) {
				logger.error(ex);
				logger.info("Nie udało się dodać nowej kategorii");
				result = null;
			}
			if (result == null) {
				return result = 0;
			} else {
				logger.info("Dodawanie nowej kategorii przebiegło pomyślnie");
				return getLastInsertId();
			}
		} else {
			return isexist;
		}
	}

	@Override
	public int isExist(String catname) {

		String SQL = "select kategoriaid from kategoria where nazwa= ?";
		Integer result;

		try {
			result = jdbcTemplate.queryForObject(SQL, Integer.class, catname.toUpperCase());
		} catch (EmptyResultDataAccessException ex) {
			logger.error(ex);
			logger.info("Dana kategoria nie istnieje w bazie");
			result = null;
		}

		if (result == null) {
			return result = 0;
		} else {
			logger.info("Dana kategoria istniej już w bazie, zwrócono jej id");
			return result.intValue();
		}

	}

	@Override
	public int getLastInsertId() {
		String SQL = "select LAST_INSERT_ID()";
		Integer result;
		try {
			result = jdbcTemplate.queryForObject(SQL, Integer.class);
		} catch (DataAccessException ex) {
			logger.error(ex);
			logger.info("Nie uzyskano LastInsertId");
			result = null;
		}

		if (result == null) {
			return result = 0;
		} else {
			logger.info("Zwrócono id istniejącej kategorii");
			return result.intValue();
		}
	}

}
