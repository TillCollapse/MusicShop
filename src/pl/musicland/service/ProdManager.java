package pl.musicland.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pl.musicland.model.AddProduct;
import pl.musicland.model.Produkt;;

public interface ProdManager {
	public List<Produkt> getSpecProdAlbum(int cat, int genre);

	public List<Produkt> getSpecProdNieAlbum(int cat, int produ);

	public Produkt getProduct(int id);

	public int getNumberofProd(int id);

	public void decreaseNumberOfProduct(int produktid, int iloscprod);

	public void insertProduct(AddProduct addprodukt, MultipartFile image);
}
