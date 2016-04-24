package pl.musicland.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.musicland.model.AddProduct;
import pl.musicland.model.Produkt;
import pl.musicland.dao.ProduktDAO;

@Service
public class ProdManagerImpl implements ProdManager {

	@Autowired
	KatManager katmanager;
	@Autowired
	GatunekManager gatunekmanager;
	@Autowired
	ProducentManager producentmanager;
	@Autowired
	AutorManager autormanager;
	@Autowired
	ProduktDAO dao;

	@Override
	public List<Produkt> getSpecProdAlbum(int cat, int genre) {
		return dao.getSpecProdAlbum(cat, genre);
	}

	@Override
	public Produkt getProduct(int id) {
		return dao.getProduct(id);
	}

	@Override
	public int getNumberofProd(int id) {
		return dao.getNumberofProd(id);
	}

	@Override
	public List<Produkt> getSpecProdNieAlbum(int cat, int produ) {
		return dao.getSpecProdOthers(cat, produ);
	}

	@Override
	public void decreaseNumberOfProduct(int produktid, int iloscprod) {
		dao.decreaseNumberOfProduct(produktid, iloscprod);
	}

	@Override
	public void insertProduct(AddProduct addproduct, MultipartFile image) {
		String nazwa = addproduct.getNazwa();
		int kategoriaid = 0;
		int gatunekid = 0;
		int autorid = 0;
		int producentid = 0;
		int ilosc = Integer.parseInt(addproduct.getIlosc());
		float cena = Float.parseFloat(addproduct.getCena());
		String opis = addproduct.getOpis();
		String zdjecie = image.getOriginalFilename();
		kategoriaid = katmanager.addCat(addproduct.getKategoria());
		producentid = producentmanager.addProd(addproduct.getProducent());
		if (addproduct.getCzyalbum().booleanValue()) {
			gatunekid = gatunekmanager.addGat(addproduct.getGatunek());
			autorid = autormanager.addAutor(addproduct.getAutorImie(), addproduct.getAutorNazwisko(),
					addproduct.getAutorPseudonim());
			dao.addProduct(nazwa, kategoriaid, producentid, gatunekid, autorid, ilosc, cena, opis, zdjecie);
		} else {
			dao.addProduct(nazwa, kategoriaid, producentid, ilosc, cena, opis, zdjecie);
		}
		try {
			if (!image.isEmpty()) {
				saveImage(image);
			}
		} catch (IOException e) {
			// result.reject(e.getMessage());
		}
	}

	private void saveImage(MultipartFile image) throws IOException {
		String dest = "D:/Eclipse/miniatury/" + image.getOriginalFilename();
		try {
			image.transferTo(new File(dest));
		} catch (IOException e) {
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
