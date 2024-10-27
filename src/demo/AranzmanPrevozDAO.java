package demo;



import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class AranzmanPrevozDAO {

    public void addPrevozToAranzman(int aranzmanId, int prevozId) throws SQLException {
        String query = "INSERT INTO aranzman_has_prevoz (ARANZMAN_idAranzmana, PREVOZ_idPrevoza) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aranzmanId);
            stmt.setInt(2, prevozId);
            stmt.executeUpdate();
        }
    }

    public void removePrevozFromAranzman(int aranzmanId, int prevozId) throws SQLException {
        String query = "DELETE FROM aranzman_has_prevoz WHERE ARANZMAN_idAranzmana = ? AND PREVOZ_idPrevoza = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aranzmanId);
            stmt.setInt(2, prevozId);
            stmt.executeUpdate();
        }
    }

    public List<Prevoz> getPrevoziForAranzman(int aranzmanId) throws SQLException {
        List<Prevoz> prevozi = new ArrayList<>();
        String query = "SELECT p.* FROM PREVOZ p " +
                       "JOIN aranzman_has_prevoz ap ON p.idPrevoza = ap.PREVOZ_idPrevoza " +
                       "WHERE ap.ARANZMAN_idAranzmana = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aranzmanId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prevoz prevoz = new Prevoz();
                prevoz.setIdPrevoza(rs.getInt("idPrevoza"));
                prevoz.setTipPrevoza(rs.getString("tipPrevoza"));
                prevoz.setNazivKompanije(rs.getString("nazivKompanije"));
                prevozi.add(prevoz);
            }
        }
        return prevozi;
    }

    public List<Aranzman> getAranzmaniForPrevoz(int prevozId) throws SQLException {
        List<Aranzman> aranzmani = new ArrayList<>();
        String query = "SELECT a.* FROM ARANZMAN a " +
                       "JOIN aranzman_has_prevoz ap ON a.idAranzmana = ap.ARANZMAN_idAranzmana " +
                       "WHERE ap.PREVOZ_idPrevoza = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, prevozId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Aranzman aranzman = new Aranzman();
                aranzman.setIdAranzmana(rs.getInt("idAranzmana"));
                aranzman.setNazivDestinacije(rs.getString("naziv"));
                aranzman.setCijena(rs.getBigDecimal("cena"));
                aranzmani.add(aranzman);
            }
        }
        return aranzmani;
    }
}

