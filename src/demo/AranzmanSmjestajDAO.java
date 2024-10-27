package demo;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class AranzmanSmjestajDAO {

    public void addSmjestajToAranzman(int aranzmanId, int smjestajId) throws SQLException {
        String query = "INSERT INTO smjestaj_has_aranzman (ARANZMAN_idAranzmana, SMJESTAJ_idSmjestaja) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aranzmanId);
            stmt.setInt(2, smjestajId);
            stmt.executeUpdate();
        }
    }

    public void removeSmjestajFromAranzman(int aranzmanId, int smjestajId) throws SQLException {
        String query = "DELETE FROM  WHERE ARANZMAN_idAranzmana = ? AND SMJESTAJ_idSmjestaja = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aranzmanId);
            stmt.setInt(2, smjestajId);
            stmt.executeUpdate();
        }
    }

    public List<Smjestaj> getSmjestajiForAranzman(int aranzmanId) throws SQLException {
        List<Smjestaj> smjestaji = new ArrayList<>();
        String query = "SELECT s.* FROM SMJESTAJ s " +
                       "JOIN smjestaj_has_aranzman as ON s.idSmjestaja = as.SMJESTAJ_idSmjestaja " +
                       "WHERE as.ARANZMAN_idAranzmana = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aranzmanId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Smjestaj smjestaj = new Smjestaj();
                smjestaj.setId(rs.getInt("id"));
                smjestaj.setVrstaSmjestaja(rs.getString("vrstaSmjestaja"));
                smjestaj.setNazivSmjestaja(rs.getString("nazivSmjestaja"));
                smjestaj.setLokacija(rs.getString("adresa"));
                smjestaj.setBrojZvjezdica(rs.getInt("brojZvjezdica"));
                smjestaj.setPogodnosti(rs.getString("pogodnosti"));
                smjestaj.setCijena(rs.getBigDecimal("cijena"));
               
                smjestaji.add(smjestaj);
            }
        }
        return smjestaji;
    }

    public List<Aranzman> getAranzmaniForSmjestaj(int smjestajId) throws SQLException {
        List<Aranzman> aranzmani = new ArrayList<>();
        String query = "SELECT a.* FROM ARANZMAN a " +
                       "JOIN smjestaj_has_aranzman as ON a.idAranzmana = as.ARANZMAN_idAranzmana " +
                       "WHERE as.SMJESTAJ_idSmjestaja = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, smjestajId);
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

