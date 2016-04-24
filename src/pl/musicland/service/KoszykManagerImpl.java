package pl.musicland.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.musicland.dao.KoszykDAO;
import pl.musicland.model.Koszyk;
import pl.musicland.model.ListaZakupow;

@Service
@Transactional
public class KoszykManagerImpl implements KoszykManager {

	@Autowired
	KoszykDAO dao;
	@Autowired
	ProdManager prodmanager;
	@Autowired
	ListaZakupowManager listazakmanager;

	private Logger logger = Logger.getLogger(KoszykManagerImpl.class);

	public int isExist(int userid) {
		return dao.isExist(userid);
	}

	@Override
	public int createBasket(int userid) {
		return dao.createBasket(userid);
	}

	@Override
	public int finish(int userid) {
		return dao.finish(userid);
	}

	@Override
	public List<Koszyk> getKoszykByStatusId(int statusid) {
		return dao.getKoszykByStatusId(statusid);
	}

	@Override
	public int getUserId(int koszykid) {
		return dao.getUserId(koszykid);
	}

	public List<Map<String, Object>> getProductsFromBasket(int koszykid) {
		return dao.getProductsFromBasket(koszykid);
	}

	@Override
	public void cancelOrder(int userid) {
		List<Map<String, Object>> products = dao.getProductsFromBaskeByUserId(userid);
		if (products != null) {
			for (Map<String, Object> ob : products) {
				System.out.println("produktid " + ob.get("produktid") + " " + "ilosc " + ob.get("ilosc"));
				dao.increaseNumberOfProduct((Integer) ob.get("produktid"), (Integer) ob.get("ilosc"));
			}
		}
		dao.deleteOrder(userid);
	}

	public void deleteProduct(int produktid, int userid) {
		int ilosc = dao.getNumberOfProductById(produktid, userid);
		dao.increaseNumberOfProduct(produktid, ilosc);
		dao.deleteProduct(produktid, userid);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	public String addProduct(int produktid, int iloscprod, int userid) {
		int iloscwmag = prodmanager.getNumberofProd(produktid);
		logger.info("Ilosc produktu w magazynie " + iloscwmag);
		int koszykid = isExist(userid);
		if (produktid != 0 && iloscprod != 0) {
			if (iloscwmag >= iloscprod) {
				if (koszykid != 0) {
					logger.info("Koszyk id " + koszykid);
				} else {
					int tmp = createBasket(userid);
					if (tmp > 0) {
						koszykid = tmp;
						logger.info("Koszyk id " + koszykid);
					} else {
						logger.warn("Nie udało się zwrócić id nowego koszyka");
					}
				}
				if (koszykid > 0) {
					ListaZakupow ob = listazakmanager.numOfProd(koszykid, produktid);
					if (ob != null) {
						if (listazakmanager.increasenumOfProd(iloscprod + ob.getIlosc(), koszykid, produktid) > 0) {
							prodmanager.decreaseNumberOfProduct(produktid, iloscprod);
							logger.info("Zwiększono ilosc produktu w koszyku " + "+ " + iloscprod);
							return "Zwiekszono ilosc produktu w koszyku " + "+ " + iloscprod;
						}
					} else {
						if (listazakmanager.insertOntoList(koszykid, produktid, iloscprod) == 1) {
							prodmanager.decreaseNumberOfProduct(produktid, iloscprod);
							logger.info("Dodano produkt do koszyka " + "+ " + iloscprod);
							return "Dodano produkt do koszyka " + "+ " + iloscprod;
						} else {
							logger.warn("Produkt nie zostal dodany do koszyka");
						}
					}
				}
			} else {
				return "Nie dysponujmy dana iloscia produktu - pozostalo " + iloscwmag;
			}
		} else {
			return "Nie podano ilosci produktu";
		}
		return "Operacja nie powiodłą się";
	}

	@Override
	public int toSend(int koszykid) {
		dao.tosend(koszykid);
		return 0;
	}

}
