package coder.teamplte.dtos;

import org.springframework.web.multipart.MultipartFile;

public class TagDto {
   private String name;
   private MultipartFile file;

   public TagDto() {
   };

   public TagDto(String name, MultipartFile file) {
      this.name = name;
      this.file = file;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public MultipartFile getFile() {
      return this.file;
   }

   public void setFile(MultipartFile file) {
      this.file = file;
   }

   public String toString() {
      return "Named : " + this.name + " File : " + this.file.getOriginalFilename().toString();
   }

}
