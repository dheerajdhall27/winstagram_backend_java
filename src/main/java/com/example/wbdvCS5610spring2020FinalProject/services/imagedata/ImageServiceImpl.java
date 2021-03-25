package com.example.wbdvCS5610spring2020FinalProject.services.imagedata;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.Image;
import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.ImageFromHashTag;
import com.example.wbdvCS5610spring2020FinalProject.models.user.User;
import com.example.wbdvCS5610spring2020FinalProject.repositories.HashTagRepository;
import com.example.wbdvCS5610spring2020FinalProject.repositories.ImageRepository;
import com.example.wbdvCS5610spring2020FinalProject.repositories.UserRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * This class represents the implementation of the {@link IImageService} it provides the
 * functionality to create an image search for an image.
 */
@Service
public class ImageServiceImpl implements IImageService {

  @Autowired
  ImageRepository imageRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  HashTagRepository hashTagRepository;

  @Override
  public List<Image> findAllImages() {
    return (List<Image>) imageRepository.findAll();
  }

  @Override
  public ResponseEntity<Image> createImage(HttpSession httpSession, Image image) {
    Image existingImage = imageRepository.findImageByUrl(image.getImageUrl());

    if (existingImage != null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    User sessionUser = (User) httpSession.getAttribute("profile");
    User existingUser = userRepository.findUserByHandle(sessionUser.getHandle());

    image.setUser(existingUser);
    image.setUserHandle(existingUser.getHandle());

    Image uploadedImage = imageRepository.save(image);

    existingUser.getImageList().add(uploadedImage);

    userRepository.save(existingUser);

    return new ResponseEntity<>(uploadedImage, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<Image> uploadProfileImage(User user, Image image) {
    Image existingImage = imageRepository.findImageByUrl(image.getImageUrl());

    if(existingImage != null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    Image uploadedImage = imageRepository.save(image);

    User existingUser = userRepository.findUserByHandle(user.getHandle());
//    existingUser.setProfileImage(uploadedImage);

    userRepository.save(existingUser);

    return new ResponseEntity<>(uploadedImage, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<Image> findImageByUrl(String imageUrl) {
    Image image = imageRepository.findImageByUrl(imageUrl);

    if (image == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(image, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<List<ImageFromHashTag>> findAllImagesWithHashTag(String hashTagText) {
    List<ImageFromHashTag> imageList = imageRepository.findAllImagesWithAHashTag(hashTagText);
    if (imageList == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    for(int i = 0;i < imageList.size(); i++) {
      System.out.println(imageList.get(i));
    }

    return new ResponseEntity<>(imageList, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<Image> getImageById(Integer id) {
    if(!imageRepository.findById(id).isPresent()) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(imageRepository.findById(id).get(), HttpStatus.ACCEPTED);
  }

  @Override
  public Integer deleteImageById(Integer id) {
    hashTagRepository.deleteImageById(id);
    imageRepository.deleteById(id);

    return 1;
  }
}
