package pl.musicland.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.musicland.dao.KategoriaDAO;
import pl.musicland.model.Kategoria;

@Service
public class KatManagerImpl implements KatManager{
	
	@Autowired
	KategoriaDAO dao;
	
	@Override
	public List<Kategoria> getAllCat() {
		return dao.getAllCat();
	}

	@Override
	public int addCat(String catname) {
		return dao.addCat(catname);
	}

}
