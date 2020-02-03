package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;

/**
 *
 * @author Tasos
 */
public interface InsuranceDetailsDao {

    public void getObject();
    
    public ResultSet fetchInsuranceInfoData(Integer userId);

    public void updateInsuranceDetails(Integer userId, String ins_expire_date, Integer european, Integer ekas, String ins_comments, Integer ins_comp_id);
}
