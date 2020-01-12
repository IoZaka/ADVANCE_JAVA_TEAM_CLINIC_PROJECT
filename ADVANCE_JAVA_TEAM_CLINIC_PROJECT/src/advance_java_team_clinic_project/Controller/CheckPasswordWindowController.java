/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseConnection;
import advance_java_team_clinic_project.Model.DatabaseLoginRegister;
import advance_java_team_clinic_project.Model.DatabaseProfileDetails;
import advance_java_team_clinic_project.Model.User;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
    
    private String hashPwd;
    User user = User.getInstance();
    private Statement stmt;
    
    private static final DatabaseProfileDetails ak = new DatabaseProfileDetails();
    private ResultSet rs;
    private DatabaseConnection object;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
        
        try {
            object = DatabaseConnection.getInstance();
            ak.getObject();
            stmt = object.connection.createStatement();
            rs = ak.getPassword(user.getId());
            if(rs.next()){
                hashPwd = rs.getString("password");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CheckPasswordWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        submitBtn.setDisable(true);
        passwordRepeatInput.addEventFilter(KeyEvent.KEY_RELEASED, newPasswordRepeatValidation());
        passwordInput.addEventFilter(KeyEvent.KEY_RELEASED, newPasswordValidation());
        
        submitBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String password = makeHashPwd(currentPassword.getText());
                if(password.equals(hashPwd)){
                    String newPassword = makeHashPwd(passwordRepeatInput.getText());
                    System.out.println(newPassword);
                }else{
                    System.out.println("Wrong password");
                }
            }
        });
        
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
   
   
   /**
     * Generates the string password to Hash.
     * @param passWord
     * @return 
     */
    private String makeHashPwd(String passWord) {
        String localPwd;
        String pwdSql;
        try {
            pwdSql = "SELECT DBMS_OBFUSCATION_TOOLKIT.md5(input => UTL_I18N.STRING_TO_RAW (\'" + passWord + "\', 'AL32UTF8')) pwd from dual";
            rs = stmt.executeQuery(pwdSql);
            if (rs.next()) {
                localPwd = rs.getString("pwd");
                rs.close();
                return localPwd;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseLoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
