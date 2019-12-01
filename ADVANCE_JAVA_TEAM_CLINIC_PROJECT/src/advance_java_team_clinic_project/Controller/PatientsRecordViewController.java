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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class PatientsRecordViewController implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private Button editProfileBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private ListView<String> recordsListView;

    ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4");
    @FXML
    private Button refreshBtn;
    @FXML
    private AnchorPane recordsPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       recordsListView.setItems(list);
       recordsListView.setPrefHeight(list.size()*24); //default size each row = 24
       
       logoutBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e) {
                try {
                    handleButtonAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

           
        });
       
    }
    
    private void handleButtonAction(ActionEvent event) throws IOException { 
        
        Stage currentStage = (Stage)recordsPane.getScene().getWindow();;
        Parent root;
        Scene scene;
        
        final Node source = (Node) event.getSource();
        String id = source.getId();

        if(id.equals("logoutBtn")){
            root = FXMLLoader.load(getClass().getResource("../View/loginStyleFX.fxml"));
            scene = new Scene(root);
            currentStage.setScene(scene);
        }
        currentStage.show();
    }
    
}
