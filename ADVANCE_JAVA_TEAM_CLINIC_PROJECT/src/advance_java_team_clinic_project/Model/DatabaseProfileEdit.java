/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author User
 */
public class DatabaseProfileEdit {

    private Statement stmt;
    private String sql;
    private ResultSet rs;
    private DatabaseConnection object;
    LoggedInUser user = LoggedInUser.getInstance();
    public Integer roleId;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
    }

    /**
     * @param tableName
     * @return
     * @throws SQLException
     */
    public ObservableList<CustomCombo> FetchData(String tableName) throws SQLException {
        ObservableList<CustomCombo> customCombo = FXCollections.observableArrayList();
        stmt = object.connection.createStatement();
        sql = "select id,description from " + tableName;
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            customCombo.add(new CustomCombo(rs.getInt("id"), rs.getString("description")));
        }
        return customCombo;
    }

}
