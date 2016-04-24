package pl.musicland.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.musicland.dao.ListaZakupowDAO;
import pl.musicland.model.ListaZakupow;

@Service
public class ListaZakupowManagerImpl implements ListaZakupowManager {

	@Autowired
	ListaZakupowDAO dao;

	@Override
	public ListaZakupow numOfProd(int koszykid, int prodid) {
		return dao.numOfProd(koszykid, prodid);
	}

	@Override
	public int increasenumOfProd(int ilosc, int koszykid, int prodid) {
		return dao.increasenumOfProd(ilosc, koszykid, prodid);
	}

	@Override
	public Integer insertOntoList(int koszykid, int prodid, int iloscprod) {
		return dao.insertOntoList(koszykid, prodid, iloscprod);
	}

	@Override
	public List<ListaZakupow> getListaZakupow(int koszykid) {
		return dao.getListaZakupow(koszykid);
	}

}
