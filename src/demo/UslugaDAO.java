package demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UslugaDAO {

  
	public void createUsluga(Usluga usluga) throws SQLException {
	    String query = "INSERT INTO USLUGA (tipUsluge, cijena) VALUES (?, ?)";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setString(1, usluga.getTipUsluge());
	        stmt.setBigDecimal(2, usluga.getCijena());
	        stmt.executeUpdate();

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                usluga.setIdUsluge(generatedKeys.getInt(1));
	            } else {
	                throw new SQLException("Creating usluga failed, no ID obtained.");
	            }
	        }
	    }
	}

    
    public void deleteUslugaByTip(String tipUsluge) throws SQLException {
        String sql = "DELETE FROM Usluga WHERE tipUsluge = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipUsluge);
            stmt.executeUpdate();
        }
    }

    public Usluga getUsluga(int id) throws SQLException {
        String query = "SELECT * FROM USLUGA WHERE idUsluge = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usluga usluga = new Usluga();
                usluga.setIdUsluge(rs.getInt("idUsluge"));
                usluga.setTipUsluge(rs.getString("tipUsluge"));
                usluga.setCijena(rs.getBigDecimal("cijena"));
                return usluga;
            }
        }
        return null;
    }

    public List<Usluga> getAllUsluge() throws SQLException {
        List<Usluga> usluge = new ArrayList<>();
        String query = "SELECT * FROM USLUGA";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usluga usluga = new Usluga();
                usluga.setIdUsluge(rs.getInt("idUsluge"));
                usluga.setTipUsluge(rs.getString("tipUsluge"));
                usluga.setCijena(rs.getBigDecimal("cijena"));
                usluge.add(usluga);
            }
        }
        return usluge;
    }

    public void updateUsluga(Usluga usluga) throws SQLException {
        String query = "UPDATE USLUGA SET tipUsluge = ?, cijena = ? WHERE idUsluge = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, usluga.getTipUsluge());
            stmt.setBigDecimal(2, usluga.getCijena());
            stmt.setInt(3, usluga.getIdUsluge());
            stmt.executeUpdate();
        }
    }

    public void deleteUsluga(int id) throws SQLException {
        String query = "DELETE FROM USLUGA WHERE idUsluge = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

