/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseConnection;
import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import advance_java_team_clinic_project.Model.Records;
import advance_java_team_clinic_project.Model.Tests;
import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    @FXML
    private Button backBtn;
    @FXML
    private AnchorPane testsPane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        diagIDCol.setCellValueFactory(new PropertyValueFactory<>("diag_id"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        isCompletedCol.setCellValueFactory(new PropertyValueFactory<>("is_completed"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        isPaidCol.setCellValueFactory(new PropertyValueFactory<>("is_paid"));
        
        testsTable.getColumns().addAll(idCol,diagIDCol,descriptionCol,
                isCompletedCol,costCol,isPaidCol);
          
        
    }     
    
    public void setTestID(String id, Integer diagID){
        
        
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(TestsTableViewController.this.getClass().getResource("../View/DiagnosisInfoView.fxml"));
                    Parent root = (Parent)loader.load();
                    DiagnosisInfoController diagnosisID = loader.getController();
                    diagnosisID.setDiagnosisID(id,diagID);
                    testsPane.getChildren().clear();
                    testsPane.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "select * from pm_diag_tests where diag_id= " + Integer.valueOf(diagID);
            //sql = "select a.id id, a.description, decode(a.is_completed,0,'No',1,'Yes') is_completed,cost,\n" +
               // "decode(a.is_paid,0,'No',1,'Yes') is_paid, b.description status from pm_diag_tests a, \n" +
               // "pm_status b where a.status_id = b.id and diag_id= " +testID;
            rs = stmt.executeQuery(sql);
            data = FXCollections.observableArrayList(databaseTests(rs));
            testsTable.setItems(data);          
        } catch (SQLException ex) {
            Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList databaseTests(ResultSet rs) throws SQLException {
        ArrayList<Tests> data = new ArrayList();
        
        if(rs.next()){
            Tests test = new Tests();
            test.idProperty().set(rs.getString("id"));
            test.diag_idProperty().set(rs.getString("diag_id"));
            test.descriptionProperty().set(rs.getString("description"));
            test.is_completedProperty().set(rs.getString("is_completed"));
            test.costProperty().set(rs.getString("cost"));
            test.is_paidProperty().set(rs.getString("is_paid"));
            data.add(test);
        }
        return data;
    }
    
}



