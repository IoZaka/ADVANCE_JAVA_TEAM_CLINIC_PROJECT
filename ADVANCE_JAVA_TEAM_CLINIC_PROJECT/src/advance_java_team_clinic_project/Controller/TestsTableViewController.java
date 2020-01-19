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
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class TestsTableViewController implements Initializable {

    @FXML
    private TableView testsTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}



/*
select a.id id, a.description, decode(a.is_completed,0,'No',1,'Yes') is_completed,cost,
decode(a.is_paid,0,'No',1,'Yes') is_paid, b.description status from pm_diag_tests a, 
pm_status b where a.status_id = b.id and diag_id = edw_tha_baleis_to_id_tou_diagnosis_me_java_kwdika ;
*/