package demo;

public class Poslovnica {

	
	private int idPoslovnice;
	private String brojTelefona;
	private String adresa;
	
	public int getIdPoslovnice() {
		return idPoslovnice;
	}
	public void setIdPoslovnice(int idPoslovnice) {
		this.idPoslovnice = idPoslovnice;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public String toString() {
		return this.adresa;
	}
	
}
