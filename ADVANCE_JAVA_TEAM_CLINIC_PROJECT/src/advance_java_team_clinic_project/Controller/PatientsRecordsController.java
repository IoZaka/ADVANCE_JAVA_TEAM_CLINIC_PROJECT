/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class PatientsRecordsController implements Initializable{
    

 
    private DatabaseLoginRecords  ak;
    private ResultSet rs;
    private ObservableList<ObservableList> data;  
    @FXML
    private TableView recordsTable;
    
 
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            ak = new DatabaseLoginRecords();
            ak.getObject();
            rs = ak.fetchBasicInfoData();
            data = FXCollections.observableArrayList();
            
            for(int i=0;i<rs.getMetaData().getColumnCount();i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }                    
                });
                
                recordsTable.getColumns().add(col);
                System.out.println("Column ["+i+"] ");
            }
            
            while(rs.next()){
                String patient = rs.getString("patient");
                System.out.println("gggggggggg"+patient);
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }
            recordsTable.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(PatientsRecordsController.class.getName()).log(Level.SEVERE, null, ex);
        }


            }    
}
    



