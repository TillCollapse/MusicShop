package pl.musicland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.musicland.dao.GatunekDAO;

@Service
public class GatunekManagerImpl implements GatunekManager {

	@Autowired
	GatunekDAO dao;
	@Override
	public int addGat(String gatname) {
		return dao.addGat(gatname);
	}
	
}
