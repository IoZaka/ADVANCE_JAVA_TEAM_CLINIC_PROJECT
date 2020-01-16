/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import static sun.misc.MessageUtils.where;

/**
 *
 * @author Tasos
 */
public class DatabaseInsuranceDetails {
private Statement stmt;
    private String sql,sql_contact;
    private ResultSet rs;
    private DatabaseConnection object;
    private User user = User.getInstance();
    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
    } 
    
    /**
     * Returns userID's information from data.
     * @param userId
     * @return
     * @throws SQLException 
     */
    public ResultSet fetchInsuranceInfoData(Integer userId) throws SQLException {
        stmt = object.connection.createStatement();
        sql = "select id, "
                    +"to_char(ins_expire_date,'dd-MM-yyyy') ins_expire_date,"
                    + "european, "
                    + "ekas, "
                    + "ins_comments, "
                    + "ins_comp_id "
                + "from pm_patients_ins_info "
                + "where user_id = " + userId;
        rs = stmt.executeQuery(sql);
        return rs;
    }
    
    public void updateInsuranceDetails(Integer userId,String ins_expire_date,Integer european, Integer ekas, String ins_comments,Integer ins_comp_id) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        stmt = object.connection.createStatement();
        sql_contact = "update pm_patients_ins_info set ins_expire_date = to_date(\'"+ins_expire_date+"\','dd-mm-yyyy'),european=" + european + ",ekas=" + ekas + ",ins_comments=\'" + ins_comments + "\',ins_comp_id=" + ins_comp_id +",updated_by="+ user.getId() + " where user_id =" + userId;
        rs = stmt.executeQuery(sql_contact);
        alert.setTitle("Update");
        alert.setContentText("Update submitted");
        alert.showAndWait();
    }
}