package pl.musicland.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.musicland.model.Produkt;
import pl.musicland.dao.ProduktDAO;

@Service
public class ProdManagerImpl implements ProdManager {
	
	@Autowired
	ProduktDAO dao;

	@Override
	public List<Produkt> getSpecProdAlbum(int cat, int genre) {
		return dao.getSpecProdAlbum(cat, genre);
	}

	@Override
	public Produkt getProduct(int id) {
		return dao.getProduct(id);
	}

	@Override
	public int getNumberofProd(int id) {
		return dao.getNumberofProd(id);
	}

	@Override
	public List<Produkt> getSpecProdNieAlbum(int cat, int produ) {
		return dao.getSpecProdOthers(cat, produ);
	}

}
