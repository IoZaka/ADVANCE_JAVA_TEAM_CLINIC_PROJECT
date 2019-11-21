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
public class Diag_Tests {
    private int diag_id,is_completed,cost,is_paid,case_status;
    private String description,results;

    public int getDiag_id() {
        return diag_id;
    }

    public void setDiag_id(int diag_id) {
        this.diag_id = diag_id;
    }

    public int getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(int is_completed) {
        this.is_completed = is_completed;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getIs_paid() {
        return is_paid;
    }

    public void setIs_paid(int is_paid) {
        this.is_paid = is_paid;
    }

    public int getCase_status() {
        return case_status;
    }

    public void setCase_status(int case_status) {
        this.case_status = case_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
