/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.LoggedInUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class ProfileController implements Initializable {

    LoggedInUser user = LoggedInUser.getInstance();
    @FXML
    private Text firstNameText;
    @FXML
    private Text lastNameText;
    @FXML
    private Text usernameText;
    @FXML
    private AnchorPane profilePane;
    @FXML
    private Text roleText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstNameText.setText(firstNameText.getText() + " " + user.getFirstName());
        lastNameText.setText(lastNameText.getText() + " " + user.getSurname());
        usernameText.setText(usernameText.getText() + " " + user.getUsername());

        switch (user.getRoleID()) {
            case 1:
                roleText.setText(roleText.getText() + "ADMIN");
                break;
            case 2:
                roleText.setText(roleText.getText() + "DOCTOR");
                break;
            case 4:
                roleText.setText(roleText.getText() + "RECEPTION");
                break;
            case 5:
                roleText.setText(roleText.getText() + "CLINIC");
                break;
        }

    }

}
