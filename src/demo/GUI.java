package demo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class GUI extends JFrame {

    private JPanel mainPanel;
    private JPanel dynamicPanel;
    private JPanel homePanel;

    public GUI() {
        // Set up the main frame
        setTitle("Travel Agency System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Create the main panel with a background image
        mainPanel = new BackgroundPanel("slika10.jpg");
        mainPanel.setLayout(new BorderLayout());

        // Create the menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 5));

        // Create buttons for the new menu options
        JButton aranzmaniButton = new JButton("Aranžmani");
        JButton rezervacijeButton = new JButton("Rezervacije");
        JButton smjestajButton = new JButton("Smještaj");
        JButton prevozButton = new JButton("Prevoz");
        JButton uslugeButton = new JButton("Usluge");
        
        Font boldFont = new Font("Arial", Font.BOLD, 14); // Adjust the size as needed
        aranzmaniButton.setFont(boldFont);
        aranzmaniButton.setForeground(Color.WHITE);
        rezervacijeButton.setFont(boldFont);
        rezervacijeButton.setForeground(Color.WHITE);
        smjestajButton.setFont(boldFont);
        smjestajButton.setForeground(Color.WHITE);
        prevozButton.setFont(boldFont);
        prevozButton.setForeground(Color.WHITE);
        uslugeButton.setFont(boldFont);
        uslugeButton.setForeground(Color.WHITE);
        
        aranzmaniButton.setBackground(new Color(255,229,204));
        rezervacijeButton.setBackground(new Color(255,229,204));
        smjestajButton.setBackground(new Color(255,229,204));
        prevozButton.setBackground(new Color(255,229,204));
        uslugeButton.setBackground(new Color(255,229,204));

        // Add buttons to the menu panel
        menuPanel.add(aranzmaniButton);
        menuPanel.add(rezervacijeButton);
        menuPanel.add(smjestajButton);
        menuPanel.add(prevozButton);
        menuPanel.add(uslugeButton);

        // Add the menu panel to the top of the main panel
        mainPanel.add(menuPanel, BorderLayout.NORTH);

        // Create the dynamic panel where content will be displayed
        dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new CardLayout());
        mainPanel.add(dynamicPanel, BorderLayout.CENTER);

        // Create the home panel and add it to the dynamic panel
        createHomePanel();
        dynamicPanel.add(homePanel, "Home");

        // Add action listeners to the main menu buttons
        aranzmaniButton.addActionListener(e -> {
			try {
				showAranzmanCrudPanel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        rezervacijeButton.addActionListener(e -> {
			try {
				showRezervacijaCrudPanel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        smjestajButton.addActionListener(e -> {
			try {
				showSmjestajCrudPanel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        prevozButton.addActionListener(e -> {
			try {
				showPrevozCrudPanel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}); 
        uslugeButton.addActionListener(e -> {
			try {
				showUslugaCrudPanel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        // Add the main panel to the frame
        add(mainPanel);

        // Show the home panel by default
        try {
			showAranzmanCrudPanel();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    private void createHomePanel() {
        //homePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        //homePanel.add(new JLabel("Odaberite opciju:", JLabel.CENTER));
    	
    	    // Create the homePanel with GridLayout
    	    homePanel = new JPanel(new GridLayout(3, 2, 10, 10));
    	    
    	    // Add a label with the instruction text
    	    JLabel instructionLabel = new JLabel("Dobrodosli! Odaberite opciju:", JLabel.CENTER);
    	    
    	    // Set font to bold and color to white
    	    Font boldFont = new Font("Arial", Font.BOLD, 16); // Adjust the size as needed
    	    instructionLabel.setFont(boldFont);
    	    instructionLabel.setForeground(Color.WHITE); // Set the text color to white
    	    
    	    // Add the instruction label to the panel
    	    homePanel.add(instructionLabel);
    	    
    	    // Load and add an image
    	    ImageIcon imageIcon = new ImageIcon(getClass().getResource("slika19.jpg")); // Replace "your-image.jpg" with the actual image file name
    	    JLabel imageLabel = new JLabel(imageIcon);
    	    
    	    // Add the image label to the panel
    	    homePanel.add(imageLabel);
    	    
    	    // Optionally, add more components or configure the panel as needed
    	}

    

    private void showHomePanel() {
        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Home");
    }

    private void showAranzmaniSubMenu() {
        JPanel aranzmaniPanel = new JPanel(new GridLayout(3, 1));
        JButton vidisveButton = new JButton("Vidi sve aranžmane");
        JButton filtrirajButton = new JButton("Filtriraj po destinaciji");
        JButton dodajNoviButton = new JButton("Dodaj novi aranžman");

        // Add buttons to the panel
        aranzmaniPanel.add(vidisveButton);
        aranzmaniPanel.add(filtrirajButton);
        aranzmaniPanel.add(dodajNoviButton);

        // Add the panel to the dynamic panel
        dynamicPanel.add(aranzmaniPanel, "Aranžmani");

        // Show the aranzmani panel
        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Aranžmani");

        // Add action listeners for sub-menu options
        vidisveButton.addActionListener(e -> showSubMenuContent("Vidi sve aranžmane"));
        filtrirajButton.addActionListener(e -> showSubMenuContent("Filtriraj po destinaciji"));
        dodajNoviButton.addActionListener(e -> showSubMenuContent("Dodaj novi aranžman"));
    }

    private void showRezervacijeSubMenu() {
        JPanel rezervacijePanel = new JPanel(new GridLayout(3, 1));
        JButton vidisveButton = new JButton("Vidi sve rezervacije");
        JButton filtrirajButton = new JButton("Filtriraj po destinaciji");
        JButton dodajNovuButton = new JButton("Dodaj novu rezervaciju");

        // Add buttons to the panel
        rezervacijePanel.add(vidisveButton);
        rezervacijePanel.add(filtrirajButton);
        rezervacijePanel.add(dodajNovuButton);

        // Add the panel to the dynamic panel
        dynamicPanel.add(rezervacijePanel, "Rezervacije");

        // Show the rezervacije panel
        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Rezervacije");

        // Add action listeners for sub-menu options
        vidisveButton.addActionListener(e -> showSubMenuContent("Vidi sve rezervacije"));
        filtrirajButton.addActionListener(e -> showSubMenuContent("Filtriraj po destinaciji"));
        dodajNovuButton.addActionListener(e -> showSubMenuContent("Dodaj novu rezervaciju"));
    }

    private void showCrudPanel(String entityName) {
        JPanel crudPanel = new JPanel(new GridLayout(4, 1));
        JButton viewButton = new JButton("Vidi sve " + entityName.toLowerCase());
        JButton addButton = new JButton("Dodaj novi " + entityName.toLowerCase());
        JButton updateButton = new JButton("Ažuriraj " + entityName.toLowerCase());
        JButton deleteButton = new JButton("Obriši " + entityName.toLowerCase());

        // Add buttons to the panel
        crudPanel.add(viewButton);
        crudPanel.add(addButton);
        crudPanel.add(updateButton);
        crudPanel.add(deleteButton);

        // Add the panel to the dynamic panel
        dynamicPanel.add(crudPanel, entityName);

        // Show the CRUD panel
        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, entityName);

        // Add action listeners for CRUD options
        viewButton.addActionListener(e -> showSubMenuContent("Vidi sve " + entityName.toLowerCase()));
        addButton.addActionListener(e -> showSubMenuContent("Dodaj novi " + entityName.toLowerCase()));
        updateButton.addActionListener(e -> showSubMenuContent("Ažuriraj " + entityName.toLowerCase()));
        deleteButton.addActionListener(e -> showSubMenuContent("Obriši " + entityName.toLowerCase()));
    }

    private void showSubMenuContent(String content) {
        JPanel contentPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(content, JLabel.CENTER);
        contentPanel.add(label, BorderLayout.CENTER);

        // Clear dynamic panel and show new content
        dynamicPanel.removeAll();
        dynamicPanel.add(contentPanel);
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }
    
    private void showUslugaCrudPanel() throws IOException, SQLException {
        JPanel uslugaPanel = new JPanel(new GridLayout(4, 1));

        // Load the full image
        BufferedImage fullImage = ImageIO.read(getClass().getResource("slika25.jpg")); // Use your image path here
        int imageHeight = fullImage.getHeight();
        int imageWidth = fullImage.getWidth();

        // Calculate the height of each part
        int partHeight = imageHeight / 4;

        // Extract the image parts
        Image topPart = fullImage.getSubimage(0, 0, imageWidth, partHeight);
        Image middlePart = fullImage.getSubimage(0, partHeight, imageWidth, partHeight);
        Image bottomPart = fullImage.getSubimage(0, partHeight * 2, imageWidth, partHeight);
        Image bottomPart2 = fullImage.getSubimage(0, partHeight * 3, imageWidth, partHeight);

        // Create buttons
        JButton viewButton = new JButton("Vidi sve usluge");
        JButton addButton = new JButton("Dodaj novu uslugu");
        JButton updateButton = new JButton("Ažuriraj uslugu");
        JButton deleteButton = new JButton("Obriši uslugu");

        // Set font to bold and color to white
        Font boldFont = new Font("Arial", Font.BOLD, 14); // Adjust size as needed
        viewButton.setFont(boldFont);
        viewButton.setForeground(Color.WHITE);
        addButton.setFont(boldFont);
        addButton.setForeground(Color.WHITE);
        updateButton.setFont(boldFont);
        updateButton.setForeground(Color.WHITE);
        deleteButton.setFont(boldFont);
        deleteButton.setForeground(Color.WHITE);

        // Add component listener to get button width after panel layout
        uslugaPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int buttonWidth = viewButton.getWidth();

                if (buttonWidth > 0) { // Check if the button width is valid
                    // Resize the images to match the button width
                    Image img1 = topPart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img2 = middlePart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img3 = bottomPart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img4 = bottomPart2.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);

                    // Set the resized images as icons
                    viewButton.setIcon(new ImageIcon(img1));
                    addButton.setIcon(new ImageIcon(img2));
                    updateButton.setIcon(new ImageIcon(img3));
                    deleteButton.setIcon(new ImageIcon(img4));

                    // Set the text to be centered with the icon
                    viewButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    viewButton.setVerticalTextPosition(SwingConstants.CENTER);

                    addButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    addButton.setVerticalTextPosition(SwingConstants.CENTER);

                    updateButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    updateButton.setVerticalTextPosition(SwingConstants.CENTER);

                    deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    deleteButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
            }
        });

        // Set up action listeners
        viewButton.addActionListener(e -> showAllUsluge());
        addButton.addActionListener(e -> showCreateUslugaForm());
        updateButton.addActionListener(e -> showUpdateUslugaForm());
        deleteButton.addActionListener(e -> showDeleteUslugaForm());

        // Add buttons to the panel
        uslugaPanel.add(viewButton);
        uslugaPanel.add(addButton);
        uslugaPanel.add(updateButton);
        uslugaPanel.add(deleteButton);

        // Add the panel to dynamicPanel and show it
        dynamicPanel.add(uslugaPanel, "Usluga");

        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Usluga");
    }


    private void showAllPrevoz() {
        JPanel prevozPanel = new JPanel(new BorderLayout());
        prevozPanel.setBackground(new Color(255, 255, 204)); // Postavlja pozadinsku boju panela

        PrevozDAO prevozDAO = new PrevozDAO();
        List<Prevoz> prevozi = new ArrayList<>();
        try {
            prevozi = prevozDAO.getAllPrevoz();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greška prilikom dohvatanja prevoza: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] columnNames = {"ID Prevoza", "Tip Prevoza", "Naziv Kompanije", "Cijena"};
        Object[][] data = new Object[prevozi.size()][columnNames.length];
        for (int i = 0; i < prevozi.size(); i++) {
            Prevoz prevoz = prevozi.get(i);
            data[i][0] = prevoz.getIdPrevoza();
            data[i][1] = prevoz.getTipPrevoza();
            data[i][2] = prevoz.getNazivKompanije();
            data[i][3] = prevoz.getCijena();
        }

        JTable prevozTable = new JTable(data, columnNames);
        prevozTable.setBackground(new Color(204,255,204)); // Postavlja pozadinsku boju tabele
        prevozTable.setOpaque(true); // Osigurava da je pozadina tabele vidljiva
        
        
        // Oboji zaglavlja kolona
        JTableHeader tableHeader = prevozTable.getTableHeader();
        tableHeader.setBackground(new Color(204,255,204)); // Pozadinska boja zaglavlja kolona
        tableHeader.setForeground(Color.BLACK); // Boja teksta zaglavlja kolona
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Stil i veličina fonta za zaglavlje


        JScrollPane scrollPane = new JScrollPane(prevozTable);
        scrollPane.getViewport().setBackground(new Color(204,255,204)); // Postavlja pozadinsku boju skrola

        prevozPanel.add(scrollPane, BorderLayout.CENTER);

        dynamicPanel.removeAll();
        dynamicPanel.add(prevozPanel, "Prevoz");
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
        dynamicPanel.setBackground(new Color(204,255,204)); // Postavlja pozadinsku boju dinamičkog panela
    }

    private void showCreatePrevozForm() {
        JFrame frame = new JFrame("Kreiraj Prevoz");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255,229,204));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label and text field for ID Prevoza
        /*JLabel idLabel = new JLabel("ID Prevoza:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(idLabel, gbc);

        JTextField idField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(idField, gbc);*/

        // Label and text field for Tip Prevoza
        JLabel tipLabel = new JLabel("Tip Prevoza:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(tipLabel, gbc);

        JTextField tipField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(tipField, gbc);

        // Label and text field for Naziv Kompanije
        JLabel kompanijaLabel = new JLabel("Naziv Kompanije:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(kompanijaLabel, gbc);

        JTextField kompanijaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(kompanijaField, gbc);

        // Label and text field for Cijena
        JLabel cijenaLabel = new JLabel("Cijena:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(cijenaLabel, gbc);

        JTextField cijenaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cijenaField, gbc);

        // Button to submit the form
        JButton saveButton = new JButton("Dodaj Prevoz");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(saveButton, gbc);

        saveButton.addActionListener(e -> {
            try {
                Prevoz prevoz = new Prevoz();
                //prevoz.setIdPrevoza(Integer.parseInt(idField.getText()));
                prevoz.setTipPrevoza(tipField.getText());
                prevoz.setNazivKompanije(kompanijaField.getText());
                prevoz.setCijena(new BigDecimal(cijenaField.getText()));

                PrevozDAO prevozDAO = new PrevozDAO();
                prevozDAO.createPrevoz(prevoz);

                JOptionPane.showMessageDialog(frame, "Prevoz uspješno dodat.");
                frame.dispose(); // Close the frame after successful addition
                showAllPrevoz();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Greška: Unesite validan broj za ID Prevoza i Cijenu.", "Greška", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Greška prilikom dodavanja prevoza: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private void showUpdatePrevozForm() {
        JFrame frame = new JFrame("Ažuriraj Prevoz");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(204,229,255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // ComboBox to select existing Prevoz by type
        JLabel selectLabel = new JLabel("Odaberi Prevoz:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(selectLabel, gbc);

        JComboBox<Prevoz> prevozComboBox = new JComboBox<>();
        try {
            PrevozDAO prevozDAO = new PrevozDAO();
            List<Prevoz> prevozi = prevozDAO.getAllPrevoz();
            for (Prevoz prevoz : prevozi) {
                prevozComboBox.addItem(prevoz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(prevozComboBox, gbc);

        // Tip Prevoza field
        JLabel tipLabel = new JLabel("Tip Prevoza:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(tipLabel, gbc);

        JTextField tipField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(tipField, gbc);

        // Naziv Kompanije field
        JLabel kompanijaLabel = new JLabel("Naziv Kompanije:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(kompanijaLabel, gbc);

        JTextField kompanijaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(kompanijaField, gbc);

        // Cijena field
        JLabel cijenaLabel = new JLabel("Cijena:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(cijenaLabel, gbc);

        JTextField cijenaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cijenaField, gbc);

        // Load selected Prevoz details
        prevozComboBox.addActionListener(e -> {
            Prevoz selectedPrevoz = (Prevoz) prevozComboBox.getSelectedItem();
            if (selectedPrevoz != null) {
                tipField.setText(selectedPrevoz.getTipPrevoza());
                kompanijaField.setText(selectedPrevoz.getNazivKompanije());
                cijenaField.setText(selectedPrevoz.getCijena().toString());
            }
        });

        // Update button
        JButton updateButton = new JButton("Ažuriraj Prevoz");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(updateButton, gbc);

        updateButton.addActionListener(e -> {
            try {
                Prevoz selectedPrevoz = (Prevoz) prevozComboBox.getSelectedItem();
                if (selectedPrevoz != null) {
                    selectedPrevoz.setTipPrevoza(tipField.getText());
                    selectedPrevoz.setNazivKompanije(kompanijaField.getText());
                    selectedPrevoz.setCijena(new BigDecimal(cijenaField.getText()));

                    PrevozDAO prevozDAO = new PrevozDAO();
                    prevozDAO.updatePrevoz(selectedPrevoz);

                    JOptionPane.showMessageDialog(frame, "Prevoz uspješno ažuriran.");
                    frame.dispose();
                    showAllPrevoz(); // Refresh the list
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Greška prilikom ažuriranja prevoza: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private void showDeletePrevozForm() {
        JFrame frame = new JFrame("Obriši Prevoz");
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(229,204,255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Labela za JComboBox
        JLabel prevozLabel = new JLabel("Odaberite prevoz za brisanje:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(prevozLabel, gbc);

        // JComboBox za odabir tipa prevoza i naziva kompanije
        JComboBox<Prevoz> prevozComboBox = new JComboBox<>();
        gbc.gridx = 1;
        panel.add(prevozComboBox, gbc);

        // Popuni JComboBox sa svim prevozima
        try {
            PrevozDAO prevozDAO = new PrevozDAO();
            List<Prevoz> prevozi = prevozDAO.getAllPrevoz();
            for (Prevoz prevoz : prevozi) {
                prevozComboBox.addItem(prevoz);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Greška prilikom preuzimanja prevoza: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }

        // Delete button
        JButton deleteButton = new JButton("Obriši Prevoz");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(deleteButton, gbc);

        deleteButton.addActionListener(e -> {
            try {
                Prevoz selectedPrevoz = (Prevoz) prevozComboBox.getSelectedItem();
                if (selectedPrevoz != null) {
                    PrevozDAO prevozDAO = new PrevozDAO();
                    prevozDAO.deletePrevozByTipAndKompanija(selectedPrevoz.getTipPrevoza(), selectedPrevoz.getNazivKompanije());
                    JOptionPane.showMessageDialog(frame, "Prevoz uspješno obrisan.");
                    frame.dispose();
                    showAllPrevoz(); // Pretpostavljam da je ovo metoda koja osvežava prikaz svih prevoza
                } else {
                    JOptionPane.showMessageDialog(frame, "Niste izabrali prevoz.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Greška prilikom brisanja prevoza: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }


    private void showPrevozCrudPanel() throws IOException, SQLException {
        JPanel prevozPanel = new JPanel(new GridLayout(4, 1));

        // Load the full image
        BufferedImage fullImage = ImageIO.read(getClass().getResource("slika19.jpg")); // Use your image path here
        int imageHeight = fullImage.getHeight();
        int imageWidth = fullImage.getWidth();

        // Calculate the height of each part
        int partHeight = imageHeight / 4;

        // Extract the image parts
        Image topPart = fullImage.getSubimage(0, 0, imageWidth, partHeight);
        Image middlePart = fullImage.getSubimage(0, partHeight, imageWidth, partHeight);
        Image bottomPart = fullImage.getSubimage(0, partHeight * 2, imageWidth, partHeight);
        Image bottomPart2 = fullImage.getSubimage(0, partHeight * 3, imageWidth, partHeight);

        // Create buttons
        JButton viewButton = new JButton("Vidi sve prevoze");
        JButton addButton = new JButton("Dodaj novi prevoz");
        JButton updateButton = new JButton("Ažuriraj prevoz");
        JButton deleteButton = new JButton("Obriši prevoz");

        // Set font to bold and color to white
        Font boldFont = new Font("Arial", Font.BOLD, 14); // Adjust size as needed
        viewButton.setFont(boldFont);
        viewButton.setForeground(Color.WHITE);
        addButton.setFont(boldFont);
        addButton.setForeground(Color.WHITE);
        updateButton.setFont(boldFont);
        updateButton.setForeground(Color.WHITE);
        deleteButton.setFont(boldFont);
        deleteButton.setForeground(Color.WHITE);

        // Add component listener to get button width after panel layout
        prevozPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int buttonWidth = viewButton.getWidth();
                
                if (buttonWidth > 0) { // Check if the button width is valid
                    // Resize the images to match the button width
                    Image img1 = topPart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img2 = middlePart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img3 = bottomPart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img4 = bottomPart2.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);

                    // Set the resized images as icons
                    viewButton.setIcon(new ImageIcon(img1));
                    addButton.setIcon(new ImageIcon(img2));
                    updateButton.setIcon(new ImageIcon(img3));
                    deleteButton.setIcon(new ImageIcon(img4));

                    // Set the text to be centered with the icon
                    viewButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    viewButton.setVerticalTextPosition(SwingConstants.CENTER);

                    addButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    addButton.setVerticalTextPosition(SwingConstants.CENTER);

                    updateButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    updateButton.setVerticalTextPosition(SwingConstants.CENTER);

                    deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    deleteButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
            }
        });

        // Set up action listeners
        viewButton.addActionListener(e -> showAllPrevoz());
        addButton.addActionListener(e -> showCreatePrevozForm());
        updateButton.addActionListener(e -> showUpdatePrevozForm());
        deleteButton.addActionListener(e -> showDeletePrevozForm());

        // Add buttons to the panel
        prevozPanel.add(viewButton);
        prevozPanel.add(addButton);
        prevozPanel.add(updateButton);
        prevozPanel.add(deleteButton);

        // Add the panel to dynamicPanel and show it
        dynamicPanel.add(prevozPanel, "Prevoz");

        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Prevoz");
    }

    private void showAllUsluge() {
        JPanel uslugePanel = new JPanel(new BorderLayout());
        uslugePanel.setBackground(new Color(204,255,204)); // Pozadinska boja panela

        UslugaDAO uslugaDAO = new UslugaDAO();
        List<Usluga> usluge = new ArrayList<>();
        try {
            usluge = uslugaDAO.getAllUsluge();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greška prilikom dohvatanja usluga: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] columnNames = {"ID Usluge", "Tip Usluge", "Cijena"};
        Object[][] data = new Object[usluge.size()][columnNames.length];
        for (int i = 0; i < usluge.size(); i++) {
            Usluga usluga = usluge.get(i);
            data[i][0] = usluga.getIdUsluge();
            data[i][1] = usluga.getTipUsluge();
            data[i][2] = usluga.getCijena();
        }

        JTable uslugeTable = new JTable(data, columnNames);
        uslugeTable.setBackground(new Color(204,255,204)); // Pozadinska boja tabele
        uslugeTable.setOpaque(true); // Osigurava da je pozadina vidljiva

        // Oboji zaglavlja kolona
        JTableHeader tableHeader = uslugeTable.getTableHeader();
        tableHeader.setBackground(new Color(204,255,204)); // Pozadinska boja zaglavlja
        tableHeader.setForeground(Color.BLACK); // Boja teksta zaglavlja
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Stil i veličina fonta

        

        JScrollPane scrollPane = new JScrollPane(uslugeTable);
        scrollPane.getViewport().setBackground(new Color(204,255,204)); // Pozadinska boja skrola

        uslugePanel.add(scrollPane, BorderLayout.CENTER);

        dynamicPanel.removeAll();
        dynamicPanel.add(uslugePanel, "Usluge");
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }


    private void showCreateUslugaForm() {
        JFrame frame = new JFrame("Kreiraj Uslugu");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255,229,204));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label and text field for ID usluge
        /*JLabel idLabel = new JLabel("ID usluge:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(idLabel, gbc);

        JTextField idField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(idField, gbc);*/

        // Label and text field for Tip usluge
        JLabel tipLabel = new JLabel("Tip usluge:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(tipLabel, gbc);

        JTextField tipField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(tipField, gbc);

        // Label and text field for Cijena
        JLabel cijenaLabel = new JLabel("Cijena:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(cijenaLabel, gbc);

        JTextField cijenaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cijenaField, gbc);

        // Button to submit the form
        JButton saveButton = new JButton("Dodaj uslugu");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(saveButton, gbc);

        saveButton.addActionListener(e -> {
            try {
                Usluga usluga = new Usluga();
                //usluga.setIdUsluge(Integer.parseInt(idField.getText()));
                usluga.setTipUsluge(tipField.getText());
                usluga.setCijena((new BigDecimal(cijenaField.getText())));

                UslugaDAO uslugaDAO = new UslugaDAO();
                uslugaDAO.createUsluga(usluga);

                JOptionPane.showMessageDialog(frame, "Usluga uspješno dodata.");
                frame.dispose(); // Close the frame after successful addition
                showAllUsluge();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Greška: Unesite validan broj za ID usluge i Cijenu.", "Greška", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Greška prilikom dodavanja usluge: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }


    private void showUpdateUslugaForm() {
        JFrame frame = new JFrame("Ažuriraj Uslugu");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(204,229,255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // ComboBox to select existing Usluga by type
        JLabel selectLabel = new JLabel("Odaberi Uslugu:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(selectLabel, gbc);

        JComboBox<Usluga> uslugaComboBox = new JComboBox<>();
        try {
            UslugaDAO uslugaDAO = new UslugaDAO();
            List<Usluga> usluge = uslugaDAO.getAllUsluge();
            for (Usluga usluga : usluge) {
                uslugaComboBox.addItem(usluga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(uslugaComboBox, gbc);

        // Tip Usluge field
        JLabel tipLabel = new JLabel("Tip Usluge:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(tipLabel, gbc);

        JTextField tipField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(tipField, gbc);

        // Cijena field
        JLabel cijenaLabel = new JLabel("Cijena:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(cijenaLabel, gbc);

        JTextField cijenaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cijenaField, gbc);

        // Load selected Usluga details
        uslugaComboBox.addActionListener(e -> {
            Usluga selectedUsluga = (Usluga) uslugaComboBox.getSelectedItem();
            if (selectedUsluga != null) {
                tipField.setText(selectedUsluga.getTipUsluge());
                cijenaField.setText(selectedUsluga.getCijena().toString());
            }
        });

        // Update button
        JButton updateButton = new JButton("Ažuriraj Uslugu");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(updateButton, gbc);

        updateButton.addActionListener(e -> {
            try {
                Usluga selectedUsluga = (Usluga) uslugaComboBox.getSelectedItem();
                if (selectedUsluga != null) {
                    selectedUsluga.setTipUsluge(tipField.getText());
                    selectedUsluga.setCijena(new BigDecimal(cijenaField.getText()));

                    UslugaDAO uslugaDAO = new UslugaDAO();
                    uslugaDAO.updateUsluga(selectedUsluga);

                    JOptionPane.showMessageDialog(frame, "Usluga uspješno ažurirana.");
                    frame.dispose();
                    showAllUsluge(); // Refresh the list
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Greška prilikom ažuriranja usluge: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }


    

    private void showDeleteUslugaForm() {
        JFrame frame = new JFrame("Obriši Uslugu");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(229,204,255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Tip Usluge field
        JLabel tipLabel = new JLabel("Tip usluge za brisanje:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(tipLabel, gbc);

        // JComboBox za odabir usluge
        JComboBox<Usluga> uslugaComboBox = new JComboBox<>();
        gbc.gridx = 1;
        panel.add(uslugaComboBox, gbc);

        // Popuni JComboBox sa svim uslugama
        try {
            UslugaDAO uslugaDAO = new UslugaDAO();
            List<Usluga> usluge = uslugaDAO.getAllUsluge();
            for (Usluga usluga : usluge) {
                uslugaComboBox.addItem(usluga);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Greška prilikom preuzimanja usluga: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }

        // Delete button
        JButton deleteButton = new JButton("Obriši Uslugu");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(deleteButton, gbc);

        deleteButton.addActionListener(e -> {
            try {
                Usluga selectedUsluga = (Usluga) uslugaComboBox.getSelectedItem();
                if (selectedUsluga != null) {
                    UslugaDAO uslugaDAO = new UslugaDAO();
                    uslugaDAO.deleteUslugaByTip(selectedUsluga.getTipUsluge());
                    JOptionPane.showMessageDialog(frame, "Usluga uspješno obrisana.");
                    frame.dispose();
                    showAllUsluge(); // Pretpostavljam da je ovo metoda koja osvežava prikaz svih usluga
                } else {
                    JOptionPane.showMessageDialog(frame, "Niste izabrali uslugu.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Greška prilikom brisanja usluge: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }


    
    private void showSmjestajCrudPanel() throws IOException, SQLException {
        JPanel smjestajPanel = new JPanel(new GridLayout(4, 1));

        // Load the full image
        BufferedImage fullImage = ImageIO.read(getClass().getResource("slika21.jpg"));
        int imageHeight = fullImage.getHeight();
        int imageWidth = fullImage.getWidth();

        // Calculate the height of each part
        int partHeight = imageHeight / 4;

        // Extract the image parts
        Image topPart = fullImage.getSubimage(0, 0, imageWidth, partHeight);
        Image middlePart = fullImage.getSubimage(0, partHeight, imageWidth, partHeight);
        Image bottomPart = fullImage.getSubimage(0, partHeight * 2, imageWidth, partHeight);
        Image bottomPart2 = fullImage.getSubimage(0, partHeight * 3, imageWidth, partHeight);

        // Create buttons
        JButton viewButton = new JButton("Vidi sve smještaje");
        JButton addButton = new JButton("Dodaj novi smještaj");
        JButton updateButton = new JButton("Ažuriraj smještaj");
        JButton deleteButton = new JButton("Obriši smještaj");

        // Set font to bold and color to black
        Font boldFont = new Font("Arial", Font.BOLD, 14); // Adjust size as needed
        viewButton.setFont(boldFont);
        viewButton.setForeground(Color.WHITE);
        addButton.setFont(boldFont);
        addButton.setForeground(Color.WHITE);
        updateButton.setFont(boldFont);
        updateButton.setForeground(Color.WHITE);
        deleteButton.setFont(boldFont);
        deleteButton.setForeground(Color.WHITE);

        // Add component listener to get button width after panel layout
        smjestajPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int buttonWidth = viewButton.getWidth();
                
                if (buttonWidth > 0) { // Check if the button width is valid
                    // Resize the images to match the button width
                    Image img1 = topPart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img2 = middlePart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img3 = bottomPart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img4 = bottomPart2.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);

                    // Set the resized images as icons
                    viewButton.setIcon(new ImageIcon(img1));
                    addButton.setIcon(new ImageIcon(img2));
                    updateButton.setIcon(new ImageIcon(img3));
                    deleteButton.setIcon(new ImageIcon(img4));

                    // Set the text to be underneath the icon
                    viewButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    viewButton.setVerticalTextPosition(SwingConstants.CENTER);

                    addButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    addButton.setVerticalTextPosition(SwingConstants.CENTER);

                    updateButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    updateButton.setVerticalTextPosition(SwingConstants.CENTER);

                    deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    deleteButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
            }
        });

        // Set up action listeners
        viewButton.addActionListener(e -> {
            showAllSmjestaj();
        });
        addButton.addActionListener(e -> showCreateSmjestajForm());
        updateButton.addActionListener(e -> {
            showUpdateSmjestajForm();
        });
        deleteButton.addActionListener(e -> showDeleteSmjestajForm());

        // Add buttons to the panel
        smjestajPanel.add(viewButton);
        smjestajPanel.add(addButton);
        smjestajPanel.add(updateButton);
        smjestajPanel.add(deleteButton);

        // Add the panel to dynamicPanel and show it
        dynamicPanel.add(smjestajPanel, "Smjestaj");

        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Smjestaj");
    }


    private void showAllSmjestaj() {
        JPanel smjestajPanel = new JPanel(new BorderLayout());
        smjestajPanel.setBackground(new Color(204,255,204)); // Pozadinska boja panela

        SmjestajDAO smjestajDAO = new SmjestajDAO();
        List<Smjestaj> smjestaji = new ArrayList<>();
        try {
            smjestaji = smjestajDAO.getAllSmjestaji();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greška prilikom dohvatanja smještaja: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] columnNames = {"ID", "Naziv Smještaja", "Vrsta Smještaja", "Lokacija", "Broj Zvjezdica", "Pogodnosti", "Cijena"};
        Object[][] data = new Object[smjestaji.size()][columnNames.length];
        for (int i = 0; i < smjestaji.size(); i++) {
            Smjestaj smjestaj = smjestaji.get(i);
            data[i][0] = smjestaj.getId();
            data[i][1] = smjestaj.getNazivSmjestaja();
            data[i][2] = smjestaj.getVrstaSmjestaja();
            data[i][3] = smjestaj.getLokacija();
            data[i][4] = smjestaj.getBrojZvjezdica();
            data[i][5] = smjestaj.getPogodnosti();
            // Pretpostavljamo da getCijena() vraća samo numerički iznos, a valuta je odvojena
            BigDecimal cijena = smjestaj.getCijena();
            String valuta = smjestaj.getValuta(); // Dodajte ovo ako imate metodu za dohvat valute
            data[i][6] = cijena;
        }

        JTable smjestajTable = new JTable(data, columnNames);
        smjestajTable.setBackground(new Color(204,255,204)); // Pozadinska boja tabele
        smjestajTable.setOpaque(true); // Osigurava da je pozadina vidljiva

        // Stilizovanje zaglavlja kolona
        JTableHeader tableHeader = smjestajTable.getTableHeader();
        tableHeader.setBackground(new Color(204,255,204)); // Pozadinska boja zaglavlja
        tableHeader.setForeground(Color.BLACK); // Boja teksta zaglavlja
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Stil i veličina fonta

        JScrollPane scrollPane = new JScrollPane(smjestajTable);
        scrollPane.getViewport().setBackground(new Color(204,255,204)); // Pozadinska boja skrola

        smjestajPanel.add(scrollPane, BorderLayout.CENTER);

        dynamicPanel.removeAll();
        dynamicPanel.add(smjestajPanel, "Smjestaj");
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }


    private void showCreateSmjestajForm() {
        JFrame frame = new JFrame("Kreiraj Smještaj");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255,229,204));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label and text field for naziv smještaja
        JLabel nazivLabel = new JLabel("Naziv Smještaja:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nazivLabel, gbc);

        JTextField nazivField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nazivField, gbc);

        // Label and text field for vrsta smještaja
        JLabel vrstaLabel = new JLabel("Vrsta Smještaja:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(vrstaLabel, gbc);

        JTextField vrstaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(vrstaField, gbc);

        // Label and text field for lokacija
        JLabel lokacijaLabel = new JLabel("Lokacija:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lokacijaLabel, gbc);

        JTextField lokacijaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(lokacijaField, gbc);

        // Label and text field for broj zvjezdica
        JLabel brojZvjezdicaLabel = new JLabel("Broj Zvjezdica:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(brojZvjezdicaLabel, gbc);

        JTextField brojZvjezdicaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(brojZvjezdicaField, gbc);

        // Label and text field for pogodnosti
        JLabel pogodnostiLabel = new JLabel("Pogodnosti:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(pogodnostiLabel, gbc);

        JTextField pogodnostiField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(pogodnostiField, gbc);

        // Label and text field for cijena
        JLabel cijenaLabel = new JLabel("Cijena:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(cijenaLabel, gbc);

        JTextField cijenaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cijenaField, gbc);

        // Label and combo box for valuta
        JLabel valutaLabel = new JLabel("Valuta:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(valutaLabel, gbc);

        String[] valute = {"USD", "EUR", "KM", "GBP"}; // Dodajte sve valute koje želite
        JComboBox<String> valutaComboBox = new JComboBox<>(valute);
        gbc.gridx = 1;
        panel.add(valutaComboBox, gbc);

        // Button to submit the form
        JButton saveButton = new JButton("Dodaj Smještaj");
        saveButton.addActionListener(e -> {
            try {
                Smjestaj smjestaj = new Smjestaj();
                smjestaj.setNazivSmjestaja(nazivField.getText());
                smjestaj.setVrstaSmjestaja(vrstaField.getText());
                smjestaj.setLokacija(lokacijaField.getText());
                smjestaj.setBrojZvjezdica(Integer.parseInt(brojZvjezdicaField.getText()));
                smjestaj.setPogodnosti(pogodnostiField.getText());
                smjestaj.setCijena(new BigDecimal(cijenaField.getText()));
                smjestaj.setValuta((String) valutaComboBox.getSelectedItem()); // Dodano

                SmjestajDAO smjestajDAO = new SmjestajDAO();
                smjestajDAO.createSmjestaj(smjestaj);

                JOptionPane.showMessageDialog(frame, "Smještaj uspješno dodat.");
                frame.dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Greška prilikom dodavanja smještaja: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 8; // Adjusted to be in the correct position
        panel.add(saveButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }




   
    private void showUpdateSmjestajForm() {
        JFrame frame = new JFrame("Ažuriraj Smještaj");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(204,229,255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // ComboBox to select existing Smjestaj by name
        JLabel selectLabel = new JLabel("Odaberi Smještaj:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(selectLabel, gbc);

        JComboBox<Smjestaj> smjestajComboBox = new JComboBox<>();
        try {
            SmjestajDAO smjestajDAO = new SmjestajDAO();
            List<Smjestaj> smjestaji = smjestajDAO.getAllSmjestaji();
            for (Smjestaj smjestaj : smjestaji) {
                smjestajComboBox.addItem(smjestaj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(smjestajComboBox, gbc);

        // Naziv field
        JLabel nazivLabel = new JLabel("Naziv Smještaja:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nazivLabel, gbc);

        JTextField nazivField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nazivField, gbc);

        // Vrsta field
        JLabel vrstaLabel = new JLabel("Vrsta Smještaja:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(vrstaLabel, gbc);

        JTextField vrstaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(vrstaField, gbc);

        // Lokacija field
        JLabel lokacijaLabel = new JLabel("Lokacija:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lokacijaLabel, gbc);

        JTextField lokacijaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(lokacijaField, gbc);

        // Broj Zvjezdica field
        JLabel brojZvjezdicaLabel = new JLabel("Broj Zvjezdica:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(brojZvjezdicaLabel, gbc);

        JTextField brojZvjezdicaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(brojZvjezdicaField, gbc);

        // Pogodnosti field
        JLabel pogodnostiLabel = new JLabel("Pogodnosti:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(pogodnostiLabel, gbc);

        JTextField pogodnostiField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(pogodnostiField, gbc);

        // Cijena field
        JLabel cijenaLabel = new JLabel("Cijena:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(cijenaLabel, gbc);

        JTextField cijenaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cijenaField, gbc);

        // Load selected Smjestaj details
        smjestajComboBox.addActionListener(e -> {
            Smjestaj selectedSmjestaj = (Smjestaj) smjestajComboBox.getSelectedItem();
            if (selectedSmjestaj != null) {
                nazivField.setText(selectedSmjestaj.getNazivSmjestaja());
                vrstaField.setText(selectedSmjestaj.getVrstaSmjestaja());
                lokacijaField.setText(selectedSmjestaj.getLokacija());
                brojZvjezdicaField.setText(String.valueOf(selectedSmjestaj.getBrojZvjezdica()));
                pogodnostiField.setText(selectedSmjestaj.getPogodnosti());
                cijenaField.setText(selectedSmjestaj.getCijena().toString());
            }
        });

        // Update button
        JButton updateButton = new JButton("Ažuriraj Smještaj");
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(updateButton, gbc);

        updateButton.addActionListener(e -> {
            try {
                Smjestaj selectedSmjestaj = (Smjestaj) smjestajComboBox.getSelectedItem();
                if (selectedSmjestaj != null) {
                    selectedSmjestaj.setNazivSmjestaja(nazivField.getText());
                    selectedSmjestaj.setVrstaSmjestaja(vrstaField.getText());
                    selectedSmjestaj.setLokacija(lokacijaField.getText());
                    selectedSmjestaj.setBrojZvjezdica(Integer.parseInt(brojZvjezdicaField.getText()));
                    selectedSmjestaj.setPogodnosti(pogodnostiField.getText());
                    selectedSmjestaj.setCijena(new BigDecimal(cijenaField.getText()));

                    SmjestajDAO smjestajDAO = new SmjestajDAO();
                    smjestajDAO.updateSmjestaj(selectedSmjestaj);

                    JOptionPane.showMessageDialog(frame, "Smještaj uspešno ažuriran.");
                    frame.dispose();
                    showAllSmjestaj(); // Refresh the list
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Greška prilikom ažuriranja smještaja: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }



    private void showDeleteSmjestajForm() {
        JFrame frame = new JFrame("Obriši Smještaj");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(229,204,255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Naziv Smještaja field
        JLabel nazivLabel = new JLabel("Naziv smještaja za brisanje:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nazivLabel, gbc);

        // JComboBox za odabir smještaja
        JComboBox<Smjestaj> smjestajComboBox = new JComboBox<>();
        gbc.gridx = 1;
        panel.add(smjestajComboBox, gbc);

        // Popuni JComboBox sa svim smještajem
        try {
            SmjestajDAO smjestajDAO = new SmjestajDAO();
            List<Smjestaj> smjestaji = smjestajDAO.getAllSmjestaji();
            for (Smjestaj smjestaj : smjestaji) {
                smjestajComboBox.addItem(smjestaj);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Greška prilikom preuzimanja smještaja: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }

        // Delete button
        JButton deleteButton = new JButton("Obriši Smještaj");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(deleteButton, gbc);

        deleteButton.addActionListener(e -> {
            try {
                Smjestaj selectedSmjestaj = (Smjestaj) smjestajComboBox.getSelectedItem();
                if (selectedSmjestaj != null) {
                    SmjestajDAO smjestajDAO = new SmjestajDAO();
                    smjestajDAO.deleteSmjestajByNaziv(selectedSmjestaj.getNazivSmjestaja());
                    JOptionPane.showMessageDialog(frame, "Smještaj uspješno obrisan.");
                    frame.dispose();
                    showAllSmjestaj(); 
                } else {
                    JOptionPane.showMessageDialog(frame, "Niste izabrali smještaj.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Greška prilikom brisanja smještaja: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }


    private void showCreateAranzmanForm() {
        JFrame frame = new JFrame("Kreiraj Aranžman");
        frame.setSize(600, 600);        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255,229,204));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label and text field for naziv destinacije
        JLabel nazivDestinacijeLabel = new JLabel("Naziv destinacije:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nazivDestinacijeLabel, gbc);

        JTextField nazivDestinacijeField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nazivDestinacijeField, gbc);

        // Label and text field for cijena
        JLabel cijenaLabel = new JLabel("Cijena:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(cijenaLabel, gbc);

        JTextField cijenaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cijenaField, gbc);

        // Label and text field for termin
        JLabel lblTermin = new JLabel("Termin (yyyy-MM-dd):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblTermin, gbc);

        JTextField txtTermin = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtTermin, gbc);

        // Label and text field for trajanje
        JLabel trajanjeLabel = new JLabel("Trajanje:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(trajanjeLabel, gbc);

        JTextField trajanjeField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(trajanjeField, gbc);

        // Label and combo box for vodič
        JLabel vodicLabel = new JLabel("Vodič:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(vodicLabel, gbc);

        JComboBox<Vodic> vodicComboBox = new JComboBox<>();
        try {
            AranzmanDAO aranzmanDAO = new AranzmanDAO();
            List<Vodic> vodici = aranzmanDAO.getAllVodici();
            for (Vodic vodic : vodici) {
                vodicComboBox.addItem(vodic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(vodicComboBox, gbc);

        // Label and combo box for osiguranje
        JLabel osiguranjeLabel = new JLabel("Osiguranje:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(osiguranjeLabel, gbc);

        JComboBox<Osiguranje> osiguranjeComboBox = new JComboBox<>();
        try {
            AranzmanDAO aranzmanDAO = new AranzmanDAO();
            List<Osiguranje> osiguranja = aranzmanDAO.getAllOsiguranja();
            for (Osiguranje osiguranje : osiguranja) {
                osiguranjeComboBox.addItem(osiguranje);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(osiguranjeComboBox, gbc);

        // Label and combo box for smještaj
        JLabel smjestajLabel = new JLabel("Smještaj:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(smjestajLabel, gbc);

        JComboBox<Smjestaj> smjestajComboBox = new JComboBox<>();
        try {
            SmjestajDAO smjestajDAO = new SmjestajDAO();
            List<Smjestaj> smjestaji = smjestajDAO.getAllSmjestaji();
            for (Smjestaj smjestaj : smjestaji) {
                smjestajComboBox.addItem(smjestaj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(smjestajComboBox, gbc);

        // Label and combo box for prevoz
        JLabel prevozLabel = new JLabel("Prevoz:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(prevozLabel, gbc);

        JComboBox<Prevoz> prevozComboBox = new JComboBox<>();
        try {
            PrevozDAO prevozDAO = new PrevozDAO();
            List<Prevoz> prevozi = prevozDAO.getAllPrevoz();
            for (Prevoz prevoz : prevozi) {
                prevozComboBox.addItem(prevoz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(prevozComboBox, gbc);
        
        // Label and combo box for kategorija aranžmana
        JLabel kategorijaLabel = new JLabel("Kategorija Aranžmana:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(kategorijaLabel, gbc);

        JComboBox<Kategorija> kategorijaComboBox = new JComboBox<>();
        try {
            AranzmanDAO kategorijaDAO = new AranzmanDAO();
            List<Kategorija> kategorije = kategorijaDAO.getAllKategorija();
            for (Kategorija kategorija : kategorije) {
                kategorijaComboBox.addItem(kategorija);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(kategorijaComboBox, gbc);

        // Button to submit the form
        JButton submitButton = new JButton("Kreiraj Aranžman");
        submitButton.addActionListener(e -> {
            // Get data from form
            String nazivDestinacije = nazivDestinacijeField.getText();
            BigDecimal cijena = new BigDecimal(cijenaField.getText());
            String terminStr = txtTermin.getText();
            LocalDate termin = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                termin = LocalDate.parse(terminStr, formatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Datum nije u ispravnom formatu (dd-MM-yyyy).", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String trajanje = trajanjeField.getText();
            Vodic selectedVodic = (Vodic) vodicComboBox.getSelectedItem();
            Osiguranje selectedOsiguranje = (Osiguranje) osiguranjeComboBox.getSelectedItem();
            Smjestaj selectedSmjestaj = (Smjestaj) smjestajComboBox.getSelectedItem();
            Prevoz selectedPrevoz = (Prevoz) prevozComboBox.getSelectedItem();
            Kategorija selectedKategorija = (Kategorija) kategorijaComboBox.getSelectedItem();

            // Create Aranzman object
            Aranzman aranzman = new Aranzman();
            aranzman.setNazivDestinacije(nazivDestinacije);
            aranzman.setCijena(cijena);
            aranzman.setTermin(termin);
            aranzman.setTrajanje(trajanje);
            if (selectedVodic != null) aranzman.setVodicId(selectedVodic.getIdVodica());
            if (selectedOsiguranje != null) aranzman.setOsiguranjeId(selectedOsiguranje.getIdOsiguranja());
            if (selectedKategorija != null) aranzman.setIdKategorije(selectedKategorija.getIdKategorije());

            // Save the Aranzman to database
            try {
                AranzmanDAO aranzmanDAO = new AranzmanDAO();
                aranzmanDAO.createAranzman(aranzman);

                int aranzmanId = aranzmanDAO.getIdForNewAranzman(aranzman);

                if (selectedSmjestaj != null) {
                    AranzmanSmjestajDAO aranzmanSmjestajDAO = new AranzmanSmjestajDAO();
                    aranzmanSmjestajDAO.addSmjestajToAranzman(aranzmanId, selectedSmjestaj.getId());
                }

                if (selectedPrevoz != null) {
                    AranzmanPrevozDAO aranzmanPrevozDAO = new AranzmanPrevozDAO();
                    aranzmanPrevozDAO.addPrevozToAranzman(aranzmanId, selectedPrevoz.getIdPrevoza());
                }

                JOptionPane.showMessageDialog(frame, "Aranžman je uspješno kreiran.");
                frame.dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Došlo je do greške prilikom kreiranja aranžmana.");
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(submitButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    
    private void deleteAranzmanByDestinacija() {
        // Prompt the user to enter the naziv destinacije
        String nazivDestinacije = JOptionPane.showInputDialog(this, "Unesite naziv destinacije:", "Brisanje aranžmana", JOptionPane.PLAIN_MESSAGE);

        if (nazivDestinacije == null || nazivDestinacije.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Naziv destinacije ne može biti prazan.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a DAO object to delete the aranzman
        AranzmanDAO aranzmanDAO = new AranzmanDAO();
        try {
            int rowsAffected = aranzmanDAO.deleteAranzmanByDestinacija(nazivDestinacije);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Aranžman uspješno obrisan.", "Uspjeh", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Nije pronađen aranžman sa zadatim nazivom destinacije.", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greška prilikom brisanja aranžmana: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showFilterRezervacijaForm() throws SQLException {
        String destination = JOptionPane.showInputDialog(this, "Unesite naziv destinacije za filtriranje rezervacija:", "Filtriranje rezervacija", JOptionPane.PLAIN_MESSAGE);
        if (destination != null && !destination.trim().isEmpty()) {
            List<Rezervacija> rezervacije = new ArrayList<>();
            try {
                RezervacijaDAO rezervacijaDAO = new RezervacijaDAO();
                rezervacije = rezervacijaDAO.getRezervacijeByDestination(destination);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Greška prilikom filtriranja rezervacija: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Define the column names
            String[] columnNames = {"ID", "Datum Rezervacije", "Ukupna Cijena", "Klijent", "Aranžman", "Zaposleni", "Način Plaćanja", "Poslovnica"};

            // Create a 2D array to hold the data
            Object[][] data = new Object[rezervacije.size()][columnNames.length];
            for (int i = 0; i < rezervacije.size(); i++) {
                Rezervacija rezervacija = rezervacije.get(i);
                data[i][0] = rezervacija.getIdRezervacije();
                data[i][1] = rezervacija.getDatumRezervacije();
                data[i][2] = rezervacija.getUkupnaCijena();
                
                RezervacijaDAO rezervacijaDAO = new RezervacijaDAO();
                // Retrieve additional details like client name, payment method, etc.
                Klijent klijent = rezervacijaDAO.getKlijentById(rezervacija.getKlijentId());
                Aranzman aranzman = rezervacijaDAO.getAranzmanById(rezervacija.getAranzmanId());
                Zaposleni zaposleni = rezervacijaDAO.getZaposleniById(rezervacija.getZaposleniId());
                NacinPlacanja nacinPlacanja = rezervacijaDAO.getNacinPlacanjaById(rezervacija.getNacinPlacanjaId());
                Poslovnica poslovnica = rezervacijaDAO.getPoslovnicaById(rezervacija.getPoslovnicaId());

                data[i][3] = klijent != null ? klijent.getIme() + " " + klijent.getPrezime() : "N/A";
                data[i][4] = aranzman != null ? aranzman.getNazivDestinacije() : "N/A";
                data[i][5] = zaposleni != null ? zaposleni.getIme() + " " + zaposleni.getPrezime() : "N/A"; 
                data[i][6] = nacinPlacanja != null ? nacinPlacanja.getNazivNacinaPlacanja() : "N/A";
                data[i][7] = poslovnica != null ? poslovnica.getAdresa() : "N/A";
            }

            // Create a table with the data and column names
            JTable rezervacijaTable = new JTable(data, columnNames);
            rezervacijaTable.setBackground(new Color(204,255,204)); // Pozadinska boja tabele

            // Stilizovanje zaglavlja kolona
            JTableHeader tableHeader = rezervacijaTable.getTableHeader();
            tableHeader.setBackground(new Color(204,255,204)); // Pozadinska boja zaglavlja
            tableHeader.setForeground(Color.BLACK); // Boja teksta zaglavlja
            tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Stil i veličina fonta

           

            // Create a scroll pane and add the table to it
            JScrollPane scrollPane = new JScrollPane(rezervacijaTable);
            scrollPane.getViewport().setBackground(new Color(204,255,204)); // Pozadinska boja skrola

            // Create a new panel to hold the filtered table
            JPanel filterPanel = new JPanel(new BorderLayout());
            filterPanel.setBackground(new Color(204,255,204)); // Pozadinska boja panela
            filterPanel.add(scrollPane, BorderLayout.CENTER);

            // Remove any existing components and add the new panel
            dynamicPanel.removeAll();
            dynamicPanel.add(filterPanel, "FilterRezervacija");

            // Show the new panel
            CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
            cardLayout.show(dynamicPanel, "FilterRezervacija");
        }
    }



    private void showFilterAranzmanForm() {
        String destination = JOptionPane.showInputDialog(this, "Unesite naziv destinacije za filtriranje:", "Filtriranje aranžmana", JOptionPane.PLAIN_MESSAGE);
        if (destination != null && !destination.trim().isEmpty()) {
            List<Aranzman> aranzmani = new ArrayList<>();
            try {
                AranzmanDAO aranzmanDAO = new AranzmanDAO();
                aranzmani = aranzmanDAO.getAranzmaniByDestination(destination);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Greška prilikom filtriranja aranžmana: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Define the column names
            String[] columnNames = {"ID", "Naziv Destinacije", "Cijena", "Termin", "Trajanje", "Vodic", "Osiguranje"};

            // Create a 2D array to hold the data
            Object[][] data = new Object[aranzmani.size()][columnNames.length];
            for (int i = 0; i < aranzmani.size(); i++) {
                Aranzman aranzman = aranzmani.get(i);
                data[i][0] = aranzman.getIdAranzmana();
                data[i][1] = aranzman.getNazivDestinacije();
                data[i][2] = aranzman.getCijena();
                data[i][3] = aranzman.getTermin();
                data[i][4] = aranzman.getTrajanje();
                
                // Get Vodic details
                AranzmanDAO aranzmanDAO = new AranzmanDAO();
                try {
                    Vodic vodic = aranzmanDAO.getVodicById(aranzman.getVodicId());
                    data[i][5] = vodic != null ? vodic.getIme() + " " + vodic.getPrezime() : "N/A";
                    
                    Osiguranje osiguranje = aranzmanDAO.getOsiguranjeById(aranzman.getOsiguranjeId());
                    data[i][6] = osiguranje != null ? osiguranje.getTipOsiguranja() : "N/A";
                } catch (SQLException e) {
                    data[i][5] = "N/A";
                    data[i][6] = "N/A";
                }
            }

            // Create a table with the data and column names
            JTable aranzmanTable = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(aranzmanTable);

            // Style the table and scroll pane
            aranzmanTable.setFillsViewportHeight(true);
            aranzmanTable.setBackground(new Color(204,255,204));
            aranzmanTable.setGridColor(new Color(204,255,204));

            // Style the header (column names)
            JTableHeader header = aranzmanTable.getTableHeader();
            header.setBackground(new Color(204,255,204)); // Set background color (light blue)
            header.setForeground(Color.WHITE); // Set text color (white)
            header.setFont(new Font("Arial", Font.BOLD, 14)); // Set font style

            // Create a new panel to hold the filtered table
            JPanel filterPanel = new JPanel(new BorderLayout());
            filterPanel.add(scrollPane, BorderLayout.CENTER);
            filterPanel.setBackground(new Color(204,255,204)); // Soft green background

            // Remove any existing components and add the new panel
            dynamicPanel.removeAll();
            dynamicPanel.add(filterPanel, "FilterAranzman");

            // Show the new panel
            CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
            cardLayout.show(dynamicPanel, "FilterAranzman");
        }
    }

    
   /* private void showAranzmanCrudPanel() {
        JPanel aranzmanPanel = new JPanel(new GridLayout(4, 1));
        JButton viewButton = new JButton("Vidi sve aranžmane");
        JButton filtrirajButton = new JButton("Filtriraj po destinaciji");
        JButton addButton = new JButton("Dodaj novi aranžman");
        JButton deleteButton = new JButton("Obriši aranžman");

        aranzmanPanel.add(viewButton);
        aranzmanPanel.add(filtrirajButton);
        aranzmanPanel.add(addButton);
       

        dynamicPanel.add(aranzmanPanel, "Aranzman");

        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Aranzman");

        viewButton.addActionListener(e -> showAllAranzman());
        addButton.addActionListener(e -> showCreateAranzmanForm());
        filtrirajButton.addActionListener(e -> showFilterAranzmanForm());
        //treba by destinacija i termin
        
    }*/
    
  /*  private void showAranzmanCrudPanel() throws IOException {
        JPanel aranzmanPanel = new JPanel(new GridLayout(3, 1));

        // Load the images
        ImageIcon viewIcon = new ImageIcon("travel.jpg");
        ImageIcon filterIcon = new ImageIcon("travel3.jpg");
        ImageIcon addIcon = new ImageIcon("travel5.jpg");
       

        // Create buttons with images
        Image img1 = ImageIO.read(getClass().getResource("slika1.jpg"));
        Image img2 = ImageIO.read(getClass().getResource("slika4.jpg"));
        Image img3 = ImageIO.read(getClass().getResource("slika10.jpg"));
        
        JButton viewButton = new JButton("Vidi sve aranžmane");
        viewButton.setIcon(new ImageIcon(img1));
        JButton filtrirajButton = new JButton("Filtriraj po destinaciji", filterIcon);
        filtrirajButton.setIcon(new ImageIcon(img2));
        JButton addButton = new JButton("Dodaj novi aranžman", addIcon);
        addButton.setIcon(new ImageIcon(img3));
       // JButton deleteButton = new JButton("Obriši aranžman", deleteIcon);

        // Set the text to be underneath the icon
        viewButton.setHorizontalTextPosition(SwingConstants.CENTER);
        viewButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        filtrirajButton.setHorizontalTextPosition(SwingConstants.CENTER);
        filtrirajButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        addButton.setHorizontalTextPosition(SwingConstants.CENTER);
        addButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        viewButton.addActionListener(e -> showAllAranzman());
        addButton.addActionListener(e -> showCreateAranzmanForm());
        filtrirajButton.addActionListener(e -> showFilterAranzmanForm());

        // Add buttons to the panel
        aranzmanPanel.add(viewButton);
        aranzmanPanel.add(filtrirajButton);
        aranzmanPanel.add(addButton);
       

        dynamicPanel.add(aranzmanPanel, "Aranzman");

        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Aranzman");

       
        //deleteButton.addActionListener(e -> showDeleteAranzmanForm());
    }*/
    private void showAranzmanCrudPanel() throws IOException {
        JPanel aranzmanPanel = new JPanel(new GridLayout(3, 1));

        // Load the full image
        BufferedImage fullImage = ImageIO.read(getClass().getResource("slika10.jpg"));
        int imageHeight = fullImage.getHeight();
        int imageWidth = fullImage.getWidth();

        // Calculate the height of each part
        int partHeight = imageHeight / 3;

        // Extract the top, middle, and bottom parts of the image
        Image topPart = fullImage.getSubimage(0, 0, imageWidth, partHeight);
        Image middlePart = fullImage.getSubimage(0, partHeight, imageWidth, partHeight);
        Image bottomPart = fullImage.getSubimage(0, partHeight * 2, imageWidth, partHeight);

        // Create buttons
        JButton viewButton = new JButton("Vidi sve aranžmane"); 
        JButton filtrirajButton = new JButton("Filtriraj po destinaciji");
        JButton addButton = new JButton("Dodaj novi aranžman");
        
        // Set font to bold and color to white
        Font boldFont = new Font("Arial", Font.BOLD, 14); // Adjust size as needed
        viewButton.setFont(boldFont);
        viewButton.setForeground(Color.WHITE);
        filtrirajButton.setFont(boldFont);
        filtrirajButton.setForeground(Color.WHITE);
        addButton.setFont(boldFont);
        addButton.setForeground(Color.WHITE);

        // Add component listener to get button width after panel layout
        aranzmanPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int buttonWidth = viewButton.getWidth();
                
                if (buttonWidth > 0) { // Check if the button width is valid
                    // Resize the images to match the button width
                    Image img1 = topPart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img2 = middlePart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img3 = bottomPart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);

                    // Set the resized images as icons
                    viewButton.setIcon(new ImageIcon(img1));
                    filtrirajButton.setIcon(new ImageIcon(img2));
                    addButton.setIcon(new ImageIcon(img3));

                    // Set the text to be underneath the icon
                    viewButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    viewButton.setVerticalTextPosition(SwingConstants.CENTER);

                    filtrirajButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    filtrirajButton.setVerticalTextPosition(SwingConstants.CENTER);

                    addButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    addButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
            }
        });

        // Add buttons to the panel
        aranzmanPanel.add(viewButton);
        aranzmanPanel.add(filtrirajButton);
        aranzmanPanel.add(addButton);

        // Set up action listeners
        viewButton.addActionListener(e -> showAllAranzman());
        addButton.addActionListener(e -> showCreateAranzmanForm());
        filtrirajButton.addActionListener(e -> showFilterAranzmanForm());

        // Add the panel to dynamicPanel and show it
        dynamicPanel.add(aranzmanPanel, "Aranzman");

        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Aranzman");
    }

    
    
    private void showRezervacijaCrudPanel() throws IOException {
        JPanel rezervacijaPanel = new JPanel(new GridLayout(3, 1));

        

        // Load the full image
        BufferedImage fullImage = ImageIO.read(getClass().getResource("slika15.jpg"));
        int imageHeight = fullImage.getHeight();
        int imageWidth = fullImage.getWidth();

        // Calculate the height of each part
        int partHeight = imageHeight / 3;

        // Extract the top, middle, and bottom parts of the image
        Image topPart = fullImage.getSubimage(0, 0, imageWidth, partHeight);
        Image middlePart = fullImage.getSubimage(0, partHeight, imageWidth, partHeight);
        Image bottomPart = fullImage.getSubimage(0, partHeight * 2, imageWidth, partHeight);

        // Create buttons
        JButton viewButton = new JButton("Vidi sve rezervacije");
        JButton filtrirajButton = new JButton("Filtriraj po destinaciji");
        JButton addButton = new JButton("Dodaj novu rezervaciju");
        
        // Set font to bold and color to white
        Font boldFont = new Font("Arial", Font.BOLD, 14); // Adjust size as needed
        viewButton.setFont(boldFont);
        viewButton.setForeground(Color.WHITE);
        filtrirajButton.setFont(boldFont);
        filtrirajButton.setForeground(Color.WHITE);
        addButton.setFont(boldFont);
        addButton.setForeground(Color.WHITE);

        // Add component listener to get button width after panel layout
        rezervacijaPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int buttonWidth = viewButton.getWidth();
                
                if (buttonWidth > 0) { // Check if the button width is valid
                    // Resize the images to match the button width
                    Image img1 = topPart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img2 = middlePart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);
                    Image img3 = bottomPart.getScaledInstance(buttonWidth, -1, Image.SCALE_SMOOTH);

                    // Set the resized images as icons
                    viewButton.setIcon(new ImageIcon(img1));
                    filtrirajButton.setIcon(new ImageIcon(img2));
                    addButton.setIcon(new ImageIcon(img3));

                    // Set the text to be underneath the icon
                    viewButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    viewButton.setVerticalTextPosition(SwingConstants.CENTER);

                    filtrirajButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    filtrirajButton.setVerticalTextPosition(SwingConstants.CENTER);

                    addButton.setHorizontalTextPosition(SwingConstants.CENTER);
                    addButton.setVerticalTextPosition(SwingConstants.CENTER);
                }
            }
        });
        // Set the icons for the buttons
        

        // Set up action listeners
        viewButton.addActionListener(e -> {
            try {
                showAllRezervacija();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        addButton.addActionListener(e -> showCreateRezervacijaForm());
        filtrirajButton.addActionListener(e -> {
            try {
                showFilterRezervacijaForm();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        // Add buttons to the panel
        rezervacijaPanel.add(viewButton);
        rezervacijaPanel.add(filtrirajButton);
        rezervacijaPanel.add(addButton);

        // Add the panel to dynamicPanel and show it
        dynamicPanel.add(rezervacijaPanel, "Rezervacija");

        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Rezervacija");
    }


    private void showFilterRezervacijaForma() throws SQLException {
        String destination = JOptionPane.showInputDialog(this, "Unesite naziv destinacije za filtriranje rezervacija:", "Filtriranje rezervacija", JOptionPane.PLAIN_MESSAGE);
        if (destination != null && !destination.trim().isEmpty()) {
            List<Rezervacija> rezervacije = new ArrayList<>();
            try {
                RezervacijaDAO rezervacijaDAO = new RezervacijaDAO();
                rezervacije = rezervacijaDAO.getRezervacijeByDestination(destination);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Greška prilikom filtriranja rezervacija: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Define the column names
            String[] columnNames = {"ID", "Datum Rezervacije", "Ukupna Cijena", "Klijent", "Aranžman", "Zaposleni", "Način Plaćanja", "Poslovnica"};

            // Create a 2D array to hold the data
            Object[][] data = new Object[rezervacije.size()][columnNames.length];
            for (int i = 0; i < rezervacije.size(); i++) {
                Rezervacija rezervacija = rezervacije.get(i);
                data[i][0] = rezervacija.getIdRezervacije();
                data[i][1] = rezervacija.getDatumRezervacije();
                data[i][2] = rezervacija.getUkupnaCijena();
                
                RezervacijaDAO rezervacijaDAO = new RezervacijaDAO();
                // Retrieve additional details like client name, payment method, etc.
                Klijent klijent = rezervacijaDAO.getKlijentById(rezervacija.getKlijentId());
                Aranzman aranzman = rezervacijaDAO.getAranzmanById(rezervacija.getAranzmanId());
                Zaposleni zaposleni = rezervacijaDAO.getZaposleniById(rezervacija.getZaposleniId()); // Assuming there's a method for this
                NacinPlacanja nacinPlacanja = rezervacijaDAO.getNacinPlacanjaById(rezervacija.getNacinPlacanjaId());
                Poslovnica poslovnica = rezervacijaDAO.getPoslovnicaById(rezervacija.getPoslovnicaId());

                data[i][3] = klijent != null ? klijent.getIme() + " " + klijent.getPrezime() : "N/A";
                data[i][4] = aranzman != null ? aranzman.getNazivDestinacije() : "N/A";
                data[i][5] = zaposleni != null ? zaposleni.getIme() + " " + zaposleni.getPrezime() : "N/A"; // Adjust as needed
                data[i][6] = nacinPlacanja != null ? nacinPlacanja.getNazivNacinaPlacanja() : "N/A";
                data[i][7] = poslovnica != null ? poslovnica.getAdresa() : "N/A";
            }

            // Create a table with the data and column names
            JTable rezervacijaTable = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(rezervacijaTable);

            // Create a new panel to hold the filtered table
            JPanel filterPanel = new JPanel(new BorderLayout());
            filterPanel.add(scrollPane, BorderLayout.CENTER);
            filterPanel.setBackground(new Color(204,255,204));

            // Remove any existing components and add the new panel
            dynamicPanel.removeAll();
            dynamicPanel.add(filterPanel, "FilterRezervacija");

            // Show the new panel
            CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
            cardLayout.show(dynamicPanel, "FilterRezervacija");
        }
    }


    private void showCreateRezervacijaForm() {
        JFrame frame = new JFrame("Kreiraj Rezervaciju");
        frame.setSize(600, 700); // Increase size to accommodate new field
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255,229,204));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label and combo box for Aranžman
        JLabel aranzmanLabel = new JLabel("Aranžman:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(aranzmanLabel, gbc);

        JComboBox<Aranzman> aranzmanComboBox = new JComboBox<>();
        try {
            AranzmanDAO aranzmanDAO = new AranzmanDAO();
            List<Aranzman> aranzmani = aranzmanDAO.getAllAranzmani();
            for (Aranzman aranzman : aranzmani) {
                aranzmanComboBox.addItem(aranzman);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(aranzmanComboBox, gbc);

        // Label and combo box for Klijent
        JLabel klijentLabel = new JLabel("Klijent:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(klijentLabel, gbc);

        JComboBox<Klijent> klijentComboBox = new JComboBox<>();
        try {
            RezervacijaDAO rezervacijaDAO = new RezervacijaDAO();
            List<Klijent> klijenti = rezervacijaDAO.getAllKlijenti();
            for (Klijent klijent : klijenti) {
                klijentComboBox.addItem(klijent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(klijentComboBox, gbc);

        // Label and combo box for Poslovnica
        JLabel poslovnicaLabel = new JLabel("Poslovnica:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(poslovnicaLabel, gbc);

        JComboBox<Poslovnica> poslovnicaComboBox = new JComboBox<>();
        try {
            RezervacijaDAO rezervacijaDAO = new RezervacijaDAO();
            List<Poslovnica> poslovnice = rezervacijaDAO.getAllPoslovnice();
            for (Poslovnica poslovnica : poslovnice) {
                poslovnicaComboBox.addItem(poslovnica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(poslovnicaComboBox, gbc);

        // Label and combo box for Zaposleni
        JLabel zaposleniLabel = new JLabel("Zaposleni:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(zaposleniLabel, gbc);

        JComboBox<Zaposleni> zaposleniComboBox = new JComboBox<>();
        try {
            RezervacijaDAO rezervacijaDAO = new RezervacijaDAO();
            List<Zaposleni> zaposleni = rezervacijaDAO.getAllZaposleni();
            for (Zaposleni zaposlen : zaposleni) {
                zaposleniComboBox.addItem(zaposlen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(zaposleniComboBox, gbc);

        // Label and text field for datum rezervacije
        JLabel datumRezervacijeLabel = new JLabel("Datum rezervacije (yyyy-MM-dd):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(datumRezervacijeLabel, gbc);

        JTextField datumRezervacijeField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(datumRezervacijeField, gbc);

        // Label and text field for ukupna cijena
        JLabel ukupnaCijenaLabel = new JLabel("Ukupna cijena:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(ukupnaCijenaLabel, gbc);

        JTextField ukupnaCijenaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(ukupnaCijenaField, gbc);

        // Label and combo box for način plaćanja
        JLabel nacinPlacanjaLabel = new JLabel("Način plaćanja:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(nacinPlacanjaLabel, gbc);

        JComboBox<NacinPlacanja> nacinPlacanjaComboBox = new JComboBox<>();
        try {
            RezervacijaDAO nacinPlacanjaDAO = new RezervacijaDAO();
            List<NacinPlacanja> naciniPlacanja = nacinPlacanjaDAO.getAllNaciniPlacanja();
            for (NacinPlacanja nacinPlacanja : naciniPlacanja) {
                nacinPlacanjaComboBox.addItem(nacinPlacanja);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(nacinPlacanjaComboBox, gbc);

        // Label and list for dodatne usluge
        JLabel uslugeLabel = new JLabel("Dodatne usluge:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(uslugeLabel, gbc);

        DefaultListModel<Usluga> uslugeListModel = new DefaultListModel<>();
        try {
            UslugaDAO uslugaDAO = new UslugaDAO();
            List<Usluga> usluge = uslugaDAO.getAllUsluge();
            for (Usluga usluga : usluge) {
                uslugeListModel.addElement(usluga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JList<Usluga> uslugeList = new JList<>(uslugeListModel);
        uslugeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(uslugeList), gbc);
        gbc.gridheight = 1;

        // Button to submit the form
        JButton submitButton = new JButton("Kreiraj Rezervaciju");
        submitButton.addActionListener(e -> {
            // Get data from form
            Aranzman selectedAranzman = (Aranzman) aranzmanComboBox.getSelectedItem();
            Klijent selectedKlijent = (Klijent) klijentComboBox.getSelectedItem();
            Poslovnica selectedPoslovnica = (Poslovnica) poslovnicaComboBox.getSelectedItem();
            Zaposleni selectedZaposleni = (Zaposleni) zaposleniComboBox.getSelectedItem(); // New employee selection
            NacinPlacanja selectedNacinPlacanja = (NacinPlacanja) nacinPlacanjaComboBox.getSelectedItem();
            // Get selected payment method
            String datumRezervacijeStr = datumRezervacijeField.getText();
            LocalDate datumRezervacije = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                datumRezervacije = LocalDate.parse(datumRezervacijeStr, formatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Datum nije u ispravnom formatu (yyyy-MM-dd).", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BigDecimal ukupnaCijena = BigDecimal.ZERO;
            try {
                if (selectedAranzman != null) {
                    ukupnaCijena = selectedAranzman.getCijena();

                    // Fetch osiguranje details
                    Osiguranje osiguranje = null;
                    AranzmanDAO osiguranjeDAO = new AranzmanDAO();
                    osiguranje = osiguranjeDAO.getOsiguranjeById(selectedAranzman.getOsiguranjeId());
                 /*  if (osiguranje != null) {
                        ukupnaCijena = ukupnaCijena.add(osiguranje.getCijena());
                    }
*/
                    // Add prices of additional services
                    for (Usluga usluga : uslugeList.getSelectedValuesList()) {
                        ukupnaCijena = ukupnaCijena.add(usluga.getCijena());
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Došlo je do greške prilikom izračunavanja ukupne cijene.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ukupnaCijenaField.setText(ukupnaCijena.toString());

            // Create Rezervacija object
            Rezervacija rezervacija = new Rezervacija();
            if (selectedAranzman != null) rezervacija.setAranzmanId(selectedAranzman.getIdAranzmana());
            if (selectedKlijent != null) rezervacija.setKlijentId(selectedKlijent.getIdKlijenta());
            rezervacija.setDatumRezervacije(datumRezervacije);
            rezervacija.setUkupnaCijena(ukupnaCijena);
            if (selectedNacinPlacanja != null) rezervacija.setNacinPlacanjaId(selectedNacinPlacanja.getIdNacinaPlacanja()); // Set payment method ID
            if (selectedPoslovnica != null) rezervacija.setPoslovnicaId(selectedPoslovnica.getIdPoslovnice());
            if (selectedZaposleni != null) rezervacija.setZaposleniId(selectedZaposleni.getIdZaposlenog()); // Set employee ID
            rezervacija.setUsluge(uslugeList.getSelectedValuesList());

            // Save the Rezervacija to database
            try {
                RezervacijaDAO rezervacijaDAO = new RezervacijaDAO();
                rezervacijaDAO.createRezervacija(rezervacija);

                JOptionPane.showMessageDialog(frame, "Rezervacija je uspješno kreirana.");
                frame.dispose(); // Close the form
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Došlo je do greške prilikom spremanja rezervacije.", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 9; // Update the position for submit button
        panel.add(submitButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }


   

    
    private void showAllAranzman() {
        // Create a panel to hold the table
        JPanel aranzmanPanel = new JPanel(new BorderLayout());
        aranzmanPanel.setBackground(new Color(204,255,204));

        // Create a DAO object to get all aranzmani
        AranzmanDAO aranzmanDAO = new AranzmanDAO();
        List<Aranzman> aranzmani = new ArrayList<>();
        try {
            aranzmani = aranzmanDAO.getAllAranzmani();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greška prilikom dohvatanja aranžmana: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Define the column names
        String[] columnNames = {"ID", "Naziv Destinacije", "Cijena", "Termin", "Trajanje", "Vodič", "Tip Osiguranja"};

        // Create a 2D array to hold the data
        Object[][] data = new Object[aranzmani.size()][columnNames.length];
        for (int i = 0; i < aranzmani.size(); i++) {
            Aranzman aranzman = aranzmani.get(i);
            data[i][0] = aranzman.getIdAranzmana();
            data[i][1] = aranzman.getNazivDestinacije();
            data[i][2] = aranzman.getCijena();
            data[i][3] = aranzman.getTermin();
            data[i][4] = aranzman.getTrajanje();

            // Fetch additional details using DAO methods
            Vodic vodic = null;
            Osiguranje osiguranje = null;

            try {
                vodic = aranzmanDAO.getVodicById(aranzman.getVodicId());
                osiguranje = aranzmanDAO.getOsiguranjeById(aranzman.getOsiguranjeId());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Greška prilikom dohvatanja vodiča ili osiguranja: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }

            // Display the name and surname of the guide, or "N/A" if not found
            data[i][5] = vodic != null ? vodic.getIme() + " " + vodic.getPrezime() : "N/A";

            // Display the insurance type, or "N/A" if not found
            data[i][6] = osiguranje != null ? osiguranje.getTipOsiguranja() : "N/A";
        }

        // Create a table with the data and column names
        JTable aranzmanTable = new JTable(data, columnNames);

        // Styling the table
        aranzmanTable.setBackground(new Color(204,255,204)); // Table background color
        JTableHeader tableHeader = aranzmanTable.getTableHeader();
        tableHeader.setBackground(new Color(204,255,204)); // Header background color
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Header font style

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(aranzmanTable);
        scrollPane.getViewport().setBackground(new Color(204,255,204)); // Scroll pane background color

        // Add the table to the panel
        aranzmanPanel.add(scrollPane, BorderLayout.CENTER);

        // Remove any existing components and add the new panel
        dynamicPanel.removeAll();
        dynamicPanel.add(aranzmanPanel, "Aranzman");

        // Show the new panel
        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Aranzman");
    }

    private void showAllRezervacija() throws SQLException {
        // Create a panel to hold the table
        JPanel rezervacijaPanel = new JPanel(new BorderLayout());
        rezervacijaPanel.setBackground(new Color(204,255,204)); // Pozadinska boja panela

        // Create a DAO object to get all rezervacije
        RezervacijaDAO rezervacijaDAO = new RezervacijaDAO();
        List<Rezervacija> rezervacije = new ArrayList<>();
        try {
            rezervacije = rezervacijaDAO.getAllRezervacijeWithDetails();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greška prilikom dohvatanja rezervacija: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Define the column names
        String[] columnNames = {"ID", "Datum Rezervacije", "Ukupna Cijena", "Klijent", "Način Plačanja", "Poslovnica", "Destinacija", "Datum Aranžmana", "Trajanje Aranžmana"};

        // Create a 2D array to hold the data
        Object[][] data = new Object[rezervacije.size()][columnNames.length];
        for (int i = 0; i < rezervacije.size(); i++) {
            Rezervacija rezervacija = rezervacije.get(i);

            // Get details for each rezervacija
            Klijent klijent = rezervacijaDAO.getKlijentById(rezervacija.getKlijentId());
            NacinPlacanja nacinPlacanja = rezervacijaDAO.getNacinPlacanjaById(rezervacija.getNacinPlacanjaId());
            Poslovnica poslovnica = rezervacijaDAO.getPoslovnicaById(rezervacija.getPoslovnicaId());
            Aranzman aranzman = rezervacijaDAO.getAranzmanById(rezervacija.getAranzmanId());

            // Populate data array
            data[i][0] = rezervacija.getIdRezervacije();
            data[i][1] = rezervacija.getDatumRezervacije();
            data[i][2] = rezervacija.getUkupnaCijena();
            data[i][3] = (klijent != null) ? klijent.getIme() + " " + klijent.getPrezime() : "Nepoznato";
            data[i][4] = (nacinPlacanja != null) ? nacinPlacanja.getNazivNacinaPlacanja() : "Nepoznato";
            data[i][5] = (poslovnica != null) ? poslovnica.getAdresa() : "Nepoznata";
            data[i][6] = (aranzman != null) ? aranzman.getNazivDestinacije() : "Nepoznato";
            data[i][7] = (aranzman != null) ? aranzman.getTermin() : "Nepoznat";
            data[i][8] = (aranzman != null) ? aranzman.getTrajanje() : "Nepoznato";
        }

        // Create a table with the data and column names
        JTable rezervacijaTable = new JTable(data, columnNames);
        rezervacijaTable.setBackground(new Color(204,255,204)); // Pozadinska boja tabele

        // Stilizovanje zaglavlja kolona
        JTableHeader tableHeader = rezervacijaTable.getTableHeader();
        tableHeader.setBackground(new Color(204,255,204)); // Pozadinska boja zaglavlja
        tableHeader.setForeground(Color.BLACK); // Boja teksta zaglavlja
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Stil i veličina fonta

      

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(rezervacijaTable);
        scrollPane.getViewport().setBackground(new Color(204,255,204)); // Pozadinska boja skrola

        // Add the scroll pane to the panel
        rezervacijaPanel.add(scrollPane, BorderLayout.CENTER);

        // Remove any existing components and add the new panel
        dynamicPanel.removeAll();
        dynamicPanel.add(rezervacijaPanel, "Rezervacija");

        // Show the new panel
        CardLayout cardLayout = (CardLayout) dynamicPanel.getLayout();
        cardLayout.show(dynamicPanel, "Rezervacija");
    }

   


    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
    
    class BackgroundPanel extends JPanel {

        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                // Load the background image from the specified path
                backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Background image not found: " + imagePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
