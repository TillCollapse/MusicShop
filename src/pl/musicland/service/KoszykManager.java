package pl.musicland.service;

import java.util.List;
import java.util.Map;

import pl.musicland.model.Koszyk;

public interface KoszykManager {
	public int isExist(int userid);

	public int createBasket(int userid);

	public int finish(int userid);

	public List<Koszyk> getKoszykByStatusId(int statusid);

	public int getUserId(int koszykid);

	public List<Map<String, Object>> getProductsFromBasket(int koszykid);

	public void cancelOrder(int userid);

	public void deleteProduct(int produktid, int userid);

	public String addProduct(int prodid, int iloscprod, int userid);

	public int toSend(int koszykid);
}
