package pl.musicland.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pl.musicland.model.AddProduct;
import pl.musicland.service.KoszykManager;
import pl.musicland.service.ProdManager;
import pl.musicland.service.UserManager;
import pl.musicland.service.AuthoritiesManager;
import pl.musicland.model.User;
import pl.musicland.model.UserAuthority;

@Controller
public class AdminController {

	@Autowired
	UserManager usermanager;
	@Autowired
	AuthoritiesManager authoritiesmanager;
	@Autowired
	KoszykManager koszykmanager;
	@Autowired
	ProdManager prodmanager;

	@RequestMapping(value = "/adminAddProd", method = RequestMethod.GET)
	public String showPanel(Model model) {
		AddProduct addproduct = new AddProduct();
		model.addAttribute("addproduct", addproduct);
		return "adminAddProd";
	}

	/*
	 * @Valid informuje o konieczności walidacji obiektu AddProduct, który
	 * zostanie przekazany dopiero po pozytywnym przejsciu walidacji
	 */
	@RequestMapping(value = "/adminAddProd", method = RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute("addproduct") AddProduct addproduct, BindingResult result,
			@RequestParam(value = "image", required = false) MultipartFile image) {
		if (result.hasErrors()) {
			return "adminAddProd";
		} else if (!image.isEmpty()) {
			prodmanager.insertProduct(addproduct, image);
		}
		return "adminAddProd";
	}

	@RequestMapping(value = "/changeAuthorities", method = RequestMethod.GET)
	public String changeAuthorities(Model model) {
		List<UserAuthority> userauthority;
		List<String> authorities;
		authorities = authoritiesmanager.getAuthorityTypes();
		userauthority = usermanager.getUserAuthority();
		model.addAttribute("userauthority", userauthority);
		model.addAttribute("authorities", authorities);
		return "changeAuthoritiesForm";
	}

	@RequestMapping(value = "/changeAuthorities", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public @ResponseBody String changeAuthoritiesProcess(HttpServletRequest request, HttpServletResponse response) {
		String authority = request.getParameter("authority");
		String email = request.getParameter("email");
		String returnvalue;
		boolean result = false;
		result = authoritiesmanager.changeUserAuthority(email, authority);
		if (result == true) {
			returnvalue = "Uprawnienia zmienione";
		} else {
			returnvalue = "Błąd";
		}
		return returnvalue;
	}

	@RequestMapping(value = "/registeredOrder")
	public String registeredOrder(Model model) {
		List<Map<String, Object>> userdatas = usermanager.getUserDetails();
		model.addAttribute("userdatas", userdatas);

		return "orderManagment";
	}

	@RequestMapping(value = "/registeredOrderDetails")
	public String registeredOrderDetails(Model model, @RequestParam() int id) {
		int userid = koszykmanager.getUserId(id);
		User user = usermanager.getUserById(userid);
		List<Map<String, Object>> productsList = koszykmanager.getProductsFromBasket(id);
		float cost = 0;
		for (Map<String, Object> tmp : productsList) {
			cost += (Integer) tmp.get("ilosc") * (Float) tmp.get("cena");
		}
		cost = round(cost, 2);
		model.addAttribute("user", user);
		model.addAttribute("productslist", productsList);
		model.addAttribute("cost", cost);
		model.addAttribute("koszykid", id);
		return "orderManagmentDetails";
	}

	@RequestMapping(value = "/tosend", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public @ResponseBody String send(@RequestParam() int id) {
		koszykmanager.toSend(id);
		return "Zamówienie czeka na wysyłke";
	}

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

}
