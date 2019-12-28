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
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class DoctorDashboardController extends NewStage implements Initializable {

    @FXML
    private TextFlow usernamePane;
    @FXML
    private Text usernameText;
    @FXML
    private ToggleButton profileBtn;
    @FXML
    private ToggleButton editProfileBtn;
    @FXML
    private ToggleButton patientsBtn;
    @FXML
    private ToggleButton logoutBtn;
    @FXML
    private BorderPane patientsPane;

    User user = User.getInstance();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUIonSamePane("../View/profilePane.fxml", patientsPane);
        profileBtn.setSelected(true);
        usernameText.setText(usernameText.getText() + user.getUsername());
        usernamePane.setTextAlignment(TextAlignment.CENTER);
        
        profileBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                loadUIonSamePane("../View/profilePane.fxml", patientsPane);
                clearSelectedButtons();
                profileBtn.setSelected(true);
            }
        });
        
        editProfileBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                loadUIonSamePane("../View/editProfile.fxml", patientsPane);
                clearSelectedButtons();
                editProfileBtn.setSelected(true);
            }
        });
        
        patientsBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                loadUIonSamePane("../View/doctorsPatientsPane.fxml", patientsPane);
                clearSelectedButtons();
                patientsBtn.setSelected(true);
            }
        });
        
        
        logoutBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Stage currentStage = (Stage) patientsPane.getScene().getWindow();
                try {
                    setNewStage("../View/loginStyleFX.fxml", currentStage);
                } catch (IOException ex) {
                    Logger.getLogger(PatientsDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void clearSelectedButtons(){
        profileBtn.setSelected(false);
        editProfileBtn.setSelected(false);
        patientsBtn.setSelected(false);
    }
    
}
