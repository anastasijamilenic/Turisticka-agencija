package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Rezervacija {
    private int idRezervacije;
    private LocalDate datumRezervacije;
    private BigDecimal ukupnaCijena;
    
    private int klijentId;
    private int aranzmanId;
    private int zaposleniId;
    private int nacinPlacanjaId;
    private int poslovnicaId;
    private List<Usluga> usluge;
    private String valuta;
  
    
    // Konstruktor sa svim poljima
    public Rezervacija(int id, LocalDate datum, BigDecimal ukupnaCijena, int nacinPlacanjaId,
                       int klijentId, int aranzmanId, int zaposleniId, int poslovnicaId, int brojOsoba) {
        this.idRezervacije = id;
        this.datumRezervacije = datum;
        this.ukupnaCijena = ukupnaCijena;
        this.nacinPlacanjaId = nacinPlacanjaId;
        this.klijentId = klijentId;
        this.aranzmanId = aranzmanId;
        this.zaposleniId = zaposleniId;
        this.poslovnicaId = poslovnicaId;
       
    }

    public Rezervacija() {
        // Prazan konstruktor
    }

    // Getteri i setteri za sva polja
    public int getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(int idRezervacije) {
        this.idRezervacije = idRezervacije;
    }

    public LocalDate getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(LocalDate datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public BigDecimal getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setUkupnaCijena(BigDecimal ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public int getNacinPlacanjaId() {
        return nacinPlacanjaId;
    }

    public void setNacinPlacanjaId(int nacinPlacanjaId) {
        this.nacinPlacanjaId = nacinPlacanjaId;
    }

    public int getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(int klijentId) {
        this.klijentId = klijentId;
    }

    public int getAranzmanId() {
        return aranzmanId;
    }

    public void setAranzmanId(int aranzmanId) {
        this.aranzmanId = aranzmanId;
    }

    public int getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(int zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public int getPoslovnicaId() {
        return poslovnicaId;
    }

    public void setPoslovnicaId(int poslovnicaId) {
        this.poslovnicaId = poslovnicaId;
    }

	public List<Usluga> getUsluge() {
		return usluge;
	}

	

	public void setUsluge(List<Usluga> selectedUsluge) {
		
		this.usluge = selectedUsluge;
		
	}
	
	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

   
}
