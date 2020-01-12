/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseCheckAddressDetails;
import advance_java_team_clinic_project.Model.DatabaseProfileDetails;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CheckAddressDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button submitBtn;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField county;
    @FXML
    private TextField postalCode;
    

    private static DatabaseCheckAddressDetails ak = new DatabaseCheckAddressDetails();
    private ResultSet rs;
    User user = User.getInstance();
    
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
                ak.updateAddressDetails(user.getId(), address.getText(), city.getText(), county.getText(), postalCode.getText());
            } catch (SQLException ex) {
                Logger.getLogger(CheckContactDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
    }
    

   private void setData() throws SQLException {
        ak.getObject();
        rs = ak.fetchAddressInfoData(user.getId());
        if (rs.next()) {
            address.setText(rs.getString("address"));
            city.setText(rs.getString("city"));
            county.setText(rs.getString("county"));
            postalCode.setText(rs.getString("postal_code"));  
        } 
  }
   
   
   
   
   
   
}
