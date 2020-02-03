/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.AppointmentsModel;
import advance_java_team_clinic_project.Model.CustomComboModel;
import advance_java_team_clinic_project.classes.CustomComboClass;
import advance_java_team_clinic_project.classes.RecordsClass;
import advance_java_team_clinic_project.classes.LoggedInUserClass;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class AppointmentRecordsController extends StageRedirect implements Initializable {

    private AppointmentsModel ak;
    private ResultSet rs;
    private ObservableList data;
    @FXML
    private TableView<RecordsClass> recordsTable = new TableView<>();

    TableColumn idCol = new TableColumn("CODE");
    TableColumn appDateCol = new TableColumn("APP DATE");
    TableColumn hourCol = new TableColumn("HOUR");
    TableColumn commentsCol = new TableColumn("COMMENTS");
    TableColumn createdDateCol = new TableColumn("CREATED");
    TableColumn updatedDateCol = new TableColumn("UPDATED");
    TableColumn patientCol = new TableColumn("PATIENT");
    TableColumn doctorCol = new TableColumn("DOCTOR");
    TableColumn updatedByCol = new TableColumn("UPDATED BY");
    TableColumn createdByCol = new TableColumn("CREATED BY");
    @FXML
    private Text textHead;
    LoggedInUserClass user = LoggedInUserClass.getInstance();
    @FXML
    private AnchorPane recordsPane;
    @FXML
    private ComboBox doctorComboBox;
    @FXML
    private ComboBox patientComboBox;
    @FXML
    private Text doctorHeader;
    @FXML
    private Text patientHeader;
    
    private CustomComboModel ed = new CustomComboModel();
    ObservableList<CustomComboClass> customCombo = FXCollections.observableArrayList();
    private Integer patientID;
    private Integer doctorID;
    @FXML
    private DatePicker createdFromDate;
    @FXML
    private DatePicker createdToDate;
    @FXML
    private Button searchBtn;
    @FXML
    private ComboBox appointmentsComboBox;
    @FXML
    private Text appointmentsHeader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //RECEPTION -> New Combo
        
       if(user.getRoleID() == 3){
           doctorComboBox.setVisible(false);
           patientComboBox.setVisible(false);
           doctorHeader.setVisible(false);
           patientHeader.setVisible(false);
        }else{
           doctorComboBox.setVisible(true);
           patientComboBox.setVisible(true);
           doctorHeader.setVisible(true);
           patientHeader.setVisible(true);
        }
           
       if(user.getRoleID() == 4){
           appointmentsComboBox.setVisible(true);
           appointmentsHeader.setVisible(true);
       }else{
           appointmentsHeader.setVisible(false);
           appointmentsComboBox.setVisible(false);
       }
       
       customCombo.addAll(new CustomComboClass(0,"New Appointments"), new CustomComboClass(1,"All Appointments"));
       appointmentsComboBox.setItems(FXCollections.observableArrayList(customCombo));
       
       recordsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       recordsTable.setId("tables");
       idCol.setCellValueFactory(new PropertyValueFactory<>("app_code"));
       appDateCol.setCellValueFactory(new PropertyValueFactory<>("app_date"));
       hourCol.setCellValueFactory(new PropertyValueFactory<>("hour"));
       commentsCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
       createdDateCol.setCellValueFactory(new PropertyValueFactory<>("created"));
       updatedDateCol.setCellValueFactory(new PropertyValueFactory<>("updated"));
       patientCol.setCellValueFactory(new PropertyValueFactory<>("patient"));
       doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctor"));
       updatedByCol.setCellValueFactory(new PropertyValueFactory<>("updated_by"));
       createdByCol.setCellValueFactory(new PropertyValueFactory<>("created_by"));
       
       
       Callback<TableColumn<RecordsClass, Void>, TableCell<RecordsClass, Void>>  cellFactory = (TableColumn<RecordsClass, Void> param) -> {
           TableCell<RecordsClass, Void> cell = new TableCell<RecordsClass, Void>() {
               private Button btn = new Button();
               
               @Override
               public void updateItem(Void item, boolean empty) {
                   super.updateItem(item, empty);
                   if (empty) {
                       setGraphic(null);
                   } else {
                       RecordsClass data = new RecordsClass();
                       data = getTableView().getItems().get(getIndex());
                       String appID = data.idProperty().getValue().substring(4);
                       btn.setText("OPEN");
                       btn.setOnMouseClicked((MouseEvent event) -> {
                           try {
                               FXMLLoader loader = new FXMLLoader(AppointmentRecordsController.this.getClass().getResource("../View/AppointmentRecordInfoView.fxml"));
                               Parent root = (Parent) loader.load();
                               AppointmentRecordInfoController id = loader.getController();
                               id.setID(appID);
                               //Scene
                               recordsPane.getChildren().clear();
                               recordsPane.getChildren().add(root);
                           } catch (IOException ex) {
                               Logger.getLogger(AppointmentRecordsController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       });
                       setGraphic(btn);
                   }
               }
           };
           return cell;
       };

        switch (user.getRoleID()) {
            case 2:
                textHead.setText("YOUR APPOINTMENTS");
                break;
            case 3:
                textHead.setText("YOUR RECORDS");
                break;
            case 4:
                textHead.setText("APPOINTMENTS");
                break;
        }

        try {
            ak = new AppointmentsModel();
            rs = ak.fetchBasicInfoData(user.getRoleID(), user.getId());
            data = FXCollections.observableArrayList(databaseRecords(rs));

            idCol.setCellFactory(cellFactory);
            recordsTable.getColumns().add(idCol);
            recordsTable.getColumns().addAll(appDateCol, hourCol, commentsCol,
                    createdDateCol, updatedDateCol, patientCol, doctorCol, updatedByCol, createdByCol);
            
            ed.getObject();
            customCombo = ed.FetchUserFilterData(3);
            patientComboBox.setItems(FXCollections.observableArrayList(customCombo));
            
            customCombo = ed.FetchUserFilterData(2);
            doctorComboBox.setItems(FXCollections.observableArrayList(customCombo));
            
            setComboEventListeners();
            
            recordsTable.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(AppointmentRecordsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        searchBtn.setOnMouseClicked((MouseEvent event) -> {
             System.out.println("Created From:" + createdFromDate.getValue());
             System.out.println("Created To:" + createdToDate.getValue());
             System.out.println("Patient: " + patientComboBox.getSelectionModel().getSelectedItem());
             System.out.println("Doctor: " + doctorComboBox.getSelectionModel().getSelectedItem());   
        });
        
    }
    
    private void setComboEventListeners(){
        patientComboBox.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomComboClass coPatient = (CustomComboClass) patientComboBox.getSelectionModel().getSelectedItem();
                patientID = coPatient.getId();
                System.out.println(coPatient.getId());
            }
        });
        
        doctorComboBox.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomComboClass coDoctor = (CustomComboClass) doctorComboBox.getSelectionModel().getSelectedItem();
                doctorID = coDoctor.getId();
                System.out.println(coDoctor.getId());
                
            }
        });
        
        appointmentsComboBox.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomComboClass coAppointment = (CustomComboClass) appointmentsComboBox.getSelectionModel().getSelectedItem();
                System.out.println(coAppointment.getId());
                
            }
        });
    }

    /**
     * Returns data from a database
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private ArrayList databaseRecords(ResultSet rs) throws SQLException {
        ArrayList<RecordsClass> data = new ArrayList();

        while (rs.next()) {
            RecordsClass record = new RecordsClass();
            record.idProperty().set(rs.getString("app_code"));
            record.app_dateProperty().set(rs.getString("app_date"));
            record.hourProperty().set(rs.getString("app_hour"));
            record.commentsProperty().set(rs.getString("comments"));
            record.createdProperty().set(rs.getString("created"));
            record.updatedProperty().set(rs.getString("updated"));
            record.patientProperty().set(rs.getString("patient"));
            record.doctorProperty().set(rs.getString("doctor"));
            record.updated_byProperty().set(rs.getString("updated_by"));
            record.created_byProperty().set(rs.getString("created_by"));
            data.add(record);
        }
        return data;
    }

}
