/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author Chris
 */
public class SignUpViewController implements Initializable{

    @FXML
    private TextField registerUsername;
    @FXML
    private PasswordField registerPassword;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Button backBtn;
    

    @FXML
    private Pane signUpPane;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
}
