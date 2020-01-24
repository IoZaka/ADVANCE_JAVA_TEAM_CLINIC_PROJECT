/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
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
     *
     * @return
     * @throws SQLException
     */
    public ResultSet fetchBasicInfoData(Integer roleID, Integer userID) throws SQLException {
        stmt = object.connection.createStatement();
        sql = "select a.id, "
                + "    to_char(a.app_date,'dd/mm/yyyy') app_date, "
                + "    a.comments, "
                + "    a.app_code, "
                + "    to_char(a.created,'dd/mm/yyyy') created, "
                + "    to_char(a.updated,'dd/mm/yyyy') updated, "
                + "    b.Surname || ' ' || b.firstname patient, "
                + "    c.Surname || ' ' || c.firstname doctor, "
                + "    d.Surname || ' ' || d.firstname updated_by, "
                + "    e.Surname || ' ' || e.firstname created_by "
                + "from pm_appointment_info a, pm_users b, pm_users c, pm_users d, pm_users e "
                + "where a.patient_id = b.id "
                + "        and a.doctor_id = c.id(+) "
                + "        and a.updated_by = d.id "
                + "        and a.created_by = e.id "
                + "        and " + roleID + " = 1 "
                + "union "
                + "select a.id, "
                + "    to_char(a.app_date,'dd/mm/yyyy') app_date, "
                + "    a.comments, "
                + "    a.app_code, "
                + "    to_char(a.created,'dd/mm/yyyy') created, "
                + "    to_char(a.updated,'dd/mm/yyyy') updated, "
                + "    b.Surname || ' ' || b.firstname patient, "
                + "    c.Surname || ' ' || c.firstname doctor, "
                + "    d.Surname || ' ' || d.firstname updated_by, "
                + "    e.Surname || ' ' || e.firstname created_by "
                + "from pm_appointment_info a, pm_users b, pm_users c, pm_users d, pm_users e "
                + "where a.patient_id = b.id "
                + "        and a.doctor_id = c.id "
                + "        and a.updated_by = d.id "
                + "        and a.created_by = e.id "
                + "       and a.doctor_id = " + userID
                + "       and " + roleID + " = 2 "
                + "union "
                + "select a.id, "
                + "    to_char(a.app_date,'dd/mm/yyyy') app_date, "
                + "    a.comments, "
                + "    a.app_code, "
                + "    to_char(a.created,'dd/mm/yyyy') created, "
                + "    to_char(a.updated,'dd/mm/yyyy') updated, "
                + "    b.Surname || ' ' || b.firstname patient, "
                + "    c.Surname || ' ' || c.firstname doctor, "
                + "    d.Surname || ' ' || d.firstname updated_by, "
                + "    e.Surname || ' ' || e.firstname created_by "
                + "from pm_appointment_info a, pm_users b, pm_users c, pm_users d, pm_users e "
                + "where a.patient_id = b.id "
                + "        and a.doctor_id = c.id(+) "
                + "        and a.updated_by = d.id "
                + "        and a.created_by = e.id "
                + "       and a.patient_id = " + userID
                + "       and " + roleID + " = 3 "
                + "union "
                + "select a.id, "
                + "    to_char(a.app_date,'dd/mm/yyyy') app_date, "
                + "    a.comments, "
                + "    a.app_code, "
                + "    to_char(a.created,'dd/mm/yyyy') created, "
                + "    to_char(a.updated,'dd/mm/yyyy') updated, "
                + "    b.Surname || ' ' || b.firstname patient, "
                + "    c.Surname || ' ' || c.firstname doctor, "
                + "    d.Surname || ' ' || d.firstname updated_by, "
                + "    e.Surname || ' ' || e.firstname created_by "
                + "from pm_appointment_info a, pm_users b, pm_users c, pm_users d, pm_users e "
                + "where a.patient_id = b.id "
                + "       and a.doctor_id = c.id(+) "
                + "       and a.updated_by = d.id "
                + "       and a.created_by = e.id "
                + "       and " + roleID + " = 4";
        rs = stmt.executeQuery(sql);
        return rs;
    }

    public ResultSet fetchBasicInfoData(Integer appID) throws SQLException {
        stmt = object.connection.createStatement();
        System.out.println("APPID: " + appID);
        sql = "select a.id, "
                + " to_char(a.app_date,'dd/mm/yyyy') app_date, "
                + " a.comments, "
                + " a.app_code, "
                + " to_char(a.created,'dd/mm/yyyy') created, "
                + " to_char(a.updated,'dd/mm/yyyy') updated, "
                + " b.Surname || ' ' || b.firstname patient, "
                + " c.Surname || ' ' || c.firstname doctor, "
                + " d.Surname || ' ' || d.firstname updated_by, "
                + " e.Surname || ' ' || e.firstname created_by, "
                + " nvl(f.id,-1) diagnosis "
                + " from pm_appointment_info a, pm_users b, pm_users c, pm_users d, pm_users e , pm_diagnosis f "
                + " where a.patient_id = b.id "
                + " and a.doctor_id = c.id(+) "
                + " and a.updated_by = d.id "
                + " and a.created_by = e.id "
                + " and a.id = f.app_info_id(+) "
                + " and a.id = " + appID;
        rs = stmt.executeQuery(sql);
        return rs;
    }
}
