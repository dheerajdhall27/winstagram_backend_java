package com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.imagekernel.filterkernel;

import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.imagekernel.AbstractImageKernel;

public class BlurKernel extends AbstractImageKernel {

  private static float [][] kernelMatrix = {
      {1f / 16, 1f / 8, 1f / 16},
      {1f / 8, 1f / 4, 1f / 8},
      {1f / 16, 1f / 8, 1f / 16}
  };

  public BlurKernel() {
    super(kernelMatrix);
  }
}
