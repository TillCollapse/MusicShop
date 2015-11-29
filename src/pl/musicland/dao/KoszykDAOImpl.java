package pl.musicland.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.musicland.controller.ShopingConroller;

@Repository
public class KoszykDAOImpl implements KoszykDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(KoszykDAOImpl.class);
	
	public int isExist(int userid){
		String SQL = "select koszykid from koszyk where userid =? and statusid='1'";
		Integer result;
		try {
			result = jdbcTemplate.queryForObject(SQL, Integer.class, userid);
		} catch (EmptyResultDataAccessException error) {
			logger.error(error);
			logger.info("Koszyk nie istnieje utwórz go");
			result = null;
		}
		
		if ( result == null ) {
			return result = 0;
		} else {
			return result.intValue();
		}
		
	}

	@Override
	public int createBasket(int userid) {
		
		Integer result;
		String SQL = "insert into koszyk(statusid,userid) values(1, ?)";
		try {
			jdbcTemplate.update(SQL, userid);
			logger.info("Utworzono nowy koszyk ");
		} catch (DataAccessException ex) {
			logger.info("Tworzenie koszyka nie powiodło się ");
			logger.error(ex);
		}
		
		try {
			String SQL2 = "select koszykid from koszyk where userid=? and statusid='1'";
			result = jdbcTemplate.queryForObject( SQL2, Integer.class, userid );
		} catch ( EmptyResultDataAccessException error ) {
			logger.error(error);
			logger.warn("Nie udało się pobrać id koszyka");
			result = null;
		}
		if ( result == null ) {
			result = 0;
		} else {
			result = result.intValue();
		}	
		return result;
	}

	@Override
	public int finish(int userid) {
		String SQL = "update koszyk set statusid=2 where statusid=1 and userid=?";
		return jdbcTemplate.update(SQL, userid);
	}
}
