package com.example.wbdvCS5610spring2020FinalProject.controllers;

import com.example.wbdvCS5610spring2020FinalProject.services.amazons3.IAmazonS3ClientServiceImpl;
import com.example.wbdvCS5610spring2020FinalProject.services.amazons3.IAmazonS3ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class represents a controller for the
 * {@link IAmazonS3ClientService}
 * It provides the endpoints to call the API to upload the Image files.
 */
@RestController
@RequestMapping("/api/S3Images/")
@CrossOrigin(origins = "*")
public class AmazonS3BucketController {

  private IAmazonS3ClientServiceImpl amazonS3ClientService;

  @Autowired
  AmazonS3BucketController(IAmazonS3ClientServiceImpl amazonS3ClientService) {
    this.amazonS3ClientService = amazonS3ClientService;
  }

  @PostMapping("/uploadImage")
  public String uploadFile(@RequestPart(value = "file")MultipartFile multipartFile) {
    return this.amazonS3ClientService.uploadFile(multipartFile);
  }
}
