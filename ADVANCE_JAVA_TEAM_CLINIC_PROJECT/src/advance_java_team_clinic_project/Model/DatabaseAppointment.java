package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DatabaseAppointment {
    private Statement stmt;
    private String sql,sql_contact;
    private ResultSet rs;
    private DatabaseConnection object;
    private User user= User.getInstance();
    
    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
    } 
    public void makeAppointment (String comments){
        try {
            stmt = object.connection.createStatement();
            sql = "insert into PM_APPOINTMENT_INFO (patient_id,comments,created_by,updated_by) values (" + user.getId() + " , '" + comments+"' , " + user.getId() + " , " + user.getId() +" )";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAppointment.class.getName()).log(Level.SEVERE, null, ex);
        } System.out.println("mpika stin function");
    }
}
