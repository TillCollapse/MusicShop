package pl.musicland.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.musicland.model.Albumy;
import pl.musicland.dao.AlbumyDAO;

@Service
public class AlbumyManagerImpl implements AlbumyManager {

	@Autowired
	AlbumyDAO dao;

	@Override
	public List<Albumy> getAllAlbumy() {
		return dao.getAllAlbumy();
	}

	public List<Map<String, Object>> getCatNames() {
		return dao.getCatNames();
	}

	public List<Map<String, Object>> getGenreInCat() {
		return dao.getGenreInCat();
	}

}
