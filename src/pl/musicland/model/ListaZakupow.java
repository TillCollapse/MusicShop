package pl.musicland.model;

public class ListaZakupow {
	private int listazakupowid;
	private int koszykid;
	private int produktid;
	private int ilosc;
	
	public int getListazakupowid() {
		return listazakupowid;
	}
	public void setListazakupowid(int listazakupowid) {
		this.listazakupowid = listazakupowid;
	}
	public int getKoszykid() {
		return koszykid;
	}
	public void setKoszykid(int koszykid) {
		this.koszykid = koszykid;
	}
	public int getIlosc() {
		return ilosc;
	}
	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}
	public int getProduktid() {
		return produktid;
	}
	public void setProduktid(int produktid) {
		this.produktid = produktid;
	}
}
