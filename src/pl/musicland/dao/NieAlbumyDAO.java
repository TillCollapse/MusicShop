package pl.musicland.dao;

import java.util.List;
import java.util.Map;

import pl.musicland.model.Niealbumy;

public interface NieAlbumyDAO {
	public List<Niealbumy> getAllNieAlbumy();

	public List<Map<String, Object>> getCatNames();

	public List<Map<String, Object>> getGenreInCat();
}
