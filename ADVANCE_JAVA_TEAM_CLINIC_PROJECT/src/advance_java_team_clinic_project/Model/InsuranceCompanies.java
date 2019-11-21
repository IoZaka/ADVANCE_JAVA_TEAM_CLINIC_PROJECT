package advance_java_team_clinic_project.Model;


public class InsuranceCompanies {
    private int id;
    private String description;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public InsuranceCompanies(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
