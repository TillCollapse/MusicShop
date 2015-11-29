package pl.musicland.dao;

public interface KoszykDAO {
	public int isExist(int userid);
	public int createBasket(int userid);
	public int finish(int userid);
}
