package com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.imagekernel.filterkernel;

import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.imagekernel.AbstractImageKernel;

public class SharpenKernel extends AbstractImageKernel {

  private static float [][] kernelMatrix = {
      {-1.0f / 8, -1.0f / 8, -1.0f / 8, -1.0f / 8, -1.0f / 8},
      {-1.0f / 8,  1.0f / 4,  1.0f / 4,  1.0f / 4, -1.0f / 8},
      {-1.0f / 8,  1.0f / 4,  1.0f    ,  1.0f / 4, -1.0f / 8},
      {-1.0f / 8,  1.0f / 4,  1.0f / 84,  1.0f / 4, -1.0f / 8},
      {-1.0f / 8, -1.0f / 8, -1.0f / 8, -1.0f / 8, -1.0f / 8}
  };

  public SharpenKernel() {
    super(kernelMatrix);
  }
}
