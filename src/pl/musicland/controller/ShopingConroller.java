package pl.musicland.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;

import pl.musicland.service.KoszykManager;
import pl.musicland.service.ListaZakupowManager;
import pl.musicland.service.ProdManager;
import pl.musicland.model.User;
import pl.musicland.model.ListaZakupow;
import pl.musicland.model.Produkt;
import pl.musicland.model.HelpObject;

@Controller
public class  ShopingConroller {
	
	@Autowired
	ProdManager prod;
	@Autowired
	KoszykManager koszyk;
	@Autowired
	ListaZakupowManager listazak;
	
	private User userData = null;
	private Logger logger = Logger.getLogger(ShopingConroller.class);
	
	
	
	@RequestMapping(value="/addToBasket", method = RequestMethod.POST)
	//@ResponseBody This annotation can be put on a method and indicates that the return type should be written straight to the HTTP response body (and not placed in a Model, or interpreted as a view name)
	@ResponseBody
	public String addProduct(HttpServletRequest request, HttpSession session){
		
		String idprod = request.getParameter("idprod");
		String prodilosc = request.getParameter("iloscprod");
		int id = Integer.parseInt(idprod);
		int ilosc = Integer.parseInt(prodilosc);
		//System.out.println(id);
		//System.out.println(ilosc);
		
		//Pobieram dane o użytkowniku z sesji 
		User user = (User)session.getAttribute("user");
		
		logger.info("ShopingController logs");
		
		
		int iloscwmag = prod.getNumberofProd(id);
		logger.info("Ilosc produktu w magazynie " + iloscwmag);
		int koszykid = koszyk.isExist(user.getId());
		//1 spr dostepnosc produktu w bazie
		//2 spr czy koszyk użytkownika istnieje
		//3 #up jężli nie istnieje to utwórz go
		//4 dodawaj produkty do listyzakupow
		if(id != 0 && ilosc != 0) {
			if (iloscwmag > 0 || iloscwmag == 0) {
					if (iloscwmag >= ilosc) {
					//sprawdzenie czy koszyk użytkownika już istnieje w przeciwnym razie utworzenie go
						if( koszykid != 0  ){
							logger.info("Koszyk id " + koszykid);
						} else {
							int tmp = koszyk.createBasket(user.getId());
							if ( tmp > 0) {
								koszykid = tmp;
								logger.info("Koszyk id " + koszykid);
							} else {
								logger.warn("Nie udało się zwrócić id nowego koszyka");
							}
						}
						
						if (koszykid > 0) {
					
							ListaZakupow ob = listazak.numOfProd(koszykid, id);
							if (ob != null) {
								if(listazak.increasenumOfProd(ilosc + ob.getIlosc(), koszykid, id) > 0) {
									logger.info("Zwiększono ilosc produktu w koszyku " + "+ " + ilosc);
									return "Zwiekszono ilosc produktu w koszyku " + "+ " + ilosc;
								}
							} else {
								if (listazak.insertOntoList(koszykid, id, ilosc) == 1){
									logger.info("Dodano produkt do koszyka " + "+ " + ilosc );
									return "Dodano produkt do koszyka " + "+ " + ilosc ;
								} else {
									logger.warn("Produkt nie zostal dodany do koszyka");
								}
							}
						}
							
					} 
					else {
						return "Nie dysponujmy dana iloscia produktu - pozostalo " + iloscwmag;
					}
						
			} 
			else {
				return "Produkt o danym id nieistnieje";
			}
			
		} 
		else {
			return  "Nie podano ilosci produktu";
		}
			
		return "STH WRONG";
		
	}
	 @RequestMapping("/basket") 
	    public String showbasket(Model model, HttpSession session) {
		  	User user = (User)session.getAttribute("user");
		  	int koszykid = koszyk.isExist(user.getId());
		  	float koszt = 0;
		  	logger.info("ID koszyka " + koszykid );
		  	List<HelpObject> help = new ArrayList<HelpObject>();
		  	List<Integer> ilosc = new ArrayList<Integer>();
		  	List<Produkt>  produkty = new ArrayList<Produkt>();
		  	List<ListaZakupow> lista = listazak.getListaZakupow(koszykid);
		  	
		  	for (ListaZakupow ob : lista) {
		  		produkty.add(prod.getProduct(ob.getProduktid()));
		  	}
		  	for (ListaZakupow ob : lista) {
		  		ilosc.add(ob.getIlosc());
		  	}
		  	for (int i=0; i < produkty.size(); i++) {
		  		//System.out.println( produkty.get(i).getNazwa());
		  		HelpObject ob = new HelpObject();
		  		ob.setNazwa(produkty.get(i).getNazwa());
		  		ob.setCena(produkty.get(i).getCena());
		  		ob.setIlosc(ilosc.get(i));
		  		koszt += ob.getCena() * ob.getIlosc();
		  		help.add(ob);
		  		
		  	}
			model.addAttribute("produkty",help);
			model.addAttribute("koszt", koszt);
	    	return "basket";
	    }
	 @RequestMapping("/finish")
	    public String finish(HttpSession session) {
	    	this.userData = (User)session.getAttribute("user");

		 	koszyk.finish(userData.getId());
	    	return "redirect:/logedUser";
	    }
}
