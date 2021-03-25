package com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.transformationmatrix;

public abstract class AbstractTransformationMatrix implements ITransformationMatrix {

  protected float[][] matrixData;

  /**
   * This constructor is more of a placeholder. This class cannot be instantiated.
   */
  public AbstractTransformationMatrix(float[][] matrixData) {
    this.matrixData = matrixData;
  }

  @Override
  public float[][] getMatrixData() {
    return matrixData;
  }
}
