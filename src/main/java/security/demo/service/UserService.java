package security.demo.service;

import security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);

    boolean existByUsername(String username);

    void deleteByUsername(String username);

    void saveUser(User user);

    Optional<User> findByUsername(String username);

    List<User> findAll();
}
