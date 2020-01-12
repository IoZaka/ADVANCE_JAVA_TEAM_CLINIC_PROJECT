/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabseContactDetails;
import advance_java_team_clinic_project.Model.User;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class CheckContactDetailsController implements Initializable {
    @FXML
    private Button submitBtn;
    @FXML
    private TextField email;
    @FXML
    private TextField relativeTelephoneNumber;
    @FXML
    private TextField telephoneNumber;
    @FXML
    private TextField cellphoneNumber;
    /**
     * Initializes the controller class.
     */
    private static DatabseContactDetails ak = new DatabseContactDetails();
    private ResultSet rs;
    User user = User.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        
        try {
            setData();
        } catch (SQLException ex) {
            Logger.getLogger(CheckAddressDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addTextLimiter(email);
        addTextLimiter(relativeTelephoneNumber);
        
        submitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {           
            try {
                ak.updateContactDetails(user.getContactID(), Integer.parseInt(telephoneNumber.getText()), Integer.parseInt(cellphoneNumber.getText()), email.getText(), Integer.parseInt(relativeTelephoneNumber.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(CheckContactDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        
        
        
    }

   private void setData() throws SQLException {
        ak.getObject();
        rs = ak.fetchContactInfoData(user.getContactID());
        if (rs.next()) {
            telephoneNumber.setText(String.valueOf(rs.getString("tel_number")));
            cellphoneNumber.setText(String.valueOf(rs.getString("cel_number")));
            email.setText(rs.getString("email"));
            relativeTelephoneNumber.setText(String.valueOf(rs.getString("relative_tel_number")));
        } 
        }
   
   
   public static void addTextLimiter(final TextField tf) {
    tf.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
            if (tf.getText().length() > 10) {
                String s = tf.getText().substring(0, 10);
                tf.setText(s);
            }
        }
    });
}
   
   
   
   
   
   
   
   
}