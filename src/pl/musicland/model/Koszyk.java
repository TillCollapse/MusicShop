package pl.musicland.model;

import java.sql.Timestamp;

public class Koszyk {
	private int koszykid;
	private int userid;
	private int statusid;
	private Timestamp data;
	
	public int getKoszykid() {
		return koszykid;
	}
	public void setKoszykid(int koszykid) {
		this.koszykid = koszykid;
	}
	public int getStatusid() {
		return statusid;
	}
	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
}
