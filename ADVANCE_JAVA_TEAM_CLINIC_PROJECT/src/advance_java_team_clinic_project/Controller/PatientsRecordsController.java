/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class PatientsRecordsController extends NewStage implements Initializable {

    @FXML
    private ListView<String> recordsList;

    ObservableList<String> rLists = FXCollections.observableArrayList("1","2","3","4");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recordsList.setItems(rLists);
        recordsList.setPrefHeight(rLists.size() * 24); // default size each row = 24
    }    
    
}
