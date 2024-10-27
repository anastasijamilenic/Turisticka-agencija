package demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RezervacijaDAO {

    // CREATE
    public void createRezervacija(Rezervacija rezervacija) throws SQLException {
        String query = "INSERT INTO REZERVACIJA ( datumRezervacije,  ukupnaCijena, idKlijenta, idAranzmana, idZaposlenog, NACIN_PLACANJA_idNacinaPlacanja, POSLOVNICA_idPoslovnice) VALUES (?, ?, ?, ?, ?, ?,  ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            
            stmt.setDate(1, Date.valueOf(rezervacija.getDatumRezervacije()));
            stmt.setBigDecimal(2, rezervacija.getUkupnaCijena());
            stmt.setInt(3, rezervacija.getKlijentId());
            stmt.setInt(4, rezervacija.getAranzmanId());
            stmt.setInt(5, rezervacija.getZaposleniId());
            stmt.setInt(6, rezervacija.getNacinPlacanjaId());
            
            stmt.setInt(7, rezervacija.getPoslovnicaId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    rezervacija.setIdRezervacije(generatedKeys.getInt(1));
                }
            }
        }
    }

    // READ
    public Rezervacija getRezervacijaById(int idRezervacije) throws SQLException {
        String query = "SELECT * FROM REZERVACIJA WHERE idRezervacije = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idRezervacije);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractRezervacijaFromResultSet(rs);
                }
            }
        }
        return null;
    }

    // Metoda za dobijanje svih aranžmana
    public List<Rezervacija> getAllRezervacije() throws SQLException {
        List<Rezervacija> rezervacije = new ArrayList<>();
        String query = "SELECT * FROM REZERVACIJA";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               Rezervacija rezervacija = new Rezervacija();
                rezervacija.setIdRezervacije(rs.getInt("idRezervacije"));
                rezervacija.setDatumRezervacije(rs.getDate("datumRezervacije").toLocalDate());
                rezervacija.setUkupnaCijena(rs.getBigDecimal("ukupnaCijena"));
                rezervacija.setKlijentId(rs.getInt("idKlijenta"));
                rezervacija.setAranzmanId(rs.getInt("idAranzmana"));
                rezervacija.setZaposleniId(rs.getInt("idZaposlenog"));
                rezervacija.setNacinPlacanjaId(rs.getInt("NACIN_PLACANJA_idNacinaPlacanja"));
                rezervacija.setPoslovnicaId(rs.getInt("POSLOVNICA_idPoslovnice"));
                
                rezervacije.add(rezervacija);
            }
        }
        return rezervacije;
    }
    public Zaposleni getZaposleniById(int idZaposleni) throws SQLException {
        String query = "SELECT * FROM ZAPOSLENI WHERE idZaposlenog = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idZaposleni);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Zaposleni zaposleni = new Zaposleni();
                    zaposleni.setIdZaposlenog(rs.getInt("idZaposlenog"));
                    zaposleni.setIme(rs.getString("ime"));
                    zaposleni.setPrezime(rs.getString("prezime"));
                    zaposleni.setPlata(rs.getBigDecimal("plata"));
                    zaposleni.setPozicija(rs.getString("pozicija"));
                    zaposleni.setIdPoslovnice(rs.getInt("idPoslovnice"));
                    
                    
                    return zaposleni;
                }
            }
        }
        return null; // If no employee is found with the given ID
    }
    public List<Rezervacija> getRezervacijeByDestination(String nazivDestinacije) throws SQLException {
        List<Rezervacija> rezervacije = new ArrayList<>();
        String query = "SELECT * FROM REZERVACIJA r " +
                       "JOIN ARANZMAN a ON r.idAranzmana = a.idAranzmana " +
                       "WHERE a.nazivDestinacije = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nazivDestinacije);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Rezervacija rezervacija = new Rezervacija();
                    rezervacija.setIdRezervacije(rs.getInt("idRezervacije"));
                    rezervacija.setDatumRezervacije(rs.getDate("datumRezervacije").toLocalDate());
                    rezervacija.setUkupnaCijena(rs.getBigDecimal("ukupnaCijena"));
                    rezervacija.setKlijentId(rs.getInt("idKlijenta"));
                    rezervacija.setAranzmanId(rs.getInt("idAranzmana"));
                    rezervacija.setZaposleniId(rs.getInt("idZaposlenog"));
                    rezervacija.setNacinPlacanjaId(rs.getInt("NACIN_PLACANJA_idNacinaPlacanja"));
                    rezervacija.setPoslovnicaId(rs.getInt("POSLOVNICA_idPoslovnice"));
                    rezervacije.add(rezervacija);
                }
            }
        }
        return rezervacije;
    }


    
    public List<Rezervacija> getAllRezervacijeWithDetails() throws SQLException {
        List<Rezervacija> rezervacije = new ArrayList<>();
        String query = "SELECT r.*, k.ime, k.prezime, n.nazivNacinaPlacanja AS nacin_placanja, p.adresa AS poslovnica_adresa, a.nazivDestinacije, a.termin, a.trajanje " +
                       "FROM REZERVACIJA r " +
                       "JOIN KLIJENT k ON r.idKlijenta = k.idKlijenta " +
                       "JOIN NACIN_PLACANJA n ON r.NACIN_PLACANJA_idNacinaPlacanja = n.idNacinaPlacanja " +
                       "JOIN POSLOVNICA p ON r.POSLOVNICA_idPoslovnice = p.idPoslovnice " +
                       "JOIN ARANZMAN a ON r.idAranzmana = a.idAranzmana";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rezervacija rezervacija = new Rezervacija();
                rezervacija.setIdRezervacije(rs.getInt("idRezervacije"));
                rezervacija.setDatumRezervacije(rs.getDate("datumRezervacije").toLocalDate());
                rezervacija.setUkupnaCijena(rs.getBigDecimal("ukupnaCijena"));
                rezervacija.setKlijentId(rs.getInt("idKlijenta"));
                rezervacija.setAranzmanId(rs.getInt("idAranzmana"));
                rezervacija.setZaposleniId(rs.getInt("idZaposlenog"));
                rezervacija.setNacinPlacanjaId(rs.getInt("NACIN_PLACANJA_idNacinaPlacanja"));
                rezervacija.setPoslovnicaId(rs.getInt("POSLOVNICA_idPoslovnice"));

                rezervacije.add(rezervacija);
            }
        }
        return rezervacije;
    }
    
    public Klijent getKlijentById(int klijentId) throws SQLException {
        String query = "SELECT * FROM KLIJENT WHERE idKlijenta = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, klijentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Klijent klijent = new Klijent();
                    klijent.setIdKlijenta(rs.getInt("idKlijenta"));
                    klijent.setIme(rs.getString("ime"));
                    klijent.setPrezime(rs.getString("prezime"));
                    // Set other fields if needed
                    return klijent;
                }
            }
        }
        return null; // Return null if no client is found
    }

    
    public NacinPlacanja getNacinPlacanjaById(int nacinPlacanjaId) throws SQLException {
        String query = "SELECT * FROM NACIN_PLACANJA WHERE idNacinaPlacanja = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nacinPlacanjaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    NacinPlacanja nacinPlacanja = new NacinPlacanja();
                    nacinPlacanja.setIdNacinaPlacanja(rs.getInt("idNacinaPlacanja"));
                    nacinPlacanja.setNazivNacinaPlacanja(rs.getString("nazivNacinaPlacanja"));
                    // Set other fields if needed
                    return nacinPlacanja;
                }
            }
        }
        return null; // Return null if no payment method is found
    }

    public Aranzman getAranzmanById(int id) throws SQLException {
        String query = "SELECT * FROM ARANZMAN WHERE idAranzmana = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Aranzman aranzman = new Aranzman();
                aranzman.setIdAranzmana(rs.getInt("idAranzmana"));
                aranzman.setNazivDestinacije(rs.getString("nazivDestinacije"));
                aranzman.setCijena(rs.getBigDecimal("cijena"));
                aranzman.setTermin(rs.getDate("termin").toLocalDate());
                aranzman.setTrajanje(rs.getString("trajanje"));
                aranzman.setVodicId(rs.getInt("VODIC_idVodica"));
                aranzman.setOsiguranjeId(rs.getInt("OSIGURANJE_idOsiguranja"));
                return aranzman;
            }
        }
        return null;
    }
    
    public Poslovnica getPoslovnicaById(int poslovnicaId) throws SQLException {
        String query = "SELECT * FROM POSLOVNICA WHERE idPoslovnice = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, poslovnicaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Poslovnica poslovnica = new Poslovnica();
                    poslovnica.setIdPoslovnice(rs.getInt("idPoslovnice"));
                    poslovnica.setAdresa(rs.getString("adresa"));
                    // Set other fields if needed
                    return poslovnica;
                }
            }
        }
        return null; // Return null if no business is found
    }



    // UPDATE
    public void updateRezervacija(Rezervacija rezervacija) throws SQLException {
        String query = "UPDATE REZERVACIJA SET aranzmanId = ?, klijentId = ?, datumRezervacije = ?, brojOsoba = ?, ukupnaCijena = ?, nacinPlacanjaId = ?, zaposleniId = ?, poslovnicaId = ? WHERE idRezervacije = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, rezervacija.getAranzmanId());
            stmt.setInt(2, rezervacija.getKlijentId());
            stmt.setDate(3, Date.valueOf(rezervacija.getDatumRezervacije()));
            
            stmt.setBigDecimal(4, rezervacija.getUkupnaCijena());
            stmt.setInt(5, rezervacija.getNacinPlacanjaId());
            stmt.setInt(6, rezervacija.getZaposleniId());
            stmt.setInt(7, rezervacija.getPoslovnicaId());
           
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deleteRezervacija(int idRezervacije) throws SQLException {
        String query = "DELETE FROM REZERVACIJA WHERE idRezervacije = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idRezervacije);
            stmt.executeUpdate();
        }
    }



    public List<Klijent> getAllKlijenti() throws SQLException {
        List<Klijent> klijenti = new ArrayList<>();
        String sql = "SELECT * FROM Klijent";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                

               

                Klijent klijent = new Klijent();
                klijent.setIdKlijenta(rs.getInt("idKlijenta"));
                klijent.setIme(rs.getString("ime"));
                klijent.setPrezime(rs.getString("prezime"));
                klijent.setBrojTelefona(rs.getString("brojTelefona"));
                klijent.setEmail(rs.getString("email"));
                klijent.setDatumRodjenja(rs.getDate("datumRodjenja").toLocalDate());
                
                klijenti.add(klijent);
                
            }
        }

        return klijenti;
    }

    
    public List<Poslovnica> getAllPoslovnice() throws SQLException {
        List<Poslovnica> poslovnice = new ArrayList<>();
        String sql = "SELECT * FROM Poslovnica";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
               
                Poslovnica poslovnica = new Poslovnica();
                

                poslovnica.setIdPoslovnice(rs.getInt("idPoslovnice"));
                poslovnica.setBrojTelefona(rs.getString("brojTelefona"));
                poslovnica.setAdresa(rs.getString("adresa"));
                
                poslovnice.add(poslovnica);
            }
        }

        return poslovnice;
    }
    
    public List<NacinPlacanja> getAllNaciniPlacanja() throws SQLException {
        List<NacinPlacanja> naciniPlacanja = new ArrayList<>();
        String sql = "SELECT * FROM NACIN_PLACANJA"; // Pretpostavljam da je tabela u bazi podataka NacinPlacanja

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                NacinPlacanja nacinPlacanja = new NacinPlacanja();
                nacinPlacanja.setIdNacinaPlacanja(rs.getInt("idNacinaPlacanja"));
                nacinPlacanja.setNazivNacinaPlacanja(rs.getString("nazivNacinaPlacanja"));
                naciniPlacanja.add(nacinPlacanja);
            }
        }

        return naciniPlacanja;
    }
    
 // Method to fetch all employees
    public List<Zaposleni> getAllZaposleni() throws SQLException {
        List<Zaposleni> zaposleniList = new ArrayList<>();
        String query = "SELECT * FROM Zaposleni"; // Adjust the query based on your table structure

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Zaposleni zaposleni = new Zaposleni();
                zaposleni.setIdZaposlenog(rs.getInt("idZaposlenog"));
                zaposleni.setIme(rs.getString("ime"));
                zaposleni.setPrezime(rs.getString("prezime"));
                zaposleni.setPozicija(rs.getString("pozicija"));
                // Add other fields as necessary
                zaposleniList.add(zaposleni);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return zaposleniList;
    }


    private Rezervacija extractRezervacijaFromResultSet(ResultSet rs) throws SQLException {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setIdRezervacije(rs.getInt("idRezervacije"));
        rezervacija.setAranzmanId(rs.getInt("aranzmanId"));
        rezervacija.setKlijentId(rs.getInt("klijentId"));
        rezervacija.setDatumRezervacije(rs.getDate("datumRezervacije").toLocalDate());
        rezervacija.setUkupnaCijena(rs.getBigDecimal("ukupnaCijena"));
        rezervacija.setNacinPlacanjaId(rs.getInt("nacinPlacanjaId"));
        rezervacija.setZaposleniId(rs.getInt("zaposleniId"));
        rezervacija.setPoslovnicaId(rs.getInt("poslovnicaId"));
       
        return rezervacija;
    }
}

