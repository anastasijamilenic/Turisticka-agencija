package demo;

import java.math.BigDecimal;

public class Smjestaj {
    private int id;
    private String nazivSmjestaja; // Dodato novo polje
    private String vrstaSmjestaja;
    private String lokacija; // Promijenjeno iz adrese u lokaciju
    private int brojZvjezdica;
    private String pogodnosti;
    private BigDecimal cijena;
    private String valuta;

    // Getters and Setters
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return nazivSmjestaja + " (" + vrstaSmjestaja + ")";
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivSmjestaja() {
        return nazivSmjestaja;
    }

    public void setNazivSmjestaja(String nazivSmjestaja) {
        this.nazivSmjestaja = nazivSmjestaja;
    }

    public String getVrstaSmjestaja() {
        return vrstaSmjestaja;
    }

    public void setVrstaSmjestaja(String vrstaSmjestaja) {
        this.vrstaSmjestaja = vrstaSmjestaja;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getBrojZvjezdica() {
        return brojZvjezdica;
    }

    public void setBrojZvjezdica(int brojZvjezdica) {
        this.brojZvjezdica = brojZvjezdica;
    }

    public String getPogodnosti() {
        return pogodnosti;
    }

    public void setPogodnosti(String pogodnosti) {
        this.pogodnosti = pogodnosti;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	
}
