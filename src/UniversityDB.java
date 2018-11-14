import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class UniversityDB extends JFrame {

  public static void main(String[] args) throws Exception {
    //Log into sqlplus:
    //sqlplus
    //sys as sysdba
    //Seraphim97
    
    
    
    
    
    String[] userTypes = {"Staff", "Faculty", "Student"};
    
    
    
    
    //Main frame
    
    JFrame frame = new JFrame("CS430 Project");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1200, 800);
    
    //Container contentPane = frame.getContentPane();
    //contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
    
    //End main frame
    
    
    
    
    
    
    
    
    //Login modal
    
    JDialog dialog = new JDialog(frame, "Login", true);
    dialog.setSize(400, 200);
    
    Container dialogPane = dialog.getContentPane();
    dialogPane.setLayout(new FlowLayout());
    
    JLabel label1 = new JLabel("User type: ");
    dialog.add(label1);
    
    JComboBox userList = new JComboBox(userTypes);
    userList.setSelectedIndex(0);
    dialog.add(userList);
    
    JLabel label2 = new JLabel("ID #: ");
    dialog.add(label2);
    
    JPasswordField passLogin = new JPasswordField(20);
    dialog.add(passLogin);
    JButton submitLogin = new JButton("Login");
    dialog.add(submitLogin);
    
    //End login modal
    
    
    
    
    
    
    
    
    //Search records modal
    
    JDialog searchDialog = new JDialog(frame, "Search database...", true);
    searchDialog.setSize(400, 200);
    
    Container searchDialogPane = searchDialog.getContentPane();
    searchDialogPane.setLayout(new FlowLayout());
    
    //End search records modal
    
    
    
    
    
    
    
    //Menu bar
    JMenuBar menu = new JMenuBar();
    JLabel mlabel1 = new JLabel(" Create records:  ");
    menu.add(mlabel1);
    JButton createStaffBtn = new JButton("Add staff");
    menu.add(createStaffBtn);
    JButton createFacultyBtn = new JButton("Add faculty");
    menu.add(createFacultyBtn);
    JButton createStudentBtn = new JButton("Add student");
    menu.add(createStudentBtn);
    
    JLabel mlabel3 = new JLabel("     Modify records:  ");
    menu.add(mlabel3);
    JButton m2 = new JButton("Delete");
    menu.add(m2);
    JButton m3 = new JButton("Update");
    menu.add(m3);
    JLabel mlabel2 = new JLabel("     Search:  ");
    menu.add(mlabel2);
    JButton searchUniBtn = new JButton("Search university");
    menu.add(searchUniBtn);
    JButton searchCoursesBtn = new JButton("Search courses");
    menu.add(searchCoursesBtn);

    //Main text area
    JTextArea textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    textArea.setEditable(false);
    textArea.setFont(new Font("monospaced", Font.PLAIN, 14));
    textArea.getCaret().setVisible(true);
    textArea.setCaretPosition(textArea.getDocument().getLength());
    
    textArea.setText("Welcome to Test University's dashboard! All events will be logged to this panel.\nClick any button on the toolbar to perform the listed action.\nYou will be prompted to enter your university ID each time you request a service.");
    
    
    //Mock database
    MockDB mockdb = new MockDB();
    String[] acc1 = new String[]{"Staff", "abc", "admin"};
    String[] acc2 = new String[]{"zach", "abc", "staff"};
    String[] acc3 = new String[]{"justin", "abc", "staff"};
    String[] acc4 = new String[]{"luigi", "xyz", "customer"};
    String[] acc5 = new String[]{"sam", "abc", "customer"};
    mockdb.accounts.add(acc1);
    mockdb.accounts.add(acc2);
    mockdb.accounts.add(acc3);
    mockdb.accounts.add(acc4);
    mockdb.accounts.add(acc5);
    
    
    
    
    
    
    
    //SQL connection stuff
    ConnectSQL con = new ConnectSQL();
    Connection connect = con.getMySqlConnection();
    System.out.println("Connected to database");
    
    PreparedStatement stmt = connect.prepareStatement("select * from Student");
    ResultSet rs = stmt.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    
    //rs.next();
    //String foundType1 = rs.getString(1);
    //String foundType2 = rs.getString(2);
    //String foundType3 = rs.getString(3);
    //String foundType4 = rs.getString(4);
    //textArea.setText(foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    stmt.close();
    connect.close();
    
    //End SQL connection stuff
    
    
    
    QueryFactory queryFac = new QueryFactory();
    ArrayList<String[]> res = new ArrayList<String[]>();
    res = queryFac.searchDB("select * from Department");
    
    //System.out.println(res.get(0)[0] + " " + res.get(0)[1] + " " + res.get(0)[2]);
    for(int i = 0; i < res.size(); i++) {
      for(int j = 0; j < res.get(i).length; j++) {
        System.out.print(res.get(i)[j]);
      }
      System.out.println();
    }
    
    



    //Events
    submitLogin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean found = false;
        String pass = String.valueOf(passLogin.getPassword());
        
        for(int i = 0; i < mockdb.accounts.size(); i++) {
          if(mockdb.accounts.get(i)[0].equals(userList.getSelectedItem()) && mockdb.accounts.get(i)[1].equals(pass)) {
            found = true;
            break;
          }
        }
        
        if(found) {
          System.out.println("Logged in successfully!");
          dialog.setVisible(false);
        }
        else {
          System.out.println("Incorrect username or password.");
        }
      }
    });
    
    createStaffBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText(textArea.getText() + "\n" + "About");
      }
    });
    
    createFacultyBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText(textArea.getText() + "\n" + "About");
      }
    });
    
    createStudentBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText(textArea.getText() + "\n" + "About");
      }
    });
    
    m2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText(textArea.getText() + "\n" + "About");
      }
    });

    m3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText(textArea.getText() + "\n" + "Interests");
      }
    });
    
    
    
    searchUniBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        
        JDialog searchUni = new JDialog(frame, "Search university records...", true);
        searchUni.setSize(400, 200);
        searchUni.setLayout(new GridLayout(0,1));
        
        JPanel nested1 = new JPanel();
        JPanel nested2 = new JPanel();
        JPanel nested3 = new JPanel();
        
        
        JLabel searchAsLbl = new JLabel("Search as: ");
        nested1.add(searchAsLbl);
        JComboBox searchAsCombo = new JComboBox(userTypes);
        searchAsCombo.setSelectedIndex(0);
        nested1.add(searchAsCombo);
        JLabel label2 = new JLabel("     ID #: ");
        nested1.add(label2);
        JTextField searchField = new JTextField(10);
        nested1.add(searchField);
        
        JLabel searchForLbl = new JLabel("Search for: ");
        nested2.add(searchForLbl);
        JComboBox searchForCombo = new JComboBox(userTypes);
        searchForCombo.setSelectedIndex(0);
        nested2.add(searchForCombo);
        JLabel label3 = new JLabel("     ID #: ");
        nested2.add(label3);
        JTextField searchField2 = new JTextField(10);
        nested2.add(searchField2);
        
        JButton searchSubmit = new JButton("Search");
        nested3.add(searchSubmit);
        
        searchUni.add(nested1);
        searchUni.add(nested2);
        searchUni.add(nested3);
        
        searchSubmit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.out.println("University records search submitted!");
            
            ArrayList<String[]> uniSearchRes = new ArrayList<String[]>();
            try {
              uniSearchRes = queryFac.searchDB("select * from Department");
            } catch (Exception e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
            }
            
            for(int i = 0; i < uniSearchRes.size(); i++) {
              textArea.setText(textArea.getText() + "\n");
              for(int j = 0; j < uniSearchRes.get(i).length; j++) {
                //System.out.print(uniSearchRes.get(i)[j]);
                textArea.setText(textArea.getText() + uniSearchRes.get(i)[j]);
              }
              //System.out.println();
            }
            
          }
        });
        
        searchUni.pack();
        searchUni.setLocationRelativeTo(frame);
        searchUni.setVisible(true);
        nested1.setVisible(true);
        nested2.setVisible(true);
        nested3.setVisible(true);
        
      }
    });
    
    
    
    searchCoursesBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        
        JDialog searchCourses = new JDialog(frame, "Search course list...", true);
        searchCourses.setSize(400, 200);
        searchCourses.setLayout(new GridLayout(0,1));
        
        JPanel nested1 = new JPanel();
        JPanel nested2 = new JPanel();
        JPanel nested3 = new JPanel();
        
        
        JLabel searchAsLbl = new JLabel("Search as: ");
        nested1.add(searchAsLbl);
        JComboBox searchAsCombo = new JComboBox(userTypes);
        searchAsCombo.setSelectedIndex(0);
        nested1.add(searchAsCombo);
        
        JLabel label2 = new JLabel("ID #: ");
        nested2.add(label2);
        JTextField searchField = new JTextField(20);
        nested2.add(searchField);
        
        JButton searchSubmit = new JButton("Search");
        nested3.add(searchSubmit);
        
        searchCourses.add(nested1);
        searchCourses.add(nested2);
        searchCourses.add(nested3);
        
        searchCourses.pack();
        searchCourses.setLocationRelativeTo(frame);
        searchCourses.setVisible(true);
        nested1.setVisible(true);
        nested2.setVisible(true);
        nested3.setVisible(true);
      }
    });




    //Set layout
    frame.getContentPane().add(BorderLayout.NORTH, menu);
    frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    
    dialog.pack();
    dialog.setLocationRelativeTo(frame);
    dialog.setVisible(true);

  }
  
  
}
