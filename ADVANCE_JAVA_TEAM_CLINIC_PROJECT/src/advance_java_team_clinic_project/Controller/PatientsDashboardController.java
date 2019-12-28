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
public class PatientsDashboardController extends NewStage implements Initializable {

    User user = User.getInstance();
    @FXML
    private Text usernameText;
    @FXML
    private TextFlow textPane;
    @FXML
    private BorderPane patientPane;
    @FXML
    private ToggleButton recordsBtn;
    @FXML
    private ToggleButton editProfileBtn;
    @FXML
    private ToggleButton logoutBtn;
    @FXML
    private ToggleButton makeAnAppointment;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUIonSamePane("../View/patientsRecords.fxml", patientPane);
        recordsBtn.setSelected(true);
        usernameText.setText(usernameText.getText() + user.getUsername());
        textPane.setTextAlignment(TextAlignment.CENTER);
        recordsBtn.setOnMouseClicked((MouseEvent event) -> {
            loadUIonSamePane("../View/patientsRecords.fxml", patientPane);
            clearSelectedButtons();
            recordsBtn.setSelected(true);
        });

        editProfileBtn.setOnMouseClicked((MouseEvent event) -> {
            loadUIonSamePane("../View/editProfile.fxml", patientPane);
            clearSelectedButtons();
            editProfileBtn.setSelected(true);
        });

        makeAnAppointment.setOnMouseClicked((MouseEvent event) -> {
            loadUIonSamePane("../View/Appointment.fxml", patientPane);
            clearSelectedButtons();
            makeAnAppointment.setSelected(true);
        });

        logoutBtn.setOnMouseClicked((MouseEvent event) -> {
            Stage currentStage = (Stage) patientPane.getScene().getWindow();
            try {
                setNewStage("../View/loginStyleFX.fxml", currentStage);
            } catch (IOException ex) {
                Logger.getLogger(PatientsDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    void clearSelectedButtons() {
        recordsBtn.setSelected(false);
        editProfileBtn.setSelected(false);
        makeAnAppointment.setSelected(false);
    }
}
