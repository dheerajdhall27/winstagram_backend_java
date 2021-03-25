package com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing;

import com.example.wbdvCS5610spring2020FinalProject.services.imageprocessing.EFilter;
import com.example.wbdvCS5610spring2020FinalProject.services.imageprocessing.ETransform;
import java.awt.image.BufferedImage;

/**
 * This interface represents the Image Model used apply filters and transformations to the Image.
 */
public interface IImageModel {

  BufferedImage filter(EFilter filterType, float[][][] imageInformation);

  BufferedImage transform(ETransform transformType, float[][][] imageInformation);
}
