package co.edu.udec.taskmgr.domain.puertos;

import co.edu.udec.taskmgr.domain.entidades.User;
import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public interface IUserRepository {
    User findByEmail(String email);
    void save(User user);
    List<User> findAll();
    User update(User user);
}
