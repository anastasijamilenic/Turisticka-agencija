package demo;

import java.math.BigDecimal;

public class Osiguranje {


	    private int idOsiguranja;
	    private String tipOsiguranja;
	    private String opis;
	    private BigDecimal cijena;
	    
	    
		public int getIdOsiguranja() {
			return idOsiguranja;
		}
		public void setIdOsiguranja(int idOsiguranja) {
			this.idOsiguranja = idOsiguranja;
		}
		public String getTipOsiguranja() {
			return tipOsiguranja;
		}
		public void setTipOsiguranja(String tipOsiguranja) {
			this.tipOsiguranja = tipOsiguranja;
		}
		public String getOpis() {
			return opis;
		}
		public void setOpis(String opis) {
			this.opis = opis;
		}
		public BigDecimal getCijena() {
			return cijena;
		}
		public void setCijena(BigDecimal cijena) {
			this.cijena = cijena;
		}
		
		@Override
		public String toString() {
			
			return this.tipOsiguranja ;
		}

	    
	

}
