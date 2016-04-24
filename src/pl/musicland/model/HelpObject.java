package pl.musicland.model;

public class HelpObject {
	int produktid;
	String nazwa;
	int ilosc;
	float cena;
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public int getIlosc() {
		return ilosc;
	}
	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		this.cena = cena;
	}
	public int getProduktid() {
		return produktid;
	}
	public void setProduktid(int produktid) {
		this.produktid = produktid;
	}
}
