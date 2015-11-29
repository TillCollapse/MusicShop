package pl.musicland.model;

public class Autor {
	private int autorid;
	private String imie;
	private String nazwisko;
	private String pseudonim;
	
	public int getAutorid() {
		return autorid;
	}
	public void setAutorid(int autorid) {
		this.autorid = autorid;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getPseudonim() {
		return pseudonim;
	}
	public void setPseudonim(String pseudonim) {
		this.pseudonim = pseudonim;
	}
	
}
