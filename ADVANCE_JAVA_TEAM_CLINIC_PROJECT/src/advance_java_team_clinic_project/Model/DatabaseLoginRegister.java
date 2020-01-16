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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class DatabaseLoginRegister {

    private Statement stmt;
    private String sql, existSql, regSql, pwdSql;
    private ResultSet rs, regRs, pwdRs;
    private String username, password, role, created, updated, hashPwd;
    private DatabaseConnection object;
    User user = User.getInstance();
    public Integer roleId;
    Alert alert = new Alert(AlertType.INFORMATION);

    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
    }

    /**
     * Returns true/false if the login information is correct or not.
     * @param userName
     * @param passWord
     * @return
     * @throws SQLException 
     */
    public boolean loginQuery(String userName, String passWord) throws SQLException {
        /* Alert Initialization */
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        /* End of Alert Initialiization*/
        stmt = object.connection.createStatement();
        hashPwd = makeHashPwd(passWord);
        sql = "select id, password, role_id, firstname as firstname, surname, username, address_id, contact_id from pm_users where username = '" + userName + "'";
        rs = stmt.executeQuery(sql);

        if (rs.next()) {
            password = rs.getString("password");
            if (password.equals(hashPwd)) {
                user.setRoleID(rs.getInt("role_id"));
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstname"));
                user.setSurname(rs.getString("surname"));
                user.setUsername(rs.getString("username"));
                user.setAddressID(rs.getInt("address_id"));
                user.setContactID(rs.getInt("contact_id"));
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

    
    /**
     * Returns true/false if the register information is correct or not.
     * @param userName
     * @param passWord
     * @return
     * @throws SQLException 
     */
    public boolean registerQuery(String userName, String passWord) throws SQLException {
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        stmt = object.connection.createStatement();
        existSql = "select count(*) as existing from pm_users where username ='" + userName + "'";
        rs = stmt.executeQuery(existSql);
        rs.next();
        if (rs.getInt("existing") > 0) {
            alert.setTitle("Wrong username");
            alert.setContentText("Username already existing, please add another username.");
            alert.showAndWait();
            return false;
        } else if (rs.getInt("existing") == 0) {
            hashPwd = makeHashPwd(passWord);
            regSql = "insert into pm_users (username,password,role_id) values ('" + userName + "','" + hashPwd + "',3)";
            regRs = stmt.executeQuery(regSql);
            alert.setTitle("Success!");
            alert.setContentText("User was succesfully registered! You may login now.");
            alert.showAndWait();
            return true;

        }
        return false;
    }

    /**
     * Generates the string password to Hash.
     * @param passWord
     * @return 
     */
    private String makeHashPwd(String passWord) {
        String localPwd;
        try {
            pwdSql = "SELECT DBMS_OBFUSCATION_TOOLKIT.md5(input => UTL_I18N.STRING_TO_RAW (\'" + passWord + "\', 'AL32UTF8')) pwd from dual";
            pwdRs = stmt.executeQuery(pwdSql);
            if (pwdRs.next()) {
                localPwd = pwdRs.getString("pwd");
                pwdRs.close();
                return localPwd;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseLoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
