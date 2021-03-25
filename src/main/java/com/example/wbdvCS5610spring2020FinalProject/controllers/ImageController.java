package com.example.wbdvCS5610spring2020FinalProject.controllers;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.HashTag;
import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.Image;
import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.ImageFromHashTag;
import com.example.wbdvCS5610spring2020FinalProject.models.user.User;
import com.example.wbdvCS5610spring2020FinalProject.services.imagedata.IHashTagService;
import com.example.wbdvCS5610spring2020FinalProject.services.imagedata.IImageService;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class represents a controller for the Image table It provides the endpoints to call the API
 * to perform CRUD operations on Image.
 */
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ImageController {

  @Autowired
  IImageService imageService;

  @Autowired
  IHashTagService hashTagService;

  @GetMapping("/api/images")
  public List<Image> findAllImages() {
    return imageService.findAllImages();
  }

  @GetMapping("/api/images/hashtags/{hashtag}")
  public ResponseEntity<List<ImageFromHashTag>> findAllImagesWithHashTag(
      @PathVariable("hashtag") String hashtag) {
    return imageService.findAllImagesWithHashTag(hashtag);
  }

  @PostMapping("/api/images")
  public Image createImage(HttpSession httpSession, @RequestBody Image image) {
    Image newImage = imageService.createImage(httpSession, image).getBody();

    if(newImage != null) {
      List<HashTag> hashTagList = newImage.getHashTags();

      for(int i = 0;i < hashTagList.size(); i++) {
        HashTag hashTag = hashTagList.get(i);
        if(hashTag == null) {
          continue;
        } else if(hashTag.getHashTagText() == null || hashTag.getHashTagText().equals("")) {
          continue;
        }

        hashTag.setImage(newImage);
        hashTagService.save(hashTag);
      }
    }

    return newImage;
  }

  @GetMapping("/api/images/{imageUrl}")
  public ResponseEntity<Image> findImageByUrl(@PathVariable("imageUrl") String imageUrl) {
    return imageService.findImageByUrl(imageUrl);
  }

  @PostMapping("api/images/uploadprofile")
  public ResponseEntity<Image> uploadProfileImage(@RequestBody User user,
      @RequestBody Image image) {
    return imageService.uploadProfileImage(user, image);
  }

  @GetMapping("/api/images/getimage/{imageId}")
  public ResponseEntity<Image> getImageById(@PathVariable("imageId") Integer id) {
    return imageService.getImageById(id);
  }


  @DeleteMapping("/api/images/delete/{id}")
  public Integer deleteImageById(@PathVariable("id")Integer id) {
    return imageService.deleteImageById(id);
  }

}
