/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Chris
 */
public class Admission {
    
    private Statement stmt;
    private String sql;
    private ResultSet rs;
    private Integer ak;
    private LoggedInUser user = LoggedInUser.getInstance();
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private DatabaseConnection object;

    /**
     * Get the database connection
     */
    public void getObject() {
        try {
            object = DatabaseConnection.getInstance();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInsuranceDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet fetchAdmissionData(Integer diagID){
        try {
            stmt = object.connection.createStatement();
            sql = " select a.id, a.diag_id, to_char(a.admission_date, 'yyyy/MM/dd') admission_date, to_date(a.discharge_date, 'yyyy/MM/dd') discharge_date, "
                    + "a.cost_per_day, a.room, a.bed, b.SURNAME || ' ' || b.FIRSTNAME created_by, " 
                    + "to_char(a.CREATED,'yyyy/MM/dd') created , " 
                    + "c.SURNAME || ' ' || c.FIRSTNAME updated_by, " 
                    + " to_char(a.UPDATED,'yyyy/MM/dd') updated "
                    + "from pm_addmissions a, pm_users b, pm_users c where "
                    + "diag_id= " + diagID + " and a.updated_by = c.id and a.created_by = b.id";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInsuranceDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
