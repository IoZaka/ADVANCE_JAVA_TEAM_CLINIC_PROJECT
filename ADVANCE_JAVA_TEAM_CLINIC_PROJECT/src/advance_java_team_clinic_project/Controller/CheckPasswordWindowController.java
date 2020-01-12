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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML
    private AnchorPane passwordPane;
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
                    String updateSql = "update pm_users set password = \'"+ newPassword +"\',updated_by = " + user.getId() +" where id = " + user.getId();
                    try {
                        rs = stmt.executeQuery(updateSql);
                    } catch (SQLException ex) {
                        Logger.getLogger(CheckPasswordWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Password successfully changed!");
                    alert.showAndWait();
                    Stage s = (Stage)passwordPane.getScene().getWindow();
                    s.close();
                }else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Wrong current password.");
                    alert.showAndWait();
                }
            }
        });
        
    }  
    
}
