/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Beast
 */
public class DatabaseProfileDetails {
    private Statement stmt;
    private String sql;
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
                    + "a.name, "
                    + "a.address_id, "
                    + "a.contact_id, "
                    + "b.amka, "
                    + "b.ama, "
                    + "b.date_of_birth, "
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
}

