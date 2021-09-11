package coder.teamplte.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUploadService {

   static String UPLOAD_FOLDER = "src/main/resources/static/uploads/";

   public String saveMultipleFIle(MultipartFile[] files) {
      String filenames = "";
      for (MultipartFile file : files) {
         filenames += "," + saveFile(file);
      }
      return filenames.substring(1);
   }

   public String saveFile(MultipartFile file) {
      String filename = file.getOriginalFilename();
      try {
         byte[] bytes = file.getBytes();
         Path path = Paths.get(UPLOAD_FOLDER + filename);
         Files.write(path, bytes);
      } catch (IOException e) {
         System.out.println("File Write Error");
      }
      return filename;
   }

}
