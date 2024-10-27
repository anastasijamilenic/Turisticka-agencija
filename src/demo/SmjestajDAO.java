package demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SmjestajDAO {

	public void createSmjestaj(Smjestaj smjestaj) throws SQLException {
	    String query = "INSERT INTO SMJESTAJ (nazivSmjestaja, vrstaSmjestaja, lokacija, brojZvjezdica, pogodnosti, cijena) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setString(1, smjestaj.getNazivSmjestaja());
	        stmt.setString(2, smjestaj.getVrstaSmjestaja());
	        stmt.setString(3, smjestaj.getLokacija());
	        stmt.setInt(4, smjestaj.getBrojZvjezdica());
	        stmt.setString(5, smjestaj.getPogodnosti());
	        stmt.setBigDecimal(6, smjestaj.getCijena());
	        stmt.executeUpdate();

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                smjestaj.setId(generatedKeys.getInt(1));
	            } else {
	                throw new SQLException("Creating smjestaj failed, no ID obtained.");
	            }
	        }
	    }
	}


    public Smjestaj getSmjestaj(int id) throws SQLException {
        String query = "SELECT * FROM SMJESTAJ WHERE idSmjestaja = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Smjestaj smjestaj = new Smjestaj();
                smjestaj.setId(rs.getInt("idSmjestaja"));
                smjestaj.setNazivSmjestaja(rs.getString("nazivSmjestaja"));
                smjestaj.setVrstaSmjestaja(rs.getString("vrstaSmjestaja"));
                smjestaj.setLokacija(rs.getString("lokacija"));
                smjestaj.setBrojZvjezdica(rs.getInt("brojZvjezdica"));
                smjestaj.setPogodnosti(rs.getString("pogodnosti"));
                smjestaj.setCijena(rs.getBigDecimal("cijena"));
                return smjestaj;
            }
        }
        return null;
    }

    public List<Smjestaj> getAllSmjestaji() throws SQLException {
        List<Smjestaj> smjestaji = new ArrayList<>();
        String query = "SELECT * FROM SMJESTAJ";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Smjestaj smjestaj = new Smjestaj();
                smjestaj.setId(rs.getInt("idSmjestaja"));
                smjestaj.setNazivSmjestaja(rs.getString("nazivSmjestaja"));
                smjestaj.setVrstaSmjestaja(rs.getString("vrstaSmjestaja"));
                smjestaj.setLokacija(rs.getString("lokacija"));
                smjestaj.setBrojZvjezdica(rs.getInt("brojZvjezdica"));
                smjestaj.setPogodnosti(rs.getString("pogodnosti"));
                smjestaj.setCijena(rs.getBigDecimal("cijena"));
                smjestaji.add(smjestaj);
            }
        }
        return smjestaji;
    }

    public void updateSmjestaj(Smjestaj smjestaj) throws SQLException {
        String query = "UPDATE SMJESTAJ SET nazivSmjestaja = ?, vrstaSmjestaja = ?, lokacija = ?, brojZvjezdica = ?, pogodnosti = ?, cijena = ? WHERE idSmjestaja = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, smjestaj.getNazivSmjestaja());
            stmt.setString(2, smjestaj.getVrstaSmjestaja());
            stmt.setString(3, smjestaj.getLokacija());
            stmt.setInt(4, smjestaj.getBrojZvjezdica());
            stmt.setString(5, smjestaj.getPogodnosti());
            stmt.setBigDecimal(6, smjestaj.getCijena());
            stmt.setInt(7, smjestaj.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteSmjestaj(int id) throws SQLException {
        String query = "DELETE FROM SMJESTAJ WHERE idSmjestaja = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public void deleteSmjestajByNaziv(String nazivSmjestaja) throws SQLException {
        String sql = "DELETE FROM Smjestaj WHERE nazivSmjestaja = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nazivSmjestaja);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Smještaj uspješno obrisan.");
            } else {
                System.out.println("Nije pronađen smještaj s ovim nazivom.");
            }
        }
    }
}
