package coder.teamplte.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
   @Id
   @GeneratedValue
   int id;

   String name;
   String image;

   @OneToMany
   @JoinColumn(name = "category_id")
   private List<Subcat> subcats = new ArrayList<>();

   @OneToMany
   @JoinColumn(name = "category_id")
   private List<Product> products = new ArrayList<>();

   public Category() {
   } // Hibernate

   public Category(String name, String image) {
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

   public List<Subcat> getSubcats() {
      return subcats;
   }

   public void setSubcats(List<Subcat> subcats) {
      this.subcats = subcats;
   }

   @Override
   public String toString() {
      return "Category{" + "id=" + id + ", name='" + name + '\'' + ", image='" + image + '\'' + '}';
   }
}
