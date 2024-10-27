package demo;

public class Vodic {
	
	private int idVodica;
    private String ime;
    private String prezime;
    private String JMB;
    private String lokacija;
    private String brojTelefona;
    
    
	public int getIdVodica() {
		return idVodica;
	}
	public void setIdVodica(int idVodica) {
		this.idVodica = idVodica;
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
	public String getJMB() {
		return JMB;
	}
	public void setJMB(String jMB) {
		JMB = jMB;
	}
	public String getLokacija() {
		return lokacija;
	}
	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}
	
	@Override
	public String toString() {
		
		return this.ime + " " + this.prezime;
	}
	
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

}
