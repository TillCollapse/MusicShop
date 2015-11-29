package pl.musicland.dao;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GatunekDAOImpl implements GatunekDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(GatunekDAOImpl.class);
	
	@Override
	public int addGat(String gatname) {
		
		int isexist = isExist(gatname);
		if(isexist == 0) {
			String SQL = "insert into gatunek(nazwa) values(?)";
			Integer result;
			try {
				result = jdbcTemplate.update(SQL, gatname);
			} catch (DataAccessException ex) {
				logger.error(ex);
				logger.info("Nie udało się dodać nowego gatunku");
				result = null;
			}
			if ( result == null ) {
				return result = 0;
			} else {
				logger.info("Dodawanie nowego gatunku przebiegło pomyślnie");
				return getLastInsertId();
			}
		} else {
			return isexist;
		}
	}

	@Override
	public int isExist(String gatname) {
		
		String SQL = "select gatunekid from gatunek where nazwa= ?";
		Integer result;
		
		try {
			result = jdbcTemplate.queryForObject(SQL, Integer.class, gatname.toUpperCase());
		} catch (EmptyResultDataAccessException ex) {
			logger.error(ex);
			logger.info("Dany gatunek nie istnieje w bazie");
			result = null;
		}
		
		if ( result == null ) {
			return result = 0;
		} else {
			logger.info("Dany gatunek istniej już w bazie, zwrócono jego id");
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
		
		if ( result == null ) {
			return result = 0;
		} else {
			logger.info("Zwrócono id nowo dodanego gatunku");
			return result.intValue();
		}
	}
}
