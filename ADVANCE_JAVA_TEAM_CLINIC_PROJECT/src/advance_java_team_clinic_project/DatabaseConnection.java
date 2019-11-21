/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Made with Singleton Pattern
 * @author Beast
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    Connection connection;
    //private String url =  "jdbc:oracle:thin:@localhost:1521:xe";
    private String url =  "jdbc:oracle:thin:@25.57.202.173:1521:xe";
    private String username = "javadev";
    private String password = "javadev";
    
    private DatabaseConnection() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex){
                System.out.println("Database Connection Creation Failed: " + ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()){
            instance = new DatabaseConnection();
        }
        return instance;
    }
    

   
}
