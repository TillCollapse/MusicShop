package pl.musicland.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;

import pl.musicland.service.KoszykManager;
import pl.musicland.service.ListaZakupowManager;
import pl.musicland.service.ProdManager;
import pl.musicland.service.UserManager;
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
	@Autowired
	UserManager userManager;
	
	private Logger logger = Logger.getLogger(ShopingConroller.class);
	
	//@ResponseBody This annotation can be put on a method and indicates that the return type should be written straight to the HTTP response body (and not placed in a Model, or interpreted as a view name)
	@RequestMapping(value="/addToBasket", method = RequestMethod.POST)
	@ResponseBody
	public String addProduct(HttpServletRequest request){
		String idprod = request.getParameter("idprod");
		String prodilosc = request.getParameter("iloscprod");
		int produktid = Integer.parseInt(idprod);
		int iloscprod = Integer.parseInt(prodilosc);
		int userid = getUserId();
		String result = koszyk.addProduct(produktid, iloscprod, userid);
		return result;
	}
	@RequestMapping("/basket") 
    public String showbasket(Model model) {
	  	int koszykid = koszyk.isExist(getUserId());
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
	  		HelpObject ob = new HelpObject();
	  		ob.setProduktid(produkty.get(i).getProduktid());
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
	@RequestMapping("/delete")
	public @ResponseBody void delete(@RequestParam() int id) {
		koszyk.deleteProduct(id, getUserId());
	}
	@RequestMapping(value="/cancel",  produces = "text/html; charset=UTF-8") 
	public @ResponseBody String cancel() {
		int userid = getUserId();
		koszyk.cancelOrder(userid);
		return "Twój koszyk został opróżniony";
	}
	@RequestMapping("/finish")
    public String finish() {
	 	koszyk.finish(getUserId());
    	return "redirect:/logedUser";
    }
	private Authentication getAuthentication() {
    	return SecurityContextHolder.getContext().getAuthentication();
    }
	private int getUserId() {
		Authentication authentication = getAuthentication();
		String email = null;
    	if( authentication.isAuthenticated() == true) {
    		Object principal = authentication.getPrincipal();
	    	if (principal instanceof UserDetails) {
	    		email = ((UserDetails)principal).getUsername();
	    	} else {
	    		email = principal.toString();
	    	}
    	} 
    	return userManager.getUserId(email);
	}
	
}
