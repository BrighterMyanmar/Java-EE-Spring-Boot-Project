package coder.teamplte.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Subcat {
   @Id
   @GeneratedValue
   int id;

   String name, image;

   @OneToOne
   Category category;

   @OneToMany
   @JoinColumn(name = "subcat_id")
   private List<Childcat> childcats = new ArrayList<>();

   @OneToMany
   @JoinColumn(name = "subcat_id")
   private List<Product> products = new ArrayList<>();

   public Subcat() {
   }

   public Subcat(String name, String image) {
      this.name = name;
      this.image = image;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public Category getCategory() {
      return category;
   }

   public void setCategory(Category category) {
      this.category = category;
   }

   @Override
   public String toString() {
      return "Subcat{" + "id=" + id + ", name='" + name + '\'' + ", image='" + image + '\'' + '}';
   }
}
