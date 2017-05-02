package repairSystem.service;

/**
 * Created by Алексей on 01.05.2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repairSystem.dao.UserRepository;
import repairSystem.model.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    /*@Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }*/

    @Override
    public User findByUsername(String username) {
        return userRepository.findByLogin(username);
    }
}
