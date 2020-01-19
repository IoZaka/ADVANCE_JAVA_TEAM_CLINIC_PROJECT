/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseConnection;
import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import advance_java_team_clinic_project.Model.Records;
import advance_java_team_clinic_project.Model.User;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class TestsTableViewController implements Initializable {

    private ObservableList data;  
    
    @FXML
    private TableView testsTable;
    
     User user = User.getInstance();
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    private DatabaseConnection object;
    
    TableColumn idCol = new TableColumn("ID");
    TableColumn diagIDCol = new TableColumn("Diag ID");
    TableColumn descriptionCol = new TableColumn("Description");
    TableColumn isCompletedCol = new TableColumn("Is Completed");
    TableColumn costCol = new TableColumn("Cost");
    TableColumn isPaidCol = new TableColumn("Is paid");
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellFactory(new PropertyValueFactory<>("id"));
        diagIDCol.setCellFactory(new PropertyValueFactory<>("diag_id"));
        descriptionCol.setCellFactory(new PropertyValueFactory<>("description"));
        isCompletedCol.setCellFactory(new PropertyValueFactory<>("is_completed"));
        costCol.setCellFactory(new PropertyValueFactory<>("cost"));
        isPaidCol.setCellFactory(new PropertyValueFactory<>("is_paid"));
        
        testsTable.getColumns().addAll(idCol,diagIDCol,descriptionCol,
                isCompletedCol,costCol,isPaidCol);
        testsTable.setItems(data);   
        
    }     
    
    public void setDiagID(String id){
        String testID = null;
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "select id from pm_diagnosis where app_info_id=" + Integer.valueOf(id);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                testID = rs.getString("id");
            }else{
                System.out.println("doesnt work");
            }
            
            //sql = "select id from pm_diag_tests where diag_id= " + Integer.valueOf(testID);
            sql = "select * from pm_diag_tests";
            rs = stmt.executeQuery(sql);
            System.out.println(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
}



/*
select a.id id, a.description, decode(a.is_completed,0,'No',1,'Yes') is_completed,cost,
decode(a.is_paid,0,'No',1,'Yes') is_paid, b.description status from pm_diag_tests a, 
pm_status b where a.status_id = b.id and diag_id = edw_tha_baleis_to_id_tou_diagnosis_me_java_kwdika ;
*/