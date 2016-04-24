package pl.musicland.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.musicland.model.LoginUser;
import pl.musicland.service.UserManager;

@Controller
public class LoginController {
	
	@Autowired
	UserManager userManager;
	
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String viewLogin(@Valid @ModelAttribute("loginuser") LoginUser user,BindingResult result, Model model) {
		if(result.hasErrors()) {
			result.rejectValue("email","Pattern.loginuser.email,Pattern.email,Pattern.java.lang.String,Pattern","Użytkownik o podanym email lub haśle nieistnieje");
			return "loginForm";
		}
		return "loginForm";
	}
	
	@RequestMapping(value = "/login/loginProcess", method = RequestMethod.GET)
	public String loginProcess() {
		return "redirect:/logedUser";
	}
}
