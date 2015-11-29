package pl.musicland.service;

import java.util.List;

import pl.musicland.model.ListaZakupow;

public interface ListaZakupowManager {
	public ListaZakupow numOfProd(int koszykid, int prodid);
	public int increasenumOfProd(int ilosc, int koszykid, int prodid);
	public Integer insertOntoList(int koszykid, int prodid, int iloscprod);
	public List<ListaZakupow> getListaZakupow(int koszykid);
}
