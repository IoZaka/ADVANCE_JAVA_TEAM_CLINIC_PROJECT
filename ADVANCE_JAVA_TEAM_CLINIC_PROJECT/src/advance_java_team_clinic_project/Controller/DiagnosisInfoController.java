/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class DiagnosisInfoController implements Initializable {

    @FXML
    private DatePicker createdDateInput;
    @FXML
    private TextField createdByInput;
    @FXML
    private DatePicker updatedDateInput;
    @FXML
    private TextField updatedByInput;
    @FXML
    private ComboBox<?> patientType;
    @FXML
    private Button backBtn;
    @FXML
    private Button admissionInfoBtn;
    @FXML
    private TextField medicineInput;
    @FXML
    private TextField commentsInput;

    User user = User.getInstance();
    @FXML
    private AnchorPane diagnosisPanel;
    
    private Integer id;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        createdDateInput.setEditable(false);
        createdByInput.setEditable(false);
        updatedDateInput.setEditable(false);
        updatedByInput.setEditable(false);
        patientType.setEditable(false);
        medicineInput.setEditable(false);
        commentsInput.setEditable(false);
        
        
        
    }   
    
    public void setDiagnosisID(String id){
          
        this.id = Integer.valueOf(id);  
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(DiagnosisInfoController.this.getClass().getResource("../View/id_RecordView.fxml"));
                    Parent root = (Parent)loader.load();
                    Id_RecordViewController idRecord = loader.getController();
                    idRecord.setID(id);
                    diagnosisPanel.getChildren().clear();
                    diagnosisPanel.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        admissionInfoBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(DiagnosisInfoController.this.getClass().getResource("../View/AdmissionInfoView.fxml"));
                    Parent root = (Parent)loader.load();
                    AdmissionInfoController admissionInfoID = loader.getController();
                    admissionInfoID.setAdmissionID(id);
                    diagnosisPanel.getChildren().clear();
                    diagnosisPanel.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
}