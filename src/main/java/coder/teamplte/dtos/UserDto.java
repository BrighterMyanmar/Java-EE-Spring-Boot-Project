package coder.teamplte.dtos;

public class UserDto {
   String name;
   String email;
   String phone;
   String password;

   public UserDto() {
   }

   public UserDto(String name, String email, String phone, String password) {
      this.name = name;
      this.email = email;
      this.phone = phone;
      this.password = password;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

}
