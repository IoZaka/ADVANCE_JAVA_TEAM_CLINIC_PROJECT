/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class ClinicCenterMenuController extends NewStage implements Initializable {

    @FXML
    private TextFlow usernameFlow;
    @FXML
    private Text usernameText;
    @FXML
    private ToggleButton profileBtn;
    @FXML
    private ToggleButton editProfileBtn;
    @FXML
    private ToggleButton clinicalTestsBtn;
    @FXML
    private ToggleButton logoutBtn;
    
    User user = User.getInstance();
    @FXML
    private BorderPane clinicCenterPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUIonSamePane("../View/profilePane.fxml", clinicCenterPane);
        profileBtn.setSelected(true);
        usernameText.setText(usernameText.getText() + user.getUsername());
        usernameFlow.setTextAlignment(TextAlignment.CENTER);
        
        profileBtn.setOnMouseClicked((MouseEvent event) -> {
            loadUIonSamePane("../View/profilePane.fxml", clinicCenterPane);
            clearSelectedButtons();
            profileBtn.setSelected(true);
        });
        
        editProfileBtn.setOnMouseClicked((MouseEvent event) -> {
            loadUIonSamePane("../View/editProfile.fxml", clinicCenterPane);
            clearSelectedButtons();
            editProfileBtn.setSelected(true);
        });
        
        clinicalTestsBtn.setOnMouseClicked((MouseEvent event) -> {
           
            try {
                clearSelectedButtons();
                clinicalTestsBtn.setSelected(true);
                
                FXMLLoader loader = new FXMLLoader(ClinicCenterMenuController.this.getClass().getResource("../View/testsTableView.fxml"));
                Parent root = (Parent)loader.load();
                TestsTableViewController allTests = loader.getController();
                allTests.setID();
                clinicCenterPane.setCenter(root);
            } catch (IOException ex) {
                Logger.getLogger(ClinicCenterMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });

        logoutBtn.setOnMouseClicked((MouseEvent event) -> {
          Stage currentStage = (Stage) clinicCenterPane.getScene().getWindow();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText("Do you want to Logout?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    try {
                        setNewStage("../View/loginStyleFX.fxml", currentStage);
                    } catch (IOException ex) {
                        Logger.getLogger(PatientsDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    clearSelectedButtons();
                }
        });
    }    
    
    private void clearSelectedButtons() {
        profileBtn.setSelected(false);
        editProfileBtn.setSelected(false);
        clinicalTestsBtn.setSelected(false);
        logoutBtn.setSelected(false);
    }
}
