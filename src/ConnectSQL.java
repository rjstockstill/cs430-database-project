import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectSQL {
  
  String url = "jdbc:oracle:thin:@localhost:1521:orcl";
  String user = "sys as sysdba";
  // TODO change sqlplus password
  String password = "MYPASSWORD";
  
  public ConnectSQL() throws Exception {
    //
  }
  
  Connection getMySqlConnection() throws Exception {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    return DriverManager.getConnection(url, user, password);
  }
}
