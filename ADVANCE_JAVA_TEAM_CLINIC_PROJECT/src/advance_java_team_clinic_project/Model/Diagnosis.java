/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Model;

/**
 *
 * @author Tasos
 */
public class Diagnosis {
  private int app_info_id,patient_type;

    public int getApp_info_id() {
        return app_info_id;
    }

    public void setApp_info_id(int app_info_id) {
        this.app_info_id = app_info_id;
    }

    public int getPatient_type() {
        return patient_type;
    }

    public void setPatient_type(int patient_type) {
        this.patient_type = patient_type;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMeds() {
        return meds;
    }

    public void setMeds(String meds) {
        this.meds = meds;
    }
  private String comments,meds;  
}
