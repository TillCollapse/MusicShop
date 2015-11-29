package pl.musicland.service;

import java.util.List;
import pl.musicland.model.Kategoria;

public interface KatManager {
	public List<Kategoria> getAllCat();
	public int addCat(String catname);
}
