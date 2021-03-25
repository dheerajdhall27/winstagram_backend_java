package com.example.wbdvCS5610spring2020FinalProject.services.imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;

/**
 * This interface represents the ImageProcessing functionality. Provides access to using filter and
 * transformations on images.
 */
public interface IImageProcessingService {

  byte [] filter(EFilter filterType, MultipartFile multipartFile);

  byte [] transform(ETransform transformType, MultipartFile multipartFile);
}
