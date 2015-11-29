package pl.musicland.service;

import pl.musicland.dao.KoszykDAO;


public interface KoszykManager {
	public int isExist( int userid);
	public int createBasket ( int userid);
	public int finish ( int userid);
}
