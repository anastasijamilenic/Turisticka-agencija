package demo;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrevozDAO {
	public void createPrevoz(Prevoz prevoz) throws SQLException {
	    String query = "INSERT INTO PREVOZ (tipPrevoza, nazivKompanije, cijena) VALUES (?, ?, ?)";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setString(1, prevoz.getTipPrevoza());
	        stmt.setString(2, prevoz.getNazivKompanije());
	        stmt.setBigDecimal(3, prevoz.getCijena());
	        stmt.executeUpdate();

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                prevoz.setIdPrevoza(generatedKeys.getInt(1));
	            } else {
	                throw new SQLException("Creating prevoz failed, no ID obtained.");
	            }
	        }
	    }
	}

    public Prevoz getPrevoz(int id) throws SQLException {
        String query = "SELECT * FROM PREVOZ WHERE idPrevoza = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Prevoz(
                    rs.getInt("idPrevoza"),
                    rs.getString("tipPrevoza"),
                    rs.getString("nazivKompanije"),
                    rs.getBigDecimal("cijena")
                );
            }
        }
        return null;
    }

    public List<Prevoz> getAllPrevoz() throws SQLException {
        List<Prevoz> prevozi = new ArrayList<>();
        String query = "SELECT * FROM PREVOZ";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                prevozi.add(new Prevoz(
                    rs.getInt("idPrevoza"),
                    rs.getString("tipPrevoza"),
                    rs.getString("nazivKompanije"),
                    rs.getBigDecimal("cijena")
                ));
            }
        }
        return prevozi;
    }

    public void updatePrevoz(Prevoz prevoz) throws SQLException {
        String query = "UPDATE PREVOZ SET tipPrevoza = ?, nazivKompanije = ?, cijena = ? WHERE idPrevoza = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, prevoz.getTipPrevoza());
            stmt.setString(2, prevoz.getNazivKompanije());
            stmt.setBigDecimal(3, prevoz.getCijena());
            stmt.setInt(4, prevoz.getIdPrevoza());
            stmt.executeUpdate();
        }
    }

    public void deletePrevozByTipAndKompanija(String tipPrevoza, String nazivKompanije) throws SQLException {
        String sql = "DELETE FROM Prevoz WHERE tipPrevoza = ? AND nazivKompanije = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipPrevoza);
            stmt.setString(2, nazivKompanije);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Prevoz uspješno obrisan.");
            } else {
                System.out.println("Nije pronađen prevoz s ovim tipom i nazivom kompanije.");
            }
        }
    }
    
    //brisanje po nazivu TREBA
    public void deletePrevoz(int id) throws SQLException {
        String query = "DELETE FROM PREVOZ WHERE idPrevoza = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
