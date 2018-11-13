import java.awt.*;
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
    rs.next();
    String foundType1 = rs.getString(1);
    String foundType2 = rs.getString(2);
    String foundType3 = rs.getString(3);
    String foundType4 = rs.getString(4);
    textArea.setText(foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    rs.next();
    foundType1 = rs.getString(1);
    foundType2 = rs.getString(2);
    foundType3 = rs.getString(3);
    foundType4 = rs.getString(4);
    textArea.setText(textArea.getText() + "\n" + foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    rs.next();
    foundType1 = rs.getString(1);
    foundType2 = rs.getString(2);
    foundType3 = rs.getString(3);
    foundType4 = rs.getString(4);
    textArea.setText(textArea.getText() + "\n" + foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    rs.next();
    foundType1 = rs.getString(1);
    foundType2 = rs.getString(2);
    foundType3 = rs.getString(3);
    foundType4 = rs.getString(4);
    textArea.setText(textArea.getText() + "\n" + foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    rs.next();
    foundType1 = rs.getString(1);
    foundType2 = rs.getString(2);
    foundType3 = rs.getString(3);
    foundType4 = rs.getString(4);
    textArea.setText(textArea.getText() + "\n" + foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    rs.next();
    foundType1 = rs.getString(1);
    foundType2 = rs.getString(2);
    foundType3 = rs.getString(3);
    foundType4 = rs.getString(4);
    textArea.setText(textArea.getText() + "\n" + foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    rs.next();
    foundType1 = rs.getString(1);
    foundType2 = rs.getString(2);
    foundType3 = rs.getString(3);
    foundType4 = rs.getString(4);
    textArea.setText(textArea.getText() + "\n" + foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    rs.next();
    foundType1 = rs.getString(1);
    foundType2 = rs.getString(2);
    foundType3 = rs.getString(3);
    foundType4 = rs.getString(4);
    textArea.setText(textArea.getText() + "\n" + foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    rs.next();
    foundType1 = rs.getString(1);
    foundType2 = rs.getString(2);
    foundType3 = rs.getString(3);
    foundType4 = rs.getString(4);
    textArea.setText(textArea.getText() + "\n" + foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    rs.next();
    foundType1 = rs.getString(1);
    foundType2 = rs.getString(2);
    foundType3 = rs.getString(3);
    foundType4 = rs.getString(4);
    textArea.setText(textArea.getText() + "\n" + foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    
    //System.out.println(foundType1 + " " + foundType2 + " " + foundType3 + " " + foundType4);
    //displayResults(rs, rsmd);
    stmt.close();
    connect.close();
    
    //End SQL connection stuff
    
    
    
    
    



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




    //Set layout
    frame.getContentPane().add(BorderLayout.NORTH, menu);
    frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    
    dialog.pack();
    dialog.setLocationRelativeTo(frame);
    dialog.setVisible(true);

  }
  
  
  
  
  public static void displayResults(ResultSet rs, ResultSetMetaData rsmd) throws SQLException{
    String result=new String();
        int numberofcolumn =rsmd.getColumnCount();
        System.out.println("number of columns= " + numberofcolumn );
        String columnnames=new String("");
        
        for(int i=1;i<=numberofcolumn;i++) /*for loop needs to from  1 not 0*/
        {
           String name=rsmd.getColumnName(i);
           columnnames=columnnames+"\t"+name;
        }
        
        result+=columnnames;
        result+="\n";

        /*Read the result*/
        /*rs.next() will return true when the result set still  * contains next row*/
         while (rs.next())
        {
         /*Read each field of the row, and the for loop also begin  * with 1*/
          for(int i=1;i<=numberofcolumn;i++)
           {
              String s=rs.getString(i);
              result+="\t"+s;
           }
          result+="\n";
        }
        System.err.println("\n"+result);
  }
  
  
}
