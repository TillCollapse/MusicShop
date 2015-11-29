package pl.musicland.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.musicland.model.ListaZakupow;

public class ListaZakupowRowMapper implements RowMapper<ListaZakupow>{
	public ListaZakupow mapRow(ResultSet rs, int rowNum) throws SQLException {
		ListaZakupow lista = new ListaZakupow();
		lista.setListazakupowid(rs.getInt("listazakupowid"));
		lista.setKoszykid(rs.getInt("koszykid"));
		lista.setProduktid(rs.getInt("produktid"));
		lista.setIlosc(rs.getInt("ilosc"));
		return lista;
	}
}
