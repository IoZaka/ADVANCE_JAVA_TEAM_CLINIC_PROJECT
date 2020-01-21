package advance_java_team_clinic_project.Model;
/**
 *
 * @author Tasos
 */
public interface LoginRegisterDao {
     public void getObject();
     public boolean loginQuery(String userName, String passWord);
     public boolean registerQuery(String userName, String passWord);
     public String makeHashPwd(String passWord);
}
