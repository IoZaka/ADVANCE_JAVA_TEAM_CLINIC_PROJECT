package advance_java_team_clinic_project.Model;

import java.util.List;

/**
 *
 * @author Tasos
 */
public interface UserDao {
    public List<User> getAllUsers();
    public User getUser(int userId);
    public void updateUser(User user);
    public void deleteUser(User user);
}
