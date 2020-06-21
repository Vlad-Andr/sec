package security.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import security.demo.model.User;
import security.demo.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;



    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> userById = userRepo.getUserById(id).isPresent()?userRepo.getUserById(id):Optional.empty();
        return userById;
    }

    @Override
    public boolean existByUsername(String username) {
        boolean existUser = userRepo.existsByUsername(username);
        return existUser;
    }

    @Override
    public void deleteByUsername(String username) {
        userRepo.deleteByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username).isPresent()?userRepo.findByUsername(username):Optional.empty();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s).get();
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
