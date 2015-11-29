package pl.musicland.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.musicland.model.ListaZakupow;
import pl.musicland.controller.ShopingConroller;
import pl.musicland.jdbc.ListaZakupowRowMapper;

@Repository
public class ListaZakupowDAOImpl implements ListaZakupowDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(ListaZakupowDAOImpl.class);

	@Override
	public ListaZakupow numOfProd(int koszykid, int prodid) {
		String SQL = "select *from listazakupow where koszykid=? and produktid=?";
		ListaZakupow result;
		try {
			result = jdbcTemplate.queryForObject(SQL, new Object[] {koszykid, prodid}, new ListaZakupowRowMapper());
		} catch (DataAccessException ex) {
			logger.error("Produktu nie ma na liscie zakupow");
			result = null;
		}
		return result;
	}

	@Override
	public int increasenumOfProd(int ilosc, int koszykid, int prodid) {
		String SQL = "update listazakupow set ilosc =? where koszykid =? and produktid=?";
		return jdbcTemplate.update(SQL, new Object[] {ilosc, koszykid, prodid});
		
	}

	@Override
	public int insertOntoList(int koszykid, int prodid, int iloscprod) {
		String SQL = "insert into listazakupow(koszykid,produktid,ilosc) values(?, ?, ?)";
		Integer result;
		try {
			result = jdbcTemplate.update(SQL, new Object[] {koszykid, prodid, iloscprod });
		} catch(DataAccessException ex) {
			logger.error(ex);
			result = null;
		}
		if ( result == null ) {
			return result = 0;
		} else {
			return result.intValue();
		}
		
	}

	@Override
	public List<ListaZakupow> getListaZakupow(int koszykid)  {
		String SQL = "select * from listazakupow where koszykid=?";
		List <ListaZakupow> result;
		try {
			result = jdbcTemplate.query(SQL, new Object[] {koszykid}, new ListaZakupowRowMapper());
		} catch (DataAccessException ex) {
			logger.error("Nie można pozyskać dostępu do listy zakupow");
			result = null;
		}
		return result;
	}
	
}
