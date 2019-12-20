/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.User;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class PatientsRecordViewController extends NewStage implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private ImageView detailsBtn;
    @FXML
    private ListView<String> recordsListView;

    ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3", "4");
    @FXML
    private Button refreshBtn;
    @FXML
    private AnchorPane recordsPane;
    @FXML
    private Pane detailsPane;
    @FXML
    private ImageView logoutBtnIcon;
    @FXML
    private Button editProfileBtn;
    @FXML
    private Button aboutBtn;
    @FXML
    private Button logOutBtn;
    User user = User.getInstance();
    @FXML
    private Text logged_as;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recordsListView.setItems(list);
        recordsListView.setPrefHeight(list.size() * 24); //default size each row = 24
        logged_as.setText(logged_as.getText() + user.getSurname() + " " + user.getFirstName() + ".");
    }

    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException {

        Stage currentStage = (Stage)recordsPane.getScene().getWindow();;
        
        final Node source = (Node) event.getSource();
        String id = source.getId();
        
        if(id.equals("detailsBtn")){
            if(detailsPane.getOpacity() == 1){detailsPane.setOpacity(0);}
            else{detailsPane.setOpacity(1);}
        }else if(id.equals("logoutBtnIcon") || id.equals("logOutBtn")){
            user.setInstance(user);
            setNewStage("../View/loginStyleFX.fxml", currentStage);
        }else if(id.equals("editProfileBtn")){
            setNewStage("../View/editProfile.fxml", currentStage);
        }
        currentStage.show();
    }

}
