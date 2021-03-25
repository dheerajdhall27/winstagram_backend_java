package com.example.wbdvCS5610spring2020FinalProject.controllers;

import com.example.wbdvCS5610spring2020FinalProject.services.imageprocessing.EFilter;
import com.example.wbdvCS5610spring2020FinalProject.services.imageprocessing.ETransform;
import com.example.wbdvCS5610spring2020FinalProject.services.imageprocessing.IImageProcessingServiceImpl;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class represents a Controller that provides the user the ability to apply certain filters to
 * the images. It also is a Rest Controller.
 */

@RestController
@CrossOrigin(origins = "*")
public class ImageProcessingController {

  private IImageProcessingServiceImpl imageProcessingService;

  @Autowired
  ImageProcessingController(IImageProcessingServiceImpl imageProcessingService) {
    this.imageProcessingService = imageProcessingService;
  }

  @RequestMapping(value = "/api/filter/blur", method = RequestMethod.POST, produces = MediaType.IMAGE_JPEG_VALUE)
  public String applyBlurFilter(@RequestPart(value = "file") MultipartFile multipartFile) {
    return Base64.getEncoder().encodeToString(imageProcessingService.filter(EFilter.BLUR, multipartFile));
  }

  @RequestMapping(value = "/api/filter/sharpen", method = RequestMethod.POST, produces = MediaType.IMAGE_JPEG_VALUE)
  public String applySharpenFilter(@RequestPart(value = "file") MultipartFile multipartFile) {
    return Base64.getEncoder().encodeToString(imageProcessingService.filter(EFilter.SHARPEN, multipartFile));
  }

  @RequestMapping(value = "/api/transform/grayscale", method = RequestMethod.POST, produces = MediaType.IMAGE_JPEG_VALUE)
  public String applyGrayscaleTransformation(
      @RequestPart(value = "file") MultipartFile multipartFile) {
    return Base64.getEncoder().encodeToString(imageProcessingService.transform(ETransform.GRAYSCALE, multipartFile));
  }

  @RequestMapping(value = "/api/transform/sepia", method = RequestMethod.POST, produces = MediaType.IMAGE_JPEG_VALUE)
  public String applySepiaTransformation(
      @RequestPart(value = "file") MultipartFile multipartFile) {
    return Base64.getEncoder().encodeToString(imageProcessingService.transform(ETransform.SEPIA, multipartFile));
  }
}
