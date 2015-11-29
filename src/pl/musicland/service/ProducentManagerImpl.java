package pl.musicland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.musicland.dao.ProducentDAO;;

@Service
public class ProducentManagerImpl implements ProducentManager {
	
	@Autowired
	ProducentDAO dao;
	
	@Override
	public int addProd(String prodname) {
		return dao.addProd(prodname);
	}
	
}
