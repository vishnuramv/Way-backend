package com.example.way.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "login")
    public ResponseEntity login(@RequestBody User user) {
        return ok(userService.login(user));
    }

    @PostMapping(path = "signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        System.out.println(user);
        userService.signup(user);
        return ok("success");
    }


    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") String userId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        userService.updateUser(userId, name, email);
    }
}
