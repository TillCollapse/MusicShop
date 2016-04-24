package pl.musicland.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.musicland.model.Produkt;

public class ProdRowMapper implements RowMapper<Produkt> {
	public Produkt mapRow(ResultSet rs, int rowNum) throws SQLException {
		Produkt prod = new Produkt();
		prod.setProduktid(rs.getInt("produktid"));
		prod.setNazwa(rs.getString("nazwa"));
		prod.setKategoriaid(rs.getInt("kategoriaid"));
		prod.setGatunekid(rs.getInt("gatunekid"));
		prod.setAutorsid(rs.getInt("autorsid"));
		prod.setProducentid(rs.getInt("producentid"));
		prod.setIlosc(rs.getInt("ilosc"));
		prod.setCena(rs.getFloat("cena"));
		prod.setOpis(rs.getString("opis"));
		prod.setZdjecie(rs.getString("zdjecie"));
		return prod;
	}

}
