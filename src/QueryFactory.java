import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class QueryFactory {
  
  public QueryFactory() throws Exception {
    //
  }
  
  public ArrayList<String[]> searchDB(String query) throws Exception {
    
    ConnectSQL con = new ConnectSQL();
    Connection connect = con.getMySqlConnection();
    System.out.println("QueryFactory: Connected to database");
    
    PreparedStatement stmt = connect.prepareStatement(query);
    ResultSet rs = stmt.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    
    int numOfCols = rsmd.getColumnCount();
    
    ArrayList<String[]> records = new ArrayList<>();
    
    while(rs.next()) {
      String[] record = new String[numOfCols];
      for(int i = 1; i <= numOfCols; i++) {
        for(int j = 1; j <= numOfCols; j++) {
          record[j - 1] = rs.getString(j);
        }
      }
      records.add(record);
    }
    
    stmt.close();
    connect.close();
    return records;
  }
  
  public int addRecordToDB(String query) throws Exception {
    
    ConnectSQL con = new ConnectSQL();
    Connection connect = con.getMySqlConnection();
    System.out.println("QueryFactory: Connected to database");
    
    PreparedStatement stmt = connect.prepareStatement(query);
    int result = stmt.executeUpdate();
    stmt.close();
    connect.close();
    return result;
  }
  
}
