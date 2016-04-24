package pl.musicland.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.musicland.model.Niealbumy;
import pl.musicland.dao.NieAlbumyDAO;

@Service
public class NieAlbumyManagerImpl implements NieAlbumyManager {

	@Autowired
	NieAlbumyDAO dao;

	@Override
	public List<Niealbumy> getAllNieAlbumy() {
		return dao.getAllNieAlbumy();
	}

	public List<Map<String, Object>> getCatNames() {
		return dao.getCatNames();
	}

	public List<Map<String, Object>> getGenreInCat() {
		return dao.getGenreInCat();
	}

}
