/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

import advance_java_team_clinic_project.Model.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class DatabaseModel {

    private Statement stmt;
    private String sql,existSql,regSql;
    private ResultSet rs,regRs;
    private String username, password, role, created, updated;
    private DatabaseConnection object;
    public Integer roleId;
    Alert alert = new Alert(AlertType.INFORMATION);

    public DatabaseModel() {

    }

    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
    }

    public void Query() throws SQLException {
        stmt = object.connection.createStatement();
        sql = "select a.username as \"user\",a.password pass,b.description role,c.name||' ' || c.surname created_by,d.name||' ' || d.surname updated_by from pm_users a, pm_roles b, pm_users c, pm_users d where a.created_by = c.id and a.updated_by = d.id and a.role_id = b.id";
        rs = stmt.executeQuery(sql);
    }
    
    public boolean loginQuery(String userName, String passWord) throws SQLException {
        /* Alert Initialization */
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        /* End of Alert Initialiization*/
        stmt = object.connection.createStatement();
        sql = "select id, password, role_id from pm_users where username = '" + userName + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            password = rs.getString("password");
            if (password.equals(passWord)) {
                roleId = rs.getInt("role_id");
                System.out.println("Password correct!" + roleId);
                return true;
            } else if (password != passWord) {
                alert.setTitle("Incorrect Password");
                alert.setContentText("The Password you have entered is not correct!");
                alert.showAndWait();
                return false;
            }
        } else {
            alert.setTitle("Incorrect Username");
            alert.setContentText("The Username you have entered does not match any existing user!");
            alert.showAndWait();
            return false;
        }
        return false;
    }
    
    public void registerQuery(String userName, String passWord) throws SQLException{
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        stmt = object.connection.createStatement();
        existSql = "select count(*) as existing from pm_users where username ='"+userName+"'";
        rs = stmt.executeQuery(existSql);
        rs.next();
        if (rs.getInt("existing") > 0){
            alert.setTitle("Wrong username");
            alert.setContentText("Username already existing, please add another username.");
            alert.showAndWait();
        }else if (rs.getInt("existing") == 0){
            regSql = "insert into pm_users (username,password,role_id) values ('"+userName+"','"+passWord+"',3)";
            regRs = stmt.executeQuery(regSql);
            alert.setTitle("Success!");
            alert.setContentText("User was succesfully registered! You may login now.");
            alert.showAndWait();
        }
    }

    public void getData() throws SQLException {
        while (rs.next()) {
            username = rs.getString("user");
            password = rs.getString("pass");
            role = rs.getString("role");
            created = rs.getString("created_by");
            updated = rs.getString("updated_by");

            System.out.println("User: " + username);
            System.out.println("Password: " + password);
            System.out.println("Role: " + role);
            System.out.println("Created: " + created);
            System.out.println("Updated: " + updated);
        }
        rs.close();
    }
}
