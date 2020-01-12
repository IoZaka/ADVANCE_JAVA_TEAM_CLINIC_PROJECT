/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class ProfileController implements Initializable {

    User user = User.getInstance();
    @FXML
    private Text firstNameText;
    @FXML
    private Text lastNameText;
    @FXML
    private Text usernameText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * 
         */
        firstNameText.setText(firstNameText.getText() + " " + user.getFirstName());
        lastNameText.setText(lastNameText.getText()+ " " + user.getSurname());
        usernameText.setText(usernameText.getText() + " " + user.getUsername());
    }    
    
}
