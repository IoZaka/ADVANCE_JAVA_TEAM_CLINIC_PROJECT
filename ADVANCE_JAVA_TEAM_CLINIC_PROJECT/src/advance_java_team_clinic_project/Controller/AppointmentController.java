/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class AppointmentController implements Initializable {

    @FXML
    private Button submitBtn;
    @FXML
    private Pane appointmentPane;
    @FXML
    private TextArea reasonText;
    @FXML
    private DatePicker desiredDate;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        submitBtn.setOnMouseClicked((MouseEvent event) -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(AppointmentController.this.getClass().getResource("../View/appointmentSuccess.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            appointmentPane.getChildren().clear();
            appointmentPane.getChildren().add(root);
        });
    }

}
