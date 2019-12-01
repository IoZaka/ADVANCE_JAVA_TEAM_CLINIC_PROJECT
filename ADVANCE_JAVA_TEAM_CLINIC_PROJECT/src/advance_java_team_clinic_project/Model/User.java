/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

public class User {
    
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String surname;
//    private ContactDetails contactDetails;
//    private AddressDetails addressDetails;
    private int addressID;
    private int contactID;
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getSurname() {return surname;}
    public void setSurname(String surname) {this.surname = surname;}

    public int getAddressID() {return addressID;}
    public void setAddressID(int addressID) {this.addressID = addressID;}

    public int getContactID() {return contactID;}
    public void setContactID(int contactID) {this.contactID = contactID;}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
    
}
