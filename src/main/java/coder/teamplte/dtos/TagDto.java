package coder.teamplte.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class TagDto {
   @NotNull
   @NotEmpty(message = "Tag နာမည် မပါလို့ မရဘူး")
   @Size(min = 5, max = 15)
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
