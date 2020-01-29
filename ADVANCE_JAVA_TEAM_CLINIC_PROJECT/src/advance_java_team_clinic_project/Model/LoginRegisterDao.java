/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

/**
 *
 * @author Tasos
 */
public interface LoginRegisterDao {

    public void getObject();

    public boolean loginQuery(String userName, String passWord);

   public boolean registerQuery(String userName, String passWord, Integer question1, Integer question2, String answer1, String answer2);

    public String makeHashPwd(String passWord);
}
