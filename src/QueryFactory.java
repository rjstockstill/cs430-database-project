import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

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
    
    //rs.next();
    int numOfCols = rsmd.getColumnCount();
    //System.out.println(numOfCols);
    
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
    //System.out.println(records.get(0)[0] + " " + records.get(0)[1] + " " + records.get(0)[2]);
    
    stmt.close();
    connect.close();
    return records;
  }
  
  public int addRecordToDB(String query) throws Exception {
    
    ConnectSQL con = new ConnectSQL();
    Connection connect = con.getMySqlConnection();
    System.out.println("QueryFactory: Connected to database");
    
    PreparedStatement stmt = connect.prepareStatement(query);
    //ResultSet rs = stmt.executeQuery();
    int result = stmt.executeUpdate();
    stmt.close();
    connect.close();
    return result;
  }
  
}
