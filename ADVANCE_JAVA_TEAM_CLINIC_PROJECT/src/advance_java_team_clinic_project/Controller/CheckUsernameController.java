/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CheckUsernameController implements Initializable {
    
    @FXML
    private TextField newUsernameInput;
    @FXML
    private Button submitBtn;
    @FXML
    private ImageView statusIcon;
    @FXML
    private Text statusText;

  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newUsernameInput.addEventFilter(KeyEvent.KEY_RELEASED , usernameValidation("christos"));  
    }    
    
    public EventHandler<KeyEvent> usernameValidation(final String username) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if(txt_TextField.getText().equals(username)){
                    statusIcon.setImage(new Image(getClass().getResourceAsStream("../View/images/error.png")));
                    submitBtn.setDisable(true);
                    statusText.setText("Username already exists.");
                }else{
                    statusIcon.setImage(null);
                    statusText.setText("");
                    submitBtn.setDisable(false);
                }
            }
        };
    }    
    
}
