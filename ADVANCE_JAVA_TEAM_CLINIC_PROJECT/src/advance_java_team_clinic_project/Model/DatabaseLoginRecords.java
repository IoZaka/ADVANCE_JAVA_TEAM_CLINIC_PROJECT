/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author Tasos
 */
public class DatabaseLoginRecords {
    private Statement stmt;
    private String sql;
    private ResultSet rs;
    private DatabaseConnection object;
    User user = User.getInstance();
    public Integer roleId;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
    }
    /**
     * Fetches Data from Database.
     * @return
     * @throws SQLException 
     */
    public ResultSet fetchBasicInfoData() throws SQLException {
        stmt = object.connection.createStatement();
        sql = "select a.id, \n" +
              "to_char(a.app_date,'dd/mm/yyyy') app_date,\n" +
              "a.comments,\n" +
              "a.app_code,\n" +
              "to_char(a.created,'dd/mm/yyyy') created,\n" +
              "to_char(a.updated,'dd/mm/yyyy') updated,\n" +
              "b.Surname || ' ' || b.firstname patient,\n" +
              "c.Surname || ' ' || c.firstname doctor,\n" +
              "d.Surname || ' ' || d.firstname updated_by,\n" +
              "e.Surname || ' ' || e.firstname created_by\n" +
              "from pm_appointment_info a, pm_users b, pm_users c, pm_users d, pm_users e \n" +
              "where a.patient_id = b.id\n" +
              "and a.doctor_id = c.id\n" +
              "and a.updated_by = d.id\n" +
              "and a.created_by = e.id";
        rs = stmt.executeQuery(sql);
        return rs;
    }
    
    public ResultSet fetchBasicInfoData(Integer appID) throws SQLException{
        stmt = object.connection.createStatement();
        System.out.println("APPID: " + appID);
        sql = "select a.id, " +
              "to_char(a.app_date,'dd/mm/yyyy') app_date, " +
              "a.comments, " +
              "a.app_code, " +
              "to_char(a.created,'dd/mm/yyyy') created, " +
              "to_char(a.updated,'dd/mm/yyyy') updated, " +
              "b.Surname || ' ' || b.firstname patient, " +
              "c.Surname || ' ' || c.firstname doctor, " +
              "d.Surname || ' ' || d.firstname updated_by, " +
              "e.Surname || ' ' || e.firstname created_by " +
              "from pm_appointment_info a, pm_users b, pm_users c, pm_users d, pm_users e " +
              "where a.patient_id = b.id " +
              "and a.doctor_id = c.id " +
              "and a.updated_by = d.id " +
              "and a.created_by = e.id " + 
              "and a.id = " + appID; 
            rs = stmt.executeQuery(sql);
        return rs;
    }

}
