package demo;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Aranzman {
    private int idAranzmana;
    private String nazivDestinacije;
    private BigDecimal cijena;
    private LocalDate termin;
    private String trajanje;
    private int vodicId;
    private int osiguranjeId;
    private int idKategorije;
    private String valuta;
    
    public Aranzman() {
    	
    }
    
	public Aranzman(int id, String naziv, BigDecimal cijena2, LocalDate termin2, String trajanje, int vodicId,
			int osiguranjeId2) {
		this.idAranzmana = id;
		this.nazivDestinacije = naziv;
		this.cijena = cijena2;
		this.termin = termin2;
		this.trajanje = trajanje;
		this.vodicId = vodicId;
	}
	public int getIdAranzmana() {
		return idAranzmana;
	}
	public void setIdAranzmana(int idAranzmana) {
		this.idAranzmana = idAranzmana;
	}
	public String getNazivDestinacije() {
		return nazivDestinacije;
	}
	public void setNazivDestinacije(String nazivDestinacije) {
		this.nazivDestinacije = nazivDestinacije;
	}
	public LocalDate getTermin() {
		return termin;
	}
	public void setTermin(LocalDate termin) {
		this.termin = termin;
	}
	public String getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(String trajanje) {
		this.trajanje = trajanje;
	}
	public BigDecimal getCijena() {
		return cijena;
	}
	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}
	public int getVodicId() {
		return vodicId;
	}
	public void setVodicId(int vodicId) {
		this.vodicId = vodicId;
	}
	public int getOsiguranjeId() {
		return osiguranjeId;
	}
	public void setOsiguranjeId(int osiguranjeId) {
		this.osiguranjeId = osiguranjeId;
	}

	public int getIdKategorije() {
		return idKategorije;
	}

	public void setIdKategorije(int idKategorije) {
		this.idKategorije = idKategorije;
	}
	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	public String toString() {
		
		return this.nazivDestinacije + " " + this.termin + " " + this.trajanje;
	}

   
}