/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.InsuranceCompanies;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class DoctorsPatientsController implements Initializable {

    @FXML
    private Pane profilePatientPane;
    @FXML
    private TableView<InsuranceCompanies> patientTable;

    final ObservableList<InsuranceCompanies> data = FXCollections.observableArrayList(
            new InsuranceCompanies(1, "Smith"),
            new InsuranceCompanies(2, "Johnson"),
            new InsuranceCompanies(3, "Williams"),
            new InsuranceCompanies(4, "Jones"),
            new InsuranceCompanies(5, "Brown")
    );
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn lastNameCol;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<InsuranceCompanies,Integer>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<InsuranceCompanies,String>("lastName"));
        patientTable.setItems(data);
    }    
    
}
