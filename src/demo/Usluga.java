package demo;

import java.math.BigDecimal;

public class Usluga {
    private int idUsluge;
    private String tipUsluge;
    private BigDecimal cijena;
    private String valuta;

   
    public int getIdUsluge() {
        return idUsluge;
    }

    public void setIdUsluge(int idUsluge) {
        this.idUsluge = idUsluge;
    }

    public String getTipUsluge() {
        return tipUsluge;
    }

    public void setTipUsluge(String tipUsluge) {
        this.tipUsluge = tipUsluge;
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
    public String toString() {
    	
    	return this.tipUsluge + " " + this.cijena;
    }
}
