package com.example.way.user;

import com.example.way.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;


    public Map<Object, Object> login(User user){
        User userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional==null) {
            throw new IllegalStateException("No user present by this email: " + user.getEmail());
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        String token = jwtTokenProvider.createToken(userOptional.getEmail());
        Map<Object, Object> model = new HashMap<>();
        model.put("email", userOptional.getEmail());
        model.put("name", userOptional.getName());
        model.put("username", userOptional.getUsername());
        model.put("token", token);
        model.put("dp_url", userOptional.getDpUrl());
        model.put("dob", userOptional.getDob());
        model.put("userId", userOptional.getId());
        return model;
    }

//    public void signup(User user) {
//        System.out.println(user);
//        userRepository.save(user);
//    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public HashMap<String, Object> getUser(String username) {
        HashMap<String, Object> output = new HashMap<>();
        if(userRepository.findUserByUsername(username)==null) {
            throw new UsernameNotFoundException("No user present by this email: " + username);
        }
        User user = userRepository.findUserByUsername(username);
        output.put("user", user);
        output.put("message", "User found");
        return output;
    }

    public Map<Object, Object> addNewUser(User user) {
        User userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional!= null) {
            throw new IllegalStateException("email taken");
        }
        if(userRepository.findUserByUsername(user.getUsername())!=null) {
            throw new IllegalStateException("username taken");
        }
        userRepository.save(user);
        Map<Object, Object> model = login(user);
        return model;
    }

    public String deleteUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exists = userRepository.existsById(user.getUsername());
        if (!exists) {
            throw new IllegalStateException("No user present by this id: " + user.getUsername());
        }
        userRepository.deleteById(user.getUsername());
        return ("User deleted");
    }

    @Transactional
    public String updateUser(User user) {
        System.out.println("jello");
        UserDetails userReq = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userReq.getUsername());
        User userOptional = userRepository.findUserById(userReq.getUsername());
        System.out.println("hello");
        System.out.println(userOptional.getUsername());
        System.out.println(user.getUsername());

//        userRepository.UpdateUser(user.getEmail(), user.getUsername(), user.getName(),user.getDob(), user.isPublic(), user.getPassword(), user.getDpUrl(), userOptional.getId());

        if (user.getName() != null && user.getName().length() > 0 && !Objects.equals(user.getName(), userOptional.getName())) {
            userOptional.setName(user.getName());
        }
        if (user.getDob() != null && !Objects.equals(user.getDob(), userOptional.getDob())) {
            userOptional.setDob(user.getDob());
        }
        if (user.getEmail() != null && user.getEmail().length() > 0 && !Objects.equals(user.getEmail(), userOptional.getEmail())) {
            if(userRepository.findUserByEmail(user.getEmail())!=null) {
                throw new IllegalStateException("email taken");
            }
            userOptional.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && user.getPassword().length() > 0 && !Objects.equals(user.getPassword(), userOptional.getPassword())) {
            userOptional.setPassword(user.getPassword());
        }
        if (!Objects.equals(user.isPublic(), userOptional.isPublic())) {
            userOptional.setPublic(user.isPublic());
        }
        if (user.getDpUrl() != null && user.getDpUrl().length() > 0 && !Objects.equals(user.getDpUrl(), userOptional.getDpUrl())) {
            userOptional.setDpUrl(user.getDpUrl());
        }

        if(user.getUsername() != null && user.getUsername().length() > 0 && !Objects.equals(user.getUsername(), userOptional.getUsername())) {
            if(userRepository.findUserByUsername(user.getUsername())!=null) {
                throw new IllegalStateException("username taken");
            }
            userOptional.setUsername(user.getUsername());
        }
        userRepository.save(userOptional);
        return ("User updated successfully");
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("hello --->>" +email);
        User user = userRepository.findUserByEmailEquals(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), new ArrayList<>());

    }
}
