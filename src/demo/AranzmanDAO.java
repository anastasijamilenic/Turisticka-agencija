package demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AranzmanDAO {

	public int createAranzman(Aranzman aranzman) throws SQLException {
	    String query = "INSERT INTO ARANZMAN (nazivDestinacije, cijena, termin, trajanje, VODIC_idVodica, OSIGURANJE_idOsiguranja) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

	        // Log parameter values
	        System.out.println("Naziv Destinacije: " + aranzman.getNazivDestinacije());
	        System.out.println("Cijena: " + aranzman.getCijena());
	        System.out.println("Termin: " + aranzman.getTermin());
	        System.out.println("Trajanje: " + aranzman.getTrajanje());
	        System.out.println("Vodic ID: " + aranzman.getVodicId());
	        System.out.println("Osiguranje ID: " + aranzman.getOsiguranjeId());
	        System.out.println(Date.valueOf(aranzman.getTermin()));

	        // Set parameters
	        stmt.setString(1, aranzman.getNazivDestinacije());
	        stmt.setBigDecimal(2, aranzman.getCijena());
	     
            stmt.setDate(3, java.sql.Date.valueOf(aranzman.getTermin())); // Convert LocalDate to java.sql.Date
            
	        stmt.setString(4, aranzman.getTrajanje());
	        stmt.setInt(5, aranzman.getVodicId());
	        stmt.setInt(6, aranzman.getOsiguranjeId());

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating aranžman failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1); // Return the generated ID
	            } else {
	                throw new SQLException("Creating aranžman failed, no ID obtained.");
	            }
	        }
	    }
	}
	
	public int deleteAranzmanByDestinacija(String nazivDestinacije) throws SQLException {
	    String query = "DELETE FROM ARANZMAN WHERE nazivDestinacije = ?";
	    try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, nazivDestinacije);
	        return stmt.executeUpdate();
	    }
	}

	
	public List<Aranzman> getAranzmaniByDestination(String destination) throws SQLException {
	    String query = "SELECT * FROM ARANZMAN WHERE nazivDestinacije LIKE ?";
	    List<Aranzman> aranzmani = new ArrayList<>();
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, "%" + destination + "%");
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Aranzman aranzman = new Aranzman();
	                aranzman.setIdAranzmana(rs.getInt("idAranzmana"));
	                aranzman.setNazivDestinacije(rs.getString("nazivDestinacije"));
	                aranzman.setCijena(rs.getBigDecimal("cijena"));
	                aranzman.setTermin(rs.getDate("termin").toLocalDate());
	                aranzman.setTrajanje(rs.getString("trajanje"));
	                aranzman.setVodicId(rs.getInt("VODIC_idVodica"));
	                aranzman.setOsiguranjeId(rs.getInt("OSIGURANJE_idOsiguranja"));
	                aranzmani.add(aranzman);
	            }
	        }
	    }
	    return aranzmani;
	}



	public int getIdForNewAranzman(Aranzman aranzman) throws SQLException {
	    String query = "SELECT idAranzmana FROM ARANZMAN WHERE nazivDestinacije = ? AND cijena = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, aranzman.getNazivDestinacije());
	        stmt.setBigDecimal(2, aranzman.getCijena());
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("idAranzmana");
	            } else {
	                throw new SQLException("No ID found for the new aranžman.");
	            }
	        }
	    }
	}


    // Metoda za dobijanje aranžmana po ID-u
    public Aranzman getAranzman(int id) throws SQLException {
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

    // Metoda za dobijanje svih aranžmana
    public List<Aranzman> getAllAranzmani() throws SQLException {
        List<Aranzman> aranzmani = new ArrayList<>();
        String query = "SELECT * FROM ARANZMAN";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Aranzman aranzman = new Aranzman();
                aranzman.setIdAranzmana(rs.getInt("idAranzmana"));
                aranzman.setNazivDestinacije(rs.getString("nazivDestinacije"));
                aranzman.setCijena(rs.getBigDecimal("cijena"));
                aranzman.setTermin(rs.getDate("termin").toLocalDate());
                aranzman.setTrajanje(rs.getString("trajanje"));
                aranzman.setVodicId(rs.getInt("VODIC_idVodica"));
                aranzman.setOsiguranjeId(rs.getInt("OSIGURANJE_idOsiguranja"));
                aranzmani.add(aranzman);
            }
        }
        return aranzmani;
    }

    // Metoda za filtriranje aranžmana po destinaciji
    public List<Aranzman> getAranzmaniByDestinacija(String destinacija) throws SQLException {
        List<Aranzman> aranzmani = new ArrayList<>();
        String query = "SELECT * FROM ARANZMAN WHERE nazivDestinacije = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, destinacija);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Aranzman aranzman = new Aranzman();
                aranzman.setIdAranzmana(rs.getInt("idAranzmana"));
                aranzman.setNazivDestinacije(rs.getString("nazivDestinacije"));
                aranzman.setCijena(rs.getBigDecimal("cijena"));
                aranzman.setTermin(rs.getDate("termin").toLocalDate());
                aranzman.setTrajanje(rs.getString("trajanje"));
                aranzman.setVodicId(rs.getInt("VODIC_idVodica"));
                aranzman.setOsiguranjeId(rs.getInt("OSIGURANJE_idOsiguranja"));
                aranzmani.add(aranzman);
            }
        }
        return aranzmani;
    }

    // Metode za dodavanje usluga i smeštaja (ostaju nepromenjene)
    public void addUslugaToAranzman(int aranzmanId, int uslugaId) throws SQLException {
        String query = "INSERT INTO Aranzman_Usluga (aranzmanId, uslugaId) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aranzmanId);
            stmt.setInt(2, uslugaId);
            stmt.executeUpdate();
        }
    }

    private AranzmanSmjestajDAO aranzmanSmjestajDAO = new AranzmanSmjestajDAO();

    public void addSmjestajToAranzman(int aranzmanId, int smjestajId) throws SQLException {
        aranzmanSmjestajDAO.addSmjestajToAranzman(aranzmanId, smjestajId);
    }

    public List<Smjestaj> getSmjestajiForAranzman(int aranzmanId) throws SQLException {
        return aranzmanSmjestajDAO.getSmjestajiForAranzman(aranzmanId);
    }
    
    public List<Kategorija> getAllKategorija() throws SQLException {
        List<Kategorija> kategorije = new ArrayList<>();
        
        String query = "SELECT * FROM KATEGORIJA_ARANZMANA"; // Assuming your table name is Kategorija
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int idKategorije = resultSet.getInt("idKategorije");
                String nazivKategorije = resultSet.getString("nazivKategorije");

                Kategorija kategorija = new Kategorija();
                kategorija.setIdKategorije(idKategorije);
                kategorija.setNazivKategorije(nazivKategorije);

                kategorije.add(kategorija);
            }
        }

        return kategorije;
    }
    
    public List<Vodic> getAllVodici() throws SQLException {
        List<Vodic> vodici = new ArrayList<>();
        String query = "SELECT * FROM VODIC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vodic vodic = new Vodic();
                vodic.setIdVodica(rs.getInt("idVodica"));
                vodic.setIme(rs.getString("ime"));
                vodic.setPrezime(rs.getString("prezime"));
                vodici.add(vodic);
            }
        }
        return vodici;
    }


        // Metoda za dohvat vodiča po ID-u
        public Vodic getVodicById(int vodicId) throws SQLException {
            Vodic vodic = null;
            String query = "SELECT * FROM Vodic WHERE idVodica = ?";
            
            try (Connection conn = DatabaseConnection.getConnection(); 
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, vodicId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        vodic = new Vodic();
                        vodic.setIdVodica(rs.getInt("idVodica"));
                        vodic.setIme(rs.getString("ime"));
                        vodic.setPrezime(rs.getString("prezime"));
                    }
                }
            } catch (SQLException e) {
                throw new SQLException("Greška prilikom dohvatanja vodiča: " + e.getMessage());
            }
            return vodic;
        }

        // Metoda za dohvat osiguranja po ID-u
        public Osiguranje getOsiguranjeById(int osiguranjeId) throws SQLException {
            Osiguranje osiguranje = null;
            String query = "SELECT * FROM Osiguranje WHERE idOsiguranja = ?";
            
            try (Connection conn = DatabaseConnection.getConnection(); 
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, osiguranjeId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        osiguranje = new Osiguranje();
                        osiguranje.setIdOsiguranja(rs.getInt("idOsiguranja"));
                        osiguranje.setTipOsiguranja(rs.getString("tipOsiguranja"));
                    }
                }
            } catch (SQLException e) {
                throw new SQLException("Greška prilikom dohvatanja osiguranja: " + e.getMessage());
            }
            return osiguranje;
        }
    


    // Dohvati osiguranje prema tipu
    public List<Osiguranje> getAllOsiguranja() throws SQLException {
        List<Osiguranje> osiguranja = new ArrayList<>();
        String query = "SELECT * FROM OSIGURANJE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Osiguranje osiguranje = new Osiguranje();
                osiguranje.setIdOsiguranja(rs.getInt("idOsiguranja"));
                osiguranje.setTipOsiguranja(rs.getString("tipOsiguranja"));
                osiguranja.add(osiguranje);
            }
        }
        return osiguranja;
    }
    
    // Dohvati ID vodiča prema imenu i prezimenu
    public int getVodicIdByName(String ime, String prezime) throws SQLException {
        String query = "SELECT idVodica FROM VODIC WHERE ime = ? AND prezime = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ime);
            stmt.setString(2, prezime);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idVodica");
            }
        }
        return -1;
    }


    
 

    // Dohvati ID osiguranja prema tipu
    public int getOsiguranjeIdByTip(String tipOsiguranja) throws SQLException {
        String query = "SELECT idOsiguranja FROM OSIGURANJE WHERE tipOsiguranja = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tipOsiguranja);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idOsiguranja");
            }
        }
        return -1;
    }
}

