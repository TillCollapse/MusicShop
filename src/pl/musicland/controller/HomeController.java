package pl.musicland.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.musicland.model.Produkt;
import pl.musicland.service.AlbumyManager;
import pl.musicland.service.AutorManager;
import pl.musicland.service.NieAlbumyManager;
import pl.musicland.service.ProdManager;
import pl.musicland.service.ProducentManager;
import pl.musicland.service.UserManager;

@Controller
public class HomeController {

	@Autowired
	AlbumyManager albumy;
	@Autowired
	NieAlbumyManager niealbumy;
	@Autowired
	ProdManager prodManager;
	@Autowired
	UserManager userManager;
	@Autowired
	ProducentManager producentmanager;
	@Autowired
	AutorManager autormanager;

	private List<Map<String, Object>> getGenreInCat;
	private List<Map<String, Object>> getCatNames;
	private List<Map<String, Object>> getGenreInCat2;
	private List<Map<String, Object>> getCatNames2;
	private String email = null;
	private String username = null;

	@RequestMapping("/")
	public String getAllCat(Model model) {

		this.getGenreInCat = albumy.getGenreInCat();
		this.getCatNames = albumy.getCatNames();
		this.getGenreInCat2 = niealbumy.getGenreInCat();
		this.getCatNames2 = niealbumy.getCatNames();
		// Kategorie produktów
		model.addAttribute("genreList", this.getGenreInCat);
		model.addAttribute("catnameList", this.getCatNames);
		model.addAttribute("genreList2", this.getGenreInCat2);
		model.addAttribute("catnameList2", this.getCatNames2);

		username = getUserNameFromAuthentication();
		model.addAttribute("username", username);

		return "index";

	}

	@RequestMapping("/products")
	public String showSpecProducts(Model model, @RequestParam() int genre, @RequestParam() int cat) {
		model.addAttribute("genreList", this.getGenreInCat);
		model.addAttribute("catnameList", this.getCatNames);
		model.addAttribute("genreList2", this.getGenreInCat2);
		model.addAttribute("catnameList2", this.getCatNames2);
		model.addAttribute("productsList", prodManager.getSpecProdAlbum(cat, genre));
		model.addAttribute("username", username);
		return "productsList";
	}

	@RequestMapping("/products2")
	public String showSpecProducts2(Model model, @RequestParam() int produ, @RequestParam() int cat) {
		model.addAttribute("genreList", this.getGenreInCat);
		model.addAttribute("catnameList", this.getCatNames);
		model.addAttribute("genreList2", this.getGenreInCat2);
		model.addAttribute("catnameList2", this.getCatNames2);
		model.addAttribute("productsList", prodManager.getSpecProdNieAlbum(cat, produ));
		model.addAttribute("username", username);
		return "productsList";
	}

	@RequestMapping("/prodspec")
	public String showProduct(Model model, @RequestParam() int id) {
		Produkt produkt = prodManager.getProduct(id);
		String producent = producentmanager.getProducentNameById(produkt.getProducentid());
		String wykonawca = autormanager.getAutorById(produkt.getAutorsid());
		System.out.println(produkt.getAutorsid());
		model.addAttribute("genreList", this.getGenreInCat);
		model.addAttribute("catnameList", this.getCatNames);
		model.addAttribute("genreList2", this.getGenreInCat2);
		model.addAttribute("catnameList2", this.getCatNames2);
		model.addAttribute("product", produkt);
		model.addAttribute("producent", producent);
		model.addAttribute("wykonawca", wykonawca);
		model.addAttribute("username", username);

		return "product";
	}

	@RequestMapping("/logedUser")
	public String logedVersion(HttpSession session, Model model) {
		username = getUserNameFromAuthentication();
		model.addAttribute("username", username);
		model.addAttribute("genreList", this.getGenreInCat);
		model.addAttribute("catnameList", this.getCatNames);
		model.addAttribute("genreList2", this.getGenreInCat2);
		model.addAttribute("catnameList2", this.getCatNames2);
		model.addAttribute("username", username);

		return "index";
	}

	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	private String getUserNameFromAuthentication() {
		Authentication authentication = getAuthentication();
		if (authentication.isAuthenticated() == true) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				email = ((UserDetails) principal).getUsername();
			} else {
				email = principal.toString();
			}
		}
		if (!email.equals("anonymousUser")) {
			username = userManager.getUserName(email);
		}
		return username;
	}
}