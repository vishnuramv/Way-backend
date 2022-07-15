package com.example.way.user;

import com.example.way.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Map login(User user){
        User userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional==null) {
            throw new IllegalStateException("No user present by this email: " + user.getEmail());
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        String token = jwtTokenProvider.createToken(userOptional.getEmail());
        Map<Object, Object> model = new HashMap<>();
        model.put("email", userOptional.getEmail());
        model.put("Token", token);
        return model;

    }

    public void signup(User user) {
        System.out.println(user);
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        User userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional== null) {
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);

    }

    public void deleteUser(String userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("No user present by this id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(String userId, String name, String email) {
        User user = userRepository.
                findById(userId).orElseThrow(
                        () -> new IllegalStateException("No user present by this id: " + userId)
                );
        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            User userOptional = userRepository.findUserByEmail(email);
            if (userOptional==null) {
                throw new IllegalStateException("Email taken");
            }
            user.setEmail(email);
        }
    }

}
