package pl.musicland.dao;

public interface GatunekDAO {

	/* Funkcje odpwiadające za dodanie nowego gatunku */

	// Zwraca id dodanego gatunku
	public int addGat(String gatname);

	// Zwraca id istniejącego gatunku
	public int isExist(String gatname);

	// Zwraca id ostatnio wstawionego wiersza
	public int getLastInsertId();

}
