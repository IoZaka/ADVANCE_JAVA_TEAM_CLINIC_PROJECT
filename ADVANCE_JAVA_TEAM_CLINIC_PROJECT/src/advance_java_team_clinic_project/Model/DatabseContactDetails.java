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
import javafx.stage.StageStyle;

/**
 *
 * @author Tasos
 */
public class DatabseContactDetails {

    private Statement stmt;
    private String sql, sql_contact;
    private ResultSet rs;
    private DatabaseConnection object;

    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
    }

    /**
     * Returns userID's information from data.
     *
     * @param contact_id
     * @return
     * @throws SQLException
     */
    public ResultSet fetchContactInfoData(Integer contact_id) throws SQLException {
        stmt = object.connection.createStatement();
        sql = "select id, "
                + "tel_number,"
                + "cel_number, "
                + "email, "
                + "relative_tel_number "
                + "from pm_contact_details "
                + "where id = " + contact_id;
        rs = stmt.executeQuery(sql);
        return rs;
    }

    public void updateContactDetails(Integer contact_id, Integer tel_number, Integer cel_number, String email, Integer relative_tel_number) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        stmt = object.connection.createStatement();
        sql_contact = "update pm_contact_details set tel_number=" + tel_number + ",cel_number=" + cel_number + ",email=\'" + email + "\',relative_tel_number=" + relative_tel_number + " where id =" + contact_id;
        rs = stmt.executeQuery(sql_contact);
        alert.setTitle("Update");
        alert.setContentText("Update submitted");
        alert.showAndWait();
    }
}
