package demo;

import java.math.BigDecimal;

public class Zaposleni {

	
	private int idZaposlenog;
	private String ime;
	private String prezime;
	private BigDecimal plata;
	private String pozicija;
	private int idPoslovnice;
	
	public int getIdZaposlenog() {
		return idZaposlenog;
	}
	public void setIdZaposlenog(int idZaposlenog) {
		this.idZaposlenog = idZaposlenog;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public BigDecimal getPlata() {
		return plata;
	}
	public void setPlata(BigDecimal plata) {
		this.plata = plata;
	}
	public String getPozicija() {
		return pozicija;
	}
	public void setPozicija(String pozicija) {
		this.pozicija = pozicija;
	}
	public int getIdPoslovnice() {
		return idPoslovnice;
	}
	public void setIdPoslovnice(int idPoslovnice) {
		this.idPoslovnice = idPoslovnice;
	}

	public String toString() {
		
		return this.ime + " " + this.prezime;
	}
	
}
