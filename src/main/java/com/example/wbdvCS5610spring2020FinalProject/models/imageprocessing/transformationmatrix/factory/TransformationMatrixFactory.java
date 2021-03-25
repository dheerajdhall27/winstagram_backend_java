package com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.transformationmatrix.factory;

import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.transformationmatrix.AbstractTransformationMatrix;
import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.transformationmatrix.matrixType.GrayscaleTransformationMatrix;
import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.transformationmatrix.matrixType.SepiaTransformationMatrix;
import com.example.wbdvCS5610spring2020FinalProject.services.imageprocessing.ETransform;

/**
 * This class represents a Factory that creates Transformation Matrix based on the type provided.
 */
public class TransformationMatrixFactory {

  /**
   * This method is used to create a matrix transformation based on the type.
   * @param transformType the type of matrix required
   */
  public static AbstractTransformationMatrix  buildTransformationMatrix(ETransform transformType) {
    switch (transformType) {
      case GRAYSCALE:
        return new GrayscaleTransformationMatrix();
      case SEPIA:
        return new SepiaTransformationMatrix();
      default:
        throw new IllegalArgumentException("Argument passed is not valid");
    }
  }
}
