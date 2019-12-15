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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class EditProfileController implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private ImageView detailsBtn;
    @FXML
    private ImageView logoutBtnIcon;
    @FXML
    private Button checkUsernameBtn;
    @FXML
    private Button insuranceBtn;
    @FXML
    private Button checkPasswordBtn;
    @FXML
    private Button addressBtn;
    @FXML
    private Button contactBtn;
    @FXML
    private AnchorPane editProfilePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       checkUsernameBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               try {
                   Parent root = FXMLLoader.load(getClass().getResource("../View/checkUsernameWindow.fxml"));
                   Stage stage = new Stage();
                   stage.setTitle("Check Username");
                   stage.setScene(new Scene(root, 400, 200));
                   stage.show();
               } catch (IOException ex) {
                   Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        });
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) {
        
    }
    
    
}
