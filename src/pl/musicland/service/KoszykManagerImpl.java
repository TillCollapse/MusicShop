package pl.musicland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.musicland.dao.KoszykDAO;

@Service 
public class KoszykManagerImpl implements KoszykManager {

	@Autowired
	KoszykDAO dao;
	
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

}
