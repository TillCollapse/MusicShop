package pl.musicland.dao;
import java.util.List;
import pl.musicland.model.Kategoria;

public interface KategoriaDAO {
	public List<Kategoria>getAllCat();
	
	//Zwraca id dodanej kategorii
	public int addCat(String catname);
	//Zwraca id istniejącej kategorii
	public int isExist(String catname);
	//Zwraca id ostatnio wstawionego wiersza
	public int getLastInsertId();
}
