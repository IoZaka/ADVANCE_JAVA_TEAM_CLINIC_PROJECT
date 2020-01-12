/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseInsuranceDetails;
import advance_java_team_clinic_project.Model.User;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CheckInsuranceDetailsController implements Initializable {

    @FXML
    private Button submitBtn;
    @FXML
    private TextField insuranceExpiredDate;
    @FXML
    private TextField european;
    @FXML
    private TextField ekas;
    @FXML
    private TextArea insureanceComments;
    
    private static DatabaseInsuranceDetails ak = new DatabaseInsuranceDetails();
    private ResultSet rs;
    User user = User.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            setData();
        } catch (SQLException ex) {
            Logger.getLogger(CheckAddressDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        submitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {           
            try {
                ak.updateInsuranceDetails(user.getId(), insuranceExpiredDate.getText(), european.getText(), ekas.getText(), insureanceComments.getText());
            } catch (SQLException ex) {
                Logger.getLogger(CheckContactDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
    }    
    
       private void setData() throws SQLException {
        ak.getObject();
        rs = ak.fetchInsuranceInfoData(user.getId());
        if (rs.next()) {
            insuranceExpiredDate.setText(rs.getString("ins_expire_date"));
            european.setText(rs.getString("european"));
            ekas.setText(rs.getString("ekas"));
            insureanceComments.setText(rs.getString("ins_comments"));
        } 
}
}

