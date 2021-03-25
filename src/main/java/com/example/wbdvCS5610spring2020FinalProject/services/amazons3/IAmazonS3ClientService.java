package com.example.wbdvCS5610spring2020FinalProject.services.amazons3;

import org.springframework.web.multipart.MultipartFile;

/**
 * This interface represents a client which is used to interact with the S3 bucket to store Images.
 */
public interface  IAmazonS3ClientService {

  /**
   * This method is used to upload an Image file to the S3 bucket.
   * @param file the file that is being uploaded to the bucket (Image File)
   * @return
   */
  String uploadFile(MultipartFile file);
}
