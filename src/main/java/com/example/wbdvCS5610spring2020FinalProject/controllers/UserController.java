package com.example.wbdvCS5610spring2020FinalProject.controllers;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.Image;
import com.example.wbdvCS5610spring2020FinalProject.models.user.User;
import com.example.wbdvCS5610spring2020FinalProject.services.user.IUserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {

  @Autowired
  IUserService userService;

  @GetMapping("/api/users")
  public List<User> findAllUsers() {
    return userService.findAllUsers();
  }

  @GetMapping("/api/users/{handle}")
  public ResponseEntity<User> findUserByHandle(@PathVariable("handle") String handle) {
    return userService.findUserByHandle(handle);
  }

  @GetMapping("/api/users/search/{handle}")
  public ResponseEntity<List<User>> searchUsersByHandle(@PathVariable("handle") String handle){
    return userService.searchUserByHandle(handle);
  }

  @PostMapping("/api/users/register")
  public ResponseEntity<User> createUser(HttpSession httpSession, @RequestBody User newUser){
    return userService.createUser(httpSession, newUser);
  }

  @PostMapping("/api/users/login")
  public ResponseEntity<User> loginUser(HttpSession httpSession, @RequestBody User user) {
    return userService.loginUser(httpSession, user);
  }

  @PutMapping("/api/users/update")
  public ResponseEntity<User> updateUser(HttpSession httpSession, @RequestBody User user) {
    return userService.updateUser(httpSession, user);
  }

  @PostMapping("/api/users/logout")
  public void logoutUser(HttpSession httpSession) {
    userService.logoutUser(httpSession);
  }

  @PostMapping("/api/users/profile")
  public ResponseEntity<User> getProfileInformation(HttpSession httpSession) {
    return userService.getProfileInformation(httpSession);
  }

  @GetMapping("/api/users/profileimage")
  public ResponseEntity<Image> getProfileImage(@RequestBody User user) {
    return userService.getProfileImage(user);
  }

  @GetMapping("api/users/images")
  public ResponseEntity<List<Image>> getAllImages(@RequestBody User user) {
    return userService.getAllImagesForUser(user);
  }
}
