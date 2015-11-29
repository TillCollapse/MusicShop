package pl.musicland.model;

public class Produkt {
	
	private int produktid;
	private String nazwa;
	private int kategoriaid;
	private int gatunekid;
	private int autorsid;
	private int producentid;
	private int ilosc;
	private float cena;
	private String opis;
	private String zdjecie;
	
	public int getProduktid() {
		return produktid;
	}
	public void setProduktid(int produktid) {
		this.produktid = produktid;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public int getKategoriaid() {
		return kategoriaid;
	}
	public void setKategoriaid(int kategoriaid) {
		this.kategoriaid = kategoriaid;
	}
	public int getGatunekid() {
		return gatunekid;
	}
	public void setGatunekid(int gatunekid) {
		this.gatunekid = gatunekid;
	}
	public int getProducentid() {
		return producentid;
	}
	public void setProducentid(int producentid) {
		this.producentid = producentid;
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
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getZdjecie() {
		return zdjecie;
	}
	public void setZdjecie(String zdjecie) {
		this.zdjecie = zdjecie;
	}
	public int getAutorsid() {
		return autorsid;
	}
	public void setAutorsid(int autorsid) {
		this.autorsid = autorsid;
	}
}
