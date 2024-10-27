package demo;

import java.time.LocalDate;

public class Klijent {
	
	private int idKlijenta;
	private String ime;
	private String prezime;
	private String brojTelefona;
	private String email;
	private LocalDate datumRodjenja;
	
	public int getIdKlijenta() {
		return idKlijenta;
	}
	public void setIdKlijenta(int idKlijenta) {
		this.idKlijenta = idKlijenta;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	
	public String toString() {
		return this.ime + " " + this.prezime;
	}
	
}
