/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class PasswordRecoveryController implements Initializable {

    @FXML
    private Pane RecoveryPane;
    @FXML
    private Button registerBtn;
    @FXML
    private ImageView backBtn;
    @FXML
    private TextField UsernameText;
    @FXML
    private ComboBox<?> ComboQuest;
    @FXML
    private TextField AnswerPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
