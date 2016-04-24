package pl.musicland.service;

public interface AutorManager {
	public int addAutor(String imie, String nazwisko, String pseudonim);

	public String getAutorById(int id);
}
