package com.example.way.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody User user) {
        return ok(userService.login(user));
    }

    @PostMapping(path = "signup")
    public ResponseEntity<Map<Object, Object>> signup(@RequestBody User user) {
        System.out.println(user);
        return ok(userService.addNewUser(user));
    }

    @GetMapping(path = "{username}")
    public ResponseEntity<HashMap<String, Object>> getUser(@PathVariable String username) {
        System.out.println("Hello user ----->>>>    "+username);
        return ok(userService.getUser(username));
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

//    @PostMapping
//    public void registerNewUser(@RequestBody User user) {
//        userService.addNewUser(user);
//    }


    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping()
    public ResponseEntity<String> deleteUser() {
        return ok(userService.deleteUser());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        return ok(userService.updateUser(user));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "logout")
    public ResponseEntity<String> logout() {
        return ok(userService.logout());
    }
}
