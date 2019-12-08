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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class Success_AppointmentController implements Initializable {

    @FXML
    private AnchorPane successAppPane;
    @FXML
    private Button homeBtn;
    @FXML
    private ImageView detailsBtn;
    @FXML
    private ImageView logoutBtnIcon;
    @FXML
    private Hyperlink clickHereBtn;
    @FXML
    private Pane detailsPane;
    @FXML
    private Button editProfileBtn;
    @FXML
    private Button aboutBtn;
    @FXML
    private Button logOutBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException {
        Stage currentStage = (Stage)successAppPane.getScene().getWindow();;
        Parent root;
        Scene scene;
        
        final Node source = (Node) event.getSource();
        String id = source.getId();
        
        
        if(id.equals("detailsBtn")){
            if(detailsPane.getOpacity() == 1){detailsPane.setOpacity(0);}
            else{detailsPane.setOpacity(1);}
        }else if(id.equals("logoutBtnIcon") || id.equals("logOutBtn")){
            root = FXMLLoader.load(getClass().getResource("../View/loginStyleFX.fxml"));
            scene = new Scene(root);
            currentStage.setScene(scene);
        }else if(id.equals("clickHereBtn")){
            root = FXMLLoader.load(getClass().getResource("../View/patientsRecordsStyle.fxml"));
            scene = new Scene(root);
            currentStage.setScene(scene);
        }
        currentStage.show();
    }

    
  
    
    
}
