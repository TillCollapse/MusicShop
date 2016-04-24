package pl.musicland.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AutorDAOImpl implements AutorDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Logger logger = Logger.getLogger(AutorDAOImpl.class);

	@Override
	public int addAutor(String imie, String nazwisko, String pseudonim) {
		int isexist = isExist(imie, nazwisko, pseudonim);
		if (isexist == 0) {

			Integer result;
			try {
				if ((imie.length() > 0) && (nazwisko.length() > 0) && (pseudonim.length() > 0)) {
					String SQL = "insert into autor(imie,nazwisko, pseudonim) values(?, ?, ?)";
					result = jdbcTemplate.update(SQL, imie.toUpperCase(), nazwisko.toUpperCase(),
							pseudonim.toUpperCase());
				} else if (imie.length() > 0 && nazwisko.length() > 0) {
					String SQL = "insert into autor(imie,nazwisko) values(?, ?)";
					result = jdbcTemplate.update(SQL, imie.toUpperCase(), nazwisko.toUpperCase());
				} else if (imie.length() == 0 && nazwisko.length() == 0 && pseudonim.length() > 0) {
					String SQL = "insert into autor(pseudonim) values(?)";
					result = jdbcTemplate.update(SQL, pseudonim.toUpperCase());
				} else {
					logger.warn("Błędne parametry zapytania");
					result = null;
				}
			} catch (DataAccessException ex) {
				logger.error(ex);
				logger.info("Nie udało się dodać nowego autora");
				result = null;
			}

			if (result == null) {
				return result = 0;
			} else {
				logger.info("Dodawanie nowego autora przebiegło pomyślnie");
				return getLastInsertId();
			}
		} else {
			return isexist;
		}
	}

	// W zapytaniu muszą być spełnione 3 warunki imie, nazwisko, pseudonim albo
	// 2 warunki imie, nazwisko albo tylko 1 warunek: pseudonim
	@Override
	public int isExist(String imie, String nazwisko, String pseudonim) {
		Integer result;
		try {
			if ((imie.length() > 0) && (nazwisko.length() > 0) && (pseudonim.length() > 0)) {
				String SQL = "select autorid from autor where imie= ? and nazwisko= ? or pseudonim= ?";
				result = jdbcTemplate.queryForObject(SQL, Integer.class, imie.toUpperCase(), nazwisko.toUpperCase(),
						pseudonim.toUpperCase());
			} else if (imie.length() > 0 && nazwisko.length() > 0) {
				String SQL = "select autorid from autor where imie= ? and nazwisko= ?";
				result = jdbcTemplate.queryForObject(SQL, Integer.class, imie.toUpperCase(), nazwisko.toUpperCase());
			} else if (imie.length() == 0 && nazwisko.length() == 0 && pseudonim.length() > 0) {
				String SQL = "select autorid from autor where pseudonim= ?";
				result = jdbcTemplate.queryForObject(SQL, Integer.class, pseudonim.toUpperCase());
			} else {
				logger.warn("Błędne parametry zapytania");
				result = null;
			}
		} catch (EmptyResultDataAccessException ex) {
			logger.error(ex);
			logger.info("Dany autor nie istnieje w bazie");
			result = null;
		}

		if (result == null) {
			return result = 0;
		} else {
			logger.info("Dany autor istniej już w bazie, zwrócono jego id");
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
			logger.info("Zwrócono id nowo dodanego autora");
			return result.intValue();
		}
	}

	@Override
	public String getAutorById(int id) {
		String SQL = "select pseudonim from autor where autorid=?";
		String result = null;
		try {
			result = jdbcTemplate.queryForObject(SQL, String.class, id);
		} catch (DataAccessException ex) {

		}
		return result;
	}

}
