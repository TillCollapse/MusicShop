package pl.musicland.dao;
import java.util.List;
import pl.musicland.model.User;
public interface UserDAO {
	public List<User> getAllUsers();
	public void insertUser(User user);
	public User loginUser(String email, String password);
}
