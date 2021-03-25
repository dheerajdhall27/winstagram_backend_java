package com.example.wbdvCS5610spring2020FinalProject.services.imageprocessing;

import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.IImageModel;
import com.example.wbdvCS5610spring2020FinalProject.models.imageprocessing.IImageModelImpl;
import com.example.wbdvCS5610spring2020FinalProject.utility.Utility;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class provides the implementation of the IImag
 */
@Service
public class IImageProcessingServiceImpl implements IImageProcessingService {

  IImageModel imageModel;


  @PostConstruct
  private void initializeImageModel() {
    imageModel = new IImageModelImpl();
  }

  @Override
  public byte [] filter(EFilter filterType, MultipartFile multipartFile) {
    File updatedFile = new File("");
    try {
      File file = Utility.convertMultipartFileToFile(multipartFile);
      BufferedImage bufferedImage = ImageIO.read(file);
      float[][][] imageInformation = getImageInformation(bufferedImage, bufferedImage.getWidth(),
          bufferedImage.getHeight());
      BufferedImage updatedBufferImage = imageModel.filter(filterType, imageInformation);

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write( updatedBufferImage, "jpg", baos );
      baos.flush();
      byte[] imageInByte = baos.toByteArray();
      baos.close();

      updatedFile.delete();
      file.delete();
      return imageInByte;
    } catch (IOException e) {
      e.printStackTrace();
    }

    updatedFile.delete();
    return null;
  }

  @Override
  public byte[] transform(ETransform transformType, MultipartFile multipartFile) {
    try {
      File file = Utility.convertMultipartFileToFile(multipartFile);
      BufferedImage bufferedImage = ImageIO.read(file);
      float[][][] imageInformation = getImageInformation(bufferedImage, bufferedImage.getWidth(),
          bufferedImage.getHeight());
      BufferedImage updatedBufferImage = imageModel.transform(transformType, imageInformation);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      ImageIO.write( updatedBufferImage, "jpg", baos );
      baos.flush();
      byte[] imageInByte = baos.toByteArray();
      baos.close();

      file.delete();
      return imageInByte;

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * This method is used to extract the Image information from the {@link BufferedImage} class.
   */
  private static float[][][] getImageInformation(BufferedImage bufferedImage, int imgWidth,
      int imgHeight) {
    float[][][] imageInformation = new float[imgWidth][imgHeight][3];

    for (int i = 0; i < imageInformation.length; i++) {
      for (int j = 0; j < imageInformation[i].length; j++) {
        int pixelData = bufferedImage.getRGB(i, j);
        Color color = new Color(pixelData);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        imageInformation[i][j][0] = red;
        imageInformation[i][j][1] = green;
        imageInformation[i][j][2] = blue;
      }
    }

    return imageInformation;
  }
}
