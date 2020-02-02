/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;

/**
 *
 * @author Vincent
 */
public interface AdmissionDao {

    public void getObject();

    public ResultSet fetchAdmissionData(Integer ID);

    public boolean createAdmissionData(Integer diagID, Integer costPerDay, String room, String bed, String ad_date);

    public boolean updateAdmissionData(Integer ID, Integer costPerDay, String room, String bed, String ad_date, String dis_date);

    public void updateIsPaid(Integer ID, Integer amount);

}
