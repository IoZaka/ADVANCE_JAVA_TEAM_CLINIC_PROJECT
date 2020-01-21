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
public class Tests {
    
    private StringProperty id = new SimpleStringProperty();
    private StringProperty diag_id = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty is_completed = new SimpleStringProperty();
    private StringProperty cost = new SimpleStringProperty();
    private StringProperty is_paid = new SimpleStringProperty();
    
    public StringProperty idProperty(){ return id;}
    public StringProperty diag_idProperty(){return diag_id;}
    public StringProperty descriptionProperty(){return description;}
    public StringProperty is_completedProperty(){return is_completed;}
    public StringProperty costProperty(){return cost;}
    public StringProperty is_paidProperty(){return is_paid;}
    
    public Tests(){}
    
}
