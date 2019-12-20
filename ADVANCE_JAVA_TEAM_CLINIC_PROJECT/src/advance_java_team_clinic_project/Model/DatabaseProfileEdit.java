/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author User
 */
public class DatabaseProfileEdit {
    
    private Statement stmt;
    private String sql;
    private ResultSet rs;
    private int id;
    private DatabaseConnection object;
    User user = User.getInstance();
    public Integer roleId;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private List<Integer> id_list; 
    private List<String> Description_list;
    private List<Co_Role> coRoles;    
    
    public DatabaseProfileEdit(){
        id_list = new ArrayList<Integer>(100); 
        Description_list = new ArrayList<String>(100); 
    }
    
    public List<Integer> getId_list() {
        return id_list;
    }

    public List<String> getDescription_list() {
        return Description_list;
    }
    
    public void Clean_Lists(){
        id_list.clear();
        Description_list.clear();
    }
    
    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
    } 
    
    
    
    public List<Co_Role> FetchData(String tableName) throws SQLException{
   
    stmt = object.connection.createStatement();
    sql = "select id,description from " + tableName;
    rs = stmt.executeQuery(sql);
    coRoles = new ArrayList<>();
    while (rs.next()) {
        coRoles.add(new Co_Role(rs.getInt("id"),rs.getString("description")));
        
    }
    for (int i = 0; i < coRoles.size(); i++)
{
   System.out.println(coRoles.get(i).getDescription());
}
    
    return coRoles;
    }
    
    public boolean EditQuery(String tableName) throws SQLException {
    /* Alert Initialization */
    alert.setHeaderText(null);
    alert.initStyle(StageStyle.UTILITY);
    /* End of Alert Initialiization*/
    stmt = object.connection.createStatement();
    sql = "select id,description from " + tableName;
    rs = stmt.executeQuery(sql);
    while (rs.next()) {
        id_list.add(rs.getInt("id"));
        Description_list.add(rs.getString("description"));
    }
    return false;
}

}