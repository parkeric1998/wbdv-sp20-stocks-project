package com.example.myapp.controllers;


import com.example.myapp.models.User;
import com.example.myapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
  @Autowired
  UserService userService;


  @PostMapping("/register")
  public User createUser(
          HttpSession session,
          @RequestBody User newUser) {
    User createdUser = userService.createUser(newUser);
    createdUser.setPassword("*");
    session.setAttribute("profile", createdUser);
    return createdUser;
  }

  @PostMapping("/login")
  public User loginUser(
          HttpSession session,
          @RequestBody User user) {
    User foundUser = userService.findUserByCredentials(user);
    foundUser.setPassword("*");
    session.setAttribute("profile", foundUser);
    return foundUser;
  }

  @PostMapping("/logout")
  public void logout(HttpSession session) {
    session.invalidate();
  }

  @GetMapping("/api/topics/{userId}")
  public User findUserById(
          @PathVariable("userId") Integer tid) {
    return userService.findUserById(tid);
  }

  @GetMapping("/api/topics")
  public List<User> findAllUsers() {
    return userService.findAllUsers();
  }


  @PutMapping("/api/topics/{topicId}")
  public int updateUser(
          @PathVariable("topicId") Integer tid, @RequestBody User updatedTopic) {
    return userService.updateUser(tid, updatedTopic);
  }

  @DeleteMapping("/api/topics/{topicId}")
  public int deleteUser(
          @PathVariable("topicId") Integer uid) {
    return userService.deleteUser(uid);
  }



}
