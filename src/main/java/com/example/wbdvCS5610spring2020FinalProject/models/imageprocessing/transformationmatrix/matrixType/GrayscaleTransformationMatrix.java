package com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.transformationmatrix.matrixType;

import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.transformationmatrix.AbstractTransformationMatrix;

public class GrayscaleTransformationMatrix extends AbstractTransformationMatrix {

  private static float[][] matrix = new float[][] {
      {0.2126f, 0.7512f, 0.0722f},
      {0.2126f, 0.7512f, 0.0722f},
      {0.2126f, 0.7512f, 0.0722f}
  };

  /**
   * This constructor is used to create a matrix for Grayscale Transformation
   */
  public GrayscaleTransformationMatrix() {
    super(matrix);
  }
}
