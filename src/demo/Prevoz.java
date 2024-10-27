package demo;

import java.math.BigDecimal;

public class Prevoz {
    private int idPrevoza;
    private String tipPrevoza;
    private String nazivKompanije;
    private BigDecimal cijena;
    private String valuta;

    public Prevoz() {}

    public Prevoz(int idPrevoza, String tipPrevoza, String nazivKompanije, BigDecimal cijena) {
        this.setIdPrevoza(idPrevoza);
        this.setTipPrevoza(tipPrevoza);
        this.setNazivKompanije(nazivKompanije);
        this.setCijena(cijena);
    }

	public int getIdPrevoza() {
		return idPrevoza;
	}

	
	@Override
    public String toString() {
        return tipPrevoza + " - " + nazivKompanije;
    }
	
	public void setIdPrevoza(int idPrevoza) {
		this.idPrevoza = idPrevoza;
	}

	public String getTipPrevoza() {
		return tipPrevoza;
	}

	public void setTipPrevoza(String tipPrevoza) {
		this.tipPrevoza = tipPrevoza;
	}

	public String getNazivKompanije() {
		return nazivKompanije;
	}

	public void setNazivKompanije(String nazivKompanije) {
		this.nazivKompanije = nazivKompanije;
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
