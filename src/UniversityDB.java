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
    String[] departmentTypes = {"Computer Science", "Computer Engineering", "Electrical Engineering", "Physics", "Mathematics", "Chemistry", "Human Physiology", "Biology", "Zoology", "Geology"};
    
    
    
    
    //Main frame
    
    JFrame frame = new JFrame("CS430 Project");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1200, 800);
    
    //Container contentPane = frame.getContentPane();
    //contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
    
    //End main frame
    
    
    
    
    
    
    
    
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
    JButton deleteRecordBtn = new JButton("Delete");
    menu.add(deleteRecordBtn);
    JButton updateRecordBtn = new JButton("Update");
    menu.add(updateRecordBtn);
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
    textArea.setText(textArea.getText() + "\n");
    
    
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
    //ArrayList<String[]> res = new ArrayList<String[]>();
    //res = queryFac.searchDB("select * from Department");
    
    /*
    //System.out.println(res.get(0)[0] + " " + res.get(0)[1] + " " + res.get(0)[2]);
    for(int i = 0; i < res.size(); i++) {
      for(int j = 0; j < res.get(i).length; j++) {
        System.out.print(res.get(i)[j]);
      }
      System.out.println();
    }
    */
    
    
    //Events
    
    createStaffBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JDialog addStaff = new JDialog(frame, "Add staff record...", true);
        addStaff.setSize(400, 200);
        addStaff.setLayout(new GridLayout(0,1));
        
        JPanel nested1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel nested3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel confStaffIdLbl = new JLabel("Confirm staff ID: ");
        nested1.add(confStaffIdLbl);
        JTextField confStaffIdField = new JTextField(10);
        nested1.add(confStaffIdField);
        
        JLabel newEntryLbl = new JLabel("~ - ~ - ~ NEW STAFF ENTRY ~ - ~ - ~");
        nested2.add(newEntryLbl);
        
        JLabel newIdLbl = new JLabel("ID: ");
        nested3.add(newIdLbl);
        JTextField newIdField = new JTextField(10);
        nested3.add(newIdField);
        
        JLabel newNameLbl = new JLabel("Name: ");
        nested4.add(newNameLbl);
        JTextField newNameField = new JTextField(10);
        nested4.add(newNameField);
        
        JLabel newAgeLbl = new JLabel("Age: ");
        nested5.add(newAgeLbl);
        JTextField newAgeField = new JTextField(10);
        nested5.add(newAgeField);
        
        JLabel newDeptLbl = new JLabel("Dept #: ");
        nested6.add(newDeptLbl);
        JTextField newDeptField = new JTextField(10);
        nested6.add(newDeptField);
        
        JButton addStaffSubmit = new JButton("Add record");
        nested7.add(addStaffSubmit);
        
        
        //confStaffIdField.setText("00001");
        //newIdField.setText("00015");
        //newNameField.setText("First");
        //newAgeField.setText("30");
        //newDeptField.setText("5");
        
        addStaff.add(nested1);
        addStaff.add(nested2);
        addStaff.add(nested3);
        addStaff.add(nested4);
        addStaff.add(nested5);
        addStaff.add(nested6);
        addStaff.add(nested7);
        
        addStaffSubmit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            
            ArrayList<String[]> uniSearchRes = new ArrayList<String[]>();
            uniSearchRes = null;
            String identifiers = null;
            int result = 0;
            
            //Confirms ID of user
            try {
              uniSearchRes = queryFac.searchDB("select id from Staff where id=" + confStaffIdField.getText());
            } catch (Exception e1) {
              // TODO Auto-generated catch block
              //e1.printStackTrace();
            }
            
            //Checks if query result is empty or null
            if(uniSearchRes != null && !uniSearchRes.isEmpty() && !newIdField.getText().isEmpty() && !newNameField.getText().isEmpty() && !newAgeField.getText().isEmpty() && !newDeptField.getText().isEmpty()) {
              String idFieldStr = newIdField.getText();
              String nameFieldStr = newNameField.getText();
              String ageFieldStr = newAgeField.getText();
              String deptFieldStr = newDeptField.getText();
              
              //Proceed with insertion
              try {
                String addQuery = "insert into Staff values ('" + idFieldStr + "','" + nameFieldStr + "'," + ageFieldStr + "," + deptFieldStr + ")";
                result = queryFac.addRecordToDB(addQuery);
              } catch (Exception e2) {
                // TODO Auto-generated catch block
                //e2.printStackTrace();
              }
              
              System.out.println(result);
              if(result != 0) {
                //Echoes results of above addition to the program log
                textArea.setText(textArea.getText() + "\n" + "----------------------------");
                textArea.setText(textArea.getText() + "\n" + "New staff added!");
                textArea.setText(textArea.getText() + "\n" + "----------------------------");
                textArea.setText(textArea.getText() + "\n" + "ID: " + idFieldStr);
                textArea.setText(textArea.getText() + "\n" + "Name: " + nameFieldStr);
                textArea.setText(textArea.getText() + "\n" + "Age: " + ageFieldStr);
                textArea.setText(textArea.getText() + "\n" + "Department: " + departmentTypes[Integer.parseInt(deptFieldStr) - 1]);
                textArea.setText(textArea.getText() + "\n");
              }
              else {
                //Throw error notif at user
                JDialog notif = new JDialog(frame, "Attention!", true);
                notif.setSize(300, 150);
                notif.setLayout(new GridLayout(0,1));
                JPanel notifNested1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JLabel notifLbl1 = new JLabel("     Illegal insertion field!     ");
                notifNested1.add(notifLbl1);
                
                notif.add(notifNested1);
                //notif.pack();
                notif.setLocationRelativeTo(addStaff);
                notif.setVisible(true);
              }
              
            }
            else {
              //Throw error notif at user
              JDialog notif = new JDialog(frame, "Attention!", true);
              notif.setSize(300, 150);
              notif.setLayout(new GridLayout(0,1));
              JPanel notifNested1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
              JLabel notifLbl1 = new JLabel("     You are not authorized to do that!     ");
              notifNested1.add(notifLbl1);
              
              notif.add(notifNested1);
              //notif.pack();
              notif.setLocationRelativeTo(addStaff);
              notif.setVisible(true);
            }
            
          }
        });
        
        addStaff.pack();
        addStaff.setLocationRelativeTo(frame);
        addStaff.setVisible(true);
        nested1.setVisible(true);
        nested2.setVisible(true);
        nested3.setVisible(true);
      }
    });
    
    
    
    createFacultyBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JDialog addFaculty = new JDialog(frame, "Add faculty record...", true);
        addFaculty.setSize(400, 200);
        addFaculty.setLayout(new GridLayout(0,1));
        
        JPanel nested1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel nested3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested7 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel confStaffIdLbl = new JLabel("Confirm staff ID: ");
        nested1.add(confStaffIdLbl);
        JTextField confStaffIdField = new JTextField(10);
        nested1.add(confStaffIdField);
        
        JLabel newEntryLbl = new JLabel("~ - ~ - ~ NEW FACULTY ENTRY ~ - ~ - ~");
        nested2.add(newEntryLbl);
        
        JLabel newIdLbl = new JLabel("ID: ");
        nested3.add(newIdLbl);
        JTextField newIdField = new JTextField(10);
        nested3.add(newIdField);
        
        JLabel newNameLbl = new JLabel("Name: ");
        nested4.add(newNameLbl);
        JTextField newNameField = new JTextField(10);
        nested4.add(newNameField);
        
        JLabel newAgeLbl = new JLabel("Age: ");
        nested5.add(newAgeLbl);
        JTextField newAgeField = new JTextField(10);
        nested5.add(newAgeField);
        
        JLabel newDeptLbl = new JLabel("Dept #: ");
        nested6.add(newDeptLbl);
        JTextField newDeptField = new JTextField(10);
        nested6.add(newDeptField);
        
        JLabel newSpecialtyLbl = new JLabel("Research area: ");
        nested7.add(newSpecialtyLbl);
        JTextField newSpecialtyField = new JTextField(10);
        nested7.add(newSpecialtyField);
        
        JButton addFacultySubmit = new JButton("Add record");
        nested8.add(addFacultySubmit);
        
        
        //confStaffIdField.setText("00001");
        //newIdField.setText("00015");
        //newNameField.setText("First");
        //newAgeField.setText("30");
        //newDeptField.setText("5");
        //newSpecialtyField.setText("Cloud computing");
        
        addFaculty.add(nested1);
        addFaculty.add(nested2);
        addFaculty.add(nested3);
        addFaculty.add(nested4);
        addFaculty.add(nested5);
        addFaculty.add(nested6);
        addFaculty.add(nested7);
        addFaculty.add(nested8);
        
        addFacultySubmit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            
            ArrayList<String[]> uniSearchRes = new ArrayList<String[]>();
            uniSearchRes = null;
            String identifiers = null;
            int result = 0;
            
            //Confirms ID of user
            try {
              uniSearchRes = queryFac.searchDB("select id from Staff where id=" + confStaffIdField.getText());
            } catch (Exception e1) {
              // TODO Auto-generated catch block
              //e1.printStackTrace();
            }
            
            //Checks if query result is empty or null
            if(uniSearchRes != null && !uniSearchRes.isEmpty() && !newIdField.getText().isEmpty() && !newNameField.getText().isEmpty() && !newAgeField.getText().isEmpty() && !newDeptField.getText().isEmpty()) {
              String idFieldStr = newIdField.getText();
              String nameFieldStr = newNameField.getText();
              String ageFieldStr = newAgeField.getText();
              String deptFieldStr = newDeptField.getText();
              String specialtyFieldStr = newSpecialtyField.getText();
              
              //Proceed with insertion
              try {
                String addQuery = "insert into Faculty values ('" + idFieldStr + "','" + nameFieldStr + "'," + ageFieldStr + "," + deptFieldStr + ",'" + specialtyFieldStr + "')";
                result = queryFac.addRecordToDB(addQuery);
              } catch (Exception e2) {
                // TODO Auto-generated catch block
                //e2.printStackTrace();
              }
              
              System.out.println(result);
              if(result != 0) {
                //Echoes results of above addition to the program log
                textArea.setText(textArea.getText() + "\n" + "----------------------------");
                textArea.setText(textArea.getText() + "\n" + "New faculty added!");
                textArea.setText(textArea.getText() + "\n" + "----------------------------");
                textArea.setText(textArea.getText() + "\n" + "ID: " + idFieldStr);
                textArea.setText(textArea.getText() + "\n" + "Name: " + nameFieldStr);
                textArea.setText(textArea.getText() + "\n" + "Age: " + ageFieldStr);
                textArea.setText(textArea.getText() + "\n" + "Department: " + departmentTypes[Integer.parseInt(deptFieldStr) - 1]);
                textArea.setText(textArea.getText() + "\n" + "Research area: " + specialtyFieldStr);
                textArea.setText(textArea.getText() + "\n");
              }
              else {
                //Throw error notif at user
                JDialog notif = new JDialog(frame, "Attention!", true);
                notif.setSize(300, 150);
                notif.setLayout(new GridLayout(0,1));
                JPanel notifNested1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JLabel notifLbl1 = new JLabel("     Illegal insertion field!     ");
                notifNested1.add(notifLbl1);
                
                notif.add(notifNested1);
                //notif.pack();
                notif.setLocationRelativeTo(addFaculty);
                notif.setVisible(true);
              }
              
            }
            else {
              //Throw error notif at user
              JDialog notif = new JDialog(frame, "Attention!", true);
              notif.setSize(300, 150);
              notif.setLayout(new GridLayout(0,1));
              JPanel notifNested1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
              JLabel notifLbl1 = new JLabel("     You are not authorized to do that!     ");
              notifNested1.add(notifLbl1);
              
              notif.add(notifNested1);
              //notif.pack();
              notif.setLocationRelativeTo(addFaculty);
              notif.setVisible(true);
            }
            
          }
        });
        
        addFaculty.pack();
        addFaculty.setLocationRelativeTo(frame);
        addFaculty.setVisible(true);
        nested1.setVisible(true);
        nested2.setVisible(true);
        nested3.setVisible(true);
      }
    });
    
    
    
    createStudentBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JDialog addStudent = new JDialog(frame, "Add student record...", true);
        addStudent.setSize(400, 200);
        addStudent.setLayout(new GridLayout(0,1));
        
        JPanel nested1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel nested3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel confStaffIdLbl = new JLabel("Confirm staff ID: ");
        nested1.add(confStaffIdLbl);
        JTextField confStaffIdField = new JTextField(10);
        nested1.add(confStaffIdField);
        
        JLabel newEntryLbl = new JLabel("~ - ~ - ~ NEW STUDENT ENTRY ~ - ~ - ~");
        nested2.add(newEntryLbl);
        
        JLabel newIdLbl = new JLabel("ID: ");
        nested3.add(newIdLbl);
        JTextField newIdField = new JTextField(10);
        nested3.add(newIdField);
        
        JLabel newNameLbl = new JLabel("Name: ");
        nested4.add(newNameLbl);
        JTextField newNameField = new JTextField(10);
        nested4.add(newNameField);
        
        JLabel newAgeLbl = new JLabel("Age: ");
        nested5.add(newAgeLbl);
        JTextField newAgeField = new JTextField(10);
        nested5.add(newAgeField);
        
        JLabel newDeptLbl = new JLabel("Dept #: ");
        nested6.add(newDeptLbl);
        JTextField newDeptField = new JTextField(10);
        nested6.add(newDeptField);
        
        JButton addStudentSubmit = new JButton("Add record");
        nested7.add(addStudentSubmit);
        
        
        //confStaffIdField.setText("00001");
        //newIdField.setText("00015");
        //newNameField.setText("First");
        //newAgeField.setText("30");
        //newDeptField.setText("5");
        
        addStudent.add(nested1);
        addStudent.add(nested2);
        addStudent.add(nested3);
        addStudent.add(nested4);
        addStudent.add(nested5);
        addStudent.add(nested6);
        addStudent.add(nested7);
        
        addStudentSubmit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            
            ArrayList<String[]> uniSearchRes = new ArrayList<String[]>();
            uniSearchRes = null;
            String identifiers = null;
            int result = 0;
            
            //Confirms ID of user
            try {
              uniSearchRes = queryFac.searchDB("select id from Staff where id=" + confStaffIdField.getText());
            } catch (Exception e1) {
              // TODO Auto-generated catch block
              //e1.printStackTrace();
            }
            
            //Checks if query result is empty or null
            if(uniSearchRes != null && !uniSearchRes.isEmpty() && !newIdField.getText().isEmpty() && !newNameField.getText().isEmpty() && !newAgeField.getText().isEmpty() && !newDeptField.getText().isEmpty()) {
              String idFieldStr = newIdField.getText();
              String nameFieldStr = newNameField.getText();
              String ageFieldStr = newAgeField.getText();
              String deptFieldStr = newDeptField.getText();
              
              //Proceed with insertion
              try {
                String addQuery = "insert into Student values ('" + idFieldStr + "','" + nameFieldStr + "'," + ageFieldStr + "," + deptFieldStr + ")";
                result = queryFac.addRecordToDB(addQuery);
              } catch (Exception e2) {
                // TODO Auto-generated catch block
                //e2.printStackTrace();
              }
              
              System.out.println(result);
              if(result != 0) {
                //Echoes results of above addition to the program log
                textArea.setText(textArea.getText() + "\n" + "----------------------------");
                textArea.setText(textArea.getText() + "\n" + "New student added!");
                textArea.setText(textArea.getText() + "\n" + "----------------------------");
                textArea.setText(textArea.getText() + "\n" + "ID: " + idFieldStr);
                textArea.setText(textArea.getText() + "\n" + "Name: " + nameFieldStr);
                textArea.setText(textArea.getText() + "\n" + "Age: " + ageFieldStr);
                textArea.setText(textArea.getText() + "\n" + "Department: " + departmentTypes[Integer.parseInt(deptFieldStr) - 1]);
                textArea.setText(textArea.getText() + "\n");
              }
              else {
                //Throw error notif at user
                JDialog notif = new JDialog(frame, "Attention!", true);
                notif.setSize(300, 150);
                notif.setLayout(new GridLayout(0,1));
                JPanel notifNested1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JLabel notifLbl1 = new JLabel("     Illegal insertion field!     ");
                notifNested1.add(notifLbl1);
                
                notif.add(notifNested1);
                //notif.pack();
                notif.setLocationRelativeTo(addStudent);
                notif.setVisible(true);
              }
              
            }
            else {
              //Throw error notif at user
              JDialog notif = new JDialog(frame, "Attention!", true);
              notif.setSize(300, 150);
              notif.setLayout(new GridLayout(0,1));
              JPanel notifNested1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
              JLabel notifLbl1 = new JLabel("     You are not authorized to do that!     ");
              notifNested1.add(notifLbl1);
              
              notif.add(notifNested1);
              //notif.pack();
              notif.setLocationRelativeTo(addStudent);
              notif.setVisible(true);
            }
            
          }
        });
        
        addStudent.pack();
        addStudent.setLocationRelativeTo(frame);
        addStudent.setVisible(true);
        nested1.setVisible(true);
        nested2.setVisible(true);
        nested3.setVisible(true);
      }
    });
    
    
    
    deleteRecordBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JDialog addStudent = new JDialog(frame, "Delete record...", true);
        addStudent.setSize(400, 200);
        addStudent.setLayout(new GridLayout(0,1));
        
        JPanel nested1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel nested3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel confStaffIdLbl = new JLabel("Confirm staff ID: ");
        nested1.add(confStaffIdLbl);
        JTextField confStaffIdField = new JTextField(10);
        nested1.add(confStaffIdField);
        
        JLabel newEntryLbl = new JLabel("~ - ~ - ~ RECORD TO DELETE ~ - ~ - ~");
        nested2.add(newEntryLbl);
        
        JLabel accTypeLbl = new JLabel("Account type: ");
        nested3.add(accTypeLbl);
        JComboBox accTypeCombo = new JComboBox(userTypes);
        accTypeCombo.setSelectedIndex(0);
        nested3.add(accTypeCombo);
        
        JLabel newIdLbl = new JLabel("ID: ");
        nested4.add(newIdLbl);
        JTextField newIdField = new JTextField(10);
        nested4.add(newIdField);
        
        JLabel newNameLbl = new JLabel("Name: ");
        nested5.add(newNameLbl);
        JTextField newNameField = new JTextField(10);
        nested5.add(newNameField);
        
        JButton deleteRecordSubmit = new JButton("DELETE");
        nested6.add(deleteRecordSubmit);
        
        
        //confStaffIdField.setText("00001");
        //newIdField.setText("20015");
        //newNameField.setText("First");
        
        addStudent.add(nested1);
        addStudent.add(nested2);
        addStudent.add(nested3);
        addStudent.add(nested4);
        addStudent.add(nested5);
        addStudent.add(nested6);
        
        deleteRecordSubmit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            
            ArrayList<String[]> uniSearchRes = new ArrayList<String[]>();
            uniSearchRes = null;
            String identifiers = null;
            int result = 0;
            
            //Confirms ID of user
            try {
              uniSearchRes = queryFac.searchDB("select id from Staff where id=" + confStaffIdField.getText());
            } catch (Exception e1) {
              // TODO Auto-generated catch block
              //e1.printStackTrace();
            }
            
            //Checks if query result is empty or null
            if(uniSearchRes != null && !uniSearchRes.isEmpty() && !newIdField.getText().isEmpty() && !newNameField.getText().isEmpty()) {
              String comboStr = accTypeCombo.getSelectedItem().toString();
              String idFieldStr = newIdField.getText();
              String nameFieldStr = newNameField.getText();
              
              //Proceed with deletion
              try {
                String addQuery = "delete from " + comboStr + " where id='" + idFieldStr + "' and name='" + nameFieldStr + "'";
                System.out.println(addQuery);
                result = queryFac.addRecordToDB(addQuery);
              } catch (Exception e2) {
                // TODO Auto-generated catch block
                //e2.printStackTrace();
              }
              
              System.out.println("Rows affected by deletion: " + result);
              if(result != 0) {
                //Echoes results of above addition to the program log
                textArea.setText(textArea.getText() + "\n" + "----------------------------");
                textArea.setText(textArea.getText() + "\n" + "Record deleted!");
                textArea.setText(textArea.getText() + "\n" + "----------------------------");
              }
              else {
                //Throw error notif at user
                JDialog notif = new JDialog(frame, "Attention!", true);
                notif.setSize(300, 150);
                notif.setLayout(new GridLayout(0,1));
                JPanel notifNested1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JLabel notifLbl1 = new JLabel("     Illegal deletion field!     ");
                notifNested1.add(notifLbl1);
                
                notif.add(notifNested1);
                //notif.pack();
                notif.setLocationRelativeTo(addStudent);
                notif.setVisible(true);
              }
              
            }
            else {
              //Throw error notif at user
              JDialog notif = new JDialog(frame, "Attention!", true);
              notif.setSize(300, 150);
              notif.setLayout(new GridLayout(0,1));
              JPanel notifNested1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
              JLabel notifLbl1 = new JLabel("     You are not authorized to do that!     ");
              notifNested1.add(notifLbl1);
              
              notif.add(notifNested1);
              //notif.pack();
              notif.setLocationRelativeTo(addStudent);
              notif.setVisible(true);
            }
            
          }
        });
        
        addStudent.pack();
        addStudent.setLocationRelativeTo(frame);
        addStudent.setVisible(true);
        nested1.setVisible(true);
        nested2.setVisible(true);
        nested3.setVisible(true);
      }
    });

    
    
    updateRecordBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText(textArea.getText() + "\n" + "! Update feature is not currently supported !\n");
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
        String[] staffArr = new String[]{"Staff", "Faculty"};
        JComboBox searchAsCombo = new JComboBox(staffArr);
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
            uniSearchRes = null;
            String identifiers = null;
            
            //Confirms ID of user
            try {
              uniSearchRes = queryFac.searchDB("select id from " + searchAsCombo.getSelectedItem() + " where id=" + searchField.getText());
            } catch (Exception e2) {
              // TODO Auto-generated catch block
              //e2.printStackTrace();
            }
            
            if(uniSearchRes != null && !uniSearchRes.isEmpty()) {
              //Constructs query values based on user type
              if(searchForCombo.getSelectedItem().equals(userTypes[0]) || searchForCombo.getSelectedItem().equals(userTypes[2])) {
                identifiers = "id, name, age, dept";
              }
              else if(searchForCombo.getSelectedItem().equals(userTypes[1])) {
                identifiers = "id, name, age, dept, specialty";
              }
              
              if(!searchField2.getText().isEmpty()) {
                //Queries DB for staff/faculty/student info
                try {
                  uniSearchRes = queryFac.searchDB("select " + identifiers + " from " + searchForCombo.getSelectedItem() + " where id=" + searchField2.getText());
                } catch (Exception e1) {
                  // TODO Auto-generated catch block
                  //e1.printStackTrace();
                }
                
                //Echoes results of above query to the program log
                if((searchForCombo.getSelectedItem().equals(userTypes[0]) || searchForCombo.getSelectedItem().equals(userTypes[2])) && !uniSearchRes.isEmpty()) {
                  textArea.setText(textArea.getText() + "\n" + "----------------------------");
                  textArea.setText(textArea.getText() + "\n" + searchForCombo.getSelectedItem() + " - " + uniSearchRes.get(0)[1]);
                  textArea.setText(textArea.getText() + "\n" + "----------------------------");
                  textArea.setText(textArea.getText() + "\n" + "ID: " + uniSearchRes.get(0)[0]);
                  textArea.setText(textArea.getText() + "\n" + "Age: " + uniSearchRes.get(0)[2]);
                  textArea.setText(textArea.getText() + "\n" + "Department: " + departmentTypes[Integer.parseInt(uniSearchRes.get(0)[3]) - 1]);
                  textArea.setText(textArea.getText() + "\n");
                }
                else if(searchForCombo.getSelectedItem().equals(userTypes[1]) && !uniSearchRes.isEmpty()) {
                  textArea.setText(textArea.getText() + "\n" + "----------------------------");
                  textArea.setText(textArea.getText() + "\n" + searchForCombo.getSelectedItem() + " - " + uniSearchRes.get(0)[1]);
                  textArea.setText(textArea.getText() + "\n" + "----------------------------");
                  textArea.setText(textArea.getText() + "\n" + "ID: " + uniSearchRes.get(0)[0]);
                  textArea.setText(textArea.getText() + "\n" + "Age: " + uniSearchRes.get(0)[2]);
                  textArea.setText(textArea.getText() + "\n" + "Department: " + departmentTypes[Integer.parseInt(uniSearchRes.get(0)[3]) - 1]);
                  textArea.setText(textArea.getText() + "\n" + "Research area: " + uniSearchRes.get(0)[4]);
                  textArea.setText(textArea.getText() + "\n");
                }
              }
              
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
        JDialog searchCourses = new JDialog(frame, "Search available courses...", true);
        searchCourses.setSize(400, 200);
        searchCourses.setLayout(new GridLayout(0,1));
        
        JPanel nested1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel nested2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel nested4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        
        JLabel findCourseLbl = new JLabel("~ - ~ - ~ FIND A COURSE ~ - ~ - ~");
        nested1.add(findCourseLbl);
        
        JLabel searchWithinLbl = new JLabel("Search within: ");
        nested2.add(searchWithinLbl);
        JComboBox searchWithinCombo = new JComboBox(departmentTypes);
        searchWithinCombo.setSelectedIndex(0);
        nested2.add(searchWithinCombo);
        
        JLabel label2 = new JLabel("Course #: ");
        nested3.add(label2);
        JTextField courseNumField = new JTextField(10);
        nested3.add(courseNumField);
        
        JButton searchSubmit = new JButton("SEARCH");
        nested4.add(searchSubmit);
        
        searchCourses.add(nested1);
        searchCourses.add(nested2);
        searchCourses.add(nested3);
        searchCourses.add(nested4);
        
        searchSubmit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            
            System.out.println("University records search submitted!");
            ArrayList<String[]> uniSearchRes = new ArrayList<String[]>();
            uniSearchRes = null;
            String identifiers = null;
            
            //Queries course info
            try {
              uniSearchRes = queryFac.searchDB("select Department.name, Courses.id, Courses.name, Courses.regCap from Courses, Department where Department.name='" + searchWithinCombo.getSelectedItem() + "' and Courses.dept=Department.depNum and Courses.id='" + courseNumField.getText() + "'");
            } catch (Exception e2) {
              // TODO Auto-generated catch block
              e2.printStackTrace();
            }
            
            
            if(uniSearchRes != null && !uniSearchRes.isEmpty()) {
              textArea.setText(textArea.getText() + "\n" + "----------------------------");
              textArea.setText(textArea.getText() + "\n" + "Course Search Results");
              textArea.setText(textArea.getText() + "\n" + "----------------------------");
              textArea.setText(textArea.getText() + "\n" + uniSearchRes.get(0)[1] + ": " + uniSearchRes.get(0)[2]);
              textArea.setText(textArea.getText() + "\n" + "College: " + uniSearchRes.get(0)[0]);
              textArea.setText(textArea.getText() + "\n" + "Seats left: " + uniSearchRes.get(0)[3]);
              textArea.setText(textArea.getText() + "\n");
            }
            
            
          }
        });
        
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

  }
  
  
}
