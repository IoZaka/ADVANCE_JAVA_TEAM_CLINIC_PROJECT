/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
    User user = User.getInstance();
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
