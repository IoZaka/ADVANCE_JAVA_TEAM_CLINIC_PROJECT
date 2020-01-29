package advance_java_team_clinic_project.Model;

import java.util.List;

/**
 *
 * @author Tasos
 */
public interface UserDao {
    public List<Users> getAllUsers();
    public Users getUser(int userId);
    public void updateUser(Users user);
    public void deleteUser(Users user);
}
