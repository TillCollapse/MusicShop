package pl.musicland.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.musicland.model.Produkt;
import pl.musicland.jdbc.ProdRowMapper;

@Repository
public class ProduktDAOImpl implements ProduktDAO{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Produkt> getSpecProdAlbum(int cat, int genre) {
		String SQL = "select * from produkt where gatunekid = ? and kategoriaid= ?";
		return jdbcTemplate.query(SQL, new Object [] { genre, cat },new ProdRowMapper());
	}

	@Override
	public List<Produkt> getSpecProdOthers(int cat, int prod) {
		String SQL = "select * from produkt where producentid= ? and kategoriaid= ?";
		return jdbcTemplate.query(SQL, new Object [] { prod, cat },new ProdRowMapper());
	}

	@Override
	public Produkt getProduct(int id) {
		String SQL = "select * from produkt where produktid = ?";
		return jdbcTemplate.queryForObject(SQL, new Object[] { id }, new ProdRowMapper());
	}

	@Override
	public int getNumberofProd(int id) {
		String SQL = "select ilosc from produkt where produktid = ?";
		//System.out.println(jdbcTemplate.queryForObject( SQL, Integer.class, id ));
		return jdbcTemplate.queryForObject( SQL, Integer.class, id );
	}

}
