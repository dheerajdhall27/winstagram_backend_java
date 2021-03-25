package com.example.wbdvCS5610spring2020FinalProject.services.imagedata;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.Image;
import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.ImageFromHashTag;
import com.example.wbdvCS5610spring2020FinalProject.models.user.User;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

/**
 * This interface represents the Image service which provides the functionality to add Images and
 * search for images.
 */
public interface IImageService {

  /**
   * This method is to list all the images that exist in the data base.
   *
   * @return a list of images that are in the Data Base.
   */
  List<Image> findAllImages();

  /**
   * This method is used to create a new Image.
   *
   * @param httpSession current session of the browser
   * @param image the image object that has to be saved
   * @return the saved Image object.
   */
  ResponseEntity<Image> createImage(HttpSession httpSession, Image image);

  /**
   * This method is used to upload the profile image for a certain user.
   * @param user the user whose profile image is being uploaded
   * @param image the image user used to upload
   * @return an Image object after upload is complete bad request otherwise
   */
  ResponseEntity<Image> uploadProfileImage(User user, Image image);

  /**
   * This method is used to find an image using the Url provided.
   *
   * @param imageUrl the url which is used to search an image
   * @return the Image object found using the url otherwise Bad Request.
   */
  ResponseEntity<Image> findImageByUrl(String imageUrl);

  /**
   * This method is used to get all the images associated with the hash tag.
   *
   * @param hashTagText the hash tag text used to get all the images.
   * @return the List of images associated with a has tag otherwise a bad request.
   */
  ResponseEntity<List<ImageFromHashTag>> findAllImagesWithHashTag(String hashTagText);


  /**
   * This method is used to get a particular image by using the id
   * @param id the id being used to search a particular image
   * @return the Image that was requested
   */
  ResponseEntity<Image> getImageById(Integer id);


  /**
   * THis method is used to delete an image from the DB
   * @param id the id  of  the image that is being deleted
   * @return 1 if delete was successful 0 otherwise
   */
  Integer deleteImageById(Integer id);
}
