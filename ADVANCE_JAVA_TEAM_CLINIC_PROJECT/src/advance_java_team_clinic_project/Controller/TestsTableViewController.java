/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseConnection;
import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import advance_java_team_clinic_project.Model.Records;
import advance_java_team_clinic_project.Model.Tests;
import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class TestsTableViewController implements Initializable {

    private ObservableList data;  
    
    @FXML
    private TableView<Tests> testsTable = new TableView<>();
    
    TableColumn idCol = new TableColumn("Edit");
    TableColumn descriptionCol = new TableColumn("Description");
    TableColumn isCompletedCol = new TableColumn("Is Completed");
    TableColumn costCol = new TableColumn("Cost");
    TableColumn isPaidCol = new TableColumn("Is paid");
    @FXML
    private Button backBtn;
    @FXML
    private AnchorPane testsPane;
    
    User user = User.getInstance();
    Tests tests = new Tests();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        isCompletedCol.setCellValueFactory(new PropertyValueFactory<>("is_completed"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        isPaidCol.setCellValueFactory(new PropertyValueFactory<>("is_paid"));
        
        
        
        
        
    }     
    
    public void setTestID(String appID, Integer diagID){
        Callback<TableColumn<Tests, String>, TableCell<Tests, String>> cellFactory = new Callback<TableColumn<Tests, String>, TableCell<Tests, String>>() {
            public TableCell call(final TableColumn<Tests, String> param) {
                final TableCell<Tests, String> cell = new TableCell<Tests, String>() {
                    final Button btn = new Button();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Tests test = new Tests();
                            test = getTableView().getItems().get(getIndex());
                            btn.setText(test.idProperty().getValue());
                            btn.setOnAction(event -> {
                                    FXMLLoader loader = new FXMLLoader(TestsTableViewController.this.getClass().getResource("../View/testIDView.fxml"));
                                    Parent root = null;
                                    try {
                                        root = (Parent)loader.load();
                                    } catch (IOException ex) {
                                        Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    TestIDController id = loader.getController();
                                    String testID = btn.getText();
                                    id.setTestIDView(appID,Integer.valueOf(testID));
                                    //Scene
                                    testsPane.getChildren().clear();
                                    testsPane.getChildren().add(root);
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        idCol.setCellFactory(cellFactory);
        testsTable.getColumns().add(idCol);
        testsTable.getColumns().addAll(descriptionCol,isCompletedCol,costCol,isPaidCol);
        
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(TestsTableViewController.this.getClass().getResource("../View/DiagnosisInfoView.fxml"));
                    Parent root = (Parent)loader.load();
                    DiagnosisInfoController diagnosisID = loader.getController();
                    diagnosisID.setDiagnosisID(appID,diagID);
                    testsPane.getChildren().clear();
                    testsPane.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        try {
            data = FXCollections.observableArrayList(databaseTests(tests.getTestByDiagID(diagID)));
        } catch (SQLException ex) {
            Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
            testsTable.setItems(data);          
    } 
 

   
    
    public void setID(){ 
        Callback<TableColumn<Tests, String>, TableCell<Tests, String>> cellFactory = new Callback<TableColumn<Tests, String>, TableCell<Tests, String>>() {
            public TableCell call(final TableColumn<Tests, String> param) {
                final TableCell<Tests, String> cell = new TableCell<Tests, String>() {
                    final Button btn = new Button();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Tests test = new Tests();
                            test = getTableView().getItems().get(getIndex());
                            btn.setText(test.idProperty().getValue());
                            btn.setOnAction(event -> {
                                    FXMLLoader loader = new FXMLLoader(TestsTableViewController.this.getClass().getResource("../View/testIDView.fxml"));
                                    Parent root = null;
                                    try {
                                        root = (Parent)loader.load();
                                    } catch (IOException ex) {
                                        Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    TestIDController id = loader.getController();
                                    String testID = btn.getText();
                                    id.setTestIDView(Integer.valueOf(testID));
                                    //Scene
                                    testsPane.getChildren().clear();
                                    testsPane.getChildren().add(root);
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        idCol.setCellFactory(cellFactory);
        testsTable.getColumns().add(idCol);
        testsTable.getColumns().addAll(descriptionCol,isCompletedCol,costCol,isPaidCol);
        try {
            data = FXCollections.observableArrayList(databaseTests(tests.getAllTests()));
            testsTable.setItems(data); 
        } catch (SQLException ex) {
            Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
     private ArrayList databaseTests(ResultSet rs) throws SQLException {
        ArrayList<Tests> data = new ArrayList();
        
        while(rs.next()){
            Tests test = new Tests();
            test.idProperty().set(rs.getString("id"));
            //test.diag_idProperty().set(rs.getString("diag_id"));
            test.descriptionProperty().set(rs.getString("description"));
            test.is_completedProperty().set(rs.getString("is_completed"));
            test.costProperty().set(rs.getString("cost"));
            test.is_paidProperty().set(rs.getString("is_paid"));
            data.add(test);
        }
        return data;
    }
    
}



