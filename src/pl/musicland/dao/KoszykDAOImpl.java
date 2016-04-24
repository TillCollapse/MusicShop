package pl.musicland.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.musicland.jdbc.KoszykRowMapper;
import pl.musicland.model.Koszyk;

@Repository
public class KoszykDAOImpl implements KoszykDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private Logger logger = Logger.getLogger(KoszykDAOImpl.class);

	public int isExist(int userid) {
		String SQL = "select koszykid from koszyk where userid =? and statusid='1'";
		Integer result;
		try {
			result = jdbcTemplate.queryForObject(SQL, Integer.class, userid);
		} catch (EmptyResultDataAccessException error) {
			logger.error(error);
			logger.info("Koszyk nie istnieje utwórz go");
			result = null;
		}

		if (result == null) {
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
		} catch (DataAccessException ex) {
			logger.error(ex);
		}
		try {
			String SQL2 = "select koszykid from koszyk where userid=? and statusid='1'";
			result = jdbcTemplate.queryForObject(SQL2, Integer.class, userid);
		} catch (EmptyResultDataAccessException error) {
			logger.error(error);
			result = null;
		}
		if (result == null) {
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

	@Override
	public List<Koszyk> getKoszykByStatusId(int statusid) {
		String SQL = "select * from koszyk where statusid= ?";
		List<Koszyk> result = null;
		try {
			result = jdbcTemplate.query(SQL, new KoszykRowMapper());
		} catch (Exception ex) {
			logger.error(ex);
		}
		return result;
	}

	@Override
	public int getUserId(int koszykid) {
		String SQL = "select userid from koszyk where koszykid=?";
		Integer result = null;
		try {
			result = jdbcTemplate.queryForObject(SQL, Integer.class, koszykid);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return result == null ? 0 : result.intValue();
	}

	@Override
	public List<Map<String, Object>> getProductsFromBasket(int koszykid) {
		String SQL = "select p.nazwa, p.cena, l.ilosc from produkt p, listazakupow l, koszyk k where k.koszykid=l.koszykid and k.koszykid=? and k.statusid=2 and l.produktid=p.produktid";
		List<Map<String, Object>> result = null;
		try {
			result = jdbcTemplate.queryForList(SQL, koszykid);
		} catch (DataAccessException ex) {
			logger.error(ex);
		}
		return result;
	}

	@Override
	public void deleteOrder(int userid) {
		String SQL = "delete from koszyk where koszyk.statusid='1' and koszyk.userid=?";
		try {
			jdbcTemplate.update(SQL, userid);
		} catch (DataAccessException ex) {
			logger.error(ex);
		}
	}

	@Override
	public void increaseNumberOfProduct(int produktid, int ilosc) {
		String SQL = "update produkt p set p.ilosc=p.ilosc+? where p.produktid=?";
		try {
			jdbcTemplate.update(SQL, ilosc, produktid);
		} catch (DataAccessException ex) {
			logger.error(ex);
		}
	}

	@Override
	public List<Map<String, Object>> getProductsFromBaskeByUserId(int userid) {
		String SQL = "select l.produktid, l.ilosc from koszyk k, listazakupow l where k.statusid='1' and k.userid=? and l.koszykid=k.koszykid";
		List<Map<String, Object>> result = null;
		try {
			result = jdbcTemplate.queryForList(SQL, userid);
		} catch (DataAccessException ex) {
			logger.error(ex);
		}
		return result;
	}

	public void deleteProduct(int produktid, int userid) {
		String SQL = "delete l from listazakupow l, koszyk k where l.produktid=? and l.koszykid=k.koszykid and k.userid=?";
		try {
			jdbcTemplate.update(SQL, produktid, userid);
		} catch (DataAccessException ex) {
			logger.error(ex);
		}
	}

	public int getNumberOfProductById(int produktid, int userid) {
		String SQL = "select l.ilosc from koszyk k, listazakupow l where k.statusid='1' and k.userid=? and l.produktid=? and l.koszykid=k.koszykid;";
		Integer result = null;
		try {
			result = jdbcTemplate.queryForObject(SQL, Integer.class, userid, produktid);
		} catch (DataAccessException ex) {
			logger.error(ex);
		}
		return result.intValue();
	}

	@Override
	public int tosend(int koszykid) {
		String SQL = "update koszyk set statusid=3 where statusid=2 and koszykid=?";
		return jdbcTemplate.update(SQL, koszykid);
	}
}
