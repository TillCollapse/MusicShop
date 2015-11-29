package pl.musicland.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import pl.musicland.service.AlbumyManager;
import pl.musicland.service.NieAlbumyManager;
import pl.musicland.service.ProdManager;
import pl.musicland.model.Niealbumy;
import pl.musicland.model.User;
import pl.musicland.model.FormObject;

@Controller
public class HomeController {
	
	@Autowired
	AlbumyManager albumy;
	@Autowired
	NieAlbumyManager niealbumy;
	@Autowired
	ProdManager prodManager;
	
	private List<Map<String,Object>> getGenreInCat;
	private List<Map<String,Object>> getCatNames;
	private List<Niealbumy> getAllNieAlbumy;
	private List<Map<String,Object>> getGenreInCat2;
	private List<Map<String,Object>> getCatNames2;
	
	private String sessionid = null;
	private Object userData = null;
 
    @RequestMapping("/")
    public String getAllCat(Model model) {
    	
    	this.getGenreInCat = albumy.getGenreInCat();
    	this.getCatNames = albumy.getCatNames();
    	this.getGenreInCat2 = niealbumy.getGenreInCat();
    	this.getCatNames2 = niealbumy.getCatNames();
    	//Kategorie produktów
    	model.addAttribute("genreList", this.getGenreInCat);
    	model.addAttribute("catnameList", this.getCatNames);
    	model.addAttribute("genreList2", this.getGenreInCat2);
    	model.addAttribute("catnameList2", this.getCatNames2);
    	//
    	model.addAttribute("sessionid",this.sessionid);
    	model.addAttribute("userData", this.userData);
        return "index";
        
    }
    
    @RequestMapping("/products")
    public String showSpecProducts(Model model,
    @RequestParam() int genre,
    @RequestParam() int cat)
    {
    	//FormObject formObject = new FormObject();
    	model.addAttribute("genreList", this.getGenreInCat);
    	model.addAttribute("catnameList", this.getCatNames);
    	model.addAttribute("genreList2", this.getGenreInCat2);
    	model.addAttribute("catnameList2", this.getCatNames2);
    	model.addAttribute("productsList", prodManager.getSpecProdAlbum(cat, genre) );
    	model.addAttribute("sessionid",this.sessionid);
    	model.addAttribute("userData", this.userData);
    	//model.addAttribute("formObject", formObject);
    	return "productsList";
    }
    @RequestMapping("/products2")
    public String showSpecProducts2(Model model,
    @RequestParam() int produ,
    @RequestParam() int cat)
    {
    	//FormObject formObject = new FormObject();
    	model.addAttribute("genreList", this.getGenreInCat);
    	model.addAttribute("catnameList", this.getCatNames);
    	model.addAttribute("genreList2", this.getGenreInCat2);
    	model.addAttribute("catnameList2", this.getCatNames2);
    	model.addAttribute("productsList", prodManager.getSpecProdNieAlbum(cat, produ) );
    	model.addAttribute("sessionid",this.sessionid);
    	model.addAttribute("userData", this.userData);
    	//model.addAttribute("formObject", formObject);
    	return "productsList";
    }
    @RequestMapping("/prodspec")
    public String showProduct(Model model,
    @RequestParam() int id)
    {
    	model.addAttribute("genreList", this.getGenreInCat);
    	model.addAttribute("catnameList", this.getCatNames);
    	model.addAttribute("genreList2", this.getGenreInCat2);
    	model.addAttribute("catnameList2", this.getCatNames2);
    	model.addAttribute("product", prodManager.getProduct(id));
    	model.addAttribute("sessionid",this.sessionid);
    	model.addAttribute("userData", this.userData);
    	
    
    	return "product";
    }
    
    @RequestMapping("/logedUser")
    public String logedVersion(HttpSession session, Model model){
    	
    	this.sessionid = session.getId();
    	this.userData = session.getAttribute("user");
    	
    	model.addAttribute("genreList", this.getGenreInCat);
    	model.addAttribute("catnameList", this.getCatNames);
    	model.addAttribute("genreList2", this.getGenreInCat2);
    	model.addAttribute("catnameList2", this.getCatNames2);
    	
    	model.addAttribute("sessionid",this.sessionid);
    	model.addAttribute("userData", this.userData);
    	
    	return "index";
    }
    @RequestMapping("/logedOutUser")
    public String logout(HttpSession session) {
    	session.invalidate();
    	this.sessionid = null;
    	this.userData = null;
    	return "redirect:/";
    }
  
}