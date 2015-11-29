package pl.musicland.service;

import java.util.List;
import java.util.Map;

import pl.musicland.model.Albumy;


public interface AlbumyManager {
	public List<Albumy> getAllAlbumy();
	public List<Map<String, Object>> getCatNames();
	public List<Map<String, Object>> getGenreInCat();
	
}
