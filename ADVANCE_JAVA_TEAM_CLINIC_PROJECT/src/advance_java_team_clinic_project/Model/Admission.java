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
    
    public ResultSet fetchAdmissionData(Integer ID){
        try {
            getObject();
            stmt = object.connection.createStatement();
            sql =   "select a.id, a.diag_id, to_char(a.admission_date, 'yyyy/MM/dd') admission_date, nvl(to_char(a.discharge_date, 'yyyy/MM/dd'),'-1') discharge_date, "
                    +" a.cost_per_day, a.room, a.bed, b.SURNAME || ' ' || b.FIRSTNAME created_by,  "
                    +" to_char(a.CREATED,'yyyy/MM/dd') created ,  "
                    +" c.SURNAME || ' ' || c.FIRSTNAME updated_by,"
                    +" a.total_cost,  " 
                    +" to_char(a.UPDATED,'yyyy/MM/dd') updated ,"
                    +" doc.SURNAME || ' ' || doc.FIRSTNAME doctor,"
                    +" pat.SURNAME || ' ' || pat.FIRSTNAME patient"
                    +" from pm_addmissions a, pm_users b, pm_users c, pm_diagnosis di, pm_appointment_info ap, pm_users doc, pm_users pat where "
                    +" a.id=   "+ ID +"   and a.updated_by = c.id and a.created_by = b.id and a.diag_id = di.id and di.app_info_id = ap.id and ap.doctor_id = doc.id and ap.patient_id = pat.id ";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInsuranceDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
