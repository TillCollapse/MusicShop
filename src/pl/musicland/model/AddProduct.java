package pl.musicland.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import pl.musicland.others.ImageValidator;

public class AddProduct {
	
	private boolean czyalbum;
	
	@NotBlank(message = "Nazwa produktu nie powina być pusta")
	private String nazwa;
	private String producent;
	private String kategoria;
	private String gatunek;
	private String autorImie;
	private String autorNazwisko;
	private String autorPseudonim;
	private String opis;
	private String cenna;
	private String ilosc;
	
	@ImageValidator
	private MultipartFile image;

	public AddProduct() {
		
	}
	
	public boolean getCzyalbum() {
		return czyalbum;
	}

	public void setCzyalbum(boolean czyalbum) {
		this.czyalbum = czyalbum;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getProducent() {
		return producent;
	}
	public void setProducent(String producent) {
		this.producent = producent;
	}
	public String getKategoria() {
		return kategoria;
	}
	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}
	public String getGatunek() {
		return gatunek;
	}
	public void setGatunek(String gatunek) {
		this.gatunek = gatunek;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getCenna() {
		return cenna;
	}
	public void setCenna(String cenna) {
		this.cenna = cenna;
	}
	public String getIlosc() {
		return ilosc;
	}
	public void setIlosc(String ilosc) {
		this.ilosc = ilosc;
	}
	public String getAutorImie() {
		return autorImie;
	}
	public void setAutorImie(String autorImie) {
		this.autorImie = autorImie;
	}
	public String getAutorNazwisko() {
		return autorNazwisko;
	}
	public void setAutorNazwisko(String autorNazwisko) {
		this.autorNazwisko = autorNazwisko;
	}
	public String getAutorPseudonim() {
		return autorPseudonim;
	}
	public void setAutorPseudonim(String autorPseudonim) {
		this.autorPseudonim = autorPseudonim;
	}
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
