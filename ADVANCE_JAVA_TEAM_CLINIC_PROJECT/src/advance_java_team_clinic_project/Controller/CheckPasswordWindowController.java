/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseProfileDetails;
import advance_java_team_clinic_project.Model.User;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CheckPasswordWindowController implements Initializable {

    @FXML
    private PasswordField passwordInput;
    @FXML
    private PasswordField passwordRepeatInput;
    @FXML
    private PasswordField currentPassword;
    @FXML
    private Button submitBtn;
    User user = User.getInstance();
    
    
    private static final DatabaseProfileDetails ak = new DatabaseProfileDetails();
    private ResultSet rs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
        try {
            ak.getObject();
            rs = ak.getPassword(user.getId());
             if(rs.next()){
                System.out.println(rs.getString("password"));
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(CheckPasswordWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        submitBtn.setDisable(true);
        passwordRepeatInput.addEventFilter(KeyEvent.KEY_RELEASED, newPasswordRepeatValidation());
        passwordInput.addEventFilter(KeyEvent.KEY_RELEASED, newPasswordValidation());
        
    }  
    
   public EventHandler<KeyEvent> newPasswordRepeatValidation() {
        return (KeyEvent e) -> {
            PasswordField txt_TextField = (PasswordField) e.getSource();
            if(txt_TextField.getText().equals(passwordInput.getText()) && !currentPassword.getText().isEmpty()){
                submitBtn.setDisable(false);
            }else{
                submitBtn.setDisable(true);
            }
        };
     }
   
   public EventHandler<KeyEvent> newPasswordValidation() {
        return (KeyEvent e) -> {
            PasswordField txt_TextField = (PasswordField) e.getSource();
            if(txt_TextField.getText().equals(passwordRepeatInput.getText()) && !currentPassword.getText().isEmpty()){
                submitBtn.setDisable(false);
            }else{
                submitBtn.setDisable(true);
            }
        };
     }
   
}
