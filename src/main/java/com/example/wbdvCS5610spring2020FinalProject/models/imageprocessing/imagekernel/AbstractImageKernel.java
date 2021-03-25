package com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.imagekernel;

/**
 * This class represents the implementation of the {@link IImageKernel}. This is an abstract class
 * and it cannot be instantiated. It hold the information regarding the multiple Kernels.
 */
public abstract class AbstractImageKernel implements IImageKernel{

  protected float[][] kernelData;

  /**
   * This constructor acts as a placeholder. Since this is an abstract class it cannot be
   * instantiated.
   * @param kernelData the kernel information which is a 2D matrix
   */
  public AbstractImageKernel(float[][] kernelData) {
    this.kernelData = kernelData;
  }

  @Override
  public float[][] getKernelData() {
    return kernelData;
  }
}

