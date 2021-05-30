package ujkz.ibam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServiceDbConnection {
  private String database = "bd_releve";
  private String url = "jdbc:mysql://localhost:3306/" + database;
  private String username = "root";
  private String password = "";
  private static Connection connexion = null;

  ServiceDbConnection() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connexion = DriverManager.getConnection(url, username, password);
    } catch (ClassNotFoundException | SQLException exception) {
      exception.printStackTrace();
    }
  }

  public static Connection getConnection() {
    if (connexion == null) {
      new ServiceDbConnection();
    }
    return connexion;
  }

  public static void stopConnection() {
    if (connexion != null) {
      try {
        connexion.close();
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
    }
  }
}
