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
 * @author Beast
 */
public class DatabaseProfileDetails {
    private Statement stmt;
    private String sql, sql_users, sql_user_details;
    private ResultSet rs;
    private DatabaseConnection object;

    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
    } 
    public ResultSet fetchBasicInfoData(Integer userId) throws SQLException {
        stmt = object.connection.createStatement();
        sql = "select a.id, "
                    + "a.username,"
                    + "a.role_id, "
                    + "a.surname, "
                    + "a.firstname, "
                    + "a.address_id, "
                    + "a.contact_id, "
                    + "b.amka, "
                    + "b.ama, "
                    + "to_char(b.date_of_birth,'dd-MM-yyyy') date_of_birth, "
                    + "b.fathers_name, "
                    + "b.mothers_name, "
                    + "b.gender_id, "
                    + "b.eco_status_id, "
                    + "b.global_code, "
                    + "b.nationality_id, "
                    + "b.profession, "
                    + "b.place_of_birth, "
                    + "b.member_id, "
                    + "b.id basic_info_id "
                + "from pm_users a, pm_patients_basic_info b "
                + "where a.id = " + userId + " and a.id = b.user_id(+)";
        rs = stmt.executeQuery(sql);
        return rs;
    }
    
    public ResultSet fetchAllUsernames() throws SQLException{
        stmt = object.connection.createStatement();
        sql = "select username from pm_users";
        rs = stmt.executeQuery(sql);
        return rs;
    }
    
    public void updateBasicInfoData(Integer userId,Integer lRole_Id, String lSurname, String lName, String lAmka, String lAma, String lDate_of_birth, String lFathers_name, String lMothers_name, 
            Integer lGender_id, Integer lEco_status_id, Integer lNationality_id, String lProffesion, String lPlace_of_birth/*, Integer lMember_id*/) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        stmt = object.connection.createStatement();
        sql_users = "update pm_users set role_id=" + lRole_Id + ",surname=\'" + lSurname + "\',firstname=\'" + lName + "\' where id =" + userId;
        sql_user_details = "update pm_patients_basic_info set "
                + "amka=\'"             + lAmka 
                + "\',ama=\'"           + lAma
                + "\',date_of_birth = to_date(\'"+lDate_of_birth+"\','dd-mm-yyyy')"
                + ",fathers_name=\'"    +lFathers_name
                + "\',mothers_name=\'"  +lMothers_name
                + "\',gender_id ="      +lGender_id
                + ",eco_status_id ="    +lEco_status_id
                + ",nationality_id="    +lNationality_id
                + ",profession=\'"      +lProffesion
                + "\',place_of_birth=\'"+lPlace_of_birth
                + "\' where user_id =" + userId;
        rs = stmt.executeQuery(sql_users);
        rs = stmt.executeQuery(sql_user_details);
        alert.setTitle("Update");
        alert.setContentText("Update submitted");
        alert.showAndWait();
    }
}

