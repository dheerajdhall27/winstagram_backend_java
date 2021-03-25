package com.example.wbdvCS5610spring2020FinalProject.services.user;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.Image;
import com.example.wbdvCS5610spring2020FinalProject.models.user.User;
import com.example.wbdvCS5610spring2020FinalProject.repositories.UserRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * This class represents the implementation of the {@link IUserService} which provides the
 * functionality to deal with methods related to a User.
 */
@Service
public class UserServiceImpl implements IUserService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> findAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  @Override
  public ResponseEntity<User> findUserByHandle(String handle) {
    User user = userRepository.findUserByHandle(handle);
    HttpHeaders headers = new HttpHeaders();

    if(user == null) {
      headers.set("UserInformation", "NotFound");
      return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    }

    headers.set("UserInformation", "Found");

    return new ResponseEntity<>(user, headers, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<List<User>> searchUserByHandle(String handle) {
    List<User> userList = userRepository.findUserByHandleForSearch(handle);

    if(userList == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(userList, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<User> createUser(HttpSession httpSession, User newUser) {
    String userHandle = newUser.getHandle();
    User existingUser = userRepository.findUserByHandle(userHandle);

    HttpHeaders headers = new HttpHeaders();
    if(existingUser != null) {
      headers.set("UserInformation", "AlreadyExists");
      newUser.setPassword("******");
      return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    }

    userRepository.save(newUser);
    httpSession.setAttribute("profile", newUser);
    newUser.setPassword("********");

    headers.set("UserInformation", "Created");
    return new ResponseEntity<>(newUser, headers, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<User> loginUser(HttpSession httpSession, User user) {
    if(user.getHandle().equals("") && user.getPassword().equals("")) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    } else if(user.getHandle() == null || user.getPassword() == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    User existingUser = userRepository.findUserByCredentials(user.getHandle(), user.getPassword());

    HttpHeaders headers = new HttpHeaders();
    if(existingUser == null) {
      headers.set("Login", "NoUser");
      user.setPassword("*******");
      return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    }

    httpSession.setAttribute("profile", existingUser);
    headers.set("Login", "FoundUser");
    existingUser.setPassword("******");
    return new ResponseEntity<>(existingUser, headers, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<User> updateUser(HttpSession httpSession, User user) {
    User sessionUser = (User) httpSession.getAttribute("profile");

    if(sessionUser == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    User existingUser = userRepository.findUserByHandle(sessionUser.getHandle());

    existingUser.setEmail(user.getEmail());
    existingUser.setUserType(user.getUserType());
//    existingUser.setFirstName(user.getFirstName());
//    existingUser.setLastName(user.getLastName());
    existingUser.setProfileImageUrl(user.getProfileImageUrl());
    User updatedUser = userRepository.save(existingUser);

    existingUser.setPassword("******");
    return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
  }

  @Override
  public void logoutUser(HttpSession httpSession) {
    httpSession.invalidate();
  }

  @Override
  public ResponseEntity<User> getProfileInformation(HttpSession httpSession) {
    User profile = (User) httpSession.getAttribute("profile");

    if(profile == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    User user = userRepository.findUserByHandle(profile.getHandle());

    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<List<Image>> getAllImagesForUser(User user) {
    User existingUser = userRepository.findUserByHandle(user.getHandle());

    if (existingUser == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    List<Image> imageList = existingUser.getImageList();

    return new ResponseEntity<>(imageList, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<Image> getProfileImage(User user) {
    User existingUser = userRepository.findUserByHandle(user.getHandle());

    if (existingUser == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

//    Image image = existingUser.getProfileImage();

    return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
  }
}
