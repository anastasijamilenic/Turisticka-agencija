package demo;

public class Kategorija {

	
	private int idKategorije;
	private String nazivKategorije;
	
	public int getIdKategorije() {
		return idKategorije;
	}
	public void setIdKategorije(int idKategorije) {
		this.idKategorije = idKategorije;
	}
	public String getNazivKategorije() {
		return nazivKategorije;
	}
	public void setNazivKategorije(String nazivKategorije) {
		this.nazivKategorije = nazivKategorije;
	}
	
	public String toString() {
		return this.nazivKategorije;
	}
}
