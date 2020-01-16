package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class DataBaseAppoinmentReason {
    private Statement stmt;
    private String sql,sql_contact;
    private ResultSet rs;
    private DatabaseConnection object;
    private User user= User.getInstance();
    
    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
        
    } 
}
