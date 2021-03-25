package com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.imagekernel.factory;

import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.imagekernel.AbstractImageKernel;
import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.imagekernel.filterkernel.BlurKernel;
import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.imagekernel.filterkernel.SharpenKernel;
import com.example.wbdvCS5610spring2020FinalProject.services.imageprocessing.EFilter;

/**
 * This class represents a Kernel Factory which creates the Kernel based on the type provided.
 */
public class KernelFactory {

  /**
   * This method is used to create a Kernel for the filter option. It takes the type of the filter
   * and returns the required kernel.
   * @param filterType represents the filter type whose Kernel needs to be instantiated
   * @return the appropriate Kernel type
   * @throws IllegalArgumentException if the filterType is null or invalid
   */
  public static AbstractImageKernel buildFilterKernel(EFilter filterType)
      throws IllegalArgumentException {
    if (filterType == null) {
      throw new IllegalArgumentException("The filter type cannot be null");
    }

    switch (filterType) {
      case BLUR:
        return new BlurKernel();
      case SHARPEN:
        return new SharpenKernel();
      default:
        throw new IllegalArgumentException("Argument passed is Invalid");
    }
  }
}
