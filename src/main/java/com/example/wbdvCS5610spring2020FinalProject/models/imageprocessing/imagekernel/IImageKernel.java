package com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.imagekernel;

/**
 * This interface represents the various Kernels, which can be used to apply filters and
 * transformations to the images.
 */
public interface IImageKernel {
  float [][] getKernelData();
}
