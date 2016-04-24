package pl.musicland.dao;

public interface AutorDAO {

	/* Funkcje odpwiadające za dodanie nowego autora */

	// Zwraca id dodanego autora
	public int addAutor(String imie, String nazwisko, String pseudonim);

	// Zwraca id istniejącego autora
	public int isExist(String imie, String nazwisko, String pseudonim);

	// Zwraca id ostatnio wstawionego wiersza
	public int getLastInsertId();

	public String getAutorById(int id);
}
