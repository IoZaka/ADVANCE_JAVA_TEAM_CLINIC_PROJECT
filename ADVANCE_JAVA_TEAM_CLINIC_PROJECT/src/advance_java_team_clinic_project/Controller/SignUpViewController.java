/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;


import advance_java_team_clinic_project.Model.DatabaseModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private Pane signUpPane;
    @FXML
    private Button submitBtn;
    @FXML
    private ImageView backBtn;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    handleBackBtn(event);
                } catch (IOException ex) {
                    Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void handleBackBtn(MouseEvent event) throws IOException{
        Stage currentStage = (Stage)signUpPane.getScene().getWindow();;
        Parent root;
        Scene scene;
        
        final Node source = (Node) event.getSource();
        String id = source.getId();
        
        if(id.equals("backBtn")){
           root = FXMLLoader.load(getClass().getResource("../View/loginStyleFX.fxml"));
            scene = new Scene(root);
            currentStage.setScene(scene);
        }
        currentStage.show();
    }
       
    
}
