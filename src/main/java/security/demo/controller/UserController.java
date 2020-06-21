package security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.demo.model.User;
import security.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/admin/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUsersById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }


    @GetMapping("/admin/all")
    public ResponseEntity<List<User>> getAllUSers(){
        List<User> all = userService.findAll();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(all);

    }
}
