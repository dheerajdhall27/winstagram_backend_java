package com.example.wbdvCS5610spring2020FinalProject.services.amazons3;

import com.example.wbdvCS5610spring2020FinalProject.utility.Utility;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/**
 * This class represents the implementation of the {@link IAmazonS3ClientService}. It provides the
 * user the ability to upload an Image to the S3 bucket and provide a link to
 */

@Service
public class IAmazonS3ClientServiceImpl implements IAmazonS3ClientService {
  private S3Client amazonS3Client;

  @Value("${aws.s3.image.bucket}")
  private String bucketName;

  @Value("${aws.access.key.id}")
  private String accessKeyId;

  @Value("${aws.access.key.secret}")
  private String secretKeyId;

  @Value("${aws.endpoint.url}")
  private String endpointUrl;

  @PostConstruct
  private void initializeAmazonClient() {
    AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKeyId, secretKeyId);
    amazonS3Client = S3Client.builder()
                        .region(Region.US_EAST_2)
                        .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                        .build();
  }

  @Override
  public String uploadFile(MultipartFile multipartFile) {

    StringBuilder builder = new StringBuilder();
    builder.append(endpointUrl).append("/").append(bucketName).append("/");

    try {
      File file = Utility.convertMultipartFileToFile(multipartFile);
      String fileName = generateFileName();
      builder.append(fileName);
      uploadFileToS3(file, fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return builder.toString();
  }


  private void uploadFileToS3(File file, String fileName) {
    amazonS3Client.putObject(PutObjectRequest.builder()
                                             .bucket(bucketName)
                                             .key(fileName)
                                             .build(), RequestBody.fromFile(file));
  }

  private String generateFileName() {
    UUID uuid = UUID.randomUUID();

    return uuid.toString();
  }

}
