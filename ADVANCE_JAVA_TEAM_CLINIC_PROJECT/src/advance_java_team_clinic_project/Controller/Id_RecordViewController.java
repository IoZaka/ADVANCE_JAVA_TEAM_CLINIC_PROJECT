/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import advance_java_team_clinic_project.Model.Records;
import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class Id_RecordViewController implements Initializable {

    
    @FXML
    private TextField appDateInput;
    @FXML
    private TextField appCodeInput;
    @FXML
    private TextField createdInput;
    @FXML
    private TextArea commentsTextArea;

    private DatabaseLoginRecords  ak;
    private ResultSet rs;
    private Integer id;
    @FXML
    private Button backBtn;
    @FXML
    private Button diagnoseInfoBtn;
    @FXML
    private AnchorPane idRecordPane;
    
    User user = User.getInstance();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appDateInput.setEditable(true);
        appCodeInput.setEditable(true);
        createdInput.setEditable(true);
        commentsTextArea.setEditable(false);
        
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(Id_RecordViewController.this.getClass().getResource("../View/patientsRecords.fxml"));
                    Parent root = (Parent)loader.load();
                    idRecordPane.getChildren().clear();
                    idRecordPane.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }    
    
    public void setID(String id){
        
        this.id = Integer.valueOf(id);
        try {
            ak = new DatabaseLoginRecords();
            ak.getObject();
            rs = ak.fetchBasicInfoData();
            if(rs.next()){
                appDateInput.setText(rs.getString("app_date"));
                appCodeInput.setText(rs.getString("app_code"));
                createdInput.setText(rs.getString("created"));
                commentsTextArea.setText(rs.getString("comments"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        
        diagnoseInfoBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(Id_RecordViewController.this.getClass().getResource("../View/DiagnosisInfoView.fxml"));
                    Parent root = (Parent)loader.load();    
                    DiagnosisInfoController diagnosisID = loader.getController();
                    diagnosisID.setDiagnosisID(id);
                    idRecordPane.getChildren().clear();
                    idRecordPane.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    public Integer getID(){
        return this.id;
    }
    
}
