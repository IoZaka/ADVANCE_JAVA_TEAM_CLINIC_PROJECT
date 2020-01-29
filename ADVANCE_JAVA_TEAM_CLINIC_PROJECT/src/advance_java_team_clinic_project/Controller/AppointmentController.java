/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseAppointment;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class AppointmentController implements Initializable {

    @FXML
    private Button submitBtn;
    @FXML
    private TextArea reasonText;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private DatabaseAppointment ap = new DatabaseAppointment();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ap.getObject();
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        submitBtn.setOnMouseClicked((MouseEvent event) -> {
            ap.makeAppointment(reasonText.getText());
            reasonText.clear();
            alert.setTitle("Success");
            alert.setContentText("Appointment successfully submitted");
            alert.showAndWait();
        });
    }

}
