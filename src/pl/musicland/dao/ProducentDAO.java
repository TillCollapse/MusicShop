package pl.musicland.dao;

public interface ProducentDAO {
	/* Funkcje odpwiadające zadodanie nowego producenta */
	// Zwraca id dodanego producenta
	public int addProd(String prodname);

	// Zwraca id istniejącego producenta
	public int isExist(String prodname);

	// Zwraca id ostatnio wstawionego wiersza
	public int getLastInsertId();

	public String getProducentNameById(int id);

}
