package repairSystem.service;

import repairSystem.model.User;

/**
 * Created by Алексей on 01.05.2017.
 */
public interface UserService {

//    public void save(User user);
    public User findByUsername(String username);
}
