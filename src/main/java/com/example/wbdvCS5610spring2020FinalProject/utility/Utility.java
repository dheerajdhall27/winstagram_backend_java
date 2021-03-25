package com.example.wbdvCS5610spring2020FinalProject.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.springframework.web.multipart.MultipartFile;

public class Utility {
  public static File convertMultipartFileToFile(MultipartFile file) throws IOException {
    File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
    FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);
    fileOutputStream.write(file.getBytes());
    fileOutputStream.close();
    return convertedFile;
  }
}
