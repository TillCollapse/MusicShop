package pl.musicland.controller;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pl.musicland.model.AddProduct;
import pl.musicland.service.KatManager;
import pl.musicland.service.ProducentManager;
import pl.musicland.service.AutorManager;
import pl.musicland.service.GatunekManager;

@Controller
@RequestMapping(value="adminAddProd")
public class AdminAddProd {
	
	@Autowired
	KatManager katmanager;
	@Autowired
	GatunekManager gatunekmanager;
	@Autowired
	ProducentManager producentmanager;
	@Autowired 
	AutorManager autormanager;
	
	@RequestMapping(method = RequestMethod.GET) 
	public String showPanel(Model model) {
		AddProduct addproduct = new AddProduct();
		model.addAttribute("addproduct",addproduct);
		return "adminAddProd";
	}
	
	/*@Valid informuje o konieczności walidacji obiektu User, który zostanie przekazany dopiero po pozytywnym przejsciu walidacji*/
	@RequestMapping( method = RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute("addproduct") AddProduct addproduct,BindingResult result, @RequestParam(value="image", required=false) MultipartFile image) {
		//System.out.println(image.getOriginalFilename());
		if(result.hasErrors()) {
			return "adminAddProd";
		} /*else {
			
			katmanager.addCat(addproduct.getKategoria());
			producentmanager.addProd(addproduct.getProducent());
			if(addproduct.getCzyalbum() == true) {
				
				gatunekmanager.addGat(addproduct.getGatunek());
				autormanager.addAutor(addproduct.getAutorImie(), addproduct.getAutorNazwisko(), addproduct.getAutorPseudonim());
			
			} else {}
			
		}*/
		/*try {
			if(!image.isEmpty()) {
				//saveImage(image);
			}
		} catch (IOException e) {
				result.reject(e.getMessage());
		}*/
		
		return "adminAddProd";
	}
	
	private void saveImage(MultipartFile image) throws IOException {
		String dest = "D:\\Eclipse/workspace/MusicLand/WebContent/resources/images/" + image.getOriginalFilename();
		System.out.println(dest);
		try {
			image.transferTo(new File(dest));
		} catch(IOException e) {
			System.out.println("Nie udało się zapisać obrazu");
		}
	}
	
	
	
	class ImageUploadException extends RuntimeException {
		
		private static final long serialVersionUID = 1L;

		public ImageUploadException(String s) {
			super(s);
		}

	}


}
