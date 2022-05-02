package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.SauceNames;

public class SauceNamesDao {

  private Connection connection;
  private final String GET_SAUCE_NAME_QUERY = "SELECT * FROM sauce_names";
  private final String GET_SAUCE_NAME_BY_ID_QUERY = "SELECT * FROM sauce_names WHERE id = ?";
  private final String CREATE_NEW_SAUCE_NAME_QUERY = "INSERT INTO sauce_names(name) VALUES(?)";
  private final String DELETE_SAUCE_NAME_BY_ID_QUERY = "DELETE FROM sauce_names WHERE id = ?";
      
  public SauceNamesDao() {
    connection = DBConnection.getConnection();
    
  }
  
  public List<SauceNames> getSauceNames() throws SQLException {
    ResultSet rs = connection.prepareStatement(GET_SAUCE_NAME_QUERY).executeQuery();
    List<SauceNames> sauceNames = new ArrayList<SauceNames>();
    
    while (rs.next()) {
      sauceNames.add(populateSauceNames(rs.getInt(1), rs.getString(2)));
    }
    
    return sauceNames;
    
  }
  
  public SauceNames getSauceNamesById (int id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(GET_SAUCE_NAME_BY_ID_QUERY);
    ps.setInt(1, id);
    ResultSet rs =ps.executeQuery();
    rs.next();
    return populateSauceNames(rs.getInt(1), rs.getString(2));
  }
  
  public void createSauceNames(String name) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(CREATE_NEW_SAUCE_NAME_QUERY);
    ps.setString(1, name);
    ps.executeUpdate();
  }
  public void deleteSauceNamesById(int id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(DELETE_SAUCE_NAME_BY_ID_QUERY);
    ps.setInt(1, id);
    ps.executeUpdate();
  }
  
  private SauceNames populateSauceNames(int id, String name) {
    return new SauceNames(id, name);
  }
  
  
}
