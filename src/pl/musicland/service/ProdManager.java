package pl.musicland.service;

import java.util.List;
import pl.musicland.model.Produkt;;

public interface ProdManager {
	public List<Produkt> getSpecProdAlbum(int cat, int genre);
	public List<Produkt> getSpecProdNieAlbum(int cat, int produ);

	public Produkt getProduct(int id);
	//zwraca ilosc produktu w magazynie
	public int getNumberofProd(int id);
}
