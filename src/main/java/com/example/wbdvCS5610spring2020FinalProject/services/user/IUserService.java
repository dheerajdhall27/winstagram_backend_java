package com.example.wbdvCS5610spring2020FinalProject.services.user;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.Image;
import com.example.wbdvCS5610spring2020FinalProject.models.user.User;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import org.springframework.http.ResponseEntity;

/**
 * This interface represents the user service which provides the functionality to add users, login
 * users, look for user information...
 */
public interface IUserService {

  /**
   * This method lists all the users saved in the Database.
   * @return a list of all the users
   */
  List<User> findAllUsers();

  /**
   * This method is used to Find a specific user by using the user handle (username).
   * @param handle the username that the user chose when signing up.
   * @return the user info if exists otherwise a Bad Request.
   */
  ResponseEntity<User> findUserByHandle(String handle);

  /**
   *
   * @param handle
   * @return
   */
  ResponseEntity<List<User>> searchUserByHandle(String handle);

  /**
   * This method is used to create a new user. This method is called when the a new user is
   * registering.
   * @param httpSession this is to store the current session for the user.
   * @param user the user whose information needs to be saved and created.
   * @return a response entity stating the new user was created.
   */
  ResponseEntity<User> createUser(HttpSession httpSession,  User user);

  /**
   * This method is used to log a user in. It checks to see if the user is already logged in.
   * @param httpSession the current session of the browser.
   * @param user the User data that us being logged in.
   * @return a response entity stating if login was successful other wise a Bad Request.
   */
  ResponseEntity<User> loginUser(HttpSession httpSession, User user);

  /**
   * This method is used to update details of a particular user.
   * @param httpSession the current session of the user
   * @param user the data of the user being updated
   * @return the new user data after updating the user if user does not exist the Bad Request.
   */
  ResponseEntity<User> updateUser(HttpSession httpSession, User user);

  /**
   * This method is used to log the user out. It invalidates the current session.
   * @param httpSession the current session the user is on
   */
  void logoutUser(HttpSession httpSession);

  /**
   * This method is used to get the profile information of a particular user.
   * @param httpSession the current session of the user
   * @return the User information
   */
  ResponseEntity<User> getProfileInformation(HttpSession httpSession);

  /**
   * This method is used to get all the Images uploaded by a user
   * @param user the user whose uploaded images are required
   * @return a list of images uploaded by the user
   */
  ResponseEntity<List<Image>> getAllImagesForUser(User user);


  /**
   * This method is used to get the profile image of a user.
   * @param user the user whose profile image is required
   * @return an th profile image uploaded by the user.
   */
  ResponseEntity<Image> getProfileImage(User user);
}
