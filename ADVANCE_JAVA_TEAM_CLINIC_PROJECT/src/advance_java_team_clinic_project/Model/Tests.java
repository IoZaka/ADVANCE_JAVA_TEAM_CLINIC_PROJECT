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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Chris
 */
public class Tests {

    LoggedInUser user = LoggedInUser.getInstance();
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    private DatabaseConnection object;

    private StringProperty id = new SimpleStringProperty();
    private StringProperty diag_id = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty is_completed = new SimpleStringProperty();
    private StringProperty cost = new SimpleStringProperty();
    private StringProperty is_paid = new SimpleStringProperty();
    private StringProperty status_id = new SimpleStringProperty();
    private StringProperty results = new SimpleStringProperty();
    private StringProperty created = new SimpleStringProperty();
    private StringProperty created_by = new SimpleStringProperty();
    private StringProperty updated = new SimpleStringProperty();
    private StringProperty updated_by = new SimpleStringProperty();
    private StringProperty patient = new SimpleStringProperty();
    private StringProperty doctor = new SimpleStringProperty();
    
    
    
    public StringProperty idProperty(){ return id;}
    public StringProperty diag_idProperty(){return diag_id;}
    public StringProperty descriptionProperty(){return description;}
    public StringProperty is_completedProperty(){return is_completed;}
    public StringProperty costProperty(){return cost;}
    public StringProperty is_paidProperty(){return is_paid;}
    public StringProperty status_idProperty(){return status_id;}
    public StringProperty resultsProperty(){return results;}
    public StringProperty createdProperty(){return created;}
    public StringProperty created_byProperty(){return created_by;}
    public StringProperty updatedProperty(){return updated;}
    public StringProperty updated_byProperty(){return updated_by;}
    public StringProperty patientProperty(){return patient;}
    public StringProperty doctorProperty(){return doctor;}
    
    
    public Tests(){}
    
    public ResultSet getTestByDiagID(Integer diagID){
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "select a.id id, " +
" diag.id diag, " +
" a.description, " +
" a.cost, " +
" a.results, " +
" decode(a.is_paid,1,'Yes','No') Paid, " +
" decode(a.status_id,1,'Completed','In progresss') status," +
" decode(a.is_completed,1,'Yes','No') is_completed, " +
"f.SURNAME || ' ' || f.FIRSTNAME doctor, " +
 "a.created, " +
 " cr.SURNAME || ' ' || cr.FIRSTNAME created_by, " +
 " a.updated, " +
 " up.SURNAME || ' ' || up.FIRSTNAME updated_by ," +
" pat.SURNAME || ' ' || pat.FIRSTNAME patient" +
 " from pm_diag_tests a, pm_diagnosis diag , pm_appointment_info ap,pm_users f , pm_users up, pm_users cr, pm_users pat " +
" where ap.patient_id = pat.id " +
" and a.updated_by = up.id " +
                    "and a.created_by = cr.id " +
 " and a.diag_id =    " +   diagID  +
" and a.diag_id = diag.id  " +
" and diag.APP_INFO_ID = ap.id   " +
" and ap.doctor_id = f.id  " +
" and   " +   diagID  + "  != -1  " +
"union  " +
"select a.id id,  " +
" diag.id diag,  " +
" a.description,   " +
" a.cost,   " +
" a.results,   " +
" decode(a.is_paid,1,'Yes','No') Paid,   " +
" decode(a.status_id,1,'Completed','In progresss') status,   " +
" decode(a.is_completed,1,'Yes','No') is_completed,                  " +
" f.SURNAME || ' ' || f.FIRSTNAME doctor, " +
" a.created, " +
" cr.SURNAME || ' ' || cr.FIRSTNAME created_by, " +
" a.updated, " +
" up.SURNAME || ' ' || up.FIRSTNAME updated_by ," +
" pat.SURNAME || ' ' || pat.FIRSTNAME patient " +
" from pm_diag_tests a, pm_diagnosis diag , pm_appointment_info ap,pm_users f, pm_users up, pm_users cr, pm_users pat\n" +
" where ap.patient_id = pat.id " +
" and a.updated_by = up.id " +
" and a.created_by = cr.id " +
" and  diag.APP_INFO_ID = ap.id " +
" and a.diag_id = diag.id " +
" and ap.doctor_id = f.id(+) " +
" and   " + user.getRoleID() + " = 1 " +
" and   " +   diagID  + "    = -1 " +
"union " +
"select a.id id,  " +
" diag.id diag, " +
" a.description,  " +
" a.cost,  " +
" a.results,  " +
" decode(a.is_paid,1,'Yes','No') Paid, " +
" decode(a.status_id,1,'Completed','In progresss') status, " +
" decode(a.is_completed,1,'Yes','No') is_completed, " +
" f.SURNAME || ' ' || f.FIRSTNAME doctor , " +
" a.created, " +
" cr.SURNAME || ' ' || cr.FIRSTNAME created_by, " +
" a.updated, " +
" up.SURNAME || ' ' || up.FIRSTNAME updated_by ," +
" pat.SURNAME || ' ' || pat.FIRSTNAME patient" +
" from pm_diag_tests a, pm_diagnosis diag , pm_appointment_info ap,pm_users f, pm_users up, pm_users cr, pm_users pat " +
" where ap.patient_id = pat.id " +
" and a.updated_by = up.id" +
" and a.created_by = cr.id " +
" and diag.APP_INFO_ID = ap.id " +
" and a.diag_id = diag.id " +
" and ap.doctor_id = f.id " +
" and ap.doctor_id =    " +user.getId() +
" and   " + user.getRoleID() + " = 2 " +
" and   " +   diagID  + "    = -1  " +
"union  " +
"select a.id id, " +
" diag.id diag, " +
" a.description, " +
" a.cost, " +
" a.results, " +
" decode(a.is_paid,1,'Yes','No') Paid, " +
" decode(a.status_id,1,'Completed','In progresss') status, " +
" decode(a.is_completed,1,'Yes','No') is_completed, " +
" f.SURNAME || ' ' || f.FIRSTNAME doctor, " +
" a.created, " +
" cr.SURNAME || ' ' || cr.FIRSTNAME created_by, " +
" a.updated, " +
" up.SURNAME || ' ' || up.FIRSTNAME updated_by , " +
" pat.SURNAME || ' ' || pat.FIRSTNAME patient" +
" from pm_diag_tests a, pm_diagnosis diag , pm_appointment_info ap,pm_users f, pm_users up, pm_users cr, pm_users pat " +
" where ap.patient_id = pat.id " +
" and a.updated_by = up.id " +
" and a.created_by = cr.id " +
" and diag.APP_INFO_ID = ap.id " +
" and a.diag_id = diag.id " +
" and ap.doctor_id = f.id(+) " +
" and ap.patient_id =   " +user.getId() +
" and  " + user.getRoleID() +"  = 3 " +
" and   " +   diagID  + "    = -1 " +
"union " +
"select a.id id, " +
" diag.id diag, " +
" a.description,  " +
" a.cost, " +
" a.results, " +
" decode(a.is_paid,1,'Yes','No') Paid, " +
" decode(a.status_id,1,'Completed','In progresss') status, " +
" decode(a.is_completed,1,'Yes','No') is_completed, " +
" f.SURNAME || ' ' || f.FIRSTNAME doctor, " +
" a.created, " +
" cr.SURNAME || ' ' || cr.FIRSTNAME created_by, " +
" a.updated, " +
" up.SURNAME || ' ' || up.FIRSTNAME updated_by ," +
" pat.SURNAME || ' ' || pat.FIRSTNAME patient " +
" from pm_diag_tests a, pm_diagnosis diag , pm_appointment_info ap,pm_users f, pm_users up, pm_users cr, pm_users pat " +
" where ap.patient_id = pat.id " +
" and a.updated_by = up.id " +
" and a.created_by = cr.id " +
" and diag.APP_INFO_ID = ap.id  " +
" and a.diag_id = diag.id  " +
" and ap.doctor_id = f.id(+) " +
" and  " + user.getRoleID() + " = 4 " +
" and   " +   diagID  + "    = -1 " +
" union " +
" select a.id id, " +
"diag.id diag, " +
"a.description, " +
"a.cost, " +
"a.results, " +
"decode(a.is_paid,1,'Yes','No') Paid, " +
"decode(a.status_id,1,'Completed','In progresss') status, " +
"decode(a.is_completed,1,'Yes','No') is_completed, " +
"f.SURNAME || ' ' || f.FIRSTNAME doctor, " +
" a.created, " +
" cr.SURNAME || ' ' || cr.FIRSTNAME created_by, " +
" a.updated, " +
" up.SURNAME || ' ' || up.FIRSTNAME updated_by ," +
" pat.SURNAME || ' ' || pat.FIRSTNAME patient" +
" from pm_diag_tests a, pm_diagnosis diag , pm_appointment_info ap,pm_users f, pm_users up, pm_users cr, pm_users pat " +
" where ap.patient_id = pat.id " +
" and a.updated_by = up.id " +
" and a.created_by = cr.id " +
" and  diag.APP_INFO_ID = ap.id  " +
"and a.diag_id = diag.id " +
"and ap.doctor_id = f.id(+) " +
"and    " + user.getRoleID() + "  = 5 " +
"and   " +   diagID  + "    = -1";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public ResultSet getTestByID(Integer testID) {
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            //sql = "select * from pm_diag_tests where id= " + testID;
            sql = "select a.id id, " +
                    "a.diag_id, " + 
                    "a.description, " +
                    "a.cost, " +
                    "a.results, " +
                    "decode(a.is_paid,1,'Yes','No') Paid, " +
                    "decode(a.status_id,1,'Completed','In progresss') status, " +
                    "decode(a.is_completed,1,'Yes','No') is_completed, " +
                    "to_char(a.created,'MM/DD/YYYY') created, " +
                    "c.SURNAME || ' ' || c.FIRSTNAME createdby, " +
                    " to_char(a.UPDATED,'MM/DD/YYYY') updated, " +
                    "d.SURNAME || ' ' || d.FIRSTNAME updated_by, " +
                    "e.SURNAME || ' ' || e.FIRSTNAME patient, " +
                    "f.SURNAME || ' ' || f.FIRSTNAME doctor " +
                    "from pm_diag_tests a, pm_users c, pm_users d, pm_diagnosis diag , pm_appointment_info ap, pm_users e, pm_users f " +
                    "where a.created_by = c.id " +
                    "  and a.updated_by = d.id " +
                    "  and a.diag_id = diag.id " +
                    "  and diag.APP_INFO_ID = ap.id " +
                    "  and ap.patient_id = e.id " +
                    "  and ap.doctor_id = f.id(+) " + 
                    "  and a.id = " + testID;
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public ResultSet getAllTests() {
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "select a.id id, " +
                    "a.description, " +
                    "a.cost, " +
                    "a.results, " +
                    "decode(a.is_paid,1,'Yes','No') Paid, " +
                    "decode(a.status_id,1,'Completed','In progresss') status, " +
                    "decode(a.is_completed,1,'Yes','No') is_completed, " +
                    "a.created, " +
                    "c.SURNAME || ' ' || c.FIRSTNAME createdby, " +
                    "a.updated, " +
                    "d.SURNAME || ' ' || d.FIRSTNAME updated_by, " +
                    "e.SURNAME || ' ' || e.FIRSTNAME patient, " +
                    "f.SURNAME || ' ' || f.FIRSTNAME doctor " +
                    "from pm_diag_tests a, pm_users c, pm_users d, pm_diagnosis diag , pm_appointment_info ap, pm_users e, pm_users f " +
                    "where a.created_by = c.id " +
                    "  and a.updated_by = d.id " +
                    "  and a.diag_id = diag.id " +
                    "  and diag.APP_INFO_ID = ap.id " +
                    "  and ap.patient_id = e.id " +
                    "  and ap.doctor_id = f.id(+)";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
        
}
