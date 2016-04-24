package pl.musicland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.musicland.dao.AutorDAO;

@Service
public class AutorManagerImpl implements AutorManager {
	@Autowired
	AutorDAO dao;

	@Override
	public int addAutor(String imie, String nazwisko, String pseudonim) {
		return dao.addAutor(imie, nazwisko, pseudonim);
	}

	public String getAutorById(int id) {
		return dao.getAutorById(id);
	}
}
