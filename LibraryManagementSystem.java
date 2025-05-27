// LIBRARY MANAGEMENT SYSTEM BY HELP OF SWING, AWT DEVELOPED BY ROHAN RAJ CHAUDHARY WITH A PASSION FOR CODING HERE IS THE CODE FOR LIBRARY MANAGEMENT SYSTEM AND A PERSONAL ASSISTANT FOR YOU TO HELP YOU WITH YOUR TASKS IN HOME PAGE OF THIS PROJECT YOU CAN FIND THE BUTTONS FOR ADMIN LOGIN AND STUDENT ACCESS, ONCE YOU CLICK ON ADMIN LOGIN YOU WILL BE REDIRECTED TO ADMIN PANEL WHERE YOU CAN ADD, DELETE, LEND AND RETURN BOOKS AND EXPORT AND IMPORT BOOKS FROM CSV FILES. IN STUDENT PANEL YOU CAN SEARCH BOOKS AND BORROW THEM. THIS PROJECT IS DEVELOPED USING JAVA SWING, AWT, AND JAVAX SWING. ENJOY THE LIBRARY MANAGEMENT SYSTEM!
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import org.w3c.dom.events.MouseEvent;

public class LibraryManagementSystem {
    private JFrame frame;
    private JPanel mainPanel, adminPanel, bookPanel, studentPanel;
    private CardLayout cardLayout;
    private ArrayList<Book> books = new ArrayList<>();
    private JTable bookTable, studentBookTable;
    private BookTableModel tableModel;
    private StudentBookTableModel studentTableModel;
    private JTextField searchField, titleField, authorField, erpField, semesterField, studentSearchField;
    private JComboBox<String> semesterCombo;

    public LibraryManagementSystem() {
        initialize();
        // Add some sample books for testing
        books.add(new Book("Higher Mathmatics", "KC SINGHA", "1234567", "Semester 1", "Available", "", "", ""));
        books.add(new Book("Physics", "HC VERMA", "7654321", "Semester 2", "Available", "", "", ""));
    }

    private JButton createGradientButton(String text, Color startColor, Color endColor) {
        return new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(
                    0, 0, startColor,
                    getWidth(), getHeight(), endColor);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();

                super.paintComponent(g);
            }

            @Override
            public void setContentAreaFilled(boolean b) {
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
                g2.dispose();
            }
        };
    }

    private void initialize() {
        // Main frame setup
        frame = new JFrame("Rungta University Library Management System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Card layout for switching panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create all panels
        JPanel homePanel = createHomePanel();
        createAdminPanel();
        createBookPanel();
        createStudentPanel();

        // Add panels to main panel
        mainPanel.add(homePanel, "home");
        mainPanel.add(adminPanel, "admin");
        mainPanel.add(bookPanel, "books");
        mainPanel.add(studentPanel, "student");

        frame.add(mainPanel);
        frame.setVisible(true);
    }




    private JPanel createHomePanel() {
    JPanel panel = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            GradientPaint gradient = new GradientPaint(
                0, 0, new Color(255, 50, 50),
                getWidth(), getHeight(), new Color(255, 215, 0));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    // Header panel with logo and title
    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setOpaque(false);

    // Add logo (replace with your actual image loading code)
    try {

         ImageIcon originalIcon = new ImageIcon("C:\\Users\\Rohan Raj Chaudhary\\Downloads\\20250527_0054_Rungta University Library System_simple_compose_01jw70rq0ge6tak97xqaxcfnwn.png");
        // ImageIcon icon = new ImageIcon(getClass().getResource("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Rohan Raj Chaudhary\\\\\\\\\\\\\\\\Downloads\\\\\\\\\\\\\\\\20250527_0054_Rungta University Library System_simple_compose_01jw70rq0ge6tak97xqaxcfnwn.png"));
        JLabel logoLabel = new JLabel(new ImageIcon(originalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
        headerPanel.add(logoLabel, BorderLayout.WEST);
    } catch (Exception e) {
        JLabel textLabel = new JLabel("RUNGTA UNIVERSITY");
        textLabel.setForeground(Color.BLACK);
        textLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(textLabel, BorderLayout.WEST);
    }

    JLabel welcomeLabel = new JLabel("WELCOME TO CENTRAL LIBRARY OF RUNGTA UNIVERSITY ", JLabel.CENTER);
    welcomeLabel.setFont(new Font("Arial", Font.BOLD, 29));
    welcomeLabel.setForeground(Color.BLACK);
    headerPanel.add(welcomeLabel, BorderLayout.CENTER);

    panel.add(headerPanel, BorderLayout.NORTH);

    // Button panel
    JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));
    buttonPanel.setOpaque(false);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

    JButton adminButton = createGradientButton("Admin Login", new Color(255, 105, 180), new Color(100, 149, 237));
    adminButton.setFont(new Font("Arial", Font.BOLD, 29));

    adminButton.addActionListener(e -> cardLayout.show(mainPanel, "admin"));

    JButton studentButton = createGradientButton("Student Access", new Color(100, 149, 237), new Color(255, 105, 180));
    studentButton.setFont(new Font("Arial", Font.BOLD, 29));
    studentButton.addActionListener(e -> cardLayout.show(mainPanel, "student"));

    buttonPanel.add(adminButton);
    buttonPanel.add(studentButton);

    panel.add(buttonPanel, BorderLayout.CENTER);

    // Footer with social media links
    JPanel footerPanel = new JPanel(new BorderLayout());
    footerPanel.setOpaque(false);
    
    JLabel copyrightLabel = new JLabel("© 2025 Developed By Rohan Raj Chaudhary", JLabel.CENTER);
    copyrightLabel.setForeground(Color.BLACK);
    //  JLabel footerLabel = new JLabel("© 2025 Developed By Rohan Raj Chaudhary", JLabel.CENTER);
        copyrightLabel.setFont(new Font("Arial",Font.PLAIN, 16));
        // footerLabel.setForeground(Color.BLACK);
        copyrightLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(copyrightLabel, BorderLayout.SOUTH);

    
    JPanel socialPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
    socialPanel.setOpaque(false);


// here
   
    
    // // Add social media buttons
    socialPanel.add(createSocialButton("GitHub", "https://github.com/rohanrajchaudhary"));
   
    socialPanel.add(createSocialButton("LinkedIn", "https://linkedin.com/in/rohanrajchaudhary"));
     
    socialPanel.add(createSocialButton("Leetcode", "https://leetcode.com/rohan_raj_chaudhary"));
     socialPanel.setForeground(Color.BLACK);
     socialPanel.add(createSocialButton("Personal Assistant", "https://ai-content-generator-five-ecru.vercel.app/"));
      
    
    footerPanel.add(copyrightLabel, BorderLayout.NORTH);
    footerPanel.add(socialPanel, BorderLayout.CENTER);
    
    panel.add(footerPanel, BorderLayout.SOUTH);

    return panel;
}
private JButton createSocialButton(String textWithEmoji, String url) {
    JButton button = new JButton(textWithEmoji);
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
    button.setBorderPainted(false);
    button.setContentAreaFilled(false);
    button.setFocusPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    button.addActionListener(e -> {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Could not open the link: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    });
    
    button.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent evt) {
            button.setForeground(new Color(200, 200, 255));
            button.setFont(button.getFont().deriveFont(Font.BOLD));
        }
        public void mouseExited(MouseEvent evt) {
            button.setForeground(Color.WHITE);
            button.setFont(button.getFont().deriveFont(Font.PLAIN));
        }
    });
    
    return button;
}

    private void createAdminPanel() {
        adminPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(255, 105, 180), 
                    getWidth(), getHeight(), new Color(100, 149, 237));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setOpaque(false);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.BLACK);
        userLabel.setFont(new Font("Arial",Font.BOLD, 35));
        JTextField userField = new JTextField();
        
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.BLACK);
        passLabel.setFont(new Font("Arial",Font.BOLD, 35));
        JPasswordField passField = new JPasswordField();
        
        JButton loginButton = createGradientButton("Login", new Color(100, 149, 237), new Color(255, 105, 180));
        loginButton.setFont(new Font("Arial",Font.BOLD, 35));
        JButton backButton = createGradientButton("Back", new Color(255, 50, 50), 
        new Color(255, 215, 0));
        backButton.setFont(new Font("Arial",Font.BOLD, 35));

        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(backButton);
        loginPanel.add(loginButton);

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "home"));
        
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            
            if(username.equals("@rungtauniversity") && password.equals("admin")) {
                cardLayout.show(mainPanel, "books");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        adminPanel.add(loginPanel, BorderLayout.CENTER);
    }

    private void createBookPanel() {
        bookPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(255, 105, 180), 
                    getWidth(), getHeight(), new Color(100, 149, 237));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Create table model and table
        tableModel = new BookTableModel();
        tableModel.setBooks(books);
        bookTable = new JTable(tableModel);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Create control panel
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setOpaque(false);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        searchField = new JTextField(20);
        JButton searchButton = createGradientButton("Search", new Color(100, 149, 237), new Color(255, 105, 180));
        JButton clearSearchButton = createGradientButton("Clear", new Color(255, 50, 50), new Color(255, 215, 0));
        
        searchButton.addActionListener(e -> searchBooks());
        clearSearchButton.addActionListener(e -> {
            searchField.setText("");
            tableModel.setBooks(books);
        });

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearSearchButton);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 7, 5, 5));
        buttonPanel.setOpaque(false);
        
        JButton addButton = createGradientButton("Add Book", new Color(100, 149, 237), new Color(255, 105, 180));
        JButton deleteButton = createGradientButton("Delete Book", new Color(255, 50, 50), new Color(255, 215, 0));
        JButton lendButton = createGradientButton("Lend Book", new Color(100, 149, 237), new Color(255, 105, 180));
        JButton returnButton = createGradientButton("Return Book", new Color(100, 149, 237), new Color(255, 105, 180));
        JButton exportButton = createGradientButton("Export CSV", new Color(255, 215, 0), new Color(255, 50, 50));
        JButton importButton = createGradientButton("Import CSV", new Color(255, 215, 0), new Color(255, 50, 50));
        JButton backButton = createGradientButton("Back", new Color(255, 50, 50), new Color(255, 215, 0));

        addButton.addActionListener(e -> showAddBookDialog());
        deleteButton.addActionListener(e -> deleteBook());
        lendButton.addActionListener(e -> lendBook());
        returnButton.addActionListener(e -> returnBook());
        exportButton.addActionListener(e -> exportToCSV());
        importButton.addActionListener(e -> importFromCSV());
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "admin"));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(lendButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(importButton);
        buttonPanel.add(backButton);

        controlPanel.add(searchPanel, BorderLayout.NORTH);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        bookPanel.add(controlPanel, BorderLayout.NORTH);
        bookPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createStudentPanel() {
        studentPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(100, 149, 237), 
                    getWidth(), getHeight(), new Color(255, 105, 180));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Create table model and table for student view
        studentTableModel = new StudentBookTableModel();
        studentTableModel.setBooks(books);
        studentBookTable = new JTable(studentTableModel);
        studentBookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(studentBookTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Create control panel for student
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setOpaque(false);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Search panel for student
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        studentSearchField = new JTextField(20);
        JButton searchButton = createGradientButton("Search", new Color(100, 149, 237), new Color(255, 105, 180));
        JButton clearSearchButton = createGradientButton("Clear", new Color(255, 50, 50), new Color(255, 215, 0));
        
        searchButton.addActionListener(e -> searchStudentBooks());
        clearSearchButton.addActionListener(e -> {
            studentSearchField.setText("");
            studentTableModel.setBooks(books);
        });

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(studentSearchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearSearchButton);

        // Button panel for student
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonPanel.setOpaque(false);
        
        JButton viewButton = createGradientButton("View Details", new Color(100, 149, 237), new Color(255, 105, 180));
        JButton borrowButton = createGradientButton("Borrow Book", new Color(100, 149, 237), new Color(255, 105, 180));
        JButton backButton = createGradientButton("Back", new Color(255, 50, 50), new Color(255, 215, 0));

        viewButton.addActionListener(e -> viewBookDetails());
        borrowButton.addActionListener(e -> borrowBookAsStudent());
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "home"));

        buttonPanel.add(viewButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(backButton);

        controlPanel.add(searchPanel, BorderLayout.NORTH);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        studentPanel.add(controlPanel, BorderLayout.NORTH);
        studentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void showAddBookDialog() {
        JDialog dialog = new JDialog(frame, "Add New Book", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField();
        JLabel erpLabel = new JLabel("ERP ID (7 digits):");
        erpField = new JTextField();
        JLabel semesterLabel = new JLabel("Semester:");
        
        String[] semesters = new String[8];
        for (int i = 0; i < 8; i++) {
            semesters[i] = "Semester " + (i + 1);
        }
        semesterCombo = new JComboBox<>(semesters);

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        addButton.addActionListener(e -> {
            if (validateBookInput()) {
                addBook();
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(erpLabel);
        panel.add(erpField);
        panel.add(semesterLabel);
        panel.add(semesterCombo);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(addButton);
        panel.add(cancelButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private boolean validateBookInput() {
        if (titleField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter book title", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (authorField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter author name", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!erpField.getText().matches("\\d{7}")) {
            JOptionPane.showMessageDialog(frame, "ERP ID must be 7 digits", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void addBook() {
        Book book = new Book(
            titleField.getText(),
            authorField.getText(),
            erpField.getText(),
            (String) semesterCombo.getSelectedItem(),
            "Available",
            "",
            "",
            ""
        );
        books.add(book);
        tableModel.setBooks(books);
        studentTableModel.setBooks(books);
        
        titleField.setText("");
        authorField.setText("");
        erpField.setText("");
        semesterCombo.setSelectedIndex(0);
    }

    private void deleteBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            frame, 
            "Are you sure you want to delete this book?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            books.remove(selectedRow);
            tableModel.setBooks(books);
            studentTableModel.setBooks(books);
        }
    }

    private void lendBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to lend", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book book = books.get(selectedRow);
        if (!book.getStatus().equals("Available")) {
            JOptionPane.showMessageDialog(frame, "This book is not available for lending", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String borrower = JOptionPane.showInputDialog(frame, "Enter borrower name:");
        if (borrower == null || borrower.trim().isEmpty()) {
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lendDate = sdf.format(new Date());
        
        // Calculate return date (14 days from now)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 14);
        String returnDate = sdf.format(cal.getTime());

        book.setStatus("Borrowed");
        book.setBorrower(borrower);
        book.setLendDate(lendDate);
        book.setReturnDate(returnDate);
        
        tableModel.setBooks(books);
        studentTableModel.setBooks(books);
    }

    private void returnBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to return", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book book = books.get(selectedRow);
        if (!book.getStatus().equals("Borrowed")) {
            JOptionPane.showMessageDialog(frame, "This book is not currently borrowed", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        book.setStatus("Available");
        book.setBorrower("");
        book.setLendDate("");
        book.setReturnDate("");
        
        tableModel.setBooks(books);
        studentTableModel.setBooks(books);
    }

    private void searchBooks() {
        String query = searchField.getText().toLowerCase();
        if (query.isEmpty()) {
            tableModel.setBooks(books);
            return;
        }

        ArrayList<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query) ||
                book.getAuthor().toLowerCase().contains(query) ||
                book.getErpId().contains(query) ||
                book.getSemester().toLowerCase().contains(query) ||
                book.getStatus().toLowerCase().contains(query) ||
                book.getBorrower().toLowerCase().contains(query)) {
                results.add(book);
            }
        }
        
        tableModel.setBooks(results);
    }

    private void searchStudentBooks() {
        String query = studentSearchField.getText().toLowerCase();
        if (query.isEmpty()) {
            studentTableModel.setBooks(books);
            return;
        }

        ArrayList<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query) ||
                book.getAuthor().toLowerCase().contains(query) ||
                book.getErpId().contains(query) ||
                book.getSemester().toLowerCase().contains(query)) {
                results.add(book);
            }
        }
        
        studentTableModel.setBooks(results);
    }

    private void viewBookDetails() {
        int selectedRow = studentBookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to view details", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book book = books.get(selectedRow);
        String message = "Title: " + book.getTitle() + "\n" +
                         "Author: " + book.getAuthor() + "\n" +
                         "ERP ID: " + book.getErpId() + "\n" +
                         "Semester: " + book.getSemester() + "\n" +
                         "Status: " + book.getStatus();
        
        if (book.getStatus().equals("Borrowed")) {
            message += "\nBorrowed by: " + book.getBorrower() + 
                       "\nBorrowed on: " + book.getLendDate() +
                       "\nReturn by: " + book.getReturnDate();
        }

        JOptionPane.showMessageDialog(frame, message, "Book Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void borrowBookAsStudent() {
        int selectedRow = studentBookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to borrow", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book book = books.get(selectedRow);
        if (!book.getStatus().equals("Available")) {
            JOptionPane.showMessageDialog(frame, "This book is not available for borrowing", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String erpId = JOptionPane.showInputDialog(frame, "Enter your 7-digit ERP ID:");
        if (erpId == null || !erpId.matches("\\d{7}")) {
            JOptionPane.showMessageDialog(frame, "Invalid ERP ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lendDate = sdf.format(new Date());
        
        // Calculate return date (14 days from now)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 14);
        String returnDate = sdf.format(cal.getTime());

        book.setStatus("Borrowed");
        book.setBorrower("Student " + erpId);
        book.setLendDate(lendDate);
        book.setReturnDate(returnDate);
        
        studentTableModel.setBooks(books);
        tableModel.setBooks(books);
        
        JOptionPane.showMessageDialog(frame, 
            "Book borrowed successfully!\nReturn by: " + returnDate, 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportToCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export to CSV");
        int userSelection = fileChooser.showSaveDialog(frame);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave + ".csv"))) {
                writer.println("Title,Author,ERP ID,Semester,Status,Borrower,Lend Date,Return Date");
                for (Book book : books) {
                    writer.println(String.format("\"%s\",\"%s\",%s,%s,%s,\"%s\",%s,%s",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getErpId(),
                        book.getSemester(),
                        book.getStatus(),
                        book.getBorrower(),
                        book.getLendDate(),
                        book.getReturnDate()));
                }
                JOptionPane.showMessageDialog(frame, "Data exported successfully to " + fileToSave.getName() + ".csv");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error exporting data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void importFromCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Import from CSV");
        int userSelection = fileChooser.showOpenDialog(frame);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
                books.clear();
                // Skip header
                reader.readLine();
                
                String line;
                while ((line = reader.readLine()) != null) {
                    // Handle quoted fields with commas
                    String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    
                    if (parts.length >= 8) {
                        // Remove quotes from fields
                        String title = parts[0].replaceAll("^\"|\"$", "");
                        String author = parts[1].replaceAll("^\"|\"$", "");
                        String erpId = parts[2];
                        String semester = parts[3];
                        String status = parts[4];
                        String borrower = parts[5].replaceAll("^\"|\"$", "");
                        String lendDate = parts[6];
                        String returnDate = parts[7];
                        
                        books.add(new Book(title, author, erpId, semester, status, borrower, lendDate, returnDate));
                    }
                }
                
                tableModel.setBooks(books);
                studentTableModel.setBooks(books);
                JOptionPane.showMessageDialog(frame, "Data imported successfully from " + fileToOpen.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error importing data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new LibraryManagementSystem();
        });
    }

    class Book {
        private String title;
        private String author;
        private String erpId;
        private String semester;
        private String status;
        private String borrower;
        private String lendDate;
        private String returnDate;

        public Book(String title, String author, String erpId, String semester, String status, 
                    String borrower, String lendDate, String returnDate) {
            this.title = title;
            this.author = author;
            this.erpId = erpId;
            this.semester = semester;
            this.status = status;
            this.borrower = borrower;
            this.lendDate = lendDate;
            this.returnDate = returnDate;
        }

        // Getters
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getErpId() { return erpId; }
        public String getSemester() { return semester; }
        public String getStatus() { return status; }
        public String getBorrower() { return borrower; }
        public String getLendDate() { return lendDate; }
        public String getReturnDate() { return returnDate; }

        // Setters
        public void setStatus(String status) { this.status = status; }
        public void setBorrower(String borrower) { this.borrower = borrower; }
        public void setLendDate(String lendDate) { this.lendDate = lendDate; }
        public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    }

    class BookTableModel extends AbstractTableModel {
        private ArrayList<Book> books;
        private String[] columnNames = {"Title", "Author", "ERP ID", "Semester", "Status", "Borrower", "Lend Date", "Return Date"};

        public BookTableModel() {
            books = new ArrayList<>();
        }

        public void setBooks(ArrayList<Book> books) {
            this.books = books;
            fireTableDataChanged();
        }

        public int getRowCount() {
            return books.size();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public String getColumnName(int column) {
            return columnNames[column];
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Book book = books.get(rowIndex);
            switch (columnIndex) {
                case 0: return book.getTitle();
                case 1: return book.getAuthor();
                case 2: return book.getErpId();
                case 3: return book.getSemester();
                case 4: return book.getStatus();
                case 5: return book.getBorrower();
                case 6: return book.getLendDate();
                case 7: return book.getReturnDate();
                default: return null;
            }
        }
    }

    class StudentBookTableModel extends AbstractTableModel {
        private ArrayList<Book> books;
        private String[] columnNames = {"Title", "Author", "ERP ID", "Semester", "Status"};

        public StudentBookTableModel() {
            books = new ArrayList<>();
        }

        public void setBooks(ArrayList<Book> books) {
            this.books = books;
            fireTableDataChanged();
        }

        public int getRowCount() {
            return books.size();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public String getColumnName(int column) {
            return columnNames[column];
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Book book = books.get(rowIndex);
            switch (columnIndex) {
                case 0: return book.getTitle();
                case 1: return book.getAuthor();
                case 2: return book.getErpId();
                case 3: return book.getSemester();
                case 4: return book.getStatus();
                default: return null;
            }
        }
    }
}