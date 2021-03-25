package com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.transformationmatrix.matrixType;

import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.transformationmatrix.AbstractTransformationMatrix;

public class SepiaTransformationMatrix extends AbstractTransformationMatrix {

  private static float[][] matrix = new float[][]{
      {0.393f, 0.769f, 0.189f},
      {0.349f, 0.686f, 0.168f},
      {0.272f, 0.534f, 0.131f}
  };

  /**
   * This constructor is used to create a SepiaTransformation Matrix.
   */
  public SepiaTransformationMatrix() {
    super(matrix);
  }
}
