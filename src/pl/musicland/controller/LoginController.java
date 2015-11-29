package pl.musicland.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.musicland.model.User;
import pl.musicland.service.UserManager;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserManager userManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewLogin(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "loginForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String loginProcess( @ModelAttribute User user, Model model, HttpSession session) {
		//System.out.println(user.getEmail());
		User tmp = userManager.loginUser(user.getEmail(), user.getHaslo());
		//System.out.println(tmp.getImie());
		//System.out.println(tmp.getNazwisko());
		session.setAttribute("user", tmp);
		return "redirect:/logedUser";
	}
}
