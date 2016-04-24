package pl.musicland.dao;

import java.util.List;
import java.util.Map;

import pl.musicland.model.Albumy;

public interface AlbumyDAO {
	public List<Albumy> getAllAlbumy();

	public List<Map<String, Object>> getCatNames();

	public List<Map<String, Object>> getGenreInCat();
}
