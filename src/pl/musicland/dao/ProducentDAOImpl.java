package pl.musicland.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class ProducentDAOImpl implements ProducentDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(ProducentDAOImpl.class);
	
	@Override
	public int addProd(String prodname) {
		
		int isexist = isExist(prodname);
		if(isexist == 0) {
			String SQL = "insert into producent(nazwa) values(?)";
			Integer result;
			try {
				result = jdbcTemplate.update(SQL, prodname);
			} catch (DataAccessException ex) {
				logger.error(ex);
				logger.info("Nie udało się dodać nowego producenta");
				result = null;
			}
			if ( result == null ) {
				return result = 0;
			} else {
				logger.info("Dodawanie nowego producenta przebiegło pomyślnie");
				return getLastInsertId();
			}
		} else {
			return isexist;
		}
	}

	@Override
	public int isExist(String prodname) {
		
		String SQL = "select producentid from producent where nazwa= ?";
		Integer result;
		
		try {
			result = jdbcTemplate.queryForObject(SQL, Integer.class, prodname.toUpperCase());
		} catch (EmptyResultDataAccessException ex) {
			logger.error(ex);
			logger.info("Dany producent nie istnieje w bazie");
			result = null;
		}
		
		if ( result == null ) {
			return result = 0;
		} else {
			logger.info("Dany producent istniej już w bazie, zwrócono jego id.");
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
			logger.info("Zwrócono id nowo wprowadzonego producenta");
			return result.intValue();
		}
	}
}
