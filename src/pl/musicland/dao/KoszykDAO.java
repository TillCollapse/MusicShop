package pl.musicland.dao;

import java.util.List;
import java.util.Map;

import pl.musicland.model.Koszyk;

public interface KoszykDAO {
	public int isExist(int userid);

	public int createBasket(int userid);

	public int finish(int userid);

	public List<Koszyk> getKoszykByStatusId(int statusid);

	public int getUserId(int koszykid);

	public List<Map<String, Object>> getProductsFromBasket(int koszykid);

	public void deleteOrder(int userid);

	public List<Map<String, Object>> getProductsFromBaskeByUserId(int userid);

	public void increaseNumberOfProduct(int produktid, int ilosc);

	public void deleteProduct(int produktid, int userid);

	public int getNumberOfProductById(int produktid, int userid);

	public int tosend(int koszykid);

}
