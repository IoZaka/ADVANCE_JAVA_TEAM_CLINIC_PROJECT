/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Chris
 */
public class Records {
    
    private StringProperty id = new SimpleStringProperty();
    private StringProperty app_date = new SimpleStringProperty();
    private StringProperty comments = new SimpleStringProperty();
    private StringProperty app_code = new SimpleStringProperty();
    private StringProperty created = new SimpleStringProperty();
    private StringProperty updated = new SimpleStringProperty();
    private StringProperty patient = new SimpleStringProperty();
    private StringProperty doctor = new SimpleStringProperty();
    private StringProperty updated_by = new SimpleStringProperty();
    private StringProperty created_by = new SimpleStringProperty();

    
    public StringProperty idProperty(){ return id;}
    public StringProperty app_dateProperty(){return app_date;}
    public StringProperty commentsProperty(){return comments;}
    public StringProperty app_codeProperty(){return app_code;}
    public StringProperty createdProperty(){return created;}
    public StringProperty updatedProperty(){return updated;}
    public StringProperty patientProperty(){return patient;}
    public StringProperty doctorProperty(){return doctor;}
    public StringProperty updated_byProperty(){return updated_by;}
    public StringProperty created_byProperty(){return created_by;}
    
//    public Records(String id, String app_date, String comments, String app_code, String created, String updated, String patient, String doctor, String updated_by, String created_by){
//      this.id.set(id);
//      this.app_date.set(app_date);
//      this.comments.set(comments);
//      this.app_code.set(app_code);
//      this.created.set(created);
//      this.updated.set(updated);
//      this.patient.set(patient);
//      this.doctor.set(doctor);
//      this.updated_by.set(updated_by);
//      this.created_by.set(created_by);
//    }
    
    public Records(){}
    
    
}
