package pl.musicland.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.musicland.model.Produkt;
import pl.musicland.jdbc.ProdRowMapper;

@Repository
public class ProduktDAOImpl implements ProduktDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private Logger logger = Logger.getLogger(ProduktDAOImpl.class);

	@Override
	public List<Produkt> getSpecProdAlbum(int cat, int genre) {
		String SQL = "select * from produkt where gatunekid = ? and kategoriaid= ?";
		return jdbcTemplate.query(SQL, new Object[] { genre, cat }, new ProdRowMapper());
	}

	@Override
	public List<Produkt> getSpecProdOthers(int cat, int prod) {
		String SQL = "select * from produkt where producentid= ? and kategoriaid= ?";
		return jdbcTemplate.query(SQL, new Object[] { prod, cat }, new ProdRowMapper());
	}

	@Override
	public Produkt getProduct(int id) {
		String SQL = "select * from produkt where produktid = ?";
		return jdbcTemplate.queryForObject(SQL, new Object[] { id }, new ProdRowMapper());
	}

	@Override
	public int getNumberofProd(int id) {
		String SQL = "select ilosc from produkt where produktid = ?";
		return jdbcTemplate.queryForObject(SQL, Integer.class, id);
	}

	@Override
	public void decreaseNumberOfProduct(int produktid, int iloscprod) {
		String SQL = "update produkt p set p.ilosc=p.ilosc-? where p.produktid=?";
		try {
			jdbcTemplate.update(SQL, iloscprod, produktid);
		} catch (DataAccessException ex) {
			logger.error(ex);
		}
	}

	@Override
	public void addProduct(String nazwa, int kategoriaid, int producentid, int gatunekid, int autorsid, int ilosc,
			float cena, String opis, String zdjecie) {
		String SQL = "insert into produkt(nazwa, kategoriaid, gatunekid, autorsid, producentid, ilosc, cena, opis, zdjecie) values(?,?,?,?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(SQL, nazwa, kategoriaid, gatunekid, autorsid, producentid, ilosc, cena, opis, zdjecie);
		} catch (DataAccessException ex) {
			logger.error(ex);
		}

	}

	@Override
	public void addProduct(String nazwa, int kategoriaid, int producentid, int ilosc, float cena, String opis,
			String zdjecie) {
		String SQL = "insert into produkt(nazwa, kategoriaid, producentid, ilosc, cena, opis, zdjecie) values(?,?,?,?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(SQL, nazwa, kategoriaid, producentid, ilosc, cena, opis, zdjecie);
		} catch (DataAccessException ex) {
			logger.error(ex);
		}

	}

}
